
public class DP {

	static long count = 0;

	static long binomialCoefficientRec(int N, int K) {
		if (K == 0 || K == N)
			return 1;
		if (K > 0 && K < N)
			return binomialCoefficientRec(N - 1, K - 1) + binomialCoefficientRec(N - 1, K);
		return 0;
	}

	// programación dinámica (memorización): bottom-up
	static long binomialCoefficientBU(int N, int K) {
		long[][] cache = new long[N + 1][K + 1];
//		Bottom-up: calcula primero instancias pequeñas (de respuesta inmediata)
		for (int n = 0; n <= N; n++) {
			for (int k = 0; k <= K; k++) {
				// (n, k)
				if (k == 0 || k == n)
					cache[n][k] = 1;
				else if (k > n)
					cache[n][k] = 0;
				else
					cache[n][k] = cache[n - 1][k - 1] + cache[n - 1][k];
			}
		}
		return cache[N][K]; // en la esquina inferior derecha reside la solución
	}

	static long binomialCoefficientTD(int N, int K, long[][] cache) {
		if (N == 20 && K == 10)
			count++;
		if (K == 0 || K == N)
			return 1;
		if (K > N)
			return 0;
		// Si ya se calculó este par (N, K) simplemente se devuelve el resultado ya
		// almacenado
		if (cache[N][K] > 0)
			return cache[N][K];
		// Si no se ha calculado, hacemos las llamadas recursivas y
		// almacenamos/devolvemos el resultado.
		cache[N][K] = binomialCoefficientTD(N - 1, K - 1, cache) + binomialCoefficientTD(N - 1, K, cache);
		return cache[N][K];
	}

	// programación dinámica (memorización): top-down
	static long binomialCoefficientTD(int N, int K) {
		long[][] cache = new long[N + 1][K + 1];
		return binomialCoefficientTD(N, K, cache);
	}

	// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, ...
	// 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
	static long fibonacci(int N) {
		if (N == 18)
			count++;
		if (N <= 0)
			return 0;
		if (N <= 2)
			return N;
		return fibonacci(N - 1) + fibonacci(N - 2);
	}

	// programación dinámica (memorización): bottom-up
	static long fibonacciBU(int N) {
		long[] cache = new long[N + 1];
//			Bottom-up: calcula primero instancias pequeñas (de respuesta inmediata)
		for (int n = 0; n <= N; n++) {
			if (n <= 1)
				cache[n] = 1;
			else
				cache[n] = cache[n - 1] + cache[n - 2];
			//System.out.print(n + ": ");
			//System.out.println(cache[n]);
		}
		return cache[N]; // en la esquina inferior derecha reside la solución
	}

	static long fibonacciTD(int N, long[] cache) {
		if (N == 18)
			count++;
		if (N <= 1)
			return 1;
		// Si ya se calculó (N) simplemente se devuelve el resultado ya
		// almacenado
		if (cache[N] > 0)
			return cache[N];
		// Si no se ha calculado, hacemos las llamadas recursivas y
		// almacenamos/devolvemos el resultado.
		cache[N] = fibonacciTD(N - 1, cache) + fibonacciTD(N - 2, cache);
		return cache[N];
	}

	// programación dinámica (memorización): top-down
	static long fibonacciTD(int N) {
		long[] cache = new long[N + 1];
		count = 0;
		return fibonacciTD(N, cache);
	}
	
	public static void main(String[] args) {
			
		long start, end;
		int N;

		N = 45;
		start = System.currentTimeMillis();
		System.out.printf("fibonacci(%d) = %d\n", N, fibonacci(N));
		System.out.printf("fibonacci count = %d\n", count);
		end = System.currentTimeMillis();
		System.out.printf("Segundos: %.1f\n", (end - start) / 1000.0);
		start = System.currentTimeMillis();
		System.out.printf("fibonacciBU(%d) = %d\n", N, fibonacciBU(N));
		end = System.currentTimeMillis();
		System.out.printf("Segundos: %.1f\n", (end - start) / 1000.0);
		start = System.currentTimeMillis();
		System.out.printf("fibonacciTD(%d) = %d\n", N, fibonacciTD(N));
		System.out.printf("fibonacciTD count = %d\n", count);
		end = System.currentTimeMillis();
		System.out.printf("Segundos: %.1f\n", (end - start) / 1000.0);


	}

}
