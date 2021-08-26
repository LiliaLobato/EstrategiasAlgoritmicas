
public class Main
{
    static int insertion_sort(int[] array){
	    final int n = array.length;
	    for (int p =1; p<n;p++){
	    	int pivot = array[p];
	    	int i = p-1;
	    	while(i>=0 & array[i]>pivot){
	    		array[i+1] = array[i];
	    		i--;
	    	}
	    	array[i+1] = pivot;
	    }
	}
	
	public static boolean isSorted(int[] array){
		for (int i =1; i<array.length; i++){
			if(array[i] < array[i-1]) return false;
		}
		return true;
	}
	
	public static int[] createArray(int N, int min, int max) {
		int[] array = new int[N];
		for(int i = 0; i < N; i ++) {
			array[i] = min + (int) ((max - min + 1) * Math.random());
		}
		return array;
	}
	
    public static void main(String[] args) {
        int[] array = {5, 3, 7, 2, 6, 1, 4};
        insertion_sort(array);
        //System.out.println(Arrays.toString(array));
        //System.out.pritnln(isSorted(array));
    }  
	
}