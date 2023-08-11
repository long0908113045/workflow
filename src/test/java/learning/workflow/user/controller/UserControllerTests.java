package learning.workflow.user.controller;

import learning.workflow.user.model.User;
import learning.workflow.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTests {
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserRepository userRepository;

  @Test
  public void getAllUsers() throws Exception {
    List<User> users = new ArrayList<>();
    User user = new User();
    user.setName("user name");
    user.setEmail("user@email.com");
    user.setId(0);
    users.add(user);
    when(userRepository.findAll()).thenReturn(users);
    this.mockMvc.perform(get("/api/user/all").contentType(MediaType.ALL))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(users.get(0).getId()))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(users.get(0).getName()))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(users.get(0).getEmail()));

    /*List<User> result = (List<User>) userController.getAllUsers();*/

    /*Assertions.assertEquals(result.size(), users.size());
    Assertions.assertEquals(result.get(0).getId(), users.get(0).getId());
    Assertions.assertEquals(result.get(0).getName(), users.get(0).getName());
    Assertions.assertEquals(result.get(0).getEmail(), users.get(0).getEmail());*/
  }
}
