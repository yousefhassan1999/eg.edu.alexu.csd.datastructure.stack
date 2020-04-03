package stack_app_classes;

import stack_app_iterface.IExpressionEvaluator;
import stake_classes.stack;

public class code implements IExpressionEvaluator {	
	
	/////////////////////////////////////////////////////////////
	/**
	 * @param expression
	 * this to remove the space
	 * @return
	 * return without spaces
	 */
	public String remove_space(String expression) {
		String new_exp = new String();
	 	 int i=0;
		 while(i<expression.length()) {
			 if(expression.charAt(i)==' ')
				 i++;
			 else
				 new_exp+=expression.charAt(i++);
			 
		 }
		 expression=new_exp;
		 return expression;
	}
	/////////////////////////////////////////////////////
	/**
	 * @param expression
	 * to check if there is a special char
	 * @throws RuntimeException
	 * throw if there is aspecial char
	 */
	public void special_char(String expression)throws RuntimeException {
	 	 int i=0;
		 while(i<expression.length()) {
			 if(!(Character.isDigit(expression.charAt(i))||Character.isLetter(expression.charAt(i)))) {
				 if(!(expression.charAt(i)=='+'||expression.charAt(i)=='-'||expression.charAt(i)=='*'||expression.charAt(i)=='/'||expression.charAt(i)=='('||expression.charAt(i)==')'||expression.charAt(i)=='.'))
					 throw new RuntimeException(" invalid expression : there is special character"); 
			 }
			 i++;			 
		 }
	}
	/////////////////////////////////////////////////////
	/**
	 * @param expression
	 * to check the parenthese
	 * @throws RuntimeException
	 * throw if the parenthese is not correct
	 */
	public void check_parenthese(String expression)throws RuntimeException{
		stack check=new stack();
		int i=0;
		while(i<expression.length()) {
			if(expression.charAt(i)=='(') {
				check.push('(');
			}
			else if(expression.charAt(i)==')') {
				if(check.isEmpty())
					 throw new RuntimeException(" invalid expression : the parenthes not open to close it ");
				else
					check.pop();
			}
			i++;
		}
		 if(!(check.isEmpty()))
			 throw new RuntimeException(" invalid expression : the parenthes not close  ");
	}
	
	////////////////////////////////////////////////////////
	
