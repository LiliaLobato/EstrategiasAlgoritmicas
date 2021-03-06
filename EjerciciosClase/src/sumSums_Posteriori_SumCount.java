
public class sumSums_Posteriori_SumCount {

	public static void main(String[] args) {
	    for (int n = 5000; n <= 640000; n*=2){
	        long start = System.currentTimeMillis();
    	    int[] array = randomArray (n, -10, 10);
    	    sumsSum(array); //ECUACION A ANALIZAR
    	    long end = System.currentTimeMillis();
    	    //System.out.printf("%d\t%d\n", n, end-start);
	    }
	}
	
	static int[] randomArray (int N, int min, int max){
	    int[] array = new int[N];
	    for(int i = 0; i <N; i++) array[i] = random(min,max);
	    return array;
	}
	
	static int random (int min, int max) {
	    return min + (int)((max-min+1)*Math.random());
	}
	
	//ECUACION A ANALIZAR
	static int sumsSum(int[] array){
	    int sumS=0;
	    long sumCount=0;
	    for(int m=0;m<array.length; m++){
	        int sumM =0;
	        for(int k=0; k<=m;k++) {
	            sumM += array[k];
	            sumCount ++;
	        }
	        sumS+=sumM;
	        sumCount ++;
	    }
	    System.out.printf("%d\t%d\n", array.length, sumCount);
	    return sumS;
	}

}
