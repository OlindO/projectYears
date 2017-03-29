package com.example.yanis.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import Class.PersonneDao;
import Class.Personne;
public class RegisterActivity extends AppCompatActivity {


    private RadioButton radiobutton_monsieur, radiobutton_madame, radiobutton_mademoiselle;
    private EditText edittext_nom, edittext_prenom, edittext_email, edittext_password, edittext_telephone;
    private String civilite, nom, prenom, email, password, telephone;
    private Button button_valider;
    private boolean formIsOk = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initialiser();

        radiobutton_monsieur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                civilite = buttonView.getText().toString();
            }
        });

        radiobutton_madame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                civilite = buttonView.getText().toString();
            }
        });

        radiobutton_mademoiselle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                civilite = buttonView.getText().toString();
            }
        });


        button_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(civilite==null){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.libelle_civilite_obligatoire), Toast.LENGTH_SHORT).show();
                }
                else{
                    nom = edittext_nom.getText().toString();
                    prenom = edittext_prenom.getText().toString();
                    email = edittext_email.getText().toString();
                    password = edittext_password.getText().toString();
                    telephone = edittext_telephone.getText().toString();

                    if(nom.trim().length() == 0) {
                        edittext_nom.setError(getResources().getString(R.string.libelle_nom_obligatoire));
                        formIsOk = false;
                    }
                    if(prenom.trim().length() == 0) {
                        edittext_prenom.setError(getResources().getString(R.string.libelle_prenom_obligatoire));
                        formIsOk = false;
                    }
                    if(email.trim().length() == 0){
                        edittext_email.setError(getResources().getString(R.string.libelle_email_obligatoire));
                        formIsOk = false;
                    }
                    if(password.trim().length() == 0){
                        edittext_password.setError(getResources().getString(R.string.libelle_password_obligatoire));
                        formIsOk = false;
                    }
                    if(telephone.trim().length() == 0){
                        edittext_telephone.setError(getResources().getString(R.string.libelle_telephone_obligatoire));
                        formIsOk = false;
                    }

                    if(formIsOk) ajouterPersonne();
                }
            }
        });
    }

    private void ajouterPersonne(){
        PersonneDao daoPersonne = new PersonneDao(RegisterActivity.this);
        daoPersonne.openForWrite();

        Personne personne = new Personne();
        personne.setCivilite(civilite);
        personne.setName(nom);
        personne.setPrenom(prenom);
        personne.setEmail(email);
        personne.setPassword(password);
        personne.setTelephone(telephone);

        daoPersonne.instertPersonne(personne);
        daoPersonne.close();

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.libelle_donnees_enregistrees), Toast.LENGTH_SHORT).show();
    }

    private void initialiser(){
        radiobutton_monsieur = (RadioButton)findViewById(R.id.radiobutton_monsieur);
        radiobutton_madame = (RadioButton)findViewById(R.id.radiobutton_madame);
        radiobutton_mademoiselle = (RadioButton)findViewById(R.id.radiobutton_mademoiselle);
        edittext_nom = (EditText) findViewById(R.id.edittext_nom);
        edittext_prenom = (EditText) findViewById(R.id.edittext_prenom);
        edittext_email = (EditText) findViewById(R.id.edittext_email);
        edittext_password = (EditText) findViewById(R.id.edittext_password);
        edittext_telephone = (EditText) findViewById(R.id.edittext_telephone);
        button_valider = (Button)findViewById(R.id.button_valider);
    }
}

