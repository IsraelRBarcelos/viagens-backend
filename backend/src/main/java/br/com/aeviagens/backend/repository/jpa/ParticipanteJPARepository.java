package br.com.aeviagens.backend.repository.jpa;

import br.com.aeviagens.backend.domain.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteJPARepository extends JpaRepository<Participante, Long> {
}
