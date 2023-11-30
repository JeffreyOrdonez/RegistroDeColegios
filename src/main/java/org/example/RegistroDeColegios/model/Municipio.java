package org.example.RegistroDeColegios.model;


import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter

public class Municipio extends ID_ALL {
    @Required
    @ReadOnly
    private String nombre;

    @Required
    @ReadOnly
    @ManyToOne(fetch= FetchType.LAZY)
    private Departamento departamento;
}
