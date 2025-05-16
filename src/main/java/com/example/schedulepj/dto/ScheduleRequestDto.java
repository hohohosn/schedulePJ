package com.example.schedulepj.dto;

public class ScheduleRequestDto {
    private String todo;
    private String author;
    private String password;

    // 생성자, Getter, Setter
    public ScheduleRequestDto() {
    }

    public ScheduleRequestDto(String todo, String author, String password) {
        this.todo = todo;
        this.author = author;
        this.password = password;
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
}