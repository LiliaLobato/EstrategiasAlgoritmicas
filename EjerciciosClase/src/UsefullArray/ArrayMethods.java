package UsefullArray;

import java.util.Arrays;

public class ArrayMethods {

	public enum Cost {
		comp, // Comparaciones, C2
		mov // Movimientos, C1
	}

	public enum ArrayCase {
		aleatorio, ordenado, invertido
	}

	// REVISA SI EL ARRAY ESTÁ ORDENADO
	public static boolean isSorted(int[] array) {
		// O(N), Omega(1)
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1])
				return false;
		}
		return true;
	}

	// CREACION DEL ARRAY CON CONTENIDO ALEATORIO
	public static int[] createArray(int N, int min, int max) {
		int[] array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = min + (int) ((max - min + 1) * Math.random());
		}
		return array;
	}

	// CREACION DEL ARRAY CON CONTENIDO ORDENADO
	public static int[] createOrderedArray(int N, int min) {
		int[] array = new int[N];
		array[0] = min;
		for (int i = 1; i < N; i++) {
			array[i] = array[i - 1] + (int) (4 * Math.random());
		}
		return array;
	}
	
	// CREACION DEL ARRAY CON CONTENIDO ORDENADO
	public static int[] createSpecialArray(int N, int min) {
		int[] array = new int[N];
		array[0] = min;
		for (int i = 1; i < N; i++) {
			array[i] = array[i - 1] + (int) (1);
		}
		return array;
	}

	// CREACION DEL ARRAY CON CONTENIDO ORDENADO INVERSO
	public static int[] createInvertedArray(int N, int min) {
		int[] array = new int[N];
		array[N - 1] = min;
		for (int i = N - 2; i >= 0; i--) {
			array[i] = array[i + 1] + (int) (4 * Math.random());
		}
		return array;
	}

	// CREACION DEL ARRAY CON CONTENIDO ORDENADO INVERSO
	public static int[] getArray(ArrayCase AC, int N, int min, int max) {
		int[] array = new int[N];
		switch (AC) {
			case aleatorio:
				array = createArray(N, min, max);
				break;
			case ordenado:
				array = createOrderedArray(N, min);
				break;
			case invertido:
				array = createInvertedArray(N, min);
				break;
			default:
				array = createArray(N, min, max);
				break;
		}
		return array;
	}

}
