package br.com.ntconsult.assembleia.application.v1.usecase.implementations;


import br.com.ntconsult.assembleia.application.v1.usecase.contracts.GerarRelatorioPdfDeVotosPorIdUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.ResultadoPautaResponse;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.ByteArrayInputStream;

@Component
@RequiredArgsConstructor
public class GerarRelatorioPdfDeVotosPorIdUseCaseImpl implements GerarRelatorioPdfDeVotosPorIdUseCase {

    private final PautaRepository pautaRepository;
    private final ResultadoDaPautaPorIdUseCaseImpl resultadoDaPautaPorIdUseCase;
    @Override
    public ByteArrayInputStream RelatorioPdfDeVotos(Long id) throws IOException {
        var pauta = pautaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        var resultado = resultadoDaPautaPorIdUseCase.resultadoDaPautaPorId(id);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDocument);

        Paragraph title = new Paragraph("Assembleia: "+pauta.getAssembleia().getNome()+"\nContagens de Voto da Pauta: "+ pauta.getDescricao())
                .setFontSize(20)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD))
                .setTextAlignment(TextAlignment.CENTER);

        document.add(title);
        document.add(new Paragraph("\n"));

        preencherRelatorio(resultado, document);

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private static void preencherRelatorio(ResultadoPautaResponse resultado, Document document) {
        Table table = new Table(4)
                .useAllAvailableWidth()
                .setTextAlignment(TextAlignment.CENTER);


        table.addHeaderCell("DESCRICAO DA PAUTA");
        table.addHeaderCell("QTD VOTOS SIM");
        table.addHeaderCell("QTD VOTOS NAO");
        table.addHeaderCell("RESULTADO DA PAUTA");

        table.addCell(resultado.pautaDescricao());
        table.addCell(resultado.votosSim().toString());
        table.addCell(resultado.votosNao().toString());
        table.addCell(resultado.resultado());

        document.add(table);
        document.close();
    }
}
