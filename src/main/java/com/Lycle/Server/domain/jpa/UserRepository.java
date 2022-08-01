package com.Lycle.Server.domain.jpa;

import com.Lycle.Server.domain.User;
import com.Lycle.Server.dto.User.SearchProfileWrapper;
import com.Lycle.Server.dto.User.SearchUserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<SearchUserWrapper> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<SearchProfileWrapper>findUserById(Long id);
}
