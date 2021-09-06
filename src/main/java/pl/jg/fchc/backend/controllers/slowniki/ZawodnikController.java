package pl.jg.fchc.backend.controllers.slowniki;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jg.fchc.backend.domain.dto.ZawodnikDto;
import pl.jg.fchc.backend.domain.model.entity.Zawodnik;
import pl.jg.fchc.backend.domain.service.ZawodnikService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/slowniki/zawodnicy")
@CrossOrigin("*")
public class ZawodnikController {

    private final ZawodnikService zawodnikService;

    @GetMapping()
    public List<ZawodnikDto> getZawodnicy(){
        return zawodnikService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zawodnik> getZawodnikById(@PathVariable Long id){
        Optional<Zawodnik> zawodnik = zawodnikService.findZawodnikById(id);
        return zawodnik.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public void addNewZawodnik(@RequestBody ZawodnikDto zawodnikDTO) {
        zawodnikService.addZawodnik(zawodnikDTO);
    }
}
