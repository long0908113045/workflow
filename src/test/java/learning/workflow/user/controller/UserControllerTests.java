package learning.workflow.user.controller;

import learning.workflow.user.model.User;
import learning.workflow.user.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTests {
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserController userController;

  @Before
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void getAllUsers() throws Exception {
    List<User> users = new ArrayList<>();
    User user = new User();
    user.setName("user name");
    user.setEmail("user@email.com");
    user.setId(0);
    users.add(user);
    when(userRepository.findAll()).thenReturn(users);

    List<User> result = (List<User>) userController.getAllUsers();

    Assertions.assertEquals(result.size(), users.size());
    Assertions.assertEquals(result.get(0).getId(), users.get(0).getId());
    Assertions.assertEquals(result.get(0).getName(), users.get(0).getName());
    Assertions.assertEquals(result.get(0).getEmail(), users.get(0).getEmail());
  }
}
