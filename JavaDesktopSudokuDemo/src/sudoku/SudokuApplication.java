package sudoku;

import javax.
public class SudokuApplication {
	private IUserInterfaceContract.View uiImpl;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		uiImpl = new UserInterfaceImpl(primaryStage);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
