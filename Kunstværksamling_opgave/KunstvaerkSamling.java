import java.util.ArrayList;
public class KunstvaerkSamling
{
    protected ArrayList<Kunstvaerk> kunstvaerk;
    
    //Constructor
    public KunstvaerkSamling()
    {
        kunstvaerk = new ArrayList<>();
    }
    
    //Metoder
    //Associerings eksempel
     public void add(Kunstvaerk maleri)
    {
        kunstvaerk.add(maleri);
    }
    
     public void findSangeMedFlereKomponister()
    {
        for (int i = 0; i < kunstvaerk.size(); i++)
        {
            if (kunstvaerk.get(i) instanceof Komposition && kunstvaerk.get(i).getAntalOphavsmand() > 1)
                System.out.println(kunstvaerk.get(i).getTitle());
        }
    }
    
    public void findMalerierPaaMuseum(String museum)
    {
        for (int i = 0; i < kunstvaerk.size(); i++)
        {
            if (kunstvaerk.get(i) instanceof Maleri && ((Maleri)kunstvaerk.get(i)).getMuseum().getNavn() == museum)
                System.out.println(kunstvaerk.get(i).getTitle());
        }
    }
    
    public void beregnAntalSiderLaest()
    {
        int sumAntalSider = 0;
        for (int i = 0; i < kunstvaerk.size(); i++)
        {
            if (kunstvaerk.get(i) instanceof Bog)
            {
                sumAntalSider = ((Bog)kunstvaerk.get(i)).getAntalSider() + sumAntalSider;
            }   
        } 
        System.out.println(sumAntalSider);
    }
}
