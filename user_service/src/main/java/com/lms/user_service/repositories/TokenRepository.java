package com.lms.user_service.repositories;

import com.lms.user_service.entities.Token;
import com.lms.user_service.entities.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);
    Token findByTokenAndType(String token, TokenType type);
}
