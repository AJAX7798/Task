package com.tasks.ajax.tasks;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import static android.R.string.no;
import static android.R.string.yes;

public class IconDialog extends Dialog implements android.view.View.OnClickListener {

    static int preid = 0;
    static int ids = 0;
    private String whichAc = "Add";
    public Activity c;
    public Dialog d;
    public Button cancel, ok;
    ImageView im[];
    public IconDialog(Activity a,String which){
        super(a);
        this.c = a;
        this.whichAc = which;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.icon_dialog);
        im = new ImageView[40];
        ok = (Button) findViewById(R.id.dialog_ok);
        cancel = (Button) findViewById(R.id.dialog_cancel);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        this.d = this;
        init5();
    }

    public void init5(){
        ImageView im = (ImageView)findViewById(R.id.icona);
        im.setTag("o");
        im.setOnClickListener(this);

        ImageView im1 = (ImageView)findViewById(R.id.iconb);
        im1.setTag("w");
        im1.setOnClickListener(this);

        ImageView im2 = (ImageView)findViewById(R.id.iconc);
        im2.setTag("a1");
        im2.setOnClickListener(this);

        ImageView im3 = (ImageView)findViewById(R.id.icond);
        im3.setTag("h1");
        im3.setOnClickListener(this);

        ImageView im4 = (ImageView)findViewById(R.id.icone);
        im4.setTag("z");
        im4.setOnClickListener(this);

        ImageView im5 = (ImageView)findViewById(R.id.iconf);
        im5.setTag("j");
        im5.setOnClickListener(this);

        ImageView im6 = (ImageView)findViewById(R.id.icong);
        im6.setTag("h");
        im6.setOnClickListener(this);

        ImageView im7 = (ImageView)findViewById(R.id.iconh);
        im7.setTag("d1");
        im7.setOnClickListener(this);

        ImageView im8 = (ImageView)findViewById(R.id.iconi);
        im8.setTag("r1");
        im8.setOnClickListener(this);

        ImageView im9 = (ImageView)findViewById(R.id.iconj);
        im9.setTag("r");
        im9.setOnClickListener(this);

        ImageView im10 = (ImageView)findViewById(R.id.iconk);
        im10.setTag("s1");
        im10.setOnClickListener(this);

        ImageView im11 = (ImageView)findViewById(R.id.iconl);
        im11.setTag("l");
        im11.setOnClickListener(this);

        ImageView im12 = (ImageView)findViewById(R.id.iconm);
        im12.setTag("p");
        im12.setOnClickListener(this);

        ImageView im13 = (ImageView)findViewById(R.id.iconn);
        im13.setTag("m");
        im13.setOnClickListener(this);

        ImageView im14 = (ImageView)findViewById(R.id.icono);
        im14.setTag("n1");
        im14.setOnClickListener(this);

        ImageView im15 = (ImageView)findViewById(R.id.iconp);
        im15.setTag("e1");
        im15.setOnClickListener(this);

        ImageView im16 = (ImageView)findViewById(R.id.iconq);
        im16.setTag("x");
        im16.setOnClickListener(this);

        ImageView im17 = (ImageView)findViewById(R.id.iconr);
        im17.setTag("b1");
        im17.setOnClickListener(this);

        ImageView im18 = (ImageView)findViewById(R.id.icons);
        im18.setTag("o1");
        im18.setOnClickListener(this);

        ImageView im19 = (ImageView)findViewById(R.id.icont);
        im19.setTag("e1");
        im19.setOnClickListener(this);

        ImageView im20 = (ImageView)findViewById(R.id.iconu);
        im20.setTag("g1");
        im20.setOnClickListener(this);

        ImageView im21 = (ImageView)findViewById(R.id.iconv);
        im21.setTag("n");
        im21.setOnClickListener(this);

        ImageView im22 = (ImageView)findViewById(R.id.iconw);
        im22.setTag("j1");
        im22.setOnClickListener(this);

        ImageView im23 = (ImageView)findViewById(R.id.iconx);
        im23.setTag("d");
        im23.setOnClickListener(this);

        ImageView im24 = (ImageView)findViewById(R.id.icony);
        im24.setTag("t1");
        im24.setOnClickListener(this);

        ImageView im25 = (ImageView)findViewById(R.id.iconz);
        im25.setTag("k1");
        im25.setOnClickListener(this);

        ImageView im26 = (ImageView)findViewById(R.id.icona1);
        im26.setTag("v1");
        im26.setOnClickListener(this);

        ImageView im27 = (ImageView)findViewById(R.id.iconb1);
        im27.setTag("q1");
        im27.setOnClickListener(this);

        ImageView im28 = (ImageView)findViewById(R.id.iconc1);
        im28.setTag("g");
        im28.setOnClickListener(this);

        ImageView im29 = (ImageView)findViewById(R.id.icond1);
        im29.setTag("l1");
        im29.setOnClickListener(this);

        ImageView im30 = (ImageView)findViewById(R.id.icone1);
        im30.setTag("k");
        im30.setOnClickListener(this);

        ImageView im31 = (ImageView)findViewById(R.id.iconf1);
        im31.setTag("q");
        im31.setOnClickListener(this);

        ImageView im32 = (ImageView)findViewById(R.id.icong1);
        im32.setTag("f");
        im32.setOnClickListener(this);

        ImageView im33 = (ImageView)findViewById(R.id.iconh1);
        im33.setTag("u");
        im33.setOnClickListener(this);

        ImageView im34 = (ImageView)findViewById(R.id.iconi1);
        im34.setTag("i");
        im34.setOnClickListener(this);

        ImageView im35 = (ImageView)findViewById(R.id.iconj1);
        im35.setTag("p1");
        im35.setOnClickListener(this);

        ImageView im36 = (ImageView)findViewById(R.id.iconk1);
        im36.setTag("t");
        im36.setOnClickListener(this);

        ImageView im37 = (ImageView)findViewById(R.id.iconl1);
        im37.setTag("v");
        im37.setOnClickListener(this);

        ImageView im38 = (ImageView)findViewById(R.id.iconm1);
        im38.setTag("u1");
        im38.setOnClickListener(this);

        ImageView im39 = (ImageView)findViewById(R.id.iconn1);
        im39.setTag("s1");
        im39.setOnClickListener(this);

        ImageView im40 = (ImageView)findViewById(R.id.icono1);
        im40.setTag("f1");
        im40.setOnClickListener(this);

        ImageView im41 = (ImageView)findViewById(R.id.iconp1);
        im41.setTag("b");
        im41.setOnClickListener(this);

        ImageView im42 = (ImageView)findViewById(R.id.iconq1);
        im42.setTag("e");
        im42.setOnClickListener(this);

        ImageView im43 = (ImageView)findViewById(R.id.iconr1);
        im43.setTag("m1");
        im43.setOnClickListener(this);

        ImageView im44 = (ImageView)findViewById(R.id.icons1);
        im44.setTag("c");
        im44.setOnClickListener(this);

        ImageView im45 = (ImageView)findViewById(R.id.icont1);
        im45.setTag("y");
        im45.setOnClickListener(this);

        ImageView im46 = (ImageView)findViewById(R.id.iconu1);
        im46.setTag("a");
        im46.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_ok:
                if(whichAc.equals("Add")) {
                    AddingTask.selectedIcon = ids;
                    AddingTask.setIcon();
                }else if(whichAc.equals("Edit")){
                    Edit_Task.selectedIcon = ids;
                    Edit_Task.setIcon();
                }
                dismiss();
                return;
 //               break;
            case R.id.dialog_cancel:
                if(whichAc.equals("Add")){
                    AddingTask.selectedIcon = 0;
                    AddingTask.setIcon();
                }else if(whichAc.equals("Edit")){
                    Edit_Task.selectedIcon = 0;
                    Edit_Task.setIcon();
                }
                dismiss();
                return;
        }
        int id = v.getId();

        if(Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.actionbar));
        }else{
            v.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.actionbar));
        }
        try {
            if (this.preid != 0) {
                ImageView im = (ImageView) findViewById(this.preid);
                im.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backdefault));
            }
        }catch(Exception e){

        }
        this.preid = id;
        String name = (String)v.getTag();
        ids = getContext().getResources().getIdentifier(name,"drawable",getContext().getApplicationContext().getPackageName());
    }
}
