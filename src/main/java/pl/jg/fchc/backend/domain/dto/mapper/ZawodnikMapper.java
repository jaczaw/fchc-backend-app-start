package pl.jg.fchc.backend.domain.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.jg.fchc.backend.domain.dto.ZawodnikDTO;
import pl.jg.fchc.backend.domain.model.entity.Zawodnik;

@Mapper(componentModel = "spring")
public interface ZawodnikMapper {
    ZawodnikMapper MAPPER = Mappers.getMapper( ZawodnikMapper.class );

    Zawodnik toEntity(ZawodnikDTO zawodnikDTO);
    ZawodnikDTO toDTO( Zawodnik zawodnik );
}
