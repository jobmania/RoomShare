package son.roomshare.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import son.roomshare.config.security.jwt.JwtFilter;
import son.roomshare.config.security.jwt.TokenProvider;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    // h2 database 테스트가 원활하도록 관련 API 들은 전부 무시
    // local 관련 파일 접속들 허용
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/h2-console/**", "/favicon.ico", "/js/**", "/image/**", "/css/**", "/scss/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /** security filter 설정 변경
     * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        JwtFilter customFilter = new JwtFilter(tokenProvider);

        // CSRF 설정 Disable, JWT는 CSRF 공격 걱정 ㄴㄴ
        http.csrf().disable()

                // h2-console 을 위한 설정을 추가
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션 관련 설정 : 사용하지 않기때문에 stateless
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 모든 url 인증 및 허용 url 설정
                .and()
                .authorizeRequests()
                .antMatchers("/member/auth/**").permitAll() // 로그인 // 회원가입
                .antMatchers("/").permitAll() // 홈화면

                .antMatchers("/**").permitAll() // 테스트중에는 허용


                .anyRequest().authenticated()


                // 내가 수정해본 사항 jwtSecurityConfig 사용하지 않고 적용해보기
                .and() // 지정된 필터 앞에 커스텀 필터를 추가 (UsernamePasswordAuthenticationFilter 보다 먼저 실행된다)
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}
