package org.demo;

public class Event {
    private String eventId;
    private String userId;
    private String taskId;
    private EventType type;
    private long timestamp;

    public Event(String eventId, String userId, String taskId, EventType type, long timestamp) {
        this.eventId = eventId;
        this.userId = userId;
        this.taskId = taskId;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
