package viettel_vts.jira_clone.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import viettel_vts.jira_clone.infrastructure.persistence.entity.UserEntity;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends JpaRepository<UserEntity, String> {
    public Optional<UserEntity> findByEmail(String email);
}
