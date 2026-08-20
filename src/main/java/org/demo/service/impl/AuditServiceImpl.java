package org.demo.service.impl;

import org.demo.Event;
import org.demo.EventType;
import org.demo.Stats;
import org.demo.service.AuditService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class AuditServiceImpl  implements AuditService {
    private Map<String, TreeMap<Long, List<Event>>> userActivityLog;

    public AuditServiceImpl() {
        this.userActivityLog = new ConcurrentHashMap<>();
    }

    @Override
    public List<Event> getActivityLog(String userId, Long start, Long end) {
        long from = (start != null) ? start : 0L;
        long to = (end != null) ? end : Long.MAX_VALUE;

        return userActivityLog.get(userId).subMap(from, true, to, true)
                .values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Stats getStatistics(String userId, Long start, Long end) {
        List<Event> events = getActivityLog(userId, start, end);

        int added = 0, completed = 0, spilled = 0;
        for (Event e : events) {
            if (e.getType() == EventType.ADDED) added++;
            if (e.getType() == EventType.COMPLETED) completed++;
            if (e.getType() == EventType.SPILLED) spilled++;
        }

        return new Stats(added, completed, spilled);
    }

    @Override
    public void logEvent(Event event) {

        if (!userActivityLog.containsKey(event.getUserId())) {
            userActivityLog.put(event.getUserId(), new TreeMap<>());
        }
        TreeMap<Long, List<Event>> log = userActivityLog.get(event.getUserId());

        if (!log.containsKey(event.getTimestamp())) {
            log.put(event.getTimestamp(), new ArrayList<>());
        }
        log.get(event.getTimestamp()).add(event);
    }
}
