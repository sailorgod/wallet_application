package wallet.application.servises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import application.dto.GetResponse;
import application.dto.GetResponseError;
import application.model.Wallet;
import application.model.WalletRepository;
import application.services.GetBalance;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetBalanceServiceTest {

    private final WalletRepository walletRepository = Mockito.mock ( WalletRepository.class );
    private final GetBalance getBalance = new GetBalance (walletRepository);
    private final String correctUUID = "5ba71a62-f9ad-4125-84ac-aefb32e623bc";

    @Test
    @DisplayName ( "Передан пустой параметр UUID" )
    public void getBalanceWhenNullWalletUUID() {
        GetResponse response = getBalance.getBalance ( null );
        GetResponseError error = new GetResponseError ( "" );
        assertEquals (error.getClass (), response.getClass ());
    }

    @Test
    @DisplayName ( "Счёт с таким UUID отсутвует в базе" )
    public void getBalanceWhenUUIDNotFound() {
        mockQuery ();
        GetResponseError error = new GetResponseError ( "" );
        String UUID = "91236tey23ep98e107103eu0e1jud1y9";
        GetResponse response = getBalance.getBalance ( UUID );
        verify ( walletRepository , times ( 1 ) ).findByWalletUUId ( UUID );
        assertEquals ( error.getClass (), response.getClass ());
    }

    @Test
    @DisplayName ( "Передан корректный UUID, показан баланс" )
    public void getBalanceWithCorrectUUID() {
        mockQuery ();
        GetResponse response = getBalance.getBalance ( correctUUID );
        verify ( walletRepository , times ( 1 )).findByWalletUUId ( correctUUID );
        int balance = 1000;
        assertEquals ( balance, response.getBalance () );
    }

    private void mockQuery() {
        Wallet wallet = new Wallet ();
        wallet.setBalance ( 1000 );
        wallet.setWalletUUId ( correctUUID );
        when ( walletRepository.findByWalletUUId ( correctUUID ) ).thenReturn ( Optional.of ( wallet ) );
    }
 }
