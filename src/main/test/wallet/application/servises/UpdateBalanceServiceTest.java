package wallet.application.servises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import application.dto.OperationType;
import application.dto.UpdateError;
import application.dto.UpdateResponse;
import application.model.Wallet;
import application.model.WalletRepository;
import application.services.UpdateBalance;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName ( "Проведение операций над счётом" )
public class UpdateBalanceServiceTest {

    private final WalletRepository repository = Mockito.mock ( WalletRepository.class );
    private final UpdateBalance updateBalance = new UpdateBalance ( repository );
    private final String correctUUID = "5ba71a62-f9ad-4125-84ac-aefb32e623bc";

    @Test
    @DisplayName ( "Обновление данных при передаче всех пустых аргументов" )
    public void updateWhenAllArgumentsNull() {
        UpdateResponse response = updateBalance.update ( null, null, null );
        UpdateError error = new UpdateError ( "" );
        assertEquals(error.getClass (), response.getClass ());
    }

    @Test
    @DisplayName ( "Значение суммы отрицательно" )
    public void updateWhenAmountIsNegative() {
        UpdateResponse response = updateBalance.update ( correctUUID, OperationType.DEPOSIT, -46413125  );
        UpdateError error = new UpdateError ( "" );
        assertEquals ( error.getClass (), response.getClass () );
    }

    @Test
    @DisplayName ( "Счёт с данным UUID не найден" )
    public void updateWhenWalletNotFound() {
        mockQuery ();
        String UUID = "ed25ss5wd165ds16ds1";
        UpdateResponse response = updateBalance.update ( UUID, OperationType.DEPOSIT, 1000 );
        verify ( repository, times ( 1 ) ).findByWalletUUId ( UUID );
        UpdateError error = new UpdateError ( "" );
        assertEquals ( error.getClass (), response.getClass () );
    }

    @Test
    @DisplayName ( "Обновление счета при депозите" )
    public void updateWhenDepositOperation() {
        mockQuery ();
        UpdateResponse response = updateBalance.update ( correctUUID, OperationType.DEPOSIT, 1000 );
        verify ( repository, times ( 1 ) ).findByWalletUUId ( correctUUID );
        assertEquals ( 2000, response.getBalance () );
    }

    @Test
    @DisplayName ( "Обновление счёта при снятии средств" )
    public void updateWhenWithdrawOperation() {
        mockQuery ();
        UpdateResponse response = updateBalance.update ( correctUUID, OperationType.WITHDRAW, 1000 );
        verify ( repository, times ( 1 ) ).findByWalletUUId ( correctUUID );
        assertEquals ( 0, response.getBalance () );
    }

    @Test
    @DisplayName ( "Попытка снятия со счёта при вводе суммы большей, чем на балансе" )
    public void withdrawWhenNegativeWalletAmount(){
        mockQuery ();
        UpdateResponse response = updateBalance.update ( correctUUID, OperationType.WITHDRAW, 2000 );
        verify ( repository, times ( 1 ) ).findByWalletUUId ( correctUUID );
        UpdateError error = new UpdateError ( "" );
        assertEquals ( error.getClass (), response.getClass () );
    }

    private void mockQuery() {
        Wallet wallet = new Wallet ();
        wallet.setWalletUUId ( correctUUID );
        wallet.setBalance ( 1000 );
        when(repository.findByWalletUUId ( correctUUID )).thenReturn ( Optional.of ( wallet ) );
    }

}
