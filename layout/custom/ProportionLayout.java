package layout.custom;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.Serializable;

public class ProportionLayout extends AbstractLayout implements LayoutManager, Serializable {
	private final static int MINIMIZED_LAYOUT = 0;
	private final static int PREFERRED_LAYOUT = 1;

	private int[] iRows = { 1 };
	private int[] iColumns = { 1 };
	private float[] fRows = { 1.0F };
	private float[] fColumns = { 1.0F };

	public ProportionLayout() {
		super();
	}

	public ProportionLayout(int rows, int cols) {
		super();
		setRows(rows);
		setColumns(cols);
	}

	public ProportionLayout(int[] rows, int cols) {
		super();
		setRows(rows);
		setColumns(cols);
	}

	public ProportionLayout(int rows, int[] cols) {
		super();
		setRows(rows);
		setColumns(cols);
	}

	public ProportionLayout(int[] rows, int[] cols) {
		super();
		setRows(rows);
		setColumns(cols);
	}

	public ProportionLayout(int[] rows, int[] cols, int hgap, int vgap) {
		super(hgap, vgap);
		setRows(rows);
		setColumns(cols);
	}

	public int[] getRows() {
		return iRows;
	}

	public int[] getColumns() {
		return iColumns;
	}

	public void setRows(int rows) {
		if (rows == 0) {
			throw new IllegalArgumentException("rows cannot set to zero");
		}
		iRows = new int[rows];
		for (int i = 0; i < iRows.length; ++i)
			iRows[i] = 1;
		setfRows();
	}

	public void setRows(int[] rows) {
		if ((rows == null) || (rows.length == 0)) {
			throw new IllegalArgumentException("rows cannot be null or zero length");
		}

		for (int i = 0; i < rows.length; ++i) {
			if (rows[i] == 0) {
				throw new IllegalArgumentException("rows cannot have zero");
			}
		}

		iRows = rows;
		setfRows();
	}

	public void setColumns(int cols) {
		if (cols == 0) {
			throw new IllegalArgumentException("columns cannot set to zero");
		}
		iColumns = new int[cols];
		for (int i = 0; i < iColumns.length; ++i)
			iColumns[i] = 1;
		setfColumns();
	}

	public void setColumns(int[] cols) {
		if ((cols == null) || (cols.length == 0)) {
			throw new IllegalArgumentException("columns cannot be null or zero length");
		}

		for (int i = 0; i < cols.length; ++i)
			if (cols[i] == 0) {
				throw new IllegalArgumentException("columns cannot have zero");
			}

		iColumns = cols;
		setfColumns();
	}

	private void setfRows() {
		float total = 0.0F;
		for (int i = 0; i < iRows.length; ++i)
			total += iRows[i];

		fRows = new float[iRows.length];

		for (int i = 0; i < fRows.length; ++i)
			fRows[i] = iRows[i] / total;
	}

	private void setfColumns() {
		float total = 0.0F;
		for (int i = 0; i < iColumns.length; ++i)
			total += iColumns[i];

		fColumns = new float[iColumns.length];

		for (int i = 0; i < fColumns.length; ++i)
			fColumns[i] = iColumns[i] / total;
	}

	private float[] calculateDimensions(Container c, int mode) {
		Dimension[] dims = new Dimension[c.getComponentCount()];
		for (int i = 0; i < dims.length; ++i) {
			if (mode == PREFERRED_LAYOUT)
				dims[i] = c.getComponent(i).getPreferredSize();
			else
				dims[i] = c.getComponent(i).getMinimumSize();
		}

		float xUnit = 0.0F;
		float yUnit = 0.0F;
		int count = 0;

		outer: for (int i = 0; i < iRows.length; ++i) {
			for (int j = 0; j < iColumns.length; ++j) {
				if (count >= dims.length)
					break outer;

				xUnit = (float) Math.max(xUnit, dims[count].getWidth() / iColumns[j]);
				yUnit = (float) Math.max(yUnit, dims[count++].getHeight() / iRows[i]);
			}
		}

		return new float[] { xUnit, yUnit };
	}

	public Dimension preferredLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			int nComps = parent.getComponentCount();
			Insets inset = parent.getInsets();

			if (nComps == 0)
				return new Dimension(inset.left + inset.right, inset.top + inset.bottom);

			float[] result = calculateDimensions(parent, PREFERRED_LAYOUT);
			int nCols = nComps > iColumns.length ? iColumns.length : nComps;
			int nRows = nComps / iColumns.length;
			if (nComps % iColumns.length != 0)
				++nRows;
			nRows = (int) Math.min(nRows, iRows.length);

			int width = inset.left + inset.right + (nCols - 1) * hgap;
			int height = inset.top + inset.bottom + (nRows - 1) * vgap;

			for (int i = 0; i < nCols; ++i)
				width += iColumns[i] * (int) result[0];
			for (int i = 0; i < nRows; ++i)
				height += iRows[i] * (int) result[1];

			return new Dimension(width, height);
		}
	}

	public Dimension minimumLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			int nComps = parent.getComponentCount();
			Insets inset = parent.getInsets();

			if (nComps == 0)
				return new Dimension(inset.left + inset.right, inset.top + inset.bottom);

			float[] result = calculateDimensions(parent, MINIMIZED_LAYOUT);
			int nCols = nComps > iColumns.length ? iColumns.length : nComps;
			int nRows = nComps / iColumns.length;
			if (nComps % iColumns.length != 0)
				++nRows;
			nRows = (int) Math.min(nRows, iRows.length);

			int width = inset.left + inset.right + (nCols - 1) * hgap;
			int height = inset.top + inset.bottom + (nRows - 1) * vgap;

			for (int i = 0; i < nCols; ++i)
				width += iColumns[i] * (int) result[0];
			for (int i = 0; i < nRows; ++i)
				height += iRows[i] * (int) result[1];

			return new Dimension(width, height);
		}
	}

	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Dimension d = parent.getSize();
			Insets inset = parent.getInsets();
			Component[] comps = parent.getComponents();
			if (comps.length == 0)
				return;

			int nCols = comps.length > iColumns.length ? iColumns.length : comps.length;
			int nRows = comps.length / iColumns.length;
			if (comps.length % iColumns.length != 0)
				++nRows;

			if (nCols < iColumns.length) {
				fColumns = new float[nCols];
				float total = 0.0F;

				for (int i = 0; i < fColumns.length; ++i)
					total += iColumns[i];
				for (int i = 0; i < fColumns.length; ++i)
					fColumns[i] = iColumns[i] / total;
			}

			if (nRows < iRows.length) {
				fRows = new float[nRows];
				float total = 0.0F;

				for (int i = 0; i < fRows.length; ++i)
					total += iRows[i];
				for (int i = 0; i < fRows.length; ++i)
					fRows[i] = iRows[i] / total;
			}

			int width = (int) d.getWidth() - inset.left - inset.right - (nCols - 1) * hgap;
			int height = (int) d.getHeight() - inset.top - inset.bottom - (nRows - 1) * vgap;

			int count = 0;
			int y = inset.top;

			outer: for (int i = 0; i < fRows.length; ++i) {
				int x = inset.left;

				for (int j = 0; j < fColumns.length; ++j) {
					if (count >= comps.length)
						break outer;

					comps[count++].setBounds(x, y, (int) (width * fColumns[j]), (int) (height * fRows[i]));
					x += (int) (width * fColumns[j]) + hgap;
				}

				y += (int) (height * fRows[i]) + vgap;
			}
		}
	}
}
