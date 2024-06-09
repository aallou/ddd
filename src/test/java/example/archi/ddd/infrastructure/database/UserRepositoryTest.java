package example.archi.ddd.infrastructure.database;

import example.archi.ddd.domain.user.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {

  UserRepository userRepository = new UserRepository();

  @Test
  public void createUser() {
    String userId = userRepository.createUser(new User(null, "Joe", "Doe"));

    Assertions.assertThat(userId).isNotEmpty();
    User user = userRepository.getUser(userId);
    Assertions.assertThat(user.lastName()).isEqualTo("Joe");
    Assertions.assertThat(user.firstName()).isEqualTo("Doe");
  }
}
