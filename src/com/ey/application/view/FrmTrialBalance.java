package com.ey.application.view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;

import com.ey.application.controller.DBManager;
import com.ey.application.model.Trial_balance_subtotal_master;

public class FrmTrialBalance extends FrmBase {

	JPanel pnlMain 			= new JPanel();

	JLabel lblDepartment 	= new JLabel();
	JLabel lblOutputmonth 	= new JLabel();
	JLabel lblMonth 		= new JLabel();
	JLabel lblFromDay 		= new JLabel();
	JLabel lblMonth2 		= new JLabel();
	JLabel lblUntilDay 		= new JLabel();

	JComboBox cmbDepartment = new JComboBox();

	JTextField txtMonth 	= new JTextField();
	JTextField txtFromDay 	= new JTextField();
	JTextField txtMonth2 	= new JTextField();
	JTextField txtUntilday 	= new JTextField();

	JButton cmdSubjects 	= new JButton();
	JButton cmdDetails 		= new JButton();
	JButton cmdProjectExpenses = new JButton();
	JButton cmdBudget 		= new JButton();
	JButton cmdPartner 		= new JButton();
	JButton cmdClose 		= new JButton();

	public FrmTrialBalance() {
		try {
			jbInit();
		} catch (Exception e) {
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

		cmbDepartment.setVisible(true);
		cmbDepartment.setBounds(10, 55, 100, 30);

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

		cmdSubjects.setText("科目別");
		cmdSubjects.setVisible(true);
		cmdSubjects.setBounds(10, 220, 120, 50);
		cmdSubjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TB_Q_CONTROL(3);
			}
		});
		
		cmdDetails.setText("細目別");
		cmdDetails.setVisible(true);
		cmdDetails.setBounds(140, 220, 120, 50);
		cmdDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Details_by();
				TB_Q_CONTROL(1);
			}
		});

		cmdProjectExpenses.setText("<html>プロジェク<br />ト別経費</html>");
		cmdProjectExpenses.setVisible(true);
		cmdProjectExpenses.setBounds(10, 275, 120, 50);
		cmdProjectExpenses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				projectExpenses();
			}
		});

		cmdBudget.setText("<html>プロジェク<br />ト別収支</html>");
		cmdBudget.setVisible(true);
		cmdBudget.setBounds(140, 275, 120, 50);
		cmdBudget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				budget();
			}
		});

		cmdPartner.setText("取引先別");
		cmdPartner.setVisible(true);
		cmdPartner.setBounds(10, 330, 120, 50);
		cmdPartner.setHorizontalAlignment(SwingConstants.CENTER);
		cmdPartner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cmd_run_Click();
				TB_Q_CONTROL(2);
			}
		});

		cmdClose.setText("閉じる");
		cmdClose.setVisible(true);
		cmdClose.setBounds(140, 330, 120, 50);
		cmdClose.setHorizontalAlignment(SwingConstants.CENTER);
		cmdClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});

		pnlMain.add(lblDepartment);
		pnlMain.add(lblOutputmonth);
		pnlMain.add(lblMonth);
		pnlMain.add(lblFromDay);
		pnlMain.add(lblMonth2);
		pnlMain.add(lblUntilDay);
		pnlMain.add(cmbDepartment);
		pnlMain.add(txtMonth);
		pnlMain.add(txtFromDay);
		pnlMain.add(txtMonth2);
		pnlMain.add(txtUntilday);
		pnlMain.add(cmdSubjects);
		pnlMain.add(cmdDetails);
		pnlMain.add(cmdProjectExpenses);
		pnlMain.add(cmdBudget);
		pnlMain.add(cmdPartner);
		pnlMain.add(cmdClose);

		departmentCode();

	}

	public void departmentCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT devcode, devname FROM devtables");
				// cmbPrintByDev.addItem("");
				while (rs.next()) {
					String devcode = rs.getString(1);
					String devname = rs.getString(2);

					String Iteam = devcode + " | " + devname;
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

	private static FrmTrialBalance myInstance;

	public static FrmTrialBalance getInstance() {
		myInstance = new FrmTrialBalance();
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
	public void projectExpenses() {
		long YMD_FROM;
		long YMD_TO;

		long TUKI_FROM = Long.parseLong(String.valueOf(txtMonth.getText()));
		long HI_FROM = Long.parseLong(String.valueOf(txtFromDay.getText()));
		long TUKI_TO = Long.parseLong(String.valueOf(txtMonth2.getText()));
		long HI_TO = Long.parseLong(String.valueOf(txtUntilday.getText()));

		YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
		YMD_TO = YM_GET(TUKI_TO, HI_TO);

		try {
			DBManager db = new DBManager();
			try {
				PreparedStatement furiden_clear = db.getPreparStamt("DELETE FROM project_budget");

				furiden_clear.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			DBManager db = new DBManager();
			DBManager dbc = new DBManager();
			try {
				String sql = "INSERT INTO project_budget (prjcode,prjname,aggregatekey,debitamount) SELECT journals.prjcode, journals.prjname, journals.drkey, SUM(journals.dramount) AS dramount FROM journals WHERE journals.draccode >='5000' AND yyyy * 10000 + mm * 100 + dd >="
						+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd  <= " + YMD_TO
						+ " GROUP BY journals.prjcode, journals.prjname, journals.drkey HAVING ((Not (journals.prjcode) Is Null)) ORDER BY journals.prjcode, journals.drkey";
				System.out.println(sql);
				PreparedStatement psInsertProjectBudget = db.getPreparStamt(sql);
				
				String sql2 = "INSERT INTO project_budget (prjcode,prjname,aggregatekey,creditamount) SELECT journals.prjcode, journals.prjname, journals.crkey, SUM(journals.cramount) AS cramount FROM journals WHERE journals.craccode >= '5000' AND yyyy * 10000 + mm * 100 + dd >="
						+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd  <=" + YMD_TO
						+ " GROUP BY journals.prjcode, journals.prjname, journals.crkey HAVING ((Not (journals.prjcode) Is Null)) ORDER BY journals.prjcode, journals.crkey";

				System.out.println(sql2);
				PreparedStatement psInsertProjectBudgetCr = db.getPreparStamt(sql2);

				psInsertProjectBudget.executeUpdate();
				psInsertProjectBudgetCr.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//DoCmd.OpenReport "プロジェクト別収支", acViewPreview
		CallReport("rptProjectBudget","");

	}
	
	
	
	//private void Details_by()
	private void TB_Q_CONTROL(int SHORI)
	{
		//2002/12/08 移行
		//Public Function TB_Q_CONTROL(SHORI=1)
		//残高試算表
		String BUMON; 
		long NEN_FROM;
		long TUKI_FROM;
		long HI_FROM;
		long NEN_TO;
		long TUKI_TO;
		long HI_TO;
		long KESSAN_NEN=0;
		long KESSAN_TUKI=0;
		long YMD_FROM;
		long YMD_TO;
		long KISHU;
		int dum;
		String ZS_AC="";
		String ZS_AC_NM="";
		double ZS_Amt=0d;
		
	    	    
	    //フォームの初期値算定
	        //NULL値のチェック
		if(cmbDepartment.getSelectedItem().equals("")) {
			BUMON = "";
		} else {
			//フォームからデータを取得
		    BUMON = String.valueOf(cmbDepartment.getSelectedItem()).split(" | ")[0];
		}
		if(txtMonth.getText().equals("")) {
			txtMonth.setText("4");
			TUKI_FROM = 4;
		} else {
			//フォームからデータを取得
			TUKI_FROM = Long.parseLong(txtMonth.getText());
		}
		if(txtMonth2.getText().equals("")) {
			txtMonth2.setText("4");
			TUKI_TO = 4;
		} else {
			//フォームからデータを取得
			TUKI_TO = Long.parseLong(txtMonth2.getText());
		}
		if(txtFromDay.getText().equals("")) {
			txtFromDay.setText("1");
			HI_FROM = 1;
		} else {
			//フォームからデータを取得
			HI_FROM = Long.parseLong(txtFromDay.getText());
		}
		if(txtUntilday.getText().equals("")) {
			txtUntilday.setText("31");
			HI_TO = 31;
		} else {
			//フォームからデータを取得
			HI_TO = Long.parseLong(txtUntilday.getText());
		}
	    
		//フォームからデータを取得
	    YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
	    YMD_TO = YM_GET(TUKI_TO, HI_TO);
	    NEN_FROM = YMD_FROM / 10000;
	    NEN_TO = YMD_TO / 10000;
	    
	    try {
			DBManager db = new DBManager();

			try {
				String sql = "SELECT * FROM basedatas";

				ResultSet rs = db.getRecord(sql);
				
				if ((SHORI==1) && (rs.last())) {
					KESSAN_NEN = rs.getLong(2);
					KESSAN_TUKI = rs.getLong(3);
				} else if (((SHORI==3)||(SHORI==2)) && (rs.next())) {
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
	    
	    KISHU = (KESSAN_NEN - 1) * 10000 + (KESSAN_TUKI + 1) * 100 + 1;
	    
	    //初期値設定
	    if(SHORI!=2){
		    ZS_AC = "3000";
		    ZS_AC_NM = "前期繰越正味財産額";
		    //Set rst = dbs.OpenRecordset("SELECT 部門コード, 月度, 繰越正味財産額,指定正味財産期首残高 FROM 報告書データ保持 WHERE (部門コード='" & BUMON & "');", dbOpenDynaset)
		    try {
		    	DBManager db = new DBManager();
	
		    	try {
		    		String sql = "SELECT devcode,monthly,carryoverbalance_of_paymentsbalance,specifiednetassetsopeningbalance FROM reportdataretention WHERE devcode ='"+ BUMON +"'";
	   				ResultSet rs = db.getRecord(sql);
	   				if (rs.next()) {
	   					double carryoverbalance_of_paymentsbalance = rs.getDouble(3);
	   					double specifiednetassetsopeningbalance = rs.getDouble(4);
	   					
	   					ZS_Amt = (carryoverbalance_of_paymentsbalance + specifiednetassetsopeningbalance) * -1;
	   				}
	
	   			} catch (SQLException e) {
	   				e.printStackTrace();
	  			} finally {
	   				db.close();
	   			}
	   		} catch (Exception e) {
	   			e.printStackTrace();
	   		}
	    }
	    
	    //残高試算表クエリーのパラメータセット
	    if((SHORI == 1) || (SHORI==3)){
	    	dum = TB_Q_SET(BUMON, YMD_FROM, YMD_TO, KISHU);
	    } else if(SHORI == 2){
	    	//Call TORI_SIWAKE_T
	    	TORI_SIWAKE_T();
	    	dum = TB_Q_SET3(BUMON, YMD_FROM, YMD_TO, KISHU);
	    }
	    
	    //If OBJ_EXIST("試算表TEMP") = 2 Then
	        //DoCmd.DeleteObject acTable, "試算表TEMP"
	    //End If
	    try {
	    	DBManager db = new DBManager();
			DBManager dbC = new DBManager();
				
			try {
				boolean fo = db.doQuery("DELETE FROM trial_balance_temp");	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
				
			try{
				StringBuffer strSql = new StringBuffer("INSERT INTO trial_balance_temp (keycode,devcode,devname,accode,acname,acsubcode,acsubname,budget,balance_before_provision,debit_amount,credit_amount,balance_amount,subtotal_key,subtotal_content) SELECT DISTINCTROW balance_before_provision.keycode,balance_before_provision.devcode,devtables.devname,balance_before_provision.accode,balance_before_provision.acname,balance_before_provision.acsubcode,balance_before_provision.acsubname,balance_before_provision.budget,balance_before_provision.balance_before_provision,debit_expense_item_totals.debit_amount,credit_expense_item_totals.credit_amount,IFNULL(balance_before_provision,0) + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_amount, '' AS subtotal_key, '' AS subtotal_content FROM devtables RIGHT JOIN balance_before_provision ON devtables.devcode = balance_before_provision.devcode LEFT JOIN debit_expense_item_totals ON balance_before_provision.keycode = debit_expense_item_totals.drkey LEFT JOIN credit_expense_item_totals ON balance_before_provision.keycode = credit_expense_item_totals.crkey");
				
				System.out.println(strSql);
				dbC.doQuery(strSql.toString());
					
				System.out.println(strSql);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    if((SHORI == 1) || (SHORI==3)){
		    //前期繰越正味財産額の作成
		    try {
		    	DBManager db = new DBManager();
				
		    	try{
					StringBuffer strSql = new StringBuffer("INSERT INTO trial_balance_temp (keycode,devcode,devname,accode,acname,budget,balance_before_provision,debit_amount,credit_amount,balance_amount) SELECT CONCAT('" + BUMON + "','" + ZS_AC + "'), '" + BUMON + "', '' , '" + ZS_AC + "', '" + ZS_AC_NM + "', " + ZS_Amt + ", " + ZS_Amt + ", 0, 0, " + ZS_Amt);
					
					boolean fo1= db.doQuery(strSql.toString());
						
					System.out.println(strSql);
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    //小計行の処理
	    List<Trial_balance_subtotal_master> subtotal_master = new ArrayList<Trial_balance_subtotal_master>();
	    try {
			DBManager db = new DBManager();
			try {
				String sql = "SELECT * FROM trial_balance_subtotal_master";
				ResultSet rs = db.getRecord(sql);
				Trial_balance_subtotal_master obj= new Trial_balance_subtotal_master();
				if(rs.next()) {
					obj.setAC_FROM(rs.getString(1));
					obj.setAC_TO(rs.getString(2));
					obj.setNAIYO(rs.getString(3));
					obj.setKEY(rs.getString(4));
					
					subtotal_master.add(obj);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    for(Trial_balance_subtotal_master rst:subtotal_master)
	    {
	    	try {
		    	DBManager db = new DBManager();
				
		    	try{
					StringBuffer strSql = new StringBuffer("UPDATE trial_balance_temp SET trial_balance_temp.subtotal_key = '"+ rst.getKEY()+"', trial_balance_temp.subtotal_content ='"+ rst.getNAIYO() +"' WHERE trial_balance_temp.accode>= '"+ rst.getAC_FROM() +"' AND trial_balance_temp.accode<= '"+ rst.getAC_TO() +"'");
					
					boolean fo1= db.doQuery(strSql.toString());
						
					System.out.println(strSql);
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	    	
	    }
	    //reportparameters
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
	    try {
			DBManager dbc = new DBManager();
			String je_trial_balance_cond = "抽出条件　部門" + BUMON + "" + NEN_FROM + "年" + TUKI_FROM + "月" + HI_FROM + "日から" + NEN_TO + "年" + TUKI_TO + "月" + HI_TO + "日まで";
			try{
				if(fo){
					if((SHORI==1) || (SHORI==3))
					{
						PreparedStatement pstm = dbc.getPreparStamt("UPDATE reportparameters SET trial_balance_cond = '"+je_trial_balance_cond+"'");
						pstm.executeUpdate();
					}
				}else {
					if(SHORI==1){
						PreparedStatement pstm = dbc.getPreparStamt("INSERT INTO reportparameters(trial_balance_cond) VALUES(?)");
					
						pstm.setString(1, je_trial_balance_cond);
						pstm.executeUpdate();
					}
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String param="";
    	try {
			DBManager db = new DBManager();
			
			try {
				String sql = "SELECT DISTINCTROW reportparameters.trial_balance_cond FROM reportparameters";
				
				ResultSet rs = db.getRecord(sql);

				if(rs.next()) {
					param=rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	    if(SHORI==1){
	        if(BUMON.equals("999")){
	            //DoCmd.OpenReport "残高試算表", acPreview	
	        	CallReport("rptTrialBalance",param);
	        } else {
	            //DoCmd.OpenReport "残高試算表全部門", acPreview
	        	CallReport("rptTrialBalanceAllDepartments",param);
	        }
	    } else if(SHORI==2){
	    	//DoCmd.OpenReport "取引先別残高試算表", acPreview
	    	CallReport("CustomerTrialBalance",param);
	    } else if(SHORI==3){
	        //DoCmd.OpenReport "残高試算表科目別", acPreview 	
	    	CallReport("trialbalancesubjectsby",param);
	    }
	
	}
	    
	public void TORI_SIWAKE_T(){
		//取引先別仕訳テーブル作成	    
	    //DoCmd.OpenQuery "取引先別仕訳テーブルクリア"
		try {
			DBManager db = new DBManager();
			try {
				String sql = "DELETE trading_destination_journal.* FROM trading_destination_journal";
				boolean fo = db.doQuery(sql);				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    //DoCmd.OpenQuery "取引先別仕訳テーブル追加"
		try {
			DBManager db = new DBManager();
			try {
				String sql = "INSERT INTO trading_destination_journal (je_number, l_number, drkey, crkey, devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname, cracsubcode, cracsubname, yyyy, mm, dd, dramount,"
						+ "cramount, balance, descode, desname, memo, transtime, sundry, drctax, crctax, drctaxamount, crctaxamount, vendorcode)"
						+ "SELECT DISTINCTROW journals.je_number, journals.l_number, COALESCE(journals.drkey,'') + COALESCE(journals.vendorcode,'') AS drkey, COALESCE(journals.crkey,'') + COALESCE(journals.vendorcode,'') AS crkey,"
						+ "journals.devcode, journals.devname, journals.draccode, journals.dracname, journals.dracsubcode, journals.dracsubname, journals.craccode, journals.cracname, journals.cracsubcode,"
						+ "journals.cracsubname, journals.yyyy, journals.mm, journals.dd, journals.dramount, journals.cramount, journals.balance, journals.descode, journals.desname, journals.memo,"
						+ "journals.transtime, journals.sundry, journals.drctax, journals.crctax, journals.drctaxamount, journals.crctaxamount, journals.vendorcode "
						+ "FROM journals "
						+ "WHERE COALESCE(journals.vendorcode,'')> '' AND journals.vendorcode <> 'null'"
								+ "ORDER BY journals.je_number, journals.l_number;";
				boolean fo = db.doQuery(sql);				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
	
	
	public void budget() {
		boolean fo = true;
		long YMD_FROM;
		long YMD_TO;

		long TUKI_FROM = Long.parseLong(String.valueOf(txtMonth.getText()));
		long HI_FROM = Long.parseLong(String.valueOf(txtFromDay.getText()));
		long TUKI_TO = Long.parseLong(String.valueOf(txtMonth2.getText()));
		long HI_TO = Long.parseLong(String.valueOf(txtUntilday.getText()));

		YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
		YMD_TO = YM_GET(TUKI_TO, HI_TO);

		try {
			DBManager db = new DBManager();
			try {
				PreparedStatement furiden_clear = db.getPreparStamt("DELETE FROM project_budget");

				furiden_clear.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			DBManager db = new DBManager();
			DBManager dbc = new DBManager();
			try {
				PreparedStatement psInsertProjectBudget = db.getPreparStamt(
						"INSERT INTO project_budget (prjcode,prjname,aggregatekey,debitamount) SELECT journals.prjcode, journals.prjname, journals.drkey, SUM(journals.dramount) AS dramount FROM journals WHERE journals.draccode >= '4000' AND yyyy * 10000 + mm * 100 + dd >="
								+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd  <= " + YMD_TO
								+ " GROUP BY journals.prjcode, journals.prjname, journals.drkey HAVING ((Not (journals.prjcode) Is Null)) ORDER BY journals.prjcode, journals.drkey");

				PreparedStatement psInsertProjectBudgetCr = db.getPreparStamt(
						"INSERT INTO project_budget (prjcode,prjname,aggregatekey,creditamount) SELECT journals.prjcode, journals.prjname, journals.crkey, SUM(journals.cramount) AS cramount FROM journals WHERE journals.craccode >= '4000' AND yyyy * 10000 + mm * 100 + dd >="
								+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd  <=" + YMD_TO
								+ " GROUP BY journals.prjcode, journals.prjname, journals.crkey HAVING ((Not (journals.prjcode) Is Null)) ORDER BY journals.prjcode, journals.crkey");

				psInsertProjectBudget.executeUpdate();
				psInsertProjectBudgetCr.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//DoCmd.OpenReport "プロジェクト別収支2", acViewPreview
		CallReport("rptProjectBudget2","");
		
		if(fo)
			return;
		
		StringBuffer sql;
		sql = new StringBuffer("INSERT INTO tb_work (aggregate_key, debit_amount, subtotal_key) SELECT journals.drkey, SUM(journals.dramount) AS debit_amount_sum, IF(LEFT(draccode,1) = '7',prjcode, NULL) AS formula1 FROM journals GROUP BY journals.drkey, IF(LEFT(draccode,1) ='7',prjcode,NULL);");
		sql = new StringBuffer("INSERT INTO tb_work (aggregate_key, credit_amount, subtotal_key) SELECT journals.crkey, SUM(journals.cramount) AS credit_amount_sum, IF(LEFT(craccode,1) = '7',prjcode, NULL) AS formula1 FROM journals GROUP BY journals.crkey, IF(LEFT(craccode,1) ='7',prjcode,NULL);");
		sql = new StringBuffer("UPDATE tb_work SET tb_work.subtotal_name = IF(subtotal_key < '200', '4001', IF(subtotal_key >= '200' AND subtotal_key < '300','4002', IF(subtotal_key>='300' AND subtotal_key <'400','4003', IF(subtotal_key = '410','4004', IF(subtotal_key = '510','4005','4006'))))) WHERE tb_work.subtotal_key<>NULL;");
		sql = new StringBuffer("UPDATE tb_work SET tb_work.subtotal_name = IF(MID(aggregate_key,4,1)<='6','1000',IF(MID(aggregate_key,4,1)='7','4006','6000')) WHERE tb_work.subtotal_key IS NULL;");
		sql = new StringBuffer("UPDATE tb_work SET tb_work.subtotal_name = '6002' WHERE (LEFT (subtotal_name,1) =6 AND ( NOT MID(aggregate_key,4,4) ='8110' OR MID(aggregate_key,4,4)='8115'));");
		sql = new StringBuffer("UPDATE tb_work SET tb_work.subtotal_name = IF(MID(aggregate_key,4,1)<='6','1000',IF(MID(aggregate_key,4,1)='7','4006','6000')) WHERE (tb_work.subtotal_key IS NULL AND MID(aggregate_key,4,4) ='8390');");
		QueryBudget(sql);
		
		//DoCmd.OpenReport "PRJ試算表", acViewPreview
		CallReport("rptProjectTrialBalance","");
		
	}
	
	public void QueryBudget(StringBuffer sql) {
		try {
			DBManager db = new DBManager();
			
			try{
				boolean fo1= db.doQuery(sql.toString());
			
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
