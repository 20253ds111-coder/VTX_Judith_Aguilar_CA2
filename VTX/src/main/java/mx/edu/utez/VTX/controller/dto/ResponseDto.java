package mx.edu.utez.VTX.controller.dto;

import mx.edu.utez.VTX.model.VideoJuego;

public record ResponseDto(Long id, String title, String plataforma, Boolean disponible) {
    public static ResponseDto fromEntity(VideoJuego juego) {
        return new ResponseDto(juego.getId(), juego.getTitle(), juego.getPlataforma(), juego.getDisponible());
    }
}
