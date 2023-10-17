package me.bowon.springbootdeveloper.service;


import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.User;
import me.bowon.springbootdeveloper.dto.AddUserRequest;
import me.bowon.springbootdeveloper.repository.UserRepositroy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepositroy userRepositroy;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        return userRepositroy.save(User.builder()
                .email(dto.getEmail())
                // 패스워드를 저장할 때 시큐리티를 설정하며 패스워드 인코딩용으로 등록한 빈을 사용해서 암호화 한 후에 저장
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}
