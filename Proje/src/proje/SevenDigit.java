package proje;

public class SevenDigit extends EquationGenerator{
	@Override
	public String randomEquationgenerator() {
		int result = -10;
		int op1 = random.nextInt(90)+10;
		int op2;
		char operator;
		//int length = String.valueOf(op1).length(); // 2 1 
		int locationofequal = random.nextInt(2) + 4;
		//System.out.println(locationofequal);
		while(!((String.valueOf(result).length() == 1 && locationofequal == 5) || (String.valueOf(result).length() == 2 && locationofequal == 4))) 
		{
			result = 0;
			if(locationofequal == 4) {
				op2 =random.nextInt(10)+1;
				operator = operators[random.nextInt(4)];
				switch(operator) {
				case '+':
					result = op1 + op2;
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
						result = -10;
					break;
				case '*':
					result = op1 * op2;
					equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
					break;
				}
			}
			else if(locationofequal == 5) {
				//System.out.println("girdi");
				operator = operators[random.nextInt(2)+2];
				//System.out.println(operator);
				op2 =random.nextInt(90)+10;
				if(operator == '-') {
					if(op1 > op2) {
						result = op1 - op2;
						equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
					}
					else {
						result = op2 - op1;
						equation = String.valueOf(op2) + operator + String.valueOf(op1) + '=' + String.valueOf(result);
					}
				}
				else if(operator == '/') {
					if(op1 > op2) {
						if(op1 % op2 == 0) {
							result = op1 / op2;
							equation = String.valueOf(op1) + operator + String.valueOf(op2) + '=' + String.valueOf(result);
						}
						else 
							result = -20;
					}
					else {
						if(op2 % op1 == 0) {
							result = op2 / op1;
							equation = String.valueOf(op2) + operator + String.valueOf(op1) + '=' + String.valueOf(result);
						}
						else 
							result = -20;
					}
				}
			}
		}
		
		return equation;
	}
}
