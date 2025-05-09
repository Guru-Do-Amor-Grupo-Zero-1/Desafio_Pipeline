package br.com.gurudoamor.projeto.service;

import br.com.gurudoamor.projeto.dto.MatchResponseDTO;
import br.com.gurudoamor.projeto.entity.Usuario;
import br.com.gurudoamor.projeto.enums.Signos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;

    @Mock
    private MapsService mapsService;

    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario1 = new Usuario();
        usuario1.setNomeUsuario("Usuario 1");
        usuario1.setSignoUsuario(Signos.ARIES);
        usuario1.setEndereco("Endereco 1");

        usuario2 = new Usuario();
        usuario2.setNomeUsuario("Usuario 2");
        usuario2.setSignoUsuario(Signos.TOURO);
        usuario2.setEndereco("Endereco 2");
    }

    @Test
    void testMatchUsuarios() {
        // Mockando a chamada ao MapsService
        when(mapsService.calcularRota(usuario1.getEndereco(), usuario2.getEndereco())).thenReturn("10 km");

        MatchResponseDTO result = matchService.matchUsuarios(usuario1, usuario2);

        assertNotNull(result);
        assertEquals("Usuario 1", result.getUsuario1());
        assertEquals("Usuario 2", result.getUsuario2());
        assertTrue(result.getCompatibilidade().contains("%"));
        assertEquals("ARIES + TOURO", result.getSignos());
        assertEquals("10 km", result.getDistancia());
    }
}
