package pl.jg.fchc.backend.domain.service;

import org.springframework.stereotype.Service;
import pl.jg.fchc.backend.domain.dto.ZawodnikDTO;
import pl.jg.fchc.backend.domain.dto.mapper.ZawodnikMapper;
import pl.jg.fchc.backend.domain.model.entity.Zawodnik;
import pl.jg.fchc.backend.domain.repository.ZawodnikRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ZawodnikService {


    private ZawodnikRepository zawodnikRepository;


    public ZawodnikService(ZawodnikRepository zawodnikRepository) {
        this.zawodnikRepository = zawodnikRepository;
    }



    public List<Zawodnik> getAllZawodnik (){
        return zawodnikRepository.findAll();
    }

    public void addZawodnik(ZawodnikDTO zawodnikDTO){
        zawodnikRepository.save(ZawodnikMapper.MAPPER.toZawodnik(zawodnikDTO));


    }

    public Optional<Zawodnik> findZawodnikById(Long id){
        return zawodnikRepository.findById(id);
    }





}
