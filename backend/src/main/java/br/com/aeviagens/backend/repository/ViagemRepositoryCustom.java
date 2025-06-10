package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Viagem;

public interface ViagemRepositoryCustom {
    Viagem recuperarViagemPorHash(String hash);
}
