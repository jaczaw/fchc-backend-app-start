package pl.jg.fchc.backend.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jg.fchc.backend.domain.dto.KlubDTO;
import pl.jg.fchc.backend.domain.dto.KlubViewDTO;
import pl.jg.fchc.backend.domain.dto.mapper.KlubMapper;
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
    public List<KlubViewDTO> getAll() {
        return klubRepository
                .findAll()
                .stream()
                .map(mapper::toViewDTO)
                .collect(Collectors.toList());
    }

    public KlubDTO insert(KlubDTO dto) {
        Klub klub = mapper.toEntity(dto);
        log.info(String.format("Serwis: Insert Klub: %s",klub.toString()));
        return mapper.toDTO(klubRepository.save(klub));
    }

    @Transactional(readOnly = true)
    public Optional<KlubDTO> findById(Long id) {
        return klubRepository
                .findById(id)
                .map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<KlubDTO> getKlubByNazwa(String nazwa) {
        return klubRepository.findAll().stream()
                .filter(klub -> klub.getNazwa().equals(nazwa))
                .map(mapper::toDTO)
                .findFirst();
    }

    public KlubDTO update(Long id, KlubDTO newDto) {
        return klubRepository.findById(id)
                .map(klub -> {
                    klub.setNazwa(newDto.getNazwa());
                    return mapper.toDTO(klubRepository.save(klub));
                })
                .orElseThrow(() -> new KlubNotFoundException(String.format("UPDATE - Brak Klubu o takim identyfikatorze %d", id)));
    }

    public KlubDTO delete (Long id){
        KlubDTO klubDTO = klubRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new KlubNotFoundException(String.format("DELETE - Brak Klubu o takim identyfikatorze %d", id)));
        klubRepository.deleteById(id);
        return klubDTO;
    }


}
