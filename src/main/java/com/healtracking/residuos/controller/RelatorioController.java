package com.healtracking.residuos.controller;

import com.healtracking.residuos.model.Residuo;
import com.healtracking.residuos.repository.ResiduoRepository;
import com.healtracking.residuos.service.RelatorioService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private ResiduoRepository residuoRepository;

    @Autowired
    private RelatorioService relatorioService;

    // Rota para baixar o PDF: http://localhost:8080/relatorios/residuos/pdf
    @GetMapping("/residuos/pdf")
    public void baixarPdfResiduos(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio_residuos.pdf");

        List<Residuo> listaResiduos = residuoRepository.findAll();
        relatorioService.exportarResiduosPDF(response, listaResiduos);
    }

    // Rota para baixar o CSV: http://localhost:8080/relatorios/residuos/csv
    @GetMapping("/residuos/csv")
    public void baixarCsvResiduos(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio_residuos.csv");

        List<Residuo> listaResiduos = residuoRepository.findAll();
        relatorioService.exportarResiduosCSV(response.getWriter(), listaResiduos);
    }
}

