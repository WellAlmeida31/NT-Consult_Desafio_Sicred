package br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.port;

import br.com.ntconsult.assembleia.infrastructure.presentation.path.Paths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Paths.V1.Pauta.PAUTA, produces = MediaType.APPLICATION_PDF_VALUE)
public interface VotoRelatorioPort {

    @GetMapping(Paths.Pauta.REPORT)
    @Operation(
            summary = "Report - PDF do resultado da Pauta",
            description = "<ul><li><p>Deve ser inserido o ID da Pauta, Será emitido um PDF com o resultado da Pauta e contagem de votos<p></li>")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/pdf") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    ResponseEntity<InputStreamResource> contagemVotosPautaReport(@PathVariable(Paths.Placeholder.ID) Long id);
}
