package example.archi.ddd.domain.user.ports;

import example.archi.ddd.domain.user.entities.User;
import example.archi.ddd.domain.user.use_cases.UserNotFoundException;

public interface UserPort {

  String createUser(User user);

  User getUser(String userId) throws UserNotFoundException;
}
