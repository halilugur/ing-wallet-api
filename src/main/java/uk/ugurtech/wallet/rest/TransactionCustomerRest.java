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
import uk.ugurtech.wallet.model.request.TransactionCreateRequest;
import uk.ugurtech.wallet.model.response.ApiResponse;
import uk.ugurtech.wallet.service.TransactionService;
import uk.ugurtech.wallet.util.AuthenticationUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/my/transactions")
public class TransactionCustomerRest {
    private final TransactionService transactionService;

    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<ApiResponse<List<TransactionDto>>> wallet(@PathVariable Long walletId) {
        List<TransactionDto> transactions = transactionService.getTransactions(AuthenticationUtil.getCurrentUser().getTckn(), walletId);
        return ResponseEntity.ok(ApiResponseMapper.success(transactions));
    }

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<TransactionDto>> deposit(@RequestBody TransactionCreateRequest request) {
        TransactionDto transactionDto = transactionService.deposit(AuthenticationUtil.getCurrentUser().getTckn(), request);
        return ResponseEntity.ok(ApiResponseMapper.success(transactionDto));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<TransactionDto>> withdraw(@RequestBody TransactionCreateRequest request) {
        TransactionDto transactionDto = transactionService.withdraw(AuthenticationUtil.getCurrentUser().getTckn(), request);
        return ResponseEntity.ok(ApiResponseMapper.success(transactionDto));
    }
}
