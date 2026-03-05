package viettel_vts.jira_clone.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import viettel_vts.jira_clone.infrastructure.persistence.entity.TaskEntity;

@Repository
public interface SpringDataTaskRepository extends JpaRepository<TaskEntity, String> {

}
