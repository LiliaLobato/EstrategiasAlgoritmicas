import java.util.Arrays;
import UsefullArray.ArrayMethods;
import UsefullArray.ArrayMethods.*;

public class SelectionSort {

	static int cmps = 0; // Comparaciones
	static int movs = 0; // Movimientos

	static ArrayCase AC = ArrayCase.invertido; // aleatorio,ordenado,invetido

	static void selection(int[] array) {
		for (int p = 0; p < array.length - 1; p++) {
			int min = p;
			for (int i = p + 1; i < array.length; i++) {
				cmps++;
				if (array[i] < array[min]) {
					min = i;
				}
			}
			if (p != min) {
				int tmp = array[p];
				array[p] = array[min];
				array[min] = tmp;
				movs += 3;
			}
		}
	}

	public static void main(String[] args) {
		// TEST
		int[] array = ArrayMethods.getArray(AC, 10, 1, 10);
		int movs_sum = 0, movs_prom = 0;
		int cmps_sum = 0, cmps_prom = 0;

		System.out.println(Arrays.toString(array));
		selection(array);
		System.out.println(Arrays.toString(array));
		System.out.println(ArrayMethods.isSorted(array) + "\n");

		System.out.printf("N\tCOMPARACIONES\tMOVIMIENTOS\n");
		for (int N = 1000; N <= 5000; N = N + 100) {
			int ejec = N / 100;
			for (int M = 1; M <= ejec; M++) {
				int[] array2 = ArrayMethods.getArray(AC, N, 1, N);
				selection(array2);
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
