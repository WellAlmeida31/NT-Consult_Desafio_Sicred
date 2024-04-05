package br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.port;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoResponseDto;
import br.com.ntconsult.assembleia.application.v1.validation.Cpf;
import br.com.ntconsult.assembleia.domain.associado.AssociadoStatus;
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
@RequestMapping(value = Paths.V1.Associado.ASSOCIADO, produces = MediaType.APPLICATION_JSON_VALUE)
public interface AssociadoControllerPort {

    @PostMapping(Paths.Associado.CREATE)
    @Operation(
            summary = "Create - Cria um Associado",
            description = "<ul><li><p>Deve ser inserido o nome do Associado e CPF válido</p></li>" +
                    "<p><strong>OBS:</strong> Todo Associado é criado com Status ABLE_TO_VOTE</p></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = AssociadoResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<AssociadoResponseDto> criarAssociado(@RequestBody @Valid AssociadoRequestDto dto, UriComponentsBuilder uriBuilder);

    @GetMapping(Paths.Associado.FIND)
    @Operation(
            summary = "Find by CPF - Consulta um Associado",
            description = "Consulta um Associado pelo CPF, inserir somente números, retorna 404 se o Associado não existir"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AssociadoResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<AssociadoResponseDto> buscarAssociadoPorCpf(@PathVariable(Paths.Placeholder.CPF) @Valid @Cpf String cpf);

    @PutMapping(Paths.Associado.STATUS)
    @Operation(
            summary = "Update Status - Inativa ou Ativa um Associado",
            description = "<ul><li><p>Inativa um Associado pelo CPF, inserir somente números, retorna 404 se o Associado não existir</p></li>" +
                    "<li><p> O Status do Associado pode ser modificado para UNABLE_TO_VOTE ou ABLE_TO_VOTE</p></li></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AssociadoResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<AssociadoResponseDto> inativarOuAtivarAssociado(@PathVariable(Paths.Placeholder.CPF) String cpf, @RequestParam(value = Paths.Placeholder.STATUS, required = true) AssociadoStatus status);
}
