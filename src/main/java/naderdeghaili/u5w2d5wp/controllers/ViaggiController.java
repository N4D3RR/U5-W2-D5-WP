package naderdeghaili.u5w2d5wp.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import naderdeghaili.u5w2d5wp.entities.Viaggio;
import naderdeghaili.u5w2d5wp.exceptions.ValidationException;
import naderdeghaili.u5w2d5wp.payloads.ModifyViaggioDTO;
import naderdeghaili.u5w2d5wp.payloads.NewViaggioDTO;
import naderdeghaili.u5w2d5wp.services.ViaggiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {

    private final ViaggiService viaggiService;

    public ViaggiController(ViaggiService viaggiService) {
        this.viaggiService = viaggiService;
    }

    //GET VIAGGI
    @GetMapping
    public Page<Viaggio> findAll(@RequestParam(defaultValue = "0") @Min(0) int page,
                                 @RequestParam(defaultValue = "15") @Min(0) @Max(15) int size) {
        return null;
    }

    //POST VIAGGIO
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio saveViaggio(@RequestBody @Validated NewViaggioDTO payload, BindingResult validateResult) {
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


    //GET VIAGGIO
    @GetMapping("/{viaggioId}")
    public Viaggio getViaggioById(@PathVariable UUID viaggioId) {
        return this.viaggiService.findById(viaggioId);
    }

    //PUT VIAGGIO
    @PutMapping("/{viaggioId}")
    public Viaggio updateViaggio(@PathVariable UUID viaggioId, @RequestBody @Validated ModifyViaggioDTO payload, BindingResult validateResult) {
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

    //DELETE VIAGGIO
    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable UUID viaggioId) {

    }
}
