package proje;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class TestAll extends TestCase{

	@Test
	public void testSevenDigit() {
		String a;
		SevenDigit x = new SevenDigit();
		a = x.randomEquationgenerator();
		assertTrue("Bu denklem yedi digit uzunlugunda deðil.",a.length() == 7);
	}
	
	@Test
	public void testEightDigit() {
		String a;
		EightDigit x = new EightDigit();
		a = x.randomEquationgenerator();
		assertTrue("Bu denklem sekiz digit uzunlugunda deðil.",a.length() == 8);
	}
	
	@Test
	public void testNineDigit() {
		String a;
		NineDigit x = new NineDigit();
		a = x.randomEquationgenerator();
		assertTrue("Bu denklem dokuz digit uzunlugunda deðil.",a.length() == 9);
	}	
	
	@Test
	public void testNumbersofequal() {
		String a;
		int per = 0;
		SevenDigit x = new SevenDigit();
		a = x.randomEquationgenerator();
		for(int i = 0 ; i < a.length() ;i++) {
			if(a.charAt(i) == '=')
				per++;
		}
		assertFalse("Bu denklemde birden fazla eþittir iþareti var",per > 1);
	}
	
	@Test
	public void testFirsDigit() {
		String a;
		NineDigit x = new NineDigit();
		a = x.randomEquationgenerator();
		assertFalse("Bu denklem bir operatorle baþlayamaz.",a.charAt(0) == '+' || a.charAt(0) == '-' || a.charAt(0) == '*' || a.charAt(0 ) == '/');
	}
	
	@Test
	public void testConsecutiveOperator() {
		String a;
		String operators = "+-*/";
		EightDigit x = new EightDigit();
		char symbol;
		a = x.randomEquationgenerator();
		for(int i = 0;i<a.length();i++) {
			symbol = a.charAt(i);
			int j = i+1;
			int number = 0;
			while(j<a.length() && (symbol == operators.charAt(0) || symbol == operators.charAt(1) || symbol == operators.charAt(2) || symbol == operators.charAt(3))) {
				symbol = a.charAt(j);
				number++;
				j++;
			}
			System.out.println(number);
			assertFalse("Art arda iki operator olamaz",number>1);
		}
	}
}
