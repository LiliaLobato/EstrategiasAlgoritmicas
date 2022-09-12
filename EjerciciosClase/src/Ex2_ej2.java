
public class Ex2_ej2 {

/*
    C -> 0  1  2  3  4  5  6  7  8  9
    1 |  0  1  2  3  4  5  6  7  8  9
    4 |  0  1  2  3  1  2  y  y  y  y
    6 |  0  1  2  3  1  2  y  y  y  y
*/

  static int count = 0;


  public static int changeBU(int C, int[] D) {
    int[][] cache = new int[D.length][C + 1];
    for(int d = 0; d < D.length; d ++) {
      for(int c = 0; c <= C; c ++) {
        if(C <= 0) cache[d][c] = 0;
        else if(d == 0) cache[d][c] = c;
        else if(D[d] > c) cache[d][c]= cache[d-1][c];
        else {
          int res1 = 1 + cache[d][c-D[d]];   // Se usa la denominación 
          int res2 = cache[d-1][c];          // No se usa la denominación
          cache[d][c] = Math.min(res1, res2);
        }
		System.out.print(String.format("%02d", cache[d][c]) + " ");
      }
		System.out.print("\n");
    }
    

    for(int d = D.length -1; d > 0; d --) {
      for(int c = C; c >0; c --) {
    	  if(cache[d][c] == cache [d-1][c]) d--;
    	  else {
    		 System.out.print(cache[d][c]);
  			System.out.print("\n");
  			c--;
    	  }
      }
    }
    
    return cache[D.length - 1][C];
  }
   

  public static void main(String[] args) {
    int[] D = {1, 2, 5, 10, 20, 50, 100, 200, 500};
    
    System.out.println(changeBU(44, D));   
    
    
    
    
  } 

}
