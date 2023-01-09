package drawing.clock;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import layout.custom.ProportionLayout;

public class LCDPanel extends JPanel {
	private final static int[] rows = { 1 };
	private final static int[] cols = { 5, 5, 1, 5, 5 };
	private DigitPanel[] digits = new DigitPanel[4];
	private ColonPanel colons;
	private boolean ON;

	public LCDPanel(Color crystal, Color backlight) {
		this(crystal, backlight, true);
	}

	public LCDPanel(Color crystal, Color backlight, boolean on) {
		ON = on;
		setBackground(backlight);
		setLayout(new ProportionLayout(rows, cols, 0, 1));

		for (int i = 0; i < digits.length; ++i)
			digits[i] = new DigitPanel(crystal, backlight, ON);
		colons = new ColonPanel(crystal, backlight, ON);

		add(digits[0]);
		add(digits[1]);
		add(colons);
		add(digits[2]);
		add(digits[3]);
	}

	public void setDigitOn(int index, boolean b) {
		digits[index].setDigitOn(b);
	}

	public boolean isDigitOn(int index) {
		return digits[index].isDigitOn();
	}

	public void setColonOn(boolean b) {
		colons.setState(b);
	}

	public boolean isColonOn() {
		return colons.getState();
	}

	public void setDigit(int index, char c) {
		digits[index].setDigit(c);
	}

	public char getDigit(int index) {
		return digits[index].getDigit();
	}

	public void setLCDOn(boolean b) {
		ON = b;
		for (int i = 0; i < digits.length; ++i)
			digits[i].setDigitOn(ON);
		colons.setState(ON);
	}

	public boolean isLCDOn() {
		return ON;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, (int) getSize().getWidth(), (int) getSize().getHeight());
		for (int i = 0; i < digits.length; ++i)
			digits[i].repaint();
		colons.repaint();
	}
}
