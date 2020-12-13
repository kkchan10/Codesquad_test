package Codesquad_test;

import java.util.Arrays;
import java.util.Scanner;

class cube{
	void input(String[][] cube, String input){
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
			order(cube,inpstr);
			System.out.println(Arrays.toString(cube[0]));
			System.out.println(Arrays.toString(cube[1]));
			System.out.println(Arrays.toString(cube[2]));
		}
	}
	
	void order(String[][] sArr, String s) {
		String temp;
		System.out.println(s);
		if(s.equals("U")) {
			temp = sArr[0][0];
			sArr[0][0]=sArr[0][1];
			sArr[0][1]=sArr[0][2];
			sArr[0][2]=temp;
		} else if(s.equals("U'")) {
			temp = sArr[0][2];
			sArr[0][2]=sArr[0][1];
			sArr[0][1]=sArr[0][0];
			sArr[0][0]=temp;
		} else if(s.equals("R")) {
			temp = sArr[0][2];
			sArr[0][2]=sArr[1][2];
			sArr[1][2]=sArr[2][2];
			sArr[2][2]=temp;
		} else if(s.equals("R'")) {
			temp = sArr[2][2];
			sArr[2][2]=sArr[1][2];
			sArr[1][2]=sArr[0][2];
			sArr[0][2]=temp;
		} else if(s.equals("L")) {
			temp = sArr[2][0];
			sArr[2][0]=sArr[1][0];
			sArr[1][0]=sArr[0][0];
			sArr[0][0]=temp;
		} else if(s.equals("L'")) {
			temp = sArr[0][0];
			sArr[0][0]=sArr[1][0];
			sArr[1][0]=sArr[2][0];
			sArr[2][0]=temp;
		} else if(s.equals("B")) {
			temp = sArr[2][2];
			sArr[2][2]=sArr[2][1];
			sArr[2][1]=sArr[2][0];
			sArr[2][0]=temp;
		} else if(s.equals("B'")) {
			temp = sArr[2][0];
			sArr[2][0]=sArr[2][1];
			sArr[2][1]=sArr[2][2];
			sArr[2][2]=temp;
		}
	}
	
}

public class plane_cube {

	public static void main(String[] args) {
		String[][] pcube = {{"R","R","W"},{"G","C","W"},{"G","B","B"}};
		cube cube = new cube();
		Scanner scan = new Scanner(System.in);
		
		System.out.println(Arrays.toString(pcube[0]));
		System.out.println(Arrays.toString(pcube[1]));
		System.out.println(Arrays.toString(pcube[2]));
		
		while(true) {
			System.out.print("CUBE> ");
			String input = scan.next();
			
			cube.input(pcube,input);

			if(input.contains("Q")) break;
		}
		scan.close();
	}
}
