package mx.edu.utez.VTX.service;

import mx.edu.utez.VTX.controller.dto.ResponseDto;
import mx.edu.utez.VTX.model.VideoJuego;
import mx.edu.utez.VTX.repository.Repository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyService {

    private final Repository repository;

    public MyService(Repository repository) {
        this.repository = repository;
    }

    public List<ResponseDto> findAll() {
        return repository.findAll().stream()
                .map(ResponseDto::fromEntity)
                .toList();
    }

    public List<ResponseDto> findDisponibles() {
        return repository.findByDisponible(true).stream()
                .map(ResponseDto::fromEntity)
                .toList();
    }

    public VideoJuego save(VideoJuego videoJuego) {
        videoJuego.setDisponible(true);
        return repository.save(videoJuego);
    }
}
