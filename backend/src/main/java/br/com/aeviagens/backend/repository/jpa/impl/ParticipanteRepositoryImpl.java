package br.com.aeviagens.backend.repository.jpa.impl;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.domain.validation.ValidarNumeroDePassageiros;
import br.com.aeviagens.backend.domain.validation.Validator;
import br.com.aeviagens.backend.domain.validation.VerificarHost;
import br.com.aeviagens.backend.domain.validation.VerificarPassageiros;
import br.com.aeviagens.backend.repository.ParticipanteRepository;
import br.com.aeviagens.backend.repository.jpa.ParticipanteJPARepository;
import br.com.aeviagens.backend.repository.jpa.ViagemJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ParticipanteRepositoryImpl implements ParticipanteRepository {

    private final ViagemJPARepository viagemJPARepository;
    private final ParticipanteJPARepository participanteJPARepository;

    @Autowired
    public ParticipanteRepositoryImpl(ViagemJPARepository viagemJPARepository, ParticipanteJPARepository participanteJPARepository) {
        this.viagemJPARepository = viagemJPARepository;
        this.participanteJPARepository = participanteJPARepository;
    }


    @Override
    public Viagem adicionarParticipanteEmUmaViagem(String hash, Participante participante) {
        Viagem viagem = viagemJPARepository.findByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("hash inválida"));

        Validator validacoes = new ValidarNumeroDePassageiros();
        validacoes
                .encadear(new VerificarPassageiros())
                .encadear(new VerificarHost());

        validacoes.validar(viagem, participante);

        viagem.getParticipantes().add(participante);

        return viagemJPARepository.save(viagem);
    }

    @Override
    public Optional<Participante> recuperarParticipantePeloId(Long id) {
        return participanteJPARepository.findById(id);
    }
}
