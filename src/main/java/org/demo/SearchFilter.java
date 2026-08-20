package org.demo;

import java.time.LocalDateTime;
import java.util.Set;

public class SearchFilter {
    private Set<String> tags;
    private LocalDateTime deadlineFrom;
    private LocalDateTime deadlineTo;
    private TaskStatus status;
    private SortBy sortBy;

    public SearchFilter(Set<String> tags, LocalDateTime deadlineFrom, LocalDateTime deadlineTo, TaskStatus status, SortBy sortBy) {
        this.tags = tags;
        this.deadlineFrom = deadlineFrom;
        this.deadlineTo = deadlineTo;
        this.status = status;
        this.sortBy = sortBy;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getDeadlineFrom() {
        return deadlineFrom;
    }

    public void setDeadlineFrom(LocalDateTime deadlineFrom) {
        this.deadlineFrom = deadlineFrom;
    }

    public LocalDateTime getDeadlineTo() {
        return deadlineTo;
    }

    public void setDeadlineTo(LocalDateTime deadlineTo) {
        this.deadlineTo = deadlineTo;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }
}
