package com.example.oficiosprojectspring.repository;

import com.example.oficiosprojectspring.repository.model.Oficio;
import com.example.oficiosprojectspring.repository.model.Usuario;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public interface IOficioRepository {
    List<Oficio> getAll() throws SQLException;
    Oficio getById(int idOficio) throws SQLException;
    String getOficioImage(int idOficio) throws SQLException;
    Oficio deleteById(int idOficio) throws SQLException;
    Oficio insertOficio(Oficio oficio) throws SQLException;
    Oficio updateOficio(Oficio oficio) throws SQLException;
}
