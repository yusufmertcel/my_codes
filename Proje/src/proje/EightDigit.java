package proje;

public class EightDigit extends EquationGenerator{
	char[] operators = {'*','+','-','/'};
	@Override
	public String randomEquationgenerator() {
		int result = -100;
		int op1;
		int op2;
		char operator;
		//int length = String.valueOf(op1).length(); // 2 1 
		int locationofequal = random.nextInt(3) + 4;
		//System.out.println(locationofequal);
		while(!((String.valueOf(result).length() == 1 && locationofequal == 6) || (String.valueOf(result).length() == 2 && locationofequal == 5) || (String.valueOf(result).length() == 3 && locationofequal == 4))) 
		{
			result = 0;
			//System.out.println(locationofequal);
			if(locationofequal == 4) {
				op1 = random.nextInt(90)+10;
				op2 =random.nextInt(10);
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
			else if(locationofequal == 5) {
				//System.out.println("girdi");
				op1 = random.nextInt(990)+10;
				
				//System.out.println(operator);
				if(String.valueOf(op1).length() == 3) {
					op2 =random.nextInt(10)+1;
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
					op2 =random.nextInt(90)+10;
					operator = operators[random.nextInt(2)+1];
					switch(operator) {
					case '+':
						result = op1 + op2;
						equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						break;
					case '-':
						if(op1 > op2) {
							result = op1 - op2;
							equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						}
						else {
							result = op2 - op1;
							equation = String.valueOf(op2) + operator + String.valueOf(op1) + '=' + String.valueOf(result);
						}
						break;
					}
				}
			}
			else if(locationofequal == 6) {
				op1 = random.nextInt(900)+100;
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
		}
		
		return equation;
	}
	
}
