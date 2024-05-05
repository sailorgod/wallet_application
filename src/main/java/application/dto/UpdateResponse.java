package application.dto;

import lombok.Getter;
import lombok.Setter;

public class UpdateResponse {
    @Getter
    private final boolean result = true;
    @Getter
    @Setter
    private int balance;
}
