package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViagemJPARepository extends JpaRepository<Viagem, Long> {
    Optional<Viagem> findByHash(String hash);
}