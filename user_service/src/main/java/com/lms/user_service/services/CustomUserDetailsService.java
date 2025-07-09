package com.lms.user_service.services;

import com.lms.user_service.config.UserDetailsImpl;
import com.lms.user_service.entities.User;
import com.lms.user_service.exceptions.APIExceptionFactory;
import com.lms.user_service.exceptions.ExceptionTypeEnum;
import com.lms.user_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final APIExceptionFactory exceptionFactory;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw exceptionFactory.throwException(ExceptionTypeEnum.USER_NOT_FOUND,"Email",email);
        }
        return new UserDetailsImpl(user);
    }
}
