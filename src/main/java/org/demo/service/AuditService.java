package org.demo.service;

import org.demo.Event;
import org.demo.Stats;

import java.util.List;

public interface AuditService {
    List<Event> getActivityLog(String userId, Long start, Long end);
    Stats getStatistics(String userId, Long start, Long end);
    void logEvent(Event event);
}
