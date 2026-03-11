package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Viagem;

public interface ViagemRepository {
    Viagem recuperarViagemPorHash(String hash) throws IllegalArgumentException;
    Viagem salvarViagem(Viagem viagem);
    void removerViagemPorHash(String hash);
}
