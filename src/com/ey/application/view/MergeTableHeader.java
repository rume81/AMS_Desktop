package com.ey.application.view;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;

import javax.swing.CellRendererPane;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

class DefaultHeaderRenderer extends DefaultTableCellRenderer {
	{
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	}

	public void updateUI() {
		super.updateUI();

		setHorizontalAlignment(CENTER);

		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {
		MergeTableHeader header;

		if (table != null && (header = (MergeTableHeader) table.getClientProperty(MergeTableHeader.KEY)) != null) {
			setForeground(header.getForeground());
			setBackground(header.getBackground());

			setFont(header.getFont());

			setComponentOrientation(header.getComponentOrientation());

			setEnabled(header.isEnabled());
		} else {
			setForeground(UIManager.getColor("TableHeader.foreground"));
			setBackground(UIManager.getColor("TableHeader.background"));

			setFont(UIManager.getFont("TableHeader.font"));

			setComponentOrientation(ComponentOrientation.UNKNOWN);

			setEnabled(true);
		}

		setText(value != null ? value.toString() : "");

		return this;
	}
}


public class MergeTableHeader extends JComponent { 
	/** TableColumn that also has a headerSpan property. */
	public static class XTableColumn extends TableColumn {
		private int headerSpan;

		public XTableColumn() {
			headerSpan = 1;
		}

		/** number of columns that the header cell spans. */
		public final int headerSpan() {
			return headerSpan;
		}

		/**
		 * set 'headerSpan'. requires newHeaderSpan > 0. During the manipulation
		 * of TableColumnModels, there may be times where a column spans more
		 * columns than there are at the right of it. This state is only allowed
		 * during such modifications. Once they are finished, all spans must not
		 * be too large. XTableColumns that are hidden by the spans of their
		 * predecessors are ignored (in the TableHeader, of course not by the
		 * JTable).
		 * 
		 * Due to the broken notification way of TableColumnModel/Table- Column
		 * there is no way to notify the header that it must repaint(). If
		 * firePropertyChange were not private, we could send the following fake
		 * event firePropertyChange("width", getWidth(), getWidth()); which
		 * would handle that (only width and preferred width changes can reach
		 * the header). The moral is: don't change spans later, or if, repaint
		 * the header manually.
		 */
		public void setHeaderSpan(int newHeaderSpan) {
			headerSpan = newHeaderSpan;
		}
	}

	private static TableCellRenderer staticDefaultRenderer = new DefaultHeaderRenderer();

	/**
	 * Under this key, the table header is stored in the JTable, so that the
	 * renderer can access it. See demo renderer above.
	 */
	public static Object KEY = MergeTableHeader.class;

	private JTable table;

	private transient TableColumnModel columns;

	private TableCellRenderer defaultRenderer = staticDefaultRenderer;

	private transient Listener listener;

	public MergeTableHeader(JTable table) {
		this.table = table;

		table.putClientProperty(KEY, this);

		this.columns = table.getColumnModel();

		this.listener = createListener();

		table.addPropertyChangeListener(listener);

		columns.addColumnModelListener(listener);

		add(new CellRendererPane());

		updateUI();
	}

	public void updateUI() {
		LookAndFeel.installColorsAndFont(this, "TableHeader.background", "TableHeader.foreground", "TableHeader.font");

		LookAndFeel.installBorder(this, "TableHeader.border");

		if (defaultRenderer instanceof JComponent)
			((JComponent) defaultRenderer).updateUI();

		revalidate();
		repaint();
	}

	public final JTable table() {
		return table;
	}

	public void setTable(JTable t) {
		JTable oldTable = table;
		TableColumnModel oldColumns = columns;

		table.putClientProperty(KEY, null);

		table.removePropertyChangeListener(listener);

		columns.removeColumnModelListener(listener);

		table = t;

		table.putClientProperty(KEY, this);

		columns = t.getColumnModel();

		table.addPropertyChangeListener(listener);

		columns.addColumnModelListener(listener);

		revalidate();
		repaint();

		firePropertyChange("table", oldTable, table);
		firePropertyChange("columns", oldColumns, columns);
	}

	public final TableColumnModel columns() {
		return columns;
	}

	/**
	 * For serialization, the TableCellRenderer is needed to be serializable.
	 */
	public void setDefaultRenderer(TableCellRenderer r) {
		TableCellRenderer oldRenderer = defaultRenderer;

		defaultRenderer = r;

		revalidate();
		repaint();

		firePropertyChange("defaultRenderer", oldRenderer, defaultRenderer);
	}

	public final TableCellRenderer defaultRenderer() {
		return defaultRenderer;
	}

