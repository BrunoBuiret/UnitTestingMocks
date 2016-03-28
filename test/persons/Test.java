/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Test
{
    public static void main(String[] args)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        GregorianCalendar birthDate = new GregorianCalendar(1994, 0, 21);
        List<GregorianCalendar> calendars = new ArrayList<>();
        calendars.add(new GregorianCalendar(1994, 0, 21));
        calendars.add(new GregorianCalendar(2016, 2, 26));
        calendars.add(new GregorianCalendar(1994, 0, 1));
        
        for(GregorianCalendar calendar : calendars)
        {
            System.out.println(String.format(
                "Is %s after %s?",
                dateFormat.format(calendar.getTime()),
                dateFormat.format(birthDate.getTime())
            ));
            System.out.println(String.format(
                "-> %s",
                calendar.compareTo(birthDate) >= 0 ? "Yes" : "No"
            ));
        }
    }
}
