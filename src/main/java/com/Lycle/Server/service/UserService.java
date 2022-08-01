package com.Lycle.Server.service;

import com.Lycle.Server.domain.jpa.UserRepository;
import com.Lycle.Server.dto.User.SearchProfileWrapper;
import com.Lycle.Server.dto.User.SearchUserWrapper;
import com.Lycle.Server.dto.User.UserJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long saveUser(UserJoinDto userJoinDto) {
        return userRepository.save(userJoinDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public SearchUserWrapper searchUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id 입니다."));
    }

    @Transactional(readOnly = true)
    public boolean verifyEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean verifyNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public SearchProfileWrapper searchProfile(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));
    }
    
}
