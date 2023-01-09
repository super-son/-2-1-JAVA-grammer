package layout.custom;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.io.Serializable;

public class CastleLayout extends AbstractLayout implements LayoutManager2, Serializable {
	private final static int MINIMIZED_LAYOUT = 0;
	private final static int PREFERRED_LAYOUT = 1;
	private int LAYOUT_MODE = PREFERRED_LAYOUT;
	private boolean expand = false;

	public final static String NORTHWEST = "NorthWest";
	public final static String NORTH = "North";
	public final static String NORTHEAST = "NorthEast";
	public final static String WEST = "West";
	public final static String CENTER = "Center";
	public final static String EAST = "East";
	public final static String SOUTHWEST = "SouthWest";
	public final static String SOUTH = "South";
	public final static String SOUTHEAST = "SouthEast";
	private Component[][] comps = new Component[3][];

	public CastleLayout() {
		this(0, 0);
	}

	public CastleLayout(boolean expand) {
		this(0, 0, expand);
	}

	public CastleLayout(int hgap, int vgap) {
		this(hgap, vgap, false);
	}

	public CastleLayout(int hgap, int vgap, boolean expand) {
		super(hgap, vgap);
		this.expand = expand;

		for (int i = 0; i < 3; ++i)
			comps[i] = new Component[3];
	}

	public void addLayoutComponent(Component comp, Object constraints) {
		synchronized (comp.getTreeLock()) {
			if ((constraints == null) || (constraints instanceof String)) {
				addLayoutComponent((String) constraints, comp);
			} else {
				throw new IllegalArgumentException("cannot add to layout: constraint must be a string (or null)");
			}
		}
	}

	public void addLayoutComponent(String name, Component comp) {
		synchronized (comp.getTreeLock()) {
			if (name == null)
				comps[1][1] = comp; // add to CENTER
			else if (name.equals(NORTHWEST))
				comps[0][0] = comp;
			else if (name.equals(NORTH))
				comps[0][1] = comp;
			else if (name.equals(NORTHEAST))
				comps[0][2] = comp;
			else if (name.equals(WEST))
				comps[1][0] = comp;
			else if (name.equals(CENTER))
				comps[1][1] = comp;
			else if (name.equals(EAST))
				comps[1][2] = comp;
			else if (name.equals(SOUTHWEST))
				comps[2][0] = comp;
			else if (name.equals(SOUTH))
				comps[2][1] = comp;
			else if (name.equals(SOUTHEAST))
				comps[2][2] = comp;
			else
				throw new IllegalArgumentException("cannot add to layout : unsupport constraints");
		}
	}

	public void removeLayoutComponent(Component comp) {
		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				if (comps[i][j] == comp) {
					comps[i][j] = null;
					return;
				}
	}

