package people;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import persons.IPerson;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class PersonUtils
{
    public List<IPerson> filterByAge(List<IPerson> persons, GregorianCalendar date, int from, int to)
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
}
