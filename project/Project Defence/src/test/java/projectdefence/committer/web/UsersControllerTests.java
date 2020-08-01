package projectdefence.committer.web;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import projectdefence.committer.demo.models.entities.Role;
import projectdefence.committer.demo.models.entities.RoleName;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.repositories.FollowerRepository;
import projectdefence.committer.demo.repositories.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository mockedUserRepository;

    private String USER_ID;
    private String USER_NICKNAME = "Pesho";
    private String USER_PASSWORD = "12345";
    private String USER_EMAIL = "email.com@email";
    private Role USER_ROLE = new Role();

    @BeforeEach
    public void setUp() {
        USER_ROLE.setRoleName(RoleName.USER);
        User user = new User();
        user.setNickname(USER_NICKNAME);
        user.setPassword(USER_PASSWORD);
        user.setEmail(USER_EMAIL);
        user.setRole(USER_ROLE);
        user = this.mockedUserRepository.saveAndFlush(user);
        USER_ID = user.getId();
    }

    @AfterEach
    public void tearDown(){
        this.mockedUserRepository.deleteAll();
    }

    @Test
    public void testRegisterPageStatusCodeOk() throws Exception {
        this.mockMvc.perform(get("/users/register")).andExpect(status().isOk());
    }

    @Test void testLoginPageStatusCodeOk() throws Exception {
        this.mockMvc.perform(get("/users/login")).andExpect(status().isOk());
    }
}
