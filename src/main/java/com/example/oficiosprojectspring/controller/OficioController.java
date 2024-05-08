package com.example.oficiosprojectspring.controller;

import com.example.oficiosprojectspring.service.OficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OficioController {
    @Autowired
    private OficioService oficioService;

    @GetMapping("/oficios/")
    public ResponseEntity<?> getAll(){
        try {
            return new ResponseEntity<>(oficioService.getAll(), HttpStatus.OK);
        } catch (SQLException sqlE){
            return response(sqlE);
        }
    }
    @GetMapping("/oficios/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int idOficio){
        try {
            return new ResponseEntity<>(oficioService.getById(idOficio),HttpStatus.OK);
        } catch (SQLException sqlE){
            return response(sqlE);
        }
    }
    @GetMapping("/oficios/{id}/image")
    public ResponseEntity<?> getOficioImage(@PathVariable("id") int idOficio){
        try {
            return new ResponseEntity<>(oficioService.getOficioImage(idOficio),HttpStatus.OK);
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
