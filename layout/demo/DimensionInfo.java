package layout.demo;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import layout.demo.tool.BigButton;
import layout.demo.tool.ExitableJFrame;

public class DimensionInfo extends JFrame {
	   DimensionInfo(String title) {
	      super(title);
	      
	      setLayout(new FlowLayout());
	      getContentPane().addContainerListener(new ContainerAdapter() {
	         public void componentAdded(ContainerEvent e) {
	            System.out.println(String.format("Parent Container: %s\n\tChild Component: %s", e.getContainer(), e.getChild()));
	         }
	      });
	      
	      add(new JButton("Button 1"));
	      add(new JButton("Button 2"));
	      add(new BigButton("Button 3"));
	      
	      pack();
	      ExitableJFrame.run(this, this.getWidth(), this.getHeight());
	   }
	   
	   public static void main(String[] args) {
	      DimensionInfo f = new DimensionInfo("Test Container's Dimension");
	      
	      System.out.println(String.format("Minimum = %s, Maximum = %s", f.getMinimumSize(), f.getMaximumSize()));
	      System.out.println(String.format("Preferred = %s, Current = %s", f.getPreferredSize(), f.getSize()));
	      
	      Container container = f.getContentPane();
	      for (int i = 0; i < container.getComponentCount(); i++) {
	         Component c = container.getComponent(i);
	         System.out.println(String.format("Component[%d] = %s, f.isAncestorOf() = %s", i, c.getClass().getSimpleName(), f.isAncestorOf(c)));
	      }
	   }
	}
