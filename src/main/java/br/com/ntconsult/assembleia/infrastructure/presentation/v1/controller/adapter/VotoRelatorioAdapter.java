package br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.adapter;

import br.com.ntconsult.assembleia.application.v1.usecase.implementations.GerarRelatorioPdfDeVotosPorIdUseCaseImpl;
import br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.port.VotoRelatorioPort;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotoRelatorioAdapter implements VotoRelatorioPort {

    private final GerarRelatorioPdfDeVotosPorIdUseCaseImpl gerarRelatorioPdfDeVotosPorIdUseCase;

    @Override
    public ResponseEntity<InputStreamResource> contagemVotosPautaReport(Long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=voto.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(gerarRelatorioPdfDeVotosPorIdUseCase.RelatorioPdfDeVotos(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
