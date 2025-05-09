package br.com.gurudoamor.projeto.controller;

import br.com.gurudoamor.projeto.service.MapsService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class MapsController {

    private final MapsService mapsService;

    public MapsController(MapsService mapsService) {
        this.mapsService = mapsService;
    }

    @GetMapping("/rota")
    public String getRoute(@RequestParam String origem, @RequestParam String destino) {
        return mapsService.calcularRota(origem, destino);
    }
}
