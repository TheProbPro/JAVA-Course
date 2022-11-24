
import java.util.Calendar;
public class CprNr
{
    private String nummeret; //10 cifre
    
    //Constructors
    public CprNr()
    {  
    }
    
    public CprNr(String etCprNr)
    {
        nummeret = etCprNr;
    }
    
    
    //Metoder
    public String getCprNr()
    {
        return nummeret;
    }
    
    public void setCprNr(String etCprNr)
    {
        nummeret = etCprNr;
    }
    
    public int getDag()
    {
        int dag = Integer.parseInt(nummeret.substring(0,2));
        return dag;
    }
    
    public int getMaanede()
    {
        int maanede = Integer.parseInt(nummeret.substring(2,4));
        return maanede;
    }
    
    public int getAar()
    {
        int Aar = Integer.parseInt(nummeret.substring(6,7));
        int Aarhundrede = 0;
        if (Aar <= 3)
            Aarhundrede = 1900;
        if (4 <= Aar && Aar <= 5)
            Aarhundrede = 1800;
        if (6 <= Aar && Aar <= 9)
            Aarhundrede = 2000;
        int Aar1 = Integer.parseInt(nummeret.substring(4,6));
        int Aarsum = Aarhundrede + Aar1;
        return Aarsum;
    }
    
    public Dato getDatoen()
    {
        int dag = getDag();
        int maanede = getMaanede();
        int aar = getAar();
        
        int fDato = aar * 10000 + maanede * 100 + dag;
        //For at lave et ny dato object
        Dato dato = new Dato(fDato);
        return dato;
    }
    //public Dato getDato()
    //{
    //    Dato d = new Dato(getAar(),getMaaned(),getDag());
    //    
    //    return d;
    //}
    public int getAlder()
    {
        Calendar c = Calendar.getInstance();
         
        int alder = c.get(c.YEAR) - getAar();
        if (getMaanede() >= c.get(c.MONTH))
            if (getDag() > c.get(c.DAY_OF_MONTH))
                alder--;
        
        return alder;
    }
    
    public boolean erMand()
    {
        int sidstecif = Integer.parseInt(nummeret.substring(9,10));
        if (sidstecif == 1 || sidstecif == 3 || sidstecif == 5 || sidstecif == 7 || sidstecif == 9)
            return true; 
        else
            return false;
    }
    public boolean erKvinde() //Doven løsning 
    {
        return !erMand();
    }
    
    // Smartere løsning
    // Public boolean erMand()
    //{
        //int sidstecif = Integer.parseInt(nummeret.substring(9,10));
        //return sidstecif % 2 == 1;
    //}
    
    
    public boolean erValid() //ikke lavet bare fyld
    {
        // tjek at CPR-nummeret ikke er længere end 10 cifre
        if (nummeret.length() != 10)
            return false;
        // tjek at alle cifrene er numeriske
        for (int i = 0; i < nummeret.length(); i++)
            if (nummeret.charAt(i) < 48 || nummeret.charAt(i) > 57)
                return false;
        // er fødselsdatoen valid
        if (getDatoen().validDato() == false)
            return false;
        //Modulus 11 tjek
        int value1 = nummeret.charAt(0) * 4;
        int value2 = nummeret.charAt(1) * 3;
        int value3 = nummeret.charAt(2) * 2;
        int value4 = nummeret.charAt(3) * 7;
        int value5 = nummeret.charAt(4) * 6;
        int value6 = nummeret.charAt(5) * 5;
        int value7 = nummeret.charAt(6) * 4;
        int value8 = nummeret.charAt(7) * 3;
        int value9 = nummeret.charAt(8) * 2;
        int value10 = nummeret.charAt(9) * 1;
        if ((value1 + value2 + value3 + value4 + value5 + value6 + value7 + value8 + value9 + value10) % 11 == 0)
            return false;
        else
            return true;
    }
}
