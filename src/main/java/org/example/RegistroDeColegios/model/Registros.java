package org.example.RegistroDeColegios.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import org.openxava.calculators.CurrentDateCalculator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Registros extends ID_ALL {

    @Required
    @ManyToOne
    @ReferenceView("Simple")
    private Colegios colegio;

    @Required
    private LocalDate fecha;

    @Required
    private Categoria categoria;

    @Column(length = 5)
    private String id;

    @Required
    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Departamento departamento;

    @Required
    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(depends = "departamento", condition = "${departamento.id} = ?")
    private Municipio municipio;

    @Required
    private int alumnosInteresados;

    @Required
    private int alumnosGraduados;

    @Transient
    private Double porcentajeExito;

    @PrePersist
    @PreUpdate
    public void validarFecha() {


        if (categoria == Categoria.Pendiente && fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser anterior al día actual para la categoría PENDIENTE");
        }
        if (categoria == Categoria.Visitado && fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser posterior al día actual para la categoría VISITADO");
        }
    }

    @Depends("alumnosInteresados, alumnosGraduados")
    public Double getPorcentajeExito() {
        if (alumnosInteresados == 0) {
            return 0.0;
        } else {
            return ((double) alumnosInteresados / alumnosGraduados) * 100;
        }
    }

}
