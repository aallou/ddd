package example.archi.ddd.domain.user.use_cases;

import static org.assertj.core.api.Assertions.assertThat;

import example.archi.ddd.domain.user.entities.User;
import example.archi.ddd.domain.user.ports.UserPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SearchUserTest {

  @InjectMocks
  SearchUser searchUser;

  @Mock
  UserPort userPort;

  @Test
  public void createUserOK() {
    Mockito.doReturn(new User("id1", "Joe", "Doe"))
        .when(userPort).getUser("id1");

    User user = searchUser.execute("id1");

    assertThat(user.id()).isEqualTo("id1");
    assertThat(user.firstName()).isEqualTo("Doe");
    assertThat(user.lastName()).isEqualTo("Joe");
  }
}
