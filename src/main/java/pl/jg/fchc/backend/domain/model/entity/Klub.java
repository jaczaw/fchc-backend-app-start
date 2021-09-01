package pl.jg.fchc.backend.domain.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "KLUB")
@Entity
@RequiredArgsConstructor
@Setter
@Getter
public class Klub {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ_KLUB_ID")
    @SequenceGenerator(name = "GEN_SEQ_KLUB_ID", sequenceName = "SEQ_KLUB_ID", allocationSize = 1)
    private Long id;

    @Column(name = "NAZWA", nullable = false)
    @ApiModelProperty(value = "Nazwa klubu")
    private String nazwa;

    @Transient
    private String temp;

}