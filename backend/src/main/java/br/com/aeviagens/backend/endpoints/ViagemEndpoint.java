package br.com.aeviagens.backend.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viagens")
public class ViagemEndpoint {
  
  @Autowired
  private ViagemService viagemService;

  @GetMapping
  public ViagemResponseDTO retornoDeViagens(String hash) {
    Viagem viagem = viagemService.recuperarViagemPorHash(hash);
    return ViagemResponseDTO.toDTO(viagem);
  } 
}
