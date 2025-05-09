package br.com.gurudoamor.projeto.repository;

import br.com.gurudoamor.projeto.entity.Usuario;
import br.com.gurudoamor.projeto.enums.Signos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setNomeUsuario("TesteUsuario");
        usuario.setTelefone("123456789");
        usuario.setSignoUsuario(Signos.ARIES);
        usuario.setEndereco("Rua Teste, 123");
        usuario.setIdade(25);

        usuario = usuarioRepository.save(usuario);
    }

    @Test
    void testSalvarUsuario() {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeUsuario("NovoUsuario");
        novoUsuario.setTelefone("987654321");
        novoUsuario.setSignoUsuario(Signos.TOURO);
        novoUsuario.setEndereco("Rua Nova, 456");
        novoUsuario.setIdade(30);

        Usuario salvo = usuarioRepository.save(novoUsuario);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getNomeUsuario()).isEqualTo("NovoUsuario");
    }

    @Test
    void testBuscarUsuarioPorId() {
        Optional<Usuario> encontrado = usuarioRepository.findById(usuario.getId());
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNomeUsuario()).isEqualTo("TesteUsuario");
    }

    @Test
    void testDeletarUsuario() {
        usuarioRepository.delete(usuario);
        Optional<Usuario> deletado = usuarioRepository.findById(usuario.getId());
        assertThat(deletado).isEmpty();
    }
}
