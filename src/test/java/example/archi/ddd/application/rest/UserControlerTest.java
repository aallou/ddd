package example.archi.ddd.application.rest;

import example.archi.ddd.domain.user.entities.User;
import example.archi.ddd.domain.user.use_cases.CreateUser;
import example.archi.ddd.domain.user.use_cases.SearchUser;
import example.archi.ddd.domain.user.use_cases.UserNotFoundException;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserControler.class)
public class UserControlerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CreateUser createUser;

  @MockBean
  SearchUser searchUser;

  @Test
  public void createUser() throws Exception {
    Mockito.doReturn(new User("id1", "Joe", "Doe"))
        .when(createUser).execute(Mockito.any(User.class));

    mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
            .content("{\"firstName\": \"Doe\", \"lastName\": \"Joe\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Is.is("id1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Is.is("Doe JOE")));
  }

  @Test
  public void getUser() throws Exception {
    Mockito.doReturn(new User("id1", "Joe", "Doe"))
        .when(searchUser).execute("id1");

    mockMvc.perform(MockMvcRequestBuilders.get("/api/users/id1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Is.is("id1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Is.is("Doe JOE")));
  }

  @Test
  public void getUserNotFound() throws Exception {
    Mockito.doThrow(UserNotFoundException.class)
        .when(searchUser).execute("id1");

    mockMvc.perform(MockMvcRequestBuilders.get("/api/users/id1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Is.is("N/A")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Is.is("N/A N/A")));
  }
}