	public void layoutContainer(Container target) {
		synchronized (target.getTreeLock()) {
			boolean x_expandable = false;
			boolean y_expandable = false;
			Dimension d = preferredLayoutSize(target);

			if (d.getWidth() >= target.getSize().getWidth()) {
				LAYOUT_MODE = MINIMIZED_LAYOUT;
				d = minimumLayoutSize(target);

				if (d.getWidth() < target.getSize().getWidth())
					x_expandable = true;
				if (d.getHeight() < target.getSize().getHeight())
					y_expandable = true;
			} else {
				LAYOUT_MODE = PREFERRED_LAYOUT;
				x_expandable = true;
				if (d.getHeight() < target.getSize().getHeight())
					y_expandable = true;
			}

			d = target.getSize();
			Insets inset = target.getInsets();
			int[][] result = calculateDimensions(target, LAYOUT_MODE);
			int x1, x2, y1, y2;
			x1 = inset.left;
			y1 = inset.top;

			if (result[0][0] != 0)
				x1 += result[0][0] + hgap;
			if (result[1][0] != 0)
				y1 += result[1][0] + vgap;

			if (x_expandable) {
				if (result[0][2] != 0)
					x2 = (int) d.getWidth() - result[0][2] - inset.right - hgap;
				else
					x2 = (int) d.getWidth() - inset.right;
			} else {
				x2 = x1 + result[0][1];
			}

			if (y_expandable) {
				if (result[1][2] != 0)
					y2 = (int) d.getHeight() - result[1][2] - inset.bottom - vgap;
				else
					y2 = (int) d.getHeight() - inset.bottom;
			} else {
				y2 = y1 + result[1][1];
			}

			boolean upper_left, upper_right, bottom_left, bottom_right;

			upper_left = false;
			if (comps[0][0] != null) { // NORTHWEST
				comps[0][0].setBounds(inset.left, inset.top, result[0][0], result[1][0]);
				upper_left = true;
			}

			upper_right = false;
			if (comps[0][2] != null) { // NORTHEAST
				comps[0][2].setBounds(x2 + hgap, inset.top, result[0][2], result[1][0]);
				upper_right = true;
			}

			bottom_left = false;
			if (comps[2][0] != null) { // SOUTHWEST
				comps[2][0].setBounds(inset.left, y2 + vgap, result[0][0], result[1][2]);
				bottom_left = true;
			}

			bottom_right = false;
			if (comps[2][2] != null) { // SOUTHEAST
				comps[2][2].setBounds(x2 + hgap, y2 + vgap, result[0][2], result[1][2]);
				bottom_right = true;
			}

			if (!expand) {
				if (comps[0][1] != null) // NORTH
					comps[0][1].setBounds(x1, inset.top, x2 - x1, result[1][0]);
				if (comps[2][1] != null) // SOUTH
					comps[2][1].setBounds(x1, y2 + vgap, x2 - x1, result[1][2]);
				if (comps[1][0] != null) // WEST
					comps[1][0].setBounds(inset.left, y1, result[0][0], y2 - y1);
				if (comps[1][2] != null) // EAST
					comps[1][2].setBounds(x2 + hgap, y1, result[0][2], y2 - y1);
				if (comps[1][1] != null) // CENTER
					comps[1][1].setBounds(x1, y1, x2 - x1, y2 - y1);

				return;
			}

			// if expand == true
			boolean top_expanded = false;
			if (comps[0][1] != null) { // NORTH
				top_expanded = true;

				if (comps[0][0] != null) {
					if (comps[0][2] != null)
						comps[0][1].setBounds(x1, inset.top, x2 - x1, result[1][0]);
					else
						comps[0][1].setBounds(x1, inset.top, (int) d.getWidth() - x1 - inset.right, result[1][0]);
				} else {
					if (comps[0][2] != null)
						comps[0][1].setBounds(inset.left, inset.top, x2 - inset.left, result[1][0]);
					else
						comps[0][1].setBounds(inset.left, inset.top, (int) d.getWidth() - inset.left - inset.right,
								result[1][0]);
				}

				upper_left = upper_right = true;
			}

			boolean bottom_expanded = false;
			if (comps[2][1] != null) { // SOUTH
				bottom_expanded = true;

				if (comps[2][0] != null) {
					if (comps[2][2] != null)
						comps[2][1].setBounds(x1, y2 + vgap, x2 - x1, result[1][2]);
					else
						comps[2][1].setBounds(x1, y2 + vgap, (int) d.getWidth() - x1 - inset.right, result[1][2]);
				} else {
					if (comps[2][2] != null)
						comps[2][1].setBounds(inset.left, y2 + vgap, x2 - inset.left, result[1][2]);
					else
						comps[2][1].setBounds(inset.left, y2 + vgap, (int) d.getWidth() - inset.left - inset.right,
								result[1][2]);
				}

				bottom_left = bottom_right = true;
			}

			boolean left_expanded = false;
			if (comps[1][0] != null) { // WEST
				left_expanded = true;

				if (top_expanded) {
					if (bottom_expanded)
						comps[1][0].setBounds(inset.left, y1, result[0][0], y2 - y1);
					else {
						if (bottom_left)
							comps[1][0].setBounds(inset.left, y1, result[0][0], y2 - y1);
						else
							comps[1][0].setBounds(inset.left, y1, result[0][0],
									(int) d.getHeight() - inset.bottom - y1);
					}
				} else {
					if (bottom_expanded) {
						if (upper_left)
							comps[1][0].setBounds(inset.left, y1, result[0][0], y2 - y1);
						else
							comps[1][0].setBounds(inset.left, inset.top, result[0][0], y2 - inset.top);
					} else {
						if (upper_left) {
							if (bottom_left)
								comps[1][0].setBounds(inset.left, y1, result[0][0], y2 - y1);
							else
								comps[1][0].setBounds(inset.left, y1, result[0][0],
										(int) d.getHeight() - inset.bottom - y1);
						} else {
							if (bottom_left)
								comps[1][0].setBounds(inset.left, inset.top, result[0][0], y2 - inset.top);
							else
								comps[1][0].setBounds(inset.left, inset.top, result[0][0],
										(int) d.getHeight() - inset.bottom - inset.top);
						}
					}
				}

				upper_left = bottom_left = true;
			}

			boolean right_expanded = false;
			if (comps[1][2] != null) { // EAST
				right_expanded = true;

				if (top_expanded) {
					if (bottom_expanded)
						comps[1][2].setBounds(x2 + hgap, y1, result[0][2], y2 - y1);
					else {
						if (bottom_right)
							comps[1][2].setBounds(x2 + hgap, y1, result[0][2], y2 - y1);
						else
							comps[1][2].setBounds(x2 + hgap, y1, result[0][2], (int) d.getHeight() - inset.bottom - y1);
					}
				} else {
					if (bottom_expanded) {
						if (upper_right)
							comps[1][2].setBounds(x2 + hgap, y1, result[0][2], y2 - y1);
						else
							comps[1][2].setBounds(x2 + hgap, inset.top, result[0][2], y2 - inset.top);
					} else {
						if (upper_right) {
							if (bottom_right)
								comps[1][2].setBounds(x2 + hgap, y1, result[0][2], y2 - y1);
							else
								comps[1][2].setBounds(x2 + hgap, y1, result[0][2],
										(int) d.getHeight() - inset.bottom - y1);
						} else {
							if (bottom_right)
								comps[1][2].setBounds(x2 + hgap, inset.top, result[0][2], y2 - inset.top);
							else
								comps[1][2].setBounds(x2 + hgap, inset.top, result[0][2],
										(int) d.getHeight() - inset.bottom - inset.top);
						}
					}
				}

				upper_right = bottom_right = true;
			}

			if (comps[1][1] != null) { // CENTER
				if (left_expanded) {
					if (right_expanded) {
						if (top_expanded) {
							if (bottom_expanded)
								comps[1][1].setBounds(x1, y1, x2 - x1, y2 - y1);
							else
								comps[1][1].setBounds(x1, y1, x2 - x1, (int) d.getHeight() - inset.bottom - y1);
						} else { // top_expandable
							if (bottom_expanded)
								comps[1][1].setBounds(x1, inset.top, x2 - x1, y2 - inset.top);
							else
								comps[1][1].setBounds(x1, inset.top, x2 - x1,
										(int) d.getHeight() - inset.bottom - inset.top);
						}
					} else { // right_expandable
						if (top_expanded) {
							if (bottom_expanded)
								comps[1][1].setBounds(x1, y1, (int) d.getWidth() - inset.right - x1, y2 - y1);
							else { // bottom_expandable
								if (bottom_right)
									comps[1][1].setBounds(x1, y1, (int) d.getWidth() - inset.right - x1, y2 - y1);
								else
									comps[1][1].setBounds(x1, y1, (int) d.getWidth() - inset.right - x1,
											(int) d.getHeight() - inset.bottom - y1);
							}
						} else { // right, top_expandable
							if (bottom_expanded) {
								if (upper_right)
									comps[1][1].setBounds(x1, y1, (int) d.getWidth() - inset.right - x1, y2 - y1);
								else
									comps[1][1].setBounds(x1, inset.top, (int) d.getWidth() - inset.right - x1,
											y2 - inset.top);
							} else { // right, top, bottom_expandable
								if (upper_right) {
									if (bottom_right)
										comps[1][1].setBounds(x1, y1, (int) d.getWidth() - inset.right - x1, y2 - y1);
									else
										comps[1][1].setBounds(x1, y1, (int) d.getWidth() - inset.right - x1,
												(int) d.getHeight() - inset.bottom - y1);
								} else {
									if (bottom_right)
										comps[1][1].setBounds(x1, inset.top, (int) d.getWidth() - inset.right - x1,
												y2 - inset.top);
									else
										comps[1][1].setBounds(x1, inset.top, (int) d.getWidth() - inset.right - x1,
												(int) d.getHeight() - inset.bottom - inset.top);
								}
							}
						}
					}
				} else { // left_expandable
					if (right_expanded) {
						if (top_expanded) {
							if (bottom_expanded) {
								comps[1][1].setBounds(inset.left, y1, x2 - inset.left, y2 - y1);
							} else { // left, bottom_expandable
								if (bottom_left)
									comps[1][1].setBounds(inset.left, y1, x2 - inset.left, y2 - y1);
								else
									comps[1][1].setBounds(inset.left, y1, x2 - inset.left,
											(int) d.getHeight() - inset.bottom - y1);
							}
						} else { // left, top_expandable
							if (bottom_expanded) {
								if (upper_left)
									comps[1][1].setBounds(inset.left, y1, x2 - inset.left, y2 - y1);
								else
									comps[1][1].setBounds(inset.left, inset.top, x2 - inset.left, y2 - inset.top);
							} else { // left, top, bottom_expandable
								if (upper_left) {
									if (bottom_left)
										comps[1][1].setBounds(inset.left, y1, x2 - inset.left, y2 - y1);
									else
										comps[1][1].setBounds(inset.left, y1, x2 - inset.left,
												(int) d.getHeight() - inset.bottom - y1);
								} else {
									if (bottom_left)
										comps[1][1].setBounds(inset.left, inset.top, x2 - inset.left, y2 - inset.top);
									else
										comps[1][1].setBounds(inset.left, inset.top, x2 - inset.left,
												(int) d.getHeight() - inset.bottom - inset.top);
								}
							}
						}
					} else { // left, right_expandable
						if (top_expanded) {
							if (bottom_expanded)
								comps[1][1].setBounds(inset.left, y1, (int) d.getWidth() - inset.right - inset.left,
										y2 - y1);
							else { // left, right, bottom_expandable
								if (bottom_left || bottom_right)
									comps[1][1].setBounds(inset.left, y1, (int) d.getWidth() - inset.right - inset.left,
											y2 - y1);
								else
									comps[1][1].setBounds(inset.left, y1, (int) d.getWidth() - inset.right - inset.left,
											(int) d.getHeight() - inset.bottom - y1);
							}
						} else { // left, right, top_expandable
							if (bottom_expanded) {
								if (upper_left || upper_right)
									comps[1][1].setBounds(inset.left, y1, (int) d.getWidth() - inset.left - inset.right,
											y2 - y1);
								else
									comps[1][1].setBounds(inset.left, inset.top,
											(int) d.getWidth() - inset.left - inset.right, y2 - inset.top);
							} else { // left, right, top, bottom_expadable
								if (upper_left || upper_right) {
									if (bottom_left || bottom_right)
										comps[1][1].setBounds(inset.left, y1,
												(int) d.getWidth() - inset.left - inset.right, y2 - y1);
									else
										comps[1][1].setBounds(inset.left, y1,
												(int) d.getWidth() - inset.left - inset.right,
												(int) d.getHeight() - inset.bottom - y1);
								} else {
									if (bottom_left || bottom_right)
										comps[1][1].setBounds(inset.left, inset.top,
												(int) d.getWidth() - inset.left - inset.right, y2 - inset.top);
									else
										comps[1][1].setBounds(inset.left, inset.top,
												(int) d.getWidth() - inset.left - inset.right,
												(int) d.getHeight() - inset.top - inset.bottom);
								}
							}
						}
					}
				}
			}
		}
	}

