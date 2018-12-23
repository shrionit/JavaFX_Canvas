/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author shripc
 */
public class Time {
    
    private String formattedDate;
    private static String s[];
    
    public Time() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        formattedDate = dateFormat.format(date);
        s = formattedDate.split(":");
    }
    
    public int getHour(){
        int h = Integer.parseInt(s[0]);
        return h;
    }
    
    public int getMinute(){
        int h = Integer.parseInt(s[1]);
        return h;
    }
    
    public int getSecond(){
        String ss[] = s[2].split(" ");
        int h = Integer.parseInt(ss[0]);
        return h;
    }
    
}
