package com.example.yanis.projectbookshopsw;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Main_Acceuil extends AppCompatActivity {

    // ImgView
    private ImageButton imgB;

    private Button btnP;
    private Button btnR;
    private Button btnL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__acceuil);

        ImageButton imagebutton = (ImageButton)findViewById(R.id.imgUser);
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Acceuil.this, Activity_Profil.class);
                startActivity(intent);

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        btnP = (Button)findViewById(R.id.btnProfil);
        btnR = (Button)findViewById(R.id.btnRegister);
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Acceuil.this, Activity_Register.class);
                startActivity(intent);

            }
        });
        btnL = (Button)findViewById(R.id.btnLogin);
        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Acceuil.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // getMenuInflater().inflate(R.menu.activity__profil);
        return super.onCreateOptionsMenu(menu);
    }
}
