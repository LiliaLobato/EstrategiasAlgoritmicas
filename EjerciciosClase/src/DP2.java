
public class DP2 {

/*
    C -> 0  1  2  3  4  5  6  7  8  9
    1 |  0  1  2  3  4  5  6  7  8  9
    4 |  0  1  2  3  1  2  y  y  y  y
    6 |  0  1  2  3  1  2  y  y  y  y
*/

  static int count = 0;

  private static int changeRec(int C, int[] D, int d) {
    if(C == 6 && D[d] == 4) count ++;
    if(C <= 0) return 0;
    if(d == 0) return C;
    
    // d = 1, D[d] = 4, C = 3
    if(D[d] > C) return changeRec(C, D, d - 1);
    
    // d = 2, D[d] = 6, C = 9
    // ¿Vale la pena usar la denominación? Intentémoslo
    int res1 = 1 + changeRec(C - D[d], D, d);   // Se usa la denominación 
    int res2 = changeRec(C, D, d - 1);          // No se usa la denominación
    return Math.min(res1, res2);
  }

  public static int changeRec(int C, int[] D) {
    return changeRec(C, D, D.length - 1);
  }



  private static int changeTD(int C, int[] D, int d, int[][] cache) {
    if(C == 6 && D[d] == 4) count ++;
    if(C <= 0) return 0;
    if(d == 0) return C;
    if(cache [d][C] > 0) return cache [d][C];
    int res2 = changeTD(C, D, d - 1, cache); 
    if(D[d] > C) {
      cache[d][C] = res2;
    } else {
      int res1 = 1 + changeTD(C - D[d], D, d, cache);
      cache[d][C] = Math.min(res1, res2);
    }
    return cache [d][C];
  }

  public static int changeTD(int C, int[] D) {
    int[][] cache = new int[D.length][C + 1];
    return changeTD(C, D, D.length - 1, cache);
  }


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
      }
    }
    return cache[D.length - 1][C];
  }

  ///////////////////////////////////////////////////////////////////////////////////
  
  public static void backpackBU(int W[], int V[], int M) {
		int n = V.length;
		int B[][] = new int[n + 1][M + 1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= M; j++) {
				B[i][j] = 0;
				B[i][j] = B[i - 1][j];
				if ((j >= W[i-1]) && (B[i][j] < B[i - 1][j - W[i - 1]] + V[i - 1])) {
					B[i][j] = B[i - 1][j - W[i - 1]] + V[i - 1]; 
				}
				
				System.out.print(String.format("%02d", B[i][j]) + " ");
			}
			System.out.print("\n");
		}
		System.out.println("Max Value:\t" + B[n][M]);
	}

  public static void main(String[] args) {
    int[] D = {1, 4, 6, 9, 12, 16, 19, 24};
		System.out.println(changeRec(300, D));
    System.out.println("Llamadas: " + count);
    count = 0;
    System.out.println(changeTD(300, D));
    System.out.println("Llamadas: " + count);
    /*
      C -> 0  1  2  3  4  5  6  7  8  9
      1 |  0  1  0  3  0  5  0  0  0  9
      4 |  0  1  0  3  0  2  0  0  0  3
      6 |  0  0  0  3  0  0  0  0  0  3
    */

    System.out.println(changeBU(300, D));
    ////////////////////////////////////////////////////
    
	//Wights
	int W[] = new int[]{1, 2, 5, 6, 7};
	//Values
	int V[] = new int[]{1, 6, 18, 22, 28};
	//Max Weight
	int M = 11;

	backpackBU(W, V, M);
	
  } 

}
