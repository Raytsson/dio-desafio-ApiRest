package desafioapirest.dio.testesIntegracao;

import desafioapirest.dio.domain.model.User;
import desafioapirest.dio.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        // Registrando um usuário de teste
        User user = new User("testuser", passwordEncoder.encode("password123"));
        userRepository.save(user);
    }

    @Test
    void shouldLoginSuccessfully() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content("{\"login\":\"testuser\", \"password\":\"password123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFailLoginWithInvalidCredentials() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType("application/json")
                        .content("{\"login\":\"testuser\", \"password\":\"wrongpassword\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRegisterSuccessfully() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .contentType("application/json")
                        .content("{\"login\":\"newuser\", \"password\":\"newpassword123\"}"))
                .andExpect(status().isOk());
    }
}
