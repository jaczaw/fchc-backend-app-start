package pl.jg.fchc.backend.domain.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.jg.fchc.backend.domain.model.entity.Zawodnik;

@JaversSpringDataAuditable
public interface ZawodnikRepository extends JpaRepository<Zawodnik, Long> {
}