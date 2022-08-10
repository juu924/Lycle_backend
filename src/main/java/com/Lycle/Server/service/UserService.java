package com.Lycle.Server.service;

import com.Lycle.Server.config.auth.UserPrincipal;
import com.Lycle.Server.config.auth.token.JwtTokenProvider;
import com.Lycle.Server.domain.User.Role;
import com.Lycle.Server.domain.User.User;
import com.Lycle.Server.domain.jpa.UserRepository;
import com.Lycle.Server.dto.User.SearchProfileWrapper;
import com.Lycle.Server.dto.User.UserJoinDto;
import com.Lycle.Server.dto.User.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Long saveUser(UserJoinDto userJoinDto) {
        //최초 가입시 일반 사용자로 설정
        return userRepository.save(User.builder()
                        .email(userJoinDto.getEmail())
                        .password(passwordEncoder.encode(userJoinDto.getPassword()))
                        .nickname(userJoinDto.getNickname())
                        .role(Role.USER)
                        .build()
                ).getId();
    }

    @Transactional
    public String loginUser(UserLoginDto userLoginDto){
        User user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(
                ()-> new IllegalArgumentException("가입되지 않은 이메일 입니다."));

        UserPrincipal userPrincipal = new UserPrincipal(user);
        if (!passwordEncoder.matches(user.getPassword(), userLoginDto.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return jwtTokenProvider.createToken(userPrincipal.getEmail(),userPrincipal.getRoles());
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
