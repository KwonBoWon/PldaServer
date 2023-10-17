package me.bowon.springbootdeveloper.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;

   /**
    * 스프링 시큐리티 모든 기능 비활성화
    * 인증, 인가 서비스를 모든 곳에 적용하지는 않음(정적 리소스(image, HTML)에는 사용X
    * /static경로 아래와 H2console에 ignore
    */
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    /**
     *     특정 HTTP 요청에 대한 웹 기반 보안 구성
     *     인증/인가및 로그인 로그아웃 관련 설정
     */
    //TODO: http오류 왜생기는지 모르겠는데 고쳐야함
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests() // 특정 경로에 대한 액세스 설정
                .requestMatchers("/login", "/signup","/user") // 특정 요청과 일치하는 url에 대한 액세스를 설정
                .permitAll() // 누구나 접근 가능, /login, /signup, /user로 요청이 오면 인증/인가 없이 접근 가능
                .anyRequest() // 위에서 설정한 url 이외의 요청에 대해서 설정
                .authenticated()  // 별도의 인가는 필요하지 않지만 인증이 성공된 상태여야 접근할 수 있다.
                .and()
                .formLogin() // 폼 기반 로그인 설정
                .loginPage("/login") // 로그인 페이지 경로를 설정
                .defaultSuccessUrl("/articles") // 로그인이 완료되었을 때 이동할 경로를 설정
                .and()
                .logout() // 로그아웃 설정
                .logoutSuccessUrl("/login") // 로그아웃이 완료되었을 때, 이동할 경로 설정
                .invalidateHttpSession(true) // 로그아웃 이후에 세션을 전체 삭제할 지 여부 설정
                .and()
                .csrf().disable() // csrf 비활성화
                .build();
    }
    // 인증 관리자 관련 설정
    // 사용자 정보를 가져올 서비스를 재정의하거나, 인증 방법, 예를 들어 LDAP, JDBC기반 인증 등을 설정할 때 사용
    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDetailsService userDetailsService)
        throws  Exception{
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)// 사용자 정보 서비스 설정
                .userDetailsService(userDetailsService) // 사용자 정보를 가져올 서비스를 설정, 이때 설정하는 서비스 클래스는 반드시 UserDetailsService를 상속받은 클래스여야 한다.
                .passwordEncoder(bCryptPasswordEncoder) // 비밀번호를 암호화하기 위한 인코더를 설정
                .and()
                .build();
    }
    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}




















