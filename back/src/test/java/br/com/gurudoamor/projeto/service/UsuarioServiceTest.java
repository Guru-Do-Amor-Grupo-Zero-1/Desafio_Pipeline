package br.com.gurudoamor.projeto.service;

import br.com.gurudoamor.projeto.dto.SignoUpdateDTO;
import br.com.gurudoamor.projeto.entity.Usuario;
import br.com.gurudoamor.projeto.enums.Signos;
import br.com.gurudoamor.projeto.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario(1L, "João", "123456789", Signos.AQUARIO, null, "Rua A", 25);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        usuarioService.save(usuario);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void deveDeletarUsuarioComSucesso() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        usuarioService.deleteUser(1L);
        verify(usuarioRepository, times(1)).delete(usuario);
    }

    @Test
    void deveLancarExcecaoAoDeletarUsuarioInexistente() {
        when(usuarioRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> usuarioService.deleteUser(2L));
    }

    @Test
    void deveAtualizarSignoComSucesso() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        SignoUpdateDTO request = new SignoUpdateDTO("aries", null, null);
        usuarioService.atualizarSigno(1L, request);
        assertEquals(Signos.ARIES, usuario.getSignoUsuario());
    }
}
