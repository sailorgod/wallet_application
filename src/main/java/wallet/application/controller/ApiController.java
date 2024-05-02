package wallet.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wallet.application.dto.GetResponse;
import wallet.application.dto.OperationType;
import wallet.application.dto.UpdateResponse;
import wallet.application.services.GetBalanceService;
import wallet.application.services.UpdateBalanceService;

@Controller(value = "/api")
public class ApiController {

    private final UpdateBalanceService updateBalanceService;
    private final GetBalanceService getBalanceService;

    public ApiController(UpdateBalanceService updateBalanceService, GetBalanceService getBalanceService) {
        this.updateBalanceService = updateBalanceService;
        this.getBalanceService = getBalanceService;
    }

    @PostMapping("/v1/wallet")
    public ResponseEntity<UpdateResponse> updateWalletBalance(@RequestParam String walletUUID,
                                                              @RequestParam OperationType operationType,
                                                              @RequestParam Integer amount) {
        return ResponseEntity.ok(updateBalanceService.update(walletUUID, operationType, amount));
    }

    @GetMapping("/v1/wallets/{wallet_ID}")
    public ResponseEntity<GetResponse> getWalletBalance(@PathVariable String walletUUID) {
        return ResponseEntity.ok(getBalanceService.getBalance(walletUUID));
    }
 }
