package br.com.aeviagens.backend.domain.validation;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;

public class VerificarPassageiros extends Validator {

    @Override
    protected void executarValidacao(Viagem viagem, Participante participante) {
        boolean presente = viagem.getParticipantes().stream()
                .anyMatch(p -> p.getCpf().equals(participante.getCpf()));
        if (!presente) {
            throw new RuntimeException("Participante não está na viagem.");
        }
    }
}