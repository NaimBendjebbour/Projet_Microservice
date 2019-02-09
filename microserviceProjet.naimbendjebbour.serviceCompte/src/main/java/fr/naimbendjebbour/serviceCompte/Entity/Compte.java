package fr.naimbendjebbour.serviceCompte.Entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Compte implements Serializable {

    @Id @GeneratedValue
    private Long id;
    private String iban;
    private String type;
    private float interet;
    private String frais_tenu_compte;

    public Compte(){}

    public Compte(String iban, String type, float interet, String frais_tenu_compte) {
        this.iban = iban;
        this.type = type;
        this.interet = interet;
        this.frais_tenu_compte = frais_tenu_compte;
    }


}
