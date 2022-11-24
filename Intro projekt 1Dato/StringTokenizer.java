
public class StringTokenizer
{
    private String tekst;
    private String delim;
    private int indeks;
    

  // Constructors (opgave 1) 
  public StringTokenizer(String source)
   {
        tekst = source;
        delim = "/n";
        indeks = 0;
    }
  public StringTokenizer(String source, String delimiters)
  {
        tekst = source;
        delim = delimiters;
        indeks = 0;
  }
  
  //Metoder
  public String getString()
  {
      return tekst;
  }
  
    //opgave 2 boolean er det char tegn?
  public boolean isDelimiter(char tegn)
  {
      if (delim.indexOf(tegn) == -1)
        return false;
      else
        return true;
  }
  
  // Oles løsning:
  //public boolean isDelimiter1(char tegn)
  //{
  //    for (int i = 0; i < delim.length(); i++)
  //      if (etTegn == delim.charAt(i))
  //          return true;
  //    return false;
  //  }
  
  //opgave 3 Tokenz
  //public boolean hasMoreTokenz()
  //{
  //    for (int i = 0; i < tekst.length(); i++)
  //      if (isDelimiter(tekst.charAt(i)) == true && !isDelimiter(tekst.charAt(i+1)))
  //          return true;
  //     return false;
  //  }
   
     //Oles løsning
     public boolean hasMoreTokens()
    {
        for (int i = indeks; i < tekst.length(); i++)
          if (!isDelimiter(tekst.charAt(i)))
            return true;
            return false;
    }
    
  //opgave 4 next token
  public String nextToken()
  {
      while (isDelimiter(tekst.charAt(indeks)) && indeks < tekst.length()-1)
        indeks++;
      int startOfToken = indeks;
      int endOfToken = -2;
      //returner " " for komma"
      for (int i = indeks; i < tekst.length(); i++){
       if (isDelimiter(tekst.charAt(i))){
           endOfToken = i;
           indeks = i;      
           String token = tekst.substring(startOfToken,endOfToken);
           return token;
       }
      }
      return "Error";
  }
  
  // Oles løsning:
  //public String nextToken()
  //{
      //String token = " ";
      //while (indeks < tekst.length() && isDelimiter(tekst.charAt(indeks)))
      //    indeks++;
      //
      //while (indeks < tekst.length() && !isDelimiter(tekst.charAt(indeks)))
      //{
          //token += tekst.charAt(indeks);
          //indeks++;
          //}
          //return token
  //}
  
  public String nextToken(String delimiters)
  {
      delim = delimiters;
      int startOfToken = indeks;
      int endOfToken = -2;
      
     
     for (int i = indeks; i < tekst.length(); i++){
            if (isDelimiter(tekst.charAt(i))){
                endOfToken = i;
                indeks = i + 1;
                
                String token = tekst.substring(startOfToken,endOfToken);
                return token;
            }
     }
     return "Error";
   }
   
  //Opgave 5 Count Tokenz
  //Call by value
  public int countTokenz()
  {
     // bruger nextToken() som udgangspunkt og skal med en tæller fortælle hvor mange gange man kan kalde funktionen endnu
        int i = 0;
        int oldIndeks = indeks;
        while (nextToken() != "Error")
            i++;
           indeks = oldIndeks;
         return i;
    }
    
  //public int countTokens()
  //{
  //      int antal = 0;
  //      int idx = indeks;
  //      
  //      while (hasMoreTokens())
  //      {
  //          nextToken();
  //          antal++;
  //      }
        
  //      indeks = idx;
  //      return antal;
  //  }
}
