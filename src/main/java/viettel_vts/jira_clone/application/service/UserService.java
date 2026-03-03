package viettel_vts.jira_clone.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import viettel_vts.jira_clone.application.factory.UserFactory;
import viettel_vts.jira_clone.domain.event.EventPublisher;
import viettel_vts.jira_clone.domain.event.UserEvent;
import viettel_vts.jira_clone.domain.model.User;
import viettel_vts.jira_clone.domain.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;
    private final UserFactory userFactory;

    public UserService(UserRepository userRepository, EventPublisher eventPublisher, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.userFactory = userFactory;
    }

    @Transactional
    public User createUser(String name, String email, String hashedPassword) {
        User newUser = userFactory.createNewUser(name, email, hashedPassword);
        userRepository.save(newUser);
        eventPublisher.publish(new UserEvent(newUser, UserEvent.EventType.CREATED));
        return newUser;
    }

    @Transactional
    public void updateUser(String userId, String name, String email, String hashedPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        user.updateName(name);
        user.updateHashedPassword(hashedPassword);
        userRepository.save(user);
        eventPublisher.publish(new UserEvent(user, UserEvent.EventType.UPDATED));
    }

    @Transactional
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        userRepository.delete(user);
        eventPublisher.publish(new UserEvent(user, UserEvent.EventType.DELETED));
    }
}
