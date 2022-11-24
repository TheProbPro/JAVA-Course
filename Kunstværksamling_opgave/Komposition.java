
public class Komposition extends Kunstvaerk
{
   protected String genre;
   protected int spilletid;

   //Constructor
   public Komposition(String enT, int etA, String enG, int enS)
    {
        super(enT, etA);
        genre = enG;
        spilletid = enS;
    }
    
   //Metoder
    public String getGenre()
   {
        return genre;
   }
   
   public int getSpilletid()
   {
        return spilletid;
   }
}
