package br.com.aeviagens.backend.repository.jpa.impl;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.repository.ParticipanteRepository;
import br.com.aeviagens.backend.repository.jpa.ParticipanteJPARepository;
import br.com.aeviagens.backend.repository.jpa.ViagemJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipanteRepositoryImpl implements ParticipanteRepository {

    @Autowired
    private ParticipanteJPARepository participanteJPARepository;

    @Autowired
    private ViagemJPARepository viagemJPARepository;


    @Override
    public Viagem adicionarParticipanteEmUmaViagem(String hash, Participante participante) {
        Viagem viagem = viagemJPARepository.findByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("hash inválida"));

        viagem.getParticipantes().add(participante);

        return viagemJPARepository.save(viagem);
    }
}
