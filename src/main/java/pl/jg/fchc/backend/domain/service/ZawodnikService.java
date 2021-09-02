package pl.jg.fchc.backend.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jg.fchc.backend.domain.dto.ZawodnikDTO;
import pl.jg.fchc.backend.domain.dto.mapper.ZawodnikMapper;
import pl.jg.fchc.backend.domain.model.entity.Zawodnik;
import pl.jg.fchc.backend.domain.repository.ZawodnikRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ZawodnikService {


    private final ZawodnikRepository zawodnikRepository;
    private final ZawodnikMapper mapper;

    public List<ZawodnikDTO> getAll() {
        return zawodnikRepository
                .findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public void addZawodnik(ZawodnikDTO zawodnikDTO){
        zawodnikRepository.save(ZawodnikMapper.MAPPER.toEntity(zawodnikDTO));
    }

    public Optional<Zawodnik> findZawodnikById(Long id){
        return zawodnikRepository.findById(id);
    }





}
