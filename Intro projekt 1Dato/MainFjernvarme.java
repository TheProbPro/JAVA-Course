
public class MainFjernvarme
{
    public static void main(String[] args)
    {
        // aftester klasserne Forbruger og Distrikt
        Forbruger victor = new Forbruger("Victor", 1106, 10000);
        Forbruger christian = new Forbruger("Christian", 1107, 10000);
        Distrikt odense = new Distrikt ("Odense", 11.5);
        odense.setForbruger(victor);
        odense.setForbruger(christian);
        
        victor.aflaesMaaler(250);
        victor.aflaesMaaler(500);
        christian.aflaesMaaler(300);
        christian.aflaesMaaler(700);
        
        System.out.println("Victor's afmåling på "+ victor.getMaalerNr() + " Victors forbrug er på " + victor.beregnForbrug());
        System.out.println(odense.afregnForbrug(1106));
        
        
        
    }
}
