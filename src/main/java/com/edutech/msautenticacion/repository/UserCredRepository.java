package com.edutech.msautenticacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.msautenticacion.model.Cred;

@Repository
public interface UserCredRepository extends JpaRepository<Cred, Integer>{

    Cred save(Cred credenciales);

    List<Cred> findAll();

    Cred findById(int idUser);

    void deleteById(int idUser);

    Cred getReferenceById(int idUser);
}
