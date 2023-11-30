package org.example.RegistroDeColegios.model;

import lombok.Getter;
import lombok.Setter;
import org.glassfish.jersey.internal.util.Property;
import org.openxava.annotations.*;
import org.openxava.calculators.CurrentDateCalculator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Registros extends ID_ALL {

    @Required
    @ManyToOne
    @ReferenceView("Simple")
    private Colegios colegio;

    @Required
    private Tipodehorario tipo_de_horario;


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

    @Property
    @Required
    @Pattern(regexp="([01]?[0-9]|2[0-3]):[0-5][0-9]")
    private String hora;


    @Required
    @Min(0)
    private int alumnosInteresados;
    @Min(0)
    @Required
    private int alumnosGraduados;
    @Min(0)
    @ReadOnly
    private Double porcentajeExito;

    @Depends("alumnosInteresados, alumnosGraduados")
    public Double getPorcentajeExito() {
        if (alumnosInteresados == 0 || alumnosGraduados <= alumnosInteresados) {
            return 0.0;
        } else {
            return ((double) alumnosInteresados / alumnosGraduados) * 100;
        }
    }

    public Categoria getCategoria() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime citaHora = LocalTime.parse(hora, formatter);
        LocalDateTime citaFechaHora = LocalDateTime.of(fecha, citaHora);

        if (categoria == Categoria.Pendiente && citaFechaHora.isBefore(LocalDateTime.now())) {
            categoria = Categoria.Visitado;
        }
        return categoria;
    }
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
