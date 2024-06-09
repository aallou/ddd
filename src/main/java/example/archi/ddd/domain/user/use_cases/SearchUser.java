package example.archi.ddd.domain.user.use_cases;

import example.archi.ddd.domain.user.entities.User;
import example.archi.ddd.domain.user.ports.UserPort;
import org.springframework.stereotype.Component;

@Component
public class SearchUser {

  private final UserPort userPort;

  public SearchUser(UserPort userPort) {
    this.userPort = userPort;
  }

  public User execute(String userId) {
    return userPort.getUser(userId);
  }
}
