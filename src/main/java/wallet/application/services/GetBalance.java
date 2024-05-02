package wallet.application.services;

import org.springframework.stereotype.Service;
import wallet.application.dto.GetResponse;

@Service
public class GetBalance implements GetBalanceService{
    @Override
    public GetResponse getBalance(String walletUUID) {
        return null;
    }
}
