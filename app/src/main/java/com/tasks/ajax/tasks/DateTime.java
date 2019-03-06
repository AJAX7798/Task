package com.tasks.ajax.tasks;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public int compare(String date){
        int compare = 3;
        Calendar calendar = Calendar.getInstance();
        adapterFormat(date);
        int curyear = calendar.get(Calendar.YEAR);
        int curmonth = calendar.get(Calendar.MONTH)+1;
        int curday = calendar.get(Calendar.DATE);
        int curhour = calendar.get(Calendar.HOUR_OF_DAY);
        int curminute = calendar.get(Calendar.MINUTE);
        Log.i("TIMEDATE:",curyear + "/" + curmonth + "/" + curday + "-" + curhour + ":" + curminute);
        Log.i("TIMEDATE:",year + "/" + month + "/" + day + "-" + hour + ":" + minute);

        if(curyear > year){
            return -1;
        }
        else if(curyear < year){
            Log.i("TIMEDATE:","f1");
            return 1;
        }
        else if(curyear == year){
            if(curmonth > month){
                return -1;
            }
            else if(curmonth < month){
                Log.i("TIMEDATE:","f2");
                return 1;
            }
            else if(curmonth == month){
                if(curday > day){
                    return -1;
                }
                else if(curday < day){
                    Log.i("TIMEDATE:","f3" + curday + "&" + day);
                    return 1;
                }
                else if(curday == day){
                    if(curhour > hour){
                        return -1;
                    }
                    else if(curhour < hour){
                        Log.i("TIMEDATE:","f4");
                        return 1;
                    }
                    else if(curhour == hour){
                        if(curminute > minute){
                            return -1;
                        }
                        else if(curminute < minute){
                            Log.i("TIMEDATE:","f5");
                            return 1;
                        }
                        else if(curminute == minute){
                            return 0;
                        }
                    }
                }
            }
        }
    //    compare = curyear + "/" + curmonth + "/" + curday + "-" + curhour + ":" + curminute + "\n" + year + "/" + month + "/" + day;
        return compare;
    }
    private void adapterFormat(String date){
        year = Integer.parseInt(date.split("-")[0].split("/")[0]);
        month = Integer.parseInt(date.split("-")[0].split("/")[1]);
        day = Integer.parseInt(date.split("-")[0].split("/")[2]);
        hour = Integer.parseInt(date.split("-")[1].split(":")[0]);
        minute = Integer.parseInt(date.split("-")[1].split(":")[1]);
    }

    public static String getNow(){
        String time = "";
        Calendar instance = Calendar.getInstance();
        int curyear = instance.get(Calendar.YEAR);
        int curmonth = instance.get(Calendar.MONTH)+1;
        int curday = instance.get(Calendar.DATE);
        int curhour = instance.get(Calendar.HOUR_OF_DAY);
        int curminute = instance.get(Calendar.MINUTE);
        time = addzero(curyear) + "/" + addzero(curmonth) + "/" + addzero(curday) + "-" + addzero(curhour) + ":" + addzero(curminute);
        return time;
    }

    public static String addzero(int s){
        String output = "";
        try{
            if(s < 10){
                output = "0" + s;
            }else{
                output = "" + s;
            }
        }catch (Exception e){

        }
        return output;
    }

    public static String getDeadline(String date){
        Bundle b = getValues(date);
        int year = b.getInt("year");
        int month = b.getInt("month");
        int day = b.getInt("day");
        int hour = b.getInt("hour");
        int minute = b.getInt("minute");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DATE,day);
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.MINUTE,minute);
        cal.set(Calendar.SECOND,0);

        Calendar instance = Calendar.getInstance();
        int curyear = instance.get(Calendar.YEAR);
        int curmonth = instance.get(Calendar.MONTH)+1;
        int curday = instance.get(Calendar.DATE);
        int curhour = instance.get(Calendar.HOUR_OF_DAY);
        int curminute = instance.get(Calendar.MINUTE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,curyear);
        calendar.set(Calendar.MONTH,curmonth);
        calendar.set(Calendar.DATE,curday);
        calendar.set(Calendar.HOUR_OF_DAY,curhour);
        calendar.set(Calendar.MINUTE,curminute);

        Log.i("datetime now",calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE)
                + "-" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));

        long compare = cal.getTimeInMillis() - calendar.getTimeInMillis();

/*        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(compare);

        Log.i("datetime",((int)calendar2.get(Calendar.YEAR) - 1970) + "/" + calendar2.get(Calendar.MONTH) + "/" + ((int)calendar2.get(Calendar.DATE) - 1)
        + "-" + calendar2.get(Calendar.HOUR) + ":" + calendar2.get(Calendar.MINUTE) + "___" + compare);
*/
        int days = (int)( compare / (1000*60*60*24));
        int years = days / 365;
        int months = (days % 365)/12;
        int d = ((days % 365) % 12);
        Log.i("datetime",days + ":" + years + "/" + months + "/" + d);
        if((int)calendar.get(Calendar.YEAR) == 0){

        }
        return "";
    }

    public static Bundle getValues(String s){
        Bundle a = new Bundle();
        a.putInt("year",Integer.parseInt(s.split("-")[0].split("/")[0]));
        a.putInt("month",Integer.parseInt(s.split("-")[0].split("/")[1]));
        a.putInt("day",Integer.parseInt(s.split("-")[0].split("/")[2]));
        a.putInt("hour",Integer.parseInt(s.split("-")[1].split(":")[0]));
        a.putInt("minute",Integer.parseInt(s.split("-")[1].split(":")[1]));
        return a;
    }
}