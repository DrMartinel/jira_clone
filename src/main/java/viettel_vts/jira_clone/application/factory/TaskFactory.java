package viettel_vts.jira_clone.application.factory;

import org.springframework.stereotype.Component;
import viettel_vts.jira_clone.domain.model.Task;

@Component
public class TaskFactory {
    public Task createNewTask(String title, String description, String assigneeId) {

        return new Task.Builder()
                .title(title)
                .description(description)
                .assigneeId(assigneeId)
                .status(Task.Status.TODO)
                .build();
    }
}