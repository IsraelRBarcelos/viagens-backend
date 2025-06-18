package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.repository.ViagemRepository;
import br.com.aeviagens.backend.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViagemServiceImpl implements ViagemService{

    private static final Logger logger = LoggerFactory.getLogger(ViagemServiceImpl.class);

    @Autowired
    ViagemRepository viagemRepository;

    @Override
    public Viagem recuperarViagemPorHash(String hash) throws IllegalArgumentException {
        LogUtil.logEntrada(logger, this.getClass(), "recuperarViagemPorHash");
        Viagem viagemRecuperada =  viagemRepository.recuperarViagemPorHash(hash);

        if(viagemRecuperada == null) {
            logger.error("Nenhuma viagem foi encontrada com o hash enviado.");
            throw new IllegalArgumentException("hash invalido");
        }

        return viagemRecuperada;
    }

    @Override
    public Viagem salvarViagem(Viagem viagem) {
        LogUtil.logEntrada(logger, this.getClass(), "salvarViagem");
        return viagemRepository.salvarViagem(viagem);
    }

    @Override
    public void removerViagemPorHash(String hash) {
        LogUtil.logEntrada(logger, this.getClass(), "removerViagemPorHash");
        viagemRepository.removerViagemPorHash(hash);
        logger.info("Viagem de hash {} foi removida.", hash);
    }
}
