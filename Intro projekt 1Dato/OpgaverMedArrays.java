
public class OpgaverMedArrays
{
    
    
    public OpgaverMedArrays()
    {
    }

    
    public void printStringArray()
    {
        String[] myStrings = new String[6];
        
        myStrings[0] = "this";
        myStrings[1] = "is";
        myStrings[2] = "Andy";
        myStrings[3] = "Warhol";
        myStrings[4] = "take";
        myStrings[5] = "one";
        
        for (int i = 0; i < myStrings.length; i++)
            System.out.println(myStrings[i]);
    }
    
    public static void printIntegerArray(int[] myInts)
    {
        for (int i = 0; i < myInts.length; i++)
            System.out.println(myInts[i]);
    }
    
    public int sumOfArray(int[] myInts)
    {
        // returnerer summen af elementerne i arrayet
        int sum = 0;
        //for (int i = 0; i < myInts.length; i++)
        //    sum += myInts[i];
        
         for (int value : myInts) {
             sum += value;
        }
         
        return sum;
    }
    
    public int averageOfArray(int[] myInts)
    {
        // returnerer gennemsnitsværdien (udregnet ved heltalsdivision)
        //int sum = 0;
        // for (int value : myInts) {
        //     sum += value;
        //}
        return sumOfArray(myInts) / myInts.length;
    }
    
    public int maxOfArray(int[] myInts)
    {
        // returnerer den største værdi i arrayet
        int max = myInts[0];
        for (int i = 0; i < myInts.length; i++)
            if (max < myInts[i])
                max = myInts[i];
        return max;
    }
    
    public static void sortArray(int[] myInts)
    {
        // sorterer arrayet og skriver det ud
        int temp = 0;
        for (int i = 0; i < myInts.length; i++)
            for (int j = i+1; j < myInts.length; j++)
                if (myInts[i] > myInts[j]){
                    temp = myInts[i];
                    myInts[i] = myInts[j];
                    myInts[j] = temp;
                }
    }
        
    public static void addMatrices(int[][] a, int[][] b)
    {
        // adder to matricer af samme størrelse og udskriv resultatet
        // tjek efterfølgende metode printMatrix for inspiration
      int c[][]=new int[a.length][b[0].length];

      for(int i = 0;i < a.length;i++){
         for(int j = 0;j < b[0].length;j++){
            c[i][j] = a[i][j]+b[i][j];
            System.out.print(c[i][j]+" ");
         }
         System.out.println();
        }
      
    }
    
    public static void printMatrix(int[][] m)
    {
         for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                System.out. print(m[i][j] + " ");   
         System.out.println("\nLength "+m.length+" Width "+m[0].length);
    }
    
    public static void main(String[] args)
    {
        int[] myInts = {17,12,89,2,43,67,11};
        
        OpgaverMedArrays o = new OpgaverMedArrays();
        
        System.out.println("sum of array " + o.sumOfArray(myInts));
        System.out.println("average of array " + o.averageOfArray(myInts));
        System.out.println("max value of array " + o.maxOfArray(myInts));
        System.out.println("\nArray sorted in ascending order: ");    
        for (int i = 0; i <myInts.length; i++) {     
            System.out.print(myInts[i] + " "); 
         }
        
         
         
         
        printIntegerArray(myInts);
        
        int[][] filter = {
            {-1, 0, 7, 4},
            {-2, 1, 8, 5},
            {-3, 2, 9, 6}
        };
        printMatrix(filter);
       
    }

}
