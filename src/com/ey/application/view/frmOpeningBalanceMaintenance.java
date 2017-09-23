package com.ey.application.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import com.ey.application.controller.DBManager;

public class frmOpeningBalanceMaintenance extends FrmBase {

	JPanel pnlMain = new JPanel();
	private JTable table;

	private JPanel pnlForm = new JPanel();

	private JLabel lblSettingOpeningBalance = new JLabel();
	private JLabel lblCreditBalance = new JLabel();

	private JButton cmdClose = new JButton();
	private JButton cmdClear = new JButton();
	private JButton cmdUpdate = new JButton();

	private JLabel lblDepartmentCode = new JLabel();
	private JLabel lblCourseCode = new JLabel();
	private JLabel lblCourseName = new JLabel();
	private JLabel lblSpecificCode = new JLabel();
	private JLabel lblSpecificName = new JLabel();
	private JLabel lblOpeningBalance = new JLabel();

	private JTextField txtDepartmentCode = new JTextField();
	private JTextField txtCourseCode = new JTextField();
	private JTextField txtCourseName = new JTextField();
	private JTextField txtSpecificCode = new JTextField();
	private JTextField txtSpecificName = new JTextField();
	private JTextField txtOpeningBalance = new JTextField();
	
	private JTextField txtKeycode = new JTextField();
	// End of variables declaration
	private int row;

