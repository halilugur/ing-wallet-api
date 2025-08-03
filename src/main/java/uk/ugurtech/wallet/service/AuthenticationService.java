package uk.ugurtech.wallet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uk.ugurtech.wallet.mapper.UserMapper;
import uk.ugurtech.wallet.model.entity.UserEntity;
import uk.ugurtech.wallet.model.request.SignInRequest;
import uk.ugurtech.wallet.model.request.SignUpRequest;
import uk.ugurtech.wallet.model.response.JwtResponse;
import uk.ugurtech.wallet.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtResponse signUp(SignUpRequest request) {
        UserEntity user = UserMapper.map(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtResponse.builder().token(jwt).build();
    }

    public JwtResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getTckn(), request.getPassword()));
        UserEntity user = userRepository.findByTckn(request.getTckn())
                .orElseThrow(() -> new IllegalArgumentException("Invalid tckn or password"));
        String jwt = jwtService.generateToken(user);
        return JwtResponse.builder().token(jwt).build();
    }
}
