package com.Lycle.Server.service;

import com.Lycle.Server.domain.jpa.UserRepository;
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
   public Long searchUser(UserLoginDto userLoginDto){
      return userRepository.findByEmailAndPassword(userLoginDto.getEmail(),userLoginDto.getPassword())
              .orElseThrow(()->new IllegalArgumentException("존재 하지 않는 Id 입니다.")).getId();
  }



}
