package com.example.yanis.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import Class.Personne;
import java.util.ArrayList;

import Class.PersonneDao;
public class LoginActivity extends AppCompatActivity {


    private EditText email;
    private EditText userName;
    private EditText password;
    private Button signIn;
    private Button register;
    private Button goTo;
    private Button btnAffichListPers;

    private PersonneDao personneDao;

    private ArrayList<Personne> arrayPersDao;
    private Intent intent;

    // STRING
    private String KEYUSERNAME =" username ";
    private String username1;



    public Dialog dialog;
    private int numberOfRemainingLoginAttempts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        email = (EditText)findViewById(R.id.etEmail);
        userName = (EditText)findViewById(R.id.etUserName);
        password = (EditText)findViewById(R.id.etEmail);

        btnAffichListPers =(Button)findViewById(R.id.btnAffichListPers);
        btnAffichListPers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personneDao = new PersonneDao(LoginActivity.this);
                personneDao.openForRead();
                arrayPersDao= personneDao.getAllPersonne();
                if(arrayPersDao != null)
                {
                    Toast.makeText(getApplicationContext(), " Affiche = " + arrayPersDao.size(), Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), " Affiche NULL !", Toast.LENGTH_LONG).show();
                }

            }
        });
        register = (Button)findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        goTo = (Button)findViewById(R.id.btnLivre);
        goTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signIn = (Button)findViewById(R.id.btnSingIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateLogin(v);
            }
        });

        dialog = new Dialog(LoginActivity.this);
        numberOfRemainingLoginAttempts = 5;
    }


    public void authenticateLogin(View view)
    {
        if (email.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")&&
                userName.getText().toString().equals("admin") ) {
            Toast.makeText(getApplicationContext(), "Hello admin!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Vous n'avez pas accès !",
                    Toast.LENGTH_SHORT).show();
            numberOfRemainingLoginAttempts--;

            Toast.makeText(getApplicationContext(), "Il vous reste : " + numberOfRemainingLoginAttempts, Toast.LENGTH_SHORT).show();


            if (userName != null && password != null) {

                dialog.setTitle("WARNING");
                dialog.setContentView(R.layout.layout);
                dialog.show();
            }
        }
    }
    /*private void openProfil()
    {
        username1 = userName.getText().toString();
        intent = new Intent(this, ActivityProfilUser.class);
        intent.putExtra(KEYUSERNAME, username1);
        startActivity(intent);
    }*/



}
