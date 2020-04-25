package com.example.securemessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Cesar extends AppCompatActivity {
     EditText clear,encod,key,perso;
     RadioButton opt1,opt2;
     RadioGroup groupe;
     Button cipherment,decipherment;
    char lower[]= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    char upper[]= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    char pers[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesar);
        opt1=(RadioButton)findViewById(R.id.defaut);
        opt2=(RadioButton)findViewById(R.id.choose);
        clear=(EditText)findViewById(R.id.chif);
        encod=(EditText)findViewById(R.id.dechiff);
        groupe=(RadioGroup)findViewById(R.id.group);
        key=(EditText)findViewById(R.id.editText2);
        perso=(EditText)findViewById(R.id.editText3);
        cipherment=(Button)findViewById(R.id.encode);
        decipherment=(Button)findViewById(R.id.decode);
        perso.setVisibility(View.GONE);
    }
    public void cipher(View v){
        encod.setError(null);
        encod.setText("");
        clear.setError(null);
        perso.setError(null);
        int pos=groupe.getCheckedRadioButtonId();
        if((pos<=0))
        {
            opt1.setError("Must select");
            opt2.setError("Must select");
        }
        String tex,t, cesar = "";
        int shif,l;
        char alphabet, alph = 'z';
        int mod = 0, index=0;
        if(!key.getText().toString().trim().isEmpty()&&!clear.getText().toString().trim().isEmpty()) {

            shif = Integer.parseInt(key.getText().toString());
            tex = clear.getText().toString();
            if (opt1.isChecked()) {

                for (int i = 0; i < tex.length(); i++) {
                    alphabet = tex.charAt(i);
                    if (Character.isLowerCase(alphabet)) {
                        for (int j = 0; j < lower.length; j++) {
                            if (alphabet == lower[j]) {
                                mod = (j + shif) % lower.length;
                                alph = lower[mod];
                            }
                        }
                        cesar = cesar + alph;
                    } else if (Character.isUpperCase(alphabet)) {
                        for (int j = 0; j < upper.length; j++) {
                            if (alphabet == upper[j]) {
                                mod = (j + shif) % upper.length;
                                alph = upper[mod];
                            }
                        }
                        cesar = cesar + alph;
                    } else {
                        cesar = cesar +alphabet;
                    }
                }
                encod.setText(cesar);
                encod.setError(null);
            }
            else if(opt2.isChecked()){
                if(!perso.getText().toString().trim().isEmpty()) {
                    char ch = ' ';
                    t = perso.getText().toString();
                    t = t.toUpperCase();
                    boolean check = false;
                    tex = tex.toUpperCase();
                    LOOP:
                    for (int i = 0; i < tex.length(); i++) {
                        ch = tex.charAt(i);
                        if (t.indexOf(ch) == -1) {
                            check = true;
                            break LOOP;
                        }
                    }
                    if (!check) {
                        l = perso.getText().toString().length();
                        pers = new char[l];
                        t = perso.getText().toString();
                        for (int c = 0; c < perso.getText().toString().length(); c++) {
                            if (Character.isLetter(t.charAt(c)))
                                pers[c] = t.toUpperCase().charAt(c);
                            else
                                pers[c] = (char) (pers[c] + t.charAt(c));
                        }
                        for (int i = 0; i < tex.length(); i++) {
                            alphabet = tex.charAt(i);
                            for (int z = 0; z < pers.length; z++) {
                                if (alphabet == pers[z]) {
                                    index = z + shif;
                                    mod = index % pers.length;
                                    alph = pers[mod];
                                }
                            }
                            cesar = cesar + alph;
                        }
                        encod.setText(cesar);
                        encod.setError(null);
                    }
                    else{
                        encod.setText("");
                        encod.setError(null);
                        clear.setError("Text must contain only letters from your alphabet");
                    }
                }
                else{
                    perso.setError("Field required");
                    encod.setError(null);
                    encod.setText("");
                }
            }
        }
        else{
            encod.setText("");
            if(key.getText().toString().trim().isEmpty())
            key.setError("Key is required");
            if(clear.getText().toString().trim().isEmpty())
            clear.setError("Text required");
        }

    }
    public void decipher(View v) {
        clear.setError(null);
        clear.setText("");
        encod.setError(null);
        perso.setError(null);
        int pos=groupe.getCheckedRadioButtonId();
        if((pos<=0))
        {
            opt1.setError("Must select");
            opt2.setError("Must select");
        }
        if(!key.getText().toString().trim().isEmpty()&&!encod.getText().toString().trim().isEmpty()) {
            String tex,t, cesar = "";
            int shif,l;
            shif = Integer.parseInt(key.getText().toString());
            tex = encod.getText().toString();
            char alphabet, alph = 'z';
            int mod = 0, index=0;
            if (opt1.isChecked()) {
                for (int i = 0; i < tex.length(); i++) {
                    alphabet = tex.charAt(i);
                    if (Character.isLowerCase(alphabet)) {
                        for (int j = 0; j < lower.length; j++) {
                            if (alphabet == lower[j]) {
                                index = (j - shif);
                                if (index < 0) {
                                    mod = (lower.length - 1) - ((-index - 1) % lower.length);
                                    alph = lower[mod];
                                } else {
                                    mod = index % lower.length;
                                    alph = lower[mod];
                                }
                            }
                        }
                        cesar = cesar + alph;
                    } else if (Character.isUpperCase(alphabet)) {
                        for (int j = 0; j < upper.length; j++) {
                            if (alphabet == upper[j]) {
                                index = (j - shif);
                                if (index < 0) {
                                    mod = (upper.length - 1) - ((-index - 1) % upper.length);
                                    alph = upper[mod];
                                } else {
                                    mod = index % upper.length;
                                    alph = upper[mod];
                                }
                            }
                        }
                        cesar = cesar + alph;
                    } else {
                        cesar = cesar +alphabet;
                    }
                }
                clear.setError(null);
                clear.setText(cesar);
            }
            else if(opt2.isChecked()){
                if(!perso.getText().toString().trim().isEmpty()) {
                    char ch = ' ';
                    t = perso.getText().toString();
                    t = t.toUpperCase();
                    boolean check = false;
                    tex = tex.toUpperCase();
                    LOOP:
                    for (int i = 0; i < tex.length(); i++) {
                        ch = tex.charAt(i);
                        if (t.indexOf(ch) == -1) {
                            check = true;
                            break LOOP;
                        }
                    }
                    if (!check) {
                        l = perso.getText().toString().length();
                        pers = new char[l];
                        t = perso.getText().toString();
                        for (int c = 0; c < l; c++) {
                            if (Character.isLetter(t.charAt(c)))
                                pers[c] = t.toUpperCase().charAt(c);
                            else
                                pers[c] = (char) (pers[c] + t.charAt(c));
                        }
                        tex = tex.toUpperCase();
                        for (int i = 0; i < tex.length(); i++) {
                            alphabet = tex.charAt(i);
                            for (int z = 0; z < pers.length; z++) {
                                if (alphabet == pers[z]) {
                                    index = z - shif;
                                    if (index < 0)
                                        mod = (pers.length - 1) - ((-index - 1) % pers.length);
                                    else
                                        mod = index % pers.length;
                                    alph = pers[mod];
                                }
                            }
                            cesar = cesar + alph;
                        }
                        clear.setText(cesar);
                        clear.setError(null);
                    }
                    else{
                        clear.setText("");
                        clear.setError(null);
                        encod.setError("Text must contain only letters from your alphabet");
                    }
                }
                else{
                    perso.setError("Field required");
                    clear.setText("");
                    clear.setError(null);
                }

            }
        }
        else{
            clear.setText("");
            if(key.getText().toString().trim().isEmpty())
                key.setError("Key is required");
            if(encod.getText().toString().trim().isEmpty())
                encod.setError("Text required");
        }
    }
    public void hide(View v){
        perso.setVisibility(View.GONE);
        opt1.setError(null);
        opt2.setError(null);
    }
    public void show(View v){
        perso.setVisibility(View.VISIBLE);
        perso.setText("");
        opt1.setError(null);
        opt2.setError(null);
    }
}
