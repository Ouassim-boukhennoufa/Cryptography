package com.example.securemessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GAMAL extends AppCompatActivity {

    Button calcul1,calcul2,dec,sin;
    EditText P,G,X,L,K,Ro,C1,C2,N;
    TextView set,s,se,ss,fin,hide,sh,rand,signat,mod;
    RadioGroup groupp;
    RadioButton rad1,rad2;
    int pp,gg,xx,c1,c2;
    int y, r, l, k,rr,m;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_a_m_a_l);
        calcul1=(Button)findViewById(R.id.su);
        calcul2=(Button)findViewById(R.id.sub);
        N=(EditText)findViewById(R.id.n);
        N.setVisibility(View.GONE);
        mod=(TextView)findViewById(R.id.modify);
        mod.setVisibility(View.GONE);
        signat=(TextView)findViewById(R.id.sign);
        signat.setVisibility(View.GONE);
         sin=(Button)findViewById(R.id.calcul);
         sin.setVisibility(View.GONE);
        hide=(TextView)findViewById(R.id.textView13);
        dec=(Button)findViewById(R.id.decry);
        dec.setVisibility(View.GONE);
       rand=(TextView)findViewById(R.id.textView);
       rand.setVisibility(View.GONE);
        sh=(TextView)findViewById(R.id.textVi);
        sh.setVisibility(View.GONE);
        C1=(EditText)findViewById(R.id.c1);
        C1.setVisibility(View.GONE);
        C2=(EditText)findViewById(R.id.c2);
        C2.setVisibility(View.GONE);
        rad1=(RadioButton)findViewById(R.id.chiff);
        rad2=(RadioButton)findViewById(R.id.dechif);
        hide.setVisibility(View.GONE);
        groupp=(RadioGroup)findViewById(R.id.groupe);
        groupp.setVisibility(View.GONE);
        P=(EditText)findViewById(R.id.p);
        G=(EditText)findViewById(R.id.g);
        X=(EditText)findViewById(R.id.x);
        set=(TextView)findViewById(R.id.resu);
        s=(TextView)findViewById(R.id.textView10);
        se=(TextView)findViewById(R.id.textView11);
        ss=(TextView)findViewById(R.id.textView12);
        L=(EditText)findViewById(R.id.letter);
        K=(EditText)findViewById(R.id.k);
        Ro=(EditText)findViewById(R.id.length);
        fin=(TextView)findViewById(R.id.finall);
        calcul2.setVisibility(View.GONE);
        fin.setVisibility(View.GONE);
        set.setVisibility(View.GONE);
        se.setVisibility(View.GONE);
        ss.setVisibility(View.GONE);
        s.setVisibility(View.GONE);
        L.setVisibility(View.GONE);
        K.setVisibility(View.GONE);
        Ro.setVisibility(View.GONE);
    }
    public void subm(View v){
        L.setText("");
        K.setText("");
        sin.setVisibility(View.GONE);
        Ro.setText("");
        dec.setVisibility(View.GONE);
        C1.setVisibility(View.GONE);
        C2.setVisibility(View.GONE);
        groupp.setVisibility(View.GONE);
        se.setVisibility(View.GONE);
        ss.setVisibility(View.GONE);
        hide.setVisibility(View.GONE);
        s.setVisibility(View.GONE);
        N.setVisibility(View.GONE);
        rand.setVisibility(View.GONE);
        set.setVisibility(View.GONE);
        L.setVisibility(View.GONE);
        mod.setVisibility(View.GONE);
        K.setVisibility(View.GONE);
        Ro.setVisibility(View.GONE);
        fin.setVisibility(View.GONE);
        sh.setVisibility(View.GONE);
        signat.setVisibility(View.GONE);
        calcul2.setVisibility(View.GONE);
        P.setError(null);
        G.setError(null);
        X.setError(null);
      if(!P.getText().toString().trim().isEmpty()&&!G.getText().toString().trim().isEmpty()&&!X.getText().toString().trim().isEmpty()){
          pp=Integer.parseInt(P.getText().toString());
          gg=Integer.parseInt(G.getText().toString());
          xx=Integer.parseInt(X.getText().toString());
          if(isPremier(pp)&&(xx>0&&xx<pp-1)) {
              String gamal;
              y = (int) ((Math.pow(gg, xx)) % pp);
              set.setVisibility(View.VISIBLE);
              String pub = "Public key=(" + pp + "," + gg + "," + y + ")";
              String pub1="y="+gg+"<sup>"+xx+"</sup> mod "+pp+"="+y+"<BR>";
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                  set.setText(Html.fromHtml(pub1+pub, Html.FROM_HTML_MODE_LEGACY));
              else
                  set.setText(Html.fromHtml(pub1+pub));

              groupp.setVisibility(View.VISIBLE);
          }
          else{
              if(!isPremier(pp))
                  P.setError(pp+" is not prime");
              if(xx>=pp-1||xx<0)
                  X.setError("x must ∈]0,"+ (pp - 1) +"[");
          }
      }else{
          if(P.getText().toString().trim().isEmpty())
              P.setError("p required");
          if(G.getText().toString().trim().isEmpty())
              G.setError("g required");
          if(X.getText().toString().trim().isEmpty())
              X.setError(" x required");
      }
    }
