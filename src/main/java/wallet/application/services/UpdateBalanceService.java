package wallet.application.services;

import wallet.application.dto.OperationType;
import wallet.application.dto.UpdateResponse;

public interface UpdateBalanceService {
    UpdateResponse update(String walletUUID, OperationType type, Integer amount);
}
