package com.example.securemessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Hill extends AppCompatActivity {

    private TextView text;
   private EditText clear,encod,a,perso,B,C,D,add;
   private RadioButton opt1,opt2;
   RadioGroup groupe;
    private Button cipherment,decipherment;
    char lower[]= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    char upper[]= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    char pers[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill);
        opt1=(RadioButton)findViewById(R.id.defaut);
        opt2=(RadioButton)findViewById(R.id.choose);
        clear=(EditText)findViewById(R.id.chif);
        encod=(EditText)findViewById(R.id.dechiff);
        add=(EditText)findViewById(R.id.car);
        a=(EditText)findViewById(R.id.editText2);
        B=(EditText)findViewById(R.id.b);
        C=(EditText)findViewById(R.id.c);
        D=(EditText)findViewById(R.id.d);
        perso=(EditText)findViewById(R.id.editText3);
        perso.setVisibility(View.GONE);
        cipherment=(Button)findViewById(R.id.encode);
        decipherment=(Button)findViewById(R.id.decode);
        text=(TextView)findViewById(R.id.textView2);
        text.setVisibility(View.GONE);
        groupe=(RadioGroup)findViewById(R.id.group);
    }

    public void ciphe(View view) {
        encod.setError(null);
        encod.setText("");
        clear.setError(null);
        perso.setError(null);
        add.setError(null);
        int pos=groupe.getCheckedRadioButtonId();
        if((pos<=0))
        {
            opt1.setError("Must select");
            opt2.setError("Must select");
        }
        if(!clear.getText().toString().trim().isEmpty()&&!a.getText().toString().trim().isEmpty()&&!B.getText().toString().trim().isEmpty()&&!C.getText().toString().trim().isEmpty()&&!D.getText().toString().trim().isEmpty()) {
            String message = clear.getText().toString();
            String hill = "";
            char alphabet, alpha, af = ' ', aff = ' ';
            int A = Integer.parseInt(a.getText().toString());
            int b = Integer.parseInt(B.getText().toString());
            int c = Integer.parseInt(C.getText().toString());
            int d = Integer.parseInt(D.getText().toString());
            int index = 0, ind = 0, mod = 0;
            if (opt1.isChecked()) {
                encod.setError(null);
                encod.setText("");
                clear.setError(null);
                perso.setError(null);
                add.setError(null);
                int size = lower.length;
                int siz = lower.length;
                if (A < 0) {
                    A = (siz - 1) - ((-A - 1) % siz);
                } else {
                    A = A % 26;
                }
                if (b < 0) {
                    b = (siz - 1) - ((-b - 1) % siz);
                } else {
                    b = b % 26;
                }
                if (c < 0) {
                    c = (siz - 1) - ((-c - 1) % siz);
                } else {
                    c = c % 26;
                }
                if (d < 0) {
                    d = (siz - 1) - ((-d - 1) % siz);
                } else {
                    d = d % 26;
                }
                int det = (A * d) - (b * c);
                if (det < 0) {
                    det = (siz - 1) - ((-det - 1) % siz);
                } else {
                    det = det % 26;
                }
                text.setVisibility(View.VISIBLE);
                text.setText("Determinant of matrix=" + det);
                if (pgcd(det, size) == 1) {
                    boolean bool = false;

                    message=message.replaceAll("[^a-zA-Z]","");
                    if (message.length() % 2 != 0 && !add.getText().toString().trim().isEmpty()) {
                        message = message + (add.getText().toString());
                    } else if (message.length() % 2 != 0 && add.getText().toString().trim().isEmpty()) {
                        add.setError("add a character text is odd");
                        encod.setText("");
                        bool = true;
                    }
                    if (!bool) {
                        for (int i = 0; i < message.length(); i = i + 2) {

                            alphabet = message.charAt(i);
                            alpha = message.charAt(i + 1);
                            if (Character.isLowerCase(alphabet)) {
                                for (int z = 0; z < lower.length; z++) {
                                    if (alphabet == lower[z]) {
                                        index = z;
                                    }
                                }
                                if (Character.isLowerCase(alpha)) {
                                    for (int l = 0; l < lower.length; l++) {
                                        if (alpha == lower[l]) {
                                            ind = l;
                                        }
                                    }
                                    mod = ((index * A) + (ind * b)) % size;
                                    af = lower[mod];
                                    hill = hill + af;
                                    mod = ((index * c) + (ind * d)) % size;
                                    aff = lower[mod];
                                    hill = hill + aff;
                                } else {
                                    for (int l = 0; l < upper.length; l++) {
                                        if (alpha == upper[l]) {
                                            ind = l;
                                        }
                                    }
                                    mod = ((index * A) + (ind * b)) % size;
                                    af = lower[mod];
                                    hill = hill + af;
                                    mod = ((index * c) + (ind * d)) % upper.length;
                                    aff = upper[mod];
                                    hill = hill + aff;
                                }
                            } else {
                                for (int z = 0; z < upper.length; z++) {
                                    if (alphabet == upper[z]) {
                                        index = z;
                                    }
                                }
                                if (Character.isLowerCase(alpha)) {
                                    for (int l = 0; l < lower.length; l++) {
                                        if (alpha == lower[l]) {
                                            ind = l;
                                        }
                                    }
                                    mod = ((index * A) + (ind * b)) % upper.length;
                                    af = upper[mod];
                                    hill = hill + af;
                                    mod = ((index * c) + (ind * d)) % size;
                                    aff = lower[mod];
                                    hill = hill + aff;
                                } else {
                                    for (int l = 0; l < upper.length; l++) {
                                        if (alpha == upper[l]) {
                                            ind = l;
                                        }
                                    }
                                    mod = ((index * A) + (ind * b)) % upper.length;
                                    af = upper[mod];
                                    hill = hill + af;
                                    mod = ((index * c) + (ind * d)) % upper.length;
                                    aff = upper[mod];
                                    hill = hill + aff;
                                }

                            }

                        }
                        encod.setText(hill);
                        encod.setError(null);
                    }
                }
                else {
                    text.setVisibility(View.VISIBLE);
                    encod.setText("");
                    text.setText("PGCD(" + det + "," + size + ")=" + pgcd(det, size) + "≠1");
                }
            }
            else if(opt2.isChecked()){
                if(!perso.getText().toString().trim().isEmpty()){
                    String t=perso.getText().toString();
                    int l;boolean check=false;
                    t=t.toUpperCase();
                    l=perso.getText().toString().length();
                    pers=new char[l];
                    char ch=' ';
                    message=message.toUpperCase();
                    LOOP:
                    for(int i=0;i<message.length();i++) {
                        ch = message.charAt(i);
                        if (t.indexOf(ch) == -1) {
                            check = true;
                            break LOOP;
                        }
                    }
                        if(!check){
                            boolean bool = false;

                            if (message.length() % 2 != 0 && !add.getText().toString().trim().isEmpty()) {
                                String m=add.getText().toString();
                                m=m.toUpperCase();
                                if(t.indexOf(m)==-1){
                                    bool=true;
                                    add.setError("Letter must be from your alphabet");
                                }
                                else {
                                    bool=false;
                                    message = message + m;
                                }
                            } else if (message.length() % 2 != 0 && add.getText().toString().trim().isEmpty()) {
                                add.setError("add a character,text is odd");
                                encod.setText("");
                                bool = true;
                            }
                            if (!bool) {
                                int aa=A;
                                for (int C = 0; C < l; C++) {
                                    if (Character.isLetter(t.charAt(C)))
                                        pers[C] = t.toUpperCase().charAt(C);
                                    else
                                        pers[C] = (char) (pers[C] + t.charAt(C));
                                }
                                if (aa < 0) {
                                    aa = (l - 1) - ((-aa - 1) % l);
                                } else {
                                    aa = aa % l;
                                }
                                if (b < 0) {
                                    b = (l - 1) - ((-b - 1) % l);
                                } else {
                                    b = b % l;
                                }
                                if (c < 0) {
                                    c = (l - 1) - ((-c - 1) % l);
                                } else {
                                    c = c % l;
                                }
                                if (d < 0) {
                                    d = (l - 1) - ((-d - 1) % l);
                                } else {
                                    d = d % l;
                                }
                                int det = (aa * d) - (b * c);
                                if (det < 0) {
                                    det = (l - 1) - ((-det - 1) % l);
                                } else {
                                    det = det % l;
                                }
                                if (pgcd(det, l) == 1) {
                                    for (int i = 0; i < message.length(); i = i + 2) {

                                        alphabet = message.charAt(i);
                                        alpha = message.charAt(i + 1);
                                        for (int z = 0; z < pers.length; z++) {
                                            if (alphabet == pers[z]) {
                                                index = z;
                                            }
                                        }
                                        for (int z = 0; z < pers.length; z++) {
                                            if (alpha == pers[z]) {
                                                ind = z;
                                            }
                                        }
                                        mod = ((index *aa) + (ind * b)) % pers.length;
                                        af = pers[mod];
                                        hill = hill + af;
                                        mod = ((index * c) + (ind * d)) % pers.length;
                                        aff = pers[mod];
                                        hill = hill + aff;
                                    }
                                    text.setVisibility(View.VISIBLE);
                                    text.setText("Determinant of matrix =" + det);
                                    encod.setText(hill);
                                    encod.setError(null);
                                }
                                else{
                                    text.setVisibility(View.VISIBLE);
                                    encod.setText("");
                                    text.setText("PGCD(" + det + "," + l + ")=" + pgcd(det, l) + "≠1");
                                }
                            }
                        }
                        else {
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
            text.setVisibility(View.GONE);
            encod.setText("");
            if(clear.getText().toString().trim().isEmpty())
                clear.setError("Text required");
            if(a.getText().toString().trim().isEmpty())
                a.setError("A is required");
            if (B.getText().toString().trim().isEmpty())
                B.setError("B is required");
            if(C.getText().toString().trim().isEmpty())
                C.setError("C is required");
            if(D.getText().toString().trim().isEmpty())
                D.setError("D is required");
        }
    }



    public void deciph(View view) {
        clear.setError(null);
        clear.setText("");
        encod.setError(null);
        perso.setError(null);
        add.setError(null);
        int pos=groupe.getCheckedRadioButtonId();
        if((pos<=0))
        {
            opt1.setError("Must select");
            opt2.setError("Must select");
        }
        if(!encod.getText().toString().trim().isEmpty()&&!a.getText().toString().trim().isEmpty()&&!B.getText().toString().trim().isEmpty()&&!C.getText().toString().trim().isEmpty()&&!D.getText().toString().trim().isEmpty()) {
            String message = encod.getText().toString();
            String hill = "";
            char alphabet, alpha, af = ' ', aff = ' ';
            int aa = Integer.parseInt(a.getText().toString());
            int b = Integer.parseInt(B.getText().toString());
            int c = Integer.parseInt(C.getText().toString());
            int d = Integer.parseInt(D.getText().toString());
            if (opt1.isChecked()) {
                int siz = lower.length, size = upper.length;
                if (aa < 0) {
                    aa = (siz - 1) - ((-aa - 1) % siz);
                } else {
                    aa = aa % 26;
                }
                if (b < 0) {
                    b = (siz - 1) - ((-b - 1) % siz);
                } else {
                    b = b % 26;
                }
                if (c < 0) {
                    c = (siz - 1) - ((-c - 1) % siz);
                } else {
                    c = c % 26;
                }
                if (d < 0) {
                    d = (siz - 1) - ((-d - 1) % siz);
                } else {
                    d = d % 26;
                }
                int det = (aa * d) - (b * c), rev, in;
                if (det < 0) {
                    det = (siz - 1) - ((-det - 1) % siz);
                } else {
                    det = det % 26;
                }
                int index = 0, ind = 0, mod = 0;
                if (pgcd(det, 26) == 1) {
                    boolean bool = false;

                    message = message.replaceAll("[^a-zA-Z]", "");
                    if (message.length() % 2 != 0 && !add.getText().toString().trim().isEmpty()) {
                        message = message + (add.getText().toString());
                    } else if (message.length() % 2 != 0 && add.getText().toString().trim().isEmpty()) {
                        add.setError("add a character text is odd");
                        clear.setText("");
                        bool = true;
                    }
                    if (!bool) {
                        rev = reverse(det);
                        if (rev < 0)
                            rev = (rev - 1) - ((-det - 1) % siz);
                        else
                            rev = rev % siz;
                        in = d;
                        d = aa;
                        aa = in;
                        b = -b;
                        c = -c;
                        aa = rev * aa;
                        if (aa < 0)
                            aa = (siz - 1) - ((-aa - 1) % siz);
                        else
                            aa = aa % 26;
                        b = rev * b;
                        if (b < 0)
                            b = (siz - 1) - ((-b - 1) % siz);
                        else
                            b = b % 26;
                        c = rev * c;
                        if (c < 0)
                            c = (siz - 1) - ((-c - 1) % siz);
                        else
                            c = c % 26;
                        d = d * rev;
                        if (d < 0)
                            d = (siz - 1) - ((-d - 1) % siz);
                        else
                            d = d % 26;
                        for (int i = 0; i < message.length(); i = i + 2) {
                            alphabet = message.charAt(i);
                            alpha = message.charAt(i + 1);
                            if (Character.isLowerCase(alphabet)) {
                                for (int z = 0; z < lower.length; z++) {
                                    if (alphabet == lower[z]) {
                                        index = z;
                                    }
                                }
                                if (Character.isLowerCase(alpha)) {
                                    for (int l = 0; l < lower.length; l++) {
                                        if (alpha == lower[l]) {
                                            ind = l;
                                        }
                                    }
                                    mod = ((index * aa) + (ind * b)) % size;
                                    af = lower[mod];
                                    hill = hill + af;
                                    mod = ((index * c) + (ind * d)) % size;
                                    aff = lower[mod];
                                    hill = hill + aff;
                                } else {
                                    for (int l = 0; l < upper.length; l++) {
                                        if (alpha == upper[l]) {
                                            ind = l;
                                        }
                                    }
                                    mod = ((index * aa) + (ind * b)) % size;
                                    af = lower[mod];
                                    hill = hill + af;
                                    mod = ((index * c) + (ind * d)) % upper.length;
                                    aff = upper[mod];
                                    hill = hill + aff;
                                }
                            } else {
                                for (int z = 0; z < upper.length; z++) {
                                    if (alphabet == upper[z]) {
                                        index = z;
                                    }
                                }
                                if (Character.isLowerCase(alpha)) {
                                    for (int l = 0; l < lower.length; l++) {
                                        if (alpha == lower[l]) {
                                            ind = l;
                                        }
                                    }
                                    mod = ((index * aa) + (ind * b)) % upper.length;
                                    af = upper[mod];
                                    hill = hill + af;
                                    mod = ((index * c) + (ind * d)) % size;
                                    aff = lower[mod];
                                    hill = hill + aff;
                                } else {
                                    for (int l = 0; l < upper.length; l++) {
                                        if (alpha == upper[l]) {
                                            ind = l;
                                        }
                                    }
                                    mod = ((index * aa) + (ind * b)) % upper.length;
                                    af = upper[mod];
                                    hill = hill + af;
                                    mod = ((index * c) + (ind * d)) % upper.length;
                                    aff = upper[mod];
                                    hill = hill + aff;
                                }

                            }
                        }
                        text.setVisibility(View.VISIBLE);
                        text.setText("Determinant of matrix=" + det);
                        clear.setText(hill);
                        clear.setError(null);
                    }
                }
                else {
                    text.setVisibility(View.VISIBLE);
                    clear.setText("");
                    text.setText("PGCD(" + det + "," + size + ")=" + pgcd(det, size) + "≠1");
                }
            }
            else if(opt2.isChecked()){

                if(!perso.getText().toString().trim().isEmpty()){
                    String t=perso.getText().toString();
                    int l;boolean check=false;
                    t=t.toUpperCase();
                    l=perso.getText().toString().length();
                    pers=new char[l];
                    char ch=' ';
                    message=message.toUpperCase();
                    LOOP:
                    for(int i=0;i<message.length();i++) {
                        ch = message.charAt(i);
                        if (t.indexOf(ch) == -1) {
                            check = true;
                            break LOOP;
                        }
                    }
                    if(!check) {
                        boolean bool = false;

                        if (message.length() % 2 != 0 && !add.getText().toString().trim().isEmpty()) {
                            String m=add.getText().toString();
                            m=m.toUpperCase();
                            if(t.indexOf(m)==-1){
                                bool=true;
                                add.setError("Letter must be from your alphabet");
                                clear.setText("");
                            }
                            else {
                                bool=false;
                                message = message + m;
                            }
                        } else if (message.length() % 2 != 0 && add.getText().toString().trim().isEmpty()) {
                            add.setError("add a character,text is impair");
                            bool = true;
                        }
                        if (!bool) {
                            for (int C = 0; C < l; C++) {
                                if (Character.isLetter(t.charAt(C)))
                                    pers[C] = t.toUpperCase().charAt(C);
                                else
                                    pers[C] = (char) (pers[C] + t.charAt(C));
                            }
                            if (aa < 0) {
                                aa = (l - 1) - ((-aa - 1) % l);
                            } else {
                                aa = aa % l;
                            }
                            if (b < 0) {
                                b = (l - 1) - ((-b - 1) % l);
                            } else {
                                b = b % l;
                            }
                            if (c < 0) {
                                c = (l - 1) - ((-c - 1) % l);
                            } else {
                                c = c % l;
                            }
                            if (d < 0) {
                                d = (l - 1) - ((-d - 1) % l);
                            } else {
                                d = d % l;
                            }
                            int det = (aa * d) - (b * c), rev, in;
                            if (det < 0) {
                                det = (l - 1) - ((-det - 1) % l);
                            } else {
                                det = det % l;
                            }
                            if (pgcd(det, l) == 1) {
                                int index = 0, ind = 0, mod = 0;
                                rev = reverse_(det, l);
                                if (rev < 0)
                                    rev = (rev - 1) - ((-det - 1) % l);
                                else
                                    rev = rev % l;
                                in = d;
                                d = aa;
                                aa = in;
                                b = -b;
                                c = -c;
                                aa = rev * aa;
                                if (aa < 0)
                                    aa = (l - 1) - ((-aa - 1) % l);
                                else
                                    aa = aa % l;
                                b = rev * b;
                                if (b < 0)
                                    b = (l - 1) - ((-b - 1) % l);
                                else
                                    b = b % l;
                                c = rev * c;
                                if (c < 0)
                                    c = (l - 1) - ((-c - 1) % l);
                                else
                                    c = c % 26;
                                d = d * rev;
                                if (d < 0)
                                    d = (l - 1) - ((-d - 1) % l);
                                else
                                    d = d % l;

                                for (int i = 0; i < message.length(); i = i + 2) {
                                    alphabet = message.charAt(i);
                                    alpha = message.charAt(i + 1);
                                    for (int z = 0; z < pers.length; z++) {
                                        if (alphabet == pers[z]) {
                                            index = z;
                                        }
                                    }
                                    for (int z = 0; z < pers.length; z++) {
                                        if (alpha == pers[z]) {
                                            ind = z;
                                        }
                                    }
                                    mod = ((index * aa) + (ind * b)) % pers.length;
                                    af = pers[mod];
                                    hill = hill + af;
                                    mod = ((index * c) + (ind * d)) % pers.length;
                                    aff = pers[mod];
                                    hill = hill + aff;
                                }
                                text.setVisibility(View.VISIBLE);
                                text.setText("Determinant of matrix=" + det);
                                clear.setText(hill);
                                clear.setError(null);
                            } else {
                                text.setVisibility(View.VISIBLE);
                                clear.setText("");
                                text.setText("PGCD(" + det + "," + l + ")=" + pgcd(det, l) + "≠1");
                            }
                        }
                    }
                    else {
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
            text.setVisibility(View.GONE);
            if(encod.getText().toString().trim().isEmpty())
            encod.setError("Text required");
            if(a.getText().toString().trim().isEmpty())
            a.setError("A is required");
            if (B.getText().toString().trim().isEmpty())
            B.setError("B is required");
            if(C.getText().toString().trim().isEmpty())
            C.setError("C is required");
            if(D.getText().toString().trim().isEmpty())
            D.setError("D is required");
        }

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
}
