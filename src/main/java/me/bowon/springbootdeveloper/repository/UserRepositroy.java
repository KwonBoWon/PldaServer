package me.bowon.springbootdeveloper.repository;

import me.bowon.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositroy extends JpaRepository<User, Long> {
    // 요청하는 쿼리 FROM users WHERE email = #{email}
    Optional<User> findByEmail (String email); // email로 사용자 정보를 가져옴
}
