package com.edutech.msautenticacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.msautenticacion.model.Cred;
import com.edutech.msautenticacion.model.Token;
import com.edutech.msautenticacion.service.TokenService;
import com.edutech.msautenticacion.service.UserCredService;

@RestController
@RequestMapping("/api/v1/tokens")
public class TokenController {

    @Autowired
    private UserCredService credencialesService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<Token>> listarTokens() {
        List<Token> tokens = tokenService.findAll();
        if(tokens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Token> createToken(@RequestBody Token token) {
        int idLink = token.getUser().getIdUsuario();
        Cred user = credencialesService.userxId(idLink);
        if(user != null) {
            token.setUser(user);
        }

        Token buscarToken = tokenService.findById(token.getIdToken());
        if(buscarToken == null) {
            Token nuevoToken = tokenService.save(token);
            return new ResponseEntity<>(nuevoToken, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{idToken}")
    public ResponseEntity<Token> readToken(@PathVariable int idToken) {
        Token token = tokenService.findById(idToken);
        if(token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idToken}")
    public ResponseEntity<Token> updateToken(@PathVariable int idToken, @RequestBody Token token) {
        Token tok = tokenService.findById(idToken);
        if(tok != null) {
            tok.setIdToken(idToken);
            tok.setEmisionToken(token.getEmisionToken());
            tok.setRevocacionToken(token.getRevocacionToken());
            tok.setIsRevoked(token.getIsRevoked());

            tokenService.save(tok);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idToken}")
    public ResponseEntity<?> deleteToken(@PathVariable int idToken) {
        Token buscarToken = tokenService.findById(idToken);
        if(buscarToken != null) {
            tokenService.deleteById(idToken);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
