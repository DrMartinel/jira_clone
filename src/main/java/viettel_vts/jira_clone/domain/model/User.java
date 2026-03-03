package viettel_vts.jira_clone.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    private final String id;
    private final String email;
    private final LocalDateTime createdAt;

    private String name;
    private String hashedPassword;
    private LocalDateTime updatedAt;

    private User(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID().toString();
        this.createdAt = builder.createdAt != null ? builder.createdAt : LocalDateTime.now();
        this.updatedAt = builder.updatedAt != null ? builder.updatedAt : this.createdAt;

        this.name = builder.name;
        this.email = builder.email;
        this.hashedPassword = builder.hashedPassword;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void changePassword(String newHashedPassword) {
        if (newHashedPassword == null || newHashedPassword.isBlank()) {
            throw new IllegalArgumentException("New password cannot be empty");
        }
        this.hashedPassword = newHashedPassword;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = newName;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateHashedPassword(String newHashedPassword) {
        if (newHashedPassword == null || newHashedPassword.isBlank()) {
            throw new IllegalArgumentException("New password cannot be empty");
        }
        this.hashedPassword = newHashedPassword;
        this.updatedAt = LocalDateTime.now();
    }

    public static class Builder {
        private String id;
        private String name;
        private String email;
        private String hashedPassword;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalStateException("Name is required");
            }
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            if (email == null || email.isBlank()) {
                throw new IllegalStateException("Email is required");
            }
            this.email = email;
            return this;
        }

        public Builder hashedPassword(String hashedPassword) {
            if (hashedPassword == null || hashedPassword.isBlank()) {
                throw new IllegalStateException("hashedPassword is required");
            }
            this.hashedPassword = hashedPassword;
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

        public User build() {
            if (this.name == null || this.email == null || this.hashedPassword == null) {
                throw new IllegalStateException("ID, name, email, and hashedPassword are required");
            }
            return new User(this);
        }
    }
}
