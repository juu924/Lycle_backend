package com.Lycle.Server.service;

import com.Lycle.Server.domain.User;
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

    @Transactional
    public Long addFriends(Long id, String nickname) {
        Long sharedId = userRepository.findUserByNickname(nickname);
        if (sharedId != 0L) {
            //현재 user 테이블 update
            userRepository.updateUserSharedId(id, sharedId);
            //공유하고자 하는 user 테이블 업데이트
            userRepository.updateUserSharedId(sharedId, id);

            return 0L;
        }
        return -1L;
    }

}
