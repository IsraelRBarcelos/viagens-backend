package br.com.aeviagens.backend.domain.validation;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;

public class VerificarHost extends Validator {

    @Override
    protected void executarValidacao(Viagem viagem, Participante participante) {
        if (participante.equals(viagem.getHost())) {
            throw new RuntimeException("Participante não é o host da viagem.");
        }
    }
}
