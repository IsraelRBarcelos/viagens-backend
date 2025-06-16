package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.DadosParticipanteDTO;

import java.util.List;

public interface ParticipanteRepository {
    Viagem adicionarParticipanteEmUmaViagem(String hash, Participante participante);
}
