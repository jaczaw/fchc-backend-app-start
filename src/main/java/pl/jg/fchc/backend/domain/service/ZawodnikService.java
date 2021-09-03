package pl.jg.fchc.backend.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jg.fchc.backend.domain.dto.ZawodnikDto;
import pl.jg.fchc.backend.domain.dto.mappers.ZawodnikMapper;
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

    public List<ZawodnikDto> getAll() {
        return zawodnikRepository
                .findAll()
                .stream()
                .map(mapper::zawodnikToZawodnikDto)
                .collect(Collectors.toList());
    }

    public void addZawodnik(ZawodnikDto zawodnikDTO){
        zawodnikRepository.save(ZawodnikMapper.MAPPER.zawodnikDtoToZawodnik(zawodnikDTO));
    }

    public Optional<Zawodnik> findZawodnikById(Long id){
        return zawodnikRepository.findById(id);
    }





}
