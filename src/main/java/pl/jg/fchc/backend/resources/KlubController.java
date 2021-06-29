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
import pl.jg.fchc.backend.domain.dto.KlubDTO;
import pl.jg.fchc.backend.domain.model.entity.Klub;
import pl.jg.fchc.backend.domain.service.KlubService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/kluby")
@CrossOrigin("*")
public class KlubController {

    private KlubService klubService;

    @Autowired
    public KlubController(KlubService klubService) {
        this.klubService = klubService;
    }

    @GetMapping()
    public ResponseEntity<List<Klub>> getKluby(){
        return ResponseEntity.ok(klubService.getAllKlub());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Klub> getKlubById(@PathVariable Long id){
        Optional<Klub> klub = klubService.findKlubById(id);
        return klub.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public void addNewKlub(@RequestBody KlubDTO klubDTO) {
        klubService.addKlub(klubDTO);
    }
}
