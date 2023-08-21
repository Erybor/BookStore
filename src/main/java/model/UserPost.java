package model;

import java.sql.Timestamp;

public class UserPost {
    private int postId;
    private int userId;
    private String content;
    private Timestamp dateCreated;

    public UserPost() {}

    public UserPost(int userId, String content, Timestamp dateCreated) {
        this.userId = userId;
        this.content = content;
        this.dateCreated = dateCreated;
    }


    public UserPost(int postId, int userId, String content, Timestamp dateCreated) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.dateCreated = dateCreated;
    }

    // Getter and Setter methods for all fields

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }


    @Override
    public String toString() {
        return "UserPost{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
