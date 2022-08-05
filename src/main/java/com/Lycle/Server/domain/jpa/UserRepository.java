package com.Lycle.Server.domain.jpa;

import com.Lycle.Server.domain.User;
import com.Lycle.Server.dto.User.SearchProfileWrapper;
import com.Lycle.Server.dto.User.SearchUserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<SearchUserWrapper> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<SearchProfileWrapper>findUserById(Long id);

    @Query(value = "select u.id from User u where u.nickname =: nickname", nativeQuery = true)
    Long findUserByNickname(String nickname);

    @Override
    Optional<User> findById(Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE User u SET u.sharedId =: sharedId WHERE u.id = : id", nativeQuery = true)
    Long updateUserSharedId(Long id, Long sharedId);

}
