package viettel_vts.jira_clone.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;
import viettel_vts.jira_clone.domain.model.Task;
import viettel_vts.jira_clone.domain.repository.TaskRepository;
import viettel_vts.jira_clone.infrastructure.persistence.mapper.TaskMapper;
import viettel_vts.jira_clone.infrastructure.persistence.repository.SpringDataTaskRepository;

import java.util.Optional;
import java.util.List;

@Component
public class TaskRepositoryAdapter implements TaskRepository {

    private final SpringDataTaskRepository springRepository;
    private final TaskMapper mapper;

    public TaskRepositoryAdapter(SpringDataTaskRepository springRepository, TaskMapper mapper) {
        this.springRepository = springRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Task task) {
        var entity = mapper.toEntity(task);
        springRepository.save(entity);

    }

    @Override
    public Optional<Task> findById(String id) {
        return springRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void delete(Task task) {
        var taskId = task.getId();
        springRepository.deleteById(taskId);
    }

    @Override
    public List<Task> findAll() {
        return springRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
