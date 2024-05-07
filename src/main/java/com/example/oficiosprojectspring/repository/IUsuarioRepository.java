package com.example.oficiosprojectspring.repository;


import com.example.oficiosprojectspring.repository.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioRepository {
    Usuario getById(int idUsuario) throws SQLException;
    List<Usuario> getAll() throws SQLException;
    Usuario deleteById(int idUsuario) throws SQLException;
    Usuario insertUsuario(Usuario usuario) throws SQLException;
    Usuario updateUsuario(Usuario usuario) throws SQLException;
}
