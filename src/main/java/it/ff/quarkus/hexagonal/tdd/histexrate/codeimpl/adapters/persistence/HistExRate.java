package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "HistExRate")
public class HistExRate implements Serializable {
    private static final long serialVersionUID = -5089769005345422535L;

    @EmbeddedId
    private HistExRateIdentity identity;

    @Column
    private BigDecimal rate;
}

