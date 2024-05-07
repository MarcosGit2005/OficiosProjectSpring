package com.example.oficiosprojectspring.controller;

import com.example.oficiosprojectspring.repository.model.Usuario;
import com.example.oficiosprojectspring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios/")
    public ResponseEntity<?> getAll(){
        try {
            return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
        } catch (SQLException sqlE){
            return response(sqlE);
        }
    }
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int idUsuario){
        try {
            return new ResponseEntity<>(usuarioService.getById(idUsuario),HttpStatus.OK);
        } catch (SQLException sqlE){
            return response(sqlE);
        }
    }
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int idUsuario){


        try {
            return new ResponseEntity<>(usuarioService.deleteById(idUsuario),HttpStatus.OK);
        } catch (SQLException sqlE){
            return response(sqlE);
        }
    }
    @PutMapping("/usuarios/")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.updateUsuario(usuario),HttpStatus.OK);
        } catch (SQLException sqlE){
            return response(sqlE);
        }
    }
    @PostMapping("/usuarios/")
    public ResponseEntity<?> insertUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.insertUsuario(usuario),HttpStatus.OK);
        } catch (SQLException sqlE){
            return response(sqlE);
        }
    }
    private ResponseEntity<?> response(SQLException sqlE){
        Map<String,Object> response = new HashMap<>();
        response.put("code",sqlE.getErrorCode());
        response.put("message",sqlE.getMessage());
        response.put("sqlstate",sqlE.getSQLState());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
