package example.archi.ddd.domain.user.use_cases;

import example.archi.ddd.domain.user.entities.User;
import example.archi.ddd.domain.user.ports.UserPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CreateUserTest {

  @InjectMocks
  CreateUser createUser;

  @Mock
  UserPort userPort;

  @Test
  public void createUserOK() {
    Mockito.doReturn("id1")
           .when(userPort).createUser(Mockito.any(User.class));

    Mockito.doReturn(new User("id1", "Joe", "Doe"))
        .when(userPort).getUser("id1");

    User user = createUser.execute(new User(null, "Joe", "Doe"));

    assertThat(user.id()).isEqualTo("id1");
    assertThat(user.firstName()).isEqualTo("Doe");
    assertThat(user.lastName()).isEqualTo("Joe");
  }
}
