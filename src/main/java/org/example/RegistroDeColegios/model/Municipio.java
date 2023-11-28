package org.example.RegistroDeColegios.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Municipio extends ID_ALL {

    private String nombre;

    @ManyToOne(fetch= FetchType.LAZY)
    private Departamento departamento;

}
