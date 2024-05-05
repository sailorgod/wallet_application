package wallet.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT", nullable = false)
    private int id;
    @Setter
    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String walletUUId;
    @Setter
    @Column(columnDefinition = "INT")
    private int balance;
}
