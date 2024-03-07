package com.ndinhthi04.app;

import java.util.HashMap;

public class ToDo {
    public String id;
    public String title;
    public String content;

    public ToDo() {
        // No-argument constructor
    }

    public ToDo(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Hàm convert dữ liệu haspmap lưu vào firebase
    public HashMap<String, Object> convertHashMap() {
        HashMap<String, Object> h = new HashMap<>();
        h.put("id", id);
        h.put("title", title);
        h.put("content", content);
        return h;
    }
}