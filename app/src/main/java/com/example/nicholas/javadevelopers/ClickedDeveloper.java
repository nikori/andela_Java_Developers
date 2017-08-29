package com.example.nicholas.javadevelopers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by kori on 2017-08-29.
 */

public class ClickedDeveloper extends AppCompatActivity {

    TextView myidt,myunamet,myurlt,mytypet,myadmint,myscoret;
    ImageView myimg;

    String myids,myunames,myurls,mytypes,myadmins,myscores,myimgurl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clicked_developer);
        setMyToolbar();
        initialise();

        getPassedValues();

        setAdmin();
        setId();
        setImgurl();
        setScore();
        setType();
        setUname();
        setUrl();

        setClickListener();

    }

    public void setClickListener(){
        try{

            myurlt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("myurl")));
                    startActivity(browser);
                }
            });
        }
        catch(Exception e){


        }
    }
    public void initialise(){

        try{
            myidt=(TextView) findViewById(R.id.myid);
            myunamet=(TextView) findViewById(R.id.myuname);
            myurlt=(TextView) findViewById(R.id.myurl);
            mytypet=(TextView) findViewById(R.id.mytype);
            myadmint=(TextView) findViewById(R.id.myadmin);
            myscoret=(TextView) findViewById(R.id.myscore);
            myimg=(ImageView) findViewById(R.id.myimgv);

            myids="";
            myunames="";
            myurls="";
            mytypes="";
            myadmins="";
            myscores="";
            myimgurl="";

        }
        catch(Exception e){


        }
    }

    public void setId(){

        try{

            myidt.setText(myids);
        }
        catch(Exception e){

        }
    }
    public void setUname(){

        try{

            myunamet.setText(myunames);
        }
        catch(Exception e){

        }
    }
    public void setUrl(){

        try{

            myurlt.setText(myurls);
        }
        catch(Exception e){

        }
    }
    public void setType(){

        try{

            mytypet.setText(mytypes);
        }
        catch(Exception e){

        }
    }
    public void setAdmin(){

        try{

            myadmint.setText(myadmins);
        }
        catch(Exception e){

        }
    }
    public void setScore(){

        try{

            myscoret.setText(myscores);
        }
        catch(Exception e){

        }
    }
    public void setImgurl(){

        try{

            Picasso.with(this).load(myimgurl).resize(400,400).into(myimg);
        }
        catch(Exception e){

        }
    }

    public void getPassedValues(){

        try{


            myids=getIntent().getStringExtra("myid");
            myunames=getIntent().getStringExtra("mylogin");
            myurls=getIntent().getStringExtra("myurl");
            mytypes=getIntent().getStringExtra("mytype");
            myadmins=getIntent().getStringExtra("mysiteadmin");
            myscores=getIntent().getStringExtra("myscore");
            myimgurl=getIntent().getStringExtra("myavatar");


        }
        catch(Exception e){

        }
    }

    public void Share(View v){

        try{

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Check Out My Profile on GITHUB\nLogin: "+myunames+"\n"+"GITHUB URL: "+myurls+"\n"+"My scores: "+myscores);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent,"Share using"));


        }
        catch(Exception e){


        }
    }


    public void setMyToolbar(){

        try{


            String title=getIntent().getStringExtra("mylogin");
            Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(title);

        }
        catch(Exception e){


        }
    }


}
