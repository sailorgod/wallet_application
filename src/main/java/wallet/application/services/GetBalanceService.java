package wallet.application.services;

import wallet.application.dto.GetResponse;

public interface GetBalanceService {
    GetResponse getBalance(String walletUUID);
}
