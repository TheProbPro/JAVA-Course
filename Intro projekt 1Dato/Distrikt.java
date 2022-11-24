
public class Distrikt
{
    private String navn;
    private double prisPrM3;
    private Forbruger[] forbrugere;
    private int antalForbrugere;
    
    //Constructors
    public Distrikt()
    {
        
    }

    public Distrikt(String etNavn, double prisPrKubikmeter)
    {
        navn = etNavn;
        prisPrM3 = prisPrKubikmeter;
        forbrugere = new Forbruger[200];
        antalForbrugere = 0;
    }
    
    //Metoder
    public void setForbruger(Forbruger nyForbruger)
    {
        forbrugere[antalForbrugere] = nyForbruger;
        antalForbrugere = antalForbrugere + 1;
    }
    
    // Metoden skal beregne prisen for et årsforbrug for én forbruger. Metoden har forbrugerens målernummer med som inputparameter og skal returnere prisen. Hvis målernummeret ikke kan findes, returneres et negativt tal (-1).
    public double afregnForbrug(int maalerNr)
    {
        for (int i = 0; i < forbrugere.length; i++)
            if (maalerNr == forbrugere[i].getMaalerNr())           
                 return forbrugere[i].beregnForbrug() * prisPrM3;
        return -1;  
    }
 
}
