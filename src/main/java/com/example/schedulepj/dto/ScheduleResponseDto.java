package com.example.schedulepj.dto;

import com.schedule.entity.Schedule;
import java.time.LocalDateTime;

public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // Entity -> DTO 변환 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.author = schedule.getAuthor();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }

    // Getter, Setter
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