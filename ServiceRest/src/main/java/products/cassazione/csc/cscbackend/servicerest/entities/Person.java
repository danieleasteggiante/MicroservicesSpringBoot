package products.cassazione.csc.cscbackend.servicerest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniele Asteggiante
 */
@Entity
@Getter
@Setter
public class Person {
    @Id
    private Long id;
    private String name;
    private String surname;
}
