import java.util.Arrays;
import UsefullArray.ArrayMethods;
import UsefullArray.ArrayMethods.*;

public class ShellSort {

	static int movs = 0; // Movimientos
	static ArrayCase AC = ArrayCase.invertido; // aleatorio,ordenado,invetido

	public static void shellSort(int arrayToSort[]) {
		int n = arrayToSort.length;
		// Start with a big gap, then reduce the gap
		for (int gap = n / 2; gap > 0; gap /= 2) {
			// Do a gapped insertion sort for this gap size.
			for (int i = gap; i < n; i++) {
				// add a[i] to the elements that have been gap
				// sorted save a[i] in temp and make a hole at
				// position i
				int key = arrayToSort[i];
				// shift earlier gap-sorted elements up until
				// the correct location for a[i] is found
				int j = i;
				while (j >= gap && arrayToSort[j - gap] > key) {
					arrayToSort[j] = arrayToSort[j - gap];
					j -= gap;
					movs++;
				}
				// put temp (the original a[i]) in its correct
				// location
				arrayToSort[j] = key;
			}
		}
	}

	public static void main(String[] args) {
		// TEST
		int[] array = ArrayMethods.getArray(AC, 10, 1, 10);
		int movs_sum = 0, movs_prom = 0;

		System.out.println(Arrays.toString(array));
		shellSort(array);
		System.out.println(Arrays.toString(array));
		System.out.println(ArrayMethods.isSorted(array) + "\n");

		System.out.printf("N\tMOVIMIENTOS\n");
		for (int N = 10000; N <= 100000; N = N + 1000) {
			int ejec = N / 100;
			for (int M = 1; M <= ejec; M++) {
				int[] array2 = ArrayMethods.getArray(AC, N, 1, N);
				shellSort(array2);
				movs_sum = movs + movs_sum;
				movs = 0;
			}
			movs_prom = movs_sum / ejec;
			System.out.printf("%d\t%d\n", N, movs_prom);
			movs = 0;
			movs_sum = 0;
		}
	}
}