public void validate(View v){
            if (!L.getText().toString().trim().isEmpty() && !K.getText().toString().trim().isEmpty()) {
                k = Integer.parseInt(K.getText().toString());
                r = Integer.parseInt(L.getText().toString());

                String aff = "Cipherment of " + r + ":<BR>";
                String af, a;

                c1=(int)((Math.pow(gg,k))%pp);
                c2=(int)(((Math.pow(y,k))*r)%pp);
                String send = "The pair to send:(" + c1 + "," + c2 + ")<BR>";
                fin.setVisibility(View.VISIBLE);
                if (Ro.getText().toString().trim().isEmpty()) {
                    af = "c1=" + gg + "<sup>" + k + "</sup> mod " + pp + "=" + c1 + "<BR>";
                    a = "c2=(" + y + "<sup>" + k + "</sup>*" + r + ") mod " + pp + "=" + c2 + "<BR>";
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                        fin.setText(Html.fromHtml(aff + af + a + send, Html.FROM_HTML_MODE_LEGACY));
                    else
                        fin.setText(Html.fromHtml(aff + af + a + send));
                } else {
                    int alphabet = Integer.parseInt(Ro.getText().toString());
                    af = c1 + " mod " + alphabet + "=" + c1 % alphabet + "<BR>";
                    a = c2 + " mod " + alphabet + "=" + c1 % alphabet + "<BR>";
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                        fin.setText(Html.fromHtml(aff + af + a + send, Html.FROM_HTML_MODE_LEGACY));
                    else
                        fin.setText(Html.fromHtml(aff + af + a + send));
                }
                rand.setVisibility(View.VISIBLE);
                sin.setVisibility(View.VISIBLE);
                signat.setVisibility(View.VISIBLE);
                N.setVisibility(View.VISIBLE);
                N.setText("");

            } else {
                if (L.getText().toString().trim().isEmpty())
                    L.setError("row required");
                if (K.getText().toString().trim().isEmpty())
                    K.setError("k required");
            }

}
    public void Decipher(View v){

          if(!C1.getText().toString().trim().isEmpty()&&!C2.getText().toString().trim().isEmpty()) {
              String aff,f;
              c1=Integer.parseInt(C1.getText().toString());
              c2=Integer.parseInt(C2.getText().toString());
              rr = (int) (Math.pow(c1, (pp - 1 - xx))%pp);
              m=(rr*c2)%pp;
              if(Ro.getText().toString().trim().isEmpty()) {

                  aff = "r=(" + c1 + ")<sup>(" + (pp - 1 - xx) + ")</sup> mod "+pp+"=" + rr + "<BR>";
                  f = "Clear message is:<BR>(" + rr + "*" + c2 + ")mod " + pp + "=" + m;
                  fin.setVisibility(View.VISIBLE);
                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                      fin.setText(Html.fromHtml(aff + f, Html.FROM_HTML_MODE_LEGACY));
                  else
                      fin.setText(Html.fromHtml(aff + f));
              }
              else{
                  int alphabet=Integer.parseInt(Ro.getText().toString());
                  aff = "r="+rr + " mod "+alphabet+"="+rr%alphabet+"\n";
                  f = "Clear message:"+ m+" mod "+alphabet+"="+m%alphabet;
                  fin.setText(aff+f);
              }


          }
          else{
              if(C1.getText().toString().trim().isEmpty())
                  C1.setError("c1 required");
              if(C2.getText().toString().trim().isEmpty())
                  C2.setError("c2 required");
          }

    }
