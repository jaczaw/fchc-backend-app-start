package pl.jg.fchc.backend.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jg.fchc.backend.domain.dto.ZawodnikDTO;
import pl.jg.fchc.backend.domain.model.entity.Zawodnik;
import pl.jg.fchc.backend.domain.service.ZawodnikService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/zawodnicy")
@CrossOrigin(origins = "http://localhost:8181") //@CrossOrigin("*") bez ograniczen CROS
public class ZawodnikController {

    private ZawodnikService zawodnikService;

    @Autowired
    public ZawodnikController(ZawodnikService zawodnikService) {
        this.zawodnikService = zawodnikService;
    }

    @GetMapping()
    public ResponseEntity<List<Zawodnik>> getZawodnik(){
        return ResponseEntity.ok(zawodnikService.getAllZawodnik());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zawodnik> getKlubById(@PathVariable Long id){
        Optional<Zawodnik> zawodnik = zawodnikService.findZawodnikById(id);
        return zawodnik.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public void addNewZawodnik(@RequestBody ZawodnikDTO zawodnikDTO) {
        zawodnikService.addZawodnik(zawodnikDTO);
    }
}
