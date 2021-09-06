package pl.jg.fchc.backend.controllers.slowniki;


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
import pl.jg.fchc.backend.domain.dto.KlubDto;
import pl.jg.fchc.backend.domain.dto.KlubViewDto;
import pl.jg.fchc.backend.domain.exception.KlubNotFoundException;
import pl.jg.fchc.backend.domain.service.KlubService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/slowniki/kluby")
@CrossOrigin("*")
public class KlubController {

    private final KlubService klubService;

    @GetMapping()
    public List<KlubViewDto> getKluby(){
        return klubService.getAll();
    }

    @GetMapping("/{id}")
    public KlubDto getKlubById(@PathVariable Long id){
        Optional<KlubDto> klubDto = klubService.findById(id);
        return klubDto.orElseThrow(()-> new KlubNotFoundException(String.format("Brak Klubu o identyfikatorze  %d",id)));
    }

    @GetMapping("/nazwa/{nazwa}")
    public KlubDto getKlubByNazwa(@PathVariable String nazwa){
        Optional<KlubDto> klubDto = klubService.getKlubByNazwa(nazwa);
        return klubDto.orElseThrow(() -> new KlubNotFoundException(String.format("Brak Klubu o nazwie  %s",nazwa)));
    }

    @PostMapping
    public ResponseEntity<KlubDto> insert(@RequestBody KlubDto dto) {
        log.info(String.format("Kontroler: Insert Klub: %s",dto.toString()));
        return new ResponseEntity<>(klubService.insert(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public KlubDto updateNazwaKlubu(@PathVariable(value = "id") Long klubId,
                                    @Valid @RequestBody KlubDto dto) {
        return klubService.update(klubId,dto);

    }

    @DeleteMapping("/{id}")
    public KlubDto deleteKlub(@PathVariable(value = "id") Long klubId) {
           return klubService.delete(klubId);
    }
}
