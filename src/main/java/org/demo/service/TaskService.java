package org.demo.service;


import org.demo.SearchFilter;
import org.demo.Task;

import java.util.List;

public interface TaskService {
    Task addTask(String userId, Task task);
    Task getTask(String userId, String taskId);
    Task modifyTask(String userId, Task task);
    void removeTask(String userId, String taskId);
    List<Task> listTasks(String userId, SearchFilter filter);
    void completeTask(String userId, String taskId);
}
