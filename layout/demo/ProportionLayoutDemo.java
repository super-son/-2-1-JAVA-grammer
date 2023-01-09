package layout.demo;

import javax.swing.JFrame;

import layout.custom.ProportionLayout;
import layout.demo.tool.BigButton;
import layout.demo.tool.ExitableJFrame;

public class ProportionLayoutDemo extends JFrame {
	public ProportionLayoutDemo(String title) {
		super(title);

		int[] rows = { 1, 2 };
		int[] cols = { 1, 2, 1 };
		setLayout(new ProportionLayout(rows, cols));

		add(new BigButton("Button1"));
		add(new BigButton("Button2"));
		add(new BigButton("Button3"));
		add(new BigButton("Button4"));
		add(new BigButton("Button5"));
		add(new BigButton("Button6"));
		pack();
		ExitableJFrame.run(this, this.getWidth(), this.getHeight());
	}

	public static void main(String[] args) {
		new ProportionLayoutDemo("ProportionLayout Demo");
	}
}
