package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.DadosParticipanteDTO;

import java.util.List;
import java.util.Optional;

public interface ParticipanteRepository {
    Viagem adicionarParticipanteEmUmaViagem(String hash, Participante participante);

    Optional<Participante> recuperarParticipantePeloId(Long id);
}
