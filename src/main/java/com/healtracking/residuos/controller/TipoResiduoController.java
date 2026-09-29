package com.healtracking.residuos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TipoResiduoController {
    @GetMapping ("/tipos")
    public String listarTipos() {
        return "lista-tipos";
    }
}
