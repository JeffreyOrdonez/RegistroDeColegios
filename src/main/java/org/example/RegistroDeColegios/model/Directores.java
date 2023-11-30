package org.example.RegistroDeColegios.model;


import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Required;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

    @Embeddable
    @Getter
    @Setter
    public class Directores {
        @Required
        private String nombre;
        @Required
        @Pattern(regexp="(^$|[0-9]{3}-[0-9]{8})")
        private String telefono;
        @Required
        @Email
        private String correo;

    }



