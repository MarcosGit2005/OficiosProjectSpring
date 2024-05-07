package com.example.oficiosprojectspring.repository;

import com.example.oficiosprojectspring.repository.model.Oficio;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public interface IOficioRepository {
    List<Oficio> getAll() throws SQLException;
    Oficio getById(int idOficio) throws SQLException;
    Blob getOficioImage(int idOficio) throws SQLException;
}
