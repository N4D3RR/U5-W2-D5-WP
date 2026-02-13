package naderdeghaili.u5w2d5wp.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import naderdeghaili.u5w2d5wp.entities.Dipendente;
import naderdeghaili.u5w2d5wp.exceptions.ValidationException;
import naderdeghaili.u5w2d5wp.payloads.NewDipendenteDTO;
import naderdeghaili.u5w2d5wp.services.DipendentiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {

    private final DipendentiService dipendentiService;

    public DipendentiController(DipendentiService dipendentiService) {
        this.dipendentiService = dipendentiService;
    }

    //GET DIPENDENTI
    @GetMapping
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") @Min(0) int page,
                                    @RequestParam(defaultValue = "15") @Min(0) @Max(15) int size) {
        return this.dipendentiService.findAll(page, size);
    }

    //POST DIPENDENTE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated NewDipendenteDTO payload, BindingResult validateResult) {
        if (validateResult.hasErrors()) {
            List<String> errorList = validateResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException((errorList));
        } else {
            return null;
        }
    }


    //GET DIPENDENTE
    @GetMapping("/{dipendenteId}")
    public Dipendente getDipendenteById(@PathVariable UUID dipendenteId) {
        return this.dipendentiService.findById(dipendenteId);
    }

    //PUT DIPENDNETE
    @PutMapping("/{dipendenteId}")
    public Dipendente updateDipendente(@PathVariable UUID dipendenteId, @RequestBody @Validated NewDipendenteDTO payload, BindingResult validateResult) {
        if (validateResult.hasErrors()) {
            List<String> errorList = validateResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException((errorList));
        } else {
            return null;
        }
    }

    //DELETE DIPENDENTE
    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable UUID dipendenteId) {
        ;
    }


}
