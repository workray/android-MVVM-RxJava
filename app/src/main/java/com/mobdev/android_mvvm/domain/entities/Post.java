package com.mobdev.android_mvvm.domain.entities;

import java.util.Date;
import java.util.UUID;

/**
 * Created by mobdev125 on 2/19/18.
 */

public class Post {
    private String body;
    private String title;
    private String uid;
    private String userId;
    private String createdAt;

    public Post(String body, String title, String uid, String userId, String createdAt) {
        this.body = body;
        this.title = title;
        this.uid = uid;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Post(String body, String title) {
        this(body, title, UUID.randomUUID().toString(), "5", Integer.toString(Math.round((new Date()).getTime() * 1000)));
    }

    public boolean equals(Post obj) {
        return body.equals(obj.body) &&
                title.equals(obj.title) &&
                uid.equals(obj.uid) &&
                userId.equals(obj.userId) &&
                createdAt.equals(obj.createdAt);
    }

    public String getBody() { return body; }
    public void setBody(String value) { this.body = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getUid() { return uid; }
    public void setUid(String value) { this.uid = value; }

    public String getUserID() { return userId; }
    public void setUserID(String value) { this.userId = value; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String value) { this.createdAt = value; }
}
