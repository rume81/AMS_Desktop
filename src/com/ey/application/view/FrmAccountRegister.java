package com.ey.application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.ey.application.controller.DBManager;
import com.ey.application.model.CommonProperties;

import groovy.swing.factory.BevelBorderFactory;

public class FrmAccountRegister extends JInternalFrame {

	Vector changeRow = new Vector();

	JPanel pnlMain = new JPanel();

	private JTable table;

	JLabel lblAccountRegister = new JLabel();
	JLabel lblPrintByDev = new JLabel();
	JLabel lblEditMsg    = new JLabel();

	JButton cmdSave 	= new JButton();
	JButton cmdExit 	= new JButton();
	JButton cmdDelete 	= new JButton();
	JButton cmdPrint 	= new JButton();
	JButton cmdCut 		= new JButton();
	JButton cmdCopy		= new JButton();
	JButton cmdPaste 	= new JButton();

	JComboBox cmbPrintByDev = new JComboBox();
	private boolean DEBUG = false;

	public FrmAccountRegister() {
		try {
			acTablemntFormOpen();
			jbInit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cmbFSCodeRange(JTable table, TableColumn accountRegisterColumn) {
		// Set up the editor for the sport cells.
		Vector model = new Vector();
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT fscrtables.fscr, fscrtables.fscrname FROM fscrtables");
				model.addElement(new comboItem("", ""));

				while (rs.next()) {
					String id = rs.getString(1);
					String name = rs.getString(2);
					model.addElement(new comboItem(id, name));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JComboBox comboBox = new JComboBox(model);
		accountRegisterColumn.setCellEditor(new DefaultCellEditor(comboBox));

		// Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("FCコードの範囲");
		accountRegisterColumn.setCellRenderer(renderer);
	}

	private void jbInit() throws Exception {
		/*this.setBounds(0, 0, 700, 800);
		this.getContentPane().setBackground(new Color(176, 224, 230));
		this.setForeground(Color.black);
		this.setResizable(false);
		this.setTitle("アカウントレジスタ");
		this.getContentPane().setLayout(null);

		this.setIconifiable(true);
		this.setMaximizable(false);
		this.setClosable(true);*/

		this.getContentPane().add(pnlMain, null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				Close();
			}
		});
		
		//Properties for icon
		CommonProperties props 	= new CommonProperties();
		String workingDir 		= props.getWorkingDir();
		String imageDir 		= props.getImageDir();

		pnlMain.setBackground(new Color(176, 224, 230));
		pnlMain.setBorder(BorderFactory.createEtchedBorder());
		pnlMain.setBounds(new Rectangle(9, 9, 800, 600));
		pnlMain.setLayout(null);

		lblAccountRegister.setText("勘定科目　登録・修正");
		lblAccountRegister.setBounds(20, 20, 210, 30);
		lblAccountRegister.setVisible(true);
		lblAccountRegister.setBorder(BorderFactory.createRaisedBevelBorder());

		lblPrintByDev.setText("部門別印刷");
		lblPrintByDev.setVisible(true);
		lblPrintByDev.setBounds(540, 5, 80, 23);
		lblPrintByDev.setBorder(BorderFactory.createRaisedBevelBorder());
		
		lblEditMsg.setText("** 編集をするにはダブルクリックしてください。");
		lblEditMsg.setVisible(true);
		lblEditMsg.setBounds(20, 520, 380, 23);

		cmdSave.setText("登録");
		cmdSave.setVisible(true);
		cmdSave.setBounds(240, 20, 100, 30);
		cmdSave.setIcon(new ImageIcon(imageDir+"save_icon.gif"));
		cmdSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String save = null;
				if (table.getCellEditor() != null)
				table.getCellEditor().stopCellEditing();
				int confirm = JOptionPane.showConfirmDialog(null, "登録しますか？", "登録", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION){
					save(0);					
				}
				
			}
		});
		
		/*cmdCut.setText("切り取り");
		cmdCut.setVisible(true);
		cmdCut.setBounds(240, 60, 100, 30);
		cmdCut.setIcon(new ImageIcon(imageDir+"cut.png"));
		cmdCut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int[] selection = table.getSelectedRows();
				String ids="";
				for(int i=0;i<selection.length;i++){
					String rowid = String.valueOf(table.getModel().getValueAt(selection[i], 6));
					if(!rowid.equals("")){
						if(i==0)
							ids = rowid;
						else
							ids = ids+","+rowid;
					}
				}
				
				int cutConfirm = JOptionPane.showConfirmDialog(null, "カットしますか？", "切り取り", JOptionPane.YES_NO_OPTION);
				if(cutConfirm == JOptionPane.YES_OPTION) {
					MyTableModel dm = (MyTableModel) table.getModel();
					dm.cut(selection);
					cmdPaste.setEnabled(true);
					
					deleteRow(ids);
					dm.reloadData();
				}
				
			}
		});*/
		
		cmdCopy.setText("コピー");
		cmdCopy.setVisible(true);
		cmdCopy.setBounds(240, 60, 100, 30);
		cmdCopy.setIcon(new ImageIcon(imageDir+"copy.png"));
		cmdCopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int[] selection = table.getSelectedRows();
				String ids="";
				for(int i=0;i<selection.length;i++){
					String rowid = String.valueOf(table.getModel().getValueAt(selection[i], 6));
					if(!rowid.equals("")){
						if(i==0)
							ids = rowid;
						else
							ids = ids+","+rowid;
					}
				}
				
				//int cutConfirm = JOptionPane.showConfirmDialog(null, "カットしますか？", "切り取り", JOptionPane.YES_NO_OPTION);
				//if(cutConfirm == JOptionPane.YES_OPTION) {
					MyTableModel dm = (MyTableModel) table.getModel();
					dm.cut(selection);
					cmdPaste.setEnabled(true);
				JOptionPane.showMessageDialog(null, "クリップボードにコピー");
				//}
				
			}
		});
		
		cmdPaste.setText("貼り付け");
		cmdPaste.setVisible(true);
		cmdPaste.setBounds(340, 60, 100, 30);
		cmdPaste.setIcon(new ImageIcon(imageDir+"paste.png"));
		cmdPaste.setEnabled(false);
		cmdPaste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				MyTableModel dm = (MyTableModel) table.getModel();
				dm.paste();
			}
		});

		cmdDelete.setText("削除");
		cmdDelete.setVisible(true);
		cmdDelete.setBounds(340, 20, 100, 30);
		cmdDelete.setIcon(new ImageIcon(imageDir+"deletered.png"));
		cmdDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int[] selection = table.getSelectedRows();
				if(selection.length>0){
					int cutConfirm = JOptionPane.showConfirmDialog(null, "削除しますか？", "削除", JOptionPane.YES_NO_OPTION);
					if(cutConfirm == JOptionPane.YES_OPTION) {
						String ids="";
						for(int i=0;i<selection.length;i++){
							String rowid = String.valueOf(table.getModel().getValueAt(selection[i], 6));
							if(!rowid.equals("")){
								if(i==0)
									ids = rowid;
								else
									ids = ids+","+rowid;
							}
						}
						//System.out.println("DELETE FROM actablewk WHERE rowid IN("+ ids +")");
						
						deleteRow(ids);
						MyTableModel dm = (MyTableModel) table.getModel();
						dm.reloadData();
						
						save(1);
					}
				}
			}
		});
		
		cmdPrint.setText("印刷");
		cmdPrint.setVisible(true);
		cmdPrint.setBounds(540, 60, 100, 30);
		cmdPrint.setIcon(new ImageIcon(imageDir+"print.png"));
		cmdPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String devCode = String.valueOf(cmbPrintByDev.getSelectedItem());
				JasperReportViewer nw = JasperReportViewer.getInstance("rptACListPrint",devCode);
				nw.pack();
				if (nw.isVisible()) {
				} else {
					getDesktopPane().add(nw);
					nw.setVisible(true);
				}
				nw.setIconifiable(true);
				nw.setMaximizable(false);
				nw.setClosable(true);
				
				try {
					nw.setMaximum(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}

				dispose();   	
			}
		});
		
		cmdExit.setText("終了");
		cmdExit.setVisible(true);
		cmdExit.setBounds(440, 20, 100, 30);
		cmdExit.setIcon(new ImageIcon(imageDir+"exit.png"));
		cmdExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});

		cmbPrintByDev.setVisible(true);
		cmbPrintByDev.setBounds(540, 30, 100, 30);
		
		printByDev();

		JScrollPane sp = new JScrollPane();
		sp.setBounds(20, 100, 720, 420);
		sp.setOpaque(true);

		table = new JTable(new MyTableModel());
		table.setBorder(UIManager.getBorder("DesktopIcon.border"));
		table.setRowHeight(25);
		// table.setBackground(new java.awt.Color(176, 224, 230));
		table.removeColumn(table.getColumnModel().getColumn(6));

		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int firstRow = e.getFirstRow();
				int lastRow = e.getLastRow();
				int index = e.getColumn();
				int totalRows = table.getModel().getRowCount();
				// System.out.println(totalRows);

				if (String.valueOf(totalRows - 1).equals(String.valueOf(firstRow))) {
					if (!table.getModel().getValueAt(firstRow, index).equals("")) {
						MyTableModel dm = (MyTableModel) table.getModel();
						dm.addBlankRow();
					}
				}
				addChangeRow(firstRow);
			}
		});
		
		sp.setViewportView(table);

		// the column in the table
		/*
		 * table.setModel(new DefaultTableModel( new Object[][] { }, new
		 * String[] { "Division", "Code", "Name", "SubCode", "SubName",
		 * "FS Code Range" } ));
		 * 
		 * DefaultTableModel model = (DefaultTableModel) table.getModel();
		 * model.addRow(new Object[]{"Division", "Code ", "Name", "SubCode",
		 * "SubName", "FS Code Range"});
		 */
		DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
		singleclick.setClickCountToStart(1);
		// set the editor as default on every column
		/*for (int i = 0; i < table.getColumnCount() - 1; i++) {
			table.setDefaultEditor(table.getColumnClass(i), singleclick);
		}*/
		cmbFSCodeRange(table, table.getColumnModel().getColumn(5));

		pnlMain.add(lblAccountRegister);
		pnlMain.add(lblPrintByDev);
		pnlMain.add(lblEditMsg);
		pnlMain.add(cmdSave);
		pnlMain.add(cmdDelete);
		pnlMain.add(cmdCut);
		pnlMain.add(cmdCopy);
		pnlMain.add(cmdPaste);
		pnlMain.add(cmdExit);
		pnlMain.add(cmdPrint);
		pnlMain.add(cmbPrintByDev);
		pnlMain.add(sp, new Integer(0), 0);

		// loadAccountRegister();

	}

	private static FrmAccountRegister myInstance;

	public static FrmAccountRegister getInstance() {
		myInstance = new FrmAccountRegister();
		return myInstance;
	}

	
	public void addChangeRow(int row) {
		boolean hasValue = false;

		for (int i = 0; i < changeRow.size(); i++) {
			if (String.valueOf(changeRow.get(i)).equals(String.valueOf(row))) {
				hasValue = true;
				break;
			}

		}
		if (!hasValue) {
			changeRow.add(String.valueOf(row));
		}
	}

	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = { "部門コード", "科目コード", "科目名称", "細目コード", "細目名称", "財務諸表区分", "KeyCode" };
		private List<Object[]> clipboard = new ArrayList<Object[]>();
		// loadAccountRegister();
		List<Object[]> data = loadAccountRegister();
		/*
		 * private Object[][] data = { { "110", "lsdk", "slk", "lksd", "rwer",
		 * "we" }, { "110", "sdf", "sds", "wer", "ert", "ert" }, { "", "", "",
		 * "", "", "" } };
		 */

		/*
		 * public final Object[] longValues = {"Jane", "Kathy",
		 * "None of the above", new Integer(20), Boolean.TRUE};
		 */
		public void cut(int row[]){
			//Remove old data from clipboard
			for(int i=0;i<clipboard.size();i++) {
				clipboard.remove(i);
			}
			//Add new data to the clipboard
			for(int i=0;i<row.length;i++){
				Object[] ro = new Object[7]; 
				Object[] roval = data.get(row[i]);
				String v1 = (String) roval[0];
				String v2 = (String) roval[1];
				String v3 = (String) roval[2];
				String v4 = (String) roval[3];
				String v5 = (String) roval[4];
				comboItem v6 = (comboItem) roval[5];
				
				//int size = ro.length;
				//ro[size-1] = "";
				ro[0] = v1;
				ro[1] = v2;
				ro[2] = v3;
				ro[3] = v4;
				ro[4] = v5;
				ro[5] = v6;
				ro[6] = "";
				clipboard.add(ro);
			}
			//Remove data from rows
			/*for(int i=0;i<row.length;i++){
				data.remove(row[i]-i);
				fireTableDataChanged();
			}*/
		}
		
		public void paste()
		{
			int clipboardSize = clipboard.size();
			int dataIndex = data.size()-1; 
			data.remove(dataIndex);
			int s = changeRow.size();
			for(int i=0;i<clipboardSize;i++)
			{
				Object[] ro = new Object[7]; 
				Object[] roval = clipboard.get(i);
				
				String v1 = (String) roval[0];
				String v2 = (String) roval[1];
				String v3 = (String) roval[2];
				String v4 = (String) roval[3];
				String v5 = (String) roval[4];
				comboItem v6 = (comboItem) roval[5];
				
				ro[0] = v1;
				ro[1] = v2;
				ro[2] = v3;
				ro[3] = v4;
				ro[4] = v5;
				ro[5] = v6;
				ro[6] = "";
				
				data.add(ro);
				changeRow.addElement(dataIndex);
				dataIndex++;
			}
			addBlankRow();
			fireTableDataChanged();
		}
		
		public void addBlankRow() {
			Object[] blkrow = { "", "", "", "", "", "", "" };
			data.add(blkrow);
		}

		public void reloadData() {
			data = loadAccountRegister();
			fireTableDataChanged();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data.get(row)[col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col < 0) {
				return false;
			} else {
				return true;
			}
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
		public void setValueAt(Object value, int row, int col) {
			/*if (DEBUG) {
				System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
						+ value.getClass() + ")");
			}*/

			// data[row][col] = value;
			Object[] ob = data.get(row);
			ob[col] = value;
			data.set(row, ob);

			fireTableCellUpdated(row, col);

			if (DEBUG) {
				//System.out.println("New value of data:");
				printDebugData();
			}
		}

		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				//System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + data.get(i)[j]);
				}
				System.out.println();
			}
			// System.out.println("--------------------------");
		}
	}

	class comboItem {
		private String id;
		private String value;

		public comboItem(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getId() {
			return id;
		}

		public String getValue() {
			return value;
		}

		public String toString() {
			return value;
		}
	}

	public void save(int callState) {
		
		try {
			DBManager db = new DBManager();

			int totalRows = table.getModel().getRowCount();
			//System.out.println(totalRows);
			int changeCount = changeRow.size();
			int savedCount = 0;
			for (int i = 0; i < changeCount; i++) {
				comboItem fscr = new comboItem("","");
				int row = Integer.parseInt(String.valueOf(changeRow.get(i)));
				
				String devcode 		= String.valueOf(table.getModel().getValueAt(row, 0));
				String accode 		= String.valueOf(table.getModel().getValueAt(row, 1));
				String acname 		= String.valueOf(table.getModel().getValueAt(row, 2));
				String acsubcode 	= String.valueOf(table.getModel().getValueAt(row, 3));
				String acsubname 	= String.valueOf(table.getModel().getValueAt(row, 4));
				Object obj 			= table.getModel().getValueAt(row, 5);
				if((""!=obj) && (null!=obj))
					fscr = (comboItem)obj;
				String rowid = String.valueOf(table.getModel().getValueAt(row, 6));

				if(devcode.equals("")){
					JOptionPane.showMessageDialog(null, "部門コードを入力してください。", "エラー", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(acsubcode.equals("")){
					JOptionPane.showMessageDialog(null, "対象コードの値を入力してください。", "エラー", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// if (String.valueOf(totalRows -
				// 1).equals(String.valueOf(changeRow.get(i)))) {
				if (rowid.equals("")) {

					String keycode = devcode + accode + acsubcode;

					boolean check = checkKeycode(keycode);
					
					if (!check) {
						// Insert

						PreparedStatement ptmt = db.getPreparStamt(
								"INSERT INTO actablewk (keycode,devcode,accode,acname,acsubcode,acsubname,fscr) VALUES(?,?,?,?,?,?,?)");

						ptmt.setString(1, keycode);
						ptmt.setString(2, devcode);
						ptmt.setString(3, accode);
						ptmt.setString(4, acname);
						ptmt.setString(5, acsubcode);
						ptmt.setString(6, acsubname);
						ptmt.setString(7, fscr.getId());

						ptmt.executeUpdate();
						savedCount++;
					} else {
						JOptionPane.showMessageDialog(null, "コードが重複しています。","エラー",JOptionPane.ERROR_MESSAGE);
					}

					// System.out.println("Insert " + changeRow.get(i));
				} else {
					// Update

					PreparedStatement updatePtmt = db.getPreparStamt(
							"UPDATE  actablewk SET keycode =?, devcode =?,accode =?,acname =?,acsubcode =?,acsubname =?,fscr =? WHERE rowid = ?");

					String keycode = devcode + accode + acsubcode;

					boolean check = checkKeycodeForUpdate(keycode, rowid);

					if (!check) {

						updatePtmt.setString(1, keycode);
						updatePtmt.setString(2, devcode);
						updatePtmt.setString(3, accode);
						updatePtmt.setString(4, acname);
						updatePtmt.setString(5, acsubcode);
						updatePtmt.setString(6, acsubname);
						updatePtmt.setString(7, fscr.getId());
						updatePtmt.setString(8, rowid);

						updatePtmt.executeUpdate();
						savedCount++;
					} else {
						JOptionPane.showMessageDialog(null, "コードが重複しています。","エラー",JOptionPane.ERROR_MESSAGE);
					}	
					
				}	
				
			}
			if(savedCount == changeCount) {
				if(callState == 0) {
					// 0 for save
				JOptionPane.showMessageDialog(null, "登録しました。");
				} else if(callState == 1) {
					//1 for delete
					JOptionPane.showMessageDialog(null, "削除しました。");
				}
			}
			
			// Delete all of the data subjects table
			deleteAllFromActables();
			// Added to the subject table while rearrange the contents of the
			// ACTEMP
			moveFromActablewkToActables();

			// Update actable with bgbalance and budget
			updateActable();

		} catch (Exception e) {
			e.printStackTrace();
		}
		changeRow.removeAllElements();
		MyTableModel dm = (MyTableModel) table.getModel();
		dm.reloadData();
	}

	public List<Object[]> loadAccountRegister() {
		List<Object[]> data = new ArrayList<Object[]>();
		
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT actablewk.* FROM actablewk ORDER BY actablewk.keycode");
				int row = 0;
				while (rs.next()) {

					Object[] rowdata = { rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
							rs.getString(7), getComboItem(rs.getString(14)), rs.getString(1) };

					data.add(rowdata);
					row++;
				}

				Object[] blkrow = { "", "", "", "", "", "", "" };
				data.add(blkrow);
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public comboItem getComboItem(String id) {
		comboItem item = null;
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT fscrtables.fscr, fscrtables.fscrname FROM fscrtables WHERE fscrtables.fscr ='"
						+ id + "'");

				if (rs.next()) {
					String vid = rs.getString(1);
					String name = rs.getString(2);
					item = new comboItem(vid, name);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	public boolean checkKeycode(String keycode) {
		boolean duplicateCheck = false;
		try {
			DBManager db = new DBManager();

			ResultSet rs = db
					.getRecord("SELECT actablewk.keycode FROM actablewk WHERE actablewk.keycode='" + keycode + "'");
			if (rs.next()) {
				duplicateCheck = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duplicateCheck;
	}

	public boolean checkKeycodeForUpdate(String keycode, String rowId) {
		boolean duplicateCheck = false;
		try {
			DBManager db = new DBManager();

			ResultSet rs = db.getRecord("SELECT actablewk.keycode FROM actablewk WHERE actablewk.keycode='" + keycode
					+ "' AND actablewk.rowid <>" + rowId);
			if (rs.next()) {
				duplicateCheck = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duplicateCheck;
	}

	public void deleteAllFromActables() {
		DBManager db;
		try {
			db = new DBManager();

			PreparedStatement psDelete = db.getPreparStamt("DELETE FROM actables");

			psDelete.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void moveFromActablewkToActables() {
		DBManager db;
		try {
			db = new DBManager();

			PreparedStatement psMoveToActables = db.getPreparStamt(
					"INSERT INTO actables (keycode,devcode,accode,acname,acsubcode,acsubname,bgbalance,budget,budgetmd,budgetadd,budgetmv,no,fscr,cachcr) SELECT keycode,devcode,accode,acname,acsubcode,acsubname,bgbalance,budget,budgetmd,budgetadd,budgetmv,no,fscr,cachcr FROM actablewk");

			psMoveToActables.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateActable() {
		DBManager db;
		try {
			db = new DBManager();

			PreparedStatement psUpdateActableBgbalance = db
					.getPreparStamt("UPDATE actables SET actables.bgbalance = 0 WHERE bgbalance IS NULL");

			psUpdateActableBgbalance.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			db = new DBManager();

			PreparedStatement psUpdateActableBudget = db
					.getPreparStamt("UPDATE actables SET actables.budget = 0 WHERE budget IS NULL");

			psUpdateActableBudget.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void acTablemntFormOpen() {
		DBManager db;
		try {
			db = new DBManager();

			PreparedStatement deleteActablewk = db.getPreparStamt("DELETE FROM actablewk");

			deleteActablewk.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			db = new DBManager();

			PreparedStatement deleteActablewk = db.getPreparStamt("ALTER TABLE actablewk AUTO_INCREMENT = 1;");

			deleteActablewk.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			db = new DBManager();

			PreparedStatement insertActablewk = db.getPreparStamt(
					"INSERT INTO actablewk (keycode,devcode,accode,acname,acsubcode,acsubname,bgbalance,budget,budgetmd,budgetadd,budgetmv,no,fscr,cachcr) SELECT keycode,devcode,accode,acname,acsubcode,acsubname,bgbalance,budget,budgetmd,budgetadd,budgetmv,no,fscr,cachcr FROM actables");

			insertActablewk.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printByDev() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord(
						"SELECT devcode FROM devtables");
				//cmbPrintByDev.addItem("");
				while (rs.next()) {
					cmbPrintByDev.addItem(rs.getString(1));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRow(String ids) {
		DBManager db;
		try {
			db = new DBManager();

			PreparedStatement deleteActablewk = db.getPreparStamt("DELETE FROM actablewk WHERE rowid IN("+ids+")");

			deleteActablewk.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Close() {
		FrmAccountMaintenance frmAccountMaintenance = FrmAccountMaintenance.getInstance();
		frmAccountMaintenance.setBounds(0, 0, 579, 500);
		frmAccountMaintenance.getContentPane().setBackground(new Color(176,224,230));
		frmAccountMaintenance.setForeground(Color.black);
		frmAccountMaintenance.setResizable(false);
		frmAccountMaintenance.setTitle("アカウントを維持する");
		frmAccountMaintenance.getContentPane().setLayout(null);
		
		if (frmAccountMaintenance.isVisible()) {
		} else {
			getDesktopPane().add(frmAccountMaintenance);
			frmAccountMaintenance.setVisible(true);
		}

		frmAccountMaintenance.setIconifiable(true);
		frmAccountMaintenance.setMaximizable(false);
		frmAccountMaintenance.setClosable(true);
		dispose();
		
	}
}
