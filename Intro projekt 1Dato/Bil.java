
public class Bil
{
    private String regNr;
    private int aargang;

    public Bil(String etR, int enA)
    {
        regNr = etR;
        aargang = enA;
    }

    public String getRegNr()
    {
        return regNr;
    }
    
    public int getAargang()
    {
        return aargang;
    }
}
