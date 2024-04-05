package br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.port;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.ResultadoPautaResponse;
import br.com.ntconsult.assembleia.domain.pauta.PautaStatus;
import br.com.ntconsult.assembleia.infrastructure.presentation.path.Paths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = Paths.V1.Pauta.PAUTA, produces = MediaType.APPLICATION_JSON_VALUE)
public interface PautaControllerPort {

    @PostMapping(Paths.Pauta.CREATE)
    @Operation(
            summary = "Create - Cria uma Pauta",
            description = "<ul><li><p>Deve ser inserida a descrição da pauta, a data de término e o ID da Assembiea a qual a Pauta pertence<p></li>" +
                    "<p><strong>OBS:</strong> Toda Pauta é criada com Status INICIADA. A data de fim deve ser no formato: yyyy-MM-ddTHH:mm (ano-mês-diaThora:minuto)</p></ul></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = PautaResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<PautaResponseDto> criarPauta(@RequestBody @Valid PautaRequestDto dto, UriComponentsBuilder uriBuilder);

    @GetMapping(Paths.Pauta.FIND)
    @Operation(
            summary = "Find by ID - Consulta uma Pauta",
            description = "Consulta uma Pauta pelo Id, retorna 404 se a Pauta não existir"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PautaResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<PautaResponseDto> buscarPautaPorId(@PathVariable(Paths.Placeholder.ID) Long id);

    @PutMapping(Paths.Pauta.STATUS)
    @Operation(
            summary = "Update Status - Inicia ou Finaliza uma Pauta",
            description = "<ul><li><p>Inicia ou Finaliza uma Pauta pelo ID, retorna 404 se a Pauta não existir</p></li>" +
                    "<li><p> O Status da Pauta pode ser modificado para INICIADA ou FINALIZADA</p></li></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PautaResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<PautaResponseDto> iniciarOuFinalizarPauta(@PathVariable(Paths.Placeholder.ID) Long id,
                                                                 @RequestParam(value = Paths.Placeholder.STATUS, required = true) PautaStatus status);

    @GetMapping(Paths.Pauta.RESULT)
    @Operation(
            summary = "Result - Consulta o resultado da Pauta",
            description = "Consulta o resultado da votação de uma Pauta pelo Id, retorna 404 se a Pauta não existir"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResultadoPautaResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<ResultadoPautaResponse> resultadoDaPauta(@PathVariable(Paths.Placeholder.ID) Long id);
}
