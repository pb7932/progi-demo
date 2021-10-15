package com.example.progidemo.controllers;

import com.example.progidemo.models.Djelatnik;
import com.example.progidemo.services.DjelatnikService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Odgovata na sve zahtjeve povezane s <code>Djelatnik</code>-om.
 */
@RestController
//@RequestMapping(value = "/djelatnici") pa onda netreba svugde taj '/djelatnici' pisati
public class DjelatnikController {

    /**
     * Servis kojem controller prosljeduje zahtjeve.
     */
    private final DjelatnikService djelatnikService;

    /**
     * Klasni konstruktor.
     * @param djelatnikService servis kojem controller prosljeduje zahtjeve
     */
    public DjelatnikController(DjelatnikService djelatnikService) {
        this.djelatnikService = djelatnikService;
    }

    @GetMapping("/djelatnici")
    public List<Djelatnik> getAllDjelatnici() {
        return this.djelatnikService.getAllDjelatnici();
    }

    @GetMapping("/djelatnici/{id}")
    public Djelatnik getDjelatnikById(@PathVariable Long id) { return this.djelatnikService.getDjelatnikById(id); }

    //ako je neka varijabla u request body-ju, odnosno predaje se ko json, onda se stavi @RequestBody
    @PostMapping("/djelatnici")
    public int saveDjelatnik(@RequestBody Djelatnik djelatnik) {
        return this.djelatnikService.saveDjelatnik(djelatnik);
    }

    @PutMapping("/djelatnici")
    public int updateDjelatnik(@RequestBody Djelatnik djelatnik) {
        return this.djelatnikService.updateDjelanitk(djelatnik);
    }

    //ako je neka varijabla u putanji (ko kak je tu id) onda se stavlja @PathVarible da ga spring more pronaci
    @DeleteMapping("/djelatnici/{id}")
    public int deleteDjelatnik(@PathVariable Long id) {
        return this.djelatnikService.deleteDjelatnik(id);
    }
}
