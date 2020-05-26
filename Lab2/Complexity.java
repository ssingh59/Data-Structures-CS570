
public class Complexity {
	
	public static int factcounter = 1; //used for fibonacci series
	
		//	O(n^2)
		public static void method1(int n) {
			
			if(n<0) {
				System.out.println("n cannot be less tha  zero.");
			}
			System.out.println("------Method1 O(n^2)------");
			if(n == (int)n) {
				int counter =1;
				for(int j = 0; j < n; j++) {
					for (int i =0; i<n; i ++) {
						System.out.println(" Operation "+ counter );
						counter++;
					}
				}
			}
		}
		
		// O(n^3)
		public static void method2(int n) {
			
			if(n<0) {
				System.out.println("n cannot be less than zero.");
			}
			System.out.println("------Method 2 O(n^3)------");
			if(n == (int)n) {
				int counter =1;
					for(int k = 0; k < n; k++) {
						for(int j = 0; j < n; j++) {
							for (int i =0; i<n; i ++) {
								System.out.println(" Operation "+ counter );
								counter++;
							}
						}
					}
			}
			
		}
		
		// O(logn)
		public static void method3(int n) {
			
			if(n<0) {
				System.out.println("n cannot be less tha  zero.");
			}
			System.out.println("--------Method3 O(logn)--------");
			if(n == (int)n) {
				int counter =1;
				for (int i =1; i<n; i = i*2) {
					System.out.println(" Operation "+ counter );
					counter++;
				}
			}
		}
		
		// O(nlogn)
		public static void method4(int n) {
			
			if(n<0) {
				System.out.println("n cannot be less tha  zero.");
			}
			System.out.println("-------Method4 O(nlogn)-------");
			if(n == (int)n) {
				int counter =1;
				for(int j = 0; j < n; j++) {
					for (int i =1; i < n ; i = i*2) {
						System.out.println(" Operation "+ counter );
						counter++;
					}
				}
			}
		}
		
		// O(loglogn) log(log16)=2
		public static void method5(int n) {
			
			if(n<0) {
				System.out.println("n cannot be less tha  zero.");
			}
			System.out.println("------Method5 O(loglogn)------");
			if(n == (int)n) {
				int counter =1;
					for (int i =2; i < n ; i = i*i) {
						System.out.println(" Operation "+ counter );
						counter++;
					//}
				}
			}
		}
		
		//O(2^n)
		public static int method6(int n) {
			
			//fibonacci series
			if (n <= 0) {
				System.out.println(" Operation "+ factcounter);
				return n; 
			}
				
			else {  // return n; 
				System.out.println(" Operation "+ factcounter);
				factcounter++;
		        return method6(n-1) + method6(n-2); 
			}
		}

	
	
	public static void main(String[] args) {
		
		Complexity.method1(3);
		Complexity.method2(3);
		Complexity.method3(8);
		Complexity.method4(4);
		Complexity.method5(64);
		System.out.println("--------Method6 O(2^n)--------");
		Complexity.method6(4);
	}

}
