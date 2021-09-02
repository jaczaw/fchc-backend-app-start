package pl.jg.fchc.backend.domain.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.jg.fchc.backend.domain.dto.KlubDTO;
import pl.jg.fchc.backend.domain.dto.KlubViewDTO;
import pl.jg.fchc.backend.domain.model.entity.Klub;

@Mapper(componentModel = "spring")
public interface KlubMapper {
    KlubMapper MAPPER = Mappers.getMapper( KlubMapper.class );

    Klub toEntity(KlubDTO klubDTO);
    KlubDTO toDTO( Klub klub );
    KlubViewDTO toViewDTO(Klub klub);
}
