package application.services;

import application.dto.GetResponse;

public interface GetBalanceService {
    GetResponse getBalance(String walletUUID);
}
