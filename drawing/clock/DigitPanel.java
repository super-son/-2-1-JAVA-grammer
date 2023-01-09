package drawing.clock;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import layout.custom.ProportionLayout;

public class DigitPanel extends JPanel {
	private final static int[] rows = { 1, 5, 1, 5, 1 };
	private final static int[] cols = { 1, 5, 1 };
	private Crystal[] segment = new Crystal[7];
	private BackPanel[] backPanel = new BackPanel[8];
	private char digit;
	private boolean ON;

	public DigitPanel(Color c, Color back) {
		this(c, back, true);
	}

	public DigitPanel(Color c, Color back, boolean on) {
		this(c, back, on, '8');
	}

	public DigitPanel(Color c, Color back, boolean on, char num) {
		digit = num;
		ON = on;
		setBackground(back);
		setForeground(back);
		setLayout(new ProportionLayout(rows, cols, 1, 1));

		for (int i = 0; i < segment.length; ++i)
			segment[i] = new Crystal(c, ON ? isOn(num, i) : false);
		for (int i = 0; i < backPanel.length; ++i)
			backPanel[i] = new BackPanel(back);

		add(backPanel[0]);
		add(segment[0]);
		add(backPanel[1]);
		add(segment[1]);
		add(backPanel[2]);
		add(segment[2]);
		add(backPanel[3]);
		add(segment[3]);
		add(backPanel[4]);
		add(segment[4]);
		add(backPanel[5]);
		add(segment[5]);
		add(backPanel[6]);
		add(segment[6]);
		add(backPanel[7]);
	}

	public boolean isDigitOn() {
		return ON;
	}

	public void setDigitOn(boolean on) {
		ON = on;
		for (int i = 0; i < segment.length; ++i)
			segment[i].setState(ON ? isOn(digit, i) : false);
	}

	private boolean isOn(char num, int pos) {
		switch (pos) {
		case 0:
			return "02356789".indexOf(num) > -1 ? true : false;
		case 1:
			return "045689".indexOf(num) > -1 ? true : false;
		case 2:
			return "01234789".indexOf(num) > -1 ? true : false;
		case 3:
			return "2345689".indexOf(num) > -1 ? true : false;
		case 4:
			return "02689".indexOf(num) > -1 ? true : false;
		case 5:
			return "013456789".indexOf(num) > -1 ? true : false;
		case 6:
			return "0235689".indexOf(num) > -1 ? true : false;
		default:
			return false;
		}
	}

	public void setDigit(char num) {
		digit = num;
		for (int i = 0; i < segment.length; ++i)
			segment[i].setState(ON ? isOn(num, i) : false);
	}

	public char getDigit() {
		return digit;
	}

	public void setCrystalColor(Color c) {
		for (int i = 0; i < segment.length; ++i)
			segment[i].setColor(c);
	}

	public Color getCrystalColor() {
		return segment[0].getColor();
	}

	public void setBackLight(Color back) {
		setBackground(back);
		setForeground(back);
		for (int i = 0; i < backPanel.length; ++i)
			backPanel[i].setColor(back);
	}

	public Color getBackLight() {
		return backPanel[0].getColor();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, (int) getSize().getWidth(), (int) getSize().getHeight());
		for (int i = 0; i < segment.length; ++i)
			segment[i].repaint();
	}
}
