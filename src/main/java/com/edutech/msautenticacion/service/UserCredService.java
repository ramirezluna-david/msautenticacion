package com.edutech.msautenticacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.msautenticacion.model.Cred;
import com.edutech.msautenticacion.repository.UserCredRepository;

@Service
public class UserCredService {
    @Autowired
    private UserCredRepository credencialesRepository;

    public Cred save(Cred usuario) {
        return credencialesRepository.save(usuario);
    }

    public List<Cred> findAll() {
        return credencialesRepository.findAll();
    }

    public Cred findById(int idUser) {
        return credencialesRepository.findById(idUser);
    }

    public void deleteById(int idUser) {
        credencialesRepository.deleteById(idUser);
    }

    public Cred userxId(int idUser) {
        return credencialesRepository.getReferenceById(idUser);
    }

    public Boolean cambiarContrasena(int idUser, String password) {
        Cred user = credencialesRepository.findById(idUser);
        if(user != null) {
            user.setPassword(password);
            credencialesRepository.save(user);
            return true;
        }

        return false;
    }
}
