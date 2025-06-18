package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Catalogo;
import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.AdicionarParticipanteRequestDTO;
import br.com.aeviagens.backend.endpoints.mappers.AdicionarParticipanteMapper;
import br.com.aeviagens.backend.exceptions.DomainException;
import br.com.aeviagens.backend.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipanteServiceImpl implements  ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    @Autowired
    public ParticipanteServiceImpl(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @Override
    public Viagem adicionarParticipanteAViagem(AdicionarParticipanteRequestDTO request) {
        return this.participanteRepository.adicionarParticipanteEmUmaViagem(request.getHash(), AdicionarParticipanteMapper.toParticipante(request.getDadosParticipante()));
    }

    @Override
    public boolean usuarioEstaElegivel(Participante participante, Catalogo catalogo) {
        return catalogo.getParticipantes().contains(participante);
    }

    @Override
    public Participante recuperarParticipantePeloId(Long idParticipante) throws DomainException {
        Optional<Participante> participante = participanteRepository.recuperarParticipantePeloId(idParticipante);
        if(participante.isEmpty()) {
            throw new DomainException("Não foi possivel encontrar participante");
        }
        return participante.get();
    }
}
