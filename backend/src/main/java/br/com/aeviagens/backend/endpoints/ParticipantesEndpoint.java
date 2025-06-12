package br.com.aeviagens.backend.endpoints;

import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.AdicionarParticipanteRequestDTO;
import br.com.aeviagens.backend.endpoints.dto.AdicionarParticipanteResponseDTO;
import br.com.aeviagens.backend.endpoints.dto.DefaultResponseDTO;
import br.com.aeviagens.backend.services.ParticipanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participantes")
public class ParticipantesEndpoint {

    @Autowired
    private ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<DefaultResponseDTO<AdicionarParticipanteResponseDTO>> adicionarParticipante(@Valid @RequestBody AdicionarParticipanteRequestDTO request) {
        Viagem viagem = participanteService.adicionarParticipanteAViagem(request);
        AdicionarParticipanteResponseDTO response = AdicionarParticipanteResponseDTO.toDTO(viagem);
        return ResponseEntity.ok(DefaultResponseDTO.success(response));
    }
}
