package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Catalogo;
import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.AdicionarParticipanteRequestDTO;
import br.com.aeviagens.backend.exceptions.DomainException;

public interface ParticipanteService {
    Viagem adicionarParticipanteAViagem(AdicionarParticipanteRequestDTO request);

    boolean usuarioEstaElegivel(Participante participante, Catalogo catalogo);

    Participante recuperarParticipantePeloId(Long idParticipante) throws DomainException;
}
