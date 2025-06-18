package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Viagem;

public interface ViagemService {

    Viagem recuperarViagemPorHash(String hash) throws IllegalArgumentException;
    Viagem salvarViagem(Viagem viagem);
    void removerViagemPorHash(String hash);
}