package persons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import people.EfficientPerson;
import people.MyPerson;
import people.OneMorePerson;
import people.SimplePerson;
import people.SmallCodePerson;
import people.YetAnotherPerson;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
@RunWith(Parameterized.class)
public class TestPerson
{
    /**
     * The first parameter, a reference to a person.
     */
    @Parameter(0)
    public IPerson personParameter;

    /**
     * The second parameter, a reference to a calendar
     */
    @Parameter(1)
    public GregorianCalendar calendarParameter;
    
    /**
     * The third parameter, a boolean indicating if the tests are supposed to succeed or not.
     */
    @Parameter(2)
    public boolean supposedToSucceedParameter;
    
    /**
     * The fourth parameter, an integer representing the wanted age.
     */
    @Parameter(3)
    public int ageParameter;

    /**
     *
     */
    protected final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates the parameters for the tests.
     * 
     * @return The parameters.
     */
    @Parameters
    public static Iterable<Object[]> createParameters()
    {
        // Initialize vars
        List<Object[]> testValues = new ArrayList<>();
        List<IPerson> persons = new ArrayList<>();
        List<GregorianCalendar> calendars = new ArrayList<>();
        
        // Build persons
        GregorianCalendar birthDate = new GregorianCalendar(1994, 0, 21);
        persons.add(new Person("Buiret", "Bruno", 1994, 1, 21));
        persons.add(new SimplePerson("Buiret", "Bruno", 1994, 1, 21));
        persons.add(new SmallCodePerson("Buiret", "Bruno", 1994, 1, 21));
        persons.add(new YetAnotherPerson("Buiret", "Bruno", 1994, 1, 21));
        persons.add(new OneMorePerson("Buiret", "Bruno", 1994, 1, 21));
        persons.add(new EfficientPerson("Buiret", "Bruno", 22));
        persons.add(new MyPerson("Buiret", "Bruno", 1994, 1, 21));
        
        // Build calendars
        calendars.add(new GregorianCalendar(1994, 0, 1)); // Birth date ~ Beggining of month
        calendars.add(new GregorianCalendar(1994, 0, 20)); // Birth date - 1 day
        calendars.add(birthDate); // Birth date
        calendars.add(new GregorianCalendar(1994, 0, 22)); // Birth date + 1 day
        calendars.add(new GregorianCalendar(1994, 1, 21)); // Birth date + 1 month
        calendars.add(new GregorianCalendar(1995, 0, 21)); // Birth date + 1 year
        calendars.add(new GregorianCalendar(2016, 2, 26)); // Now
        
        // Build tests
        persons.stream().forEach((person) ->
        {
            calendars.stream().forEach((calendar) ->
            {
                testValues.add(new Object[]{
                    person,
                    calendar,
                    calendar.compareTo(birthDate) >= 0,
                    calendar.compareTo(birthDate) < 0
                        ? calendar.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR) - 1
                        : calendar.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
                });
            });
        });

        return testValues;
    }

    /**
     * Runs tests on method <code>IPerson.wasBorn()</code>.
     *
     * @see persons.IPerson#wasBorn(java.util.GregorianCalendar)
     */
    @Test
    public void testWasBorn()
    {
        if(this.supposedToSucceedParameter)
        {
            Assert.assertTrue(
                String.format(
                    "%s.wasBorn(\"%s\") is supposed to be true.",
                    this.personParameter.getClass().getSimpleName(),
                    this.dateFormat.format(this.calendarParameter.getTime())
                ),
                this.personParameter.wasBorn(this.calendarParameter)
            );
        }
        else
        {
            Assert.assertFalse(
                String.format(
                    "%s.wasBorn(\"%s\") is supposed to be false.",
                    this.personParameter.getClass().getSimpleName(),
                    this.dateFormat.format(this.calendarParameter.getTime())
                ),
                this.personParameter.wasBorn(this.calendarParameter)
            );
        }
    }

    /**
     * Runs tests on method <code>IPerson.getAge()</code>.
     *
     * @see persons.IPerson#getAge(java.util.GregorianCalendar)
     */
    @Test
    public void testGetAge()
    {
        try
        {
            Assert.assertSame(
                String.format(
                    "%s.getAge(\"%s\") was supposed to produce %d",
                    this.personParameter.getClass().getSimpleName(),
                    this.dateFormat.format(this.calendarParameter.getTime()),
                    this.ageParameter
                ),
                this.personParameter.getAge(this.calendarParameter),
                this.ageParameter
            );
        }
        catch(IllegalArgumentException ex)
        {
            if(this.supposedToSucceedParameter)
            {
                AssertionFailedError error = new AssertionFailedError(String.format(
                    "The %s wasn't born yet on %s.",
                    this.personParameter.getClass().getSimpleName(),
                    this.dateFormat.format(this.calendarParameter.getTime())
                ));
                error.addSuppressed(ex);

                throw error;
            }
        }
    }
}
