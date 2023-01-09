package layout.demo;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import layout.demo.tool.BigButton;
import layout.demo.tool.ExitableJFrame;
import layout.demo.tool.Holder;

public class BorderedFrame extends JFrame {
	public BorderedFrame(boolean useBigButton) {
	      System.out.println("JFrame default layout manager : " + getLayout());
	      add(BorderLayout.EAST, useBigButton ? new BigButton("East") : new JButton("East"));
	      add(BorderLayout.WEST, useBigButton ? new BigButton("West") : new JButton("West"));
	      add(BorderLayout.NORTH, useBigButton ? new BigButton("North") : new JButton("North"));
	      add(BorderLayout.SOUTH, useBigButton ? new BigButton("South") : new JButton("South"));
	   }

	public static void main(String[] args) {
		BorderedFrame f = new BorderedFrame(true);
		ExitableJFrame.run(f, 250, 150);
		Holder.pause("Type Enter to add a Button to Center");

		f.add(BorderLayout.CENTER, new BigButton("Center"));
		f.validate();
		Holder.pause("Type Enter to remove the Button on the East");

		f.remove(f.getContentPane().getComponent(0));
		f.validate();
		Holder.pause("Type Enter to remove the Button on the North");
		
		f.remove(f.getContentPane().getComponent(1));
		f.validate();
	}
}