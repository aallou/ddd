package example.archi.ddd.application.rest;

import example.archi.ddd.application.rest.dto.request.UserRequest;
import example.archi.ddd.application.rest.dto.response.UserResponse;
import example.archi.ddd.domain.user.entities.User;
import example.archi.ddd.domain.user.use_cases.CreateUser;
import example.archi.ddd.domain.user.use_cases.SearchUser;
import example.archi.ddd.domain.user.use_cases.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserControler {

  private final CreateUser createUser;

  private final SearchUser searchUser;

  public UserControler(CreateUser createUser, SearchUser searchUser) {
    this.createUser = createUser;
    this.searchUser = searchUser;
  }

  @PostMapping
  public UserResponse addNewUser(@RequestBody UserRequest userRequest) {
      User user = this.createUser.execute(toUser(userRequest));
      return toUserResponse(user);
  }

  @GetMapping("/{id}")
  public UserResponse getUser(@PathVariable("id") String userId) {
    User user;
    try {
      user = this.searchUser.execute(userId);
    } catch (UserNotFoundException e) {
      user = new User("N/A", "N/A", "N/A");
    }

    return toUserResponse(user);
  }

  User toUser(UserRequest userRequest) {
    return new User(null, userRequest.lastName(), userRequest.firstName());
  }

  UserResponse toUserResponse(User user) {
    return new UserResponse(user.id(), user.firstName() + " " + user.lastName().toUpperCase());
  }
}
