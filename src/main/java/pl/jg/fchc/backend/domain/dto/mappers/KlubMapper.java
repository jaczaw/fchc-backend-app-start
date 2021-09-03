package pl.jg.fchc.backend.domain.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.jg.fchc.backend.domain.dto.KlubDto;
import pl.jg.fchc.backend.domain.dto.KlubViewDto;
import pl.jg.fchc.backend.domain.model.entity.Klub;

@Mapper(componentModel = "spring")
public interface KlubMapper {
    KlubMapper MAPPER = Mappers.getMapper( KlubMapper.class );

    Klub klubDtoToKlub(KlubDto klubDTO);
    KlubDto klubToKlubDto(Klub klub );
    KlubViewDto klubToKlubViewDto(Klub klub);
}
