package com.vallete.portfolio.backendjava.transaction.controller;

import com.vallete.portfolio.backendjava.shared.enumerated.TransactionStatus;
import com.vallete.portfolio.backendjava.shared.enumerated.TransactionType;
import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import com.vallete.portfolio.backendjava.transaction.dto.TransactionDTO;
import com.vallete.portfolio.backendjava.transaction.model.TransactionModel;
import com.vallete.portfolio.backendjava.transaction.service.TransactionService;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;

    @PreAuthorize("token")
    @GetMapping("/ok")
    public ResponseEntity ok() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public ResponseEntity fetch(@RequestParam Map<String, String> params) {
        TransactionModel transactionFilter = new TransactionModel();

        UserModel userModel = userService.getUserById(UUID.fromString(params.get("user")));
        transactionFilter.setUser(userModel);

        if (userModel == null)
            throw new BusinessException("The required user doesn't exist.");

        transactionFilter.setName(params.get("name"));

        List<TransactionModel> transactions = transactionService.fetch(transactionFilter);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    @PutMapping
    public ResponseEntity save(@RequestBody TransactionDTO transactionDTO) {

        UserModel userModel = userService.getUserById(transactionDTO.getUser());

        TransactionModel transactionModel = TransactionModel
                .builder()
                .user(userModel)
                .name(transactionDTO.getName())
                .creationDate(transactionDTO.getCreationDate())
                .dueDate(transactionDTO.getDueDate())
                .type(TransactionType.valueOf(transactionDTO.getType()))
                .status(TransactionStatus.valueOf(transactionDTO.getStatus()))
                .value(transactionDTO.getValue())
                .build();

        return ResponseEntity.ok(transactionService.save(transactionModel));
    }

    @DeleteMapping("/{idTransaction}")
    public ResponseEntity delete(@PathVariable("idTransaction") UUID idTransaction){
        transactionService.deleteById(idTransaction);
        return ResponseEntity.ok("The required transaction has been deleted.");
    }
}
