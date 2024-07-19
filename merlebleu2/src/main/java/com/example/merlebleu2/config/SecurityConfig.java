package com.example.merlebleu2.config;
import com.example.merlebleu2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MemberService memberService;


    @Bean //로그인, 로그아웃시 url설정
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(form -> form
                        .loginPage("/members/login") //로그인 페이지주소
                        .defaultSuccessUrl("/merlebleu/main"    ,true) //로그인 성공시 주소
                        .usernameParameter("email") //로그인시 사용할 파라미터
                        .failureUrl("/members/login/error")
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                        .logoutSuccessUrl("/merlebleu/main")
                );

        http.authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/", "/shop/**" ,"/image/**" ,"/images/**","/members/**", "/item/**", "/img/**", "/css/**", "/js/**", "/MerlBleu/**" , "/merlebleu/**", "/main/**").permitAll()  // 모든 사용자가 인증 없이 접근 가능한 페이지
                        .requestMatchers("/cart/**" ,"/order/**" ).authenticated() //인증된 사용자만
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // /admin으로 시작하는 경로에 ADMIN Role만 접근 가능하게 설정
                        .anyRequest().authenticated()  // 나머지 요청은 인증 필요
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())  // 인증되지 않은 사용자가 리소스 접근 시 사용되는 핸들러 등록
                );

        http.csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**") // H2 콘솔을 사용하는 경우 CSRF 무시
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