	/**
	 * @param expression
	 * to check the operator 
	 * @return
	 * the expression if it right
	 * @throws RuntimeException
	 * throw if it was wrong
	 */
	public String check_operator(String expression)throws RuntimeException {
		String new_exp = new String();
	 	 int i=0;
	 	if(expression.charAt(0)=='+') {
			char [] p=expression.toCharArray();
			p[0]=' ';
			expression=String.valueOf(p);	
		}
	 	if(expression.charAt(0)=='*'||expression.charAt(0)=='/')
			throw new RuntimeException(" invalid expression the is * or / in the first of expression ");
	 	
		 while(i<expression.length()) {
			 int z=i+1;
			 if(expression.charAt(i)=='+'||expression.charAt(i)=='-'||expression.charAt(i)=='*'||expression.charAt(i)=='/') {
				 if(expression.charAt(z)=='*'||expression.charAt(z)=='/'||(expression.charAt(i)=='+'&&expression.charAt(z)=='+'))
					 throw new RuntimeException(" invalid expression ");
			 } 
			new_exp+=expression.charAt(i);
			i++;
			 
		 }
		 new_exp= remove_space(new_exp);
		 expression=new_exp;
		 return expression;
	}
	
	
	/**
	 * @param expression
	 * this is the expression that you want to check
	 * @return
	 * to check the expression
	 * @throws RuntimeException
	 * throw if there is a wrong expression
	 */
	@SuppressWarnings("unused")
	public String check_expresion(String expression) throws RuntimeException {
		 int i=0;
		 int j = expression.length();
		
		 ///if string is empty
		 //////////////////////////////////////////////
		 if(expression==null)
			throw new RuntimeException(" there is no expression ");
		
		 //if expresssion is not correct 5+5*  .
		 ///////////////////////////////////////////////
		 if(!(Character.isDigit(expression.charAt(j-1)))&&expression.charAt(j-1)!=')'&&!Character.isLetter(expression.charAt(j-1))&&expression.charAt(j-1)!=' ')
		 	throw new RuntimeException(" invalid expression ");
			
		 //if the parenthese not right 5+(5*3
		 ///////////////////////////////////////////////////////////////////
		 check_parenthese(expression);
		 
		 // for spaces
		 ////////////////////////////////////////////////////////////////////
		 expression=remove_space(expression);
		 special_char(expression);
		 //////////////////////////////////////////////////////////////////
		 expression=check_operator(expression);
		 //////////////////////////////////////////////////////////////////\
		
		return expression;	
	}
	
	
	/* 
	 * Takes a symbolic/numeric infix expression as input and converts it to 
	 * postfix notation. There is no assumption on spaces between terms or the 
	 * length of the term (e.g., two digits symbolic or numeric term) 
	 * @param expression 
	 * infix expression 
	 * @return postfix expression 
	 */
	@Override
	public String infixToPostfix(String expression) throws RuntimeException {
		expression=check_expresion(expression);
		String new_string=new String();
		boolean minus_befor_paten=false;
		int num_of_min=0;
		int i=0;
		int num_paren=0;
		boolean n=false;
		boolean p=false;
		stack user=new stack();
		while(i<expression.length()) {
			if(num_paren!=0)
				p=true;
			
			if((expression.charAt(i)=='-')&&(i==0)&&expression.charAt(i+1)!='('){
				new_string+='0';
				new_string+=' ';
				i++;
				while(i<expression.length()) {
					if(Character.isDigit(expression.charAt(i))||Character.isLetter(expression.charAt(i))||expression.charAt(i)=='.') {
						new_string+=expression.charAt(i);
						n=true;
						i++;
					}
					else
						break;
				}
				new_string+=' ';
				new_string+='-';
				new_string+=' ';
			}
			
			else if(expression.charAt(i)== '('){
				num_paren++;
				user.push(expression.charAt(i));
				p=true;
				
				if(expression.charAt(i+1)=='-'&&expression.charAt(i+2)!='('){
					new_string+='0';
					new_string+=' ';
					i+=2;
					while(i<expression.length()) {
						if(Character.isDigit(expression.charAt(i))||Character.isLetter(expression.charAt(i))||expression.charAt(i)=='.') {
							new_string+=expression.charAt(i);
							n=true;
							i++;
						}
						else
							break;
					}
					new_string+=' ';
					new_string+='-';
					new_string+=' ';
				}
				else if(expression.charAt(i+1)=='-'&&expression.charAt(i+2)=='('){
					new_string+='0';
					new_string+=' ';
					minus_befor_paten=true;
					num_of_min++;
					i++;
				}
				
			}
			
			else if((expression.charAt(i)=='*'||expression.charAt(i)=='-'||expression.charAt(i)=='/'||expression.charAt(i)=='+')&&!p) {
				if((user.isEmpty())&&(expression.charAt(i)=='+'||expression.charAt(i)=='-')) {
					if(expression.charAt(i)=='-'&&expression.charAt(i+1)=='(') {
						if(i==0) {
							new_string+='0';
							new_string+=' ';
							minus_befor_paten=true;
							num_of_min++;
						}
						else if(!(Character.isDigit(expression.charAt(i-1))||Character.isLetter(expression.charAt(i-1)))){
							new_string+='0';
							new_string+=' ';
							minus_befor_paten=true;
							num_of_min++;
						}
					}
					else
						user.push(expression.charAt(i));
					
				}
				else if(!(user.isEmpty())&&(expression.charAt(i)=='+'||expression.charAt(i)=='-')) {
					if(expression.charAt(i)=='-'&&expression.charAt(i+1)!='('&&!(Character.isDigit(expression.charAt(i-1))||Character.isLetter(expression.charAt(i-1)))) {
						new_string+='0';
						new_string+=' ';
						i++;
						while(i<expression.length()) {
							if(Character.isDigit(expression.charAt(i))||Character.isLetter(expression.charAt(i))||expression.charAt(i)=='.') {
								new_string+=expression.charAt(i);
								n=true;
								i++;
							}
							else
								break;
						}
						new_string+=' ';
						new_string+='-';
						new_string+=' ';
					}
					else if(expression.charAt(i)=='-'&&expression.charAt(i+1)=='('&&!(Character.isDigit(expression.charAt(i-1))||Character.isLetter(expression.charAt(i-1)))) {
						new_string+='0';
						new_string+=' ';
						minus_befor_paten=true;
						num_of_min++;
					}
					else {
						while(!user.isEmpty()) {
							new_string+=user.pop();
							new_string+=' ';
						}
						user.push(expression.charAt(i));
					}
				}
				else if(!(user.isEmpty())&&(expression.charAt(i)=='*'||expression.charAt(i)=='/')) {
					if(!user.isEmpty()&&((char)user.peek()=='/'||(char)user.peek()=='*')) {
						new_string+=user.pop();
						new_string+=' ';
						user.push(expression.charAt(i));
					}
					else {
						user.push(expression.charAt(i));
					}	
				}
				else if(user.isEmpty()&&(expression.charAt(i)=='*'||expression.charAt(i)=='/')) {
							user.push(expression.charAt(i));
				}
								
			}
			else if((expression.charAt(i)=='*'||expression.charAt(i)=='-'||expression.charAt(i)=='/'||expression.charAt(i)=='+')&&p) {
				if(!(user.isEmpty())&&(expression.charAt(i)=='*'||expression.charAt(i)=='/')) {
					if(!user.isEmpty()&&((char)user.peek()=='/'||(char)user.peek()=='*')) {
						new_string+=user.pop();
						new_string+=' ';
						user.push(expression.charAt(i));
					}
					else {
						user.push(expression.charAt(i));
					}	
				}
				else if(!(user.isEmpty())&&(expression.charAt(i)=='+'||expression.charAt(i)=='-')) {
					if(expression.charAt(i)=='-'&&expression.charAt(i+1)!='('&&!(Character.isDigit(expression.charAt(i-1))||Character.isLetter(expression.charAt(i-1)))) {
						new_string+='0';
						new_string+=' ';
						i++;
						while(i<expression.length()) {
							if(Character.isDigit(expression.charAt(i))||Character.isLetter(expression.charAt(i))||expression.charAt(i)=='.') {
								new_string+=expression.charAt(i);
								n=true;
								i++;
							}
							else
								break;
						}
						new_string+=' ';
						new_string+='-';
						new_string+=' ';
					}
					else if(expression.charAt(i)=='-'&&expression.charAt(i+1)=='('&&!(Character.isDigit(expression.charAt(i-1))||Character.isLetter(expression.charAt(i-1)))) {
							new_string+='0';
							new_string+=' ';
							minus_befor_paten=true;
							num_of_min++;
					}
					else {
						while(true) {
							if(!user.isEmpty()&&(char)user.peek()!='(') {
								new_string+=user.pop();
								new_string+=' ';
							}
							else {
								break;
								}
						}
						user.push(expression.charAt(i));
					}
				}
			}
			else if(expression.charAt(i)==')') {
				while(true) {
					if(!user.isEmpty()&&(char)user.peek()!='(') {
						new_string+=user.pop();
						new_string+=' ';
					}
					else
						break;
				}
				p=false;
				num_paren--;
				if(!user.isEmpty()&&(char)user.peek()=='(')
					user.pop();
				if(minus_befor_paten) {
					num_of_min--;
					new_string+='-';
					new_string+=' ';
					minus_befor_paten=false;
				}
				if(num_of_min!=0)
					minus_befor_paten=true;
			}
			else {
				while(i<expression.length()) {
					if(Character.isDigit(expression.charAt(i))||Character.isLetter(expression.charAt(i))||expression.charAt(i)=='.') {
						new_string+=expression.charAt(i);
						n=true;
						i++;
					}
					else
						break;
				}
				new_string+=' ';
			}
			if(n) {
				n=false;
			}
			else
				i++;
			
		}
		
		while(!user.isEmpty()) {
			if(user.size()!=1) {
				new_string+=user.pop();
				new_string+=' ';
			}
			else {
				new_string+=user.pop();
			}
		}
		
		return new_string;
	}
	
