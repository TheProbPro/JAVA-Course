
public class Museum
{
    protected String navn;
    protected String addresse;
    //Construtor
    public Museum(String etN, String enA)
    {
        navn = etN;
        addresse = enA;
    }
    
    //Metoder
    public String getNavn()
    {
        return navn;
    }
    
    public String getAdresse()
    {
        return addresse;
    }
}
