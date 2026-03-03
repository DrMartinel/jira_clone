package viettel_vts.jira_clone.application.factory;

import org.springframework.stereotype.Component;
import viettel_vts.jira_clone.domain.model.User;

@Component
public class UserFactory {
    public User createNewUser(String name, String email, String hashedPassword) {

        return new User.Builder()
                .name(name)
                .email(email)
                .hashedPassword(hashedPassword)
                .build();
    }
}
