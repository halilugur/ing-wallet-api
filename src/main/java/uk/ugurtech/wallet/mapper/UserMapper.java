package uk.ugurtech.wallet.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.ugurtech.wallet.model.dto.UserDto;
import uk.ugurtech.wallet.model.entity.UserEntity;
import uk.ugurtech.wallet.model.request.SignUpRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {
    public static UserEntity map(SignUpRequest request) {
        UserEntity user = new UserEntity();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setTckn(request.getTckn());
        return user;
    }

    public static UserDto map(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .isActive(user.getIsActive())
                .isDeleted(user.getIsDeleted())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .tckn(user.getTckn())
                .role(user.getRole())
                .build();
    }
}
