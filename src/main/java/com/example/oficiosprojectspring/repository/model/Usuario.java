package com.example.oficiosprojectspring.repository.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private int idOficio;

    @Override
    public boolean equals(Object object){
        if (object==null || !(object instanceof Usuario))
            return false;
        Usuario usr = (Usuario)object;
        return getIdUsuario()== usr.getIdUsuario();
    }
    @Override
    public int hashCode(){
        return idUsuario;
    }
}
