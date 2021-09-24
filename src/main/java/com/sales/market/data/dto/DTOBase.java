/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sales.market.data.model.ModelBase;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;


@Getter
@Setter
@SuppressWarnings("rawtypes")
public class DTOBase<E extends ModelBase> {
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    protected final Logger logger = LoggerFactory.getLogger(DTOBase.class);
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date updatedAt;
    private long version;

    protected void beforeConversion(E element, ModelMapper mapper) {
        // Do nothing
    }

    protected void afterConversion(E element, ModelMapper mapper) {
        // Do nothing
    }

    @SuppressWarnings("unchecked")
    public <D extends DTOBase> D toDto(E element, ModelMapper mapper) {
        if (element == null) {
            return (D) this;
        }
        return convert(element, mapper);
    }

    @SuppressWarnings("unchecked")
    protected <D extends DTOBase> D convert(E element, ModelMapper mapper) {
        beforeConversion(element, mapper);
        try {
            mapper.map(element, this);
        } catch (Exception ex) {
            setId(element.getId());
            logger.error("Error mapping", ex);
            return (D) this;
        }
        afterConversion(element, mapper);
        return (D) this;
    }

    public <D extends DTOBase> List<D> toListDto(Collection<E> elements, ModelMapper mapper) {
        if (elements == null || elements.isEmpty()) {
            return Collections.emptyList();
        }
        return convert(elements, mapper);
    }

    public <D extends DTOBase> Set<D> toSetDto(Set<E> elements, ModelMapper mapper) {
        if (elements == null || elements.isEmpty()) {
            return Collections.emptySet();
        }
        return convertToSet(elements, mapper);
    }

    @SuppressWarnings("unchecked")
    protected <D extends DTOBase> Set<D> convertToSet(Collection<E> elements, ModelMapper mapper) {
        return (Set<D>) elements.stream().map(element -> {
            try {
                return this.getClass().newInstance().toDto(element, mapper);
            } catch (InstantiationException | IllegalAccessException e) {
                return new DTOBase<>();
            }
        }).sorted(Comparator.comparing(DTOBase::getId)).collect(Collectors.toSet());
    }

    @SuppressWarnings("unchecked")
    protected <D extends DTOBase> List<D> convert(Collection<E> elements, ModelMapper mapper) {
        return (List<D>) elements.stream().map(element -> {
            try {
                return this.getClass().newInstance().toDto(element, mapper);
            } catch (InstantiationException | IllegalAccessException e) {
                return new DTOBase<>();
            }
        }).sorted(Comparator.comparing(DTOBase::getId)).collect(Collectors.toList());
    }

}
