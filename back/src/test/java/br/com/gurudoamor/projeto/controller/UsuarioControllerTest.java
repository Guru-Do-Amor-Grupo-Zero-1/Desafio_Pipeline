package br.com.gurudoamor.projeto.controller;

import br.com.gurudoamor.projeto.dto.SignoUpdateDTO;
import br.com.gurudoamor.projeto.entity.Usuario;
import br.com.gurudoamor.projeto.enums.Signos;
import br.com.gurudoamor.projeto.service.UsuarioService;
import br.com.gurudoamor.projeto.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)  // Usa Mockito no JUnit 5
public class UsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioController usuarioController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Usuario usuario;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        usuario = new Usuario(1L, "usuarioTeste", "11999999999", Signos.ARIES, null, "Rua Teste", 25);
    }

    @Test
    void testFindByIDPath() throws Exception {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeUsuario", is("usuarioTeste")))
                .andExpect(jsonPath("$.telefone", is("11999999999")));
    }

    @Test
    void testListAll() throws Exception {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));

        mockMvc.perform(get("/api/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeUsuario", is("usuarioTeste")));
    }

    @Test
    void testCreateUsuario() throws Exception {
        doNothing().when(usuarioService).save(any(Usuario.class));

        mockMvc.perform(post("/api/usuario/adicionar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioService).deleteUser(1L);

        mockMvc.perform(delete("/api/usuario/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testAtualizarSigno() throws Exception {
        SignoUpdateDTO request = new SignoUpdateDTO();
        request.setSigno("aries");

        // Corrigindo o problema de comparação de instâncias
        doNothing().when(usuarioService).atualizarSigno(eq(1L), any(SignoUpdateDTO.class));

        mockMvc.perform(put("/api/usuario/1/signo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Signo atualizado com sucesso."));
    }

}
