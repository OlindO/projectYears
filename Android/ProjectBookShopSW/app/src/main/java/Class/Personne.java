package Class;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by yanis on 18/03/2017.
 */

public class Personne implements Serializable {

    //int
    private int id;
    //String
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String civilite;

    // ImgV
    private String photo;

    //

    public Personne(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Personne(int id, String nom, String email, String prenom,
                    String password, String civilite, String photo) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.prenom = prenom;
        this.password = password;
        this.civilite = civilite;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
