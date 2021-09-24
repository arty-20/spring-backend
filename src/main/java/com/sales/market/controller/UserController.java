package com.sales.market.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sales.market.data.dto.*;
import com.sales.market.data.model.User;
import com.sales.market.service.EmailService;
import com.sales.market.service.interfaz.GenericService;
import com.sales.market.config.security.TokenService;
import com.sales.market.service.interfaz.UserService;
import io.jsonwebtoken.JwtException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class UserController extends GenericController<User, UserDTO> {

    private final UserService userService;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, TokenService tokenService, EmailService emailService,
            AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/users/employee")
    public ResponseEntity<EmployeeDTO> getUserEmployee(@RequestBody UserDTO userDto) {
        try {
            return new ResponseEntity<>(getEmployee(userService.findByEmail(userDto.getEmail())), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("login")
    public ResponseEntity<Object> signIn(@RequestBody UserDTO userDto) throws JsonProcessingException {
        ResponseEntity<Object> responseEntity = null;
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
            responseEntity = new ResponseEntity<>(
                    new TokenDTO(tokenService.generateTokenByDay(10, authentication.getPrincipal(), true)),
                    HttpStatus.OK);
        } catch (AuthenticationException e) {
            responseEntity = new ResponseEntity<>(new OperationResultDTO<>("messages.user.invalidCredentials"),
                    HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    /**
     * This method check if the token is valid and also checks if the user
     * performing the action is the same for whom the token was created.
     *
     * @throws IOException
     */
    @PostMapping("/users")
    public ResponseEntity<Object> signUp(@RequestBody UserDTO userDto, @RequestParam("token") String token)
            throws IOException {
        ResponseEntity<Object> responseEntity = null;
        try {
            UserDTO tokenInformation = tokenService.getTokenInformation(token, UserDTO.class);

            if (!userService.isUserRegistered(tokenInformation.getEmail())) {
                userService.save(userDto.getFirstName(), userDto.getLastName(), tokenInformation.getEmail(),
                        userDto.getPassword());
                responseEntity = new ResponseEntity<>(new TokenDTO(tokenService.generateTokenByDay(10,
                        userService.findUserDetails(tokenInformation.getEmail()), true)), HttpStatus.OK);
            } else {
                throw new ValidationException();
            }
        } catch (JwtException e) {
            responseEntity = new ResponseEntity<>(new OperationResultDTO<>("messages.user.unauthorized"),
                    HttpStatus.UNAUTHORIZED);
        } catch (ValidationException e) {
            responseEntity = new ResponseEntity<>(new OperationResultDTO<>("messages.user.duplicatedUser"),
                    HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("sendSignUpInvitation")
    public ResponseEntity<Object> inviteNewUser(@Valid @RequestBody UserDTO user,
            @RequestParam("redirect") String redirect)
            throws JsonProcessingException {
        Map<String, Object> parameters = new HashMap<>();
        String[] to = {user.getEmail()};
        String url = redirect + "?token=" + tokenService.generateTokenByDay(1, user, false);
        parameters.put("invitationLink", url);
        emailService.sendMail(new MailDTO(to, "Subscription link", "invitation-template", parameters));
        return new ResponseEntity<>(new OperationResultDTO<>("messages.user.invitedUser"), HttpStatus.OK);
    }

    @PostMapping("/forgottenPassword")
    public ResponseEntity<Object> sendForgottenPasswordEmail(@Valid @RequestBody UserDTO user,
            @RequestParam("redirect") String redirect)
            throws JsonProcessingException {
        ResponseEntity<Object> responseEntity = null;
        if (userService.isUserRegistered(user.getEmail())) {
            Map<String, Object> parameters = new HashMap<>();
            String[] to = {user.getEmail()};
            String url = redirect + "?token=" + tokenService.generateTokenByDay(1, user, false);
            parameters.put("forgottenPasswordLink", url);
            emailService.sendMail(new MailDTO(to, "Forgotten password", "forgotten-password-template", parameters));
            responseEntity = new ResponseEntity<>(new OperationResultDTO<>("messages.user.forgottenPasswordSent"),
                    HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(new OperationResultDTO<>("messages.user.userNotFound"),
                    HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping("restorePassword")
    public ResponseEntity<Object> restorePassword(@RequestBody UserDTO userDto, @RequestParam("token") String token)
            throws IOException {
        ResponseEntity<Object> responseEntity = null;
        try {
            UserDTO tokenInformation = tokenService.getTokenInformation(token, UserDTO.class);
            int operationResult = userService.updatePasswordByEmail(tokenInformation.getEmail(), userDto.getPassword());
            responseEntity = (operationResult == 1)
                    ? new ResponseEntity<>(new TokenDTO(tokenService.generateTokenByDay(10,
                    userService.findUserDetails(tokenInformation.getEmail()), true)), HttpStatus.OK)
                    : new ResponseEntity<>(new OperationResultDTO<>("messages.user.notRestoredPassword"),
                    HttpStatus.BAD_REQUEST);
        } catch (JwtException e) {
            responseEntity = new ResponseEntity<>(new OperationResultDTO<>("messages.token.invalidToken"),
                    HttpStatus.UNAUTHORIZED);
        }
        return responseEntity;
    }

    @GetMapping("/users")
    public Page<UserDTO> findPaginatedUsers(@RequestParam("page") int page, @RequestParam("size") int size,
                                            @RequestParam("filter") String filter,
                                            @RequestParam("isAsc") boolean isAsc) {
        Page<UserDTO> paginatedResults = userService
                .findUsers(PageRequest.of(page - 1, size, super.getSortType(isAsc, filter))).map(this::toDto);
        return paginatedResults;
    }

    @GetMapping("/users" + "/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(toDto(user), HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<OperationResultDTO<UserDTO>> updateUser(@RequestBody UserDTO userDto) {
        User model = toModel(userDto);
        userService.update(model);
        return new ResponseEntity<>(new OperationResultDTO<>("messages.user.updatedUser", toDto(model)), HttpStatus.OK);
    }

    @Override
    @GetMapping("/users" + "/generic")
    public List<UserDTO> findAll(@RequestParam(FILTER) String filter) {
        return super.findAll(filter);
    }

    @Override
    protected GenericService<User> getService() {
        return userService;
    }

    private EmployeeDTO getEmployee(User user) {
        if (user != null && user.getEmployee() != null) {
            return new EmployeeDTO().toDto(user.getEmployee(), modelMapper);
        }
        throw new NoSuchElementException("User does not have an employee asociated or does not exist");
    }

    @Override
    @GetMapping(value = "/user/model/{id}")
    public User findModelById(Long id) {
        return super.findModelById(id);
    }

}
