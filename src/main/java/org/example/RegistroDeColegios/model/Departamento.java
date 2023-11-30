package org.example.RegistroDeColegios.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.Entity;


@Entity
@Getter
@Setter

public class Departamento extends ID_ALL {

    private String nombre;

}
