package viettel_vts.jira_clone.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel_vts.jira_clone.application.factory.TaskFactory;
import viettel_vts.jira_clone.domain.event.EventPublisher;
import viettel_vts.jira_clone.domain.event.TaskEvent;
import viettel_vts.jira_clone.domain.model.Task;
import viettel_vts.jira_clone.domain.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final EventPublisher eventPublisher;
    private final TaskFactory taskFactory;

    public TaskService(TaskRepository taskRepository,
            EventPublisher eventPublisher,
            TaskFactory taskFactory) {
        this.taskRepository = taskRepository;
        this.eventPublisher = eventPublisher;
        this.taskFactory = taskFactory;
    }

    @Transactional
    public Task createTask(String title, String description, String assigneeId) {

        Task newTask = taskFactory.createNewTask(title, description, assigneeId);
        taskRepository.save(newTask);
        eventPublisher.publish(new TaskEvent(newTask, TaskEvent.EventType.CREATED));

        return newTask;
    }

    @Transactional
    public void updateTaskStatus(String taskId, Task.Status newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + taskId));
        task.transitionTo(newStatus);
        taskRepository.save(task);
        eventPublisher.publish(new TaskEvent(task, TaskEvent.EventType.STATUS_UPDATED));
    }

    @Transactional
    public void deleteTask(String taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + taskId));
        taskRepository.delete(task);
        eventPublisher.publish(new TaskEvent(task, TaskEvent.EventType.DELETED));
    }
}