	private TableCellRenderer renderer(TableColumn c) {
		TableCellRenderer result = c.getHeaderRenderer();

		if (result != null)
			return result;

		return defaultRenderer;
	}

	private Component component(TableCellRenderer r, TableColumn c, int column) {
		return r.getTableCellRendererComponent(table, c.getHeaderValue(), false, false, -1, column);
	}

	private Dimension size(long innerWidth) {
		Insets i = getInsets();

		return new Dimension((int) Math.min(innerWidth + i.left + i.bottom, Integer.MAX_VALUE),
				innerHeight() + i.top + i.bottom);
	}

	/** Alas, this cannot be cached. */
	private int innerHeight() {
		int result = 0;

		int count = columns.getColumnCount();

		for (int j = 0; j < count;) {
			TableColumn c = columns.getColumn(j);

			int span;

			if (c instanceof XTableColumn)
				span = ((XTableColumn) c).headerSpan();
			else
				span = 1;

			Component d = component(renderer(c), c, j);

			result = Math.max(result, d.getPreferredSize().height);

			j += span;
		}

		return result;
	}

	public Dimension getMinimumSize() {
		if (isMinimumSizeSet())
			return super.getMinimumSize();

		return size(minWidth(columns));
	}

	public Dimension getPreferredSize() {
		if (isPreferredSizeSet())
			return super.getPreferredSize();

		return size(preferredWidth(columns));
	}

	public Dimension getMaximumSize() {
		if (isMaximumSizeSet())
			return super.getMaximumSize();

		return size(maxWidth(columns));
	}

	public void paintComponent(Graphics g) {
		Insets i = getInsets();

		Rectangle clip = g.getClipBounds();

		CellRendererPane pane = (CellRendererPane) getComponent(0);

		Rectangle r = new Rectangle();

		r.x = i.left;
		r.y = i.top;
		r.height = getHeight() - i.top - i.bottom;

		if (r.height <= 0)
			return;

		int count = columns.getColumnCount();

		for (int j = 0; j < count;) {
			TableColumn c = columns.getColumn(j);

			r.width = c.getWidth();

			int span;

			if (c instanceof XTableColumn) {
				span = ((XTableColumn) c).headerSpan();

				if (j + span > count) {
					System.err.println("column: " + j + " span: " + span + " > " + count);
					System.err.println("This state of TableColumnModel is forbidden!");
					span = count - j;
				}

				for (int k = 1; k < span; k++)
					r.width += columns.getColumn(j + k).getWidth();
			} else {
				span = 1;
			}

			Component d = component(renderer(c), c, j);

			pane.paintComponent(g, d, this, r.x, r.y, r.width, r.height, true);

			r.x += r.width;

			j += span;
		}

		pane.removeAll();
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();

		listener = createListener();

		table.addPropertyChangeListener(listener);

		columns = table.getColumnModel();

		columns.addColumnModelListener(listener);
	}

	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private Listener createListener() {
		return new Listener();
	}

	private class Listener implements TableColumnModelListener, PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent e) {
			if (e.getPropertyName().equals("columnModel")) {
				TableColumnModel oldColumns = columns;

				columns.removeColumnModelListener(this);

				columns = table.getColumnModel();

				columns.addColumnModelListener(this);

				revalidate();
				repaint();

				firePropertyChange("columns", oldColumns, columns);
			}
		}

		public void columnAdded(TableColumnModelEvent e) {
			revalidate();
			repaint();
		}

		public void columnRemoved(TableColumnModelEvent e) {
			revalidate();
			repaint();
		}

		public void columnSelectionChanged(ListSelectionEvent e) {
		}

		public void columnMoved(TableColumnModelEvent e) {
			repaint();
		}

		public void columnMarginChanged(ChangeEvent e) {
			revalidate();
			repaint();
		}
	}

	/* Utility methods. Copied here from TableColumnModels. */

	public static long minWidth(TableColumnModel columns) {
		long result = 0;

		for (Enumeration e = columns.getColumns(); e.hasMoreElements();)
			result += ((TableColumn) e.nextElement()).getMinWidth();

		return result;
	}

	public static long preferredWidth(TableColumnModel columns) {
		long result = 0;

		for (Enumeration e = columns.getColumns(); e.hasMoreElements();)
			result += ((TableColumn) e.nextElement()).getPreferredWidth();

		return result;
	}

	public static long maxWidth(TableColumnModel columns) {
		long result = 0;

		for (Enumeration e = columns.getColumns(); e.hasMoreElements();)
			result += ((TableColumn) e.nextElement()).getMaxWidth();

		return result;
	}
}






