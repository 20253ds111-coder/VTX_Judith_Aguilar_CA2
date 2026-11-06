package mx.edu.utez.VTX.controller;

import jakarta.validation.Valid;
import mx.edu.utez.VTX.controller.dto.PrestamoDto;
import mx.edu.utez.VTX.model.Prestamo;
import mx.edu.utez.VTX.service.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<Prestamo> solicitarPrestamo(@Valid @RequestBody PrestamoDto dto) {
        return ResponseEntity.ok(prestamoService.solicitarPrestamo(dto));
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Prestamo> devolverVideoJuego(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.devolverVideoJuego(id));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Prestamo> cancelarPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.cancelarPrestamo(id));
    }

    @DeleteMapping("/videojuego/{videojuegoId}/cancelar-todos")
    public ResponseEntity<Void> cancelarTodosLosPrestamosActivos(@PathVariable Long videojuegoId) {
        prestamoService.cancelarTodosLosPrestamosActivos(videojuegoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Prestamo>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(prestamoService.listarPorEstado(estado));
    }
}
