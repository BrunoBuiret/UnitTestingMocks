package persons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class TestPersonUtils
{
    /**
     * 
     */
    protected List<IPerson> persons = new ArrayList<>();
    
    /**
     * Fills the list of persons.
     */
    @Before
    public void setUp()
    {
        // Initialize vars
        IPerson person;
        
        // Fill the list using Mockito
        this.persons.clear();
        
        person = Mockito.mock(Person.class, "Lambda #1");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(22);
        this.persons.add(person);
        
        person = Mockito.mock(Person.class, "Lambda #2");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(25);
        this.persons.add(person);
        
        person = Mockito.mock(Person.class, "Lambda #3");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(21);
        this.persons.add(person);
        
        person = Mockito.mock(Person.class, "Lambda #4");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(19);
        this.persons.add(person);
        
        person = Mockito.mock(Person.class, "Lambda #5");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(35);
        this.persons.add(person);
        
        person = Mockito.mock(Person.class, "Lambda #6");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(17);
        this.persons.add(person);
        
        person = Mockito.mock(Person.class, "Lambda #7");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(18);
        this.persons.add(person);
        
        person = Mockito.mock(Person.class, "Lambda #8");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(24);
        this.persons.add(person);
        
        person = Mockito.mock(Person.class, "Lambda #9");
        Mockito.when(person.getAge(Mockito.any(GregorianCalendar.class))).thenReturn(26);
        this.persons.add(person);
    }
    
    /**
     * Unit test for {@link persons.PersonUtils#filterByAge(java.util.List, java.util.GregorianCalendar, int, int) persons.PersonUtils.filterByAge()}.
     * 
     * @see http://stackoverflow.com/questions/12495420/how-to-junit-test-that-two-liste-contain-the-same-elements-in-the-same-order
     */
    @Test
    public void testFilterByAge()
    {
        // Initialize vars
        List<IPerson> expectedList = Arrays.asList(
            this.persons.get(0),
            this.persons.get(1),
            this.persons.get(2),
            this.persons.get(3),
            this.persons.get(6),
            this.persons.get(7)
        );
        
        // Run assertion
        Assert.assertEquals(
            "The lists should have been equal.",
            expectedList,
            PersonUtils.filterByAge(this.persons, new GregorianCalendar(2016, 3, 20), 18, 25)
        );
    }
    
    /**
     * Unit test for {@link persons.PersonUtils#filterByAge(java.util.List, java.util.GregorianCalendar, int, int) persons.PersonUtils.filterByAge()}.
     * 
     * @see http://stackoverflow.com/questions/12495420/how-to-junit-test-that-two-liste-contain-the-same-elements-in-the-same-order
     */
    @Test
    public void testFilterByAgeOutOfBounds()
    {
        // Initialize vars
        List<IPerson> emptyList = new ArrayList<>();
            
        // Run assertion
        Assert.assertEquals(
            "The list should have been empty.",
            emptyList,
            PersonUtils.filterByAge(this.persons, new GregorianCalendar(2016, 3, 20), 100, 101)
        );
    }
    
    /**
     * Unit test for {@link persons.PersonUtils#getGreatestAge(java.util.List, java.util.GregorianCalendar) persons.PersonUtils.getGreatestAge()}.
     */
    @Test
    public void testGetGreatestAge()
    {
        Assert.assertEquals(
            "Greatest age should have been 35.",
            35,
            PersonUtils.getGreatestAge(
                this.persons,
                new GregorianCalendar(2016, 3, 20)
            )
        );
    }
    
    /**
     * Unit test for {@link persons.PersonUtils#getGreatestAge(java.util.List, java.util.GregorianCalendar) persons.PersonUtils.getGreatestAge()}.
     */
    @Test
    public void testGetGreatestAgeWithEmptyList()
    {
        Assert.assertEquals(
            "The value should have been -1.",
            -1,
            PersonUtils.getGreatestAge(new ArrayList<>(), new GregorianCalendar(2016, 3, 20))
        );
    }
    
    /**
     * Tests independency and exploitation of
     * {@link persons.PersonUtils#getGreatestAge(java.util.List, java.util.GregorianCalendar) persons.PersonUtils.getGreatestAge()}.
     */
    @Test
    public void testGetGreatestAgeExploitation()
    {
        // Initialize vars
        GregorianCalendar testCalendar = new GregorianCalendar(2016, 3, 20);
        
        // Call the method to test
        PersonUtils.getGreatestAge(this.persons, testCalendar);
        
        // Verify independency and exploitation of data
        this.persons.stream().forEach((person) ->
        {
            // Method PersonUtils.getGreatestAge() shouldn't read the person's first name
            // or the person's name
            Mockito.verify(person, Mockito.never()).getFirstName();
            Mockito.verify(person, Mockito.never()).getName();

            // Method PersonUtils.getGreatestAge() should read the person's age
            // at least once
            Mockito.verify(person, Mockito.atLeastOnce()).getAge(Mockito.any(GregorianCalendar.class));
        });
    }
}
