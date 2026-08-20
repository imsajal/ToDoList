package org.demo;

import org.demo.service.AuditService;
import org.demo.service.TaskService;
import org.demo.service.impl.AuditServiceImpl;
import org.demo.service.impl.TaskServiceImpl;

import java.time.LocalDateTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        AuditService auditService = new AuditServiceImpl();
        TaskService taskService = new TaskServiceImpl(auditService);
        String user1 = "user1", user2 = "user2";

        Task t1 = new Task(UUID.randomUUID().toString(), user1, "report",
                new HashSet<>(Arrays.asList("work")), LocalDateTime.now().plusDays(1),
                LocalDateTime.now(), TaskStatus.PENDING, null, false, LocalDateTime.now());
        t1 = taskService.addTask(user1, t1);
        System.out.println("Tags " + t1.getTags());
        t1.getTags().add("routine");
        taskService.modifyTask(user1, t1);
        System.out.println(t1.getTags());

        Task t2 = new Task(UUID.randomUUID().toString(), user2, "report 2",
                new HashSet<>(Arrays.asList("scrum notes")), LocalDateTime.now().plusDays(1),
                LocalDateTime.now(), TaskStatus.PENDING, null, false, LocalDateTime.now());
        t2 = taskService.addTask(user1, t2);
        taskService.removeTask(user1, t2.getTaskId());

        SearchFilter filter = new SearchFilter(null, null, null, TaskStatus.PENDING, SortBy.CREATED_AT_ASC);
        System.out.println("Status T1 " + taskService.listTasks(user1, filter).get(0).getStatus());

        taskService.completeTask(user1, t1.getTaskId());
        System.out.println(t1.getStatus());
        long start = 0L;
        long end = System.currentTimeMillis();

        List<Event>  events = auditService.getActivityLog(user1, null, null);
        for(Event event : events){
            System.out.println("User  " + " " + event.getUserId() + " " +
                       "Event Type " + event.getType());
        }

        Stats stats = auditService.getStatistics(user1, start, end);
        System.out.println("Added " + stats.getAdded());
        System.out.println("Completed " + stats.getCompleted());
        System.out.println("Spilled " + stats.getSpilled());

    }
}