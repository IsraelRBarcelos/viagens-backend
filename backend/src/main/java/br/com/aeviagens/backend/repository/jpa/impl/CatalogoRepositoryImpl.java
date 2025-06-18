package br.com.aeviagens.backend.repository.jpa.impl;

import br.com.aeviagens.backend.domain.Catalogo;
import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.exceptions.DomainException;
import br.com.aeviagens.backend.repository.CatalogoRepository;
import br.com.aeviagens.backend.repository.jpa.CatalogoJPARepository;
import br.com.aeviagens.backend.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CatalogoRepositoryImpl implements CatalogoRepository {

    private final CatalogoJPARepository catalogoJPARepository;

    private static final Logger logger = LoggerFactory.getLogger(CatalogoRepositoryImpl.class);

    @Autowired
    public CatalogoRepositoryImpl(CatalogoJPARepository catalogoJPARepository) {
        this.catalogoJPARepository = catalogoJPARepository;
    }

    @Override
    public Optional<Catalogo> recuperarCatalogo(String hash) {
        LogUtil.logEntrada(logger, this.getClass(), "recuperarCatalogo");
        return catalogoJPARepository.findByHash(hash);
    }

    @Override
    public Catalogo criarCatalogo(String titulo, String descricao, Participante host) {
        LogUtil.logEntrada(logger, this.getClass(), "criarCatalogo");
        Catalogo catalogo = Catalogo.builder()
                .titulo(titulo)
                .descricao(descricao)
                .host(host)
                .participantes(List.of(host))
                .build();
        catalogoJPARepository.save(catalogo);
        return catalogo;
    }

    @Override
    public Viagem adicionarViagemAoCatalogo(Catalogo catalogo, Viagem novaViagem) {
        LogUtil.logEntrada(logger, this.getClass(), "adicionarViagemAoCatalogo");
        catalogo.getViagens().add(novaViagem);
        Catalogo novoCatalogo = catalogoJPARepository.save(catalogo);
        logger.info("Entidade criada, {}",novoCatalogo);
        return novaViagem;
    }
}
