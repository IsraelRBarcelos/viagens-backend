package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Catalogo;
import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.AdicionarParticipanteRequestDTO;
import br.com.aeviagens.backend.endpoints.mappers.AdicionarParticipanteMapper;
import br.com.aeviagens.backend.exceptions.DomainException;
import br.com.aeviagens.backend.repository.ParticipanteRepository;
import br.com.aeviagens.backend.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipanteServiceImpl implements  ParticipanteService {

    private static final Logger logger = LoggerFactory.getLogger(ParticipanteServiceImpl.class);

    private final ParticipanteRepository participanteRepository;

    @Autowired
    public ParticipanteServiceImpl(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @Override
    public Viagem adicionarParticipanteAViagem(AdicionarParticipanteRequestDTO request) {
        LogUtil.logEntrada(logger, this.getClass(), "adicionarParticipanteAViagem");
        return this.participanteRepository.adicionarParticipanteEmUmaViagem(request.getHash(), AdicionarParticipanteMapper.toParticipante(request.getDadosParticipante()));
    }

    @Override
    public boolean usuarioEstaElegivel(Participante participante, Catalogo catalogo) {
        LogUtil.logEntrada(logger, this.getClass(), "usuarioEstaElegivel");
        return catalogo.getParticipantes().contains(participante);
    }

    @Override
    public Participante recuperarParticipantePeloId(Long idParticipante) throws DomainException {
        LogUtil.logEntrada(logger, this.getClass(), "recuperarParticipantePeloId");
        Optional<Participante> participante = participanteRepository.recuperarParticipantePeloId(idParticipante);
        if(participante.isEmpty()) {
            logger.error("Não foi encontrado participante com o id: {}", idParticipante);
            throw new DomainException("Não foi possivel encontrar participante");
        }
        return participante.get();
    }
}
