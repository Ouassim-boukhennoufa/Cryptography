package com.example.securemessaging;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button symetrical,asymetrical,sub,can;
    Button cesar,vigenere,affine,hill,RSa,GAM;
    TextView text,texx,t;
    EditText subj,messa;
    String uri,text1,urr;
    ImageView git,ema,link;
    AlertDialog.Builder build,builder;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=(TextView)findViewById(R.id.inspired);
        build=new AlertDialog.Builder(MainActivity.this);
        builder=new AlertDialog.Builder(MainActivity.this);
        symetrical=(Button)findViewById(R.id.symetric);
        cesar=(Button)findViewById(R.id.ces);

        vigenere=(Button)findViewById(R.id.vig);
        affine=(Button)findViewById(R.id.aff);
        hill=(Button)findViewById(R.id.hil);
        text=(TextView)findViewById(R.id.git);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        asymetrical=(Button)findViewById(R.id.asymetr);
        GAM=(Button)findViewById(R.id.gam);
        GAM.setVisibility(View.GONE);
        RSa=(Button)findViewById(R.id.rsa);
        RSa.setVisibility(View.GONE);
        git=(ImageView)findViewById(R.id.github);
        ema=(ImageView)findViewById(R.id.email);
       link=(ImageView)findViewById(R.id.linked);
        texx=(TextView)findViewById(R.id.course);
       urr="<a href=https://fsciences.univ-setif.dz/page_professionnelles/492><i><b>Dr Lemia Louail</b>'s course</i></a>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            texx.setText(Html.fromHtml(urr, Html.FROM_HTML_MODE_LEGACY));
        else
            texx.setText(Html.fromHtml(urr));
       texx.setMovementMethod(LinkMovementMethod.getInstance());
      text1="Inspired by <a href=https://www.dcode.fr><i>Dcode</i></a>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            t.setText(Html.fromHtml(text1, Html.FROM_HTML_MODE_LEGACY));
        else
            t.setText(Html.fromHtml(text1));
        t.setMovementMethod(LinkMovementMethod.getInstance());
        cesar.setVisibility(View.GONE); vigenere.setVisibility(View.GONE); affine.setVisibility(View.GONE); hill.setVisibility(View.GONE);
    }
    public void builder(String taille){

        AlertDialog dialog;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            builder.setMessage(Html.fromHtml(taille, Html.FROM_HTML_MODE_LEGACY));
        else
            builder.setMessage(Html.fromHtml(taille));
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 dialog.cancel();
            }
        });
        dialog=builder.create();
        dialog.setTitle("About CryptoMetric");
        dialog.show();
    }

    public void build(){
        View view=getLayoutInflater().inflate(R.layout.email,null);
        final AlertDialog dialog;
        subj=(EditText)view.findViewById(R.id.subject);
        messa=(EditText)view.findViewById(R.id.message);
        sub=(Button)view.findViewById(R.id.submit);
        can=(Button)view.findViewById(R.id.cancel);

        build.setView(view);
        dialog=build.create();
        dialog.show();
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dialog.dismiss();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!messa.getText().toString().trim().isEmpty()) {
                    String em,su,me;
                    if(!subj.getText().toString().trim().isEmpty())
                        su = subj.getText().toString();
                    else
                        su=null;
                    me = messa.getText().toString();
                    Intent intent=new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"ouassim2012@live.fr"});
                    intent.putExtra(Intent.EXTRA_SUBJECT,su);
                    intent.putExtra(Intent.EXTRA_TEXT,me);
                    intent.setData(Uri.parse("mailto:"));
                    startActivity(Intent.createChooser(intent,"Choose an Email Client "));
                }
                else{


                    if(messa.getText().toString().trim().isEmpty())
                        messa.setError("Field required");
                }
            }
        });
    }


    public void Open(View v){
        cesar.setVisibility(View.VISIBLE);
        vigenere.setVisibility(View.VISIBLE);
        affine.setVisibility(View.VISIBLE);
        hill.setVisibility(View.VISIBLE);
        RSa.setVisibility(View.GONE);
        GAM.setVisibility(View.GONE);
    }
    public void OpenCesar(View v){
    Intent intent=new Intent(this,Cesar.class);
    startActivity(intent);
    }
    public void OpenVigenere(View v){
        Intent intent=new Intent(this,Vigenere.class);
        startActivity(intent);
    }
    public void OpenAffine(View v){
        Intent intent=new Intent(this,Affine.class);
        startActivity(intent);
    }
    public void OpenHill(View v){
        Intent intent=new Intent(this,Hill.class);
        startActivity(intent);
    }
    public void Openbutton(View v){
        cesar.setVisibility(View.GONE);
        vigenere.setVisibility(View.GONE);
        affine.setVisibility(View.GONE);
        hill.setVisibility(View.GONE);
        RSa.setVisibility(View.VISIBLE);
        GAM.setVisibility(View.VISIBLE);
    }
    public void openRsa(View view){
        Intent intent=new Intent(this,RSA.class);
        startActivity(intent);
    }

    public void openGam(View view){
     Intent intent=new Intent(this,GAMAL.class);
     startActivity(intent);
    }
    public void page(View view){
        uri="https://github.com/Ouassim-boukhennoufa/Cryptography";
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW);
            myIntent.setData(Uri.parse(uri));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request Please install a web browser",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
    public void send(View v){
        build();
    }


    public void linked(View v){
        uri="https://www.linkedin.com/in/ouassim-boukhennoufa-328452173";
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW);
            myIntent.setData(Uri.parse(uri));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request Please install a web browser",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    public void about(View v){
       String taille="-This app is mainly destined to anyone who has some knowledge with cryptography <BR>" +
                "-Its purpose is to let any user be more comfortable with calculations <BR>"
               +"-To build this app i've been learning Dr <i><b>Lemia Louail</b></i> course,Link of the course is on home screen<BR>" +
               "-Source code is on home screen too you can get it by clicking on it or the github's logo<BR>" +
               "-Anyone can contact me to get more information or to explain any interaction by clicking on mail's logo<BR>" +
               "-It contains symetric and asymetric cryptography:<BR>" +
               " *Symetric:if the user chooses default alphabet" +
               " it will encrypt/decipher all the letters otherwise it will let the input as it is," +
               " if the user chooses personalize alphabet it will encrypt/decipher only if the input belongs the typed  otherwise" +
               " it will show an error<BR>" +
               " *Asymetric:it handles all the exception respecting each method,it will calculate all what's necessary" +
               " to encrypt/decipher any letter by its row<BR>"+
               "-It contains also landscape adavptive to any type of screen if for instance the user has a small screen,he can " +
               " rotate it so that the view is much pretty<BR>" +
               "-Finally,share my github's project with friends and let's all be productive";

        builder(taille);

    }


}
