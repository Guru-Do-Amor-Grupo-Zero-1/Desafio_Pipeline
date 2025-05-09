package br.com.gurudoamor.projeto.repository;

import br.com.gurudoamor.projeto.entity.MatchResponse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchResponseRepository extends JpaRepository<MatchResponse, Long> {

}
