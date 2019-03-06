package com.example.datepicker;
 

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;  
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	JalaliCalendar dateandtime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 

		  final TextView  t=(TextView)findViewById(R.id.textView1); 
		  
		  
		  
		   boolean isLeapYear = ((1398 %4 == 3) && (1398 % 100 !=3) || (1398 % 400 == 3));
		   if (isLeapYear) {
		         
			   Toast.makeText(getApplicationContext(),
	  	               " "+isLeapYear, Toast.LENGTH_LONG).show();
	  	        
	       }
	
		  
		
		RelativeLayout relativeclic1 =(RelativeLayout)findViewById(R.id.date);
        relativeclic1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            	DatePickerDailog dp = new DatePickerDailog(MainActivity.this,
						dateandtime, new DatePickerDailog.DatePickerListner() {

							@SuppressLint("SimpleDateFormat")
							@Override
							public void OnDoneButton(Dialog datedialog, String c) {
								datedialog.dismiss();
								  
							 
								  t.setText(c.toString()); 
							}

							@Override
							public void OnCancelButton(Dialog datedialog) {
								// TODO Auto-generated method stub
								datedialog.dismiss();
							}
						});
				dp.show();
            }
        });
		
		 
	}
 
	
}
