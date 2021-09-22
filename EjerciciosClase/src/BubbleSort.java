import java.util.Arrays;
import UsefullArray.ArrayMethods;
import UsefullArray.ArrayMethods.*;

public class BubbleSort {

	static int cmps = 0; // Comparaciones
	static int movs = 0; // Movimientos

	static ArrayCase AC = ArrayCase.aleatorio; // aleatorio,ordenado,invetido

	static void cocktailSort(int[] array) {
		int left = 1, right = array.length - 1;
		int last = right;
		while (left <= right) {
			
			for (int r = right; r >= left; r--) {
				//if (array[r - 1] > array[r]) { //COMP
				if (greaterThan(array[r - 1],array[r])) {
					swap(array, r - 1, r); //MOVS
					last = r;
				}
				//System.out.printf("RIGHT, %d, %d ,%d",array[r - 1],array[r],last);
				//System.out.println(Arrays.toString(array));
			}
			
			left = last + 1;
			//System.out.printf("LEFT, %d,%d \n",left, last);
			for (int l = left; l <= right; l++) {
				//if (array[l - 1] > array[l]) { //COMP
				if (greaterThan(array[l - 1],array[l])) {
					swap(array, l - 1, l); //MOVS
					last = l;
				}
				//System.out.printf("LEFT, %d, %d, %d",array[l - 1],array[l], last);
				//System.out.println(Arrays.toString(array));
			}
			right = last - 1;
			//System.out.printf("RIGHT, %d,%d \n",right, last);
		}
	}

	static void swap(int[] array, int i1, int i2) {
		int tmp = array[i1];
		array[i1] = array[i2];
		array[i2] = tmp;
		movs+=3;
	}
	
	static boolean greaterThan(int x, int y) {
	    cmps ++;
	    return x > y;
	}

	public static void main(String[] args) {
		// TEST
		int[] array ={200,100,5,1};// ArrayMethods.getArray(AC, 10, 1, 10); //{5,8,1,4,7,9,2};//
		int movs_sum = 0, movs_prom = 0;
		int cmps_sum = 0, cmps_prom = 0;

		System.out.println(Arrays.toString(array));
		cocktailSort(array);
		System.out.println(Arrays.toString(array));
		System.out.println(ArrayMethods.isSorted(array) + "\n");
		System.out.printf("%d", cmps);

		
		System.out.printf("N\tCOMPARACIONES\tMOVIMIENTOS\n");
		for (int N = 1000; N <= 5000; N = N + 100) {
			int ejec = N / 100;
			for (int M = 1; M <= ejec; M++) {
				int[] array2 = ArrayMethods.getArray(AC, N, 1, N);
				cocktailSort(array2);
				movs_sum = movs + movs_sum;
				cmps_sum = cmps + cmps_sum;
				movs = 0;
				cmps = 0;
			}
			movs_prom = movs_sum / ejec;
			cmps_prom = cmps_sum / ejec;
			System.out.printf("%d\t%d\t%d\n", N, cmps_prom, movs_prom);
			cmps = 0;
			movs = 0;
			cmps_sum = 0;
			movs_sum = 0;
		}
		
	}

}
