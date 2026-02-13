package naderdeghaili.u5w2d5wp.repositories;

import naderdeghaili.u5w2d5wp.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DipendentiRepository extends JpaRepository<Dipendente, UUID> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
