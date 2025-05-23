package com.edutech.msautenticacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.msautenticacion.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>{

    Token save(Token clase);

    Token findById(int idClase);

    List<Token> findAll();

    void deleteById(int idClase);
}
