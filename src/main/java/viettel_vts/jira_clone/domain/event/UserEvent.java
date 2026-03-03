package viettel_vts.jira_clone.domain.event;

import viettel_vts.jira_clone.domain.model.User;
import java.time.LocalDateTime;

public class UserEvent {
    public enum EventType {
        CREATED,
        UPDATED,
        DELETED
    }

    private final String userId;
    private final EventType eventType;
    private final User userSnapshot;
    private final LocalDateTime occurredOn;

    public UserEvent(User user, EventType eventType) {
        if (user == null || eventType == null) {
            throw new IllegalArgumentException("User and EventType are required");
        }
        this.userId = user.getId();
        this.eventType = eventType;
        this.userSnapshot = user;
        this.occurredOn = LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public User getUserSnapshot() {
        return userSnapshot;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }
}
