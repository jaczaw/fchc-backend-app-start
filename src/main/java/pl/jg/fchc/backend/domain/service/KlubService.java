package pl.jg.fchc.backend.domain.service;

import org.springframework.stereotype.Service;
import pl.jg.fchc.backend.domain.dto.KlubDTO;
import pl.jg.fchc.backend.domain.dto.mapper.KlubMapper;
import pl.jg.fchc.backend.domain.model.entity.Klub;
import pl.jg.fchc.backend.domain.repository.KlubRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KlubService {

    private KlubRepository klubRepository;


    public KlubService(KlubRepository klubRepository/*, Javers javers*/) {
        this.klubRepository = klubRepository;

    }


    public List<Klub> getAllKlub (){
        return klubRepository.findAll();
    }

    public void addKlub(KlubDTO klubDTO){
         klubRepository.save(KlubMapper.MAPPER.toKlub(klubDTO));


    }

    public Optional<Klub> findKlubById(Long id){
        return klubRepository.findById(id);
    }





}
