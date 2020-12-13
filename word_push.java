package Codesquad_test;

import java.util.Scanner;
import java.util.ArrayList;

public class word_push {
	static void left(ArrayList<Character> str, int push){
		char temp;
		for(int i=0;i<Math.abs(push);i++) {
			temp = str.remove(0);
			str.add(temp);
		}
	}
	
	static void right(ArrayList<Character> str, int push){
		char temp;
		for(int i=0;i<Math.abs(push);i++) {
			temp = str.remove(str.size()-1);
			str.add(0,temp);
		}
	}

	public static void main(String[] args) {
		ArrayList<Character> str = new ArrayList<Character>();
		Scanner scan = new Scanner(System.in);
		
		System.out.print("input> ");
		String input = scan.nextLine();
		scan.close();
		String[] input_arr = input.split(" ");
		
		for(int i=0;i<input_arr[0].length();i++) {
			str.add(input_arr[0].charAt(i));
		}
		
		int push = Integer.parseInt(input_arr[1]);
		
		if(input_arr[2].equals("L") || input_arr[2].equals("l")) {
			if(push<0) {
				right(str, push);
			} else if (push>0 ) {
				left(str, push);
			}
		} else if(input_arr[2].equals("R") || input_arr[2].equals("r")) {
			if(push<0) {
				left(str, push);
			} else if (push>0 ) {
				right(str, push);
			}
		}
		System.out.println(str.toString().replaceAll("\\[|\\]|,| ",""));		
	}
}
