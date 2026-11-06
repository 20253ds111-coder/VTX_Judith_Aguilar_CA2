package mx.edu.utez.VTX.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestDTO(
        @NotBlank String title,
        @NotBlank String plataforma
) {}
