package layout.demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import layout.demo.tool.Holder;

public class FrameGeometry extends JFrame {
	public static void main(String[] args) {
		JButton b = new JButton("Button Component");

		FrameGeometry fg = new FrameGeometry();
		fg.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent e) {
	            System.exit(0);
	         }
	      });
		
		printGeometry(fg);
		printGeometry(b);

		Holder.pause("\n1. Type ENTER to call pack() -> ");
		fg.pack();
		printGeometry(fg);
		printGeometry(b);

		Holder.pause("\n2.Type ENTER to call setVisible(true) -> ");
		fg.setVisible(true);
		b.setVisible(true);
		printGeometry(fg);
		printGeometry(b);

		Holder.pause("\n3. Type ENTER to call setBounds(...) -> ");
		fg.setBounds(50, 50, 200, 300);
		b.setBounds(0, 0, 150, 200);
		printGeometry(fg);
		printGeometry(b);

		Holder.pause("\n4. Type ENTER to call add(...) -> ");
		fg.getContentPane().add(b, BorderLayout.CENTER);
		printGeometry(fg);
		printGeometry(b);
//		fg.validate();
		
		Holder.pause("\n5. Type ENTER to call setVisible(false) -> ");
		fg.setVisible(false);
		b.setVisible(false);
		printGeometry(fg);
		printGeometry(b);

		Holder.pause("\n6. Type ENTER to call dispose() -> ");
		fg.dispose();
		printGeometry(fg);
		printGeometry(b);
	}

	public static void printGeometry(Component c) {
		String prefix = c.getClass().getSimpleName();

		System.out.println(prefix + ": Bounds = " + c.getBounds());
		System.out.println(String.format("\tDisplayable(%s), Visible(%s), Showing(%s).", c.isDisplayable(), c.isVisible(), c.isShowing()));
	}
}
