package example.archi.ddd.domain.user.use_cases;

import example.archi.ddd.domain.user.entities.User;
import example.archi.ddd.domain.user.ports.UserPort;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {

  private final UserPort userPort;

  public CreateUser(UserPort userPort) {
    this.userPort = userPort;
  }

  public User execute(User user) {
    String userId = userPort.createUser(user);
    return userPort.getUser(userId);
  }
}
