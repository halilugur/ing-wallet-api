package uk.ugurtech.wallet.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import uk.ugurtech.wallet.util.AuthenticationUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/my/wallets")
public class WalletCustomerRest {
    private final WalletService walletService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<WalletDto>>> getWallets(WalletFilter filter) {
        ApiResponse<List<WalletDto>> response = ApiResponseMapper.success(walletService.getWalletsForUser(AuthenticationUtil.getCurrentUser().getTckn(), filter));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WalletDto>> create(@RequestBody WalletCreateRequest request) {
        ApiResponse<WalletDto> response = ApiResponseMapper.success(walletService.create(AuthenticationUtil.getCurrentUser().getTckn(), request));
        return ResponseEntity.ok(response);
    }
}
