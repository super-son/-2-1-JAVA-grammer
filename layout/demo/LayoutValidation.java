package layout.demo;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JFrame;

import layout.demo.tool.ExitableJFrame;
import layout.demo.tool.Holder;

public class LayoutValidation extends JFrame {
	LayoutValidation(String title) {
		super(title);

		setLayout(new GridLayout());
		ExitableJFrame.run(this, 300, 100);

		Holder.pause("1. Type Enter to add [First Button] -> ");
		add(new Button("First Button"));
		System.out.println("\t[First Button] added, but not shown!");

		Holder.pause("2. Type Enter to call pack() -> ");
		pack();
		System.out.println("\tFrame packed. Now, [First Button] shows up.");

		Holder.pause("3: Type Enter to add a panel with [Second Button] -> ");
		Panel p = new Panel();
		p.setBackground(Color.RED);;
		p.add(new Button("Second Button"));
		add(p);
		System.out.println("\tPanel added, but not shown too!");

		Holder.pause("4: Type Enter to resize -> ");
		setSize(300, 120);
		System.out.println("\tFrame resized. Now, [Second Button] shows up.");

		Holder.pause("5. Type Enter to add [Third Button] to panel -> ");
		p.add(new Button("Third Button"));
		System.out.println("\t[Third Button] added, but not shown.");

		Holder.pause("6. Type Enter to call setVisible(true) -> ");
		setVisible(true);
		System.out.println("\tNow, [Third Button] is shown.");

		Holder.pause("7: Type Enter to add [Fourth Button] with another panel -> ");
		Panel p2 = new Panel();
		p2.setBackground(Color.ORANGE);
		p2.add(new Button("Fourth Button"));
		p.add(p2);
		System.out.println("\t[Fourth Button] added. But not shown.");

		Holder.pause("8: Type Enter to do layout -> ");
		p2.invalidate(); // can be removed
		validate();
		System.out.println("\tLayout validated. Now, [Fourth Button] shows up with inner panel.");
//		pack();
	}

	public static void main(String[] args) {
		new LayoutValidation("Test Layout Validation");
	}
}
