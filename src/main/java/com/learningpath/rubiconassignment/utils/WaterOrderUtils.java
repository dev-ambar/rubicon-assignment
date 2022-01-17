package com.learningpath.rubiconassignment.utils;

import com.learningpath.rubiconassignment.model.WaterOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class WaterOrderUtils {

    public  static  String calculateOrderStatus(WaterOrder wo){
        String currentStatus = null;
        String startDateStr = wo.getStartDateTime();;
        int duration = wo.getDuration();


        SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = dtf.format(LocalDateTime.now());


        try {
            Date startDate = sdf.parse(startDateStr);
            Date currentDate = sdf.parse(currentDateTime);

            long difference_In_Time = currentDate.getTime() - startDate.getTime();

            //long difference_In_Seconds = (difference_In_Time/ 1000)% 60;

            long difference_In_Minutes= (difference_In_Time/ (1000 * 60)) % 60;

            long difference_In_Hours= (difference_In_Time/ (1000 * 60 * 60))% 24;

            long difference_In_Years= (difference_In_Time/ (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days= (difference_In_Time/ (1000 * 60 * 60 * 24))% 365;

            if(difference_In_Years < 0 || difference_In_Days < 0 || difference_In_Hours < 0 || difference_In_Minutes< 0)
                currentStatus = "requested";
            else if(difference_In_Years > 0 || difference_In_Days > 0)
                currentStatus = "Delivered";
            else if((difference_In_Hours >=0  && difference_In_Hours <=duration) ||(difference_In_Hours < 0  && difference_In_Minutes >=0 && difference_In_Minutes <=59 ))
                currentStatus = "InProgress";
            else if(difference_In_Hours > duration)
                currentStatus = "Delivered";

        }

        catch (ParseException e) {
            e.printStackTrace();
        }

        return currentStatus;
    }

    public static Boolean isOverlapped(WaterOrder cwo, WaterOrder ewo) {
        
        Boolean isTrue = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date cd = sdf.parse(cwo.getStartDateTime());
            Date ed = sdf.parse(ewo.getStartDateTime());
            long difference_In_Time = cd.getTime() - ed.getTime();

            long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

            long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

            long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
            if(difference_In_Time >=0) {
                if (difference_In_Years == 0 && difference_In_Days == 0 && difference_In_Hours == 0 && difference_In_Minutes == 0)
                    isTrue = true;
                else if (difference_In_Years >= 1 || difference_In_Days >= 1)
                    isTrue = false;
                else if ((difference_In_Hours > ewo.getDuration()) || (difference_In_Hours == ewo.getDuration() && difference_In_Minutes > 0 && difference_In_Minutes <= 59))
                    isTrue = false;
                else if ((difference_In_Hours < ewo.getDuration()) || (difference_In_Hours == ewo.getDuration() && difference_In_Minutes == 0))
                    return true;
            }
            else
            {
                difference_In_Minutes = difference_In_Minutes *-1;
                difference_In_Hours = difference_In_Hours *-1;
                difference_In_Years = difference_In_Years * -1 ;

                    if (difference_In_Years >= 1 || difference_In_Days >= 1)
                        isTrue = false;
                    else if ((difference_In_Hours > cwo.getDuration()) || (difference_In_Hours == cwo.getDuration() && difference_In_Minutes > 0 && difference_In_Minutes <= 59))
                        isTrue = false;
                    else if ((difference_In_Hours < cwo.getDuration()) || (difference_In_Hours == cwo.getDuration() && difference_In_Minutes == 0)) return true;

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isTrue;
    }
    public static Boolean isPastDateTime(WaterOrder wo)
    {
        String startDateStr = wo.getStartDateTime();;
        int duration = wo.getDuration();
         Boolean result = false;

        SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = dtf.format(LocalDateTime.now());


        try {
            Date startDate = sdf.parse(startDateStr);
            Date currentDate = sdf.parse(currentDateTime);

            long difference_In_Time = currentDate.getTime() - startDate.getTime();
            if(difference_In_Time > 0)
                result =  true;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return result ;
    }
    
}
