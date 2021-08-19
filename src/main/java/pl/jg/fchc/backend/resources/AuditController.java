package pl.jg.fchc.backend.resources;

import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jg.fchc.backend.domain.model.entity.Klub;

import java.util.List;

@RestController
@RequestMapping(value = "/audit")
public class AuditController {

    @Autowired
    private final Javers javers;

    @Autowired
    public AuditController(Javers javers) {
        this.javers = javers;
    }

    @GetMapping("/kluby")
    public String getKlubyChangesAudit() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Klub.class);

        List<Change> changes = javers.findChanges(jqlQuery.build());

        return javers.getJsonConverter().toJson(changes);
    }
}
