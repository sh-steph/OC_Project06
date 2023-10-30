package com.openclassrooms.mdd.mddapp.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private List<String> detailedMessages;

    public ErrorDto(HttpStatus httpStatus, String message) {
        status = httpStatus.value();
        error = httpStatus.getReasonPhrase();
        this.message = message;
        timestamp = LocalDateTime.now();
        path = ServletUriComponentsBuilder.fromCurrentRequest().build()
                .getPath();
        detailedMessages = Collections.emptyList();
    }

    /**
     * Construit et retourne un objet ErrorDto à partir des informations fournies.
     *
     * @param ex         l'exception
     * @param httpStatus le code de statut HTTP
     * @param path       le chemin de la requête
     * @return l'objet ErrorDto correspondant aux informations fournies
     */
    public static ErrorDto newErrorDto(Exception ex, HttpStatus httpStatus,
                                       String path) {
        return ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status(httpStatus.value())
                .path(path)
                .build();
    }
}
