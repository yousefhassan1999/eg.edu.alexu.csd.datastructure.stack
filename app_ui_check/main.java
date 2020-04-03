package app_ui_check;

import java.util.Scanner;

import stack_app_classes.code;

public class main {

	public static void main(String[] args) throws Exception {
		code test=new code();
		Scanner in = new Scanner(System.in);
		boolean f=true;
		String req = null;
		while(f) {
			System.out.println("Please choose an action");
			System.out.println("-----------------------");
			System.out.println("1- convert from infix to postfix");
			System.out.println("2- Print the value of the postfix");
			System.out.println("3- evaluate");
			System.out.println("4- to finish the program");
			System.out.println("====================================================================");
			int Operation = in.nextInt();
			switch(Operation){
				case 1:
					req=null;
					System.out.println("please enter the infix expression");
					req= in.next();
					req=test.infixToPostfix(req);
					break;
				case 2:
					if(req==null)
						System.out.println("the expression is null");
					else
						System.out.println("the postfix expression is :"+req);
					break;
				case 3:
					System.out.println(req);
					System.out.println("the evluate value is :"+test.evaluate(req));
					break;
				case 4:
					f=false;
					break;
				default:
					throw new Exception("Undefined polynomial");
			}
		}
		System.out.println("the program is finish");
	}

}
