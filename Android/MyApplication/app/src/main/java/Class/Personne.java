package Class;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yanis on 20/02/2017.
 */

public class Personne implements Serializable {
    private int id;
    private String name;
    private String password;
    private String prenom ;
    private String civilite;
    public String telephone;
    private String email;
    public ArrayList<String> st;

    public Personne() {
    }
    public Personne(int id,String civilite, String name, String prenom , String email , String password, String telephone) {
        this.id = id;
        this.civilite = civilite;
        this.password = password;
        this.name = name;
        this.prenom = prenom ;
        this.email = email;
        this.telephone = telephone;
    }

    public Personne(int id,String name, String prenom ,String civilite, String password) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.prenom = prenom ;
        this.civilite = civilite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String description) {
        this.password = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString ()
    {
        return "id : " + id + "Name " + name + "Prenom" + prenom + "Civilite : " + civilite + "password :" + password;
    }

}

