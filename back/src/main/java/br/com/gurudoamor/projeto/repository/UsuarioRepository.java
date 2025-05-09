package br.com.gurudoamor.projeto.repository;

import br.com.gurudoamor.projeto.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
