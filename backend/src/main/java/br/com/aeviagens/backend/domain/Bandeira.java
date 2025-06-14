package br.com.aeviagens.backend.domain;

import lombok.Getter;

@Getter
public enum Bandeira {
    ELO("Elo"),
    MASTER("MasterCard");

    private final String bandeira;

    Bandeira(String bandeira) {
        this.bandeira = bandeira;
    }
}
