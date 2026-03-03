package viettel_vts.jira_clone.domain.repository;

import viettel_vts.jira_clone.domain.model.User;
import java.util.Optional;
import java.util.List;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(String id);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    void delete(User user);
}