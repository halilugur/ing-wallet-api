package uk.ugurtech.wallet.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.ugurtech.wallet.model.response.ApiResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiResponseMapper {
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .data(data)
                .success(true)
                .build();
    }

    public static <T extends Exception> ApiResponse<T> error(T exception) {
        return ApiResponse.<T>builder()
                .error(exception.getMessage())
                .success(false)
                .build();
    }
}
