package drawing.border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import layout.demo.tool.ExitableJFrame;

public class BorderDemo extends JFrame {
	static JPanel showBorder(Border b) {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		String nm = b.getClass().getSimpleName();
		jp.add(BorderLayout.CENTER, new JLabel(nm, JLabel.CENTER));
		jp.setBorder(b);
		return jp;
	}

	public BorderDemo() {
		setLayout(new GridLayout(2, 4));
		
		add(showBorder(new TitledBorder("Title")));
		add(showBorder(new EtchedBorder()));
		add(showBorder(new LineBorder(Color.blue)));
		add(showBorder(new MatteBorder(5, 5, 30, 30, Color.green)));
		add(showBorder(new BevelBorder(BevelBorder.RAISED)));
		add(showBorder(new SoftBevelBorder(BevelBorder.LOWERED)));
		add(showBorder(new CompoundBorder(new EtchedBorder(), new LineBorder(Color.red))));
	}

	public static void main(String[] args) {
		ExitableJFrame.run(new BorderDemo(), 500, 300);
	}
}
