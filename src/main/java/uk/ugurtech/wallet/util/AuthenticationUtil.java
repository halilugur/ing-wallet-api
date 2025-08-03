package uk.ugurtech.wallet.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uk.ugurtech.wallet.mapper.UserMapper;
import uk.ugurtech.wallet.model.dto.UserDto;
import uk.ugurtech.wallet.model.entity.UserEntity;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticationUtil {
    public static UserDto getCurrentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(UserEntity.class::cast)
                .map(UserMapper::map)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
