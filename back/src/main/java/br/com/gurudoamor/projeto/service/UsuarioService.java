package br.com.gurudoamor.projeto.service;

import br.com.gurudoamor.projeto.dto.SignoUpdateDTO;
import br.com.gurudoamor.projeto.entity.Usuario;
import br.com.gurudoamor.projeto.enums.Signos;
import br.com.gurudoamor.projeto.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }


    public void deleteUser (final Long id){
        final Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        if (usuario == null){
            throw new RuntimeException();
        }
        this.usuarioRepository.delete(usuario);
    }

    @Transactional
    public void atualizarSigno(Long id, SignoUpdateDTO request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (request.getSigno() != null) {
            usuario.setSignoUsuario(Signos.valueOf(request.getSigno().toUpperCase()));
        } else if (request.getMes() != null && request.getDia() != null) {
            Signos signoCalculado = Signos.obterSigno(request.getMes(), request.getDia());
            if (signoCalculado != null) {
                usuario.setSignoUsuario(signoCalculado);
            } else {
                throw new RuntimeException("Data inválida para cálculo do signo.");
            }
        } else {
            throw new RuntimeException("É necessário informar o signo ou a data de nascimento.");
        }

        usuarioRepository.save(usuario);
    }

}
