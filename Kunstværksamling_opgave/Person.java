
public class Person
{
    protected String navn;
    protected String land;
    protected int foedselsaar;
    protected int doedsaar;
    
    //Constructor
    public Person(String etN, String etL, int enFA, int enDA)
    {
        navn = etN;
        land = etL;
        foedselsaar = enFA;
        doedsaar = enDA;
    }
    
    //Metoder
    public String getNavn()
    {
        return navn;
    }
    
    public String getNationalitet()
    {
        return land;
    }
    
    public int getFoedselsaar()
    {
        return foedselsaar;
    }
    
    public int getDoedsaar()
    {
        return doedsaar;
    }
}
