
public class Main
{

    public static void Main(String[] args)
    {
        Husdyr[] husdyr = new Husdyr[3];
        
        Kat miver = new Kat("Miver", "Mus");
        
        Dato msk = new Dato(20201022);
        Hund mimse = new Hund("Mimi", "Kødben", msk);
        
        Dato sBR = new Dato(20200911);
        Hamster jerry = new Hamster("Jerry", "Ost", sBR);
        
        husdyr[0] = miver;
        husdyr[1] = mimse;
        husdyr[2] = jerry;
        
        for (int i = 0; i < husdyr.length; i++)
            System.out.println(husdyr[i].getNavn() + " " + husdyr[i].givLyd());
    }

}