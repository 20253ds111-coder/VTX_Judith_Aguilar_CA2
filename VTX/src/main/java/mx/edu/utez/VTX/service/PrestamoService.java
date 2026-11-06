package mx.edu.utez.VTX.service;

import mx.edu.utez.VTX.controller.dto.PrestamoDto;
import mx.edu.utez.VTX.exception.NotValidException;
import mx.edu.utez.VTX.exception.ResourceNotFoundException;
import mx.edu.utez.VTX.model.Prestamo;
import mx.edu.utez.VTX.model.VideoJuego;
import mx.edu.utez.VTX.repository.PrestamoRepositoy;
import mx.edu.utez.VTX.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepositoy prestamoRepository;
    private final Repository videoJuegoRepository;

    public PrestamoService(PrestamoRepositoy prestamoRepository, Repository videoJuegoRepository) {
        this.prestamoRepository = prestamoRepository;
        this.videoJuegoRepository = videoJuegoRepository;
    }

    @Transactional
    public Prestamo solicitarPrestamo(PrestamoDto dto) {
        VideoJuego juego = videoJuegoRepository.findById(dto.videojuegoId())
                .orElseThrow(() -> new ResourceNotFoundException("El videojuego no existe con ID: " + dto.videojuegoId()));

        if (!juego.getDisponible()) {
            throw new NotValidException("El videojuego no está disponible actualmente.");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setNombreSolicitante(dto.nombreSolicitante());
        prestamo.setFechaPrestamo(LocalDate.now().toString());
        prestamo.setFechaDevolucion(null);
        prestamo.setEstado("ACTIVO");
        prestamo.setVideoJuego(juego);

        juego.setDisponible(false);
        videoJuegoRepository.save(juego);

        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public Prestamo devolverVideoJuego(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new ResourceNotFoundException("El préstamo no existe."));

        if (!"ACTIVO".equals(prestamo.getEstado())) {
            throw new NotValidException("Solo se pueden devolver préstamos con estado ACTIVO.");
        }

        prestamo.setEstado("DEVUELTO");
        prestamo.setFechaDevolucion(LocalDate.now().toString());

        VideoJuego juego = prestamo.getVideoJuego();
        juego.setDisponible(true);
        videoJuegoRepository.save(juego);

        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public Prestamo cancelarPrestamo(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new ResourceNotFoundException("El préstamo no existe."));

        if (!"ACTIVO".equals(prestamo.getEstado())) {
            throw new NotValidException("Solo se pueden cancelar préstamos activos.");
        }

        prestamo.setEstado("CANCELADO");

        VideoJuego juego = prestamo.getVideoJuego();
        juego.setDisponible(true);
        videoJuegoRepository.save(juego);

        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public void cancelarTodosLosPrestamosActivos(Long videojuegoId) {
        VideoJuego juego = videoJuegoRepository.findById(videojuegoId)
                .orElseThrow(() -> new ResourceNotFoundException("El videojuego no existe."));

        List<Prestamo> prestamosActivos = prestamoRepository.findByVideoJuegoAndEstado(juego, "ACTIVO");

        for (Prestamo prestamo : prestamosActivos) {
            prestamo.setEstado("CANCELADO");
            prestamoRepository.save(prestamo);
        }

        juego.setDisponible(true);
        videoJuegoRepository.save(juego);
    }

    public List<Prestamo> listarPorEstado(String estado) {
        String estadoUpper = estado.toUpperCase();
        if (!List.of("ACTIVO", "DEVUELTO", "CANCELADO").contains(estadoUpper)) {
            throw new NotValidException("Estado no válido. Use: ACTIVO, DEVUELTO o CANCELADO.");
        }
        return prestamoRepository.findByEstado(estadoUpper);
    }
}
