package layout.demo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import layout.custom.ColumnLayout;
import layout.demo.tool.ExitableJFrame;

public class ColumnLayoutDemo extends JFrame {
	public ColumnLayoutDemo(String title) {
		super(title);

		setLayout(new ColumnLayout(5, 5, true, true));
		add(new JLabel("Test"));
		add(new JTextField(20));
		add(new JLabel("Long Long Long :"));
		add(new JTextField(30));
//		add(new JTextArea("This :", 20, 50));
		add(new JScrollPane(new JTextArea("This :", 20, 50)));
		pack();
		
		ExitableJFrame.run(this, this.getWidth(), this.getHeight());
	}

	public static void main(String[] args) {
		new ColumnLayoutDemo("ColumnLayout Demo");
	}
}
