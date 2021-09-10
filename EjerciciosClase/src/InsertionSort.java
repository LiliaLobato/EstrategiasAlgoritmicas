
import java.util.Arrays;
import UsefullArray.ArrayMethods;
import UsefullArray.ArrayMethods.*;


public class InsertionSort {
	
	static Cost cost = Cost.comp;	//mov, comp
	
	static int cmps = 0; //Comparaciones
	static int movs = 0; //Movimientos
	
	static ArrayCase AC = ArrayCase.aleatorio; //aleatorio,ordenado,invetido 

	static boolean greaterThan(int x, int y) {
		if(cost==Cost.comp) {cmps++;}
		return x > y;
	}

	static void insertion(int[] array) {
		final int N = array.length;
		for (int p = 1; p < N; p++) {
			int pivot = array[p];
			if(cost==Cost.mov) {movs++;}
			int i = p - 1;
			while (i >= 0 && greaterThan(array[i], pivot)) {
				array[i + 1] = array[i];
				if(cost==Cost.mov) {movs++;}
				i--;
			}
			array[i + 1] = pivot;
			if(cost==Cost.mov) {movs++;}
		}
	}

	public static int[] createArray(int N, int min, int max) {
		int[] array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = min + (int) ((max - min + 1) * Math.random());
		}
		return array;
	}

	public static void main(String[] args) {
		//TEST
		int[] array = ArrayMethods.getArray(AC, 10, 1, 10);

		System.out.println(Arrays.toString(array));
		insertion(array);
		System.out.println(Arrays.toString(array));
		System.out.println(ArrayMethods.isSorted(array)+"\n");
		
		System.out.printf("N\tRESULT\n");
		for (int N = 1000; N<=5000; N=N+100) {
			int[] array2 = ArrayMethods.getArray(AC, N, 1, N);
			insertion(array2);
			if(cost==Cost.mov) {
				System.out.printf("%d\t%d\n", N, movs);
				movs = 0;
			} else {
				System.out.printf("%d\t%d\n", N, cmps);	
				cmps = 0;			
			}
		}
	}
}
