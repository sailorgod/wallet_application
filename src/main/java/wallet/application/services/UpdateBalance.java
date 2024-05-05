package wallet.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wallet.application.dto.OperationType;
import wallet.application.dto.UpdateError;
import wallet.application.dto.UpdateResponse;
import wallet.application.model.Wallet;
import wallet.application.model.WalletRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateBalance implements UpdateBalanceService{

    @Autowired
    private final WalletRepository repository;

    @Override
    public UpdateResponse update(String walletUUID, OperationType type, Integer amount) {

        if(walletUUID == null || type == null || amount == null ) {
            return new UpdateError ( "В запросе отсутствует 1 или несколько параметров" );
        }

        if( amount < 0 ) return new UpdateError ( "Отрицательное число не допустимо" );

        Optional<Wallet> optionalWallet = repository.findByWalletUUId ( walletUUID );
        if(!optionalWallet.isPresent ()){
            return new UpdateError ("Счет с таким UUID не найден");
        }
        UpdateResponse updateResponse = new UpdateResponse ();
        Wallet wallet = optionalWallet.get ( );
        int currentBalance = wallet.getBalance ( );

        if(type.equals ( OperationType.DEPOSIT )) {
            currentBalance += amount;
            wallet.setBalance ( currentBalance );
            repository.save ( wallet );
            updateResponse.setBalance ( currentBalance );
            return updateResponse;
        }

        if(currentBalance - amount < 0) {
            return new UpdateError ( "Недостаточно средств" );
        }

        currentBalance -= amount;
        wallet.setBalance ( currentBalance );
        repository.save ( wallet );
        updateResponse.setBalance ( currentBalance );
        return updateResponse;
    }
}
