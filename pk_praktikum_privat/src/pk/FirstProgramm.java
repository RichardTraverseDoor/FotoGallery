package pk;

public class FirstProgramm {
	
	public static void main(String[] args) {
		maleTreppe(8,3);
	}
	
	
	
	public static void maleTreppe(int hoehe, int stufentiefe) {
		for(int i = 1; i<= hoehe; i++) {
			for(int j = i*stufentiefe; j>0;j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
