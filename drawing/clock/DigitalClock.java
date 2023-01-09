package drawing.clock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Calendar;

import javax.swing.JPanel;

public class DigitalClock extends JPanel implements Runnable {
	private LCDPanel display;
	private int HOUR, MINUTE, SECOND;
	private boolean TICK = true;

	public DigitalClock(Color crystal, Color back) {
		display = new LCDPanel(crystal, back);
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, display);
	}

	public void run() {
		while (true) {
			Calendar now = Calendar.getInstance();

			try {
				HOUR = now.get(Calendar.HOUR);
				MINUTE = now.get(Calendar.MINUTE);
				SECOND = now.get(Calendar.SECOND);

				TICK = !TICK;
				display.setColonOn(TICK);

				String h = Integer.toString(HOUR);
				String m = Integer.toString(MINUTE);
				if (HOUR >= 10) {
					display.setDigit(0, h.charAt(0));
					display.setDigit(1, h.charAt(1));
				} else {
					display.setDigitOn(0, false);
					display.setDigit(1, h.charAt(0));
				}
				if (MINUTE >= 10) {
					display.setDigit(2, m.charAt(0));
					display.setDigit(3, m.charAt(1));
				} else {
					display.setDigitOn(2, false);
					display.setDigit(3, m.charAt(0));
				}

				display.repaint();
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}
}
