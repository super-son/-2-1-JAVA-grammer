package layout.demo;

import javax.swing.JFrame;

import layout.custom.CastleLayout;
import layout.demo.tool.BigButton;
import layout.demo.tool.ExitableJFrame;

public class CastleLayoutDemo extends JFrame {
	public CastleLayoutDemo(String title) {
		super(title);

		setLayout(new CastleLayout(5, 5, true));
		add(CastleLayout.NORTHWEST, new BigButton("Button1"));
		add(CastleLayout.NORTH, new BigButton("Button2"));
		add(CastleLayout.NORTHEAST, new BigButton("Button3"));
		add(CastleLayout.WEST, new BigButton("Button4"));
		add(CastleLayout.CENTER, new BigButton("Button5"));
		add(CastleLayout.EAST, new BigButton("Button6"));
		add(CastleLayout.SOUTHWEST, new BigButton("Button7"));
		add(CastleLayout.SOUTH, new BigButton("Button8"));
		add(CastleLayout.SOUTHEAST, new BigButton("Button9"));
		pack();
		ExitableJFrame.run(this, this.getWidth(), this.getHeight());
	}

	public static void main(String[] args) {
		new CastleLayoutDemo("CastleLayout Demo");
	}
}
