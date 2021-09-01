package pl.jg.fchc.backend.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jg.fchc.backend.domain.dto.KlubDTO;
import pl.jg.fchc.backend.domain.dto.mapper.KlubMapper;
import pl.jg.fchc.backend.domain.exception.KlubNotFoundException;
import pl.jg.fchc.backend.domain.model.entity.Klub;
import pl.jg.fchc.backend.domain.repository.KlubRepository;
import pl.jg.fchc.backend.domain.service.KlubService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/kluby")
@CrossOrigin("*")
public class KlubController {

    private final KlubService klubService;
    @Autowired
    private KlubRepository klubRepository;

    @Autowired
    public KlubController(KlubService klubService) {
        this.klubService = klubService;
    }

    @GetMapping()
    public ResponseEntity<List<Klub>> getKluby(){
        return ResponseEntity.ok(klubService.getAllKlub());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KlubDTO> getKlubById(@PathVariable Long id){

        Klub klub = klubService.findKlubById(id);
        KlubDTO klubDTO = KlubMapper.MAPPER.fromKlub(klub);
        return ResponseEntity.ok(klubDTO);
    }

    @GetMapping("/nazwa/{nazwa}")
    public ResponseEntity<KlubDTO> getKlubByNazwa(@PathVariable String nazwa){
        Klub klub = klubService.getKlubByNazwa(nazwa);
        return ResponseEntity.ok(KlubMapper.MAPPER.fromKlub(klub));
    }

    @PostMapping
    public void addNewKlub(@RequestBody KlubDTO klubDTO) {
        klubService.addKlub(klubDTO);
    }

    @PutMapping("/{id}")
    public Klub updateNazwaKlubu(@PathVariable(value = "id") Long klubId,
                                 @Valid @RequestBody KlubDTO klubNazwaUpdate) {

        Klub klub = klubRepository.findById(klubId)
                .orElseThrow(() -> new KlubNotFoundException(String.format("Update - Brak Klubu o takim identyfikatorze %d",  klubId)));

        klub.setNazwa(klubNazwaUpdate.getNazwa());
        return klubRepository.save(klub);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Klub> deleteKlub(@PathVariable(value = "id") Long klubId) {
        Klub klub = klubRepository.findById(klubId)
                .orElseThrow(() -> new KlubNotFoundException(String.format("Delete - Brak Klubu o takim identyfikatorze %d",  klubId)));

        klubRepository.delete(klub);

        return ResponseEntity.ok().build();
    }
}
