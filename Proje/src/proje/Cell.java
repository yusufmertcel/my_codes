package proje;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class Cell extends JTextField implements MouseListener,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3435764672756869640L;
	//public static final Color BG_GIVEN = new Color(240,240,240);
	//public static final Color FG_GIVEN = Color.black;
	public transient static final Color BG_TO_GUESS = Color.white;
	public transient static final Color BG_GUEESED = Color.DARK_GRAY;
	public transient static final Color BG_CORRECT_PLACE = new Color(0,216,0);
	public transient static final Color BG_CORRECT_GUESS = new Color(120,120,120);
	public transient static final Color BG_WRONG_GUESS = new Color(216,0,0);
	public transient static final Font FONT_NUMBERS = new Font("Monospaced",Font.BOLD,24);
	int row,col;
	String number;
	
	public Cell(int row,int col) {
		super(); //Jtextfield
		
		this.row = row;
		this.col = col;
		super.setHorizontalAlignment(JTextField.CENTER);
		super.setText(null);
	    super.setFont(FONT_NUMBERS);
	    super.setBackground(BG_TO_GUESS);
	    super.setEditable(false);
	    super.setBounds(0, 0, 50, 50);
	    super.addMouseListener(this);
	    super.setBorder(BorderFactory.createEmptyBorder());
	
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean StatusofCell(String a,int colsize,int colnumber) {
		int i = 0;
		char cellsymbol = this.getText().charAt(0);
		//System.out.println(cellsymbol);
		while(i<colsize && (cellsymbol != a.charAt(i))) {
			i++;
		}
		//System.out.println("Girdi");
		if(cellsymbol == a.charAt(colnumber)) {
			//System.out.println("Girdi2");
			this.setBackground(BG_CORRECT_PLACE);
			return true;
		}
		else if(i<colsize && cellsymbol == a.charAt(i)) {
			//System.out.println("Girdi3");
			this.setBackground(BG_CORRECT_GUESS);
			return false;
		}
		else {
			this.setBackground(BG_GUEESED);
			return false;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(this)){
			gamescreen.lastSelectedCol = gamescreen.getCurrentPosition();
			//System.out.println("Girdi1");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		super.setBackground(new Color(0xACDDDE));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		super.setBackground(new Color(0xFFFFFF));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createEtchedBorder());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createEmptyBorder());
		//super.setBackground(Color.white);
	}

}
