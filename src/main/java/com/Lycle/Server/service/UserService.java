package com.Lycle.Server.service;

import com.Lycle.Server.domain.jpa.UserRepository;
import com.Lycle.Server.dto.User.SearchUserWrapper;
import com.Lycle.Server.dto.User.UserJoinDto;
import com.Lycle.Server.dto.User.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  @Transactional
  public Long saveUser(UserJoinDto userJoinDto){
     return userRepository.save(userJoinDto.toEntity()).getId();
  }

  @Transactional(readOnly = true)
   public SearchUserWrapper searchUser(UserLoginDto userLoginDto){
      return userRepository.findByEmailAndPassword(userLoginDto.getEmail(),userLoginDto.getPassword())
              .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 id 입니다."));
  }

  @Transactional(readOnly = true)
    public boolean verifyEmail(String email){
      return userRepository.existsByEmail(email);
  }

  @Transactional(readOnly = true)
    public boolean verifyNickname(String nickname){
      return userRepository.existsByNickname(nickname);
  }



}