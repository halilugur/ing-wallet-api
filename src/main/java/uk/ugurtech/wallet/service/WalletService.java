package uk.ugurtech.wallet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.ugurtech.wallet.exception.WalletException;
import uk.ugurtech.wallet.mapper.WalletMapper;
import uk.ugurtech.wallet.model.dto.WalletDto;
import uk.ugurtech.wallet.model.entity.UserEntity;
import uk.ugurtech.wallet.model.entity.WalletEntity;
import uk.ugurtech.wallet.model.filter.WalletFilter;
import uk.ugurtech.wallet.model.request.WalletCreateRequest;
import uk.ugurtech.wallet.repository.UserRepository;
import uk.ugurtech.wallet.repository.WalletRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public List<WalletDto> getWalletsForUser(String tckn, WalletFilter filter) {
        return walletRepository.findAllByUserAndFilter(tckn, filter).stream()
                .map(WalletMapper::map)
                .toList();
    }

    public WalletDto create(String tckn, WalletCreateRequest request) {
        if (walletRepository.existsByCurrency(tckn, request.getCurrency())) {
            throw new WalletException(request.getCurrency() + " Wallet already exists.");
        }
        WalletEntity wallet = WalletMapper.map(request);
        UserEntity user = userRepository.findByTckn(tckn).orElseThrow();
        wallet.setUser(user);
        WalletEntity walletEntity = walletRepository.save(wallet);
        return WalletMapper.map(walletEntity);
    }
}
