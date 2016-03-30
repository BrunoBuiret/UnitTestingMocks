package persons;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.Mockito;
import org.hamcrest.Matchers;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class TestPersonUtils
{
    /**
     * 
     */
    @Rule
    public ErrorCollector collector = new ErrorCollector();
    
    /**
     * Fills the list of persons.
     */
    @Before
    public void setUp()
    {
        // Initialize vars
        this.persons = new ArrayList<>();
        Person person;
        
        // Fill the list using Mockito
        
    }
    
    /**
     * Tests {@link persons.PersonUtils#filterByAge(java.util.List, java.util.GregorianCalendar, int, int) persons.PersonUtils.filterByAge()}.
     */
    @Test
    public void testFilterByAge()
    {
        // Initialize list
        List<IPerson> persons = new ArrayList<>();
        IPerson person;
        
        person = Mockito.mock(Person.class, "Bruno Buiret");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(22);
        persons.add(person);
        
        person = Mockito.mock(Person.class, "Thomas Arnaud");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(22);
        persons.add(person);
        
        person = Mockito.mock(Person.class, "Alexis Rabilloud");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(21);
        persons.add(person);
        person = Mockito.mock(Person.class, "Lambda #1");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(19);
        persons.add(person);
        
        person = Mockito.mock(Person.class, "Lambda #2");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(35);
        persons.add(person);
        
        person = Mockito.mock(Person.class, "Lambda #3");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(17);
        persons.add(person);
                
        // Run tests
        collector.checkThat(
            PersonUtils.filterByAge(
                persons,
                new GregorianCalendar(2016, 2, 30),
                0,
                0),
            matcher
        );
    }
    
    @Test
    public void testGetGreatestAge()
    {
        
    }
}
