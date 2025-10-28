package com.inboop.backend.instagram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WebhookPayload {

    @JsonProperty("object")
    private String object;

    @JsonProperty("entry")
    private List<Entry> entry;

    public static class Entry {
        @JsonProperty("id")
        private String id;

        @JsonProperty("time")
        private Long time;

        @JsonProperty("messaging")
        private List<Messaging> messaging;

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }

        public List<Messaging> getMessaging() {
            return messaging;
        }

        public void setMessaging(List<Messaging> messaging) {
            this.messaging = messaging;
        }
    }

    public static class Messaging {
        @JsonProperty("sender")
        private Participant sender;

        @JsonProperty("recipient")
        private Participant recipient;

        @JsonProperty("timestamp")
        private Long timestamp;

        @JsonProperty("message")
        private MessageData message;

        // Getters and Setters
        public Participant getSender() {
            return sender;
        }

        public void setSender(Participant sender) {
            this.sender = sender;
        }

        public Participant getRecipient() {
            return recipient;
        }

        public void setRecipient(Participant recipient) {
            this.recipient = recipient;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public MessageData getMessage() {
            return message;
        }

        public void setMessage(MessageData message) {
            this.message = message;
        }
    }

    public static class Participant {
        @JsonProperty("id")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class MessageData {
        @JsonProperty("mid")
        private String mid;

        @JsonProperty("text")
        private String text;

        @JsonProperty("attachments")
        private List<Attachment> attachments;

        // Getters and Setters
        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Attachment> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<Attachment> attachments) {
            this.attachments = attachments;
        }
    }

    public static class Attachment {
        @JsonProperty("type")
        private String type;

        @JsonProperty("payload")
        private AttachmentPayload payload;

        // Getters and Setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public AttachmentPayload getPayload() {
            return payload;
        }

        public void setPayload(AttachmentPayload payload) {
            this.payload = payload;
        }
    }

    public static class AttachmentPayload {
        @JsonProperty("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    // Getters and Setters
    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
