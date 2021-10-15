package com.example.progidemo.daos;

import com.example.progidemo.models.Djelatnik;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Radi operacije s tablicom djelatnici nad bazom.
 */
@Repository
@Transactional
public class DjelatnikDAO {

    /**
     * Objekt za povezivanje i izvrsavanje zahvata nad bazom podataka.
     */
    private final EntityManager entityManager;

    /**
     * Klasni konstruktor.
     * @param entityManager objekt za povezivanje i izvrsavanje zahvata nad bazom podataka
     */
    public DjelatnikDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Vraca sve objekte tipa <code>Djelatnik</code> koji su pohranjeni u bazi.
     * @return lista djelatnika, null ako baza nema pohranjeno nijednog djelatnika
     */
    public List<Djelatnik> getAllDjelatnik() {
        try {
            String sql = "SELECT * FROM djelatnici";

            Query query = entityManager.createNativeQuery(sql);
            return (List<Djelatnik>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vraca djelatnika iz baze podataka prema id-u <code>id</code>.
     * @param id id prema kojem se trazi djelatnik
     * @return objekt djelatnika koji se pronade, null ako ne postoji takav djelatnik
     */
    public Optional<Djelatnik> getDjelatnikById(Long id) {
        try {
            String sql = "SELECT * FROM djelatnici "  +
                         "WHERE id = :id";

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("id", id);

            List<Djelatnik> djelatnici = query.getResultList();
            return (Optional<Djelatnik>) djelatnici.stream().findFirst();
        } catch (NoResultException e) {
            return null;
        }
    }


    /**
     * Sprema djelatnika <code>djelatnik</code> u bazu podataka.
     * @param djelatnik <code>Djelatnik</code> koji se sprema u bazu podataka
     * @return id stvorenog djelatnika u bazi podataka, 0 ako se ne uspije spremiti
     */
    public int saveDjelatnik(Djelatnik djelatnik) {
        try {
            String sql = "INSERT INTO djelatnici (ime, prezime, drzava_rodjenja)" +
                         "VALUES (:ime, :prezime, :drzavaRodjenja)" +
                         "RETURNING id";

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("ime", djelatnik.getIme());
            query.setParameter("prezime", djelatnik.getPrezime());
            query.setParameter("drzavaRodjenja", djelatnik.getDrzavaRodjenja());

            return (int) query.getSingleResult();
        } catch (NoResultException ex) {
            return 0;
        }
    }

    /**
     * Brise objekt djelatnici u bazi podataka koji ima id jednak danom <code>id</code>-u.
     * @param id id djelatnika koji se brise iz baze podataka
     * @return broj uspjesno obrisanih djelatnika
     */
    public int deleteDjelatnik(Long id) {
        try {
            String sql = "DELETE FROM djelatnici WHERE djelatnici.id = :id";

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("id", id);
            return (int) query.executeUpdate();
        } catch (NoResultException ex) {
            return 0;
        }
    }

    /**
     * Azurira podatke o djelatniku <code>djelatnik</code> na temelju id-a.
     * @param djelatnik djelatnik kojem se azuriranju podaci u bazi podataka
     * @return broj djelatnika u bazi podataka kojima su se azurirali podaci
     */
    public int updateDjelatnik(Djelatnik djelatnik) {
        try {
            String sql = "UPDATE djelatnici " +
                         "SET ime = :ime, prezime = :prezime, drzava_rodjenja = :drzavaRodjenja " +
                         "WHERE id = :id";

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("id", djelatnik.getId());
            query.setParameter("ime", djelatnik.getIme());
            query.setParameter("prezime", djelatnik.getPrezime());
            query.setParameter("drzavaRodjenja", djelatnik.getDrzavaRodjenja());

            return (int) query.executeUpdate();
        } catch (NoResultException ex) {
            return 0;
        }
    }
}
