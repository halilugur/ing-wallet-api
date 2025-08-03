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
import uk.ugurtech.wallet.model.dto.WalletDto;
import uk.ugurtech.wallet.model.filter.WalletFilter;
import uk.ugurtech.wallet.model.request.WalletCreateRequest;
import uk.ugurtech.wallet.model.response.ApiResponse;
import uk.ugurtech.wallet.service.WalletService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wallets")
public class WalletRest {
    private final WalletService walletService;

    @GetMapping("/{tckn}")
    public ResponseEntity<ApiResponse<List<WalletDto>>> getWallets(@PathVariable String tckn, WalletFilter filter) {
        ApiResponse<List<WalletDto>> response = ApiResponseMapper.success(walletService.getWalletsForUser(tckn, filter));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{tckn}")
    public ResponseEntity<ApiResponse<WalletDto>> create(@PathVariable String tckn,
                                                         @RequestBody WalletCreateRequest request) {
        ApiResponse<WalletDto> response = ApiResponseMapper.success(walletService.create(tckn, request));
        return ResponseEntity.ok(response);
    }
}
