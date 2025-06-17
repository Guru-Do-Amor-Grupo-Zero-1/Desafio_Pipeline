package br.com.gurudoamor.projeto.controller;

import br.com.gurudoamor.projeto.dto.SignoUpdateDTO;
import br.com.gurudoamor.projeto.entity.Usuario;
import br.com.gurudoamor.projeto.repository.UsuarioRepository;
import br.com.gurudoamor.projeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping ("/api/usuario")
@RestController
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByIDPath(@PathVariable("id") final Long id) {
        final Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listAll() {
        return ResponseEntity.ok(this.usuarioRepository.findAll());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> createPedido(@RequestBody final Usuario usuario) {
        try {
            this.usuarioService.save(usuario);

            return new ResponseEntity<>("TESTE PRODUCTIONNNNNNNNNNNNNNNNNNNNN", HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Erro ao salvar usuário", HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") final Long id) {
        try {
            this.usuarioService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/signo")
    public ResponseEntity<?> atualizarSigno(@PathVariable Long id, @RequestBody SignoUpdateDTO request) {
        usuarioService.atualizarSigno(id, request);
        return ResponseEntity.ok("Signo atualizado com sucesso.");
    }

}
