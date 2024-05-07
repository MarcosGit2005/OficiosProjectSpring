package com.example.oficiosprojectspring.service;

import com.example.oficiosprojectspring.repository.UsuarioRepository;
import com.example.oficiosprojectspring.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public List<Usuario> getAll() throws SQLException {
        return usuarioRepository.getAll();
    }
    public Usuario getById(int idUsuario) throws SQLException{
        return usuarioRepository.getById(idUsuario);
    }
    public Usuario deleteById(int idUsuario) throws SQLException{
        return usuarioRepository.deleteById(idUsuario);
    }
    public Usuario updateUsuario(Usuario usuario) throws SQLException{
        return usuarioRepository.updateUsuario(usuario);
    }
    public Usuario insertUsuario(Usuario usuario) throws SQLException{
        return usuarioRepository.insertUsuario(usuario);
    }
}
