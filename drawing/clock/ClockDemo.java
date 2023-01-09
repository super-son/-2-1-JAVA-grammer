package drawing.clock;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import layout.custom.CastleLayout;
import layout.demo.tool.ExitableJFrame;

public class ClockDemo extends JFrame {
	public ClockDemo(String title) {
		super(title);
		DigitalClock c = new DigitalClock(Color.green, Color.black);

		setLayout(new CastleLayout());
		add(CastleLayout.NORTHEAST, outlinePanel());
		add(CastleLayout.NORTHWEST, outlinePanel());
		add(CastleLayout.SOUTHEAST, outlinePanel());
		add(CastleLayout.SOUTHWEST, outlinePanel());
		add(CastleLayout.NORTH, outlinePanel());
		add(CastleLayout.SOUTH, outlinePanel());
		add(CastleLayout.EAST, outlinePanel());
		add(CastleLayout.WEST, outlinePanel());
		add(CastleLayout.CENTER, c);

		setResizable(false);
		ExitableJFrame.run(this, 400, 230);
		
		new Thread(c).start();
	}

	private JPanel outlinePanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder());
		panel.setPreferredSize(new Dimension(12, 12));
		return panel;
	}

	public static void main(String[] args) {
		new ClockDemo("Digital Clock");
	}
}
