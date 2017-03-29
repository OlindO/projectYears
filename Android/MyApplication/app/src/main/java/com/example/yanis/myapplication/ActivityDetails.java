package com.example.yanis.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import Class.LivreAdaptateur;

import java.util.ArrayList;

import Class.Livre;
public class ActivityDetails extends AppCompatActivity {

    private ListView listView;
    private LivreAdaptateur livreAdaptateur;
    private Bundle bundle;
    private Livre livre;
    private TextView textViewDesc;


    private ImageView buyIt;
    private ArrayList<Livre> livres ;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle("Details du livre ");
        bundle = getIntent().getExtras();
        if(bundle == null || !bundle.containsKey("livre"))
        {
            finish();
        }

        textViewDesc = (TextView)findViewById(R.id.textDescription);
        livre = (Livre)bundle.getSerializable("livre");
        livres = new ArrayList<>();
        livres.add(livre);

        livreAdaptateur = new LivreAdaptateur(ActivityDetails.this, livres,true);
        textViewDesc.setText(livre.getDesc());


        listView = (ListView)findViewById(R.id.listeDetails);
        listView.setAdapter(livreAdaptateur);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.imgCart:
                intent = new Intent(this, CartActivity.class);
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*Toast.makeText(this, "SA MARCHE ICI ", Toast.LENGTH_SHORT).show();*/
        return super.onTouchEvent(event);
    }

}
