package naderdeghaili.u5w2d5wp.payloads;


import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timeStamp) {
}
