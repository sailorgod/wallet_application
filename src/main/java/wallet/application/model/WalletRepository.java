package wallet.application.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Integer>{

    Optional<Wallet> findByWalletUUId ( String WalletUUId );
}
