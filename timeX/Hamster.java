
public class Hamster extends Husdyr
{
    private Dato sidstBurRengjordt;

    public Hamster(String etN, String enL, Dato bR)
    {
        super(etN, enL);
        sidstBurRengjordt = bR;
    }
    
    public Dato getSidstRengjordt()
    {
        return sidstBurRengjordt;
    }

    //public String givLyd()
    //{
    //    return "Piv-piv";
    //}
}
