package org.demo.service.impl;


import org.demo.*;
import org.demo.service.AuditService;
import org.demo.service.TaskService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {
    private Map<String, Map<String, Task>> userTasks;
    private AuditService auditService ;

    public TaskServiceImpl(AuditService auditService) {
        this.userTasks = new ConcurrentHashMap<>();
        this.auditService = auditService;
    }

    @Override
    public Task addTask(String userId, Task task) {
        task.setTaskId(UUID.randomUUID().toString());
        task.setUserId(userId);
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        if (task.getVisibleFrom() == null) task.setVisibleFrom(LocalDateTime.now());

        userTasks.computeIfAbsent(userId, k -> new ConcurrentSkipListMap<>())
                .put(task.getTaskId(), task);

        log(userId, task.getTaskId(), EventType.ADDED);
        return task;
    }

    @Override
    public Task getTask(String userId, String taskId) {
        return userTasks.get(userId).get(taskId);
    }

    @Override
    public Task modifyTask(String userId, Task task) {
        userTasks.get(userId).put(task.getTaskId(), task);
        log(userId, task.getTaskId(), EventType.MODIFIED);
        return task;
    }

    @Override
    public void removeTask(String userId, String taskId) {
        userTasks.get(userId).remove(taskId);
        log(userId, taskId, EventType.REMOVED);

    }

    @Override
    public List<Task> listTasks(String userId, SearchFilter filter) {
        if (!userTasks.containsKey(userId)) return new ArrayList<Task>();
        return userTasks.get(userId).values().stream()
                .filter(t -> !t.getVisibleFrom().isAfter(LocalDateTime.now()))
                .filter(t -> filter.getStatus() == null || t.getStatus().equals(filter.getStatus()))
                .filter(t -> filter.getTags() == null || t.getTags().containsAll(filter.getTags()))
                .sorted(sortComparator(filter.getSortBy()))
                .collect(Collectors.toList());
    }

    @Override
    public void completeTask(String userId, String taskId) {

        Task task = userTasks.get(userId).get(taskId);
        task.setStatus(TaskStatus.COMPLETED);
        task.setCompletedTime(LocalDateTime.now());

        if (task.getDeadline() != null && task.getCompletedTime().isAfter(task.getDeadline())) {
            task.setSpilled(true);
        }

        userTasks.get(userId).remove(taskId); // auto-removed once completed
        log(userId, taskId, EventType.COMPLETED);
        if (task.isSpilled()) log(userId, taskId, EventType.SPILLED);

    }

    private void log(String userId, String taskId, EventType type) {
        auditService.logEvent(new Event(UUID.randomUUID().toString(), userId, taskId, type, System.currentTimeMillis()));
    }

    private Comparator<Task> sortComparator(SortBy sortBy) {
        if(sortBy == null) sortBy = SortBy.CREATED_AT_ASC;
        Comparator<Task> comparator = null;
        switch (sortBy) {
            case DEADLINE_DESC:
                comparator = (a, b) -> b.getDeadline().compareTo(a.getDeadline());
            case CREATED_AT_ASC:
                comparator = (a, b) -> a.getCreatedAt().compareTo(b.getCreatedAt());
            case DEADLINE_ASC:
                comparator = (a, b) -> a.getDeadline().compareTo(b.getDeadline());
        }
        return comparator;
    }
}
