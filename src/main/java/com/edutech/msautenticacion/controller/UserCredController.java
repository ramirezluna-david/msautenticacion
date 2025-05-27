package com.edutech.msautenticacion.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.msautenticacion.model.Cred;
import com.edutech.msautenticacion.service.UserCredService;

@RestController
@RequestMapping("/api/v1/credenciales")
public class UserCredController {

    @Autowired
    private UserCredService credencialessService;

    @GetMapping
    public ResponseEntity<List<Cred>> listarUsuarios() {
        List<Cred> usuarios = credencialessService.findAll();
        if(usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cred> createCredenciales(@RequestBody Cred user) {
        Cred buscarUser = credencialessService.findById(user.getIdUsuario());
        if(buscarUser == null) {
            Cred nuevoUser = credencialessService.save(user);
            return new ResponseEntity<>(nuevoUser, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Cred> readCredenciales(@PathVariable int idUsuario) {
        Cred buscarUser = credencialessService.findById(idUsuario);
        if(buscarUser != null) {
            return new ResponseEntity<>(buscarUser, HttpStatus.OK);
        } else {
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Cred> updateCredenciales(@PathVariable int idUsuario, @RequestBody Cred user) {
        if(credencialessService.update(idUsuario, user)) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> deleteCredenciales(@PathVariable int idUsuario) {
        Cred buscarUser = credencialessService.findById(idUsuario);
        if(buscarUser != null) {
            credencialessService.deleteById(idUsuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{idUsuario}/modificar/password")
    public ResponseEntity<?> cambiarContrasena(@PathVariable int idUsuario, @RequestBody Map<String, String> body) {
        String nuevoPassword = body.get("password");
        if(credencialessService.cambiarContrasena(idUsuario, nuevoPassword)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
