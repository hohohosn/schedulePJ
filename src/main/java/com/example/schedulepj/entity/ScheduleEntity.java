package com.example.schedulepj.entity;

import java.time.LocalDateTime;

public class ScheduleEntity {
    private Long id;
    private String todo;
    private String author;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // 생성자
    public ScheduleEntity() {
    }

    public ScheduleEntity(String todo, String author, String password) {
        this.todo = todo;
        this.author = author;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    // Getter, Setter 메서드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
