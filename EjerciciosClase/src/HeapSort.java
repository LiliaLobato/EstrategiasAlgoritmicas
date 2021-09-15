import java.util.Arrays;
import UsefullArray.ArrayMethods;
import UsefullArray.ArrayMethods.*;

public class HeapSort {

	static int movs = 0; // Movimientos

	static ArrayCase AC = ArrayCase.invertido; // aleatorio,ordenado,invetido
	
	static int left(int i) {
		return 2 * i + 1;
	}

	static int right(int i) {
		return 2 * i + 2;
	}

	static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
		movs += 3;
	}

	static void maxHeapify(int[] array, int root, int heapSize) {
		int l = left(root);
		int r = right(root);
		int m = root;
		if (l <= heapSize && array[l] > array[m])
			m = l;
		if (r <= heapSize && array[r] > array[m])
			m = r;
		if (m != root) {
			swap(array, root, m);
			maxHeapify(array, m, heapSize);
		}
	}

	static void buildMaxHeap(int[] array) {
		for (int root = array.length / 2; root >= 0; root--) {
			maxHeapify(array, root, array.length - 1);
		}

		//System.out.println(Arrays.toString(array));
	}

	public static void heapSort(int[] array) {
		buildMaxHeap(array);
		for (int heapSize = array.length - 1; heapSize > 0; heapSize--) {
			swap(array, 0, heapSize);
			maxHeapify(array, 0, heapSize - 1);
		}
	}
	
	public static void main(String[] args) {
		// TEST
		int[] array = ArrayMethods.getArray(AC, 10, 1, 10);
		int movs_sum = 0, movs_prom = 0;

		System.out.println(Arrays.toString(array));
		heapSort(array);
		System.out.println(Arrays.toString(array));
		System.out.println(ArrayMethods.isSorted(array) + "\n");

		System.out.printf("N\tMOVIMIENTOS\n");
		for (int N = 10000; N <= 1000; N = N + 1000) {
			int ejec = N / 100;
			for (int M = 1; M <= ejec; M++) {
				int[] array2 = ArrayMethods.getArray(AC, N, 1, N);
				heapSort(array2);
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
