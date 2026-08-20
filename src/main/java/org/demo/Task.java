package org.demo;

import java.time.LocalDateTime;
import java.util.Set;

public class Task {
    private String taskId;
    private String userId;
    private String title;
    private Set<String> tags;
    private LocalDateTime deadline;
    private LocalDateTime visibleFrom;
    private TaskStatus status;
    private LocalDateTime completedTime;
    private boolean spilled;
    private LocalDateTime createdAt;

    public Task(String taskId, String userId, String title, Set<String> tags, LocalDateTime deadline, LocalDateTime visibleFrom, TaskStatus status, LocalDateTime completedTime, boolean spilled, LocalDateTime createdAt) {
        this.taskId = taskId;
        this.userId = userId;
        this.title = title;
        this.tags = tags;
        this.deadline = deadline;
        this.visibleFrom = visibleFrom;
        this.status = status;
        this.completedTime = completedTime;
        this.spilled = spilled;
        this.createdAt = createdAt;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getVisibleFrom() {
        return visibleFrom;
    }

    public void setVisibleFrom(LocalDateTime visibleFrom) {
        this.visibleFrom = visibleFrom;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
    }

    public boolean isSpilled() {
        return spilled;
    }

    public void setSpilled(boolean spilled) {
        this.spilled = spilled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
