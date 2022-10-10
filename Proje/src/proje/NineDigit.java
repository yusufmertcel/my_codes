package proje;

public class NineDigit extends EquationGenerator{
	char[] operators = {'*','+','-','/'};
	@Override
	public String randomEquationgenerator() {
		int result = -100;
		int op1;
		int op2,op3;
		char operator;
		
		int locationofequal = random.nextInt(3) + 5;
		//System.out.println(locationofequal);
		while(!((String.valueOf(result).length() == 1 && locationofequal == 7) || (String.valueOf(result).length() == 2 && locationofequal == 6) || (String.valueOf(result).length() == 3 && locationofequal == 5))) 
		{
			result = 0;
			if(locationofequal == 5) {
				op1 = random.nextInt(900)+100;
				if(String.valueOf(op1).length() == 2) {
					op2 = random.nextInt(90)+10;
					operator = operators[random.nextInt(2)];
					switch(operator) {
					case '+':
						result = op1 + op2;
						equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						break;
					case '*':
						result = op1 * op2;
						equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						break;
					}
				}
				else if (String.valueOf(op1).length() == 3) {
					op2 = random.nextInt(10);
					operator = operators[random.nextInt(2)];
					switch(operator) {
					case '+':
						result = op1 + op2;
						equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						break;
					case '*':
						result = op1 * op2;
						equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						break;
					case '-':
						result = op1 - op2;
						equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						break;
					case '/':
						if(op1 % op2 == 0) {
							result = op1/op2;
							equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						}
						else
							result = -100;
						break;
					}
					}
				}
			else if(locationofequal == 6) {
				//System.out.println("girdi");
				op1 = random.nextInt(990)+10;
				
				//System.out.println(operator);
				if(String.valueOf(op1).length() == 3) {
					op2 =random.nextInt(90)+10;
					operator = operators[random.nextInt(2)+2];
					switch(operator) {
					case '-':
						result = op1 - op2;
						equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						break;
					case '/':
						if(op1 % op2 == 0) {
							result = op1/op2;
							equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						}
						else
							result = -100;
						break;
					}
				}
				else if(String.valueOf(op1).length() == 2) {
					equation = String.valueOf(op1);
					result = op1;
					for(int i = 0;i < 2;i++) {
						op2 =random.nextInt(10) + 1;
						operator = operators[random.nextInt(3)+1];
						switch(operator) {
						case '+':
							result = result + op2;
							equation = equation + operator + String.valueOf(op2);
							break;
						case '-':
							result = result - op2;
							equation = equation + operator + String.valueOf(op2);
							break;
						case '/':
							if(op1 % op2 == 0) {
								result = result / op2;
								equation = equation + operator + String.valueOf(op2);
							}
							else {
								i--;
							}
							break;
						}
					}
					equation = equation + '=' + String.valueOf(result);
				}
			}
			else if(locationofequal == 7) {
				op1 = random.nextInt(90)+10;
				op2 =random.nextInt(90)+10;
				op3 = random.nextInt(10);
				if(String.valueOf(op1).length() == 1) {
					equation = String.valueOf(op1);
					result = op1;
					for(int i = 0;i < 4;i++) {
						op2 =random.nextInt(10);
						operator = operators[random.nextInt(3)+1];
						switch(operator) {
						case '+':
							result = result + op2;
							equation = equation + operator + String.valueOf(op2);
							break;
						case '-':
							result = result - op2;
							equation = equation + operator + String.valueOf(op2);
							break;
						case '/':
							if(op1 % op2 == 0) {
								result = result / op2;
								equation = equation + operator + String.valueOf(op2);
							}
							break;
						}
					}
				}
				else if(String.valueOf(op1).length() == 2) {
					op2 =random.nextInt(90)+10;
					op3 =random.nextInt(10);
					equation = String.valueOf(op1);
					result = op1;
					operator = operators[random.nextInt(3)+1];
					switch(operator) {
					case '+':
						result = result + op2 - op3;
						equation = equation + operator + String.valueOf(op2) +'-'+ String.valueOf(op3);
						break;
					case '-':
						if(result > op2) {
							result = result - op2 + op3;
							equation = equation + operator + String.valueOf(op2)+'+'+String.valueOf(op3);
						}
						else {
							result = op2 - result + op3;
							equation = String.valueOf(op2) + operator + equation+'+'+String.valueOf(op3);
						}
						
						break;
					case '/':
						if(op1 % op2 == 0) {
							result = result / op2 + op3;
							equation = equation + operator + String.valueOf(op2)+'+'+String.valueOf(op3);
						}
						break;
					}
					equation = equation + '=' + String.valueOf(result);
				}
				else if(String.valueOf(op1).length() == 3) {
					op2 =random.nextInt(900)+100;
					operator = operators[random.nextInt(2)+2];
					equation = String.valueOf(op1);
					result = op1;
					switch(operator) {
					case '-':
						result = result - op2;
						equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						break;
					case '/':
						if(op1 % op2 == 0) {
							result = result/op2;
							equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						}
						else
							result = -100;
						break;
					}
				}
			}
		}
		
		return equation;
		
	}

}
