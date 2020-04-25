package com.example.securemessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RSA extends AppCompatActivity {

    private EditText P,Q,E,len;
    private TextView sh,set,see,gi,le,res,g;
   private Button vali,val,calculer;
   long  e1,fi,n,p1,q1,d,r,l,ch,de,si;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_s);
        vali=(Button)findViewById(R.id.su);
        val=(Button)findViewById(R.id.sub);
        P=(EditText)findViewById(R.id.p);
        Q=(EditText)findViewById(R.id.q);
         sh=(TextView)findViewById(R.id.textView10);
         sh.setVisibility(View.GONE);
         E=(EditText) findViewById(R.id.e);
         E.setVisibility(View.GONE);
         val.setVisibility(View.GONE);
         set=(TextView)findViewById(R.id.textView11);
         set.setVisibility(View.GONE);
         see=(TextView)findViewById(R.id.displ);
         see.setVisibility(View.GONE);
         gi=(TextView)findViewById(R.id.give);
         len=(EditText)findViewById(R.id.r);
         len.setVisibility(View.GONE);
         gi.setVisibility(View.GONE);
         le=(EditText)findViewById(R.id.l);
         le.setVisibility(View.GONE);
         res=(TextView)findViewById(R.id.resu);
         res.setVisibility(View.GONE);
         g=(TextView)findViewById(R.id.giv);
         g.setVisibility(View.GONE);
         calculer=(Button)findViewById(R.id.calc);
         calculer.setVisibility(View.GONE);
    }
    public long reverse(Long a) {
        long a_inv=0;
        long f;
        for(long i=0;i<fi;i++) {
            f=(a*i)%fi;
            if(f==1) {
                a_inv=i;
            }
        }
        return a_inv;
    }
    public void subm(View view){
        calculer.setVisibility(View.GONE);
        see.setVisibility(View.GONE);
        gi.setVisibility(View.GONE);
        le.setVisibility(View.GONE);
        res.setVisibility(View.GONE);
        len.setVisibility(View.GONE);
        g.setVisibility(View.GONE);
        if(!P.getText().toString().trim().isEmpty()&&!Q.getText().toString().trim().isEmpty()) {
            p1 = Long.parseLong(P.getText().toString());
            q1 = Long.parseLong(Q.getText().toString());

            if (isPremier(p1) && isPremier(q1)) {
                n = p1 * q1;
                fi = (p1 - 1) * (q1 - 1);
                sh.setVisibility(View.VISIBLE);
                sh.setText("n=" + n + ",φ(n)=" + fi + ",Give e:");
                val.setVisibility(View.VISIBLE);
                E.setVisibility(View.VISIBLE);
                E.setText("");
                set.setVisibility(View.GONE);
            } else {
                if(!isPremier(p1)) {
                    sh.setVisibility(View.GONE);
                    val.setVisibility(View.GONE);
                    E.setVisibility(View.GONE);
                    set.setVisibility(View.GONE);
                    P.setError(p1 + " is not prime");
                }
                if(!isPremier(q1)){
                    sh.setVisibility(View.GONE);
                    val.setVisibility(View.GONE);
                    E.setVisibility(View.GONE);
                    set.setVisibility(View.GONE);
                    Q.setError(q1 + " is not prime");
                }
            }
        }
        else{
            if(P.getText().toString().trim().isEmpty()) {
                P.setError("p is required");
                sh.setVisibility(View.GONE);
                E.setVisibility(View.GONE);
                val.setVisibility(View.GONE);
                set.setVisibility(View.GONE);
            }
            if(Q.getText().toString().trim().isEmpty()){
                Q.setError("q is requried");
                sh.setVisibility(View.GONE);
                E.setVisibility(View.GONE);
                val.setVisibility(View.GONE);
                set.setVisibility(View.GONE);
            }
        }
    }
    public void submi(View view){
        see.setVisibility(View.GONE);
        gi.setVisibility(View.GONE);
        le.setVisibility(View.GONE);
        res.setVisibility(View.GONE);
        len.setVisibility(View.GONE);
        g.setVisibility(View.GONE);
        calculer.setVisibility(View.GONE);
        String stri;
        if(!E.getText().toString().trim().isEmpty()) {
            e1 = Long.parseLong(E.getText().toString());
            if (e1 < fi) {
                if (pgcd(e1, fi) == 1) {
                    d = reverse(e1);
                    set.setVisibility(View.VISIBLE);
                    len.setText("");
                    le.setText("");
                    stri = "Public key is (" + n + "," + e1 + ")<BR>Private key is (" + n + "," + d + ") <BR>Cipherment function:<BR>C=M<sup>" + e1 + "</sup>  mod " + n + "<BR>Decipherment function:<BR>M=C<sup>" + d + "</sup> mod " + n + "<BR>Signature:<BR>S=M<sup>" + d + "</sup> mod " + n + "<BR>Signature's confirmation:<BR>S'=S<sup>" + e1 + "</sup> mod " + n;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                        set.setText(Html.fromHtml(stri, Html.FROM_HTML_MODE_LEGACY));
                    else
                        set.setText(Html.fromHtml(stri));
                        see.setVisibility(View.VISIBLE);
                        gi.setVisibility(View.VISIBLE);
                        le.setVisibility(View.VISIBLE);
                       len.setVisibility(View.VISIBLE);
                       g.setVisibility(View.VISIBLE);
                    calculer.setVisibility(View.VISIBLE);
                }
                else {
                    set.setVisibility(View.VISIBLE);
                    set.setText("PGCD(" + e1 + "," + fi + ")=" + pgcd(e1, fi) + " ≠  1");
                }
            } else if(e1==fi) {
                set.setVisibility(View.VISIBLE);
                set.setText(e1 + " equals to " + fi);
            }
            else if(e1>fi) {
                set.setVisibility(View.VISIBLE);
                set.setText(e1 + " bigger than " + fi);
            }
        }
        else{
            E.setError("e is required");
            set.setVisibility(View.GONE);
        }
    }
    public static Long pgcd(Long a, Long b) {

        Long r,q= Long.valueOf(0);

        for(;;) {
            r=a%b;
            q = (a-r)/b;
            if (r==0) break;
            a=b;
            b=r;
        }

        return b;
    }
    public boolean isPremier(Long num) {
        boolean flag = false;
        for(Long i = Long.valueOf(2); i <= num/2; ++i)
        {
            // condition for nonprime number
            if(num % i == 0)
            {
                flag = true;
                break;
            }
        }

        if (!flag)
            return true;
        else
            return false;
    }
    public void submit(View view) {
        String text1;
        if(!le.getText().toString().trim().isEmpty()){
            r=Long.parseLong(le.getText().toString());
                if (len.getText().toString().trim().isEmpty()) {
                    ch = (long) ((Math.pow(r, e1)) % n);
                    de=(long) ((Math.pow(r, d)) % n);
                    text1 ="Cipherment of "+r+":<BR>"+ r + "<sup>" + e1 + "</sup> mod " + n + " = " + ch+"<BR>"+"Decipherment of  "+r+":<BR>"+r+"<sup>"+d+"</sup> mod "+n+" = "+de;

                } else {
                    l=Long.parseLong(len.getText().toString());
                    ch = (long) ((Math.pow(r, e1)) % n);
                    de=(long) ((Math.pow(r, d)) % n);
                    text1 ="Cipherment of "+r+":<BR>"+ r + "<sup>" + e1 + "</sup> mod " + n + " =<BR>" + ch+" mod "+l+" = "+ch%l+"<BR>"+"Decipherment of  "+r+":<BR>"+r+"<sup>"+d+"</sup> mod "+n+" =<BR>"+de+" mod "+l+" = "+de%l;
                }
            res.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                res.setText(Html.fromHtml(text1, Html.FROM_HTML_MODE_LEGACY));
            else
                res.setText(Html.fromHtml(text1));
        }
        else {
            res.setVisibility(View.GONE);
            le.setError("Row required");
        }
    }
}
