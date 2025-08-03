package uk.ugurtech.wallet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.ugurtech.wallet.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDetailsService getUserDetailsService() {
        return tckn -> userRepository.findByTckn(tckn)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
