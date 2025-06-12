package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.AdicionarParticipanteRequestDTO;

public interface ParticipanteService {
    Viagem adicionarParticipanteAViagem(AdicionarParticipanteRequestDTO request);
}
