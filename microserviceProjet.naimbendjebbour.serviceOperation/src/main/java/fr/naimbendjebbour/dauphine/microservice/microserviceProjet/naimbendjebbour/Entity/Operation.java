package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Operation implements Serializable {

    @Id @GeneratedValue
    private long id;
    private String type;
    private String ibanSource;
    private String ibandestination;
    private String devise;
    private float montant;
    private Date date;

    public Operation() {
    }

    public Operation(String type, String ibanSource, String ibandestination, String devise, float montant, Date date) {
        this.type = type;
        this.ibanSource = ibanSource;
        this.ibandestination = ibandestination;
        this.devise = devise;
        this.montant = montant;
        this.date = date;
    }


}
