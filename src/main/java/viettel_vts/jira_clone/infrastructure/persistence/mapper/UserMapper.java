package viettel_vts.jira_clone.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import viettel_vts.jira_clone.domain.model.User;
import viettel_vts.jira_clone.infrastructure.persistence.entity.UserEntity;

@Component
public class UserMapper {

    public UserEntity toEntity(User domain) {
        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setHashedPassword(domain.getHashedPassword());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public User toDomain(UserEntity entity) {
        return new User.Builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .hashedPassword(entity.getHashedPassword())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
