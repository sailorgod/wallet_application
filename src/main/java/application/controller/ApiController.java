package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import application.dto.*;
import application.services.GetBalanceService;
import application.services.UpdateBalanceService;

@RestController
public class ApiController {

    private final UpdateBalanceService updateBalanceService;
    private final GetBalanceService getBalanceService;

    @Autowired
    public ApiController(UpdateBalanceService updateBalanceService, GetBalanceService getBalanceService) {
        this.updateBalanceService = updateBalanceService;
        this.getBalanceService = getBalanceService;
    }

    @PostMapping(value = "/wallet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateResponse> updateWalletBalance( @RequestBody UpdateRequest request ) {
        UpdateResponse response = updateBalanceService.
                update(request.getWalletUUID (), request.getOperationType (), request.getAmount ());
        if(response instanceof UpdateError) {
            return ResponseEntity.status ( HttpStatus.BAD_REQUEST ).body ( response );
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/wallets/{walletUUID}")
    public ResponseEntity<GetResponse> getWalletBalance(@PathVariable String walletUUID) {
        GetResponse response = getBalanceService.getBalance(walletUUID);
        if(response instanceof GetResponseError) {
            return ResponseEntity.status ( HttpStatus.BAD_REQUEST ).body ( response );
        }
        return ResponseEntity.ok(response);
    }
 }
