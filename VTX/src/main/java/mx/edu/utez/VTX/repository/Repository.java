package mx.edu.utez.VTX.repository;

import mx.edu.utez.VTX.model.VideoJuego;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Repository extends JpaRepository<VideoJuego, Long> {
    List<VideoJuego> findByDisponible(Boolean disponible);
}
