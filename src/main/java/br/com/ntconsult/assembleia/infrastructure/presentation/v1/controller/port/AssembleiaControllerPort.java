package br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.port;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaResponseDto;
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

import java.util.List;

@RestController
@RequestMapping(value = Paths.V1.Assembleia.ASSEMBLEIA, produces = MediaType.APPLICATION_JSON_VALUE)
public interface AssembleiaControllerPort {

    @GetMapping(Paths.Assembleia.FIND_ALL)
    @Operation(
            summary = "Find All",
            description = "Consulta todas as Assembleias"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    ResponseEntity<List<AssembleiaResponseDto>> findall();

    @PostMapping(Paths.Assembleia.CREATE)
    @Operation(
            summary = "Create - Cria uma Assembléia",
            description = "<ul><li><p>Deve ser inserido o nome da Assembleia<p></li>" +
                    "<p><strong>OBS:</strong> Toda Assembleais é criada com Status Aberta</p></ul></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = AssembleiaResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<AssembleiaResponseDto> criarAssembleia(@RequestBody @Valid AssembleiaRequestDto dto, UriComponentsBuilder uriBuilder);


    @PutMapping(Paths.Assembleia.ENCLOSE)
    @Operation(
            summary = "Enclose - Encerra uma Assembléia",
            description = "<ul><li><p>Deve ser informado o Id da Assembleia, o Status da Assembleia será modificado para ENCERRADA. Todas as Pautas associadas a esta Assembleia serão finalizadas</p></li></ul>")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AssembleiaResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity encerrarAssembleia(@PathVariable(Paths.Placeholder.ID) Long id);

    @GetMapping(Paths.Assembleia.FIND)
    @Operation(
            summary = "Find by Id - Consulta uma Assembléia",
            description = "Consulta uma Assembléia pelo Id, retornando suas Pautas ou 404 se a Assembleia não existir"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AssembleiaResponseDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<AssembleiaResponseDto> buscarAssembleia(@PathVariable(Paths.Placeholder.ID) Long id);

}
