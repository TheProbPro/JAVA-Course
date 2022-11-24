
public class Bog extends Kunstvaerk
{
    protected String genre;
    protected int antalSider;
    
    //Constuctor
    public Bog(String enT, int etA, String enG, int aS)
    {
        super(enT, etA);
        genre = enG;
        antalSider = aS;
    }
    
    //Metoder
    public String getGenre()
    {
        return genre;
    }
    
    public int getAntalSider()
    {
        return antalSider;
    }
}
