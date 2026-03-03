package viettel_vts.jira_clone.domain.event;

import viettel_vts.jira_clone.domain.model.Task;
import java.time.LocalDateTime;

public class TaskEvent {

    public enum EventType {
        CREATED,
        STATUS_UPDATED,
        DELETED
    }

    private final String taskId;
    private final EventType eventType;
    private final Task taskSnapshot;
    private final LocalDateTime occurredOn;

    public TaskEvent(Task task, EventType eventType) {
        if (task == null || eventType == null) {
            throw new IllegalArgumentException("Task and EventType are required");
        }
        this.taskId = task.getId();
        this.eventType = eventType;
        this.taskSnapshot = task;
        this.occurredOn = LocalDateTime.now();
    }

    public String getTaskId() {
        return taskId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Task getTaskSnapshot() {
        return taskSnapshot;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }
}