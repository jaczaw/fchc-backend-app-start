package pl.jg.fchc.backend.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;

@Table(name = "ZAWODNIK")
@Data
@Entity
//@Getter
//@Setter
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
public class Zawodnik {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ_ZAWODNIK_ID")
    @SequenceGenerator(name = "GEN_SEQ_ZAWODNIK_ID", sequenceName = "SEQ_ZAWODNIK_ID", allocationSize = 1)
    private Long id;

    @Column(name = "NAZWA")
    private String nazwaZawodnika;

    @Column(name = "WZROST")
    private Integer wzrost;

    @Column(name = "DATA_URODZENIA")
    private LocalDate dataUrodzenia;

}