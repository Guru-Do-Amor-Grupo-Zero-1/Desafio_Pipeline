package br.com.gurudoamor.projeto.controller;

import br.com.gurudoamor.projeto.dto.MatchResponseDTO;
import br.com.gurudoamor.projeto.entity.MatchResponse;
import br.com.gurudoamor.projeto.entity.Usuario;
import br.com.gurudoamor.projeto.repository.MatchResponseRepository;
import br.com.gurudoamor.projeto.repository.UsuarioRepository;
import br.com.gurudoamor.projeto.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/match")
@CrossOrigin("*")
public class MatchController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	MatchResponseRepository matchResponseRepository;

	private final MatchService matchService;

	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@PostMapping("/match")
	public MatchResponseDTO match(@RequestBody Map<String, Usuario> usuarios) {
		Usuario usuario1 = usuarios.get("usuario1");
		Usuario usuario2 = usuarios.get("usuario2");

		// Chama o serviço para calcular a compatibilidade
		MatchResponseDTO matchResponseDTO = matchService.matchUsuarios(usuario1, usuario2);

		// Cria o objeto MatchResponse a partir do MatchResponseDTO
		MatchResponse matchResponse = new MatchResponse(matchResponseDTO.getUsuario1(), matchResponseDTO.getUsuario2(),
				matchResponseDTO.getCompatibilidade(), matchResponseDTO.getSignos(), matchResponseDTO.getMensagem(),
				matchResponseDTO.getDistancia());

		// Salva o MatchResponse no banco de dados
		matchResponseRepository.save(matchResponse);

		// Retorna o MatchResponseDTO
		return matchResponseDTO;
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(this.matchResponseRepository.findAll());
	}

}
