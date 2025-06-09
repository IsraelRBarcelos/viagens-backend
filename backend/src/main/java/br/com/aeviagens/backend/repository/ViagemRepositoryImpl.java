package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Viagem;
import org.springframework.stereotype.Repository;

@Repository
public class ViagemRepositoryImpl implements ViagemRepository{
    @Override
    public Viagem recuperarViagemPorHash(String hash) {
        return null;
    }
}
