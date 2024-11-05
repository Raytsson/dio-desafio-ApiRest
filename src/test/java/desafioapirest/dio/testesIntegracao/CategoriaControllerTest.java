package desafioapirest.dio.testesIntegracao;

import desafioapirest.dio.Dtos.CategoriaDto;
import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.domain.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaRepository categoriaRepository;

    @MockBean
    private UserDetailsService userDetailsService;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        // Configurando um usuário fictício
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("user")
                .password("{noop}password") // Para não usar o encoder; use {noop} para senha em texto puro
                .roles("USER")
                .build();

        // Simulando o comportamento do UserDetailsService
        when(userDetailsService.loadUserByUsername("user")).thenReturn(userDetails);

        // Configurando uma categoria de teste
        categoria = new Categoria(1L, "Categoria Teste", 100.0);
    }

    @Test
    void shouldListAllCategories() throws Exception {
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoria));

        mockMvc.perform(get("/categorias")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Categoria Teste"));
    }

    @Test
    void shouldReturnCategoryById() throws Exception {
        when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(categoria));

        mockMvc.perform(get("/categorias/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Categoria Teste"));
    }

    @Test
    void shouldReturn404WhenCategoryNotFound() throws Exception {
        when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/categorias/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateCategory() throws Exception {
        CategoriaDto categoriaDto = new CategoriaDto(null, "Nova Categoria", 200.0);
        when(categoriaRepository.save(any())).thenReturn(categoria);

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Nova Categoria\", \"limiteOrcamento\": 200.0}")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/categorias/1"));
    }

    @Test
    void shouldUpdateCategory() throws Exception {
        when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(categoria));
        when(categoriaRepository.save(any())).thenReturn(categoria);

        mockMvc.perform(put("/categorias/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Categoria Atualizada\", \"limiteOrcamento\": 150.0}")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Categoria Atualizada"));
    }

    @Test
    void shouldReturn404WhenUpdatingNonExistentCategory() throws Exception {
        when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(put("/categorias/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Categoria Atualizada\", \"limiteOrcamento\": 150.0}")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn409WhenDuplicateName() throws Exception {
        when(categoriaRepository.findByNome("Categoria Teste")).thenReturn(java.util.Optional.of(categoria));

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Categoria Teste\", \"limiteOrcamento\": 100.0}")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isConflict());
    }
}
