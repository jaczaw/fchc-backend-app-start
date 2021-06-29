package pl.jg.fchc.backend.domain.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.jg.fchc.backend.domain.dto.ZawodnikDTO;
import pl.jg.fchc.backend.domain.model.entity.Zawodnik;

@Mapper
public interface ZawodnikMapper {
    ZawodnikMapper MAPPER = Mappers.getMapper( ZawodnikMapper.class );

    //@Mapping(source = "nazwa", target ="nazwaKlubu")
    Zawodnik toZawodnik(ZawodnikDTO zawodnikDTO);
    @InheritInverseConfiguration
    ZawodnikDTO fromZawodnik( Zawodnik zawodnik );
}
