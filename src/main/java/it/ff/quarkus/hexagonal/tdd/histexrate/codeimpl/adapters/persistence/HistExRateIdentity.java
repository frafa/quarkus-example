package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class HistExRateIdentity implements Serializable {
    private static final long serialVersionUID = -6871032108628387042L;

    @Column
    private String idCurrency;

    @Column
    private LocalDate idDate;
}