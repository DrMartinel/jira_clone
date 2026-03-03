package viettel_vts.jira_clone.domain.event;

public interface EventPublisher {

    void publish(TaskEvent event);

    void publish(UserEvent event);
}
