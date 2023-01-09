package layout.demo;

import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import layout.demo.tool.ExitableJFrame;
import layout.demo.tool.Holder;

public class InvalidateTest extends JFrame {
	   Button b = new Button("First Sized");

	   public InvalidateTest(String title) {
	      super(title);
	      
	      setLayout(new FlowLayout());
	      add(b);
	      ExitableJFrame.run(this, 200, 70);
    
	      Holder.pause("1. Press Enter to change the label of button -> ");
	      b.setLabel("New longer label string");
	      
	      Holder.pause("2. Press Enter to validate button -> ");
	      b.invalidate();
	      b.validate(); // nonsense!      
	      System.out.println("\tButton validated, but nothing changed.");
	      
	      Holder.pause("3. Press Enter to validate parent frame -> ");
	      this.validate();
	      
	      System.out.println("4. Yes, parent frame validated!");
	   }

	   public static void main(String[] args) {
	      new InvalidateTest("Invalidate Test");
	   }
	}
