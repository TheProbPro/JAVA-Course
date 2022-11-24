
public class Dato
{
    
    private int Datoen; // Format: YYYYMMDD

    
    public Dato()
    {
    }
  
    public Dato(int enDato)
    {
        Datoen = enDato;
    }
    
    
    
    public int getDatoen()
    {
      return Datoen;
    }
    
    public int getAar() // + - * / %
    {
        return Datoen / 10000;
    }
    public int getMaanede()
    {
        return Datoen / 100 - getAar() * 100;
    }
    public int getMaanede1() //den metode vist i klassen
    {
        return (Datoen % 10000) / 100;
    }
    
    public int getDag()
    {
        return Datoen - (getAar() * 100 + getMaanede()) * 100;
    }
    public int getDag1() //den metode vist i klassen
    {
        return Datoen % 100;
    }
    
    public int getKvartal()
    { 
        return ((getMaanede() - 1) / 3) + 1;
    }
    public int getKvartal1() //den metode vist i klassen
    {
        int kvartal = 0;
        int maanede = getMaanede();
        
        if (maanede >= 1 && maanede <= 3)
            kvartal = 1;
        else
        if (maanede >= 4 && maanede <= 6)
            kvartal = 2;
        else
        if (maanede >= 7 && maanede <= 9)
            kvartal = 3;
        else
        if (maanede >= 10 && maanede <= 12)
            kvartal = 4;
        return kvartal;
    }
    
    public boolean getSkudAar()  //% giver rest
    {
        boolean skudAar = false;
        if (getAar() % 4 != 0)
            skudAar = false;
        else
        if (getAar() % 400 != 0 && getAar() % 100 == 0)
            skudAar = false;
        else
            skudAar = true;
        
        return skudAar;
    }
    
    public boolean validDato()
    {
        int dag = getDag();
        int maanede = getMaanede();
        int aar = getAar();
        boolean validDato = false;
        
        if (Datoen < 17000301)
            return false;
        if (maanede < 1 && maanede > 12)
            validDato = false;
        if (maanede == 1 && dag >= 1 && dag <= 31)
            validDato = true;
        if (maanede == 2 && getSkudAar() == true && dag >= 1 && dag <= 29)
            validDato = true;
        if (maanede == 2 && getSkudAar() == false && dag >= 1 && dag <= 28)
            validDato = true;
        if (maanede == 3 && dag >= 1 && dag <=31)
            validDato = true;
        if (maanede == 4 && dag >= 1 && dag <= 30)
            validDato = true;
        if (maanede == 5 && dag >= 1 && dag <= 31)
            validDato = true;
        if (maanede == 6 && dag >=1 && dag <= 30)
            validDato = true;
        if (maanede == 7 && dag >= 1 && dag <=31)
            validDato = true;
        if (maanede == 8 && dag >= 1 && dag <=31)
            validDato = true;
        if (maanede == 9 && dag >= 1 && dag <= 30)
            validDato = true;
        if (maanede == 10 && dag >= 1 && dag <= 31)
            validDato = true;
        if (maanede == 11 && dag >= 1 && dag <= 30)
            validDato = true;
        if (maanede == 12 && dag >= 1 && dag <= 31)
            validDato = true;
        
        return validDato;
    }
    
    public int dagIAar()
    {
        int dag = getDag();
        int maanede = getMaanede();
        int maanede1 = 31;
        int maanede2;
        if (getSkudAar() == false)
            maanede2 = 28;
        else
            maanede2 = 29;
        int maanede3 = 31;
        int maanede4 = 30;
        int maanede5 = 31;
        int maanede6 = 30;
        int maanede7 = 31;
        int maanede8 = 31;
        int maanede9 = 30;
        int maanede10 = 31;
        int maanede11 = 30;
        
        if (maanede == 1)
            return dag;
        if (maanede == 2)
            return maanede1 + dag;
        if (maanede == 3)
            return maanede1 + maanede2 + dag;
        if (maanede == 4)
            return maanede1 + maanede2 + maanede3 + dag;
        if (maanede == 5)
            return maanede1 + maanede2 + maanede3 + maanede4 + dag;
        if (maanede == 6)
            return maanede1 + maanede2 + maanede3 + maanede4 + maanede5 + dag;
        if (maanede == 7)
            return maanede1 + maanede2 + maanede3 + maanede4 + maanede5 + maanede6 + dag;
        if (maanede == 8)
            return maanede1 + maanede2 + maanede3 + maanede4 + maanede5 + maanede6 + maanede7 + dag;
        if (maanede == 9)
            return maanede1 + maanede2 + maanede3 + maanede4 + maanede5 + maanede6 + maanede7 + maanede8 + dag;
        if (maanede == 10)
            return maanede1 + maanede2 + maanede3 + maanede4 + maanede5 + maanede6 + maanede7 + maanede8 + maanede9 + dag;
        if (maanede == 11)
            return maanede1 + maanede2 + maanede3 + maanede4 + maanede5 + maanede6 + maanede7 + maanede8 + maanede9 + maanede10 + dag;
        if (maanede == 12)
            return maanede1 + maanede2 + maanede3 + maanede4 + maanede5 + maanede6 + maanede7 + maanede8 + maanede9 + maanede10 + maanede11 +dag;
        
        return dagIAar();
    }
    public int restDag()
    {    
        if (getSkudAar() == false)
         return 365 - dagIAar();
         else
         return 366 - dagIAar();
    }
    public void setDatoPlusEn()
    {
       Datoen++;
       while (validDato() == false)
        {
            Datoen++;
        }
    }
    public void setDatoMinusEn()
    {
      Datoen--;
      while (validDato() == false)
      {
          Datoen--;
      }
    }
    public void setNyDato(int dag)
    {
     if (dag > 0)
     {
        for (int i = 0; i < dag; i++)
        {
           setDatoPlusEn();
        }
     }
        else 
        if (dag < 0)
        {
            for (int i = 0; i > dag; i--)
        {
           setDatoMinusEn();
        }
     }
    }
    public int forskelIDage(int Dato) //format YYYYMMDD
    {
       int i = 0;
       int originalDato = Datoen; 
       if (Datoen < Dato)
       {
           while (Datoen < Dato)
         {
           setDatoPlusEn();
           i++;
         }
         Datoen = originalDato;
         return i;
       }
       else
       if (Datoen > Dato)
       {
             while (Datoen > Dato)
         {
           setDatoMinusEn();
           i--;
         }
         Datoen = originalDato;
         return i;
       }
       else
       {
           return 0;
       }
    }
    
    public int ugeDag()
    {
        // først sæt den første dato (17000301) til at være en mandag (1) også sæt forskel i dage fra den første dato til til Datoen og set en limit der går fra 1-7 og gør den tæller forfra
        // fra 1 når den går over 7
        int foersteDato = 17000301;
        int i = 1;
        int nuVaerendeDato = Datoen;
        Datoen = foersteDato;
        
       while (Datoen < nuVaerendeDato)
         {
           setDatoPlusEn();
           i++;
           
           if (i > 7)
            i = 1;
        }  
       
        Datoen = nuVaerendeDato;
        return i;
    }
}