	public frmOpeningBalanceMaintenance() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() {

		this.getContentPane().add(pnlMain, null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				Close();
			}
		});

		pnlMain.setBackground(new Color(176, 224, 230));
		pnlMain.setBorder(BorderFactory.createEtchedBorder());
		pnlMain.setBounds(new Rectangle(9, 9, 782, 600));
		pnlMain.setLayout(null);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		
		//JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		/*sp.setVisible(true);
		add(sp);*/
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		lblSettingOpeningBalance.setText("期首残高 設定");
		lblSettingOpeningBalance.setBounds(10, 10, 250, 30);
		lblSettingOpeningBalance.setVisible(true);
		lblSettingOpeningBalance.setBorder(BorderFactory.createRaisedBevelBorder());
		lblSettingOpeningBalance.setHorizontalAlignment(SwingConstants.CENTER);

		lblCreditBalance.setText("貸方残高はマイナスで入力");
		lblCreditBalance.setBounds(360, 40, 250, 30);
		lblCreditBalance.setVisible(true);
		lblCreditBalance.setBorder(BorderFactory.createLineBorder(Color.white));
		lblCreditBalance.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDepartmentCode.setText("部門コード");
		lblDepartmentCode.setBounds(10, 80, 70, 30);
		lblDepartmentCode.setVisible(true);
		lblDepartmentCode.setBorder(BorderFactory.createRaisedBevelBorder());
		lblDepartmentCode.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtDepartmentCode.setVisible(true);
		txtDepartmentCode.setBounds(10, 115, 70, 30);
		
		lblCourseCode.setText("科目コード");
		lblCourseCode.setBounds(90, 80, 70, 30);
		lblCourseCode.setVisible(true);
		lblCourseCode.setBorder(BorderFactory.createRaisedBevelBorder());
		lblCourseCode.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtCourseCode.setVisible(true);
		txtCourseCode.setBounds(90, 115, 70, 30);
		
		lblCourseName.setText("科目名");
		lblCourseName.setBounds(170, 80, 200, 30);
		lblCourseName.setVisible(true);
		lblCourseName.setBorder(BorderFactory.createRaisedBevelBorder());
		lblCourseName.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtCourseName.setVisible(true);
		txtCourseName.setBounds(170, 115, 200, 30);
		
		lblSpecificCode.setText("細目コード");
		lblSpecificCode.setBounds(380, 80, 70, 30);
		lblSpecificCode.setVisible(true);
		lblSpecificCode.setBorder(BorderFactory.createRaisedBevelBorder());
		lblSpecificCode.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtSpecificCode.setVisible(true);
		txtSpecificCode.setBounds(380, 115, 70, 30);
		
		lblSpecificName.setText("細目名");
		lblSpecificName.setBounds(460, 80, 200, 30);
		lblSpecificName.setVisible(true);
		lblSpecificName.setBorder(BorderFactory.createRaisedBevelBorder());
		lblSpecificName.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtSpecificName.setVisible(true);
		txtSpecificName.setBounds(460, 115, 200, 30);
		
		lblOpeningBalance.setText("期首残高");
		lblOpeningBalance.setBounds(670, 80, 90, 30);
		lblOpeningBalance.setVisible(true);
		lblOpeningBalance.setBorder(BorderFactory.createRaisedBevelBorder());
		lblOpeningBalance.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtOpeningBalance.setVisible(true);
		txtOpeningBalance.setBounds(670, 115, 90, 30);
		
		cmdClear.setText("クリア");
		cmdClear.setVisible(true);
		cmdClear.setBounds(300, 155, 70, 30);
		cmdClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		
		cmdUpdate.setText("更新");
		cmdUpdate.setVisible(true);
		cmdUpdate.setBounds(380, 155, 70, 30);
		cmdUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean Fo = updateActables();
				if(Fo)
				{  	    
	        	    table.setValueAt(txtDepartmentCode.getText(), row, 0);
	        	    table.setValueAt(txtCourseCode.getText(), row, 1);
	        	    table.setValueAt(txtCourseName.getText(), row, 2);
	        	    table.setValueAt(txtSpecificCode.getText(), row, 3);
	        	    table.setValueAt(txtSpecificName.getText(), row, 4);
	        	    table.setValueAt(txtOpeningBalance.getText(), row, 5);
	        	    
					//pnlForm.setVisible(false);
				}
			}
        });
		
		cmdClose.setText("閉じる");
		cmdClose.setVisible(true);
		cmdClose.setBounds(680, 10, 70, 30);
		cmdClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});
		
		
		DefaultTableModel data = new DefaultTableModel();

		data.addColumn("部門");
		data.addColumn("");
		data.addColumn("科目名");
		data.addColumn("");
		data.addColumn("細目名");
		data.addColumn("期首残高");
		data.addColumn("");
		
		Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");

	    JLabel headerLabel1 = new JLabel("部門", JLabel.CENTER);
	    headerLabel1.setBorder(headerBorder);
	    JLabel headerLabel2 = new JLabel("科目名", JLabel.CENTER);
	    headerLabel2.setBorder(headerBorder);
	    JLabel headerLabel3 = new JLabel("細目名", JLabel.CENTER);
	    headerLabel3.setBorder(headerBorder);
	    JLabel headerLabel4 = new JLabel("期首残高", JLabel.CENTER);
	    headerLabel4.setBorder(headerBorder);
		
	    TableCellRenderer renderer = new JComponentTableCellRenderer();
	    
		TableColumn dept = new TableColumn(0);
		dept.setHeaderRenderer(renderer);
		dept.setHeaderValue(headerLabel1);
		
		MergeTableHeader.XTableColumn subname = new MergeTableHeader.XTableColumn();
		subname.setHeaderRenderer(renderer);
		subname.setHeaderValue(headerLabel2);
		subname.setHeaderSpan(2);
		subname.setModelIndex(1);
		
		MergeTableHeader.XTableColumn spcname = new MergeTableHeader.XTableColumn();
		spcname.setHeaderRenderer(renderer);
		spcname.setHeaderValue(headerLabel3);
		spcname.setHeaderSpan(2);
		spcname.setModelIndex(3);
		
		TableColumn opbal = new TableColumn(5);
		opbal.setHeaderRenderer(renderer);
		opbal.setHeaderValue(headerLabel4);
		
		TableColumn id = new TableColumn(6);
		id.setHeaderValue("");
		id.setHeaderRenderer(renderer);
		
		TableColumnModel columns = new DefaultTableColumnModel();
		
		columns.addColumn(dept);
		columns.addColumn(subname);
		columns.addColumn(new TableColumn(2));
		columns.addColumn(spcname);
		columns.addColumn(new TableColumn(4));
		columns.addColumn(opbal);
		columns.addColumn(id);
		
		table = new JTable(data, columns){
			protected void configureEnclosingScrollPane() {
			}
		};

		table.setTableHeader(null);
		
		MergeTableHeader header = new MergeTableHeader(table);

		header.setForeground(Color.black);
		header.setBackground(Color.GRAY);
		//header.setBorder(BorderFactory.createEtchedBorder());
		//header.setFont(header.getFont().deriveFont(18.0f));
		
		//table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		//table.setRowHeight(27);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setColumnHeaderView(header);
		sp.setBounds(0, 200, 750, 350);
		sp.setOpaque(true);
		//sp.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// the column in the table
		/*table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Department", " ","Subject name", " ","Specific name", "Opening Balance",""}));*/
		
		pnlMain.add(sp, new Integer(0), 0);
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				row = table.rowAtPoint(e.getPoint());
				int col= table.columnAtPoint(e.getPoint());

				txtDepartmentCode.setText(table.getModel().getValueAt(row, 0).toString());
				txtCourseCode.setText(table.getModel().getValueAt(row, 1).toString());
				txtCourseName.setText(table.getModel().getValueAt(row, 2).toString());
				txtSpecificCode.setText(table.getModel().getValueAt(row, 3).toString());
				txtSpecificName.setText(table.getModel().getValueAt(row, 4).toString());
				txtOpeningBalance.setText(table.getModel().getValueAt(row, 5).toString());
				txtKeycode.setText(table.getModel().getValueAt(row, 6).toString());
			}
		});
		
				
		TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn(table.getColumnModel().getColumn(6));
        table.getColumnModel().getColumn(0).setPreferredWidth(75);
        table.getColumnModel().getColumn(1).setPreferredWidth(75);
        table.getColumnModel().getColumn(2).setPreferredWidth(205);
        table.getColumnModel().getColumn(3).setPreferredWidth(75);
        table.getColumnModel().getColumn(4).setPreferredWidth(205);
        table.getColumnModel().getColumn(5).setPreferredWidth(95);
        //table.getColumnModel().getColumn(0).setMinWidth(500);

		pnlMain.add(lblSettingOpeningBalance);
		pnlMain.add(lblCreditBalance);
		pnlMain.add(cmdClose);
		pnlMain.add(lblDepartmentCode);
		pnlMain.add(lblCourseCode);
		pnlMain.add(lblCourseName);
		pnlMain.add(lblSpecificCode);
		pnlMain.add(lblSpecificName);
		pnlMain.add(lblOpeningBalance);
		
		pnlMain.add(txtDepartmentCode);
		pnlMain.add(txtCourseCode);
		pnlMain.add(txtCourseName);
		pnlMain.add(txtSpecificCode);
		pnlMain.add(txtSpecificName);
		pnlMain.add(txtOpeningBalance);
		pnlMain.add(cmdClear);
		pnlMain.add(cmdUpdate);
		
		loadOpeningBalance();

	}

	public void loadOpeningBalance() {
		try {
			DBManager db = new DBManager();
			try {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				ResultSet rs;
				rs = db.getRecord(
						"SELECT DISTINCTROW  actables.devcode, actables.accode, actables.acname, actables.acsubcode, actables.acsubname, actables.bgbalance, actables.budget, actables.budgetmd, actables.keycode FROM actables");
				NumberFormat formatter = new DecimalFormat("#0.00");
				//NumberFormat formatter = new DecimalFormat("#0.00");
				//dramount=formatter.format(debitAmount);
				while (rs.next()) {
					
					String department_code = rs.getString(1);
					String courseCode = rs.getString(2);
					String courseName = rs.getString(3);
					String specificCode = rs.getString(4);
					String specificName = rs.getString(5);
					// String openingBalance=rs.getString(6);

					double openingBalance = rs.getDouble(6);
					String openingbalance = "0";
					//openingBalance = formatter.format(openingBalance);
					if(openingBalance != 0)
						openingbalance=formatter.format(openingBalance);
					
					String keycode = rs.getString(9);

					dtm.addRow(new Object[] {department_code, courseCode, courseName, specificCode, specificName,
							openingbalance, keycode });

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
	
	public boolean updateActables() {
		boolean Fo = false;
 		try {
			DBManager db = new DBManager();
			try {
				String sql = "UPDATE actables SET " + 
						"devcode ='" +	txtDepartmentCode.getText() + 
						"',accode ='"+txtCourseCode.getText() +
						"',acname ='"+txtCourseName.getText() +
						"',acsubcode ='"+txtSpecificCode.getText() +
						"',acsubname ='"+txtSpecificName.getText() +
						"',bgbalance ="+txtOpeningBalance.getText() + " WHERE keycode = " +txtKeycode.getText();
				System.out.println(sql);
				Fo = db.doQuery(sql);
				
        	    
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		return Fo;
	}
	
	public void clear() {
		txtDepartmentCode.setText("");
		txtCourseCode.setText("");
		txtCourseName.setText("");
		txtSpecificCode.setText("");
		txtSpecificName.setText("");
		txtOpeningBalance.setText("");
	}

	private static frmOpeningBalanceMaintenance myInstance;

	public static frmOpeningBalanceMaintenance getInstance() {
		myInstance = new frmOpeningBalanceMaintenance();
		return myInstance;
	}

	public void Close() {
		FrmAccountMaintenance frmAccountMaintenance = FrmAccountMaintenance.getInstance();
		frmAccountMaintenance.setBounds(0, 0, 579, 500);
		frmAccountMaintenance.getContentPane().setBackground(new Color(176, 224, 230));
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
class JComponentTableCellRenderer implements TableCellRenderer {
	  public Component getTableCellRendererComponent(JTable table, Object value, 
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    return (JComponent)value;
	  }
	}
