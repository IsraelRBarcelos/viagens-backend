package br.com.aeviagens.backend.endpoints;

import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.ViagemResponseDTO;
import br.com.aeviagens.backend.services.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viagens")
public class ViagemEndpoint {
  
  @Autowired
  private ViagemService viagemService;

  @GetMapping
  public ResponseEntity<ViagemResponseDTO> retornoDeViagens(String hash) {
    try {
      Viagem viagem = viagemService.recuperarViagemPorHash(hash);
      return ResponseEntity.ok(ViagemResponseDTO.toDTO(viagem));
    }catch(IllegalArgumentException ex) {
      return ResponseEntity.status(400).build();
    }
  } 
}
