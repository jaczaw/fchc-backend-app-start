package pl.jg.fchc.backend.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Table(name = "ZAWODNIK")
@Entity
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

    //@JoinColumn(name = "klub_id", referencedColumnName = "id")
    @ManyToOne
    private Klub klub;

}