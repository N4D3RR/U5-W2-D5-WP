package naderdeghaili.u5w2d5wp.services;

import lombok.extern.slf4j.Slf4j;
import naderdeghaili.u5w2d5wp.entities.Dipendente;
import naderdeghaili.u5w2d5wp.exceptions.NotFoundException;
import naderdeghaili.u5w2d5wp.payloads.NewDipendenteDTO;
import naderdeghaili.u5w2d5wp.repositories.DipendentiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class DipendentiService {

    private final DipendentiRepository dipendentiRepository;


    public DipendentiService(DipendentiRepository dipendentiRepository) {
        this.dipendentiRepository = dipendentiRepository;
    }

    //GET LISTA DIPENDENTI
    public Page<Dipendente> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return this.dipendentiRepository.findAll(pageable);
    }

    //POST DIPENDENTI
    public Dipendente saveDipendente(NewDipendenteDTO payload) {
        if (dipendentiRepository.existsByEmail(payload.email())) {
            throw new IllegalArgumentException("l'email è già in uso");
        }
        if (dipendentiRepository.existsByUsername(payload.username())) {
            throw new IllegalArgumentException("l'username è già in uso");
        }

        Dipendente newDipendente = new Dipendente(payload.username(), payload.nome(), payload.cognome(), payload.email());

        return dipendentiRepository.save(newDipendente);
    }

    //GET DIPENDENTE
    public Dipendente findById(UUID dipendenteId) {
        return dipendentiRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException(dipendenteId));
    }

    //PUT DIPENDENTE
    public Dipendente findByIdAndUpdate(UUID dipendenteId, NewDipendenteDTO payload) {
        Dipendente found = this.findById(dipendenteId);

        if (!found.getEmail().equals(payload.email())
                && dipendentiRepository.existsByEmail(payload.email())) {
            throw new IllegalArgumentException("l'email è già in uso");
        }

        if (!found.getUsername().equals(payload.username())
                && dipendentiRepository.existsByUsername(payload.username())) {
            throw new IllegalArgumentException("l'username è già in uso");
        }

        found.setUsername(payload.username());
        found.setNome(payload.nome());
        found.setCognome(payload.cognome());
        found.setEmail(payload.email());

        Dipendente modifiedDipendente = this.dipendentiRepository.save(found);

        log.info("il dipendente con id " + dipendenteId + " è stato modificato");

        return modifiedDipendente;
    }

    //DELETE DIPENDENTE
    public void findByIdAndDelete(UUID dipendenteId) {
        Dipendente found = this.findById(dipendenteId);
        this.dipendentiRepository.delete(found);
        log.info("il dipendente è stato licenziato con successo");
    }

}
