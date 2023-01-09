package layout.custom;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.Serializable;

public class ColumnLayout extends AbstractLayout implements LayoutManager, Serializable {
	private final static int MINIMIZED_LAYOUT = 0;
	private final static int PREFERRED_LAYOUT = 1;
	private int LAYOUT_MODE = PREFERRED_LAYOUT;

	private boolean sameRowHeight;
	private boolean rowSpan;

	public ColumnLayout() {
		this(0, 0);
	}

	public ColumnLayout(int hgap, int vgap) {
		this(hgap, vgap, false);
	}

	public ColumnLayout(boolean sameRowHeight, boolean rowSpan) {
		this(0, 0, sameRowHeight, rowSpan);
	}

	public ColumnLayout(int hgap, int vgap, boolean sameRowHeight) {
		this(hgap, vgap, sameRowHeight, false);
	}

	public ColumnLayout(int hgap, int vgap, boolean sameRowHeight, boolean rowSpan) {
		super(hgap, vgap);

		this.hgap = hgap;
		this.vgap = vgap;
		this.sameRowHeight = sameRowHeight;
		this.rowSpan = rowSpan;

	}

	public void setSameRowHeight(boolean sameRowHeight) {
		this.sameRowHeight = sameRowHeight;
	}

	public boolean isSameRowHeight() {
		return sameRowHeight;
	}

	public void setRowSpan(boolean rowSpan) {
		this.rowSpan = rowSpan;
	}

	public boolean isRowSpan() {
		return rowSpan;
	}

	public void addLayoutComponent(String name, Component comp) {
		if (name != null)
			throw new IllegalArgumentException("ColumnLayout does not support constraints");
	}

	public void layoutContainer(Container target) {
		synchronized (target.getTreeLock()) {
			Component[] comps = target.getComponents();
			int[] result = calculateDimensions(target, PREFERRED_LAYOUT);

			Insets inset = target.getInsets();
			int x = inset.left;
			int y = inset.top;
			int width = (int) (target.getSize().getWidth() - x - inset.right);
			int height = (int) (target.getSize().getHeight() - y - inset.bottom);

			if (comps.length == 1) {
				if (result[0] < width)
					result = calculateDimensions(target, MINIMIZED_LAYOUT);
				comps[0].setBounds(x, y, result[0], result[1]);
				return;
			}

			if (result[0] + result[1] + hgap < width) {
				result = calculateDimensions(target, MINIMIZED_LAYOUT);
				LAYOUT_MODE = MINIMIZED_LAYOUT;
			}

			boolean even = (comps.length % 2 == 0);
			int index = 2;

			if (even) {
				if (sameRowHeight) {
					for (int i = 0; i < comps.length; i += 2) {
						comps[i].setBounds(x, y, result[0], result[2]);
						comps[i + 1].setBounds(x + result[0] + hgap, y, result[1], result[2]);
						y += result[2] + vgap;
					}
				} else {
					for (int i = 0; i < comps.length; i += 2) {
						comps[i].setBounds(x, y, result[0], result[index]);
						comps[i + 1].setBounds(x + result[0] + hgap, y, result[1], result[index]);
						y += result[index++] + vgap;
					}
				}

				return;
			}

			// number of components is odd
			if (sameRowHeight) {
				for (int i = 0; i < comps.length - 1; i += 2) {
					comps[i].setBounds(x, y, result[0], result[2]);
					comps[i + 1].setBounds(x + result[0] + hgap, y, result[1], result[2]);
					y += result[2] + vgap;
				}

				Component c = target.getComponent(comps.length - 1);
				Dimension d;
				if (LAYOUT_MODE == PREFERRED_LAYOUT)
					d = c.getPreferredSize();
				else
					d = c.getMinimumSize();

				if (rowSpan)
					comps[comps.length - 1].setBounds(x, y, result[0] + hgap + result[1], (int) d.getHeight());
				else
					comps[comps.length - 1].setBounds(x, y, result[0], (int) d.getHeight());

				return;
			}

			// odd and not sameRowHeight
			for (int i = 0; i < comps.length - 1; i += 2) {
				comps[i].setBounds(x, y, result[0], result[index]);
				comps[i + 1].setBounds(x + result[0] + hgap, y, result[1], result[index]);
				y += result[index++] + vgap;
			}

			if (rowSpan)
				comps[comps.length - 1].setBounds(x, y, result[0] + hgap + result[1], result[index]);
			else
				comps[comps.length - 1].setBounds(x, y, result[0], result[index]);
		}
	}

