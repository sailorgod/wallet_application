package wallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wallet.application.dto.OperationType;
import wallet.application.dto.UpdateResponse;
import wallet.application.model.WalletRepository;

@Service
public class UpdateBalance implements UpdateBalanceService{

    @Autowired
    private final WalletRepository repository;

    public UpdateBalance ( WalletRepository repository ) {
        this.repository = repository;
    }

    @Override
    public UpdateResponse update(String walletUUID, OperationType type, Integer amount) {
        return null;
    }
}
