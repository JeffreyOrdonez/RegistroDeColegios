package org.example.RegistroDeColegios.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Getter
@Setter
public class Departamento extends ID_ALL {

    private String nombre;

}
