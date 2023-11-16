package org.example.RegistroDeColegios.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.Required;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Registros extends ID_ALL{


    @Required
    @ManyToOne
    @ReferenceView("Simple")
    private Colegios colegio;

    @Required
    private LocalDate fecha;

    @Required
    private Categoria categoria;




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
}