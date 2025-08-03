package uk.ugurtech.wallet.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ugurtech.wallet.mapper.ApiResponseMapper;
import uk.ugurtech.wallet.model.dto.TransactionDto;
import uk.ugurtech.wallet.model.request.StatusDecisionRequest;
import uk.ugurtech.wallet.model.request.TransactionCreateRequest;
import uk.ugurtech.wallet.model.response.ApiResponse;
import uk.ugurtech.wallet.service.TransactionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionRest {
    private final TransactionService transactionService;

    @GetMapping("/{tckn}/wallet/{walletId}")
    public ResponseEntity<ApiResponse<List<TransactionDto>>> getTransactions(@PathVariable String tckn,
                                                                             @PathVariable Long walletId) {
        List<TransactionDto> transactions = transactionService.getTransactions(tckn, walletId);
        return ResponseEntity.ok(ApiResponseMapper.success(transactions));
    }

    @PostMapping("/{tckn}/deposit")
    public ResponseEntity<ApiResponse<TransactionDto>> deposit(@PathVariable String tckn,
                                                               @RequestBody TransactionCreateRequest request) {
        TransactionDto transactionDto = transactionService.deposit(tckn, request);
        return ResponseEntity.ok(ApiResponseMapper.success(transactionDto));
    }

    @PostMapping("/{tckn}/withdraw")
    public ResponseEntity<ApiResponse<TransactionDto>> withdraw(@PathVariable String tckn,
                                                                @RequestBody TransactionCreateRequest request) {
        TransactionDto transactionDto = transactionService.withdraw(tckn, request);
        return ResponseEntity.ok(ApiResponseMapper.success(transactionDto));
    }

    @PostMapping("/{transactionId}")
    public ResponseEntity<ApiResponse<TransactionDto>> approve(@PathVariable Long transactionId,
                                                               @RequestBody StatusDecisionRequest request) {
        TransactionDto transactionDto = transactionService.statusDecision(transactionId, request.getStatus());
        return ResponseEntity.ok(ApiResponseMapper.success(transactionDto));
    }
}
