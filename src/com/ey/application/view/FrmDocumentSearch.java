package com.ey.application.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;

import com.ey.application.controller.DBManager;
import com.ey.application.view.FrmAccountRegister.comboItem;

public class FrmDocumentSearch extends FrmBase {
	
	JPanel pnlMain = new JPanel();
	
	JLabel lblSelectDepartment 	= new JLabel();
	JLabel lblJournalNoRange 	= new JLabel();
	JLabel lblJournalNoFrom 	= new JLabel();
	JLabel lblJournalNoTo 		= new JLabel();
	JLabel lblDateRange 		= new JLabel();
	JLabel lblYearFrom 			= new JLabel();
	JLabel lblMonthFrom 		= new JLabel();
	JLabel lbldayFrom 			= new JLabel();
	JLabel lblDateFrom 			= new JLabel();
	JLabel lblYearTo 			= new JLabel();
	JLabel lblMonthTo 			= new JLabel();
	JLabel lblDayTo 			= new JLabel();
	JLabel lblDateTo 			= new JLabel();
	JLabel lblAmountRange 		= new JLabel();
	JLabel lblAmountFrom 		= new JLabel();
	JLabel lblAmountTo 			= new JLabel();
	JLabel lblAccountSelect 	= new JLabel();
	JLabel lblMatchVendor 		= new JLabel();
	JLabel lblMatchDescription 	= new JLabel();

	JTextField txtJournalFrom 		= new JTextField();
	JTextField txtJournalTo 		= new JTextField();
	JTextField txtYearFrom 			= new JTextField();
	JTextField txtMonthFrom 		= new JTextField();
	JTextField txtDayFrom 			= new JTextField();
	JTextField txtYearTo 			= new JTextField();
	JTextField txtMonthTo 			= new JTextField();
	JTextField txtDayTo 			= new JTextField();
	JTextField txtAmountRangeFrom 	= new JTextField();
	JTextField txtAmountrangeTo 	= new JTextField();
	JTextField txtMatchVendor 		= new JTextField();
	JTextField txtMatchDescription 	= new JTextField();
	
	JComboBox cmbSelectDepartment 	= new JComboBox();
	JComboBox cmbAccountSelect1 	= new JComboBox();
	JComboBox cmbAccountSelect2 	= new JComboBox();
	JComboBox cmbAccountSelect3 	= new JComboBox();
	
	JButton cmdJournalLog 			= new JButton();
	JButton cmdJournalSearch 		= new JButton();
	JButton cmdJournalPrint 		= new JButton();
	JButton cmdExit 				= new JButton();
	
