package application.services;

import application.dto.OperationType;
import application.dto.UpdateResponse;

public interface UpdateBalanceService {
    UpdateResponse update(String walletUUID, OperationType type, Integer amount);
}
