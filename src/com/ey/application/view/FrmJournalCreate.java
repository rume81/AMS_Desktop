package com.ey.application.view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;

import com.ey.application.controller.DBManager;

public class FrmJournalCreate extends FrmBase {
	
	JPanel pnlMain = new JPanel();
	
	JLabel lblDepartment 		= new JLabel();
	JLabel lblRearrangement 	= new JLabel();
	JLabel lblOutputmonth 		= new JLabel();
	JLabel lblMonth 			= new JLabel();
	JLabel lblFromDay 			= new JLabel();
	JLabel lblMonth2 			= new JLabel();
	JLabel lblUntilDay 			= new JLabel();
	JLabel lblDocumentRangeNum 	= new JLabel();
	JLabel lblFrom 				= new JLabel();
	JLabel lblUntil 			= new JLabel();
	
	JComboBox cmbDepartment 	= new JComboBox();
	JComboBox cmbRearrangement 	= new JComboBox();
	
	JTextField txtMonth 		= new JTextField();
	JTextField txtFromDay 		= new JTextField();
	JTextField txtMonth2 		= new JTextField();
	JTextField txtUntilday 		= new JTextField();
	JTextField txtDocumentRangeFrom = new JTextField();
	JTextField txtDocumentRangeTo = new JTextField();
	
