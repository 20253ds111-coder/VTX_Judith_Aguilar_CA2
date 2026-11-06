package mx.edu.utez.VTX.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrestamoDto(
        @NotNull Long videojuegoId,
        @NotBlank String nombreSolicitante
) {}
