package com.vallete.portfolio.backendjava.transaction.controller;

import com.vallete.portfolio.backendjava.shared.enumerated.TransactionStatus;
import com.vallete.portfolio.backendjava.shared.enumerated.TransactionType;
import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import com.vallete.portfolio.backendjava.transaction.dto.TransactionDTO;
import com.vallete.portfolio.backendjava.transaction.model.TransactionModel;
import com.vallete.portfolio.backendjava.transaction.service.DeleteTransactionService;
import com.vallete.portfolio.backendjava.transaction.service.FetchTransactionsService;
import com.vallete.portfolio.backendjava.transaction.service.SaveTransactionService;
import com.vallete.portfolio.backendjava.transaction.service.TransactionService;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TransactionController {
    private final FetchTransactionsService fetchTransactionsService;
    private final SaveTransactionService saveTransactionService;
    private final DeleteTransactionService deleteTransactionService;

    @PreAuthorize("token")
    @GetMapping("/ok")
    public ResponseEntity ok() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/transaction")
    public ResponseEntity fetchTransactions(@RequestParam Map<String, String> params) {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .name(params.get("name"))
                .observation(params.get("observation"))
                .build();

        return fetchTransactionsService.fetchTransactions(transactionDTO, params);
    }

    @PostMapping("/transaction")
    public ResponseEntity saveTransaction(@RequestBody TransactionDTO transactionDTO) {
        return saveTransactionService.saveTransaction(transactionDTO);
    }

    @DeleteMapping("/transaction/{idTransaction}")
    public ResponseEntity delete(@PathVariable("idTransaction") UUID idTransaction) {
        return deleteTransactionService.deleteTransaction(idTransaction);
    }
}
