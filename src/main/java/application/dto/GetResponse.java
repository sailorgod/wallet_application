package application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetResponse {
    private int balance;
    private final boolean result = true;
}
