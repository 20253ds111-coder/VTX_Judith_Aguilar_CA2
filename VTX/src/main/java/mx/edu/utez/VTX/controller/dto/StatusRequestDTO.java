package mx.edu.utez.VTX.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record StatusRequestDTO(
        @NotBlank
        String action
) {
}
