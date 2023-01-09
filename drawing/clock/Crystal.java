package drawing.clock;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Crystal extends JPanel {
	private Color ON;
	private Color OFF;
	private boolean isOn;

	public Crystal(Color c) {
		this(c, true);
	}

	public Crystal(Color c, boolean state) {
		ON = c;
		OFF = ON.darker().darker().darker().darker();
		isOn = state;
	}

	public void setState(boolean state) {
		isOn = state;
	}

	public boolean getState() {
		return isOn;
	}

	public void setColor(Color c) {
		ON = c;
		OFF = ON.darker().darker().darker().darker();
	}

	public Color getColor() {
		return ON;
	}

	@Override
	public void paintComponent(Graphics g) {
		int w = getSize().width;
		int h = getSize().height;
		int arc = Math.min(w, h) / 2;

		Color c = isOn ? ON : OFF;
		g.setColor(c.darker());
		g.fillRoundRect(1, 1, w, h, arc, arc);
		g.setColor(c);
		g.drawRoundRect(1, 1, w, h, arc, arc);
	}
}