	public Dimension minimumLayoutSize(Container target) {
		synchronized (target.getTreeLock()) {
			int[] result = calculateDimensions(target, MINIMIZED_LAYOUT);
			Insets in = target.getInsets();

			if (result.length == 2)
				return new Dimension(result[0] + in.left + in.right, result[1] + in.top + in.bottom);

			int width = result[0] + result[1] + hgap;
			int height = 0;
			int count = target.getComponentCount();
			int rows = (count + 1) / 2;
			boolean even = (count % 2 == 0);

			if (!sameRowHeight) {
				for (int i = 2; i < result.length; i++)
					height += result[i] + vgap;
				height -= vgap;

				return new Dimension(width + in.left + in.right, height + in.top + in.bottom);
			}

			// sameRowHeight == true
			if (even)
				height = result[2] * rows + vgap * (rows - 1);
			else {
				height += (result[2] + vgap) * (rows - 1);
				Dimension d = target.getComponent(count - 1).getMinimumSize();
				height += d.getHeight();
			}

			return new Dimension(width + in.left + in.right, height + in.top + in.bottom);
		}
	}

	public Dimension preferredLayoutSize(Container target) {
		synchronized (target.getTreeLock()) {
			int[] result = calculateDimensions(target, PREFERRED_LAYOUT);
			Insets in = target.getInsets();

			if (result.length == 2)
				return new Dimension(result[0] + in.left + in.right, result[1] + in.top + in.bottom);

			int width = result[0] + result[1] + hgap;
			int height = 0;
			int count = target.getComponentCount();
			int rows = (count + 1) / 2;
			boolean even = (count % 2 == 0);

			if (!sameRowHeight) {
				for (int i = 2; i < result.length; i++)
					height += result[i] + vgap;
				height -= vgap;

				return new Dimension(width + in.left + in.right, height + in.top + in.bottom);
			}

			// sameRowHeight == true
			if (even)
				height = result[2] * rows + vgap * (rows - 1);
			else {
				height += (result[2] + vgap) * (rows - 1);
				Dimension d = target.getComponent(count - 1).getPreferredSize();
				height += d.getHeight();
			}

			return new Dimension(width + in.left + in.right, height + in.top + in.bottom);
		}

	}

	private int[] calculateDimensions(Container c, int mode) {
		int maxWidth1 = 0;
		int maxWidth2 = 0;

		Component[] comps = c.getComponents();
		Dimension[] dims = new Dimension[comps.length];
		for (int i = 0; i < dims.length; i++) {
			if (mode == MINIMIZED_LAYOUT)
				dims[i] = comps[i].getMinimumSize();
			else if (mode == PREFERRED_LAYOUT)
				dims[i] = comps[i].getPreferredSize();
		}

		if (comps.length <= 1) {
			int[] result = new int[2]; // result = { width = 0, height = 0 }

			if (comps.length == 1) {
				result[0] = (int) dims[0].getWidth();
				result[1] = (int) dims[0].getHeight();
			}
			return result;
		}

		int rows = (comps.length + 1) / 2;
		int[] result = new int[sameRowHeight ? 3 : rows + 2];
		boolean even = (comps.length % 2 == 0);
		int index = 2;

		if (even) {
			for (int i = 0; i < dims.length; i += 2) {
				maxWidth1 = (int) Math.max(maxWidth1, dims[i].getWidth());
				maxWidth2 = (int) Math.max(maxWidth2, dims[i + 1].getWidth());
			}

			result[0] = maxWidth1;
			result[1] = maxWidth2;

			if (sameRowHeight) {
				for (int i = 0; i < dims.length; i++)
					result[2] = (int) Math.max(result[2], dims[i].getHeight());

				return result;
			}

			for (int i = 0; i < dims.length; i += 2)
				result[index++] = (int) Math.max(dims[i].getHeight(), dims[i + 1].getHeight());

			return result;
		}

		// if number of component is odd
		for (int i = 0; i < dims.length - 1; i += 2) {
			maxWidth1 = (int) Math.max(maxWidth1, dims[i].getWidth());
			maxWidth2 = (int) Math.max(maxWidth2, dims[i + 1].getWidth());
		}

		if (!rowSpan)
			maxWidth1 = (int) Math.max(maxWidth1, dims[dims.length - 1].getWidth());

		result[0] = maxWidth1;
		result[1] = maxWidth2;

		if (sameRowHeight) {
			for (int i = 0; i < dims.length - 1; ++i)
				result[2] = (int) Math.max(result[2], dims[i].getHeight());
		} else {
			for (int i = 0; i < dims.length - 1; i += 2)
				result[index++] = (int) Math.max(dims[i].getHeight(), dims[i + 1].getHeight());
			result[result.length - 1] = (int) dims[dims.length - 1].getHeight();
		}

		return result;
	}
}
