package br.com.aeviagens.backend.repository.jpa.impl;

import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.repository.ViagemRepository;
import br.com.aeviagens.backend.repository.jpa.ViagemJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ViagemRepositoryImpl implements ViagemRepository {

    private final ViagemJPARepository viagemJpaRepository;

    @Autowired
    public ViagemRepositoryImpl(ViagemJPARepository viagemJpaRepository) {
        this.viagemJpaRepository = viagemJpaRepository;
    }

    @Override
    public Viagem recuperarViagemPorHash(String hash) throws IllegalArgumentException {
        return viagemJpaRepository.findByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("hash inválida"));
    }

    @Override
    public Viagem salvarViagem(Viagem viagem) {
        viagemJpaRepository.save(viagem);
        return viagem;
    }

    @Override
    public void removerViagemPorHash(String hash) {
        Viagem viagem = recuperarViagemPorHash(hash);
        viagemJpaRepository.delete(viagem);
    }
}