	public FrmDocumentSearch() {
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
		
		lblSelectDepartment.setText("部門の選択");
		lblSelectDepartment.setVisible(true);
		lblSelectDepartment.setBounds(10, 20, 110, 30);
		lblSelectDepartment.setBorder(BorderFactory.createLoweredBevelBorder());
		
		cmbSelectDepartment.setVisible(true);
		cmbSelectDepartment.setBounds(10, 50, 110, 30);
		cmbSelectDepartment.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//departmentCode();
			}
		});
		
		lblJournalNoRange.setText("伝票番号の範囲");
		lblJournalNoRange.setVisible(true);
		lblJournalNoRange.setBounds(10, 90, 270, 30);
		lblJournalNoRange.setBorder(BorderFactory.createLoweredBevelBorder());
		lblJournalNoRange.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtJournalFrom.setText("0");
		txtJournalFrom.setVisible(true);
		txtJournalFrom.setBounds(10, 120, 100, 30);
		txtJournalFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblJournalNoFrom.setText("から");
		lblJournalNoFrom.setVisible(true);
		lblJournalNoFrom.setBounds(110, 120, 35, 30);
		
		txtJournalTo.setText("999999");
		txtJournalTo.setVisible(true);
		txtJournalTo.setBounds(145, 120, 100, 30);
		txtJournalTo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblJournalNoTo.setText("まで");
		lblJournalNoTo.setVisible(true);
		lblJournalNoTo.setBounds(245, 120, 35, 30);
		
		lblDateRange.setText("日付の範囲");
		lblDateRange.setVisible(true);
		lblDateRange.setBounds(10, 150, 270, 30);
		lblDateRange.setBorder(BorderFactory.createLoweredBevelBorder());
		lblDateRange.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtYearFrom.setText("1996");
		txtYearFrom.setVisible(true);
		txtYearFrom.setBounds(10, 180, 60, 30);
		txtYearFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblYearFrom.setText("年");
		lblYearFrom.setVisible(true);
		lblYearFrom.setBounds(70, 180, 20, 30);
		
		txtMonthFrom.setText("4");
		txtMonthFrom.setVisible(true);
		txtMonthFrom.setBounds(90, 180, 50, 30);
		txtMonthFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblMonthFrom.setText("月");
		lblMonthFrom.setVisible(true);
		lblMonthFrom.setBounds(140, 180, 20, 30);
		
		txtDayFrom.setText("1");
		txtDayFrom.setVisible(true);
		txtDayFrom.setBounds(160, 180, 50, 30);
		txtDayFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lbldayFrom.setText("日");
		lbldayFrom.setVisible(true);
		lbldayFrom.setBounds(210, 180, 30, 30);
		
		lblDateFrom.setText("から");
		lblDateFrom.setVisible(true);
		lblDateFrom.setBounds(240, 180, 40, 30);
		
		txtYearTo.setText("2099");
		txtYearTo.setVisible(true);
		txtYearTo.setBounds(10, 210, 60, 30);
		txtYearTo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblYearTo.setText("年");
		lblYearTo.setVisible(true);
		lblYearTo.setBounds(70, 210, 20, 30);
		
		txtMonthTo.setText("3");
		txtMonthTo.setVisible(true);
		txtMonthTo.setBounds(90, 210, 50, 30);
		txtMonthTo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblMonthTo.setText("月");
		lblMonthTo.setVisible(true);
		lblMonthTo.setBounds(140, 210, 20, 30);
		
		txtDayTo.setText("31");
		txtDayTo.setVisible(true);
		txtDayTo.setBounds(160, 210, 50, 30);
		txtDayTo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblDayTo.setText("日");
		lblDayTo.setVisible(true);
		lblDayTo.setBounds(210, 210, 30, 30);
		
		lblDateTo.setText("まで");
		lblDateTo.setVisible(true);
		lblDateTo.setBounds(240, 210, 40, 30);
		
		lblAmountRange.setText("金額の範囲");
		lblAmountRange.setVisible(true);
		lblAmountRange.setBounds(10, 260, 390, 30);
		lblAmountRange.setBorder(BorderFactory.createLoweredBevelBorder());
		lblAmountRange.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtAmountRangeFrom.setText("-99999999999999");
		txtAmountRangeFrom.setVisible(true);
		txtAmountRangeFrom.setBounds(10, 290, 150, 30);
		txtAmountRangeFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblAmountFrom.setText("から");
		lblAmountFrom.setVisible(true);
		lblAmountFrom.setBounds(160, 290, 50, 30);
		
		txtAmountrangeTo.setText("99999999999999");
		txtAmountrangeTo.setVisible(true);
		txtAmountrangeTo.setBounds(210, 290, 150, 30);
		txtAmountrangeTo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblAmountTo.setText("まで");
		lblAmountTo.setVisible(true);
		lblAmountTo.setBounds(360, 290, 40, 30);
		
		lblAccountSelect.setText("科目範囲の選択");
		lblAccountSelect.setVisible(true);
		lblAccountSelect.setBounds(10, 320, 390, 30);
		lblAccountSelect.setBorder(BorderFactory.createLoweredBevelBorder());
		lblAccountSelect.setHorizontalAlignment(SwingConstants.CENTER);
		
		cmbAccountSelect1.setVisible(true);
		cmbAccountSelect1.setBounds(10, 350, 200, 30);
		cmbAccountSelect1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		
		cmbAccountSelect2.setVisible(true);
		cmbAccountSelect2.setBounds(210, 350, 200, 30);
		cmbAccountSelect2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		
		cmbAccountSelect3.setVisible(true);
		cmbAccountSelect3.setBounds(410, 350, 200, 30);
		cmbAccountSelect3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		
		lblMatchDescription.setText("摘要に含まれる文字列");
		lblMatchDescription.setVisible(true);
		lblMatchDescription.setBounds(10, 380, 500, 30);
		lblMatchDescription.setBorder(BorderFactory.createLoweredBevelBorder());
		lblMatchDescription.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtMatchDescription.setVisible(true);
		txtMatchDescription.setBounds(10, 410, 502, 30);
		
		lblMatchVendor.setText("取引先名に含まれる文字列");
		lblMatchVendor.setVisible(true);
		lblMatchVendor.setBounds(10, 440, 500, 30);
		lblMatchVendor.setBorder(BorderFactory.createLoweredBevelBorder());
		lblMatchVendor.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtMatchVendor.setVisible(true);
		txtMatchVendor.setBounds(10, 470, 502, 30);
		
		cmdJournalLog.setText("履歴データ検索 1");
		cmdJournalLog.setVisible(true);
		cmdJournalLog.setBounds(320, 20, 180, 40);
		
		cmdJournalSearch.setText("検索開始 2");
		cmdJournalSearch.setVisible(true);
		cmdJournalSearch.setBounds(320, 70, 180, 50);
		cmdJournalSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FURIDEN_KENSAKU2(1);
			}
		});
		
		cmdJournalPrint.setText("伝票印刷 3");
		cmdJournalPrint.setVisible(true);
		cmdJournalPrint.setBounds(320, 120, 180, 50);
		
		cmdExit.setText("閉じる");
		cmdExit.setVisible(true);
		cmdExit.setBounds(320, 170, 180, 50);
		cmdExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});
		
		
		pnlMain.add(lblSelectDepartment);
		pnlMain.add(lblJournalNoRange);
		pnlMain.add(lblJournalNoFrom);
		pnlMain.add(lblJournalNoTo);
		pnlMain.add(lblDateRange);
		pnlMain.add(lblYearFrom);
		pnlMain.add(lblMonthFrom);
		pnlMain.add(lbldayFrom);
		pnlMain.add(lblDateFrom);
		pnlMain.add(lblYearTo);
		pnlMain.add(lblMonthTo);
		pnlMain.add(lblDayTo);
		pnlMain.add(lblDateTo);
		pnlMain.add(lblAmountRange);
		pnlMain.add(lblAmountFrom);
		pnlMain.add(lblAmountTo);
		pnlMain.add(lblAccountSelect);
		pnlMain.add(lblMatchVendor);
		pnlMain.add(lblMatchDescription);
		pnlMain.add(txtJournalFrom);
		pnlMain.add(txtJournalTo);
		pnlMain.add(txtYearFrom);
		pnlMain.add(txtMonthFrom);
		pnlMain.add(txtDayFrom);
		pnlMain.add(txtYearTo);
		pnlMain.add(txtMonthTo);
		pnlMain.add(txtDayTo);
		pnlMain.add(txtAmountRangeFrom);
		pnlMain.add(txtAmountrangeTo);
		pnlMain.add(txtMatchVendor);
		pnlMain.add(txtMatchDescription);
		pnlMain.add(cmbSelectDepartment);
		pnlMain.add(cmbAccountSelect1);
		pnlMain.add(cmbAccountSelect2);
		pnlMain.add(cmbAccountSelect3);
		pnlMain.add(cmdJournalLog);
		pnlMain.add(cmdJournalSearch);
		pnlMain.add(cmdJournalPrint);
		pnlMain.add(cmdExit);
		
		departmentCode();
		accountSelect(cmbAccountSelect1);
		accountSelect(cmbAccountSelect2);
		accountSelect(cmbAccountSelect3);
	}
	
	public void departmentCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord(
						"SELECT devcode FROM devtables");
				while (rs.next()) {
					cmbSelectDepartment.addItem(rs.getString(1));
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
	public void accountSelect(JComboBox cbo) {
		try {
			DBManager db = new DBManager();
			
			try {

				ResultSet rs;
				rs = db.getRecord(
						"SELECT agkey, acname, acsubname FROM devcodeselect");
				String id = "";
				String Iteam = "";
				comboItem item1 = new comboItem(id, Iteam);
				cbo.addItem(item1);
				while (rs.next()) {
					String val = rs.getString(1);
					String acname = rs.getString(2);
					String acsubname = rs.getString(3);
					
					id = val;
					Iteam = val+" | "+acname+" | "+acsubname;
					
					comboItem item2 = new comboItem(id, Iteam);
					
					cbo.addItem(item2);
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
	
	public void FURIDEN_KENSAKU2(int SHORI) {
		
			String StrSQL;
			int dum;
			String SIWAKE_T;
			
			String BUMON = null;
			Long SIWAKE_FROM;
			Long SIWAKE_TO;
			Long NEN_FROM;

			Long TUKI_FROM;
			Long HI_FROM; 
			Long NEN_TO;
			Long TUKI_TO;
			Long HI_TO;
			Long START_YMD;
			Long END_YMD;
			Double KINGAKU_FROM;
			Double KINGAKU_TO;
			String[] ACSEL = new String[6];
			//ReDim ACSEL (6) As String 'elective array
			String TORISAKI;
			String TEKIYO;
			String JOKEN;
			Long SIWAKE_SHORI;
			String AC_JOKEN; 
			
			if(String.valueOf(cmbSelectDepartment.getSelectedItem()).equals("")) {
				BUMON ="01";
			} else {
				BUMON = String.valueOf(cmbSelectDepartment.getSelectedItem());
			}
			if(txtJournalFrom.getText().equals("")) {
				SIWAKE_FROM =0L;
			} else {
				SIWAKE_FROM = Long.valueOf(txtJournalFrom.getText());
			}
			if(txtJournalTo.getText().equals("")) {
				SIWAKE_TO = 999999L;
			} else {
				SIWAKE_TO = Long.valueOf(txtJournalTo.getText());
			}
			if(txtYearFrom.getText().equals("")) {
				NEN_FROM = 1996L;
			} else {
				NEN_FROM = Long.valueOf(txtYearFrom.getText());
			}
			
			if(txtMonthFrom.getText().equals("")) {
				TUKI_FROM = 4L;
			} else {
				TUKI_FROM = Long.valueOf(txtMonthFrom.getText());
			}
			if(txtDayFrom.getText().equals("")) {
				HI_FROM = 1L;
			} else {
				HI_FROM = Long.valueOf(txtDayFrom.getText());
			}
			if(txtYearTo.getText().equals("")) {
				NEN_TO = 2099L;
			} else {
				NEN_TO = Long.valueOf(txtYearTo.getText());
			}
			if(txtMonthTo.getText().equals("")) {
				TUKI_TO = 3L;
			} else {
				TUKI_TO = Long.valueOf(txtMonthTo.getText());
			}
			if(txtDayTo.getText().equals("")) {
				HI_TO = 31L;
			} else {
				HI_TO = Long.valueOf(txtDayTo.getText());
			}
			if(txtAmountRangeFrom.getText().equals("")) {
				KINGAKU_FROM = 0D;
			} else {
				KINGAKU_FROM = Double.valueOf(txtAmountRangeFrom.getText());
			}
			if(txtAmountrangeTo.getText().equals("")) {
				KINGAKU_TO = 0D;
			} else {
				KINGAKU_TO = Double.valueOf(txtAmountrangeTo.getText());
			}
			if(txtMatchDescription.getText().equals("")) {
				TEKIYO = "";
			} else {
				TEKIYO = txtMatchDescription.getText();
			}
			if(txtMatchVendor.getText().equals("")) {
				TORISAKI = "";
			} else {
				TORISAKI = txtMatchVendor.getText();
			}
			
			if(String.valueOf(cmbAccountSelect1.getSelectedItem()).equals("")) {
				ACSEL[0] ="";
			} else {
				ACSEL[0] = String.valueOf(cmbSelectDepartment.getSelectedItem()) + String.valueOf(cmbAccountSelect1.getSelectedItem());
			}
			if(String.valueOf(cmbAccountSelect2.getSelectedItem()).equals("")) {
				ACSEL[1] ="";
			} else {
				ACSEL[1] = String.valueOf(cmbSelectDepartment.getSelectedItem()) + String.valueOf(cmbAccountSelect2.getSelectedItem());
			}
			if(String.valueOf(cmbAccountSelect3.getSelectedItem()).equals("")) {
				ACSEL[2] ="";
			} else {
				ACSEL[2] = String.valueOf(cmbSelectDepartment.getSelectedItem()) + String.valueOf(cmbAccountSelect3.getSelectedItem());
			}
			if(ACSEL[1].equals(String.valueOf(cmbSelectDepartment.getSelectedItem()))) {
				ACSEL[1] = ACSEL[0];
			}
			if(ACSEL[2].equals(String.valueOf(cmbSelectDepartment.getSelectedItem()))) {
				ACSEL[2] = ACSEL[0];
			}
			
			START_YMD = NEN_FROM * 10000 + TUKI_FROM * 100 + HI_FROM;
			END_YMD = NEN_TO * 10000 + TUKI_TO * 100 + HI_TO;
			
			if(SHORI == 1 || SHORI == 2) {
				SIWAKE_T = "journals";
			} else {
				SIWAKE_T = "jewrk_buf";
			}
			try {
				DBManager db = new DBManager();
				try {
					boolean fo = db.doQuery("DROP VIEW IF EXISTS je_search_result_no ");	
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(ACSEL[0].length() <4 && ACSEL[1].length() <4 && ACSEL[2].length() <4) {
				AC_JOKEN = "";
			} else if (ACSEL[1].length() <4 && ACSEL[2].length() <4) {
				AC_JOKEN = "AND (drkey = '" + ACSEL[0] + "' OR crkey = '" + ACSEL[0] + "')";
			} else if (ACSEL[0].length() <4 && ACSEL[2].length() <4) {
				AC_JOKEN = "AND (drkey = '" + ACSEL[1] + "' OR crkey = '" + ACSEL[1] + "')";
			} else if(ACSEL[0].length() <4 && ACSEL[1].length() <4) {
				AC_JOKEN = "AND (drkey = '" + ACSEL[2] + "' OR crkey = '" + ACSEL[2] + "')";
			} else if(ACSEL[0].length() <4) {
				AC_JOKEN = "AND (drkey = '" + ACSEL[1] + "' OR crkey = '" + ACSEL[1] + "' OR drkey = '" + ACSEL[2] + "' OR crkey = ' "+ ACSEL[2] +"') ";
			} else if(ACSEL[1].length() <4) {
				AC_JOKEN = "AND (drkey = '" + ACSEL[0] + "' OR crkey = '" + ACSEL[0] + "' OR drkey = '" + ACSEL[2] + "' OR crkey= ' "+ ACSEL[2] +"') ";
			} else if(ACSEL[2].length() <4) {
				AC_JOKEN = "AND (drkey = '" + ACSEL[0] + "' OR crkey = '" + ACSEL[0] + "' OR drkey = '" + ACSEL[1] + "' OR crkey = ' "+ ACSEL[1] +"') ";
			} else {
				AC_JOKEN = "AND (drkey = '" + ACSEL[0] + "' OR crkey = '" + ACSEL[0] + "' OR drkey = '" + ACSEL[1] + "' OR crkey = ' "+ ACSEL[1] +"' OR drkey = ' "+ ACSEL[2] +"' OR crkey = ' "+ ACSEL[2] +"') ";
			}
			
			if(TEKIYO =="" && TORISAKI == "") {
				StrSQL = "SELECT je_number FROM " + SIWAKE_T + " WHERE s_number >= " + (SIWAKE_FROM) + " AND s_number <= " + SIWAKE_TO + " AND (yyyy * 10000 + mm * 100 + dd) >= " + (START_YMD) + " AND (yyyy * 10000 + mm * 100 + dd) <= " + (END_YMD) +" AND ((dramount >= " + (KINGAKU_FROM) + " AND dramount <= " + (KINGAKU_TO) +") OR (cramount >= "+ (KINGAKU_FROM) +" AND cramount <= "+ (KINGAKU_TO) +")) AND devcode = ' "+ BUMON + " '" + AC_JOKEN + "GROUP BY je_number";
			} else if(TEKIYO != "" && TORISAKI =="") {
				StrSQL = "SELECT je_number FROM " + SIWAKE_T + " WHERE s_number >= " + (SIWAKE_FROM) + " AND s_number <= " + SIWAKE_TO + " AND (yyyy * 10000 + mm * 100 + dd) >= " + (START_YMD) + " AND (yyyy * 10000 + mm * 100 + dd) <= " + (END_YMD) +" AND ((dramount >= " + (KINGAKU_FROM) + " AND dramount <= " + (KINGAKU_TO) +") OR (cramount >= "+ (KINGAKU_FROM) +" AND cramount <= "+ (KINGAKU_TO) +")) AND desname LIKE '%" + TEKIYO + "%' AND devcode ='" + BUMON + "' " + AC_JOKEN + "GROUP BY je_number";
			} else if(TEKIYO =="" && TORISAKI != "") {
				StrSQL = "SELECT je_number FROM " + SIWAKE_T + " WHERE s_number >= " + (SIWAKE_FROM) + " AND s_number <= " + SIWAKE_TO + " AND (yyyy * 10000 + mm * 100 + dd) >= " + (START_YMD) + " AND (yyyy * 10000 + mm * 100 + dd) <= " + (END_YMD) +" AND ((dramount >= " + (KINGAKU_FROM) + " AND dramount <= " + (KINGAKU_TO) +") OR (cramount >= "+ (KINGAKU_FROM) +" AND cramount <= "+ (KINGAKU_TO) +")) AND vendorname LIKE '%"+ TORISAKI +"%' AND devcode ='"+ BUMON +"' "+ AC_JOKEN +" GROUP BY je_number";
			} else {
				StrSQL = "SELECT je_number FROM " + SIWAKE_T + " WHERE s_number >= " + (SIWAKE_FROM) + " AND s_number <= " + SIWAKE_TO + " AND (yyyy * 10000 + mm * 100 + dd) >= " + (START_YMD) + " AND (yyyy * 10000 + mm * 100 + dd) <= " + (END_YMD) +" AND ((dramount >= " + (KINGAKU_FROM) + " AND dramount <= " + (KINGAKU_TO) +") OR (cramount >= "+ (KINGAKU_FROM) +" AND cramount <= "+ (KINGAKU_TO) +")) AND vendorname LIKE '%"+ TORISAKI +"%' AND desname LIKE '%"+ TEKIYO +"%' AND department code ='"+ BUMON +"' "+ AC_JOKEN +" GROUP BY je_number";
			}
			
			//System.out.println(StrSQL);
			try {
				DBManager dbC = new DBManager();
				try{
					StringBuffer strViewSql = new StringBuffer("CREATE VIEW je_search_result_no AS "+ StrSQL); 
											
					boolean fo1= dbC.doQuery(strViewSql.toString());
					
					//System.out.println(strSql);
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					dbC.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				DBManager db = new DBManager();
				try {
					boolean fo = db.doQuery("DROP VIEW IF EXISTS je_search_result");	
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			StrSQL = "SELECT " + SIWAKE_T +".je_number,"+ SIWAKE_T + ".s_number," + SIWAKE_T + ".l_number," + SIWAKE_T + ".devcode," + SIWAKE_T + ".devname, "+ SIWAKE_T +".draccode, "+ SIWAKE_T +".dracname, "+ SIWAKE_T +".dracsubcode, "+ SIWAKE_T +".dracsubname, "+ SIWAKE_T +".craccode, "+ SIWAKE_T +".cracname, "+ SIWAKE_T +".cracsubcode, "+ SIWAKE_T +".cracsubname, "+ SIWAKE_T +".yyyy, "+ SIWAKE_T +".mm, "+ SIWAKE_T + ".dd," + SIWAKE_T + ".dramount," + SIWAKE_T + ".cramount," + SIWAKE_T + ".balance," + SIWAKE_T + ".descode," + SIWAKE_T + ".desname, "+ SIWAKE_T +".transtime, "+ SIWAKE_T +".drctax, "+ SIWAKE_T +".crctax, "+ SIWAKE_T +".vendorcode, "+ SIWAKE_T +".vendorname, "+ SIWAKE_T +".otherdata, "+ SIWAKE_T +".prjcode, "+ SIWAKE_T +".prjname, ctc.name, ctc_1.name as n1";
			StrSQL = StrSQL + " FROM ((je_search_result_no LEFT JOIN " + SIWAKE_T + " ON je_search_result_no.je_number =" + SIWAKE_T + ".je_number) LEFT JOIN ctc ON journals.drctax = ctc.code) LEFT JOIN ctc AS ctc_1 ON journals.crctax = ctc_1.code WHERE ((( "+ SIWAKE_T +".devcode) = '"+ BUMON +"')) ORDER BY "+ SIWAKE_T +".s_number, "+ SIWAKE_T +".l_number; ";
			
			//System.out.println(StrSQL);
			
			try {
				DBManager dbC = new DBManager();
				try{
					StringBuffer strViewSql = new StringBuffer("CREATE VIEW je_search_result AS "+ StrSQL); 
											
					boolean fo1= dbC.doQuery(strViewSql.toString());
					
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					dbC.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(SHORI ==1 || SHORI ==3) {
				boolean f = false;
				try {
					DBManager db = new DBManager();
					try {

						ResultSet rs;
						rs = db.getRecord("SELECT * FROM je_search_result");
						
						if(rs.next()) {
							f= true;
						}
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					} finally {
						db.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(!f){
					//JOptionPane.showMessageDialog(null, "該当伝票がありません");
					JOptionPane.showMessageDialog(null, "該当伝票がありません", "メッセージ",JOptionPane.INFORMATION_MESSAGE);
				} else {
					int FURIDEN_MODE = 2;
	        		FrmJournalEntry nw = FrmJournalEntry.getInstance(FURIDEN_MODE);
	        		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
					nw.setBounds(1, 1, ScreenSize.width-10, ScreenSize.height - 147);
					nw.getContentPane().setBackground(new Color(176,224,230));
					nw.setForeground(Color.black);
					nw.setResizable(false);
					nw.setTitle("会計システム2014年3月期");
					nw.getContentPane().setLayout(null);
					nw.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
					if (nw.isVisible()) {
					} else {
						getDesktopPane().add(nw);
						nw.setVisible(true);
					}

					nw.setIconifiable(true);
					nw.setMaximizable(false);
					nw.setClosable(true);
					dispose();
				}
			} 
			else if(SHORI == 2) 
		    {
				//Call FURI_REP_MAKE
				FURI_REP_MAKE();
			}
	}
	
	/*public void FURI_REP_MAKE() {
		Long MAE_BAN; 	//before the journal number
		Long MAE_DEN; 	//before the slip number
		String TEKIYO;  //for summary store
		String MAE_BUMONCODE;
		String MAE_BUMONNAME;
		Integer MAE_NEN;
		Integer MAE_TUKI; 
		Integer MAE_HI;
		
		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("DROP VIEW IF EXISTS data_delete_transfer_slip");	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			DBManager dbC = new DBManager();
			try{
				StringBuffer strViewSql = new StringBuffer("CREATE VIEW data_delete_transfer_slip AS DELETE transfer_slip.devcode,transfer_slip.devname, transfer_slip.je_number, transfer_slip.s_number, transfer_slip.yyyy, transfer_slip.mm, transfer_slip.dd, transfer_slip.abstract_name FROM transfer_slip"); 
										
				boolean fo1= dbC.doQuery(strViewSql.toString());
				
				//System.out.println(strSql);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				ResultSet rs1;
				rs = db.getRecord("SELECT * FROM je_search_result");
				rs1 = db.getRecord("");
				
				if() {
					
				}
				
				
				If Not IsNull (rst! Summary name) Then
				            TEKIYO = Trim (rst! Summary name) & Chr (13) & Chr (10)
				        End If
				        MAE_BUMONCODE = rst! Department code
				        MAE_BUMONNAME = rst! Department name
				        MAE_NEN = rst! Recorded year
				        MAE_TUKI = rst! Recorded month
				        MAE_HI = rst! Recorded Date
				        MAE_BAN = rst! Journal number
				        MAE_DEN = rst! Slip number
				
				if(rs.next()) {
					
				}
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/
	
	 /*public void bumonSelect(String devcode) {
		try {
			DBManager db = new DBManager();
			DBManager dbC = new DBManager();
			float minAmount = 0;
			//if(!txtMinimumAmount.getText().equals(""))
				//minAmount = Float.parseFloat(txtMinimumAmount.getText());
			try {
				boolean fo = db.doQuery("DROP VIEW IF EXISTS devcodeselect");	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
			
			try{
				StringBuffer strSql = new StringBuffer("CREATE VIEW devcodeselect AS SELECT Mid(actables.keycode,4,10) AS agkey, actables.devcode, actables.accode, actables.acname, actables.acsubcode, actables.acsubname, acparameters.taxcr FROM actables "+
							"LEFT JOIN acparameters ON actables.keycode = acparameters.keycode WHERE actables.devcode ='"+devcode+"'");
				boolean fo1= dbC.doQuery(strSql.toString());
				
				//System.out.println(strSql);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT devcodeselect.agkey, devcodeselect.acname, devcodeselect.acsubname FROM devcodeselect");
				
				Vector  modelo = new Vector();
				modelo.add("");
				
				cmbAccountSelect1.addItem("");
				cmbAccountSelect2.addItem("");
				cmbAccountSelect3.addItem("");
				while (rs.next()) {
					String val = rs.getString(1);
					String accountSelect1 = rs.getString(2);
					String accountSelect2 = rs.getString(3);
					String accountSelect3 = rs.getString(4);
					
					String Iteam = val+" | "+accountSelect1+" | "+accountSelect2 +" | " +accountSelect3;
					cmbAccountSelect1.addItem(Iteam);
					cmbAccountSelect2.addItem(Iteam);
					cmbAccountSelect3.addItem(Iteam);
			         
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	private static FrmDocumentSearch myInstance;
	
	public static FrmDocumentSearch getInstance() {
		myInstance = new FrmDocumentSearch();
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
	
	class comboItem {
		private String id;
		private String desc;

		public comboItem(String id, String desc) {
			this.id = id;
			this.desc = desc;
		}

		public String getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}

		public String toString() {
			return desc;
		}
	}
}