	public Dimension minimumLayoutSize(Container target) {
		synchronized (target.getTreeLock()) {
			int[][] result = calculateDimensions(target, MINIMIZED_LAYOUT);
			Insets in = target.getInsets();

			int width = result[0][0] + result[0][1] + result[0][2];
			int height = result[1][0] + result[1][1] + result[1][2];

			if (result[0][0] != 0)
				if ((result[0][1] != 0) || (result[0][2] != 0))
					width += hgap;
			if (result[0][1] != 0)
				if (result[0][2] != 0)
					width += hgap;
			if (result[1][0] != 0)
				if ((result[1][1] != 0) || (result[1][2] != 0))
					height += vgap;
			if (result[1][1] != 0)
				if (result[1][2] != 0)
					height += vgap;

			return new Dimension(width + in.left + in.right, height + in.top + in.bottom);
		}
	}

	public Dimension preferredLayoutSize(Container target) {
		synchronized (target.getTreeLock()) {
			int[][] result = calculateDimensions(target, PREFERRED_LAYOUT);
			Insets in = target.getInsets();

			int width = result[0][0] + result[0][1] + result[0][2];
			int height = result[1][0] + result[1][1] + result[1][2];

			if (result[0][0] != 0)
				if ((result[0][1] != 0) || (result[0][2] != 0))
					width += hgap;
			if (result[0][1] != 0)
				if (result[0][2] != 0)
					width += hgap;
			if (result[1][0] != 0)
				if ((result[1][1] != 0) || (result[1][2] != 0))
					height += vgap;
			if (result[1][1] != 0)
				if (result[1][2] != 0)
					height += vgap;

			return new Dimension(width + in.left + in.right, height + in.top + in.bottom);
		}
	}

