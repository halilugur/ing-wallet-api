package uk.ugurtech.wallet.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uk.ugurtech.wallet.mapper.ApiResponseMapper;
import uk.ugurtech.wallet.model.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WalletException.class)
    public ResponseEntity<ApiResponse<?>> wallet(WalletException ex) {
        return ResponseEntity.badRequest().body(ApiResponseMapper.error(ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> global(Exception ex) {
        return ResponseEntity.internalServerError().body(ApiResponseMapper.error(ex));
    }
}
