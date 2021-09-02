package pl.jg.fchc.backend.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
import pl.jg.fchc.backend.domain.dto.KlubViewDTO;
import pl.jg.fchc.backend.domain.exception.KlubNotFoundException;
import pl.jg.fchc.backend.domain.service.KlubService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kluby")
@CrossOrigin("*")
public class KlubController {

    private final KlubService klubService;

    @GetMapping()
    public List<KlubViewDTO> getKluby(){
        return klubService.getAll();
    }

    @GetMapping("/{id}")
    public KlubDTO getKlubById(@PathVariable Long id){
        Optional<KlubDTO> klubDto = klubService.findById(id);
        return klubDto.orElseThrow(()-> new KlubNotFoundException(String.format("Brak Klubu o identyfikatorze  %d",id)));
    }

    @GetMapping("/nazwa/{nazwa}")
    public KlubDTO getKlubByNazwa(@PathVariable String nazwa){
        Optional<KlubDTO> klubDto = klubService.getKlubByNazwa(nazwa);
        return klubDto.orElseThrow(() -> new KlubNotFoundException(String.format("Brak Klubu o nazwie  %s",nazwa)));
    }

    @PostMapping
    public ResponseEntity<KlubDTO> insert(@RequestBody KlubDTO dto) {
        log.info(String.format("Kontroler: Insert Klub: %s",dto.toString()));
        return new ResponseEntity<>(klubService.insert(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public KlubDTO updateNazwaKlubu(@PathVariable(value = "id") Long klubId,
                                 @Valid @RequestBody KlubDTO dto) {
        return klubService.update(klubId,dto);

    }

    @DeleteMapping("/{id}")
    public KlubDTO deleteKlub(@PathVariable(value = "id") Long klubId) {
           return klubService.delete(klubId);
    }
}
