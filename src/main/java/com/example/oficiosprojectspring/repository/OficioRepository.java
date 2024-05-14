package com.example.oficiosprojectspring.repository;

import com.example.oficiosprojectspring.repository.model.Oficio;
import com.example.oficiosprojectspring.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OficioRepository implements IOficioRepository{
    @Autowired
    @Qualifier("oracleDataSource")
    private DataSource dataSource;

    @Override
    public Oficio getById(int idOficio)  throws SQLException {
        Oficio oficio = null;
        String query = "{ call obtener_oficios(?) }";
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)){
            callableStatement.setInt(1,idOficio);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()){
                oficio = Oficio.builder().idOficio(resultSet.getInt(1))
                        .descripción(resultSet.getString(2))
                        .image(resultSet.getString(3))
                        .imageUrl(resultSet.getString(4))
                        .build();
            }
        }
        return oficio;
    }

    @Override
    public List<Oficio> getAll()  throws SQLException {
        List<Oficio> oficios = new ArrayList<>();
        String query = "{ call obtener_oficios(null) }";
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)){
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()){
                oficios.add(Oficio.builder().idOficio(resultSet.getInt(1))
                        .descripción(resultSet.getString(2))
                        .image(resultSet.getString(3))
                        .imageUrl(resultSet.getString(4))
                        .build()
                );
            }
        }
        return oficios;
    }

    @Override
    public String getOficioImage(int idOficio) throws SQLException {
        String image = null;
        String query = "{ call obtener_imagen_oficio(?,?) }";
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)){
            callableStatement.setInt(2,idOficio);
            callableStatement.registerOutParameter(1,Types.VARCHAR);

            callableStatement.executeQuery();

            image = callableStatement.getString(1);

        }
        return image;
    }

    @Override
    public Oficio deleteById(int idOficio) throws SQLException {
        return null;
    }

    @Override
    public Oficio insertOficio(Oficio oficio) throws SQLException {
        return null;
    }

    @Override
    public Oficio updateOficio(Oficio oficio) throws SQLException {
        return null;
    }
}
