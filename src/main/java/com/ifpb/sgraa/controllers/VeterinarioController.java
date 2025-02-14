package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.models.TratamentoMedico;
import com.ifpb.sgraa.models.Veterinario;
import com.ifpb.sgraa.services.VeterinarioService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veterinarios")
@RequiredArgsConstructor
public class VeterinarioController {
  private final VeterinarioService veterinarioService;

  @PostMapping
  public ResponseEntity<Veterinario> cadastrar(@RequestBody Veterinario veterinario) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(veterinarioService.cadastrarVeterinario(veterinario));
  }

  @PostMapping("/{veterinarioId}/prescrever/{animalId}")
  public ResponseEntity<TratamentoMedico> prescreverTratamento(
      @PathVariable Long veterinarioId,
      @PathVariable Long animalId,
      @RequestParam List<String> medicacoes) {
    TratamentoMedico tratamento = veterinarioService.prescreverTratamento(veterinarioId, animalId, medicacoes);
    return ResponseEntity.ok(tratamento);
  }
}
