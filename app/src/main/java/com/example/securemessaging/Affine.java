package com.example.securemessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Affine extends AppCompatActivity {

    TextView text;
    EditText clear,encod,a,perso,B;
    RadioButton opt1,opt2;
    Button cipherment,decipherment;
    RadioGroup groupe;
    char lower[]= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    char upper[]= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    char pers[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affine);
        opt1=(RadioButton)findViewById(R.id.defaut);
        opt2=(RadioButton)findViewById(R.id.choose);
        clear=(EditText)findViewById(R.id.chif);
        encod=(EditText)findViewById(R.id.dechiff);
        a=(EditText)findViewById(R.id.editText2);
        B=(EditText)findViewById(R.id.b);
        perso=(EditText)findViewById(R.id.editText3);
        perso.setVisibility(View.GONE);
        cipherment=(Button)findViewById(R.id.encode);
        decipherment=(Button)findViewById(R.id.decode);
        text=(TextView)findViewById(R.id.disp);
        text.setVisibility(View.GONE);
        groupe=(RadioGroup)findViewById(R.id.group);
    }
    public static int pgcd(int a, int b) {

        int r,q=0;

        for(;;) {
            r=a%b;
            q = (a-r)/b;
            if (r==0) break;
            a=b;
            b=r;
        }

        return b;
    }
    public static int reverse(int a) {
        int a_inv=0;
        int f;
        for(int i=0;i<26;i++) {
            f=(a*i)%26;
            if(f==1) {
                a_inv=i;
            }
        }
        return a_inv;
    }
    public static int reverse_(int a,int l) {
        int a_inv=0;
        int f;
        for(int i=0;i<l;i++) {
            f=(a*i)%l;
            if(f==1) {
                a_inv=i;
            }
        }
        return a_inv;
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
        if (!clear.getText().toString().trim().isEmpty() && !a.getText().toString().trim().isEmpty() && !B.getText().toString().trim().isEmpty()){
            String tex ;
            tex= clear.getText().toString();
        int A, b;
        String affine = "";
        A = Integer.parseInt(a.getText().toString());
        b = Integer.parseInt(B.getText().toString());
        int d = 0, r = 0, size;
        char alphabet, y = ' ';
        if (opt1.isChecked()) {
            size = lower.length;
            if (A < 0) {
                A = (size - 1) - ((-A - 1) % size);
            } else {
                A = A % 26;
            }
            if (b < 0) {
                b = (size - 1) - ((-b - 1) % size);
            } else {
                b = b % 26;
            }
            if (pgcd(A, size) == 1) {
                for (int i = 0; i < tex.length(); i++) {
                    alphabet = tex.charAt(i);
                    if (Character.isLowerCase(alphabet)) {
                        for (int k = 0; k < size; k++) {
                            if (alphabet == lower[k])
                                r = k;
                        }

                        d = (A * r + b) % size;
                        y = lower[d];
                        affine = affine + y;

                    } else if (Character.isUpperCase(alphabet)) {
                        size = upper.length;
                        for (int k = 0; k < size; k++) {
                            if (alphabet == upper[k])
                                r = k;
                        }
                        d = (A * r + b) % size;
                        y = upper[d];
                        affine = affine + y;
                    } else {
                        affine = affine + alphabet;
                    }
                }
                text.setVisibility(View.VISIBLE);
                text.setText("Cipherment function :\nY=" + A + "X+" + b + "mod 26");
                encod.setText(affine);
                encod.setError(null);

            }
            else {
                text.setVisibility(View.VISIBLE);
                encod.setText("");
                text.setText("PGCD(" + A + "," + size + ")=" + pgcd(A, size) + "≠1");
            }
        }
        else if(opt2.isChecked()){
            String t;
                if(!perso.getText().toString().trim().isEmpty()) {
                    int l = perso.getText().toString().length();
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
                        if (A < 0) {
                            A = (l - 1) - ((-A - 1) % l);
                        } else {
                            A = A % l;
                        }
                        if (b < 0) {
                            b = (l - 1) - ((-b - 1) % l);
                        } else {
                            b = b % l;
                        }
                        if (pgcd(A, l) == 1) {
                            pers = new char[l];
                            t = perso.getText().toString();
                            tex = tex.toUpperCase();
                            for (int c = 0; c < l; c++) {
                                if (Character.isLetter(t.charAt(c)))
                                    pers[c] = t.toUpperCase().charAt(c);
                                else
                                    pers[c] = (char) (pers[c] + t.charAt(c));
                            }
                            for (int i = 0; i < tex.length(); i++) {
                                alphabet = tex.charAt(i);
                                for (int z = 0; z < pers.length; z++) {
                                    if (alphabet == pers[z]) {
                                        d = ((A * z) + b) % pers.length;
                                        y = pers[d];
                                    }
                                }
                                affine = affine + y;
                            }
                            text.setVisibility(View.VISIBLE);
                            text.setText("Cipherment function :\nY=" + A + "X+" + b + "mod " + pers.length);
                            encod.setText(affine);
                            encod.setError(null);
                        }
                        else{
                            text.setVisibility(View.VISIBLE);
                            encod.setText("");
                            text.setText("PGCD(" + A + "," + l + ")=" + pgcd(A, l) + "≠1");
                        }
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
            text.setVisibility(View.GONE);
            if(clear.getText().toString().trim().isEmpty())
                clear.setError("Text is required");
            if(a.getText().toString().trim().isEmpty())
                 a.setError("A is required");
            if(B.getText().toString().trim().isEmpty())
                B.setError("B is required");
        }
    }

    public void deciph(View view) {
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
        int A, b;
        String affine = "";
        int d = 0, size;
        char alphabet, x = ' ';
        if(!encod.getText().toString().trim().isEmpty() && !a.getText().toString().trim().isEmpty() && !B.getText().toString().trim().isEmpty()) {
            A = Integer.parseInt(a.getText().toString());
            b = Integer.parseInt(B.getText().toString());
            String tex = encod.getText().toString();
            if (opt1.isChecked()) {
                size = lower.length;
                if (A < 0) {
                    A = (size - 1) - ((-A - 1) % size);
                } else {
                    A = A % 26;
                }
                if (b < 0) {
                    b = (size - 1) - ((-b - 1) % size);
                } else {
                    b = b % 26;
                }
                int r = reverse(A), af;
                if (r < 0)
                    r = (size - 1) - ((-r - 1) % size);
                else
                    r = r % size;
                if (pgcd(A, size) == 1) {
                    text.setVisibility(View.VISIBLE);
                    text.setText("Decipherment function:\nX=" + r + "(Y-" + b + ")mod 26");
                    for (int i = 0; i < tex.length(); i++) {
                        alphabet = tex.charAt(i);
                        if (Character.isLowerCase(alphabet)) {
                            for (int k = 0; k < lower.length; k++) {
                                if (alphabet == lower[k]) {
                                    d = k;
                                }
                            }
                            af = (r * (d - b));
                            if (af < 0) {
                                int neg;
                                neg = (size - 1) - ((-af - 1) % size);
                                x = lower[neg];
                            } else {
                                af = (r * (d - b)) % size;
                                x = lower[af];
                            }

                            affine = affine + x;
                        } else if (Character.isUpperCase(alphabet)) {
                            for (int k = 0; k < upper.length; k++) {
                                if (alphabet == upper[k]) {
                                    d = k;
                                }
                            }
                            af = (r * (d - b));
                            if (af < 0) {
                                int neg;
                                neg = (size - 1) - ((-af - 1) % size);
                                x = upper[neg];
                            } else {
                                af = (r * (d - b)) % size;
                                x = upper[af];
                            }

                            affine = affine + x;

                        } else {
                            affine = affine +alphabet;
                        }
                    }
                    clear.setText(affine);
                    clear.setError(null);
                }
                else {
                    text.setVisibility(View.VISIBLE);
                    clear.setText("");
                    text.setText("PGCD(" + A + "," + size + ")=" + pgcd(A, size) + "≠ 1");
                }
            }
            else if(opt2.isChecked()){
               if(!perso.getText().toString().trim().isEmpty()){
                   char ch = ' ';
                   String t;
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
                       int l = perso.getText().toString().length();
                       int r, af = 0;
                       t = perso.getText().toString();
                       if (A < 0) {
                           A = (l - 1) - ((-A - 1) % l);
                       } else {
                           A = A % l;
                       }
                       if (b < 0) {
                           b = (l - 1) - ((-b - 1) % l);
                       } else {
                           b = b % l;
                       }
                       pers = new char[l];
                       for (int c = 0; c < l; c++) {
                           if (Character.isLetter(t.charAt(c)))
                               pers[c] = t.toUpperCase().charAt(c);
                           else
                               pers[c] = (char) (pers[c] + t.charAt(c));
                       }
                       if (pgcd(A, l) == 1) {
                           char y = ' ';
                           r = reverse_(A, pers.length);
                           if (r < 0)
                               r = (pers.length - 1) - ((-r - 1) % pers.length);
                           else
                               r = r % pers.length;
                           text.setVisibility(View.VISIBLE);
                           text.setText("Decipherment function:\nX=" + r + "(Y-" + b + ")mod " + pers.length);

                           tex = tex.toUpperCase();
                           for (int i = 0; i < tex.length(); i++) {
                               alphabet = tex.charAt(i);
                               for (int z = 0; z < pers.length; z++) {
                                   if (alphabet == pers[z]) {

                                       af = (r * (z - b));
                                       if (af < 0) {
                                           int neg;
                                           neg = (pers.length - 1) - ((-af - 1) % pers.length);
                                           y = pers[neg];
                                       } else {
                                           af = af % pers.length;
                                           y = pers[af];
                                       }
                                   }
                               }
                               affine = affine + y;
                           }
                           clear.setError(null);
                           clear.setText(affine);
                       } else {
                           text.setVisibility(View.VISIBLE);
                           clear.setText("");
                           text.setText("PGCD(" + A + "," + l + ")=" + pgcd(A, l) + "≠ 1");
                       }
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
            text.setVisibility(View.GONE);
            clear.setText("");
            if(encod.getText().toString().trim().isEmpty())
                encod.setError("Text is required");
            if(a.getText().toString().trim().isEmpty())
                a.setError("A is required");
            if(B.getText().toString().trim().isEmpty())
                B.setError("B is required");
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
