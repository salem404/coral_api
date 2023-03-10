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
public class Fruta {
    @Id
    private String nombre;
    private Estacion estacion;
    private Integer dias_crecimiento;
    private String tipo;
}
