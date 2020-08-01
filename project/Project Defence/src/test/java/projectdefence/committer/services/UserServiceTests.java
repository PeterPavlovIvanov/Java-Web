package projectdefence.committer.services;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import projectdefence.committer.demo.models.entities.Role;
import projectdefence.committer.demo.models.entities.RoleName;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.repositories.UserRepository;
import projectdefence.committer.demo.services.RoleService;
import projectdefence.committer.demo.services.UserService;
import projectdefence.committer.demo.services.impl.UserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTests {

    private User testUser;
    private UserRepository mockedUserRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;

    @BeforeEach
    public void init() {
        this.testUser = new User() {{
            setId("SOME_UUID");
            setNickname("Pesho");
            setPassword("12345");
            setEmail("email.com@Email");
            setRole(new Role() {{
                setRoleName(RoleName.USER);
            }});
        }};

        this.mockedUserRepository = Mockito.mock(UserRepository.class);
    }

    @Test
    public void userService_GetUserWithCorrectNickname_ShouldReturnCorrect() {
        // Arrange
        Mockito.when(this.mockedUserRepository
                .findByNickname("Pesho"))
                .thenReturn(java.util.Optional.ofNullable(this.testUser));

        UserService userService =
                new UserServiceImpl(this.mockedUserRepository, this.modelMapper, this.roleService);
        User expected = this.testUser;

        // Act
        User actual = this.modelMapper.map(userService.getByNickname("Pesho"), User.class);

        // Assert
        Assert.assertEquals("Broken...", expected.getId(),
                actual.getId());
        Assert.assertEquals("Broken...", expected.getNickname(),
                actual.getNickname());
        Assert.assertEquals("Broken...", expected.getPassword(),
                actual.getPassword());
        Assert.assertEquals("Broken...", expected.getEmail(),
                actual.getPassword());
    }
}


