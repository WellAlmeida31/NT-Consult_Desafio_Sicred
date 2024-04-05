package br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.port;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.VotoRequestDto;
import br.com.ntconsult.assembleia.infrastructure.presentation.path.Paths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Paths.V1.Voto.VOTO, produces = MediaType.APPLICATION_JSON_VALUE)
public interface VotoControllerPort {

    @PostMapping(Paths.Voto.CREATE)
    @Operation(
            summary = "Create - Efetua um Voto",
            description = "<ul><li><p>Deve ser inserida o voto como SIM ou NAO, juntamente com o ID da Pauta e CPF do Associado<p></li>" +
                    "<p><strong>OBS:</strong> Somente um Associado ABLE_TO_VOTE pode votar e somente Pautas com status INICIADA aceitam votos </p></ul></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<?> votar(@RequestBody @Valid VotoRequestDto dto);

}
