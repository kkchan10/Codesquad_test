package Codesquad_test;

import java.util.Arrays;
import java.util.Scanner;

class rubiks{
	String[][][] cube = new String[6][3][3];
	
	void init() {
		String[][][] cube = {{{"B0","B1","B2"},{"B3","B4","B5"},{"B6","B7","B8"}},{{"W0","W1","W2"},{"W3","W4","W5"},{"W6","W7","W8"}},{{"O0","O1","O2"},{"O3","O4","O5"},{"O6","O7","O8"}},{{"G0","G1","G2"},{"G3","G4","G5"},{"G6","G7","G8"}},{{"Y0","Y1","Y2"},{"Y3","Y4","Y5"},{"Y6","Y7","Y8"}},{{"R0","R1","R2"},{"R3","R4","R5"},{"R6","R7","R8"}}};
		this.cube = cube;
		
		print();
	}
	
	void input(String input){
		String[] input_arr = input.split("");
		String inpstr = "";
		
		for(int i=0;i<input_arr.length;i++) {
			
			if(input_arr[i].equals("Q")) {
				System.out.println("Bye~");
				break;
			}
			
			inpstr=input_arr[i];
			
			if(input_arr[i].equals("'")) continue;
			if(input_arr[i]!=input_arr[input_arr.length-1]) {
				if(input_arr[i+1].equals("'")) {
					inpstr+=input_arr[i+1];
				}
			}
			order(inpstr);
			print();
		}
	}

	void Turn90(int side) {
		int n = cube[side].length;
		int m = cube[side][0].length;		
		String[][] rotate = new String[m][n];
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				rotate[i][j] = cube[side][n-1-j][i];
		}
	}
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				cube[side][i][j] = rotate[i][j];
			}		
		}
	}
	
	void rTurn90(int side) {
		int n = cube[side].length;
		int m = cube[side][0].length;		
		String[][] rotate = new String[m][n];
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				rotate[i][j] = cube[side][j][n-1-i];
		}
	}
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				cube[side][i][j] = rotate[i][j];
			}
		}
	}
	
	void print(){
		System.out.println("            "+Arrays.toString(cube[0][0]));
		System.out.println("            "+Arrays.toString(cube[0][1]));
		System.out.println("            "+Arrays.toString(cube[0][2]));
		
		for(int i=0;i<cube[0].length;i++) {
			for(int j=1;j<cube.length-1;j++) {
				System.out.print(Arrays.toString(cube[j][i]));
			}
			System.out.println();
		}
		System.out.println("            "+Arrays.toString(cube[5][0]));
		System.out.println("            "+Arrays.toString(cube[5][1]));
		System.out.println("            "+Arrays.toString(cube[5][2]));
	}
}
	


public class rubiks_cube {

	public static void main(String[] args) {
		rubiks cube = new rubiks();
		Scanner scan = new Scanner(System.in);
		
		cube.init();
		
		while(true) {
			System.out.print("CUBE> ");
			String input = scan.next();
			
			cube.input(input);

			if(input.contains("Q")) break;
		}
		scan.close();
	}
}

