package org.example.RegistroDeColegios.model;


import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.View;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Collection;


@Entity
@Getter
@Setter
@View(name="Simple",members = "nombreColegio")
public class Colegios extends ID_ALL{

    private String nombreColegio;

    @ElementCollection
    @ListProperties("nombre, telefono, correo")
    private Collection<Directores> director;


}
