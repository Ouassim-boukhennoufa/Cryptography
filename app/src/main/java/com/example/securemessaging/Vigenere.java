package com.example.securemessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static java.lang.Character.isLetter;

public class Vigenere extends AppCompatActivity {

    EditText clear,encod,ke,perso;
    RadioButton opt1,opt2;
    Button cipherment,decipherment;
    RadioGroup groupe;
    char pers[];
    char lower[]= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    char upper[]= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere);
        opt1=(RadioButton)findViewById(R.id.defaut);
        opt2=(RadioButton)findViewById(R.id.choose);
        clear=(EditText)findViewById(R.id.chif);
        encod=(EditText)findViewById(R.id.dechiff);
        ke=(EditText)findViewById(R.id.editText2);
        perso=(EditText)findViewById(R.id.editText3);
        cipherment=(Button)findViewById(R.id.encode);
        decipherment=(Button)findViewById(R.id.decode);
        perso.setVisibility(View.GONE);
        groupe=(RadioGroup)findViewById(R.id.group);

    }

    public void ciphe(View view) {
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
        if(!clear.getText().toString().trim().isEmpty()&&!ke.getText().toString().trim().isEmpty()) {
            String tex = clear.getText().toString();
            String key = ke.getText().toString();
            String vigenere = "", keyy = "";
            char alphabet, alpha, alph = 'z';
            int index = 0, ind = 0, mod = 0;
            if (opt1.isChecked()) {
                if(isString(key)) {
                    for (int i = 0, j = 0; i < tex.length(); i++) {
                        alphabet = tex.charAt(i);
                        if (!isLetter(alphabet)) {
                            keyy = keyy + alphabet;
                            continue;
                        } else {
                            keyy = keyy + (key.charAt(j % key.length()));
                        }
                        j++;
                    }

                    for (int i = 0; i < tex.length(); i++) {
                        alphabet = tex.charAt(i);
                        alpha = keyy.charAt(i);
                        if (Character.isLowerCase(alphabet)) {

                            for (int k = 0; k < lower.length; k++) {
                                if (alphabet == lower[k]) {
                                    index = k;
                                }
                            }
                            if (Character.isLowerCase(alpha)) {
                                for (int z = 0; z < lower.length; z++) {
                                    if (alpha == lower[z]) {
                                        ind = z;
                                    }
                                    mod = (index + ind) % lower.length;
                                    alph = lower[mod];
                                }

                            } else if (Character.isUpperCase(alpha)) {

                                for (int z = 0; z < upper.length; z++) {
                                    if (alpha == upper[z]) {
                                        ind = z;
                                    }
                                    mod = (index + ind) % upper.length;
                                    alph = upper[mod];
                                }

                            }

                            vigenere = vigenere + alph;


                        } else if (Character.isUpperCase(alphabet)) {
                            for (int k = 0; k < upper.length; k++) {
                                if (alphabet == upper[k]) {
                                    index = k;
                                }
                            }

                            if (Character.isLowerCase(alpha)) {
                                for (int z = 0; z < lower.length; z++) {
                                    if (alpha == lower[z]) {
                                        ind = z;
                                    }
                                    mod = (index + ind) % lower.length;
                                    alph = lower[mod];
                                }

                            } else if (Character.isUpperCase(alpha)) {

                                for (int z = 0; z < upper.length; z++) {
                                    if (alpha == upper[z]) {
                                        ind = z;
                                    }
                                    mod = (index + ind) % upper.length;
                                    alph = upper[mod];
                                }

                            }
                            vigenere = vigenere + alph;
                        } else {
                            vigenere += alphabet;
                        }
                    }
                    encod.setText(vigenere);
                    encod.setError(null);
                }
                else{
                    ke.setError("Key must contain only letters");
                    encod.setText("");
                }
            }
            else if(opt2.isChecked()){
                if(!perso.getText().toString().trim().isEmpty()){
                    String t;
                    char ch = ' ';
                    t = perso.getText().toString();
                    t = t.toUpperCase();
                    boolean check = false,checkk=false;
                    tex = tex.toUpperCase();
                    key=key.toUpperCase();
                    LOOP:
                    for (int i = 0; i < tex.length(); i++) {
                        ch = tex.charAt(i);
                        if (t.indexOf(ch) == -1) {
                            check = true;
                            break LOOP;
                        }
                    }
                    LOOPP:
                    for (int i = 0; i < key.length(); i++) {
                        ch = key.charAt(i);
                        if (t.indexOf(ch) == -1) {
                            checkk = true;
                            break LOOPP;
                        }
                    }
                    if(!check&&!checkk) {
                        int l = perso.getText().toString().length();
                        pers = new char[l];
                        t = perso.getText().toString();
                        for (int c = 0; c < perso.getText().toString().length(); c++) {
                            if (isLetter(t.charAt(c)))
                                pers[c] = t.toUpperCase().charAt(c);
                            else
                                pers[c] = (char) (pers[c] + t.charAt(c));
                        }
                        for (int i = 0; i < tex.length(); i++) {

                            keyy = keyy + (key.charAt(i % key.length()));

                        }
                        for (int i = 0; i < tex.length(); i++) {
                            alphabet = tex.charAt(i);
                            alpha = keyy.charAt(i);
                            for (int k = 0; k < pers.length; k++) {
                                if (alphabet == pers[k]) {
                                    index = k;
                                }
                            }
                            for (int z = 0; z < pers.length; z++) {
                                if (alpha == pers[z]) {
                                    ind = z;
                                }
                                mod = (index + ind) % pers.length;
                                alph = pers[mod];
                            }
                            vigenere = vigenere + alph;
                        }
                        encod.setText(vigenere);
                        encod.setError(null);
                    }

                    else{
                        if(check){
                            encod.setText("");
                            encod.setError(null);
                            clear.setError("Text must contain only letters from your alphabet");
                        }
                        if(checkk){
                            encod.setText("");
                            encod.setError(null);
                            ke.setError("Text must contain only letters from your alphabet");
                        }
                    }
                }
                else{
                    encod.setText("");
                    perso.setError("Field required");
                }
            }
        }
        else{
            encod.setText("");
            if(clear.getText().toString().trim().isEmpty())
                clear.setError("Text required");
            if(ke.getText().toString().trim().isEmpty())
                ke.setError("Key required");

        }
    }

    public void deciph(View view) {
        clear.setError(null);
        clear.setText("");
        encod.setError(null);
        perso.setError(null);
        int pos = groupe.getCheckedRadioButtonId();
        if ((pos <= 0)) {
            opt1.setError("Must select");
            opt2.setError("Must select");
        }
        if (!encod.getText().toString().trim().isEmpty() && !ke.getText().toString().trim().isEmpty()) {
            String tex = encod.getText().toString();
            String key = ke.getText().toString();
            String vigenere = "", keyy = "";
            char alphabet, alpha, alph = 'z';
            int index = 0, ind = 0, mod = 0;
                if (opt1.isChecked()) {
                    if(isString(key)) {
                        for (int i = 0, j = 0; i < tex.length(); i++) {
                            alphabet = tex.charAt(i);
                            if (!Character.isLetter(alphabet)) {
                                keyy = keyy + alphabet;
                                continue;
                            } else {
                                keyy = keyy + (key.charAt(j % key.length()));
                            }
                            j++;
                        }
                        for (int i = 0; i < tex.length(); i++) {
                            alphabet = tex.charAt(i);
                            alpha = keyy.charAt(i);
                            if (Character.isLowerCase(alphabet)) {
                                for (int k = 0; k < lower.length; k++) {
                                    if (alphabet == lower[k]) {
                                        index = k;
                                    }
                                }
                                if (Character.isLowerCase(alpha)) {
                                    for (int k = 0; k < lower.length; k++) {
                                        if (alpha == lower[k]) {
                                            ind = index - k;
                                        }
                                    }
                                    if (ind < 0) {
                                        mod = (lower.length - 1) - ((-ind - 1) % lower.length);
                                        alph = lower[mod];
                                    } else {
                                        mod = ind % lower.length;
                                        alph = lower[mod];
                                    }

                                } else if (Character.isUpperCase(alpha)) {
                                    for (int k = 0; k < upper.length; k++) {
                                        if (alpha == upper[k]) {
                                            ind = index - k;
                                        }
                                    }
                                    if (ind < 0) {
                                        mod = (upper.length - 1) - ((-ind - 1) % upper.length);
                                        alph = upper[mod];
                                    } else {
                                        mod = ind % upper.length;
                                        alph = upper[mod];
                                    }
                                }
                                vigenere = vigenere + alph;
                            } else if (Character.isUpperCase(alphabet)) {
                                for (int k = 0; k < upper.length; k++) {
                                    if (alphabet == upper[k]) {
                                        index = k;
                                    }
                                }
                                if (Character.isLowerCase(alpha)) {
                                    for (int k = 0; k < lower.length; k++) {
                                        if (alpha == lower[k]) {
                                            ind = index - k;
                                        }
                                    }
                                    if (ind < 0) {
                                        mod = (lower.length - 1) - ((-ind - 1) % lower.length);
                                        alph = lower[mod];
                                    } else {
                                        mod = ind % lower.length;
                                        alph = lower[mod];
                                    }
                                } else if (Character.isUpperCase(alpha)) {
                                    for (int k = 0; k < upper.length; k++) {
                                        if (alpha == upper[k]) {
                                            ind = index - k;
                                        }
                                    }
                                    if (ind < 0) {
                                        mod = (upper.length - 1) - ((-ind - 1) % upper.length);
                                        alph = upper[mod];
                                    } else {
                                        mod = ind % upper.length;
                                        alph = upper[mod];
                                    }
                                }

                                vigenere = vigenere + alph;
                            } else {
                                vigenere = vigenere + alphabet;
                            }


                        }
                        clear.setText(vigenere);
                        clear.setError(null);
                    }

                else{
                    ke.setError("Key must contain only letters");
                    clear.setText("");
                    }
                }
                else if (opt2.isChecked()) {
                    if (!perso.getText().toString().trim().isEmpty()) {
                        String t;
                        char ch = ' ';
                        t = perso.getText().toString();
                        t = t.toUpperCase();
                        boolean check = false, checkk = false;
                        tex = tex.toUpperCase();
                        key = key.toUpperCase();
                        LOOP:
                        for (int i = 0; i < tex.length(); i++) {
                            ch = tex.charAt(i);
                            if (t.indexOf(ch) == -1) {
                                check = true;
                                break LOOP;
                            }
                        }
                        LOOPP:
                        for (int i = 0; i < key.length(); i++) {
                            ch = key.charAt(i);
                            if (t.indexOf(ch) == -1) {
                                checkk = true;
                                break LOOPP;
                            }
                        }
                        if (!check && !checkk) {
                            int l = perso.getText().toString().length();
                            pers = new char[l];
                            t = perso.getText().toString();
                            for (int c = 0; c < perso.getText().toString().length(); c++) {
                                if (isLetter(t.charAt(c)))
                                    pers[c] = t.toUpperCase().charAt(c);
                                else
                                    pers[c] = (char) (pers[c] + t.charAt(c));
                            }
                            for (int i = 0; i < tex.length(); i++) {
                                alphabet = tex.charAt(i);

                                keyy = keyy + (key.charAt(i % key.length()));

                            }

                            for (int i = 0; i < tex.length(); i++) {
                                alphabet = tex.charAt(i);
                                alpha = keyy.charAt(i);
                                for (int k = 0; k < pers.length; k++) {
                                    if (alphabet == pers[k]) {
                                        index = k;
                                    }
                                }
                                for (int z = 0; z < pers.length; z++) {
                                    if (alpha == pers[z]) {
                                        ind = index - z;
                                    }
                                    if (ind < 0) {
                                        mod = (pers.length - 1) - ((-ind - 1) % pers.length);
                                    } else {
                                        mod = ind % pers.length;
                                    }
                                    alph = pers[mod];
                                }
                                vigenere = vigenere + alph;
                            }
                            clear.setText(vigenere);
                            clear.setError(null);
                        } else {
                            if (check) {
                                clear.setText("");
                                encod.setError(null);
                                encod.setError("Text must contain only letters from your alphabet");
                            }
                            if (checkk) {
                                clear.setText("");
                                encod.setError(null);
                                ke.setError("Text must contain only letters from your alphabet");
                            }
                        }

                    } else {
                        clear.setText("");
                        perso.setError("Field required");
                    }
                }


        }
            else{
                clear.setText("");
                if (encod.getText().toString().trim().isEmpty())
                    encod.setError("Text required");
                if (ke.getText().toString().trim().isEmpty())
                    ke.setError("Key required");
            }


    }
    public static boolean isString(String str){
        return ((str!=null)&&(!str.equals(""))&&(str.matches("^[a-zA-Z]*$")));
    }
    public void hide(View v){
        perso.setVisibility(View.GONE);
        opt1.setError(null);
        opt2.setError(null);
    }
    public void show(View v){
        perso.setVisibility(View.VISIBLE);
        perso.setText("");

    }
}
