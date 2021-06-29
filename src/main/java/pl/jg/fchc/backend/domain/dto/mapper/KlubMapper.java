package pl.jg.fchc.backend.domain.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.jg.fchc.backend.domain.dto.KlubDTO;
import pl.jg.fchc.backend.domain.model.entity.Klub;

@Mapper
public interface KlubMapper {
    KlubMapper MAPPER = Mappers.getMapper( KlubMapper.class );

    //@Mapping(source = "nazwa", target ="nazwaKlubu")
    Klub toKlub(KlubDTO klubDTO);
    @InheritInverseConfiguration
    KlubDTO fromKlub( Klub klub );
}
