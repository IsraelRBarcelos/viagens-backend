package br.com.aeviagens.backend.endpoints;

import br.com.aeviagens.backend.domain.Catalogo;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.*;
import br.com.aeviagens.backend.endpoints.mappers.InserirViagemMapper;
import br.com.aeviagens.backend.exceptions.DomainException;
import br.com.aeviagens.backend.services.CatalogoService;
import br.com.aeviagens.backend.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/catalogos")
@RestController
public class CatalogoEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(CatalogoEndpoint.class);

    private final CatalogoService catalogoService;

    @Autowired
    public CatalogoEndpoint(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping
    public ResponseEntity<DefaultResponseDTO<RecuperarCatalogoResponseDTO>> recuperarCatalogoPorHash(@RequestBody RecuperarCatalogoRequestDTO recuperarCatalogoRequestDTO) {
        LogUtil.logEntrada(logger, this.getClass(), "recuperarCatalogoPorHash");
        try {
            Catalogo catalogo = catalogoService.recuperarCatalogoPorHash(recuperarCatalogoRequestDTO.getHash(), recuperarCatalogoRequestDTO.getIdRequerente());
            return ResponseEntity.ok(DefaultResponseDTO.success(RecuperarCatalogoResponseDTO.toDTO(catalogo)));
        } catch(DomainException ex) {
            return ResponseEntity.status(400).body(DefaultResponseDTO.error(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<DefaultResponseDTO<CriarCatalogoResponseDTO>> criarCatalogo(@RequestBody CriarCatalogoRequestDTO criarCatalogoRequestDTO) {
        LogUtil.logEntrada(logger, this.getClass(), "criarCatalogo");
        try {
            Catalogo catalogo = catalogoService.criarCatalogo(criarCatalogoRequestDTO);
            return ResponseEntity.ok(DefaultResponseDTO.success(CriarCatalogoResponseDTO.toDTO(catalogo)));
        }catch(DomainException ex) {
            return ResponseEntity.status(400).body(DefaultResponseDTO.error("Não foi possível criar o catalogo."));
        }
    }

    @PostMapping("/nova-viagem")
    public ResponseEntity<DefaultResponseDTO<InserirViagemResponseDTO>> adicionarViagemAoCatalogo(@RequestBody AdicionarViagemAoCatalogoRequestDTO adicionarViagemAoCatalogoRequestDTO) {
        LogUtil.logEntrada(logger, this.getClass(), "adicionarViagemAoCatalogo");
        try {
            Viagem viagem = catalogoService.adicionarViagemAoCatalogo(adicionarViagemAoCatalogoRequestDTO);
            return ResponseEntity.ok(DefaultResponseDTO.success(InserirViagemMapper.toDTO(viagem)));
        }catch(DomainException ex) {
            return ResponseEntity.status(400).body(DefaultResponseDTO.error(ex.getMessage()));
        }

    }
}