	/* Evaluate a postfix numeric expression, with a single space separator 
	 * @param expression 
	 * postfix expression 
	 * @return the expression evaluated value 
	 */
	@Override
	public int evaluate(String expression) throws RuntimeException  {
		String new_str = expression;
		stack evalute=new stack();
		int i=0;
		while(i<new_str.length()) {
			if(Character.isLetter(expression.charAt(i)))
				throw new RuntimeException(" there is a char in expression ");
			i++;
		}
		i=0;
		while(i<new_str.length()) {
			if((expression.charAt(i)=='-'&&i+1<new_str.length()&&Character.isDigit(new_str.charAt(i+1)))||Character.isDigit(new_str.charAt(i))){
				int c=1;
				if(expression.charAt(i)=='-') {
					c=-1;
					i++;
				}
				int push_num = 0;
				if(i+1<new_str.length()&&Character.isDigit(new_str.charAt(i+1))) {
					while(i<new_str.length()) {
						if(Character.isDigit(new_str.charAt(i))) {
							push_num*=10;
							push_num+=Character.getNumericValue(new_str.charAt(i));
							i++;
						}
						else
							break;
					}
					if(c==-1)
						evalute.push(-1*(double)push_num);
					else
						evalute.push((double)push_num);
				}
				else
					if(c==-1)
						evalute.push(-1*(double)Character.getNumericValue(new_str.charAt(i)));
					else
						evalute.push((double)Character.getNumericValue(new_str.charAt(i)));
			}
			else if (new_str.charAt(i)=='*'||new_str.charAt(i)=='+'||new_str.charAt(i)=='-'||new_str.charAt(i)=='/') {
				double a = 0,b=0;
				if(!evalute.isEmpty())
					a=(double) evalute.pop();
				if(!evalute.isEmpty())
					b=(double) evalute.pop();
					switch(new_str.charAt(i)){
						case'*':
							evalute.push((double)b*a);
							break;
						case'/':
							evalute.push((double)b/a);
							break;
						case'+':
							evalute.push(a+b);
							break;
						case'-':
							evalute.push(b-a);
							break;
					}
				}
			i++;
			
		}
		double result=0;
		if(!evalute.isEmpty())
			 result=(double) evalute.pop();
		return (int) result;
	}
}
