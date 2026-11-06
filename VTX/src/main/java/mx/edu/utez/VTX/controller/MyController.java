package mx.edu.utez.VTX.controller;

import jakarta.validation.Valid;
import mx.edu.utez.VTX.controller.dto.RequestDTO;
import mx.edu.utez.VTX.controller.dto.ResponseDto;
import mx.edu.utez.VTX.model.VideoJuego;
import mx.edu.utez.VTX.service.MyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/videojuego")
public class MyController {

    private final MyService service;

    public MyController(MyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<ResponseDto>> findDisponibles() {
        return ResponseEntity.ok(service.findDisponibles());
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@Valid @RequestBody RequestDTO dto) {
        VideoJuego videojuego = service.save(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.fromEntity(videojuego));
    }

    public VideoJuego toEntity(RequestDTO dto) {
        VideoJuego videojuego = new VideoJuego();
        videojuego.setTitle(dto.title());
        videojuego.setPlataforma(dto.plataforma());
        return videojuego;
    }
}
