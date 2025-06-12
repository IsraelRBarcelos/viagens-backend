package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.AdicionarParticipanteRequestDTO;
import br.com.aeviagens.backend.endpoints.mappers.AdicionarParticipanteMapper;
import br.com.aeviagens.backend.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipanteServiceImpl implements  ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Override
    public Viagem adicionarParticipanteAViagem(AdicionarParticipanteRequestDTO request) {
        return participanteRepository.adicionarParticipanteEmUmaViagem(request.getHash(), AdicionarParticipanteMapper.toParticipante(request.getDadosParticipante()));
    }
}
