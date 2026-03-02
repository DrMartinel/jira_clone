package viettel_vts.jira_clone.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private final String id;
    private final String title;
    private final String description;
    private final String assigneeId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private String status;

    private Task(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.assigneeId = builder.assigneeId;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.status = builder.status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {
        private String id;
        private String title;
        private String description;
        private String assigneeId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String status;

        public Builder id(String id) {
            if (id == null || id.isEmpty()) {
                this.id = UUID.randomUUID().toString();
            }
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("Title is required");
            }
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder assigneeId(String assigneeId) {
            if (assigneeId == null || assigneeId.isEmpty()) {
                throw new IllegalArgumentException("Assignee ID is required");
            }
            this.assigneeId = assigneeId;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            if (createdAt == null) {
                this.createdAt = LocalDateTime.now();
            } else {
                this.createdAt = createdAt;
            }
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            if (updatedAt == null) {
                updatedAt = LocalDateTime.now();
            }
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder status(String status) {
            if (status == null || status.isEmpty()) {
                this.status = "TODO";
            } else {
                this.status = status;
            }
            return this;
        }

        public Task build() {
            if (this.id == null || this.title == null || this.assigneeId == null) {
                throw new IllegalStateException("ID, title, and assignee ID are required");
            }
            return new Task(this);
        }
    }
}
