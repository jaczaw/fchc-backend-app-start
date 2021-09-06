package pl.jg.fchc.backend.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jg.fchc.backend.domain.dto.KlubDto;
import pl.jg.fchc.backend.domain.dto.KlubViewDto;
import pl.jg.fchc.backend.domain.dto.mappers.KlubMapper;
import pl.jg.fchc.backend.domain.exception.KlubNotFoundException;
import pl.jg.fchc.backend.domain.model.entity.Klub;
import pl.jg.fchc.backend.domain.repository.KlubRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class KlubService {

    private final KlubRepository klubRepository;
    private final KlubMapper mapper;

    @Transactional(readOnly = true)
    public List<KlubViewDto> getAll() {
        return klubRepository
                .findAll()
                .stream()
                .map(mapper::klubToKlubViewDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public KlubDto insert(KlubDto dto) {
        Klub klub = mapper.klubDtoToKlub(dto);
        log.info(String.format("Serwis: Insert Klub: %s",klub.toString()));
        return mapper.klubToKlubDto(klubRepository.save(klub));
    }

    @Transactional(readOnly = true)
    public Optional<KlubDto> findById(Long id) {
        return klubRepository
                .findById(id)
                .map(mapper::klubToKlubDto);
    }

    @Transactional(readOnly = true)
    public Optional<KlubDto> getKlubByNazwa(String nazwa) {
        return klubRepository.findAll().stream()
                .filter(klub -> klub.getNazwa().equals(nazwa))
                .map(mapper::klubToKlubDto)
                .findFirst();
    }

    @Transactional
    public KlubDto update(Long id, KlubDto newDto) {
        return klubRepository.findById(id)
                .map(klub -> {
                    klub.setNazwa(newDto.getNazwa());
                    return mapper.klubToKlubDto(klubRepository.save(klub));
                })
                .orElseThrow(() -> new KlubNotFoundException(String.format("UPDATE - Brak Klubu o takim identyfikatorze %d", id)));
    }

    @Transactional
    public KlubDto delete (Long id){
        KlubDto klubDTO = klubRepository.findById(id)
                .map(mapper::klubToKlubDto)
                .orElseThrow(() -> new KlubNotFoundException(String.format("DELETE - Brak Klubu o takim identyfikatorze %d", id)));
        klubRepository.deleteById(id);
        return klubDTO;
    }


}
