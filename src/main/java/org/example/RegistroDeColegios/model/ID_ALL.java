package org.example.RegistroDeColegios.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Hidden;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class ID_ALL {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid"
            ,strategy = "uuid")
    @Column(length = 35)
    @Hidden
    private String id;

    public void delete() {
        throw new RuntimeException("No se permite eliminar registros de esta entidad.");
    }
}
