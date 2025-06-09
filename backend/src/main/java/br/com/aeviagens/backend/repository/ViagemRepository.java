package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Viagem;

public interface ViagemRepository {
    public Viagem recuperarViagemPorHash(String hash);
}
