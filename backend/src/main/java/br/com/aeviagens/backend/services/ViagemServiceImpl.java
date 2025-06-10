package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViagemServiceImpl implements ViagemService{

    @Autowired
    ViagemRepository viagemRepository;

    @Override
    public Viagem recuperarViagemPorHash(String hash) {
        Viagem viagemRecuperada =  viagemRepository.recuperarViagemPorHash(hash);

        if(viagemRecuperada.getId() == null) {
            throw new IllegalArgumentException("hash invalido");
        }

        return viagemRecuperada;
    }
}
