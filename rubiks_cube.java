package Codesquad_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javax.naming.InsufficientResourcesException;

class rubiks{
	String[][][] cube = new String[6][3][3];
	int count=0;	// 조작 받은 명령 갯수 카운트
	
	// 문제풀이 - 1
	// 큐브 상태 초기화 및 랜덤 생성
	void init() {
		String[][][] init_cube = {{{"B0","B1","B2"},{"B3","B4","B5"},{"B6","B7","B8"}},{{"W0","W1","W2"},{"W3","W4","W5"},{"W6","W7","W8"}},{{"O0","O1","O2"},{"O3","O4","O5"},{"O6","O7","O8"}},{{"G0","G1","G2"},{"G3","G4","G5"},{"G6","G7","G8"}},{{"Y0","Y1","Y2"},{"Y3","Y4","Y5"},{"Y6","Y7","Y8"}},{{"R0","R1","R2"},{"R3","R4","R5"},{"R6","R7","R8"}}};
		// 큐브 랜덤 생성
		ArrayList<String> random = new ArrayList<String>();
		for(int i=0;i<init_cube.length;i++) {
			for(int j=0;j<init_cube[0].length;j++) {
				for(int k=0;k<init_cube[0][0].length;k++) {
					random.add(init_cube[i][j][k]);		// 큐브 모든 요소를 ArrayList에 저장
				}
			}
		}
		Collections.shuffle(random);	// 저장된 리스트를 섞음
		int count=0;
		
		for(int i=0;i<init_cube.length;i++) {
			for(int j=0;j<init_cube[0].length;j++) {
				for(int k=0;k<init_cube[0][0].length;k++) {
					cube[i][j][k]=random.get(count);	// 랜덤 하게 섞인 리스트 요소를 큐브 배열에 저장
					count++;
				}
			}
		}
		print();	// 큐브 초기 상태 출력
	}
	
	// 문제풀이 - 2
	// 사용자 입력값 구분 후 명령어 별 큐브 회전 메소드 호출
	boolean input(String input){
		String[] input_arr = input.split("");
		String inpstr = "";
		
		for(int i=0;i<input_arr.length;i++) {
			if(input_arr[i].equals("Q")) {	// Q값이 입력 되는 경우 큐브 회전 종료
				break;
			}
			inpstr=input_arr[i];	// 현재 입력 받은 값 저장
			if(input_arr[i].equals("'")) continue;
			if(input_arr[i]!=input_arr[input_arr.length-1]) {
				if(input_arr[i+1].equals("'")) {
					inpstr+=input_arr[i+1];	// 현재 입력 값의 다음 값이 '인 경우 문자열 더하기(역방향)
				}
			}
			order(inpstr);		// 문제풀이 - 3 , 큐브 회전 주문 메소드 호출
			print();	// 회전 후 큐브 상태 출력
			count++; 
		}
		return finish();	// 메소드 반환 값을 finish()메소드로 하여 큐브 모든 면 맞춤 여부 판단
	}
	
	// 문제풀이 - 4
	// 사용자 명령어에 따른 회전 메소드 호출 결정
	void order(String input) {
		System.out.println(input);
		if(input.equals("F")) {
			F();
		} else if(input.equals("F'")) {
			rF();
		}else if(input.equals("U")) {
			U();
		} else if(input.equals("U'")) {
			rU();
		} else if(input.equals("R")) {
			R();
		} else if(input.equals("R'")) {
			rR();
		} else if(input.equals("L")) {
			L();
		} else if(input.equals("L'")) {
			rL();
		} else if(input.equals("B")) {
			B();
		} else if(input.equals("B'")) {
			rB();
		} else if(input.equals("D")) {
			D();
		} else if(input.equals("D'")) {
			rD();
		}
	}
	
	// 문제풀이 - 5
	// 정방향 회전 메소드(옆 면), 호출된 메소드에 따라 큐브의 값 이동
	void F() {
			Turn90(2);	// 정방향 회전 시 해당 면 90도 회전, 	문제풀이 - 6
			for(int i=0;i<cube[2].length;i++) {
				String temp = cube[0][2][i];
				cube[0][2][i] = cube[1][cube[2].length-1-i][2];
				cube[1][cube[2].length-1-i][2] = cube[5][0][cube[2].length-1-i];
				cube[5][0][cube[2].length-1-i] = cube [3][i][0];
				cube[3][i][0] = temp;
		}
	}
	// 역방향 회전 메소드(옆 면), 호출된 메소드에 따라 큐브의 값 이동
	void rF() {
			rTurn90(2);	// 역방향 회전 시 해당 면 역으로 90도 회전
			for(int i=0;i<cube[2].length;i++) {
				String temp = cube[0][2][i];
				cube[0][2][i] = cube[3][i][0];
				cube[3][i][0] = cube[5][0][cube[2].length-1-i];
				cube[5][0][cube[2].length-1-i] = cube[1][cube[2].length-1-i][2];
				cube[1][cube[2].length-1-i][2] = temp;
			}
	}
	
	void U() {
		Turn90(0);
		for(int i=0;i<cube[0].length;i++) {
			String temp = cube[1][0][i];
			cube[1][0][i] = cube[2][0][i];
			cube[2][0][i] = cube[3][0][i];
			cube[3][0][i] = cube[4][0][i];
			cube[4][0][i] = temp;
			}
	}
	
	void rU() {
		rTurn90(0);
		for(int i=0;i<cube[0].length;i++) {
			String temp = cube[4][0][i];
			cube[4][0][i] = cube[3][0][i];
			cube[3][0][i] = cube[2][0][i];
			cube[2][0][i] = cube[1][0][i];
			cube[1][0][i] = temp;
			}
	}
	
