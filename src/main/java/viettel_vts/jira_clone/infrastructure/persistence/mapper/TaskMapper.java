package viettel_vts.jira_clone.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import viettel_vts.jira_clone.domain.model.Task;
import viettel_vts.jira_clone.infrastructure.persistence.entity.TaskEntity;

@Component
public class TaskMapper {
    public TaskEntity toEntity(Task domain) {
        TaskEntity entity = new TaskEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setAssigneeId(domain.getAssigneeId());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setStatus(domain.getStatus().name());
        return entity;
    }

    public Task toDomain(TaskEntity entity) {
        return new Task.Builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .assigneeId(entity.getAssingeeId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .status(Task.Status.valueOf(entity.getStatus()))
                .build();
    }
}
