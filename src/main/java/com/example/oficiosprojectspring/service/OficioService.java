package com.example.oficiosprojectspring.service;

import com.example.oficiosprojectspring.repository.OficioRepository;
import com.example.oficiosprojectspring.repository.model.Oficio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OficioService {
    @Autowired
    private OficioRepository oficioRepository;

    public List<Oficio> getAll() throws SQLException {
        return oficioRepository.getAll();
    }
    public Oficio getById(int idOficio) throws SQLException {
        return oficioRepository.getById(idOficio);
    }
    public String getOficioImage(int idOficio) throws SQLException {
        return oficioRepository.getOficioImage(idOficio);
    }
}
