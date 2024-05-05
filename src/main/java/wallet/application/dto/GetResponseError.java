package wallet.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetResponseError extends  GetResponse {
    private final boolean result = false;
    private String error;
}
