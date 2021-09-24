package com.sales.market.data.dto;

public class MailAttachmentDTO<T> {

    private String attachmentName;
    private T attachment;

    public MailAttachmentDTO(String attachmentName, T attachment) {
        super();
        this.attachmentName = attachmentName;
        this.attachment = attachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public T getAttachment() {
        return attachment;
    }

    public void setAttachment(T attachment) {
        this.attachment = attachment;
    }

}
