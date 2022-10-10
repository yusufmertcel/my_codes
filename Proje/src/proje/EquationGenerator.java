package proje;

import java.util.Random;

public abstract class EquationGenerator{
	Random random = new Random();
	char[] operators = {'+','*','-','/'};
	String equation;
	
	public abstract String randomEquationgenerator();
	
}