public void show_dec(View v){
        C1.setText("");
    C2.setText("");
    Ro.setText("");
    signat.setVisibility(View.GONE);
    sh.setVisibility(View.VISIBLE);
    L.setText("");
    K.setText("");
    Ro.setText("");
    mod.setVisibility(View.GONE);
    L.setVisibility(View.GONE);
    K.setVisibility(View.GONE);
    s.setVisibility(View.GONE);
    fin.setText("");
    Ro.setVisibility(View.VISIBLE);
    se.setVisibility(View.GONE);
    fin.setVisibility(View.GONE);
    ss.setVisibility(View.VISIBLE);
    N.setVisibility(View.GONE);
    rand.setVisibility(View.GONE);
    hide.setVisibility(View.VISIBLE);
    C2.setVisibility(View.VISIBLE);
    C1.setVisibility(View.VISIBLE);
    calcul2.setVisibility(View.GONE);

    dec.setVisibility(View.VISIBLE);
    rad1.setError(null);
    rad2.setError(null);

    sin.setVisibility(View.GONE);

}
public void calcula(View v){
        if(!N.getText().toString().trim().isEmpty()){
            r = Integer.parseInt(L.getText().toString());
           int n=Integer.parseInt(N.getText().toString());
            int s1,s2,rev,e,f;String af,aff,afff;
            s1=(int)(Math.pow(gg,n)%pp);
            rev=reverse_(n,(pp-1));
            e=(xx*s1)%(pp-1);
            f=r-e;
            if(f<0)
                f=((pp-1)-1)-((-f-1)%(pp-1));
            else
                f=f%(pp-1);
            s2=(f*rev)%(pp-1);
            af="s1="+gg+"<sup>"+n+"</sup> mod "+pp+"="+s1+"<BR>";
            aff="s2=("+f+"*"+rev+") mod "+(pp-1)+"="+s2+"<BR>";
            afff="Signed message:"+r+"("+s1+","+s2+")";
            mod.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                mod.setText(Html.fromHtml(af+aff+afff, Html.FROM_HTML_MODE_LEGACY));
            else
                mod.setText(Html.fromHtml(af+aff + afff));

        }
        else
        N.setError("n required");


}
public void show_chif(View v){
        fin.setText("");
        sin.setVisibility(View.GONE);
    sh.setVisibility(View.GONE);
    L.setText("");
    K.setText("");
    Ro.setText("");
    dec.setVisibility(View.GONE);
    C2.setVisibility(View.GONE);
    C1.setVisibility(View.GONE);
    rad1.setError(null);
    rad2.setError(null);
    mod.setVisibility(View.GONE);
    se.setVisibility(View.VISIBLE);
    ss.setVisibility(View.VISIBLE);
    s.setVisibility(View.VISIBLE);
    L.setVisibility(View.VISIBLE);
    hide.setVisibility(View.GONE);
    signat.setVisibility(View.GONE);
    rand.setVisibility(View.GONE);
    fin.setVisibility(View.GONE);
    K.setVisibility(View.VISIBLE);
    Ro.setVisibility(View.VISIBLE);
    calcul2.setVisibility(View.VISIBLE);
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
    public boolean isPremier(int num) {
        boolean flag = false;
        for(int i = 2; i <= num/2; ++i)
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
}
