package com.example.oficiosprojectspring.repository.model;

import lombok.*;

import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Oficio {
    private int idOficio;
    private String descripción;
    private String image;
    private String imageUrl;

    @Override
    public boolean equals(Object object){
        if (object==null || !(object instanceof Oficio))
            return false;
        Oficio oficio = (Oficio)object;
        return getIdOficio()== oficio.getIdOficio();
    }
    @Override
    public int hashCode(){
        return idOficio;
    }
}
