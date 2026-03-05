package viettel_vts.jira_clone.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;
import viettel_vts.jira_clone.domain.model.User;
import viettel_vts.jira_clone.domain.repository.UserRepository;
import viettel_vts.jira_clone.infrastructure.persistence.mapper.UserMapper;
import viettel_vts.jira_clone.infrastructure.persistence.repository.SpringDataUserRepository;

import java.util.Optional;
import java.util.List;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final SpringDataUserRepository springRepository;
    private final UserMapper mapper;

    public UserRepositoryAdapter(SpringDataUserRepository springRepository, UserMapper mapper) {
        this.springRepository = springRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        var entity = mapper.toEntity(user);
        springRepository.save(entity);
    }

    @Override
    public Optional<User> findById(String id) {
        return springRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return springRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(User user) {
        var userId = user.getId();
        springRepository.deleteById(userId);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springRepository.findByEmail(email)
                .map(mapper::toDomain);
    }
}