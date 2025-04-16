package com.hr.hrsystem.audit;

import com.hr.hrsystem.entity.User;
import com.hr.hrsystem.repo.user.UserRepo;
import com.hr.hrsystem.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class ApplicationAuditAware {

    private final JwtUtil jwtUtil;

    private final HttpServletRequest httpServletRequest;

    private final UserRepo repo;

    @Bean
    public AuditorAware<String> auditorProvider() {

        return () -> {

            try {

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();

                if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {

                    String token = httpServletRequest.getHeader("Authorization");

                    if (token != null && token.startsWith("Bearer")) {
                        String username = jwtUtil.extractUsername(token.replace("Bearer", " "));
                        User user = repo.findByUserName(username);
                        if (user != null) {
                            return Optional.of(username);
                        }
                    }

                }

            } catch (Exception e) {
                return Optional.empty();
            }


            return Optional.empty();


        };

    }


}
