package application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.dto.GetResponse;
import application.dto.GetResponseError;
import application.model.Wallet;
import application.model.WalletRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetBalance implements GetBalanceService {

    @Autowired
    private final WalletRepository walletRepository;

    @Override
    public GetResponse getBalance(String walletUUID) {

        if(walletUUID == null) {
            return new GetResponseError ("Задан пустой параметр");
        }

        Optional<Wallet> optionalWallet = walletRepository.findByWalletUUId ( walletUUID );
        if(optionalWallet.isPresent ( )) {
            Wallet wallet = optionalWallet.get ( );
            GetResponse getResponse = new GetResponse ();
            getResponse.setBalance ( wallet.getBalance () );
            return getResponse;
        }

        return new GetResponseError ( "Счет с таким UUID не найден" );
    }
}
