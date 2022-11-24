
public class Maleri extends Kunstvaerk
{
    protected int laengde;
    protected int bredde;
    protected Museum reference;
    
    //Constructor
    //Aggregerings eksempel
    public Maleri(String enT, int etA, int enL, int enB, Museum enR)
    {
        super(enT, etA);
        laengde = enL;
        bredde = enB;
        reference = enR;
    }
    
    //Metoder
     public Museum getMuseum()
    {
        return reference;
    }
    
    public int getLaengde()
    {
        return laengde;
    }
    
    public int getBredde()
    {
        return bredde;
    }
}
