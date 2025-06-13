package br.com.aeviagens.backend.domain.validation;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;

public class ValidarNumeroDePassageiros extends Validator {

    @Override
    protected void executarValidacao(Viagem viagem, Participante participante) {
        if (viagem.getParticipantes().size() >= viagem.getNumeroMaximoDePassageiros()) {
            throw new RuntimeException("A viagem está lotada.");
        }
    }
}
