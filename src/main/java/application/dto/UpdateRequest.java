package application.dto;

import lombok.Data;

@Data
public class UpdateRequest {
    private String walletUUID;
    private OperationType operationType;
    private Integer amount;
}
