package com.example.oficiosprojectspring.repository;

import com.example.oficiosprojectspring.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository implements IUsuarioRepository{
    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource dataSource;

    @Override
    public Usuario getById(int idUsuario)  throws SQLException{
        Usuario usuario = null;
        String query = "SELECT * FROM USUARIO WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(query)){
            preparedStatement.setInt(1,idUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                usuario = Usuario.builder().idUsuario(resultSet.getInt(1))
                        .nombre(resultSet.getString(2))
                        .apellidos(resultSet.getString(3))
                        .build();
            }
        }
        return usuario;
    }

    @Override
    public List<Usuario> getAll() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        String query = "{ call obtener_usuarios() }";
        try (Connection connection = dataSource.getConnection();
             CallableStatement cs = connection.prepareCall(query)){
            ResultSet resultSet = cs.executeQuery();

            while (resultSet.next()){
                usuarios.add(Usuario.builder().idUsuario(resultSet.getInt(1))
                        .nombre(resultSet.getString(2))
                        .apellidos(resultSet.getString(3))
                        .idOficio(resultSet.getInt(4)).build());
            }
        }
        return usuarios;
    }

    @Override
    public Usuario deleteById(int idUsuario) throws SQLException{
        Usuario usuario;
        String query = "DELETE FROM USUARIO WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,idUsuario);

            usuario = getById(idUsuario);
            if (usuario!=null)
                preparedStatement.executeUpdate();
        }

        return usuario;
    }

    @Override
    public Usuario insertUsuario(Usuario usuario) throws SQLException{
        if (usuario==null)
            return null;
        int id=0;
        String query = "{ call crear_usuario(?,?,?,?,?) }";
        try(Connection connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){

            callableStatement.setInt(2,usuario.getIdUsuario());
            callableStatement.setString(3,usuario.getNombre());
            callableStatement.setString(4,usuario.getApellidos());
            callableStatement.setInt(5,usuario.getIdOficio());

            callableStatement.executeUpdate();

            id = callableStatement.getInt(1);

        }

        return getById(id); // tambien se puede hacer buscando con el getById
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException{
        if (usuario==null)
            return null;
        int rowsAffected = 0;
        String query = "UPDATE USUARIO SET nombre=?,apellidos=?,Oficio_idOficio=? WHERE idUsuario = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,usuario.getNombre());
            preparedStatement.setString(2,usuario.getApellidos());
            preparedStatement.setInt(3,usuario.getIdOficio());
            preparedStatement.setInt(4,usuario.getIdUsuario());

            rowsAffected = preparedStatement.executeUpdate();
        }

        return rowsAffected>0?usuario:null; // tambien se puede hacer buscando con el getById
    }
}
