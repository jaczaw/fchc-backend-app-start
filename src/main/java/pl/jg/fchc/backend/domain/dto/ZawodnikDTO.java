package pl.jg.fchc.backend.domain.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class ZawodnikDTO {

    private Long id;
    private String nazwaZawodnika;
    private Integer wzrost;
    private Date dataUrodzenia;
}
