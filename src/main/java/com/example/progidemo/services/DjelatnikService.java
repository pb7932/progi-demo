package com.example.progidemo.services;

import com.example.progidemo.daos.DjelatnikDAO;
import com.example.progidemo.models.Djelatnik;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Zna pozvati ispravan DAO objekt te ispravnu metodu nad njim kako bi se izvrsila zatrazena akcija.
 */
@Service
public class DjelatnikService {

    //mozda bude se spring boot zalil da nebre pronaci ovaj objekt ali i dalje se aplikacija moze pokrenut
    private final DjelatnikDAO djelatnikDAO;

    /**
     * Klasni konstruktor.
     * @param djelatnikDAO
     */
    public DjelatnikService(DjelatnikDAO djelatnikDAO) {
        this.djelatnikDAO = djelatnikDAO;
    }

    public List<Djelatnik> getAllDjelatnici() {
        return this.djelatnikDAO.getAllDjelatnik();
    }

    public Djelatnik getDjelatnikById(Long id) {
        return this.djelatnikDAO.getDjelatnikById(id);
    }

    public int saveDjelatnik(Djelatnik djelatnik) {
        return this.djelatnikDAO.saveDjelatnik(djelatnik);
    }

    public int deleteDjelatnik(Long id) {
        return this.djelatnikDAO.deleteDjelatnik(id);
    }

    public int updateDjelanitk(Djelatnik djelatnik) { return this.djelatnikDAO.updateDjelatnik(djelatnik);}

}
