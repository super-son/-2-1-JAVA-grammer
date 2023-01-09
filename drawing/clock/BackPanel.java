package drawing.clock;

import java.awt.Color;

import javax.swing.JPanel;

public class BackPanel extends JPanel {
	private Color BACKLIGHT;

	public BackPanel(Color c) {
		BACKLIGHT = c;
		setBackground(BACKLIGHT);
	}

	public void setColor(Color c) {
		BACKLIGHT = c;
		setBackground(BACKLIGHT);
	}

	public Color getColor() {
		return BACKLIGHT;
	}
}