	void R() {
		Turn90(3);
		for(int i=0;i<cube[3].length;i++) {
			String temp = cube[0][i][2];
			cube[0][i][2] = cube[2][i][2];
			cube[2][i][2] = cube[5][i][2];
			cube[5][i][2] = cube[4][cube[3].length-1-i][0];
			cube[4][cube[3].length-1-i][0] = temp;
			}
	}
	
	void rR() {
		rTurn90(3);
		for(int i=0;i<cube[3].length;i++) {
			String temp = cube[0][i][2];
			cube[0][i][2] = cube[4][cube[3].length-1-i][0];
			cube[4][cube[3].length-1-i][0] = cube[5][i][2];
			cube[5][i][2] = cube[2][i][2];
			cube[2][i][2] = temp;
			}
	}
	
	void L() {
		Turn90(1);
		for(int i=0;i<cube[1].length;i++) {
			String temp = cube[0][i][0];
			cube[0][i][0] = cube[4][cube[1].length-1-i][2];
			cube[4][cube[1].length-1-i][2] = cube[5][i][0];
			cube[5][i][0] = cube[2][i][0];
			cube[2][i][0] = temp;
			}		
	}
	
	void rL() {
		rTurn90(1);
		for(int i=0;i<cube[1].length;i++) {
			String temp = cube[0][i][0];
			cube[0][i][0] = cube[2][i][0];
			cube[2][i][0] = cube[5][i][0];
			cube[5][i][0] = cube[4][cube[1].length-1-i][2];
			cube[4][cube[1].length-1-i][2] = temp;
			}		
	}
	
	void B() {
		Turn90(4);
		for(int i=0;i<cube[4].length;i++) {	
			String temp = cube[0][0][i];
			cube[0][0][i] = cube[3][i][2];
			cube[3][i][2] = cube[5][2][cube[4].length-1-i];
			cube[5][2][cube[4].length-1-i] = cube[1][cube[4].length-1-i][0];
			cube[1][cube[4].length-1-i][0] = temp;
			
			}
	}
	
	void rB() {
		rTurn90(4);
		for(int i=0;i<cube[4].length;i++) {
			String temp = cube[0][0][i];
			cube[0][0][i] = cube[1][cube[4].length-1-i][0];
			cube[1][cube[4].length-1-i][0] = cube[5][2][cube[4].length-1-i];
			cube[5][2][cube[4].length-1-i] = cube[3][i][2];
			cube[3][i][2] = temp;
			}
	}
	
	void D() {
		Turn90(5);
		for(int i=0;i<cube[5].length;i++) {
			String temp = cube[1][2][i];
			cube[1][2][i] = cube[4][2][i];
			cube[4][2][i] = cube[3][2][i];
			cube[3][2][i] = cube[2][2][i];
			cube[2][2][i] = temp;
			}
	}
	
	void rD() {
		rTurn90(5);
		for(int i=0;i<cube[5].length;i++) {
			String temp = cube[1][2][i];
			cube[1][2][i] = cube[2][2][i];
			cube[2][2][i] = cube[3][2][i];
			cube[3][2][i] = cube[4][2][i];
			cube[4][2][i] = temp;
			}
	}
	
	// 문제풀이 - 6
	// 면 정방향 90도 회전
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
	
	// 면 역방향 90도 회전
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
	
	// 큐브 상태 출력 메소드
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
	
	// 문제풀이 - 8
	// 큐브가 초기 정렬된 상태와 일치하는지 여부 확인
	boolean finish() {
		String[][][] init_cube = {{{"B0","B1","B2"},{"B3","B4","B5"},{"B6","B7","B8"}},{{"W0","W1","W2"},{"W3","W4","W5"},{"W6","W7","W8"}},{{"O0","O1","O2"},{"O3","O4","O5"},{"O6","O7","O8"}},{{"G0","G1","G2"},{"G3","G4","G5"},{"G6","G7","G8"}},{{"Y0","Y1","Y2"},{"Y3","Y4","Y5"},{"Y6","Y7","Y8"}},{{"R0","R1","R2"},{"R3","R4","R5"},{"R6","R7","R8"}}};
		int count=0;
		
		for(int i=0;i<init_cube.length;i++) {
			for(int j=0;j<init_cube[0].length;j++) {
				for(int k=0;k<init_cube[0][0].length;k++) {
					if(init_cube[i][j][k].equals(cube[i][j][k])) {	// 초기 큐브와 현재 큐브 값 비교
						count++;
					}
				}
			}
		}
		if(count==54) {
			return true;
		} else {
			return false;
		}
	}
}

public class rubiks_cube {

	public static void main(String[] args) {
		long beforeTime = System.currentTimeMillis();	// 프로그램 경과 시간 체크
		rubiks cube = new rubiks();
		Scanner scan = new Scanner(System.in);		// 사용자 입력 값 저장 객체 생성
		
		cube.init();	// 큐브 초기화 및 랜덤 섞기
		
		while(true) {
			System.out.print("CUBE> ");
			String input = scan.next();		// 사용자 입력 값 저장

			if(cube.input(input)) {		// 반환 값이 True인 경우 종료
				System.out.println("축하합니다 모든 큐브를 맞추셨습니다.");
				break;
			} else if(input.contains("Q")) {
				break;
			}
		}
		scan.close();
		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime)/1000;	// 문제 풀이 - 8
		System.out.format("경과 시간 : %02d:%02d %n",secDiffTime/60,secDiffTime%60);	// 분 : 초
		System.out.println("조작 갯수 : "+ cube.count);
		System.out.println("이용해 주셔서 감사합니다.");
	}
}