	JButton cmdRun 				= new JButton();
	JButton cmdClose		 	= new JButton();
	
	
	public FrmJournalCreate() {
		try {
			jbInit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void jbInit() throws Exception {
		this.getContentPane().add(pnlMain, null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				Close();
			}
		});
		
		pnlMain.setBackground(new Color(176, 224, 230));
		pnlMain.setBorder(BorderFactory.createEtchedBorder());
		pnlMain.setBounds(new Rectangle(9, 9, 640, 520));
		pnlMain.setLayout(null);
		
		lblDepartment.setText("部門の選択");
		lblDepartment.setVisible(true);
		lblDepartment.setBounds(10, 20, 100, 30);
		lblDepartment.setBorder(BorderFactory.createLoweredBevelBorder());
		lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblRearrangement.setText("並替選択");
		lblRearrangement.setVisible(true);
		lblRearrangement.setBounds(140, 20, 100, 30);
		lblRearrangement.setBorder(BorderFactory.createLoweredBevelBorder());
		lblRearrangement.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblOutputmonth.setText("出力月日の選択");
		lblOutputmonth.setVisible(true);
		lblOutputmonth.setBounds(10, 100, 217, 30);
		lblOutputmonth.setBorder(BorderFactory.createLoweredBevelBorder());
		lblOutputmonth.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblMonth.setText("月");
		lblMonth.setVisible(true);
		lblMonth.setBounds(75, 135, 30, 30);
		lblMonth.setBorder(BorderFactory.createLoweredBevelBorder());
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblFromDay.setText("日から");
		lblFromDay.setVisible(true);
		lblFromDay.setBounds(175, 135, 50, 30);
		lblFromDay.setBorder(BorderFactory.createLoweredBevelBorder());
		lblFromDay.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblMonth2.setText("月");
		lblMonth2.setVisible(true);
		lblMonth2.setBounds(75, 170, 30, 30);
		lblMonth2.setBorder(BorderFactory.createLoweredBevelBorder());
		lblMonth2.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblUntilDay.setText("日まで");
		lblUntilDay.setVisible(true);
		lblUntilDay.setBounds(175, 170, 50, 30);
		lblUntilDay.setBorder(BorderFactory.createLoweredBevelBorder());
		lblUntilDay.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDocumentRangeNum.setText("伝票番号の範囲");
		lblDocumentRangeNum.setVisible(true);
		lblDocumentRangeNum.setBounds(10, 220, 235, 30);
		lblDocumentRangeNum.setBorder(BorderFactory.createLoweredBevelBorder());
		lblDocumentRangeNum.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblFrom.setText("から");
		lblFrom.setVisible(true);
		lblFrom.setBounds(85, 255, 40, 30);
		lblFrom.setBorder(BorderFactory.createLoweredBevelBorder());
		lblFrom.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblUntil.setText("まで");
		lblUntil.setVisible(true);
		lblUntil.setBounds(205, 255, 40, 30);
		lblUntil.setBorder(BorderFactory.createLoweredBevelBorder());
		lblUntil.setHorizontalAlignment(SwingConstants.CENTER);
		
		cmbDepartment.setVisible(true);
		cmbDepartment.setBounds(10, 55, 100, 30);
		
		cmbRearrangement.setVisible(true);
		cmbRearrangement.setBounds(140, 55, 100, 30);
		cmbRearrangement.addItem("1"+" | "+"入力順");
		cmbRearrangement.addItem("2"+" | "+"日付順");
		
		txtMonth.setVisible(true);
		txtMonth.setBounds(10, 135, 60, 30);
		txtMonth.setBorder(BorderFactory.createLoweredBevelBorder());
		txtMonth.setText("4");
		
		txtFromDay.setVisible(true);
		txtFromDay.setBounds(110, 135, 60, 30);
		txtFromDay.setBorder(BorderFactory.createLoweredBevelBorder());
		txtFromDay.setText("1");
		
		txtMonth2.setVisible(true);
		txtMonth2.setBounds(10, 170, 60, 30);
		txtMonth2.setBorder(BorderFactory.createLoweredBevelBorder());
		txtMonth2.setText("3");
		
		txtUntilday.setVisible(true);
		txtUntilday.setBounds(110, 170, 60, 30);
		txtUntilday.setBorder(BorderFactory.createLoweredBevelBorder());
		txtUntilday.setText("31");
		
		txtDocumentRangeFrom.setVisible(true);
		txtDocumentRangeFrom.setBounds(10, 255, 70, 30);
		txtDocumentRangeFrom.setBorder(BorderFactory.createLoweredBevelBorder());
		txtDocumentRangeFrom.setText("0");
		
		txtDocumentRangeTo.setVisible(true);
		txtDocumentRangeTo.setBounds(130, 255, 70, 30);
		txtDocumentRangeTo.setBorder(BorderFactory.createLoweredBevelBorder());
		txtDocumentRangeTo.setText("999999");
		
		cmdRun.setText("実　行");
		cmdRun.setVisible(true);
		cmdRun.setBounds(10, 305, 100, 50);
		cmdRun.setHorizontalAlignment(SwingConstants.CENTER);
		cmdRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cmd_run_Click();
			}
		});
		
		
		cmdClose.setText("閉じる");
		cmdClose.setVisible(true);
		cmdClose.setBounds(140, 305, 100, 50);
		cmdClose.setHorizontalAlignment(SwingConstants.CENTER);
		cmdClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});

		
		
		pnlMain.add(lblDepartment);
		pnlMain.add(lblRearrangement);
		pnlMain.add(lblOutputmonth);
		pnlMain.add(lblMonth);
		pnlMain.add(lblFromDay);
		pnlMain.add(lblMonth2);
		pnlMain.add(lblUntilDay);
		pnlMain.add(lblDocumentRangeNum);
		pnlMain.add(lblFrom);
		pnlMain.add(lblUntil);
		pnlMain.add(cmbDepartment);
		pnlMain.add(cmbRearrangement);
		pnlMain.add(txtMonth);
		pnlMain.add(txtFromDay);
		pnlMain.add(txtMonth2);
		pnlMain.add(txtUntilday);
		pnlMain.add(txtDocumentRangeFrom);
		pnlMain.add(txtDocumentRangeTo);
		pnlMain.add(cmdRun);
		pnlMain.add(cmdClose);
		
		
		departmentCode();
		
	}
	
	public void departmentCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord(
						"SELECT devcode, devname FROM devtables");
				//cmbPrintByDev.addItem("");
				while (rs.next()) {
					String devcode = rs.getString(1);
					String devname = rs.getString(2);
					
					String Iteam = devcode+" | "+devname;
					cmbDepartment.addItem(Iteam);
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
	
	public void Cmd_run_Click() {
		
		int NARABI;
		String BUMON = "";
		long NEN_FROM = 0;
		long TUKI_FROM;
		long HI_FROM = 0;
		long NEN_TO = 0;
		long TUKI_TO;
		long HI_TO;
		long KESSAN_NEN;
		long KESSAN_TUKI;
		long YMD_FROM = 0;
		long YMD_TO = 0;
		long SIWAKE_FROM;
		long SIWAKE_TO;
		String JOKEN = null;
		String SQL_TEMP;
		
		if(cmbDepartment.getSelectedItem().equals("")) {
			BUMON = "";
		}
		if(cmbRearrangement.getSelectedItem().equals("")) {
			cmbRearrangement.setSelectedItem("1"+" | "+"入力順");
			NARABI = 1;
		}
		
		if(txtMonth.getText().equals("")) {
			txtMonth.setText("4");
			TUKI_FROM = 4;
		}
		if(txtMonth2.getText().equals("")) {
			txtMonth2.setText("4");
			TUKI_TO = 4;
		}
		if(txtFromDay.getText().equals("")) {
			txtFromDay.setText("1");
			HI_FROM = 1;
		}
		if(txtUntilday.getText().equals("")) {
			
		}
		if(txtDocumentRangeFrom.getText().equals("")) {
			txtDocumentRangeFrom.setText("1");
			SIWAKE_FROM = 1;
		}
		if(txtDocumentRangeTo.getText().equals("")) {
			txtDocumentRangeTo.setText("999999");
			SIWAKE_FROM = 999999;
		}
		
		if(cmbRearrangement.getSelectedItem().equals("1"+" | "+"入力順")) {
			NARABI = 1;
		} else{
			NARABI = 2;
		}
			
		BUMON 		= String.valueOf(cmbDepartment.getSelectedItem()).split(" | ")[0];
		TUKI_FROM 	= Long.parseLong(String.valueOf(txtMonth.getText()));
		HI_FROM 	= Long.parseLong(String.valueOf(txtFromDay.getText()));
		TUKI_TO 	= Long.parseLong(String.valueOf(txtMonth2.getText()));
		HI_TO 		= Long.parseLong(String.valueOf(txtUntilday.getText()));
		YMD_FROM 	= YM_GET(TUKI_FROM, HI_FROM);
		YMD_TO 		= YM_GET(TUKI_TO, HI_TO);
		NEN_FROM 	= (YMD_FROM / 10000);
		NEN_TO		= (YMD_TO / 10000);
		SIWAKE_FROM = Long.parseLong(String.valueOf(txtDocumentRangeFrom.getText()));
		SIWAKE_TO 	= Long.parseLong(String.valueOf(txtDocumentRangeTo.getText()));
		
		try {
			DBManager dbt = new DBManager();
			try {
				boolean fo = dbt.doQuery("DROP VIEW IF EXISTS journal_table");	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(NARABI == 1) {
			//Journal numerical order
			try {
				DBManager db = new DBManager();
				try {
					
					String sql = "SELECT DISTINCTROW je_number, s_number, l_number, yyyy, mm, dd, journals.devcode, "
							+ "Debit_Department_Subjects_Specific.devname,journals.draccode, "
							+ "Debit_Department_Subjects_Specific.acname AS debit_subject_name, "
							+ "journals.dracsubcode,Debit_Department_Subjects_Specific.acsubname AS debit_details_name, "
							+ "dramount, journals.craccode, Credit_Department_Subjects_Specific.acname AS credit_subject_name, "
							+ "journals.cracsubcode, Credit_Department_Subjects_Specific.acsubname AS credit_details_name, "
							+ "cramount, journals.descode, journals.desname, journals.vendorname, transtime, sundry , "
							+ "yyyy * 10000 + mm * 100 + dd AS date FROM journals LEFT JOIN Debit_Department_Subjects_Specific ON "
							+ "journals.drkey = Debit_Department_Subjects_Specific.aggregate_key LEFT JOIN "
							+ "Credit_Department_Subjects_Specific ON journals.crkey = Credit_Department_Subjects_Specific.aggregate_key "
							+ "WHERE journals.devcode = '"+ BUMON +"' AND yyyy * 10000 + mm * 100 + dd >= "+ YMD_FROM 
							+ " AND yyyy * 10000 + mm * 100 + dd  <=" + YMD_TO + " AND s_number >=" + SIWAKE_FROM 
							+ " AND s_number <=" + SIWAKE_TO + " ORDER BY je_number, l_number, yyyy, mm, dd ";
					
					StringBuffer strViewSql = new StringBuffer("CREATE VIEW journal_table AS "+ sql); 
					System.out.println(strViewSql.toString());
					boolean fo1= db.doQuery(strViewSql.toString());
					/*ResultSet rs;
					rs = db.getRecord(sql);*/
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				} finally {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(NARABI ==2) {
			try {
				DBManager db = new DBManager();
				try {
					String sql = "SELECT DISTINCTROW je_number, s_number, l_number, yyyy, mm, dd, journals.devcode, "
							+ "Debit_Department_Subjects_Specific.devname,journals.draccode, Debit_Department_Subjects_Specific.acname "
							+ "AS debit_subject_name, journals.dracsubcode,Debit_Department_Subjects_Specific.acsubname "
							+ "AS debit_details_name, dramount, journals.craccode, Credit_Department_Subjects_Specific.acname "
							+ "AS credit_subject_name, journals.cracsubcode, Credit_Department_Subjects_Specific.acsubname "
							+ "AS credit_details_name, cramount, journals.descode, journals.desname,journals.vendorname, "
							+ "transtime, sundry , yyyy * 10000 + mm * 100 + dd AS date FROM journals "
							+ "LEFT JOIN Debit_Department_Subjects_Specific ON journals.drkey = Debit_Department_Subjects_Specific.aggregate_key "
							+ "LEFT JOIN Credit_Department_Subjects_Specific ON journals.crkey = Credit_Department_Subjects_Specific.aggregate_key "
							+ "WHERE journals.devcode = '"+ BUMON +"' AND yyyy * 10000 + mm * 100 + dd >= "
							+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd  <=" + YMD_TO + " AND s_number >=" 
							+ SIWAKE_FROM + " AND s_number <=" + SIWAKE_TO + " ORDER BY yyyy, mm, dd, je_number,l_number";
					
					
					
					StringBuffer strViewSql = new StringBuffer("CREATE VIEW journal_table AS "+ sql); 
					boolean fo1= db.doQuery(strViewSql.toString());
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				} finally {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(NARABI ==1) {
			JOKEN = "入力順";
		} else if(NARABI == 2) {
			JOKEN = "日付順";
		}
		boolean fo = false;
		try {
			DBManager db = new DBManager();
			
			try {
				String sql = "SELECT * FROM reportparameters";
				
				ResultSet rs = db.getRecord(sql);
				
				if(rs.next()) {
					fo = true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String je_extraction_cond ="抽出条件　部門" + BUMON + "  " + NEN_FROM + "年" + TUKI_FROM + "月" + HI_FROM + "日から" + NEN_TO + "年" + TUKI_TO + "月" + HI_TO + "日まで・" + JOKEN;
		try {
			DBManager dbc = new DBManager();
			if(fo) {
				PreparedStatement pstm = dbc.getPreparStamt("UPDATE reportparameters SET je_extraction_cond = '" + je_extraction_cond+ "'");
				pstm.executeUpdate();
			} else {
				PreparedStatement pstm = dbc.getPreparStamt("INSERT INTO reportparameters(je_extraction_cond) VALUES(?)");
				
				pstm.setString(1, je_extraction_cond);
				pstm.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (NARABI == 1) {
			String param = BUMON+ "~" +YMD_FROM+ "~" +YMD_TO+ "~" +SIWAKE_FROM+ "~" + SIWAKE_TO;
			
			JasperReportViewer nw = JasperReportViewer.getInstance("Journal",param);
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
			
		} else if (NARABI == 2) {
			String param = BUMON + "~" +YMD_FROM + "~" +YMD_TO +"~" +SIWAKE_FROM + "~" + SIWAKE_TO;
			
			JasperReportViewer nw = JasperReportViewer.getInstance("Journal2",param);
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
		}
	}
	//To get the closing date
	public long YM_GET (long TUKI_DATA, long HI_DATA) {
		long KESSAN_NEN = 0;
		long KESSAN_TUKI = 0;
		long NEN_DATA = 0;
		
		try {
			DBManager db = new DBManager();
			
			try {
				String sql = "SELECT * FROM basedatas";
				
				ResultSet rs = db.getRecord(sql);
				 
				if(rs.next()) {
					KESSAN_NEN = rs.getLong(2);
					KESSAN_TUKI = rs.getLong(3);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (TUKI_DATA> KESSAN_TUKI) {
			NEN_DATA = KESSAN_NEN - 1;
		}
		if(TUKI_DATA <= KESSAN_TUKI) {
			NEN_DATA = KESSAN_NEN;
		}
		return (NEN_DATA * 10000 + TUKI_DATA * 100 + HI_DATA);
	}
	
	

	private static FrmJournalCreate myInstance;
	
	public static FrmJournalCreate getInstance() {
		myInstance = new FrmJournalCreate();
		return myInstance;
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
