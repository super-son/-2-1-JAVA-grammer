package drawing.clock;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import layout.custom.ProportionLayout;

public class ColonPanel extends JPanel {
	private final static int[] cols = { 1 };
	private final static int[] rows = { 5, 1, 5, 1, 5 };
	private Crystal[] dots = new Crystal[2];
	private BackPanel[] backPanel = new BackPanel[3];
	private boolean ON;

	public ColonPanel(Color c, Color back) {
		this(c, back, true);
	}

	public ColonPanel(Color c, Color back, boolean on) {
		ON = on;
		for (int i = 0; i < dots.length; ++i)
			dots[i] = new Crystal(c, ON);
		for (int i = 0; i < backPanel.length; ++i)
			backPanel[i] = new BackPanel(back);

		setBackground(back);
		setForeground(back);
		setLayout(new ProportionLayout(rows, cols));

		add(backPanel[0]);
		add(dots[0]);
		add(backPanel[1]);
		add(dots[1]);
		add(backPanel[2]);
	}

	public void setBackLight(Color c) {
		setBackground(c);
		setForeground(c);
		for (int i = 0; i < backPanel.length; ++i)
			backPanel[i].setColor(c);
	}

	public Color getBackLight() {
		return backPanel[0].getColor();
	}

	public void setCrystalColor(Color c) {
		dots[0].setColor(c);
		dots[1].setColor(c);
	}

	public Color getCrystalColor() {
		return dots[0].getColor();
	}

	public boolean getState() {
		return ON;
	}

	public void setState(boolean isOn) {
		ON = isOn;
		dots[0].setState(ON);
		dots[1].setState(ON);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, (int) getSize().getWidth(), (int) getSize().getHeight());
		for (int i = 0; i < dots.length; ++i)
			dots[i].repaint();
	}
}
