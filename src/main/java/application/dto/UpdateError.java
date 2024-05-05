package application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateError extends UpdateResponse {
    private final boolean result = false;
    private String message;

}
