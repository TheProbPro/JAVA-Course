
public class Forbruger
{
    private String navn;
    private int maalerNr;
    private int nyAflaesning;
    private int forrigeAflaesning;
    private int maalerMax;
    
    //Constructors
    public Forbruger()
    {
        
    }

    public Forbruger(String forbrugerNavn, int etMaalerNr, int etMaalerMax)
    {
        navn = forbrugerNavn;
        maalerNr = etMaalerNr;
        maalerMax = etMaalerMax;
    }
    
    //Metoder
    public int getMaalerNr()
    {
        return maalerNr;
    }
    
    public void setNavn(String forbrugerNavn)
    {
        navn = forbrugerNavn;
    }
    
    public String getNavn()
    {
        return navn;
    }
    
    public void aflaesMaaler(int aflaesning)
    {
        forrigeAflaesning = nyAflaesning;
        nyAflaesning = aflaesning;
        //måske if statement der gør at den ikke kan gå over måler max
    }
    
    public int getForrigeAflaesning()
    {
        return forrigeAflaesning;
    }
    
    public int beregnForbrug()
    {
        if (nyAflaesning < forrigeAflaesning)
            return nyAflaesning + maalerMax - forrigeAflaesning;
        else
            return nyAflaesning - forrigeAflaesning;
    }
}
