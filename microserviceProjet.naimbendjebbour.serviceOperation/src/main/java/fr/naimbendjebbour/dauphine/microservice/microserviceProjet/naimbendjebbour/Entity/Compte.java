package fr.naimbendjebbour.dauphine.microservice.microserviceProjet.naimbendjebbour.Entity;

public class Compte {

    private Long id;
    private String iban;
    private String type;
    private float solde;
    private float interet;
    private String frais_tenu_compte;

    public Compte(){}

    public Compte(String iban, String type,float solde ,float interet, String frais_tenu_compte) {
        this.iban = iban;
        this.type = type;
        this.solde=solde;
        this.interet = interet;
        this.frais_tenu_compte = frais_tenu_compte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public float getInteret() {
        return interet;
    }

    public void setInteret(float interet) {
        this.interet = interet;
    }

    public String getFrais_tenu_compte() {
        return frais_tenu_compte;
    }

    public void setFrais_tenu_compte(String frais_tenu_compte) {
        this.frais_tenu_compte = frais_tenu_compte;
    }
}
