package layout.custom;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.io.Serializable;

public abstract class AbstractLayout implements Serializable {
	protected int hgap;
	protected int vgap;

	public AbstractLayout() {
		this(0, 0);
	}

	public AbstractLayout(int hgap, int vgap) {
		setHgap(hgap);
		setVgap(vgap);
	}

	public int getHgap() {
		return hgap;
	}

	public int getVgap() {
		return vgap;
	}

	public void setHgap(int gap) {
		hgap = gap;
	}

	public void setVgap(int gap) {
		vgap = gap;
	}

	public void addLayoutComponent(Component comp, Object constraints) {
	}

	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	public float getLayoutAlignmentX(Container parent) {
		return 0.5f;
	}

	public float getLayoutAlignmentY(Container parent) {
		return 0.5f;
	}

	public void invalidateLayout(Container target) {
	}

	public void addLayoutComponent(String name, Component comp) {
		addLayoutComponent(comp, name);
	}

	public void removeLayoutComponent(Component comp) {
	}

	public String toString() {
		return getClass().getName() + "[hgap=" + hgap + ",vgap=" + vgap + "]";
	}
}
