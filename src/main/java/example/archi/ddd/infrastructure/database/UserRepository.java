package example.archi.ddd.infrastructure.database;

import example.archi.ddd.domain.user.entities.User;
import example.archi.ddd.domain.user.ports.UserPort;
import example.archi.ddd.domain.user.use_cases.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserRepository implements UserPort {

  private final List<User> listUsers = new ArrayList<>();

  @Override
  public String createUser(User user) {
    String userId = UUID.randomUUID().toString();
    listUsers.add(new User(userId, user.lastName(), user.firstName()));
    return userId;
  }

  @Override
  public User getUser(String userId) {
    return listUsers.stream()
                    .filter(user -> user.id().equals(userId))
        .findFirst().orElseThrow(UserNotFoundException::new);
  }
}
