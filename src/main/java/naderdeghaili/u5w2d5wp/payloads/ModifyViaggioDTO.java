package naderdeghaili.u5w2d5wp.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ModifyViaggioDTO(
        @NotBlank(message = "la destinazione è obbligatoria") @Size(min = 2, max = 20, message = "la destinazione deve essere compresa tra 2 e 20 caratteri") String destinazione,
        @NotNull(message = "il viaggio deve avere una data") LocalDate dataViaggio,
        boolean statoViaggio) {

}
