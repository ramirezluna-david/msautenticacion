package com.edutech.msautenticacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.msautenticacion.model.Token;
import com.edutech.msautenticacion.repository.TokenRepository;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public Token findById(int idToken) {
        return tokenRepository.findById(idToken);
    }

    public List<Token> findAll() {
        return tokenRepository.findAll();
    }

    public void deleteById(int idToken) {
        tokenRepository.deleteById(idToken);
    }

    public Boolean cambiarEstado(int idToken) {
        Token buscarToken = tokenRepository.findById(idToken);
        if(buscarToken != null) {
            buscarToken.setIsRevoked((!buscarToken.getIsRevoked()));
            tokenRepository.save(buscarToken);
            return true;
        }

        return false;
    }
}
