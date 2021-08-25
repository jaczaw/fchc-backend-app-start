package pl.jg.fchc.backend.domain.service;

import org.springframework.stereotype.Service;
import pl.jg.fchc.backend.domain.dto.KlubDTO;
import pl.jg.fchc.backend.domain.dto.mapper.KlubMapper;
import pl.jg.fchc.backend.domain.exception.KlubNotFoundException;
import pl.jg.fchc.backend.domain.model.entity.Klub;
import pl.jg.fchc.backend.domain.repository.KlubRepository;

import java.util.List;

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

    public Klub findKlubById(Long id){
       return klubRepository.findById(id).orElseThrow(()-> new KlubNotFoundException(String.format("Brak Klubu o identyfikatorze  %d",id)));

    }

    public Klub getKlubByNazwa (String nazwa) {
        return klubRepository.findAll().stream()
                .filter(klub -> klub.getNazwa().equals(nazwa))
                .findFirst()
                .orElseThrow(() -> new KlubNotFoundException(String.format("Brak Klubu o nazwie  %s",nazwa)));
    }





}
