package co.craft.model;

/**
 * Created by rklimemnko on 06.07.2016.
 */
public class Message {

    private long userId;
    private long id;
    private String title;
    private String body;

    public Message(){}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (userId != message.userId) return false;
        if (id != message.id) return false;
        if (title != null ? !title.equals(message.title) : message.title != null) return false;
        return !(body != null ? !body.equals(message.body) : message.body != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    private Message(long userId, long id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public static class MessageBuilder {
        private long userId;
        private long id;
        private String title;
        private String body;

        public MessageBuilder addUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public MessageBuilder addId(long id) {
            this.id = id;
            return this;
        }

        public MessageBuilder addTitle(String title) {
            this.title = title;
            return this;
        }

        public MessageBuilder addBody(String body) {
            this.body = body;
            return this;
        }

        public Message build() {
            return new Message(userId,id,title,body);
        }
    }
}
