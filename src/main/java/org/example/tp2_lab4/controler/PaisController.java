package org.example.tp2_lab4.controler;

import lombok.RequiredArgsConstructor;
import org.example.tp2_lab4.model.entity.Pais;
import org.example.tp2_lab4.service.PaisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pais")
@RequiredArgsConstructor
public class PaisController {

    private final PaisService paisService;

    @GetMapping("/cargar")
    public ResponseEntity<String> cargarPaises() {
        try {
            paisService.cargarPaises();
            return ResponseEntity.ok("Carga de países completada");
        }catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }

    }
    @GetMapping("/get")
    public ResponseEntity<List<Pais>> obtenerTodos() {
        return ResponseEntity.ok(paisService.obtenerTodosLosPaises());
    }
}
