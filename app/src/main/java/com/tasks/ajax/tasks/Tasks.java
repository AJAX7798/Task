package com.tasks.ajax.tasks;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Tasks extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ViewPager viewPager;
    List<SlideFragment> slides = new ArrayList<>();
    TabLayout tabs;
    SlidePagerAdapter pagerAdapter;
    Context context;
    static int mode = 0;
    static int selectedTab = 0;
    int catCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setBG();
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabs = (TabLayout)findViewById(R.id.tablayout);
    //    Spinner spinner = (Spinner)findViewById(R.id.sortsp);
        FloatingActionButton add = (FloatingActionButton)findViewById(R.id.floatingActionButton);
    /*    String[] item = new String[]{"اهمیت-نزولی","اهمیت-صعودی","انجام شده","انجام نشده","مهلت انجام-نزولی","مهلت انجام-صعودی","تاریخ شروع-نزولی","تاریخ شروع-صعودی"};
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        context = this;
    /*    spinner.setAdapter(mAdapter);
        spinner.setSelection(mode);*/
        setUpViewPager(mode);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCatCountZero()){
                    Toast.makeText(context,"For adding any task you need to create a group first!",Toast.LENGTH_SHORT).show();
                }else {
                    AddingTask.selectedCat = selectedTab;
                    Intent intent = new Intent(Tasks.this, AddingTask.class);
                    startActivity(intent);
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectedTab = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabs.setupWithViewPager(viewPager);
    /*    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mode = position;
                setUpViewPager(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        test();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void prepareslides(int mode) {
        if(slides == null){
            slides = new ArrayList<>();
        }else{
            slides.clear();
        }
    /*    for(int i = 0 ;i < getGruopCount();i++){
            slides.add(SlideFragment.newSlide(text_views[i]));
        }
*/
        Db_helper dbHelper = new Db_helper(this);
        //int gruopCount = 0;
        Cursor cursor = dbHelper.selectGroup();
        SlideFragment.context1 = this;
        SlideFragment.activity = this;
        if(cursor.moveToFirst()){
            do{
                pagerAdapter.addFragment(SlideFragment.newSlide(cursor.getString(0),mode),cursor.getString(0));
                catCount++;
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

    private void setUpViewPager(int mode){
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        prepareslides(mode);
    }


    public class SlidePagerAdapter extends FragmentPagerAdapter{
        List<Fragment> fragments;
        List<String> tabtitles;

        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            tabtitles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) { return fragments.get(position); }

        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            tabtitles.add(title);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position){
            return tabtitles.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }
    private boolean isCatCountZero(){
        if(catCount == 0){
            return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }
    private void setBG(){
        Db_helper dbHelper = new Db_helper(this);
        RelativeLayout re = (RelativeLayout)findViewById(R.id.relativebg);
        Cursor cursor = dbHelper.getBG();
        if(cursor.moveToFirst()){
            if(Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                re.setBackgroundDrawable(ContextCompat.getDrawable(this,Integer.parseInt(cursor.getString(0))));
            }else{
                re.setBackground(ContextCompat.getDrawable(this,Integer.parseInt(cursor.getString(0))));
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Sort By");
            String[] types = {"Importance-desc","Importance-asc","Done","Undone","Deadline-desc","Deadline-asc","Start Time-desc","Start Time-asc"};
            b.setItems(types, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    switch(which){
                        case 0:
                            mode = 0;
                            break;
                        case 1:
                            mode = 1;
                            break;
                        case 2:
                            mode = 2;
                            break;
                        case 3:
                            mode = 3;
                            break;
                        case 4:
                            mode = 4;
                            break;
                        case 5:
                            mode = 5;
                            break;
                        case 6:
                            mode = 6;
                            break;
                        case 7:
                            mode = 7;
                            break;
                    }
                    setUpViewPager(mode);
                }

            });

            b.show();
            return true;
        }
        else if(id == R.id.background_setting){
            Intent intent = new Intent(Tasks.this,BackgroundSetting.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void test(){
        String s = "1398/1/28-20:10";
        DateTime.getDeadline(s);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.new_cat) {
            Db_helper dbHelper = new Db_helper(this);
            int groupCount = dbHelper.getGroupCount();
            if(groupCount <= 5){
                Intent intent = new Intent(Tasks.this,addcat.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"You have created maximum group count!",Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.cat_manage) {
            Intent intent = new Intent(Tasks.this,Group_Management.class);
            startActivity(intent);

        } else if (id == R.id.stat) {
            Intent intent = new Intent(Tasks.this,Stat.class);
            startActivity(intent);

        } else if (id == R.id.passwd) {
            Intent intent = new Intent(Tasks.this,SetPass.class);
            startActivity(intent);

        } else if (id == R.id.about) {
            Intent intent = new Intent(Tasks.this,About.class);
            startActivity(intent);
        } else if (id == R.id.exit_item) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
