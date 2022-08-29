import java.util.Random;
import java.util.Arrays;
import java.time.Duration;

public class Part2 {

	//makes array of ints of size n
	private static int[] makeArray(int n) {
		Random rand = new Random();
		int[] a = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = rand.nextInt();
		}
		return a;

	}
	
	//makes array of Integers of size n
	private static Integer[] makeArray2(int n) {
		Random rand = new Random();
		Integer[] a = new Integer[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = (Integer) rand.nextInt();
		}
		return a;
	}
	
	public static void main(String[] args) {
		String ans = "\t\theapSort(int)\theapSort(Obj)\tshellSort(int)\tshellSort(Obj)\tsort(int)\tsort(Obj)\n";
		
		for (int n = 10; n <= 1000000; n*=10) {
			ans += "n = " + String.format("%-12d", n);
			int[] a1 = makeArray(n);
			int[] a2 = a1.clone();
			int[] a3 = a1.clone();
			Integer[] b1 = makeArray2(n);
			Integer[] b2 = b1.clone();
			Integer[] b3 = b1.clone();
			
			long s = System.currentTimeMillis();
			A2.heapSort(a1);
			long time = System.currentTimeMillis() - s;
			ans += String.format("%10d ms\t", time);
			
			s = System.currentTimeMillis();
			A2.heapSort(b1);
			time = System.currentTimeMillis() - s;
			ans += String.format("%10d ms\t", time);
			
			s = System.currentTimeMillis();
			A2.shellSort(a2);
			time = System.currentTimeMillis() - s;
			ans += String.format("%10d ms\t", time);
			
			s = System.currentTimeMillis();
			A2.shellSort(b2);
			time = System.currentTimeMillis() - s;
			ans += String.format("%10d ms\t", time);
			
			s = System.currentTimeMillis();
			Arrays.sort(a3);
			time = System.currentTimeMillis() - s;
			ans += String.format("%6d ms\t", time);
			
			s = System.currentTimeMillis();
			Arrays.sort(b3);
			time = System.currentTimeMillis() - s;
			ans += String.format("%6d ms\n", time);
		}
		System.out.print(ans);
	}

}
