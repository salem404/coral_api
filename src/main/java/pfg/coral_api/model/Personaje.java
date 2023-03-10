package pfg.coral_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Personaje {
    @Id
    private String nombre;
    private Estacion estacion_cumpleaños;
    private Integer dia_cumpleaños;
}
