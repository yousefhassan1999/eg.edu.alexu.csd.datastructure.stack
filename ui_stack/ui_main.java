package ui_stack;

import java.util.Scanner;

import stake_classes.stack;

public class ui_main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		stack ps=new stack();
		System.out.println("Please choose an action");
		System.out.println("-----------------------");
		System.out.println("1- push");
		System.out.println("2- pop");
		System.out.println("3- peak");
		System.out.println("4- get size");
		System.out.println("5- check if empty");
		System.out.println("6- finish");
		System.out.println("====================================================================");
		while(true) {//will keep running forever unless user enter invalid input
			int Operation = in.nextInt();
			switch(Operation) {
			case 1 :
				System.out.println("please enter the object to push in stack");
				String push_element=in.next();
				ps.push(push_element);
				break;
			case 2 :
				System.out.println("the peak is="+ps.pop());
				break;
			case 3 :
				System.out.println("the peak is="+ps.peek());
				break;
			case 4 :
				System.out.println("the size ="+ps.size());
				break;
			case 5 :
				System.out.println(ps.isEmpty());
				break;
				
			}
			if(Operation==6)
				break;
			
			System.out.println("enter the new operation");
		}
		System.out.println("the program is finish");
	}
	
}
