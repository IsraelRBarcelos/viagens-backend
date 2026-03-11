package br.com.aeviagens.backend.endpoints;

import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.*;
import br.com.aeviagens.backend.endpoints.mappers.InserirViagemMapper;
import br.com.aeviagens.backend.services.ViagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viagens")
public class ViagemEndpoint {
  
  @Autowired
  private ViagemService viagemService;

  @PostMapping("/{hash}")
  public ResponseEntity<DefaultResponseDTO<RecuperarViagemResponseDTO>> retornoDeViagens(@PathVariable String hash) {
    if (hash.isBlank()) {
      return ResponseEntity
              .badRequest()
              .body(DefaultResponseDTO.error("Hash da viagem não pode ser nulo ou vazio."));
    }

    try {
      Viagem viagem = viagemService.recuperarViagemPorHash(hash);
      RecuperarViagemResponseDTO dto = RecuperarViagemResponseDTO.toDTO(viagem); //

      return ResponseEntity
              .ok(DefaultResponseDTO.success(dto, "Viagem recuperada com sucesso."));
    } catch (IllegalArgumentException ex) {
      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body(DefaultResponseDTO.error("Viagem não encontrada para o hash informado."));
    } catch (Exception ex) {
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(DefaultResponseDTO.error("Erro interno ao buscar a viagem."));
    }
  }


  @PostMapping
  public ResponseEntity<DefaultResponseDTO<InserirViagemResponseDTO>> inserirNovaViagem(@Valid @RequestBody InserirViagemRequestDTO request) {
    Viagem viagem = InserirViagemMapper.toViagem(request);
    Viagem viagemCompleta = viagemService.salvarViagem(viagem);

    return ResponseEntity.ok().body(DefaultResponseDTO.success(InserirViagemMapper.toDTO(viagemCompleta)));
  }

  @DeleteMapping("/{hash}")
  public ResponseEntity<DefaultResponseDTO<Void>> removerViagem(@PathVariable String hash) {
    if (hash.isBlank()) {
      return ResponseEntity
              .badRequest()
              .body(DefaultResponseDTO.error("Hash é obrigatório para encontrar uma viagem."));
    }

    try {
      viagemService.removerViagemPorHash(hash);
      return ResponseEntity
              .ok(DefaultResponseDTO.success(null, "Viagem removida com sucesso."));
    } catch (IllegalArgumentException ex) {
      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body(DefaultResponseDTO.error("Viagem não encontrada para o hash informado."));
    } catch (Exception ex) {
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(DefaultResponseDTO.error("Erro interno ao tentar remover a viagem."));
    }
  }
}
