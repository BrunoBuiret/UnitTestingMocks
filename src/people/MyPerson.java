package people;

import java.util.GregorianCalendar;
import persons.IPerson;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class MyPerson implements IPerson
{
    /**
     * A reference to the actual person.
     */
    protected Personne internalPerson;
    
    /**
     * Creates a new person.
     * 
     * @param lastName The person's last name.
     * @param firstName The person's first name.
     * @param year The person's year of birth.
     * @param month The person's month of birth.
     * @param day The person's day of birth.
     */
    public MyPerson(String lastName, String firstName, int year, int month, int day)
    {
        this.internalPerson = new Personne(lastName, firstName, year, month, day);
    }
    
    /**
     * Gets the person's last name.
     * 
     * @return The person's last name.
     */
    @Override
    public String getName()
    {
        return this.internalPerson.getName();
    }

    /**
     * Gets the person's first name.
     * 
     * @return The person's first name.
     */
    @Override
    public String getFirstName()
    {
        return this.internalPerson.getFirstName();
    }

    /**
     * Tests if a person was already born on <code>date</code>.
     * 
     * @param date The date to test.
     * @return <code>true</code> if the person was already born, <code>false</code> otherwise.
     * @todo Implement this method.
     */
    @Override
    public boolean wasBorn(GregorianCalendar date)
    {
        try
        {
            this.getAge(date);
            
            return true;
        }
        catch(IllegalArgumentException ex)
        {
            return false;
        }
    }

    /**
     * Computes the person's age on a given date from their birth date.
     * 
     * @param date The date of reference.
     * @return The person's age.
     */
    @Override
    public int getAge(GregorianCalendar date)
    {
        return this.internalPerson.getAge(date);
    }
}
