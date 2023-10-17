package me.bowon.springbootdeveloper.controller.config.jwt;

import me.bowon.springbootdeveloper.config.jwt.JwtProperties;
import me.bowon.springbootdeveloper.config.jwt.TokenProvider;
import me.bowon.springbootdeveloper.repository.UserRepositroy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserRepositroy userRepositroy;
    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("generateToken(): 유저 정보와 만료 기간을 전달해 토큰을 만들 수 있다.")
    @Test
    void generateToken(){
        // given

    }
}
