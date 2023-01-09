package layout.demo.tool;

import java.awt.Dimension;

import javax.swing.JButton;

public class BigButton extends JButton {
	public BigButton(String label) {
		super(label);
	}

	public Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		System.out.println("getPreferredSize(): " + getActionCommand());
		return new Dimension(d.width + 20, d.height + 20);
	}

	public Dimension getMaximumSize() {
		Dimension d = super.getMaximumSize();
		System.out.println("getMaximumSize(): " + getActionCommand());
		return new Dimension(d.width + 20, d.height + 20);
	}

	public Dimension getMinimumSize() {
		Dimension d = super.getMinimumSize();
		System.out.println("getMinimumSize(): " + getActionCommand());
		return new Dimension(d.width + 20, d.height + 20);
	}
}