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

        public VideoJuego save(VideoJuego videoJuego) {
            videoJuego.setDisponible(true);
            return repository.save(videoJuego);
        }

        public VideoJuego status(Long id, String action) {

            VideoJuego videoJuego = repository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("No se encontro con esa id: " + id)
            );

            if (action.equals("RESOLVE") || action.equals("REOPEN")) {
                if ((videoJuego.getDisponible()) && action.equals("RESOLVE")) {
                    videoJuego.setDisponible(false);

                } else if ((!videoJuego.getDisponible()) && action.equals("REOPEN")) {
                    videoJuego.setDisponible(true);

                } else {
                    throw new NotValidException("El juego ya ha sido prestado o no se encuentra");
                }
            } else {
                throw new NotCorrectException("accion no válida");
            }
            return repository.save(videoJuego);

        }
}