	private int[][] calculateDimensions(Container c, int mode) {
		int maxWidth1 = 0;
		int maxWidth2 = 0;
		int maxWidth3 = 0;
		int maxHeight1 = 0;
		int maxHeight2 = 0;
		int maxHeight3 = 0;

		Dimension[][] dims = new Dimension[3][3];
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (mode == MINIMIZED_LAYOUT)
					dims[i][j] = (comps[i][j] == null) ? new Dimension(0, 0) : comps[i][j].getMinimumSize();
				else if (mode == PREFERRED_LAYOUT)
					dims[i][j] = (comps[i][j] == null) ? new Dimension(0, 0) : comps[i][j].getPreferredSize();
			}
		}

		for (int i = 0; i < 3; ++i) {
			maxWidth1 = (int) Math.max(maxWidth1, dims[i][0].getWidth());
			maxWidth2 = (int) Math.max(maxWidth2, dims[i][1].getWidth());
			maxWidth3 = (int) Math.max(maxWidth3, dims[i][2].getWidth());
			maxHeight1 = (int) Math.max(maxHeight1, dims[0][i].getHeight());
			maxHeight2 = (int) Math.max(maxHeight2, dims[1][i].getHeight());
			maxHeight3 = (int) Math.max(maxHeight3, dims[2][i].getHeight());
		}

		int[][] result = { { maxWidth1, maxWidth2, maxWidth3 }, { maxHeight1, maxHeight2, maxHeight3 } };
		return result;
	}
}
