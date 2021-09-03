package pl.jg.fchc.backend.domain.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.jg.fchc.backend.domain.dto.ZawodnikDto;
import pl.jg.fchc.backend.domain.model.entity.Zawodnik;

@Mapper(componentModel = "spring")
public interface ZawodnikMapper {
    ZawodnikMapper MAPPER = Mappers.getMapper( ZawodnikMapper.class );

    Zawodnik zawodnikDtoToZawodnik(ZawodnikDto zawodnikDTO);
    ZawodnikDto zawodnikToZawodnikDto(Zawodnik zawodnik );
}
