package wallet.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UpdateResponse {
    @Getter
    private final boolean result = true;
    @Getter
    @Setter
    private int balance;
}
