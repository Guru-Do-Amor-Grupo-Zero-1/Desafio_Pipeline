package br.com.gurudoamor.projeto.repository;

import br.com.gurudoamor.projeto.entity.MatchResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MatchResponseRepositoryTest {

    @Autowired
    private MatchResponseRepository matchResponseRepository;

    @Test
    @DisplayName("Salvar MatchResponse e verificar se o ID é gerado")
    public void testSaveMatchResponse() {
        // Arrange
        MatchResponse match = new MatchResponse(
                "Usuario 1",
                "Usuario 2",
                "80%",
                "ÁRIES + TOURO",
                "Amor verdadeiro!",
                "10 km"
        );

        // Act
        MatchResponse savedMatch = matchResponseRepository.save(match);

        // Assert
        assertNotNull(savedMatch.getId(), "O ID não deve ser nulo após salvar");
        assertEquals("Usuario 1", savedMatch.getUsuario1());
    }

    @Test
    @DisplayName("Buscar MatchResponse por ID")
    public void testFindById() {
        // Arrange
        MatchResponse match = new MatchResponse(
                "Usuario 3",
                "Usuario 4",
                "90%",
                "LEÃO + VIRGO",
                "Muito compatíveis!",
                "5 km"
        );
        match = matchResponseRepository.save(match);

        // Act
        Optional<MatchResponse> found = matchResponseRepository.findById(match.getId());

        // Assert
        assertTrue(found.isPresent(), "Deve encontrar o MatchResponse salvo");
        assertEquals("Usuario 3", found.get().getUsuario1());
    }

    @Test
    @DisplayName("Buscar todos os MatchResponse")
    public void testFindAll() {
        // Arrange
        MatchResponse match1 = new MatchResponse(
                "Usuario 1",
                "Usuario 2",
                "80%",
                "ÁRIES + TOURO",
                "Amor verdadeiro!",
                "10 km"
        );
        MatchResponse match2 = new MatchResponse(
                "Usuario 3",
                "Usuario 4",
                "90%",
                "LEÃO + VIRGO",
                "Muito compatíveis!",
                "5 km"
        );

        matchResponseRepository.save(match1);
        matchResponseRepository.save(match2);

        // Act
        List<MatchResponse> matches = matchResponseRepository.findAll();

        // Assert
        assertNotNull(matches);
        assertTrue(matches.size() >= 2, "Deve haver pelo menos 2 MatchResponse na lista");
    }

    @Test
    @DisplayName("Excluir MatchResponse e verificar remoção")
    public void testDelete() {
        // Arrange
        MatchResponse match = new MatchResponse(
                "Usuario 5",
                "Usuario 6",
                "70%",
                "CÂNCER + CAPRICÓRNIO",
                "Desafios a superar",
                "15 km"
        );
        match = matchResponseRepository.save(match);
        Long id = match.getId();

        // Act
        matchResponseRepository.delete(match);
        Optional<MatchResponse> deleted = matchResponseRepository.findById(id);

        // Assert
        assertFalse(deleted.isPresent(), "O MatchResponse deve ter sido excluído");
    }
}
