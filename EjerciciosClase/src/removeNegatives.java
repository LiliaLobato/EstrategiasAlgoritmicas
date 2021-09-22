import java.util.ArrayList;
import java.util.List;

public class removeNegatives {
	static double time_mejora;
	static double time_og;
	
	public static void removeNegatives_mejora(ArrayList<Double> list) {
		List<Double> tempList = new ArrayList<>();
		long start = System.currentTimeMillis();
		for (Double e : list) {
			if (e >= 0) {
				tempList.add(e);
			}
		}
		list.clear();
		for (int i = 0; i < tempList.size(); i++) {
			double d = tempList.get(i);
			list.add(d);
		}
		long end = System.currentTimeMillis();
		//System.out.printf("funcion editada: %.2f\n", (end - start) / 1000.0);
		time_mejora = ((end - start)/ 1000.0);
	}

	public static void removeNegatives_original(ArrayList<Double> list) {
		long start_og = System.currentTimeMillis();
		List<Double> tempList = new ArrayList<>();
		while (!list.isEmpty()) {
			double d = list.remove(0);
			if (d >= 0)
				tempList.add(d);
		}
		for (int i = 0; i < tempList.size(); i++) {
			double d = tempList.get(i);
			list.add(d);
		}
		long end_og = System.currentTimeMillis();
		//System.out.printf("funcion normal: %.2f\n", (end_og - start_og) / 1000.0);
		time_og =  ((end_og - start_og)/ 1000.0);
	}

	public static void main(String[] args) {
		ArrayList<Double> list = new ArrayList<>();
		ArrayList<Double> list2 = new ArrayList<>();
		for (int num = 5_000; num < 1_000_000; num += 2000) {
			for (int i = 0; i <= num; i++) {
				double n = Math.random();
				list.add(n);
				list2.add(n);
			}
			removeNegatives_mejora(list);
			removeNegatives_original(list2);
			System.out.printf("%d\t%.5f\t%.5f\n",num, time_mejora, time_og);
			list.clear();
			list2.clear();
		}
		

	}

}
