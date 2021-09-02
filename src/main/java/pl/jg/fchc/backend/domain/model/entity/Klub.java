package pl.jg.fchc.backend.domain.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Table(name = "KLUB")
@Entity
public class Klub {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ_KLUB_ID")
    @SequenceGenerator(name = "GEN_SEQ_KLUB_ID", sequenceName = "SEQ_KLUB_ID", allocationSize = 1)
    private Long id;

    @Column(name = "NAZWA", nullable = false)
    @ApiModelProperty(value = "Nazwa klubu")
    private String nazwa;

    @OneToMany(mappedBy = "klub", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Zawodnik> zawodnicyList;

    @Transient
    private String temp;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    public void addZawodnik(Zawodnik zawodnik) {
        zawodnicyList.add( zawodnik );
        zawodnik.setKlub(this);
    }

    public void removeZawodnik(Zawodnik zawodnik) {
        zawodnicyList.remove( zawodnik );
        zawodnik.setKlub( null );
    }

}