
import java.util.Arrays;
import UsefullArray.ArrayMethods;
import UsefullArray.ArrayMethods.*;

public class InsertionSort {

	static int cmps = 0; // Comparaciones
	static int movs = 0; // Movimientos

	static ArrayCase AC = ArrayCase.ordenado; // aleatorio,ordenado,invetido

	static boolean greaterThan(int x, int y) {
		cmps++;
		return x > y;
	}

	static void insertion(int[] array) {
		final int N = array.length;
		for (int p = 1; p < N; p++) {
			int pivot = array[p];
			movs++;
			int i = p - 1;
			while (i >= 0 && greaterThan(array[i], pivot)) {
				array[i + 1] = array[i];
				movs++;
				i--;
			}
			array[i + 1] = pivot;
			movs++;
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
		// TEST
		int[] array = ArrayMethods.getArray(AC, 10, 1, 10);
		int movs_sum = 0, movs_prom = 0;
		int cmps_sum = 0, cmps_prom = 0;

		System.out.println(Arrays.toString(array));
		insertion(array);
		System.out.println(Arrays.toString(array));
		System.out.println(ArrayMethods.isSorted(array) + "\n");

		System.out.printf("N\tCOMPARACIONES\tMOVIMIENTOS\n");
		for (int N = 1000; N <= 5000; N = N + 100) {
			int ejec = N / 100;
			for (int M = 1; M <= ejec; M++) {
				int[] array2 = ArrayMethods.getArray(AC, N, 1, N);
				insertion(array2);
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