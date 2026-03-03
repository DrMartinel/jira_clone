package viettel_vts.jira_clone.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    private final String id;
    private final String title;
    private final String description;
    private final String assigneeId;
    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private Status status;

    public enum Status {
        TODO {
            @Override
            public boolean canTransitionTo(Status next) {
                return next == IN_PROGRESS || next == CANCELLED;
            }
        },
        IN_PROGRESS {
            @Override
            public boolean canTransitionTo(Status next) {
                return next == DONE || next == CANCELLED;
            }
        },
        DONE {
            @Override
            public boolean canTransitionTo(Status next) {
                return false;
            }
        },
        CANCELLED {
            @Override
            public boolean canTransitionTo(Status next) {
                return false;
            }
        };

        public abstract boolean canTransitionTo(Status next);
    }

    private Task(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID().toString();
        this.createdAt = builder.createdAt != null ? builder.createdAt : LocalDateTime.now();
        this.updatedAt = builder.updatedAt != null ? builder.updatedAt : this.createdAt;
        this.title = builder.title != null ? builder.title : Status.TODO.toString();

        this.description = builder.description;
        this.assigneeId = builder.assigneeId;
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

    public Status getStatus() {
        return status;
    }

    public void transitionTo(Status targetStatus) {
        if (targetStatus == null || targetStatus == this.status) {
            throw new IllegalArgumentException("Target status cannot be null or the same as the current status");
        }

        if (!this.status.canTransitionTo(targetStatus)) {
            throw new IllegalStateException("Invalid transition!");
        }

        this.status = targetStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public static class Builder {
        private String id;
        private String title;
        private String description;
        private String assigneeId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Status status;

        public Builder id(String id) {
            if (id == null || id.isEmpty()) {
                this.id = UUID.randomUUID().toString();
            } else {
                this.id = id;
            }
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
                this.updatedAt = LocalDateTime.now();
            } else {
                this.updatedAt = updatedAt;
            }
            return this;
        }

        public Builder status(Status status) {
            if (status == null) {
                this.status = Status.TODO;
            } else {
                this.status = status;
            }
            return this;
        }

        public Task build() {
            if (this.title == null || this.assigneeId == null) {
                throw new IllegalStateException("ID, title, and assignee ID are required");
            }
            return new Task(this);
        }
    }
}
