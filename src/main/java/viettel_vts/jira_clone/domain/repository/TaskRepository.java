package viettel_vts.jira_clone.domain.repository;

import viettel_vts.jira_clone.domain.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    void save(Task task);

    Optional<Task> findById(String id);

    List<Task> findAll();

    void delete(Task task);
}
