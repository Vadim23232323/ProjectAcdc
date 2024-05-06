import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.UserRepository;
import com.javarush.khmelov.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        userRepositoryMock = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepositoryMock);
    }

    @Test
    @DisplayName("CreateUser")
    void testCreateUser() {

        Role role = new Role();
        role.setId(1L);

        User user = User.builder()
                .login("testUser")
                .password("testPassword")
                .name("Test")
                .surname("User")
                .role(role)
                .build();

        userService.create(user);

        Mockito.verify(userRepositoryMock, Mockito.times(1)).create(user);
        Mockito.when(userRepositoryMock.findByLogin("testUser")).thenReturn(Optional.of(user));

        Optional<User> createdUserOptional = userService.findByLogin("testUser");
        assertTrue(createdUserOptional.isPresent());
        assertEquals(user, createdUserOptional.get());
    }


}