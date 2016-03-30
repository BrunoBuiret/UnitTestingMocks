package persons;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public abstract class PersonUtils
{
    /**
     * Filters a list to keep only those whose age is between <code>from</code>
     * and <code>to</code> at a given date.
     * 
     * @param persons The list of persons to filter.
     * @param date The date to use as a point of reference.
     * @param from The lower bound of age.
     * @param to The higher bound of age.
     * @return The filtered list of persons.
     * @throws IllegalArgumentException If <code>from</code> is greater than <code>to</code>.
     */
    public static List<IPerson> filterByAge(List<IPerson> persons, GregorianCalendar date, int from, int to)
    throws IllegalArgumentException
    {
        // Perform some checks
        if(from > to)
        {
            throw new IllegalArgumentException("Invalid bounds, from is supposed to be lower than to.");
        }
        
        // Initialize vars
        List<IPerson> filteredList = new ArrayList<>();
        
        // Filter the list of persons
        persons.stream().forEach((person) ->
        {
            int age = person.getAge(date);
            
            if(from <= age && age <= to)
            {
                filteredList.add(person);
            }
        });
        
        return filteredList;
    }
    
    /**
     * Gets the age of the oldest person in the list at a given date.
     * 
     * @param persons The list of persons to go through.
     * @param date The date to use as a point of reference.
     * @return The greatest age, or 0 by default.
     */
    public int getGreatestAge(List<IPerson> persons, GregorianCalendar date)
    {
        int greatestAge = 0;
        int age;
        
        for(IPerson person : persons)
        {
            age = person.getAge(date);
            
            if(age > greatestAge)
            {
                greatestAge = age;
            }
        }
        
        return greatestAge;
    }
}
