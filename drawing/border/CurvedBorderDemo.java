package drawing.border;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import layout.demo.tool.ExitableJFrame;

public class CurvedBorderDemo extends JFrame {
	public CurvedBorderDemo() {
		JPanel jp = new JPanel();
		jp.setBorder(new CurvedBorder(10, Color.darkGray));

		ImageIcon icon = new ImageIcon(getClass().getResource("T1.png"));
		JLabel label = new JLabel("Custom Border", icon, JLabel.RIGHT);
		label.setBorder(new CurvedBorder(5, Color.blue));
		jp.add(label);

		add(BorderLayout.CENTER, jp);
	}

	public static void main(String s[]) {
		ExitableJFrame.run(new CurvedBorderDemo(), 400, 300);
	}
}