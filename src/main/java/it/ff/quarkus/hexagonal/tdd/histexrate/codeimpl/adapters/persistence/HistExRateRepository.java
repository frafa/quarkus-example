package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class HistExRateRepository implements PanacheRepositoryBase<HistExRate, HistExRateIdentity> {

//    @Query(value = "SELECT TOP 1 s.* "
//            + "FROM	HistExRate s "
//            + "WHERE "
//            + "currencyCode	= ?1 AND	dateRate <= ?2 "
//            + "ORDER BY	dateRate DESC", nativeQuery = true)
//    public HistExRate getCambioAtDate(String div, LocalDate date);

    public List<HistExRate> findAllByIdentity_idCurrencyIgnoreCase(String currency, Page p) {
        return find("idCurrency = ?1", currency).page(p).list();
    }

    public List<HistExRate> findAllByIdentity_idDate(LocalDate dateRate, Page p) {
        return find("idDate = ?1", dateRate).page(p).list();
    }

    public List<HistExRate> findAll(Page p) {
        return findAll().page(p).list();
    }

    public HistExRate findByIdentity_idCurrencyAndIdentity_idDate(String currency, LocalDate date) {
        return findById(new HistExRateIdentity(currency, date));
    }

}
