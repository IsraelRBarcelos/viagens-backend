package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Catalogo;
import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.CatalogoEndpoint;
import br.com.aeviagens.backend.endpoints.dto.AdicionarViagemAoCatalogoRequestDTO;
import br.com.aeviagens.backend.endpoints.dto.CriarCatalogoRequestDTO;
import br.com.aeviagens.backend.endpoints.mappers.InserirViagemMapper;
import br.com.aeviagens.backend.exceptions.DomainException;
import br.com.aeviagens.backend.repository.CatalogoRepository;
import br.com.aeviagens.backend.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogoServiceImpl implements  CatalogoService{

    private static final Logger logger = LoggerFactory.getLogger(CatalogoServiceImpl.class);

    private final CatalogoRepository catalogoRepository;
    private final ParticipanteService participanteService;
    private final ViagemService viagemService;

    @Autowired
    public CatalogoServiceImpl(CatalogoRepository catalogoRepository, ParticipanteService participanteService, ViagemService viagemService) {
        this.catalogoRepository = catalogoRepository;
        this.participanteService = participanteService;
        this.viagemService = viagemService;
    }

    @Override
    public Catalogo recuperarCatalogoPorHash(String hash, Long idParticipante) throws DomainException {
        LogUtil.mostrarObjetoEmqualquerPonto(logger, this.getClass(), "recuperarCatalogoPorHash", hash);
        LogUtil.logEntrada(logger, this.getClass(), "recuperarCatalogoPorHash");
        Catalogo catalogoRecuperado = verificarExistenciaDeCatalogo(hash);
        LogUtil.serviceInfo(logger, this.getClass(), "recuperarCatalogoPorHash", "Foi retornado catalogo do repository, com dados: ", catalogoRecuperado);
        Participante participante = participanteService.recuperarParticipantePeloId(idParticipante);
        if (participanteService.usuarioEstaElegivel(participante, catalogoRecuperado)) {
            return catalogoRecuperado;
        }

        throw new DomainException("Não foi possível encontrar o catalogo de viagens.");

    }

    @Override
    public Catalogo criarCatalogo(CriarCatalogoRequestDTO criarCatalogoRequestDTO) throws DomainException {
        LogUtil.logEntrada(logger, this.getClass(), "criarCatalogo");
        Participante participante = participanteService.recuperarParticipantePeloId(criarCatalogoRequestDTO.getIdHost());
        return catalogoRepository.criarCatalogo(criarCatalogoRequestDTO.getTitulo(), criarCatalogoRequestDTO.getDescricao(), participante);
    }

    @Override
    public Viagem adicionarViagemAoCatalogo(AdicionarViagemAoCatalogoRequestDTO adicionarViagemAoCatalogoRequestDTO) throws DomainException {
        LogUtil.logEntrada(logger, this.getClass(), "adicionarViagemAoCatalogo");
        Catalogo catalogo = verificarExistenciaDeCatalogo(adicionarViagemAoCatalogoRequestDTO.getHashDoCatalogo());
        LogUtil.mostrarObjetoEmqualquerPonto(logger, this.getClass(), "adicionarViagemAoCatalogoRequestDTO", catalogo);
        Viagem novaViagem = viagemService.salvarViagem(InserirViagemMapper.toViagem(adicionarViagemAoCatalogoRequestDTO.getDadosDaViagem()));
        logger.info("Viagem salva, adicionando viagem ao catalogo de hash {}", catalogo.getHash());
        return catalogoRepository.adicionarViagemAoCatalogo(catalogo, novaViagem);
    }

    @Override
    public Catalogo verificarExistenciaDeCatalogo(String hash) throws DomainException {
        LogUtil.logEntrada(logger, this.getClass(), "verificarExistenciaDeCatalogo");
        Optional<Catalogo> catalogo = catalogoRepository.recuperarCatalogo(hash);
        if (catalogo.isEmpty()) {
            LogUtil.serviceInfo(logger, this.getClass(), "verificarExistenciaDeCatalogo", "Não existe catalogo com a hash: ", hash);
            throw new DomainException("Não foi possível encontrar o catalogo de viagens.");
        }
        return catalogo.get();
    }
}
