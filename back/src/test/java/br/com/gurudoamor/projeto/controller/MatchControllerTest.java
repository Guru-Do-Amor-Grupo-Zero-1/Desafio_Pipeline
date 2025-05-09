package br.com.gurudoamor.projeto.controller;

import br.com.gurudoamor.projeto.dto.MatchResponseDTO;
import br.com.gurudoamor.projeto.entity.MatchResponse;
import br.com.gurudoamor.projeto.entity.Usuario;
import br.com.gurudoamor.projeto.enums.Signos;
import br.com.gurudoamor.projeto.repository.MatchResponseRepository;
import br.com.gurudoamor.projeto.service.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MatchControllerTest {

    @TestConfiguration
    static class TestConfig {

        @Bean
        @Primary
        public MatchService matchService() {
            return Mockito.mock(MatchService.class);
        }
    }

    @Autowired
    private MatchController matchController;

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchResponseRepository matchResponseRepository;

    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    void setUp() {
        usuario1 = new Usuario();
        usuario1.setNomeUsuario("Usuario 1");
        usuario1.setSignoUsuario(Signos.ARIES);
        usuario1.setEndereco("Endereco 1");

        usuario2 = new Usuario();
        usuario2.setNomeUsuario("Usuario 2");
        usuario2.setSignoUsuario(Signos.TOURO);
        usuario2.setEndereco("Endereco 2");

        // Limpa o repositório antes de cada teste
        matchResponseRepository.deleteAll();
    }

    @Test
    void testMatch() {
        MatchResponseDTO dto = new MatchResponseDTO(
                "Usuario 1",
                "Usuario 2",
                "80%",
                "ÁRIES + TOURO",
                "Amor verdadeiro!",
                "10 km"
        );

        when(matchService.matchUsuarios(usuario1, usuario2)).thenReturn(dto);

        MatchResponseDTO result = matchController.match(Map.of("usuario1", usuario1, "usuario2", usuario2));

        assertNotNull(result);
        assertEquals("Usuario 1", result.getUsuario1());
        assertEquals("Usuario 2", result.getUsuario2());
        assertEquals("80%", result.getCompatibilidade());
        assertEquals("ÁRIES + TOURO", result.getSignos());
        assertEquals("Amor verdadeiro!", result.getMensagem());
        assertEquals("10 km", result.getDistancia());
    }

    @Test
    void testFindAll() {

        MatchResponse matchResponse = new MatchResponse("Usuario 1", "Usuario 2", "80%", "ÁRIES + TOURO", "Amor verdadeiro!", "10 km");
        matchResponseRepository.save(matchResponse);

        ResponseEntity<?> response = matchController.findAll();
        assertEquals(200, response.getStatusCodeValue());
        List<?> list = (List<?>) response.getBody();
        assertFalse(list.isEmpty());
    }
}
