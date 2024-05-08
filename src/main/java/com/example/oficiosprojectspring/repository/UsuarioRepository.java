package com.example.oficiosprojectspring.repository;

import com.example.oficiosprojectspring.repository.model.Usuario;
import com.mysql.cj.MysqlType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

@Repository
public class UsuarioRepository implements IUsuarioRepository{
    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource dataSource;

    @Override
    public Usuario getById(int idUsuario)  throws SQLException{
        Usuario usuario = null;
        String query = "SELECT * FROM Usuario WHERE idUsuario = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareCall(query)){
            preparedStatement.setInt(1,idUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                usuario = Usuario.builder().idUsuario(resultSet.getInt(1))
                        .nombre(resultSet.getString(2))
                        .apellidos(resultSet.getString(3))
                        .idOficio(resultSet.getInt(4))
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
        Usuario usuario = getById(idUsuario);
        int rowsAffected;
        if (usuario==null)
            return null;

        String query = "{ ? = call eliminar_usuario(?) }";
        try(Connection connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){
            callableStatement.setInt(2,idUsuario);
            callableStatement.registerOutParameter(1, Types.INTEGER);

            callableStatement.execute(); // Tengo que poner execute para que me deje recoger el return

            rowsAffected = callableStatement.getInt(1); // Recojo el return

        }

        return rowsAffected>0?usuario:null;
    }

    @Override
    public Usuario insertUsuario(Usuario usuario) throws SQLException{
        if (usuario==null)
            return null;
        int id;
        String query = "{ call crear_usuario(?,?,?,?,?) }";
        try(Connection connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){

            callableStatement.setInt(2,usuario.getIdUsuario());
            callableStatement.setString(3,usuario.getNombre());
            callableStatement.setString(4,usuario.getApellidos());
            callableStatement.setInt(5,usuario.getIdOficio());
            callableStatement.registerOutParameter(1,Types.INTEGER);

            callableStatement.execute();

            id = callableStatement.getInt(1);

        }

        return getById(id);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException{
        if (usuario==null)
            return null;
        int rowsAffected;
        String query = "{ ? = call actualizar_usuario(?,?,?,?) }";
        try(Connection connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){
            callableStatement.setInt(2,usuario.getIdUsuario());
            callableStatement.setString(3,usuario.getNombre());
            callableStatement.setString(4,usuario.getApellidos());
            callableStatement.setInt(5,usuario.getIdOficio());
            callableStatement.registerOutParameter(1,Types.INTEGER);

            callableStatement.execute();

            rowsAffected = callableStatement.getInt(1);

        }

        return rowsAffected>0?usuario:null;
    }
}
