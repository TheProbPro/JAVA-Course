    
public abstract class Kunstvaerk
{
    protected String title;
    protected int aar;
    protected Person[] ophavsmaend;
    protected int antalOphavsmand;
    
    //Constructor
    public Kunstvaerk(String enT, int etA)
    {
        title = enT;
        aar = etA;
        ophavsmaend = new Person[5];
        antalOphavsmand = 0;
    }
    
    //Metoder
    public void addOphavsmand(Person ophavsmand)
    {
        ophavsmaend[antalOphavsmand] = ophavsmand;
        antalOphavsmand = antalOphavsmand + 1;
    }
    
    public int getAntalOphavsmand()
    {
        return antalOphavsmand;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public int getAar()
    {
        return aar;
    }
    
    public Person[] getophavsmand()
    {
        return ophavsmaend;
    }
}
