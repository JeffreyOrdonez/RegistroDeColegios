package org.example.RegistroDeColegios.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;



@Embeddable
@Getter
@Setter
public class Directores {

    private String nombre;
    private String telefono;
    private String correo;

}
