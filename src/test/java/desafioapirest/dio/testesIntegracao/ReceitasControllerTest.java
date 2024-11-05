package desafioapirest.dio.testesIntegracao;

import desafioapirest.dio.Dtos.ReceitasDto;
import desafioapirest.dio.domain.model.Categoria;
import desafioapirest.dio.domain.model.Receitas;
import desafioapirest.dio.service.CategoriaService;
import desafioapirest.dio.service.ReceitasService;
import desafioapirest.dio.service.exceptions.BusinesErrors.ResourceNotFoundException;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReceitasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceitasService receitasService;

    @MockBean
    private CategoriaService categoriaService;

    @MockBean
    private UserDetailsService userDetailsService;

    private Receitas receita;

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

        // Configurando uma receita de teste
        Categoria categoria = new Categoria(1L, "Categoria Teste", 100.0);
        receita = new Receitas(1L, "Descrição Teste", BigDecimal.valueOf(150.0), LocalDate.now(), categoria, "Nome Receita");
    }

    @Test
    void shouldListAllReceitas() throws Exception {
        when(receitasService.findAll()).thenReturn(Collections.singletonList(receita));

        mockMvc.perform(get("/receitas")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeReceitas").value("Nome Receita"));
    }

    @Test
    void shouldReturnReceitaById() throws Exception {
        when(receitasService.findById(1L)).thenReturn(receita);

        mockMvc.perform(get("/receitas/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeReceitas").value("Nome Receita"));
    }

    @Test
    void shouldReturn404WhenReceitaNotFound() throws Exception {
        when(receitasService.findById(1L)).thenThrow(new ResourceNotFoundException("Receita não encontrada com ID: 1"));

        mockMvc.perform(get("/receitas/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateReceita() throws Exception {
        ReceitasDto dto = new ReceitasDto(null, "Nova Receita", BigDecimal.valueOf(200.0), "Nome Receita", 1L, LocalDate.now().toString());
        when(receitasService.save(any())).thenReturn(receita);

        mockMvc.perform(post("/receitas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Nova Receita\", \"valor\": 200.0, \"nomeReceitas\": \"Nome Receita\", \"categoriaId\": 1, \"dataTransacao\": \"" + LocalDate.now() + "\"}")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeReceitas").value("Nome Receita"));
    }

    @Test
    void shouldUpdateReceita() throws Exception {
        ReceitasDto dto = new ReceitasDto(1L, "Receita Atualizada", BigDecimal.valueOf(300.0), "Nome Receita Atualizada", 1L, LocalDate.now().toString());
        when(receitasService.update(anyLong(), any())).thenReturn(receita);

        mockMvc.perform(put("/receitas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Receita Atualizada\", \"valor\": 300.0, \"nomeReceitas\": \"Nome Receita Atualizada\", \"categoriaId\": 1, \"dataTransacao\": \"" + LocalDate.now() + "\"}")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeReceitas").value("Nome Receita"));
    }

    @Test
    void shouldReturn404WhenUpdatingNonExistentReceita() throws Exception {
        when(receitasService.update(anyLong(), any())).thenThrow(new ResourceNotFoundException("Receita não encontrada"));

        mockMvc.perform(put("/receitas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Receita Atualizada\", \"valor\": 300.0, \"nomeReceitas\": \"Nome Receita Atualizada\", \"categoriaId\": 1, \"dataTransacao\": \"" + LocalDate.now() + "\"}")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteReceita() throws Exception {
        doNothing().when(receitasService).delete(1L);

        mockMvc.perform(delete("/receitas/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("user").password("{noop}password").roles("USER"))) // Autenticação
                .andExpect(status().isNoContent());
    }
}
