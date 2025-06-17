package br.com.aeviagens.backend.domain.validation;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;

public abstract class Validator {
    protected Validator proximo;

    public Validator encadear(Validator proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public void validar(Viagem viagem, Participante participante) {
        executarValidacao(viagem, participante);
        if (proximo != null) {
            proximo.validar(viagem, participante);
        }
    }

    protected abstract void executarValidacao(Viagem viagem, Participante participante);
}