package com.ey.application.view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.ey.application.model.Ledger;

public class FrmCreatingLedger extends FrmBase {

	JPanel pnlMain = new JPanel();

	JLabel lblDepartment = new JLabel();
	JLabel lblRearrangement = new JLabel();
	JLabel lblPrintCondition = new JLabel();
	JLabel lblCourseRange = new JLabel();
	JLabel lblCourseFrom = new JLabel();
	JLabel lblCourseTo = new JLabel();
	JLabel lblProject = new JLabel();
	JLabel lblndividualSubjects = new JLabel();
	JLabel lblUntilDetails = new JLabel();
	JLabel lblOutputMonth = new JLabel();
	JLabel lblMonth = new JLabel();
	JLabel lblFromday = new JLabel();
	JLabel lblMonth2 = new JLabel();
	JLabel lblUntilDay = new JLabel();
	JComboBox cmbBumon = new JComboBox();
	JComboBox cmbNarabi = new JComboBox();
	JComboBox cmbPrjoken = new JComboBox();
	JComboBox cmbAcFrom = new JComboBox();
	JComboBox cmbAcTo = new JComboBox();
	JComboBox cmbPrjCode = new JComboBox();
	JComboBox cmbAcSel1 = new JComboBox();
	JComboBox cmbAcSel2 = new JComboBox();
	JComboBox cmbAcSel3 = new JComboBox();
	JComboBox cmbAcSel4 = new JComboBox();
	JComboBox cmbAcSel5 = new JComboBox();
	JComboBox cmbAcSel6 = new JComboBox();
	JComboBox cmbSaiSel = new JComboBox();

	JTextField txtTukiFrom = new JTextField();
	JTextField txtHiFrom = new JTextField();
	JTextField txtTukiTo = new JTextField();
	JTextField txtHiTo = new JTextField();

	JButton cmdOriginalBook = new JButton();
	JButton cmdCashBook = new JButton();
	JButton cmdSubsidiaryLedger = new JButton();
	JButton cmdCashierAppendix = new JButton();
	JButton cmdClose = new JButton();

	public FrmCreatingLedger() {
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
		lblDepartment.setBounds(10, 10, 160, 25);
		lblDepartment.setBorder(BorderFactory.createLoweredBevelBorder());
		lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);

		lblRearrangement.setText("並替選択");
		lblRearrangement.setVisible(true);
		lblRearrangement.setBounds(170, 10, 160, 25);
		lblRearrangement.setBorder(BorderFactory.createLoweredBevelBorder());
		lblRearrangement.setHorizontalAlignment(SwingConstants.CENTER);

		lblPrintCondition.setText("印刷頁条件");
		lblPrintCondition.setVisible(true);
		lblPrintCondition.setBounds(330, 10, 160, 25);
		lblPrintCondition.setBorder(BorderFactory.createLoweredBevelBorder());
		lblPrintCondition.setHorizontalAlignment(SwingConstants.CENTER);

		cmbBumon.setVisible(true);
		cmbBumon.setBounds(10, 37, 160, 30);

		cmbNarabi.setVisible(true);
		cmbNarabi.setBounds(170, 37, 160, 30);
		cmbNarabi.addItem("1" + " | " + "入力順");
		cmbNarabi.addItem("2" + " | " + "日付順");

		cmbPrjoken.setVisible(true);
		cmbPrjoken.setBounds(330, 37, 160, 30);
		cmbPrjoken.addItem("1" + " | " + "明細がないページを印刷しない");
		cmbPrjoken.addItem("2" + " | " + "明細がないページも印刷する");

		lblCourseRange.setText("科目範囲の選択");
		lblCourseRange.setVisible(true);
		lblCourseRange.setBounds(10, 75, 420, 25);
		lblCourseRange.setBorder(BorderFactory.createLoweredBevelBorder());
		lblCourseRange.setHorizontalAlignment(SwingConstants.CENTER);

		cmbAcFrom.setVisible(true);
		cmbAcFrom.setBounds(10, 100, 160, 30);

		lblCourseFrom.setText("から");
		lblCourseFrom.setVisible(true);
		lblCourseFrom.setBounds(170, 100, 45, 30);
		lblCourseFrom.setBorder(BorderFactory.createLoweredBevelBorder());
		lblCourseFrom.setHorizontalAlignment(SwingConstants.CENTER);

		cmbAcTo.setVisible(true);
		cmbAcTo.setBounds(225, 100, 160, 30);

		lblCourseTo.setText("まで");
		lblCourseTo.setVisible(true);
		lblCourseTo.setBounds(385, 100, 45, 30);
		lblCourseTo.setBorder(BorderFactory.createLoweredBevelBorder());
		lblCourseTo.setHorizontalAlignment(SwingConstants.CENTER);

		lblProject.setText("プロジェクト");
		lblProject.setVisible(true);
		lblProject.setBounds(10, 140, 160, 30);
		lblProject.setBorder(BorderFactory.createLoweredBevelBorder());
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);

		cmbPrjCode.setVisible(true);
		cmbPrjCode.setBounds(170, 140, 260, 30);

		lblndividualSubjects.setText("科目の個別選択");
		lblndividualSubjects.setVisible(true);
		lblndividualSubjects.setBounds(10, 180, 480, 25);
		lblndividualSubjects.setBorder(BorderFactory.createLoweredBevelBorder());
		lblndividualSubjects.setHorizontalAlignment(SwingConstants.CENTER);

		cmbAcSel1.setVisible(true);
		cmbAcSel1.setBounds(10, 205, 160, 30);

		cmbAcSel2.setVisible(true);
		cmbAcSel2.setBounds(170, 205, 160, 30);

		cmbAcSel3.setVisible(true);
		cmbAcSel3.setBounds(330, 205, 160, 30);

		cmbAcSel4.setVisible(true);
		cmbAcSel4.setBounds(10, 235, 160, 30);

		cmbAcSel5.setVisible(true);
		cmbAcSel5.setBounds(170, 235, 160, 30);

		cmbAcSel6.setVisible(true);
		cmbAcSel6.setBounds(330, 235, 160, 30);

		lblUntilDetails.setText("細目まで");
		lblUntilDetails.setVisible(true);
		lblUntilDetails.setBounds(90, 265, 80, 30);
		lblUntilDetails.setBorder(BorderFactory.createLoweredBevelBorder());
		lblUntilDetails.setHorizontalAlignment(SwingConstants.CENTER);

		cmbSaiSel.setVisible(true);
		cmbSaiSel.setBounds(170, 265, 320, 30);

		lblOutputMonth.setText("出力月日の選択");
		lblOutputMonth.setVisible(true);
		lblOutputMonth.setBounds(10, 305, 480, 25);
		lblOutputMonth.setBorder(BorderFactory.createLoweredBevelBorder());
		lblOutputMonth.setHorizontalAlignment(SwingConstants.CENTER);

		txtTukiFrom.setVisible(true);
		txtTukiFrom.setBounds(10, 332, 75, 25);
		txtTukiFrom.setBorder(BorderFactory.createLoweredBevelBorder());
		txtTukiFrom.setText("4");

		lblMonth.setText("月");
		lblMonth.setVisible(true);
		lblMonth.setBounds(90, 332, 155, 25);
		lblMonth.setBorder(BorderFactory.createLoweredBevelBorder());
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);

		txtHiFrom.setVisible(true);
		txtHiFrom.setBounds(255, 332, 75, 25);
		txtHiFrom.setBorder(BorderFactory.createLoweredBevelBorder());
		txtHiFrom.setText("1");

		lblFromday.setText("日から");
		lblFromday.setVisible(true);
		lblFromday.setBounds(335, 332, 155, 25);
		lblFromday.setBorder(BorderFactory.createLoweredBevelBorder());
		lblFromday.setHorizontalAlignment(SwingConstants.CENTER);

		txtTukiTo.setVisible(true);
		txtTukiTo.setBounds(10, 360, 75, 25);
		txtTukiTo.setBorder(BorderFactory.createLoweredBevelBorder());
		txtTukiTo.setText("3");

		lblMonth2.setText("月");
		lblMonth2.setVisible(true);
		lblMonth2.setBounds(90, 360, 155, 25);
		lblMonth2.setBorder(BorderFactory.createLoweredBevelBorder());
		lblMonth2.setHorizontalAlignment(SwingConstants.CENTER);

		txtHiTo.setVisible(true);
		txtHiTo.setBounds(255, 360, 75, 25);
		txtHiTo.setBorder(BorderFactory.createLoweredBevelBorder());
		txtHiTo.setText("31");

		lblUntilDay.setText("日まで");
		lblUntilDay.setVisible(true);
		lblUntilDay.setBounds(335, 360, 155, 25);
		lblUntilDay.setBorder(BorderFactory.createLoweredBevelBorder());
		lblUntilDay.setHorizontalAlignment(SwingConstants.CENTER);

		cmdOriginalBook.setText("元　　帳  1");
		cmdOriginalBook.setVisible(true);
		cmdOriginalBook.setBounds(10, 395, 160, 50);
		cmdOriginalBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GL_Q_CON2();
			}
		});
		
		cmdCashBook.setText("現金出納簿 2");
		cmdCashBook.setVisible(true);
		cmdCashBook.setBounds(170, 395, 160, 50);
		cmdCashBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GENKIN_SUITO();
			}
		});

		
		cmdSubsidiaryLedger.setText("補助元帳 3");
		cmdSubsidiaryLedger.setVisible(true);
		cmdSubsidiaryLedger.setBounds(10, 450, 160, 50);
		cmdSubsidiaryLedger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					GL_Q_CONTROL();
			}
		});

		cmdCashierAppendix.setText("現金出納付表 4");
		cmdCashierAppendix.setVisible(true);
		cmdCashierAppendix.setBounds(170, 450, 160, 50);
		cmdCashierAppendix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GENKIN_CHECK();
			}
		});
		
		cmdClose.setText("閉じる");
		cmdClose.setVisible(true);
		cmdClose.setBounds(330, 450, 160, 50);
		cmdClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});

		pnlMain.add(lblDepartment);
		pnlMain.add(lblRearrangement);
		pnlMain.add(lblPrintCondition);
		pnlMain.add(lblCourseRange);
		pnlMain.add(lblCourseFrom);
		pnlMain.add(lblCourseTo);
		pnlMain.add(lblProject);
		pnlMain.add(lblndividualSubjects);
		pnlMain.add(lblUntilDetails);
		pnlMain.add(lblOutputMonth);
		pnlMain.add(lblMonth);
		pnlMain.add(lblFromday);
		pnlMain.add(lblMonth2);
		pnlMain.add(lblUntilDay);
		pnlMain.add(cmbBumon);
		pnlMain.add(cmbNarabi);
		pnlMain.add(cmbPrjoken);
		pnlMain.add(cmbAcFrom);
		pnlMain.add(cmbAcTo);
		pnlMain.add(cmbPrjCode);
		pnlMain.add(cmbAcSel1);
		pnlMain.add(cmbAcSel2);
		pnlMain.add(cmbAcSel3);
		pnlMain.add(cmbAcSel4);
		pnlMain.add(cmbAcSel5);
		pnlMain.add(cmbAcSel6);
		pnlMain.add(cmbSaiSel);
		pnlMain.add(txtTukiFrom);
		pnlMain.add(txtHiFrom);
		pnlMain.add(txtTukiTo);
		pnlMain.add(txtHiTo);
		pnlMain.add(cmdOriginalBook);
		pnlMain.add(cmdCashBook);
		pnlMain.add(cmdSubsidiaryLedger);
		pnlMain.add(cmdCashierAppendix);
		pnlMain.add(cmdClose);

		departmentCode();
		acDetails(cmbAcFrom);
		acDetails(cmbAcTo);
		prjCode(cmbPrjCode);
		subjects(cmbAcSel1);
		subjects(cmbAcSel2);
		subjects(cmbAcSel3);
		subjects(cmbAcSel4);
		subjects(cmbAcSel5);
		subjects(cmbAcSel6);
		subjectCodeSelect(cmbSaiSel);
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
					cmbBumon.addItem(Iteam);
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

	public void acDetails(JComboBox ac) {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord(
						"SELECT actablesaggregation.accode,actablesaggregation.acname FROM actablesaggregation");
				// cmbPrintByDev.addItem("");
				while (rs.next()) {
					String devcode = rs.getString(1);
					String devname = rs.getString(2);

					String Iteam = devcode + " | " + devname;
					ac.addItem(Iteam);
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

	public void prjCode(JComboBox pc) {
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rs;
				rs = db.getRecord("SELECT project_code.prjcode,project_code.prjname FROM project_code");
				cmbPrjCode.addItem("");
				while (rs.next()) {
					String prjcode = rs.getString(1);
					String prjname = rs.getString(2);

					String Item = prjcode + " | " + prjname;
					pc.addItem(Item);
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

	public void subjects(JComboBox sub) {
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rs;
				rs = db.getRecord(
						"SELECT actablesaggregation.accode,actablesaggregation.acname FROM actablesaggregation");
				sub.addItem("");

				while (rs.next()) {
					String accode = rs.getString(1);
					String acname = rs.getString(2);

					String Item = accode + " | " + acname;
					sub.addItem(Item);
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

	public void subjectCodeSelect(JComboBox subcode) {
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rs;
				rs = db.getRecord(
						"SELECT devcodeselect.agkey,devcodeselect.accode,devcodeselect.acname, devcodeselect.acsubcode,devcodeselect.acsubname FROM devcodeselect");
				cmbSaiSel.addItem("");
				while (rs.next()) {
					String agkey = rs.getString(1);
					String accode = rs.getString(2);
					String acname = rs.getString(3);
					String acsubcode = rs.getString(4);
					String acsubname = rs.getString(5);

					String Item = agkey + " | " + accode + " | " + acname + " | " + acsubcode + " | " + acsubname;
					subcode.addItem(Item);
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
	
	public void GL_Q_CON2(){
		//総勘定元帳
	    int NARABI=0;
	    long NEN_FROM=0;  //開始年
	    long NEN_TO=0;    //終了年
	    long TUKI_FROM=0;  //開始月
	    long TUKI_TO=0;    //終了月
	    long HI_FROM=0;  //開始日
	    long HI_TO=0;    //終了日
	    long YMD_FROM=0;
	    long YMD_TO=0;
	    long KESSAN_NEN=0; //決算年
	    long KESSAN_TUKI;  //決算月
	    String AC_FROM;   //開始科目
	    String AC_TO;   //終了科目
	    String ACSEL[] = new String[6];   //選択科目配列
	    String ACSEL_2[] = new String[6];   //選択科目配列
	    String BUMON; //部門
	    long KISHU=0;
	    String SHUKEI_KEY;
	    String JOKEN="";
	    String KAMOKU_JOKEN;
	    double ZANDAKA_TRAN=0;
	    String SQL_TEMP;
	    int dum=0;
	    long GYOHAN=0;
	    
	    //フォームの初期値算定
	    //NULL値のチェック
	    if (cmbBumon.getSelectedItem().equals("")) {
			BUMON = "";
		} else {
			BUMON = String.valueOf(cmbBumon.getSelectedItem()).split(" | ")[0];
		}
	    if (cmbNarabi.getSelectedItem().equals("")) {
	    	cmbNarabi.setSelectedItem("1" + " | " + "入力順");
	    }
	    if (cmbAcFrom.getSelectedItem().equals("")) {
	    	cmbAcFrom.setSelectedItem("0000");
	    }
	    if (cmbAcTo.getSelectedItem().equals("")) {
	    	cmbAcTo.setSelectedItem("9999");
	    }
	    if (cmbAcSel1.getSelectedItem().equals("")) {
	    	cmbAcSel1.setSelectedItem("");
	    }
	    if (cmbAcSel2.getSelectedItem().equals("")) {
	    	cmbAcSel2.setSelectedItem("");
	    }
	    if (cmbAcSel3.getSelectedItem().equals("")) {
	    	cmbAcSel3.setSelectedItem("");
	    }
	    if (cmbAcSel4.getSelectedItem().equals("")) {
	    	cmbAcSel4.setSelectedItem("");
	    }
	    if (cmbAcSel5.getSelectedItem().equals("")) {
	    	cmbAcSel5.setSelectedItem("");
	    }
	    if (cmbAcSel6.getSelectedItem().equals("")) {
	    	cmbAcSel6.setSelectedItem("");
	    }
	    if (txtTukiFrom.getText().equals("")) {
	    	txtTukiFrom.setText("4");
	    }
	    if (txtTukiTo.getText().equals("")) {
	    	txtTukiTo.setText("4");
	    }
	    if (txtHiFrom.getText().equals("")) {
	    	txtHiFrom.setText("1");
	    }
	    if (txtHiTo.getText().equals("")) {
	    	txtHiTo.setText("31");
	    }
	    //フォームからデータを取得
	    //BUMON = Forms!元帳作成!BUMON
	    NARABI = Integer.parseInt(String.valueOf(cmbNarabi.getSelectedItem()).split(" | ")[0]);
	    AC_FROM = String.valueOf(cmbAcFrom.getSelectedItem()).split(" | ")[0];
	    AC_TO = String.valueOf(cmbAcTo.getSelectedItem()).split(" | ")[0];
	    ACSEL[0] = String.valueOf(cmbAcSel1.getSelectedItem()).split(" | ")[0];
	    ACSEL[1] = String.valueOf(cmbAcSel2.getSelectedItem()).split(" | ")[0];
	    ACSEL[2] = String.valueOf(cmbAcSel3.getSelectedItem()).split(" | ")[0];
	    ACSEL[3] = String.valueOf(cmbAcSel4.getSelectedItem()).split(" | ")[0];
	    ACSEL[4] = String.valueOf(cmbAcSel5.getSelectedItem()).split(" | ")[0];
	    ACSEL[5] = String.valueOf(cmbAcSel6.getSelectedItem()).split(" | ")[0];
	    
	    if(ACSEL[0].equals(" ")){
	        ACSEL_2[0] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[0] = ACSEL[0];
	    }
	    if(ACSEL[1].equals(" ")){ 
	        ACSEL_2[1] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[1] = ACSEL[1];
		}
	    if(ACSEL[2].equals(" ")){
	        ACSEL_2[2] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[2] = ACSEL[2];
	    }
	    if(ACSEL[3].equals(" ")){
	    	ACSEL_2[3] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[3] = ACSEL[3];
	    }
	    if(ACSEL[4].equals(" ")){
	    	ACSEL_2[4] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[4] = ACSEL[4];
	    }
	    if(ACSEL[5].equals(" ")){
	    	ACSEL_2[5] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[5] = ACSEL[5];
	    }
	    	    
	    TUKI_FROM = Integer.parseInt(txtTukiFrom.getText());
	    TUKI_TO = Integer.parseInt(txtTukiTo.getText());
	    HI_FROM = Integer.parseInt(txtHiFrom.getText());
	    HI_TO = Integer.parseInt(txtHiTo.getText());
	    YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
	    YMD_TO = YM_GET(TUKI_TO, HI_TO);
	    NEN_FROM = (int)(YMD_FROM / 10000);
	    NEN_TO = (int)(YMD_TO / 10000);
	    
	    //DoCmd.SetWarnings False
	    //Set dbs = CurrentDb()
	    
	    //残高試算表クエリーのパラメータセット
	    dum = TB_Q_SET2(BUMON, YMD_FROM, YMD_TO, KISHU);
	    //DoCmd.OpenQuery "部門科目別残高試算表作成" 
	    StringBuffer sql = new StringBuffer("INSERT INTO departments_subject_trial_balance SELECT DISTINCTROW before_provision_balance_aggregation.aggregate_key,MAX(before_provision_balance_aggregation.department_code) AS department_code, MAX(devtables.devname) AS devname,MAX(before_provision_balance_aggregation.item_code) AS item_code, MAX(before_provision_balance_aggregation.item_name) AS item_name,SUM(before_provision_balance_aggregation.budget) AS budget, SUM(before_provision_balance_aggregation.balance_before_provision) AS balance_before_provision,SUM(debit_department_subjects_totals.debit_amount) AS debit_amount, SUM(credit_department_subjects_totals.credit_amount) AS credit_amount,balance_before_provision + IFNULL(debit_amount,0)-IFNULL(credit_amount,0) AS balance_amount FROM before_provision_balance_aggregation LEFT JOIN debit_department_subjects_totals ON before_provision_balance_aggregation.aggregate_key = debit_department_subjects_totals.debit_key LEFT JOIN credit_department_subjects_totals ON before_provision_balance_aggregation.aggregate_key = credit_department_subjects_totals.crkey LEFT JOIN devtables ON before_provision_balance_aggregation.department_code = devtables.devcode GROUP BY before_provision_balance_aggregation.aggregate_key");
	    TableDefs(sql,"departments_subject_trial_balance");
	    
	    //元帳トランのデータをクリア
	    //DoCmd.OpenQuery "元帳トランクリア" 
	    TableClear("ledger_tran");
	    
	    //選択した勘定科目のデータを元帳トランに追加する。
	    //前繰残高を元帳トランに追加
	    /*Set qdf = dbs.QueryDefs("元帳前残追加集約")
	    If ACSEL(1) = " " And ACSEL(2) = " " And ACSEL(3) = " " And ACSEL(4) = " " And ACSEL(5) = " " And ACSEL(6) = " " Then
	        qdf.SQL = "INSERT INTO 元帳トラン ( 集計キー, 部門コード, 部門名, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 残高金額, 計上年, 計上月, 計上日, 仕訳番号, 行番 ) SELECT DISTINCTROW 部門科目別残高試算表.集計キー, 部門科目別残高試算表.部門コード, 部門科目別残高試算表.部門名, ' ' AS 式1, '前繰残高' AS 式2, ' ' AS 式3, ' ' AS 式4, 部門科目別残高試算表.前繰残高, 0 AS 計上年, 0 AS 計上月, 0 AS 計上日, 0 AS 仕訳番号, 0 AS 行番 FROM 部門科目別残高試算表 WHERE (((部門科目別残高試算表.集計キー)>='" & BUMON & AC_FROM & "' And (部門科目別残高試算表.集計キー)<='" & BUMON & AC_TO & "999999" & "'));"
	    Else
	        KAMOKU_JOKEN = "(部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(1) & "') OR (部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(2) & "') OR (部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(3) & "') OR (部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(4) & "') OR (部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(5) & "') OR (部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(6) & "')"
	        qdf.SQL = "INSERT INTO 元帳トラン ( 集計キー, 部門コード, 部門名, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 残高金額, 計上年, 計上月, 計上日, 仕訳番号, 行番 ) SELECT DISTINCTROW 部門科目別残高試算表.集計キー, 部門科目別残高試算表.部門コード, 部門科目別残高試算表.部門名, ' ', '前繰残高', ' ', ' ', 部門科目別残高試算表.前繰残高, 0 AS 計上年, 0 AS 計上月, 0 AS 計上日, 0 AS 仕訳番号, 0 AS 行番 FROM 部門科目別残高試算表 WHERE ((" & KAMOKU_JOKEN & "));"
	    End If
	    qdf.Close
	    DoCmd.OpenQuery "元帳前残追加集約"*/
	    	    
	    if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname,'' AS formula1, '前繰残高' AS formula2, '' AS formula3,'' AS formula4,departments_subject_trial_balance.bpbalance, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM departments_subject_trial_balance WHERE (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + AC_FROM +"' AND departments_subject_trial_balance.aggregate_key <='"+ BUMON + AC_TO +"999999"+"')");
	    } else {
	    	//KAMOKU_JOKEN = "(departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[0] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[1] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[2] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[3] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[4] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[5] +"')";
	    	KAMOKU_JOKEN ="";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[0] +"')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[1] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[1] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[2] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[2] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[3] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[3] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[4] +"')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[4] +"')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[5] +"')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[5] +"')";
	    		}
	    	}
	    	
	    	QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname,'', '前繰残高', '','',departments_subject_trial_balance.bpbalance, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM departments_subject_trial_balance WHERE ("+ KAMOKU_JOKEN +")");
	    }
	    	    
	    
	    //残高金額を元帳トランに追加
	    /*Set qdf = dbs.QueryDefs("元帳残高追加集約")
	    If ACSEL(1) = " " And ACSEL(2) = " " And ACSEL(3) = " " And ACSEL(4) = " " And ACSEL(5) = " " And ACSEL(6) = " " Then
	        qdf.SQL = "INSERT INTO 元帳トラン ( 集計キー, 部門コード, 部門名, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 残高金額, 計上年, 計上月, 計上日, 仕訳番号, 行番 ) SELECT DISTINCTROW 部門科目別残高試算表.集計キー, 部門科目別残高試算表.部門コード, 部門科目別残高試算表.部門名, ' ', '残　　高', ' ', ' ', 部門科目別残高試算表.残高金額, 9999 AS 計上年, 99 AS 計上月, 99 AS 計上日, 999999 AS 仕訳番号, 99 AS 行番 FROM 部門科目別残高試算表 WHERE (((部門科目別残高試算表.集計キー)>='" & BUMON & AC_FROM & "' And (部門科目別残高試算表.集計キー)<='" & BUMON & AC_TO & "999999" & "'));"
	    Else
	        KAMOKU_JOKEN = "(部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(1) & "') OR (部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(2) & "') OR (部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(3) & "') OR (部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(4) & "') OR (部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(5) & "') OR (部門科目別残高試算表.集計キー = '" & BUMON & ACSEL_2(6) & "')"
	        qdf.SQL = "INSERT INTO 元帳トラン ( 集計キー, 部門コード, 部門名, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 残高金額, 計上年, 計上月, 計上日, 仕訳番号, 行番 ) SELECT DISTINCTROW 部門科目別残高試算表.集計キー, 部門科目別残高試算表.部門コード, 部門科目別残高試算表.部門名, ' ', '残　　高', ' ', ' ', 部門科目別残高試算表.残高金額, 9999 AS 計上年, 99 AS 計上月, 99 AS 計上日, 999999 AS 仕訳番号, 99 AS 行番 FROM 部門科目別残高試算表 WHERE ((" & KAMOKU_JOKEN & "));"
	    End If
	    qdf.Close
	    DoCmd.OpenQuery "元帳残高追加集約"*/
	    if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname, '', '残　　高',' ',' ',departments_subject_trial_balance.balance, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM departments_subject_trial_balance WHERE (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + AC_FROM +"') AND (departments_subject_trial_balance.aggregate_key <= '"+ BUMON + AC_TO +"999999')");
	    } else {
	    	//KAMOKU_JOKEN = "(departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[0] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[1] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[2] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[3] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[4] +"') OR (departments_subject_trial_balance.aggregate_key = '"+ BUMON + ACSEL_2[5] +"')";
	    	KAMOKU_JOKEN ="";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[0] +"')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[1] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[1] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[2] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[2] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[3] +"')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[3] +"')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[4] +"')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[4] +"')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[5] +"')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (departments_subject_trial_balance.aggregate_key >= '"+ BUMON + ACSEL_2[5] +"')";
	    		}
	    	}
	    	QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW departments_subject_trial_balance.aggregate_key,departments_subject_trial_balance.devcode,departments_subject_trial_balance.devname, ' ', '残　　高',' ',' ',departments_subject_trial_balance.balance, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM departments_subject_trial_balance WHERE ("+ KAMOKU_JOKEN +")");
	    }
	    
	    //PRJCODEのNULLを空白に
	    /*Set qdf = dbs.CreateQueryDef("", "UPDATE 仕訳テーブル SET 仕訳テーブル.PRJCODE = '' WHERE 仕訳テーブル.PRJCODE Is Null;")
	    qdf.Execute
	    qdf.Close*/
	    QueryDefsUpdate("UPDATE journals SET journals.PRJCODE = '' WHERE journals.prjcode Is Null OR journals.prjcode = 'null'");
	    
	    //借方明細を元帳トランに追加
	    /*Set qdf = dbs.QueryDefs("借方科目別元帳データ")
	    SQL_TEMP = "INSERT INTO 元帳トラン ( 部門コード, 部門名, 仕訳番号,伝票番号, 行番, 計上年, 計上月, 計上日, 借方金額, 貸方金額, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 摘要コード, 摘要名, 集計キー, 諸口判断,取引先コード,取引先名,号数, 借方消費税区分, 貸方消費税区分, PRJCODE, PRJNAME ) SELECT DISTINCTROW 科目キー仕訳テーブル.部門コード, 部門テーブル.部門名, 科目キー仕訳テーブル.仕訳番号,科目キー仕訳テーブル.伝票番号, 科目キー仕訳テーブル.行番, 科目キー仕訳テーブル.計上年, 科目キー仕訳テーブル.計上月, 科目キー仕訳テーブル.計上日, 科目キー仕訳テーブル.借方金額, 0 AS 貸方金額, 科目キー仕訳テーブル.貸方科目コード AS 相手科目コード, 貸方部門科目テーブル.科目名 AS 相手科目名, 科目キー仕訳テーブル.貸方細目コード AS 相手細目コード, 科目キー仕訳テーブル.貸方細目名 AS 相手細目名, 科目キー仕訳テーブル.摘要コード, 科目キー仕訳テーブル.摘要名, 科目キー仕訳テーブル.借方キー"
	    If ACSEL(1) = " " And ACSEL(2) = " " And ACSEL(3) = " " And ACSEL(4) = " " And ACSEL(5) = " " And ACSEL(6) = " " Then
	        qdf.SQL = SQL_TEMP & ", 科目キー仕訳テーブル.諸口判断, 科目キー仕訳テーブル.取引先コード, 科目キー仕訳テーブル.取引先名, 科目キー仕訳テーブル.号数, 科目キー仕訳テーブル.借方消費税区分, 科目キー仕訳テーブル.貸方消費税区分, 科目キー仕訳テーブル.PRJCODE, 科目キー仕訳テーブル.PRJNAME FROM (科目キー仕訳テーブル LEFT JOIN 貸方部門科目テーブル ON 科目キー仕訳テーブル.貸方キー = 貸方部門科目テーブル.集計キー) LEFT JOIN 部門テーブル ON 科目キー仕訳テーブル.部門コード = 部門テーブル.部門コード WHERE (((科目キー仕訳テーブル.借方キー)>='" & BUMON & AC_FROM & "' And (科目キー仕訳テーブル.借方キー)<='" & BUMON & AC_TO & "999999" & "') AND (([計上年]*10000+[計上月]*100+[計上日])>=" & Format(YMD_FROM) & "And ([計上年]*10000+[計上月]*100+[計上日])<=" & Format(YMD_TO) & ")) AND PRJCODE LIKE '" & Forms!元帳作成!PRJCODE & "*';"
	    Else
	        KAMOKU_JOKEN = "(科目キー仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(1) & "' AND 科目キー仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(1) & "ZZZZZZ') OR (科目キー仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(2) & "' AND 科目キー仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(2) & "ZZZZZZ') OR (科目キー仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(3) & "' AND 科目キー仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(3) & "ZZZZZZ') OR (科目キー仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(4) & "' AND 科目キー仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(4) & "ZZZZZZ') OR (科目キー仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(5) & "' AND 科目キー仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(5) & "ZZZZZZ') OR (科目キー仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(6) & "' AND 科目キー仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(6) & "ZZZZZZ')"
	        qdf.SQL = SQL_TEMP & ", 科目キー仕訳テーブル.諸口判断, 科目キー仕訳テーブル.取引先コード, 科目キー仕訳テーブル.取引先名, 科目キー仕訳テーブル.号数, 科目キー仕訳テーブル.借方消費税区分, 科目キー仕訳テーブル.貸方消費税区分, 科目キー仕訳テーブル.PRJCODE, 科目キー仕訳テーブル.PRJNAME FROM (科目キー仕訳テーブル LEFT JOIN 貸方部門科目テーブル ON 科目キー仕訳テーブル.貸方キー = 貸方部門科目テーブル.集計キー) LEFT JOIN 部門テーブル ON 科目キー仕訳テーブル.部門コード = 部門テーブル.部門コード WHERE ((" & KAMOKU_JOKEN & ") AND (([計上年]*10000+[計上月]*100+[計上日])>=" & Format(YMD_FROM) & "And ([計上年]*10000+[計上月]*100+[計上日])<=" & Format(YMD_TO) & ")) AND PRJCODE LIKE '" & Forms!元帳作成!PRJCODE & "*';"
	    End If
	    qdf.Close
	    DoCmd.OpenQuery "借方科目別元帳データ"*/
	    //2
	    SQL_TEMP = "INSERT INTO ledger_tran (devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,desname,aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax, prjcode, prjname) SELECT DISTINCTROW subjects_key_journal.devcode,devtables.devname,subjects_key_journal.je_number,subjects_key_journal.s_number,subjects_key_journal.l_number,subjects_key_journal.yyyy,subjects_key_journal.mm,subjects_key_journal.dd,subjects_key_journal.dramount, 0 AS cramount,subjects_key_journal.craccode AS subject_code,credit_department_subjects.acname AS subject_name,subjects_key_journal.cracsubcode AS specific_code,subjects_key_journal.cracsubname AS specific_name, subjects_key_journal.descode,subjects_key_journal.desname,subjects_key_journal.drkey";
	    if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	SQL_TEMP += ",subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.PRJCODE,subjects_key_journal.PRJNAME FROM (subjects_key_journal LEFT JOIN credit_department_subjects ON subjects_key_journal.crkey = credit_department_subjects.aggregate_key) LEFT JOIN devtables ON subjects_key_journal.devcode= devtables.devcode WHERE (((subjects_key_journal.drkey)>= '"+ BUMON + AC_FROM +"' AND (subjects_key_journal.drkey) <= '"+ BUMON + AC_TO +"999999') AND ((yyyy * 10000 + mm * 100 + dd)>= "+ YMD_FROM + " AND (yyyy * 10000 +mm * 100 + dd) <= "+ YMD_TO +")) AND prjcode LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] + "%'";
	    } else{
	    	//KAMOKU_JOKEN = "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[0] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ') OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ') OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ') OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ') OR (subjects_key_journal.drkey >='"+ BUMON + ACSEL_2[4] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ') OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[5] + "' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    	
	    	KAMOKU_JOKEN ="";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[0] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[4] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[4] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[5] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (subjects_key_journal.drkey >= '"+ BUMON + ACSEL_2[5] +"' AND subjects_key_journal.drkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    		}
	    	}
	    	
	    	SQL_TEMP += ",subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.prjcode,subjects_key_journal.prjname FROM (subjects_key_journal LEFT JOIN credit_department_subjects ON subjects_key_journal.crkey = credit_department_subjects.aggregate_key) LEFT JOIN devtables ON subjects_key_journal.devcode = devtables.devcode WHERE (("+ KAMOKU_JOKEN +") AND ((yyyy * 10000 + mm * 100 + dd) >= "+ YMD_FROM +" AND (yyyy * 10000 + mm * 100 + dd) <= "+ YMD_TO +")) AND prjcode LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] +"%'";
	    }
	    QueryDefsInsert(SQL_TEMP);
	    
	    
	    //貸方明細を元帳トランに追加
	    /*Set qdf = dbs.QueryDefs("貸方科目別元帳データ")
	    SQL_TEMP = "INSERT INTO 元帳トラン ( 部門コード, 部門名, 仕訳番号,伝票番号, 行番, 計上年, 計上月, 計上日, 借方金額, 貸方金額, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 摘要コード, 摘要名, 集計キー, 諸口判断,取引先コード,取引先名,号数, 借方消費税区分, 貸方消費税区分, PRJCODE, PRJNAME ) SELECT DISTINCTROW 科目キー仕訳テーブル.部門コード, 部門テーブル.部門名, 科目キー仕訳テーブル.仕訳番号, 科目キー仕訳テーブル.伝票番号,科目キー仕訳テーブル.行番, 科目キー仕訳テーブル.計上年, 科目キー仕訳テーブル.計上月, 科目キー仕訳テーブル.計上日, 0 AS 借方金額, 科目キー仕訳テーブル.貸方金額, 科目キー仕訳テーブル.借方科目コード AS 相手科目コード, 借方部門科目テーブル.科目名 AS 相手科目名, 科目キー仕訳テーブル.借方細目コード AS 相手細目コード, 科目キー仕訳テーブル.借方細目名 AS 相手細目名, 科目キー仕訳テーブル.摘要コード, 科目キー仕訳テーブル.摘要名, 科目キー仕訳テーブル.貸方キー"
	    If ACSEL(1) = " " And ACSEL(2) = " " And ACSEL(3) = " " And ACSEL(4) = " " And ACSEL(5) = " " And ACSEL(6) = " " Then
	        qdf.SQL = SQL_TEMP & ", 科目キー仕訳テーブル.諸口判断, 科目キー仕訳テーブル.取引先コード, 科目キー仕訳テーブル.取引先名, 科目キー仕訳テーブル.号数, 科目キー仕訳テーブル.借方消費税区分, 科目キー仕訳テーブル.貸方消費税区分, 科目キー仕訳テーブル.PRJCODE, 科目キー仕訳テーブル.PRJNAME FROM (科目キー仕訳テーブル LEFT JOIN 部門テーブル ON 科目キー仕訳テーブル.部門コード = 部門テーブル.部門コード) LEFT JOIN 借方部門科目テーブル ON 科目キー仕訳テーブル.借方キー = 借方部門科目テーブル.集計キー WHERE ((科目キー仕訳テーブル.貸方キー)>='" & BUMON & AC_FROM & "' And (科目キー仕訳テーブル.貸方キー)<='" & BUMON & AC_TO & "999999" & "') AND (([計上年]*10000+[計上月]*100+[計上日])>=" & Format(YMD_FROM) & " And ([計上年]*10000+[計上月]*100+[計上日])<=" & Format(YMD_TO) & ") AND PRJCODE LIKE '" & Forms!元帳作成!PRJCODE & "*';"
	    Else
	        KAMOKU_JOKEN = "(科目キー仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(1) & "' AND 科目キー仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(1) & "ZZZZZZ') OR (科目キー仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(2) & "' AND 科目キー仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(2) & "ZZZZZZ') OR (科目キー仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(3) & "' AND 科目キー仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(3) & "ZZZZZZ') OR (科目キー仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(4) & "' AND 科目キー仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(4) & "ZZZZZZ') OR (科目キー仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(5) & "' AND 科目キー仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(5) & "ZZZZZZ') OR (科目キー仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(6) & "' AND 科目キー仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(6) & "ZZZZZZ')"
	        qdf.SQL = SQL_TEMP & ", 科目キー仕訳テーブル.諸口判断, 科目キー仕訳テーブル.取引先コード, 科目キー仕訳テーブル.取引先名, 科目キー仕訳テーブル.号数, 科目キー仕訳テーブル.借方消費税区分, 科目キー仕訳テーブル.貸方消費税区分, 科目キー仕訳テーブル.PRJCODE, 科目キー仕訳テーブル.PRJNAME FROM (科目キー仕訳テーブル LEFT JOIN 部門テーブル ON 科目キー仕訳テーブル.部門コード = 部門テーブル.部門コード) LEFT JOIN 借方部門科目テーブル ON 科目キー仕訳テーブル.借方キー = 借方部門科目テーブル.集計キー WHERE ((" & KAMOKU_JOKEN & ") AND (([計上年]*10000+[計上月]*100+[計上日])>=" & Format(YMD_FROM) & " And ([計上年]*10000+[計上月]*100+[計上日])<=" & Format(YMD_TO) & ")) AND PRJCODE LIKE '" & Forms!元帳作成!PRJCODE & "*';"
	    End If
	    qdf.Close
	    DoCmd.OpenQuery "貸方科目別元帳データ"*/
	    //Done 3
    	SQL_TEMP = "INSERT INTO ledger_tran (devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,desname,aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax, prjcode, prjname) SELECT DISTINCTROW subjects_key_journal.devcode,devtables.devname,subjects_key_journal.je_number,subjects_key_journal.s_number,subjects_key_journal.l_number,subjects_key_journal.yyyy,subjects_key_journal.mm,subjects_key_journal.dd,0 AS dramount,subjects_key_journal.cramount,subjects_key_journal.draccode AS subject_code,debit_department_subjects.acname AS subject_name,subjects_key_journal.dracsubcode AS specific_code,subjects_key_journal.dracsubname AS specific_name,subjects_key_journal.descode, subjects_key_journal.descode, subjects_key_journal.crkey";
    	if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
    	    SQL_TEMP += ",subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.prjcode,subjects_key_journal.prjname FROM (subjects_key_journal LEFT JOIN devtables ON subjects_key_journal.devcode = devtables.devcode LEFT JOIN debit_department_subjects ON subjects_key_journal.drkey = debit_department_subjects.aggregate_key) WHERE ((subjects_key_journal.crkey >= '"+ BUMON + AC_FROM +"' AND subjects_key_journal.crkey <= '"+ BUMON + AC_TO +"999999') AND ((yyyy * 10000 + mm * 100 + dd)>= "+ YMD_FROM + " AND (yyyy * 10000 + mm * 100 + dd) <= "+ YMD_TO +")) AND prjcode LIKE '"+ cmbPrjCode.getSelectedItem() + "%'";
    	} else{
    		//KAMOKU_JOKEN = "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[0] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ') OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[1] + "' AND subjects_key_journal.crkey <= '" + BUMON + ACSEL_2[1] +"ZZZZZZ') OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ') OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ') OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[4] + "' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ') OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2 [5] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
    	    
    		KAMOKU_JOKEN ="";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[0] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[1] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[1] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[4] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[4] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[5] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (subjects_key_journal.crkey >= '"+ BUMON + ACSEL_2[5] +"' AND subjects_key_journal.crkey <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    		}
	    	}
    		
    		SQL_TEMP += ",subjects_key_journal.sundry,subjects_key_journal.vendorcode,subjects_key_journal.vendorname,subjects_key_journal.otherdata,subjects_key_journal.drctax,subjects_key_journal.crctax,subjects_key_journal.prjcode,subjects_key_journal.prjname FROM subjects_key_journal LEFT JOIN devtables ON subjects_key_journal.devcode = devtables.devcode LEFT JOIN debit_department_subjects ON subjects_key_journal.drkey = debit_department_subjects.aggregate_key WHERE (("+ KAMOKU_JOKEN +") AND ((yyyy * 10000 + mm * 100 + dd)>="+ YMD_FROM +" AND (yyyy * 10000 + mm * 100 + dd) <="+ YMD_TO +")) AND prjcode LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] + "%'";
    	}
    	QueryDefsInsert(SQL_TEMP);
	    
	    //元帳テーブルの作成
	    /*Set qdf = dbs.QueryDefs("元帳テーブル作成")
	    If NARABI = 1 Then
	        SQL_TEMP = "ORDER BY 元帳トラン.集計キー, 元帳トラン.仕訳番号, 元帳トラン.行番, 元帳トラン.計上年, 元帳トラン.計上月, 元帳トラン.計上日;"
	    Else
	        SQL_TEMP = "ORDER BY 元帳トラン.集計キー, 元帳トラン.計上年, 元帳トラン.計上月, 元帳トラン.計上日, 元帳トラン.仕訳番号, 元帳トラン.行番;"
	    End If
	    qdf.SQL = "SELECT DISTINCTROW 元帳トラン.集計キー, 科目テーブル集約２.科目コード AS 対象科目コード, 科目テーブル集約２.科目名 AS 対象科目名, ' ' AS 対象細目コード, ' ' AS 対象細目名, 元帳トラン.部門コード, 元帳トラン.部門名, 元帳トラン.仕訳番号,元帳トラン.伝票番号,元帳トラン.行番, 元帳トラン.計上年, 元帳トラン.計上月, 元帳トラン.計上日, 元帳トラン.借方科目コード, 元帳トラン.借方科目名, 元帳トラン.借方細目コード, 元帳トラン.借方細目名, 元帳トラン.借方金額, 元帳トラン.貸方金額, 元帳トラン.残高金額, 元帳トラン.摘要コード, 元帳トラン.摘要名, 元帳トラン.諸口判断, 元帳トラン.取引先コード, 元帳トラン.取引先名, 元帳トラン.号数, 元帳トラン.借方消費税区分, 元帳トラン.貸方消費税区分, 元帳トラン.PRJCODE, 元帳トラン.PRJNAME INTO 元帳テーブル FROM 元帳トラン INNER JOIN 科目テーブル集約２ ON 元帳トラン.集計キー = 科目テーブル集約２.集計キー " & SQL_TEMP
	    qdf.Close
	    If OBJ_EXIST("元帳テーブル") = 2 Then
	        DoCmd.DeleteObject acTable, "元帳テーブル"
	    End If
	    DoCmd.OpenQuery "元帳テーブル作成"*/ 
    	//Working
    	if(NARABI == 1){
    		SQL_TEMP = "ORDER BY ledger_tran.aggregate_key,ledger_tran.je_number,ledger_tran.l_number,ledger_tran.yyyy,ledger_tran.mm,ledger_tran.dd";
    	} else{
    	    SQL_TEMP = "ORDER BY ledger_tran.aggregate_key,ledger_tran.yyyy,ledger_tran.mm,ledger_tran.dd,ledger_tran.je_number,ledger_tran.l_number";
    	}
    	StringBuffer SQL = new StringBuffer("INSERT into ledger (aggregate_key,accode,acname,acsubcode,acsubname,devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,draccode,dracname,dracsubcode,dracsubname,dramount,cramount,balance,descode,desname,sundry,vendorcode,vendorname,otherdata,drctax,crctax,prjcode,prjname) SELECT DISTINCTROW ledger_tran.aggregate_key,actablesaggregation2.item_code AS accode,actablesaggregation2.item_name AS acname, '' AS acsubcode, '' AS acsubname,ledger_tran.devcode,ledger_tran.devname,ledger_tran.je_number,ledger_tran.s_number,ledger_tran.l_number,ledger_tran.yyyy,ledger_tran.mm,ledger_tran.dd,ledger_tran.draccode,ledger_tran.dracname,ledger_tran.dracsubcode,ledger_tran.dracsubname,ledger_tran.dramount,ledger_tran.cramount,ledger_tran.balance,ledger_tran.descode,ledger_tran.desname,ledger_tran.sundry,ledger_tran.vendorcode,ledger_tran.vendorname,ledger_tran.otherdata,ledger_tran.drctax,ledger_tran.crctax,ledger_tran.prjcode,ledger_tran.prjname FROM ledger_tran INNER JOIN actablesaggregation2 ON ledger_tran.aggregate_key = actablesaggregation2.aggregate_key " + SQL_TEMP);
    	
    	TableDefs(SQL,"ledger");
	    //DoCmd.SetWarnings True
	    
	    //明細行がない科目をカット
	    /*If Forms!元帳作成!PRJOKEN = 1 Then
	        GYOHAN = 100
	        Set rst = dbs.OpenRecordset("元帳テーブル", dbOpenDynaset)
	        Do Until rst.EOF
	            If Right(Format(rst!仕訳番号), 4) = "9999" And GYOHAN = 0 Then
	                rst.MovePrevious
	                rst.Edit
	                rst!号数 = "削除データ"
	                rst.Update
	                rst.MoveNext
	                rst.Edit
	                rst!号数 = "削除データ"
	                rst.Update
	            End If
	            GYOHAN = rst!仕訳番号
	            rst.MoveNext
	        Loop
	        rst.Close
	    End If*/
    	
    	String origCode = getLedgerWithoutDetail();
    	
    	/*List<Ledger> ledgersUpdated = new ArrayList<Ledger>();
    	if(cmbPrjoken.getSelectedItem().equals("1" + " | " + "明細がないページを印刷しない")){
    		GYOHAN = 100;
    		List<Ledger> ledgers = getLedger();    			
    		int ldgSize = ledgers.size();
    		for(int i=0;i<ldgSize;i++){
    			Ledger ldg = ledgers.get(i);
    			if((Right(String.valueOf(ldg.getJe_number()),4).equals("9999")) && (GYOHAN == 0)){
    				if(i==0){
    					//last record
    					ldg = ledgers.get(ldgSize-1);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				 	//current record
    				 	ldg = ledgers.get(i);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				} else if(i==(ldgSize-1)){
    					//first record
    					ldg = ledgers.get(0);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				 	//current record
    				 	ldg = ledgers.get(i);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				} else{
    					//rst.MovePrevious
    					ldg = ledgers.get(i-1);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				 	//rst.MoveNext
    				 	ldg = ledgers.get(i);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				}
    			}
    			ledgersUpdated.add(ldg);
    			GYOHAN = ldg.getJe_number();
    		}
    	}*/
    		
	    /*Set qdf = dbs.CreateQueryDef("", "delete * from 元帳テーブル where 号数='削除データ';")
	    qdf.Execute
	    qdf.Close*/
    	//for(Ledger ldg: ledgersUpdated){
    		//if((ldg.getOtherdata()!=null)&&(ldg.getOtherdata().equals("削除データ"))){
    	String sqlLdgDel = "";
	    	if(!origCode.equals("")){
	    		sqlLdgDel = "DELETE FROM ledger WHERE ((je_number=0 OR je_number=999999) AND aggregate_key NOT IN("+origCode+"))";
	    	} else{
	    		sqlLdgDel = "DELETE FROM ledger WHERE (je_number=0 OR je_number=999999)";
	    	}
    		QueryDefsUpdate(sqlLdgDel);
    		//}
    	//}
    	
	    
	    //残高金額及び諸口の処理
	    /*Set rst = dbs.OpenRecordset("元帳テーブル", dbOpenDynaset)
	    Do Until rst.EOF
	        If rst!借方科目名 = "前繰残高" Then
	            ZANDAKA_TRAN = rst!残高金額
	        ElseIf rst!借方科目名 = "残　　高" Then
	        Else
	            ZANDAKA_TRAN = ZANDAKA_TRAN + Nz(rst!借方金額) - Nz(rst!貸方金額)
	            rst.Edit
	            rst!残高金額 = ZANDAKA_TRAN
	            rst.Update
	        End If
	        If rst!諸口判断 = 1 Then
	            rst.Edit
	            rst!借方科目コード = "    "
	            rst!借方科目名 = "諸口"
	            rst!借方細目コード = "    "
	            rst!借方細目名 = "    "
	            rst.Update
	        End If
	        rst.MoveNext
	    Loop
	    rst.Close*/
    	
    	List<Ledger> ledgers = getLedger();
    	for(Ledger ldg:ledgers){
    		if(ldg.getDracname().equals("前繰残高")){
    			ZANDAKA_TRAN = ldg.getBalance();
    		} else if(ldg.getDracname().equals("残　　高")){
    			
    		} else{
    			ZANDAKA_TRAN = ZANDAKA_TRAN + ldg.getDramount() - ldg.getCramount();
    			String sqlDel = "UPDATE ledger SET balance="+ZANDAKA_TRAN+" WHERE aggregate_key='"+ldg.getAggregate_key()+"' AND je_number="+ldg.getJe_number();
    			QueryDefsUpdate(sqlDel);
    		}
    		if(ldg.getSundry() == 1){
    			String sqlDel = "UPDATE ledger SET draccode='    ',dracname='諸口',dracsubcode='    ',dracsubname='    ' WHERE aggregate_key='"+ldg.getAggregate_key()+"' AND je_number="+ldg.getJe_number();
    			QueryDefsUpdate(sqlDel);
    		}
    	}    	
	    
	    //元帳レポートを開く
	    /*If NARABI = 1 Then
	        JOKEN = "入力順"
	    ElseIf NARABI = 2 Then
	        JOKEN = "日付順"
	    End If*/
    	if(NARABI == 1){
    		JOKEN = "入力順";
    	} else if(NARABI == 2){
    		JOKEN = "日付順";
    	}
    	
	    /*Set rst = dbs.OpenRecordset("レポートパラメータ", dbOpenDynaset)
	    rst.Edit
	    rst!元帳抽出条件 = "抽出条件　部門" & BUMON & "  " & Format(NEN_FROM) & "年" & Format(TUKI_FROM) & "月" & Format(HI_FROM) & "日から" & Format(NEN_TO) & "年" & Format(TUKI_TO) & "月" & Format(HI_TO) & "日まで・" & JOKEN
	    rst.Update
	    rst.Close*/
    	boolean fo = false;
		try {
			DBManager db = new DBManager();
			
			try {
				String sqlSt = "SELECT * FROM reportparameters";
				
				ResultSet rs = db.getRecord(sqlSt);
				
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
    	
    	String ledger_extrantion_cond ="抽出条件　部門" + BUMON + "  " + NEN_FROM + "年" + TUKI_FROM + "月" + HI_FROM + "日から" + NEN_TO + "年" + TUKI_TO + "月" + HI_TO + "日まで・" + JOKEN;
    	try {
			DBManager dbc = new DBManager();
			try{
				if(fo) {
					PreparedStatement pstm = dbc.getPreparStamt("UPDATE reportparameters SET ledger_extrantion_cond = '" + ledger_extrantion_cond+ "'");
					pstm.executeUpdate();
				} else {
					PreparedStatement pstm = dbc.getPreparStamt("INSERT INTO reportparameters(ledger_extrantion_cond) VALUES(?)");
					
					pstm.setString(1, ledger_extrantion_cond);
					pstm.executeUpdate();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
	    /*If NARABI = 1 Then
	        DoCmd.SetWarnings False
	        DoCmd.OpenReport "補助元帳", acDesign
	        Reports!補助元帳!ラベル36.Caption = "総勘定元帳"
	        DoCmd.Save acReport, "補助元帳"
	        DoCmd.Close acReport, "補助元帳"
	        DoCmd.SetWarnings True
	        DoCmd.OpenReport "補助元帳", acPreview
	    ElseIf NARABI = 2 Then
	        DoCmd.SetWarnings False
	        DoCmd.OpenReport "補助元帳2", acDesign
	        Reports!補助元帳2!ラベル36.Caption = "総勘定元帳"
	        DoCmd.Save acReport, "補助元帳2"
	        DoCmd.Close acReport, "補助元帳2"
	        DoCmd.SetWarnings True
	        DoCmd.OpenReport "補助元帳2", acPreview
	    End If*/
    	String param="";
    	try {
			DBManager db = new DBManager();
			
			try {
				String sqlRptParam = "SELECT DISTINCTROW reportparameters.ledger_extrantion_cond FROM reportparameters";
				
				ResultSet rs = db.getRecord(sqlRptParam);

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
    	
    	if(NARABI == 1){
    		param = param + "~" + "総勘定元帳";
    		CallReport("subledger",param);
    	} else if(NARABI == 2){
    		param = param + "~" + "総勘定元帳";
    		CallReport("subledger2",param);
    	}
	}
	
	public void GL_Q_CONTROL(){
		//元帳
	    int NARABI=0;
	    long NEN_FROM=0;  //開始年
	    long NEN_TO=0;    //終了年
	    long TUKI_FROM=0;  //開始月
	    long TUKI_TO=0;    //終了月
	    long HI_FROM=0;  //開始日
	    long HI_TO=0;    //終了日
	    long YMD_FROM=0;
	    long YMD_TO=0;
	    long KESSAN_NEN=0; //決算年
	    long KESSAN_TUKI;  //決算月
	    String AC_FROM;   //開始科目
	    String AC_TO;   //終了科目
	    String ACSEL[] = new String[6];   //選択科目配列
	    String ACSEL_2[] = new String[6];   //選択科目配列
	    String SAI_SEL;   //細目まで指定
	    String BUMON; //部門
	    long KISHU=0;
	    String SHUKEI_KEY;
	    String JOKEN="";
	    String KAMOKU_JOKEN;
	    double ZANDAKA_TRAN=0;
	    String SQL_TEMP;
	    int dum=0;
	    long GYOHAN=0;
	    
	    //フォームの初期値算定
	    //NULL値のチェック
	    if (cmbBumon.getSelectedItem().equals("")) {
			BUMON = "";
		} else {
			BUMON = String.valueOf(cmbBumon.getSelectedItem()).split(" | ")[0];
		}
	    if (cmbNarabi.getSelectedItem().equals("")) {
	    	cmbNarabi.setSelectedItem("1" + " | " + "入力順");
	    }
	    if (cmbAcFrom.getSelectedItem().equals("")) {
	    	cmbAcFrom.setSelectedItem("0000");
	    }
	    if (cmbAcTo.getSelectedItem().equals("")) {
	    	cmbAcTo.setSelectedItem("9999");
	    }
	    if (cmbAcSel1.getSelectedItem().equals("")) {
	    	cmbAcSel1.setSelectedItem("");
	    }
	    if (cmbAcSel2.getSelectedItem().equals("")) {
	    	cmbAcSel2.setSelectedItem("");
	    }
	    if (cmbAcSel3.getSelectedItem().equals("")) {
	    	cmbAcSel3.setSelectedItem("");
	    }
	    if (cmbAcSel4.getSelectedItem().equals("")) {
	    	cmbAcSel4.setSelectedItem("");
	    }
	    if (cmbAcSel5.getSelectedItem().equals("")) {
	    	cmbAcSel5.setSelectedItem("");
	    }
	    if (cmbAcSel6.getSelectedItem().equals("")) {
	    	cmbAcSel6.setSelectedItem("");
	    }
	    if (txtTukiFrom.getText().equals("")) {
	    	txtTukiFrom.setText("4");
	    }
	    if (txtTukiTo.getText().equals("")) {
	    	txtTukiTo.setText("4");
	    }
	    if (txtHiFrom.getText().equals("")) {
	    	txtHiFrom.setText("1");
	    }
	    if (txtHiTo.getText().equals("")) {
	    	txtHiTo.setText("31");
	    }
	    if (cmbSaiSel.getSelectedItem().equals("")) {
	    	cmbSaiSel.setSelectedItem("");
	    }
	    //フォームからデータを取得
	    //BUMON = Forms!元帳作成!BUMON
	    NARABI = Integer.parseInt(String.valueOf(cmbNarabi.getSelectedItem()).split(" | ")[0]);
	    AC_FROM = String.valueOf(cmbAcFrom.getSelectedItem()).split(" | ")[0];
	    AC_TO = String.valueOf(cmbAcTo.getSelectedItem()).split(" | ")[0];
	    ACSEL[0] = String.valueOf(cmbAcSel1.getSelectedItem()).split(" | ")[0];
	    ACSEL[1] = String.valueOf(cmbAcSel2.getSelectedItem()).split(" | ")[0];
	    ACSEL[2] = String.valueOf(cmbAcSel3.getSelectedItem()).split(" | ")[0];
	    ACSEL[3] = String.valueOf(cmbAcSel4.getSelectedItem()).split(" | ")[0];
	    ACSEL[4] = String.valueOf(cmbAcSel5.getSelectedItem()).split(" | ")[0];
	    ACSEL[5] = String.valueOf(cmbAcSel6.getSelectedItem()).split(" | ")[0];
	    
	    if(ACSEL[0].equals(" ")){
	        ACSEL_2[0] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[0] = ACSEL[0];
	    }
	    if(ACSEL[1].equals(" ")){ 
	        ACSEL_2[1] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[1] = ACSEL[1];
		}
	    if(ACSEL[2].equals(" ")){
	        ACSEL_2[2] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[2] = ACSEL[2];
	    }
	    if(ACSEL[3].equals(" ")){
	    	ACSEL_2[3] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[3] = ACSEL[3];
	    }
	    if(ACSEL[4].equals(" ")){
	    	ACSEL_2[4] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[4] = ACSEL[4];
	    }
	    if(ACSEL[5].equals(" ")){
	    	ACSEL_2[5] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[5] = ACSEL[5];
	    }
	    SAI_SEL = String.valueOf(cmbSaiSel.getSelectedItem()).split(" | ")[0];
	    		
	    TUKI_FROM = Integer.parseInt(txtTukiFrom.getText());
	    TUKI_TO = Integer.parseInt(txtTukiTo.getText());
	    HI_FROM = Integer.parseInt(txtHiFrom.getText());
	    HI_TO = Integer.parseInt(txtHiTo.getText());
	    YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
	    YMD_TO = YM_GET(TUKI_TO, HI_TO);
	    NEN_FROM = (int)(YMD_FROM / 10000);
	    NEN_TO = (int)(YMD_TO / 10000);
	    
	    //DoCmd.SetWarnings False
	    //Set dbs = CurrentDb()
	    
	    //残高試算表クエリーのパラメータセット
	    dum = TB_Q_SET(BUMON, YMD_FROM, YMD_TO, KISHU);
	   
	    
	    //元帳トランのデータをクリア
	    //DoCmd.OpenQuery "元帳トランクリア" 
	    TableClear("ledger_tran");
	    //==================================Step 1==========================================
	    //選択した勘定科目のデータを元帳トランに追加する。
	    //前繰残高を元帳トランに追加
	    /*If SAI_SEL <> " " Then
        	qdf.SQL = "INSERT INTO 元帳トラン ( 集計キー, 部門コード, 部門名, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 残高金額, 計上年, 計上月, 計上日, 仕訳番号, 行番 ) SELECT DISTINCTROW 部門科目細目別期首残高_補助元帳用.集計キー, 部門科目細目別期首残高_補助元帳用.部門コード, 部門科目細目別期首残高_補助元帳用.部門名, ' ', '前繰残高', ' ', ' ', 部門科目細目別期首残高_補助元帳用.前繰残高, 0 AS 計上年, 0 AS 計上月, 0 AS 計上日, 0 AS 仕訳番号, 0 AS 行番 FROM 部門科目細目別期首残高_補助元帳用 WHERE (((部門科目細目別期首残高_補助元帳用.集計キー)>='" & BUMON & SAI_SEL & "' And (部門科目細目別期首残高_補助元帳用.集計キー)<='" & BUMON & SAI_SEL & "999999" & "'));"
    	ElseIf ACSEL(1) = " " And ACSEL(2) = " " And ACSEL(3) = " " And ACSEL(4) = " " And ACSEL(5) = " " And ACSEL(6) = " " Then
        	qdf.SQL = "INSERT INTO 元帳トラン ( 集計キー, 部門コード, 部門名, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 残高金額, 計上年, 計上月, 計上日, 仕訳番号, 行番 ) SELECT DISTINCTROW 部門科目細目別期首残高_補助元帳用.集計キー, 部門科目細目別期首残高_補助元帳用.部門コード, 部門科目細目別期首残高_補助元帳用.部門名, ' ', '前繰残高', ' ', ' ', 部門科目細目別期首残高_補助元帳用.前繰残高, 0 AS 計上年, 0 AS 計上月, 0 AS 計上日, 0 AS 仕訳番号, 0 AS 行番 FROM 部門科目細目別期首残高_補助元帳用 WHERE (((部門科目細目別期首残高_補助元帳用.集計キー)>='" & BUMON & AC_FROM & "' And (部門科目細目別期首残高_補助元帳用.集計キー)<='" & BUMON & AC_TO & "999999" & "'));"
    	Else
        	KAMOKU_JOKEN = "(部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(1) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(1) & "ZZZZZZ') OR (部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(2) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(2) & "ZZZZZZ') OR (部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(3) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(3) & "ZZZZZZ') OR (部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(4) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(4) & "ZZZZZZ') OR (部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(5) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(5) & "ZZZZZZ') OR (部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(6) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(6) & "ZZZZZZ')"
        	qdf.SQL = "INSERT INTO 元帳トラン ( 集計キー, 部門コード, 部門名, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 残高金額, 計上年, 計上月, 計上日, 仕訳番号, 行番 ) SELECT DISTINCTROW 部門科目細目別期首残高_補助元帳用.集計キー, 部門科目細目別期首残高_補助元帳用.部門コード, 部門科目細目別期首残高_補助元帳用.部門名, ' ', '前繰残高', ' ', ' ', 部門科目細目別期首残高_補助元帳用.前繰残高, 0 AS 計上年, 0 AS 計上月, 0 AS 計上日, 0 AS 仕訳番号, 0 AS 行番 FROM 部門科目細目別期首残高_補助元帳用 WHERE ((" & KAMOKU_JOKEN & "));"
    	End If
    	qdf.Close
    	DoCmd.OpenQuery "元帳前残追加"*/
	    
	    if(!SAI_SEL.equals("")){
	    	QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode,department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ','前繰残高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd,0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE (((department_couses_details_by_opening_balance_auxuliary_ledger.keycode)>= '"+ BUMON + SAI_SEL +"' And (department_couses_details_by_opening_balance_auxuliary_ledger.keycode) <= '"+ BUMON + SAI_SEL + "999999" + "'))");
	    }
	    else if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode,department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ','前繰残高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd,0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE ((((department_couses_details_by_opening_balance_auxuliary_ledger.keycode) >= '"+ BUMON + AC_FROM +"' And (department_couses_details_by_opening_balance_auxuliary_ledger.keycode) <= '"+ BUMON + AC_TO + "999999" +"'))");
	    } else {
	    	KAMOKU_JOKEN ="";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[0] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[0] +"ZZZZZZ')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[1] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[1] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[1] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[2] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[2] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[2] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[3] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[3] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[3] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[4] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[4] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[4] +"ZZZZZZ')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[5] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL_2[5] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL_2[5] +"ZZZZZZ')";
	    		}
	    	}
	    	QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode,department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ','前繰残高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd,0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE ("+ KAMOKU_JOKEN +")");
	    }    	    
	    //==================================Step 2==========================================
	    //'残高金額を元帳トランに追加
	    /*Set qdf = dbs.QueryDefs("元帳残高追加")
	    If SAI_SEL <> " " Then
	    	qdf.SQL = "INSERT INTO 元帳トラン ( 集計キー, 部門コード, 部門名, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 残高金額, 計上年, 計上月, 計上日, 仕訳番号, 行番 ) SELECT DISTINCTROW 部門科目細目別期首残高_補助元帳用.集計キー, 部門科目細目別期首残高_補助元帳用.部門コード, 部門科目細目別期首残高_補助元帳用.部門名, ' ', '残　　高', ' ', ' ', 部門科目細目別期首残高_補助元帳用.残高金額, 9999 AS 計上年, 99 AS 計上月, 99 AS 計上日, 999999 AS 仕訳番号, 99 AS 行番 FROM 部門科目細目別期首残高_補助元帳用 WHERE (((部門科目細目別期首残高_補助元帳用.集計キー)>='" & BUMON & SAI_SEL & "' And (部門科目細目別期首残高_補助元帳用.集計キー)<='" & BUMON & SAI_SEL & "999999" & "'));"
	    ElseIf ACSEL(1) = " " And ACSEL(2) = " " And ACSEL(3) = " " And ACSEL(4) = " " And ACSEL(5) = " " And ACSEL(6) = " " Then
	        qdf.SQL = "INSERT INTO 元帳トラン ( 集計キー, 部門コード, 部門名, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 残高金額, 計上年, 計上月, 計上日, 仕訳番号, 行番 ) SELECT DISTINCTROW 部門科目細目別期首残高_補助元帳用.集計キー, 部門科目細目別期首残高_補助元帳用.部門コード, 部門科目細目別期首残高_補助元帳用.部門名, ' ', '残　　高', ' ', ' ', 部門科目細目別期首残高_補助元帳用.残高金額, 9999 AS 計上年, 99 AS 計上月, 99 AS 計上日, 999999 AS 仕訳番号, 99 AS 行番 FROM 部門科目細目別期首残高_補助元帳用 WHERE (((部門科目細目別期首残高_補助元帳用.集計キー)>='" & BUMON & AC_FROM & "' And (部門科目細目別期首残高_補助元帳用.集計キー)<='" & BUMON & AC_TO & "999999" & "'));"
	    Else
	        KAMOKU_JOKEN = "(部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(1) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(1) & "ZZZZZZ') OR (部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(2) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(2) & "ZZZZZZ') OR (部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(3) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(3) & "ZZZZZZ') OR (部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(4) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(4) & "ZZZZZZ') OR (部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(5) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(5) & "ZZZZZZ') OR (部門科目細目別期首残高_補助元帳用.集計キー >= '" & BUMON & ACSEL_2(6) & "' AND 部門科目細目別期首残高_補助元帳用.集計キー <= '" & BUMON & ACSEL_2(6) & "ZZZZZZ')"
	        qdf.SQL = "INSERT INTO 元帳トラン ( 集計キー, 部門コード, 部門名, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 残高金額, 計上年, 計上月, 計上日, 仕訳番号, 行番 ) SELECT DISTINCTROW 部門科目細目別期首残高_補助元帳用.集計キー, 部門科目細目別期首残高_補助元帳用.部門コード, 部門科目細目別期首残高_補助元帳用.部門名, ' ', '残　　高', ' ', ' ', 部門科目細目別期首残高_補助元帳用.残高金額, 9999 AS 計上年, 99 AS 計上月, 99 AS 計上日, 999999 AS 仕訳番号, 99 AS 行番 FROM 部門科目細目別期首残高_補助元帳用 WHERE ((" & KAMOKU_JOKEN & "));"
	    End If
	    qdf.Close
	    DoCmd.OpenQuery "元帳残高追加"*/
	    if(!SAI_SEL.equals("")){
	    	QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode,department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ','残　　高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE (((department_couses_details_by_opening_balance_auxuliary_ledger.keycode)>= '"+ BUMON + SAI_SEL +"' And (department_couses_details_by_opening_balance_auxuliary_ledger.keycode) <= '"+ BUMON + SAI_SEL + "999999" +"'))");
	    } else if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode,department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ','残　　高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE (((department_couses_details_by_opening_balance_auxuliary_ledger.keycode)>= '"+ BUMON + AC_FROM +"' And (department_couses_details_by_opening_balance_auxuliary_ledger.keycode) <= '"+ BUMON + AC_TO   + "999999" +"'))");
	    } else {
	    	//KAMOKU_JOKEN = "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[0] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[0] +" ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[1] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[1] +" ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[2] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[2] +" ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[3] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[3] +" ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[4] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[4] +" ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[5] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[5] +" ZZZZZZ')";
	    	KAMOKU_JOKEN ="";
	    	if(!ACSEL[0].equals("")){
	    		KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[0] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[0] +"ZZZZZZ')";
	    	}
	    	if(!ACSEL[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[1] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[1] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[1] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[1] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[2] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[2] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[2] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[2] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[3] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[3] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[3] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[3] +"ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[4] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[4] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[4] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[4] +"ZZZZZZ')";
	    		}
	    	}if(!ACSEL[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[5] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[5] +"ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '"+ BUMON + ACSEL[5] +"' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '"+ BUMON + ACSEL[5] +"ZZZZZZ')";
	    		}
	    	}
	    	QueryDefsInsert("INSERT INTO ledger_tran (aggregate_key,devcode,devname,draccode,dracname,dracsubcode,dracsubname,balance,yyyy,mm,dd,je_number,l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode,department_couses_details_by_opening_balance_auxuliary_ledger.devname, '', '残　　高',' ',' ',department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE ("+ KAMOKU_JOKEN +")");
	    }
	    //==================================Step 3==========================================
	    //PRJCODEのNULLを空白に
	    /*Set qdf = dbs.CreateQueryDef("", "UPDATE 仕訳テーブル SET 仕訳テーブル.PRJCODE = '' WHERE 仕訳テーブル.PRJCODE Is Null;")
	    qdf.Execute
	    qdf.Close*/
	    QueryDefsUpdate("UPDATE journals SET journals.PRJCODE = '' WHERE journals.prjcode Is Null OR journals.prjcode = 'null'");
	    //==================================Step 4==========================================
	    //借方明細を元帳トランに追加
	    /*Set qdf = dbs.QueryDefs("借方科目細目別元帳データ")
	    SQL_TEMP = "INSERT INTO 元帳トラン ( 部門コード, 部門名, 仕訳番号,伝票番号, 行番, 計上年, 計上月, 計上日, 借方金額, 貸方金額, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 摘要コード, 摘要名, 集計キー, 諸口判断,取引先コード,取引先名,号数, 借方消費税区分, 貸方消費税区分, PRJCODE, PRJNAME ) SELECT DISTINCTROW 仕訳テーブル.部門コード, 部門テーブル.部門名, 仕訳テーブル.仕訳番号,仕訳テーブル.伝票番号, 仕訳テーブル.行番, 仕訳テーブル.計上年, 仕訳テーブル.計上月, 仕訳テーブル.計上日, 仕訳テーブル.借方金額, 0 AS 貸方金額, 仕訳テーブル.貸方科目コード AS 相手科目コード, 貸方部門科目細目テーブル.科目名 AS 相手科目名, 仕訳テーブル.貸方細目コード AS 相手細目コード, 貸方部門科目細目テーブル.細目名 AS 相手細目名, 仕訳テーブル.摘要コード, 仕訳テーブル.摘要名, 仕訳テーブル.借方キー"
	    If SAI_SEL <> " " Then
	        qdf.SQL = SQL_TEMP & ", 仕訳テーブル.諸口判断, 仕訳テーブル.取引先コード, 仕訳テーブル.取引先名, 仕訳テーブル.号数, 借方消費税区分, 貸方消費税区分, PRJCODE, PRJNAME FROM 部門テーブル RIGHT JOIN (貸方部門科目細目テーブル RIGHT JOIN 仕訳テーブル ON 貸方部門科目細目テーブル.集計キー = 仕訳テーブル.貸方キー) ON 部門テーブル.部門コード = 仕訳テーブル.部門コード WHERE ((((仕訳テーブル.借方キー)>='" & BUMON & SAI_SEL & "' And (仕訳テーブル.借方キー)<='" & BUMON & SAI_SEL & "999999" & "') AND (([計上年]*10000+[計上月]*100+[計上日])>=" & Format(YMD_FROM) & " And ([計上年]*10000+[計上月]*100+[計上日])<=" & Format(YMD_TO) & "))) AND PRJCODE LIKE '" & Forms!元帳作成!PRJCODE & "*' ;"
	    ElseIf ACSEL(1) = " " And ACSEL(2) = " " And ACSEL(3) = " " And ACSEL(4) = " " And ACSEL(5) = " " And ACSEL(6) = " " Then
	        qdf.SQL = SQL_TEMP & ", 仕訳テーブル.諸口判断, 仕訳テーブル.取引先コード, 仕訳テーブル.取引先名, 仕訳テーブル.号数, 借方消費税区分, 貸方消費税区分, PRJCODE, PRJNAME FROM 部門テーブル RIGHT JOIN (貸方部門科目細目テーブル RIGHT JOIN 仕訳テーブル ON 貸方部門科目細目テーブル.集計キー = 仕訳テーブル.貸方キー) ON 部門テーブル.部門コード = 仕訳テーブル.部門コード WHERE ((((仕訳テーブル.借方キー)>='" & BUMON & AC_FROM & "' And (仕訳テーブル.借方キー)<='" & BUMON & AC_TO & "999999" & "') AND (([計上年]*10000+[計上月]*100+[計上日])>=" & Format(YMD_FROM) & " And ([計上年]*10000+[計上月]*100+[計上日])<=" & Format(YMD_TO) & "))) AND PRJCODE LIKE '" & Forms!元帳作成!PRJCODE & "*' ;"
	    Else
	        KAMOKU_JOKEN = "((仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(1) & "' AND 仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(1) & "ZZZZZZ') OR (仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(2) & "' AND 仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(2) & "ZZZZZZ') OR (仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(3) & "' AND 仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(3) & "ZZZZZZ') OR (仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(4) & "' AND 仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(4) & "ZZZZZZ') OR (仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(5) & "' AND 仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(5) & "ZZZZZZ') OR (仕訳テーブル.借方キー >= '" & BUMON & ACSEL_2(6) & "' AND 仕訳テーブル.借方キー <= '" & BUMON & ACSEL_2(6) & "ZZZZZZ')) AND PRJCODE LIKE '" & Forms!元帳作成!PRJCODE & "*'"
	        qdf.SQL = SQL_TEMP & ", 仕訳テーブル.諸口判断, 仕訳テーブル.取引先コード, 仕訳テーブル.取引先名, 仕訳テーブル.号数, 借方消費税区分, 貸方消費税区分, PRJCODE, PRJNAME FROM 部門テーブル RIGHT JOIN (貸方部門科目細目テーブル RIGHT JOIN 仕訳テーブル ON 貸方部門科目細目テーブル.集計キー = 仕訳テーブル.貸方キー) ON 部門テーブル.部門コード = 仕訳テーブル.部門コード WHERE ((" & KAMOKU_JOKEN & ") AND (([計上年]*10000+[計上月]*100+[計上日])>=" & Format(YMD_FROM) & " And ([計上年]*10000+[計上月]*100+[計上日])<=" & Format(YMD_TO) & ") AND (借方金額<>0));"
	    End If
	    qdf.Close
	    DoCmd.OpenQuery "借方科目細目別元帳データ"*/
	    
	    SQL_TEMP = "INSERT INTO ledger_tran (devcode,devname,je_number,s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode,desname,aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax, prjcode, prjname) SELECT DISTINCTROW journals.devcode,devtables.devname,journals.je_number,journals.s_number,journals.l_number,journals.yyyy,journals.mm,journals.dd,journals.dramount, 0 AS cramount,journals.craccode AS craccode,credit_department_subjects_specific.acname AS acname,journals.cracsubcode AS cracsubcode,credit_department_subjects_specific.acsubname AS acsubname,journals.descode,journals.desname,journals.drkey";
	    if(!SAI_SEL.equals("")){
	    	SQL_TEMP += ",journals.sundry,journals.vendorcode,journals.vendorname,journals.otherdata,drctax,crctax, prjcode, prjname FROM devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode LEFT JOIN credit_department_subjects_specific ON journals.drkey = credit_department_subjects_specific.aggregate_key WHERE ((journals.crkey>='"+ BUMON + SAI_SEL /*+"' And journals.crkey<='"+ BUMON + SAI_SEL + "999999" */+ "') AND (yyyy * 10000 + mm * 100 + dd)>= "+ YMD_FROM + " And (yyyy * 10000 + mm * 100 + dd)<="+ YMD_TO +") AND PRJCODE LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] + "%'";
	    } else if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
	    	SQL_TEMP += ",journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata,drctax,crctax,prjcode,prjname FROM devtables RIGHT JOIN (credit_department_subjects_specific RIGHT JOIN journals ON credit_department_subjects_specific.aggregate_key = journals.crkey) ON devtables.devcode = journals.devcode WHERE ((((journals.drkey)>='" + BUMON + AC_FROM /*+ "' AND (journals.drkey)<='" + BUMON + AC_TO + "999999" */+ "') AND (yyyy*10000+mm*100+dd >=" + YMD_FROM + " AND yyyy*10000+mm*100+dd <=" + YMD_TO + "))) AND prjcode LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] +"%'";
	    } else{
	    	//KAMOKU_JOKEN = "((journals.drkey >= '"+ BUMON + ACSEL_2[0] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[0] + "ZZZZZZ') OR (journals.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[1] + "ZZZZZZ') OR (journals.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[2] + "ZZZZZZ') OR (journals.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[3] + "ZZZZZZ') OR (journals.drkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[4] + "ZZZZZZ') OR (journals.drkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[5] + "ZZZZZZ')) AND PRJCODE LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] +"%'";
	    	KAMOKU_JOKEN ="(";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(journals.drkey >= '"+ BUMON + ACSEL_2[0] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[0] + "ZZZZZZ')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[1] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.drkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[1] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[2] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.drkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[2] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[3] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.drkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[3] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(journals.drkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[4] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (journals.drkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[4] + "ZZZZZZ')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(journals.drkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (journals.drkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.drkey <= '"+ BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    		}
	    	}
	    	KAMOKU_JOKEN +=") AND PRJCODE LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] +"%'";
	    	SQL_TEMP += ",journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata,drctax,crctax,prjcode,prjname FROM devtables RIGHT JOIN (credit_department_subjects_specific RIGHT JOIN journals ON credit_department_subjects_specific.aggregate_key = journals.crkey) ON devtables.devcode = journals.devcode WHERE ((" + KAMOKU_JOKEN + ") AND ((yyyy*10000+mm*100+dd) >=" + YMD_FROM + " AND (yyyy*10000+mm*100+dd)<=" + YMD_TO + ") AND (dramount<>0))";
	    }
	    QueryDefsInsert(SQL_TEMP);
	    
	    //==================================Step 5==========================================
	    //貸方明細を元帳トランに追加
	    /*Set qdf = dbs.QueryDefs("貸方科目細目別元帳データ")
	    SQL_TEMP = "INSERT INTO 元帳トラン ( 部門コード, 部門名, 仕訳番号,伝票番号, 行番, 計上年, 計上月, 計上日, 貸方金額, 借方金額, 借方科目コード, 借方科目名, 借方細目コード, 借方細目名, 摘要コード, 摘要名, 集計キー, 諸口判断,取引先コード,取引先名,号数, 借方消費税区分, 貸方消費税区分, PRJCODE, PRJNAME ) SELECT DISTINCTROW 仕訳テーブル.部門コード, 部門テーブル.部門名, 仕訳テーブル.仕訳番号,仕訳テーブル.伝票番号, 仕訳テーブル.行番, 仕訳テーブル.計上年, 仕訳テーブル.計上月, 仕訳テーブル.計上日, 仕訳テーブル.貸方金額, 0 AS 借方金額, 仕訳テーブル.借方科目コード AS 相手科目コード, 借方部門科目細目テーブル.科目名 AS 相手科目名, 仕訳テーブル.借方細目コード AS 相手細目コード, 借方部門科目細目テーブル.細目名 AS 相手細目名, 仕訳テーブル.摘要コード, 仕訳テーブル.摘要名, 仕訳テーブル.貸方キー"
	    If SAI_SEL <> " " Then
	        qdf.SQL = SQL_TEMP & ", 仕訳テーブル.諸口判断, 仕訳テーブル.取引先コード, 仕訳テーブル.取引先名, 仕訳テーブル.号数, 借方消費税区分, 貸方消費税区分, PRJCODE, PRJNAME FROM (部門テーブル RIGHT JOIN 仕訳テーブル ON 部門テーブル.部門コード = 仕訳テーブル.部門コード) LEFT JOIN 借方部門科目細目テーブル ON 仕訳テーブル.借方キー = 借方部門科目細目テーブル.集計キー WHERE ((((仕訳テーブル.貸方キー)>='" & BUMON & SAI_SEL & "' And (仕訳テーブル.貸方キー)<='" & BUMON & SAI_SEL & "999999" & "') AND (([計上年]*10000+[計上月]*100+[計上日])>=" & Format(YMD_FROM) & " And ([計上年]*10000+[計上月]*100+[計上日])<=" & Format(YMD_TO) & "))) AND PRJCODE LIKE '" & Forms!元帳作成!PRJCODE & "*'"
	    ElseIf ACSEL(1) = " " And ACSEL(2) = " " And ACSEL(3) = " " And ACSEL(4) = " " And ACSEL(5) = " " And ACSEL(6) = " " Then
	        qdf.SQL = SQL_TEMP & ", 仕訳テーブル.諸口判断, 仕訳テーブル.取引先コード, 仕訳テーブル.取引先名, 仕訳テーブル.号数, 借方消費税区分, 貸方消費税区分, PRJCODE, PRJNAME FROM (部門テーブル RIGHT JOIN 仕訳テーブル ON 部門テーブル.部門コード = 仕訳テーブル.部門コード) LEFT JOIN 借方部門科目細目テーブル ON 仕訳テーブル.借方キー = 借方部門科目細目テーブル.集計キー WHERE ((((仕訳テーブル.貸方キー)>='" & BUMON & AC_FROM & "' And (仕訳テーブル.貸方キー)<='" & BUMON & AC_TO & "999999" & "') AND (([計上年]*10000+[計上月]*100+[計上日])>=" & Format(YMD_FROM) & " And ([計上年]*10000+[計上月]*100+[計上日])<=" & Format(YMD_TO) & "))) AND PRJCODE LIKE '" & Forms!元帳作成!PRJCODE & "*'"
	    Else
	        KAMOKU_JOKEN = "((仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(1) & "' AND 仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(1) & "ZZZZZZ') OR (仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(2) & "' AND 仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(2) & "ZZZZZZ') OR (仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(3) & "' AND 仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(3) & "ZZZZZZ') OR (仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(4) & "' AND 仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(4) & "ZZZZZZ') OR (仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(5) & "' AND 仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(5) & "ZZZZZZ') OR (仕訳テーブル.貸方キー >= '" & BUMON & ACSEL_2(6) & "' AND 仕訳テーブル.貸方キー <= '" & BUMON & ACSEL_2(6) & "ZZZZZZ')) AND PRJCODE LIKE '" & Forms!元帳作成!PRJCODE & "*'"
	        qdf.SQL = SQL_TEMP & ", 仕訳テーブル.諸口判断, 仕訳テーブル.取引先コード, 仕訳テーブル.取引先名, 仕訳テーブル.号数, 借方消費税区分, 貸方消費税区分, PRJCODE, PRJNAME FROM (部門テーブル RIGHT JOIN 仕訳テーブル ON 部門テーブル.部門コード = 仕訳テーブル.部門コード) LEFT JOIN 借方部門科目細目テーブル ON 仕訳テーブル.借方キー = 借方部門科目細目テーブル.集計キー WHERE ((" & KAMOKU_JOKEN & ") AND (([計上年]*10000+[計上月]*100+[計上日])>=" & Format(YMD_FROM) & " And ([計上年]*10000+[計上月]*100+[計上日])<=" & Format(YMD_TO) & ") AND (貸方金額<>0));"
	    End If
	    qdf.Close
	    DoCmd.OpenQuery "貸方科目細目別元帳データ"*/
	    
    	SQL_TEMP = "INSERT INTO ledger_tran (devcode, devname, je_number, s_number,l_number,yyyy,mm,dd,cramount,dramount,draccode,dracname,dracsubcode,dracsubname,descode,desname, aggregate_key,sundry,vendorcode,vendorname,otherdata,drctax,crctax,prjcode,prjname) SELECT DISTINCTROW journals.devcode, devtables.devname, journals.je_number,journals.s_number, journals.l_number, journals.yyyy,journals.mm, journals.dd, journals.cramount, 0 AS dramount, journals.draccode AS partner_subject_code, debit_department_subjects_specific.acname AS acname, journals.dracsubcode AS dracsubcode, debit_department_subjects_specific.acsubname AS acsubname,journals.descode, journals.desname, journals.crkey";
    	if(!SAI_SEL.equals("")){
    		SQL_TEMP += ",journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata,drctax,crctax,prjcode,prjname FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ((((journals.crkey)>='" + BUMON + SAI_SEL + "' AND (journals.crkey)<='" + BUMON + SAI_SEL + "999999" + "') AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + "))) AND prjcode LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] +"%'";
    	}else if(ACSEL[0].equals("") && ACSEL[1].equals("") && ACSEL[2].equals("") && ACSEL[3].equals("") && ACSEL[4].equals("") && ACSEL[5].equals("")){
    	    SQL_TEMP += ",journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata,drctax,crctax,prjcode,prjname FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ((((journals.crkey)>='" + BUMON + AC_FROM + "' AND (journals.crkey)<='" + BUMON + AC_TO + "999999" + "') AND (yyyy*10000+mm*100+dd> =" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + "))) AND prjcode LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] +"%'";
    	} else{
    		//KAMOKU_JOKEN = "((journals.crkey >= '"+ BUMON + ACSEL_2[0] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[0] +"ZZZZZZ') OR (journals.crkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[1] +"ZZZZZZ') OR (journals.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[2] +"ZZZZZZ') OR (journals.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[3] +"ZZZZZZ') OR (journals.crkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[4] +"ZZZZZZ') OR (journals.crkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.crkey <='"+ BUMON + ACSEL_2[5] +"ZZZZZZ')) AND PRJCODE LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] + "%'";
    		KAMOKU_JOKEN ="(";
	    	if(!ACSEL_2[0].equals("")){
	    		KAMOKU_JOKEN += "(journals.crkey >= '"+ BUMON + ACSEL_2[0] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[0] + "ZZZZZZ')";
	    	}
	    	if(!ACSEL_2[1].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.crkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[1] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.crkey >= '"+ BUMON + ACSEL_2[1] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[1] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[2].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[2] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.crkey >= '"+ BUMON + ACSEL_2[2] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[2] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[3].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN += "(journals.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[3] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN += " OR (journals.crkey >= '"+ BUMON + ACSEL_2[3] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[3] + "ZZZZZZ')";
	    		}
	    	}
	    	if(!ACSEL_2[4].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(journals.crkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[4] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (journals.crkey >= '"+ BUMON + ACSEL_2[4] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[4] + "ZZZZZZ')";
	    		}
	    	}if(!ACSEL_2[5].equals("")){
	    		if(KAMOKU_JOKEN.equals("")){
	    			KAMOKU_JOKEN +="(journals.crkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    		} else{
	    			KAMOKU_JOKEN +=" OR (journals.crkey >= '"+ BUMON + ACSEL_2[5] +"' AND journals.crkey <= '"+ BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    		}
	    	}
	    	KAMOKU_JOKEN +=") AND PRJCODE LIKE '"+ String.valueOf(cmbPrjCode.getSelectedItem()).split(" | ")[0] +"%'";
    	    SQL_TEMP += ",journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata,drctax,crctax,prjcode,prjname FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ((" + KAMOKU_JOKEN + ") AND ((yyyy*10000+mm*100+dd) >=" + YMD_FROM + " AND (yyyy*10000+mm*100+dd) <=" + YMD_TO + ") AND (cramount<>0))";
    	}
    	QueryDefsInsert(SQL_TEMP);
    	//==================================Step 6==========================================
    	//元帳テーブルの作成
        /*Set qdf = dbs.QueryDefs("元帳テーブル作成")
        If NARABI = 1 Then
            SQL_TEMP = "ORDER BY 元帳トラン.集計キー, 元帳トラン.仕訳番号, 元帳トラン.行番, 元帳トラン.計上年, 元帳トラン.計上月, 元帳トラン.計上日;"
        Else
            SQL_TEMP = "ORDER BY 元帳トラン.集計キー, 元帳トラン.計上年, 元帳トラン.計上月, 元帳トラン.計上日, 元帳トラン.仕訳番号, 元帳トラン.行番;"
        End If
        qdf.SQL = "SELECT DISTINCTROW 元帳トラン.集計キー, 科目テーブル.科目コード AS 対象科目コード, 科目テーブル.科目名 AS 対象科目名, 科目テーブル.細目コード AS 対象細目コード, 科目テーブル.細目名 AS 対象細目名, 元帳トラン.部門コード, 元帳トラン.部門名, 元帳トラン.仕訳番号,元帳トラン.伝票番号,元帳トラン.行番, 元帳トラン.計上年, 元帳トラン.計上月, 元帳トラン.計上日, 元帳トラン.借方科目コード, 元帳トラン.借方科目名, 元帳トラン.借方細目コード, 元帳トラン.借方細目名, 元帳トラン.借方金額, 元帳トラン.貸方金額, 元帳トラン.残高金額, 元帳トラン.摘要コード, 元帳トラン.摘要名, 元帳トラン.諸口判断, 元帳トラン.取引先コード, 元帳トラン.取引先名, 元帳トラン.号数, 元帳トラン.借方消費税区分, 元帳トラン.貸方消費税区分, 元帳トラン.PRJCODE, 元帳トラン.PRJNAME INTO 元帳テーブル FROM 元帳トラン INNER JOIN 科目テーブル ON 元帳トラン.集計キー = 科目テーブル.集計キー " & SQL_TEMP
        qdf.Close
        If OBJ_EXIST("元帳テーブル") = 2 Then
            DoCmd.DeleteObject acTable, "元帳テーブル"
        End If
        DoCmd.OpenQuery "元帳テーブル作成"*/
         
    	if(NARABI == 1){
    		SQL_TEMP = "ORDER BY ledger_tran.aggregate_key,ledger_tran.je_number,ledger_tran.l_number,ledger_tran.yyyy,ledger_tran.mm,ledger_tran.dd";
    	} else{
    	    SQL_TEMP = "ORDER BY ledger_tran.aggregate_key,ledger_tran.yyyy,ledger_tran.mm,ledger_tran.dd,ledger_tran.je_number,ledger_tran.l_number";
    	}
    	StringBuffer SQL = new StringBuffer("INSERT INTO ledger(aggregate_key, accode, acname, acsubcode, acsubname, devcode, devname, je_number, s_number, l_number, yyyy, mm, dd, draccode, dracname, dracsubcode, dracsubname, dramount, cramount, balance, descode, desname, sundry, vendorcode, vendorname, otherdata,drctax,crctax,prjcode,prjname) SELECT DISTINCTROW ledger_tran.aggregate_key, actables.accode AS accode, actables.acname AS acname, actables.acsubcode AS acsubcode, actables.acsubname AS acsubname,ledger_tran.devcode, ledger_tran.devname, ledger_tran.je_number,ledger_tran.s_number,ledger_tran.l_number, ledger_tran.yyyy, ledger_tran.mm, ledger_tran.dd, ledger_tran.draccode, ledger_tran.dracname,ledger_tran.dracsubcode, ledger_tran.dracsubname, ledger_tran.dramount, ledger_tran.cramount, ledger_tran.balance, ledger_tran.descode, ledger_tran.desname, ledger_tran.sundry, ledger_tran.vendorcode,ledger_tran.vendorname, ledger_tran.otherdata, ledger_tran.drctax,ledger_tran.crctax,ledger_tran.prjcode, ledger_tran.prjname FROM ledger_tran INNER JOIN actables ON ledger_tran.aggregate_key = actables.keycode " + SQL_TEMP);
    	
    	TableDefs(SQL,"ledger");
	    //DoCmd.SetWarnings True
	    
	    //明細行がない科目をカット
	    /*If Forms!元帳作成!PRJOKEN = 1 Then
	        GYOHAN = 100
	        Set rst = dbs.OpenRecordset("元帳テーブル", dbOpenDynaset)
	        Do Until rst.EOF
	            If Right(Format(rst!仕訳番号), 4) = "9999" And GYOHAN = 0 Then
	                rst.MovePrevious
	                rst.Edit
	                rst!号数 = "削除データ"
	                rst.Update
	                rst.MoveNext
	                rst.Edit
	                rst!号数 = "削除データ"
	                rst.Update
	            End If
	            GYOHAN = rst!仕訳番号
	            rst.MoveNext
	        Loop
	        rst.Close
	    End If*/
    	String origCode = getLedgerWithoutDetail();
    	/*List<Ledger> ledgersUpdated = new ArrayList<Ledger>();
    	if(cmbPrjoken.getSelectedItem().equals("1" + " | " + "明細がないページを印刷しない")){
    		GYOHAN = 100;
    		List<Ledger> ledgers = getLedger();    			
    		int ldgSize = ledgers.size();
    		for(int i=0;i<ldgSize;i++){
    			Ledger ldg = ledgers.get(i);
    			if((Right(String.valueOf(ldg.getJe_number()),4).equals("9999")) && (GYOHAN == 0)){
    				if(i==0){
    					//last record
    					ldg = ledgers.get(ldgSize-1);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				 	//current record
    				 	ldg = ledgers.get(i);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				} else if(i==(ldgSize-1)){
    					//first record
    					ldg = ledgers.get(0);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				 	//current record
    				 	ldg = ledgers.get(i);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				} else{
    					//rst.MovePrevious
    					ldg = ledgers.get(i-1);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				 	//rst.MoveNext
    				 	ldg = ledgers.get(i);
    				 	ldg.setOtherdata("削除データ");
    				 	ledgersUpdated.add(ldg);
    				}
    			}
    			ledgersUpdated.add(ldg);
    			GYOHAN = ldg.getJe_number();
    		}
    	}*/
    		
	    /*Set qdf = dbs.CreateQueryDef("", "delete * from 元帳テーブル where 号数='削除データ';")
	    qdf.Execute
	    qdf.Close*/
    	//for(Ledger ldg: ledgersUpdated){
    		//if((ldg.getOtherdata()!=null)&&(ldg.getOtherdata().equals("削除データ"))){
    	String sqlLdgDel = "";
	    	if(!origCode.equals("")){
	    		sqlLdgDel = "DELETE FROM ledger WHERE ((je_number=0 OR je_number=999999) AND aggregate_key NOT IN("+origCode+"))";
	    	} else{
	    		sqlLdgDel = "DELETE FROM ledger WHERE (je_number=0 OR je_number=999999)";
	    	}
	    	QueryDefsUpdate(sqlLdgDel);
    		//}
    	//}
    	
	    
	    //残高金額及び諸口の処理
	    /*Set rst = dbs.OpenRecordset("元帳テーブル", dbOpenDynaset)
	    Do Until rst.EOF
	        If rst!借方科目名 = "前繰残高" Then
	            ZANDAKA_TRAN = Nz(rst!残高金額)
	        ElseIf rst!借方科目名 = "残　　高" Then
	        Else
	            ZANDAKA_TRAN = ZANDAKA_TRAN + Nz(rst!借方金額) - Nz(rst!貸方金額)
	            rst.Edit
	            rst!残高金額 = ZANDAKA_TRAN
	            rst.Update
	        End If
	        If rst!諸口判断 = 1 Then
	            rst.Edit
	            rst!借方科目コード = "    "
	            rst!借方科目名 = "諸口"
	            rst!借方細目コード = "    "
	            rst!借方細目名 = "    "
	            rst.Update
	        End If
	        rst.MoveNext
	    Loop
	    rst.Close*/
    	
    	List<Ledger> ledgers = getLedger(); 
    	for(Ledger ldg:ledgers){
    		if(ldg.getDracname().equals("前繰残高")){
    			ZANDAKA_TRAN = ldg.getBalance();
    		} else if(ldg.getDracname().equals("残　　高")){
    			
    		} else{
    			ZANDAKA_TRAN = ZANDAKA_TRAN + ldg.getDramount() - ldg.getCramount();
    			String sqlDel = "UPDATE ledger SET balance="+ZANDAKA_TRAN+" WHERE aggregate_key='"+ldg.getAggregate_key()+"' AND je_number="+ldg.getJe_number();
    			QueryDefsUpdate(sqlDel);
    		}
    		if(ldg.getSundry() == 1){
    			String sqlDel = "UPDATE ledger SET draccode='    ',dracname='諸口',dracsubcode='    ',dracsubname='    ' WHERE aggregate_key='"+ldg.getAggregate_key()+"' AND je_number="+ldg.getJe_number();
    			QueryDefsUpdate(sqlDel);
    		}
    	}    	
	    
	    //元帳レポートを開く
	    /*If NARABI = 1 Then
	        JOKEN = "入力順"
	    ElseIf NARABI = 2 Then
	        JOKEN = "日付順"
	    End If*/
    	if(NARABI == 1){
    		JOKEN = "入力順";
    	} else if(NARABI == 2){
    		JOKEN = "日付順";
    	}
    	
	    /*Set rst = dbs.OpenRecordset("レポートパラメータ", dbOpenDynaset)
	    rst.Edit
	    rst!元帳抽出条件 = "抽出条件　部門" & BUMON & "  " & Format(NEN_FROM) & "年" & Format(TUKI_FROM) & "月" & Format(HI_FROM) & "日から" & Format(NEN_TO) & "年" & Format(TUKI_TO) & "月" & Format(HI_TO) & "日まで・" & JOKEN
	    rst.Update
	    rst.Close*/
    	boolean fo = false;
		try {
			DBManager db = new DBManager();
			
			try {
				String sqlSt = "SELECT * FROM reportparameters";
				
				ResultSet rs = db.getRecord(sqlSt);
				
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
    	
    	String ledger_extrantion_cond ="抽出条件　部門" + BUMON + "  " + NEN_FROM + "年" + TUKI_FROM + "月" + HI_FROM + "日から" + NEN_TO + "年" + TUKI_TO + "月" + HI_TO + "日まで・" + JOKEN;
    	try {
			DBManager dbc = new DBManager();
			try{
				if(fo) {
					PreparedStatement pstm = dbc.getPreparStamt("UPDATE reportparameters SET ledger_extrantion_cond = '" + ledger_extrantion_cond+ "'");
					pstm.executeUpdate();
				} else {
					PreparedStatement pstm = dbc.getPreparStamt("INSERT INTO reportparameters(ledger_extrantion_cond) VALUES(?)");
					
					pstm.setString(1, ledger_extrantion_cond);
					pstm.executeUpdate();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
	    /*If NARABI = 1 Then
	        DoCmd.SetWarnings False
	        DoCmd.OpenReport "補助元帳", acDesign
	        Reports!補助元帳!ラベル36.Caption = "総勘定元帳"
	        DoCmd.Save acReport, "補助元帳"
	        DoCmd.Close acReport, "補助元帳"
	        DoCmd.SetWarnings True
	        DoCmd.OpenReport "補助元帳", acPreview
	    ElseIf NARABI = 2 Then
	        DoCmd.SetWarnings False
	        DoCmd.OpenReport "補助元帳2", acDesign
	        Reports!補助元帳2!ラベル36.Caption = "総勘定元帳"
	        DoCmd.Save acReport, "補助元帳2"
	        DoCmd.Close acReport, "補助元帳2"
	        DoCmd.SetWarnings True
	        DoCmd.OpenReport "補助元帳2", acPreview
	    End If*/
    	String param="";
    	try {
			DBManager db = new DBManager();
			
			try {
				String sqlRptParam = "SELECT DISTINCTROW reportparameters.ledger_extrantion_cond FROM reportparameters";
				
				ResultSet rs = db.getRecord(sqlRptParam);

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
    	
    	if(NARABI == 1){
    		param = param + "~" + "補助元帳";
    		CallReport("subledger",param);
    	} else if(NARABI == 2){
    		param = param + "~" + "補助元帳";
    		CallReport("subledger2",param);
    	}
	}
	
	public List<Ledger> getLedger(){
		List<Ledger> ledgers = new ArrayList<Ledger>();
		
		try {
			DBManager dbC = new DBManager();
			ResultSet rs = null;
			String sqlLdg = "SELECT * FROM ledger";
			try {
				StringBuffer strSql = new StringBuffer(sqlLdg);
				//System.out.println(strSql);
				rs = dbC.getRecord(strSql.toString());
				
				while(rs.next()){
					Ledger ledger = new Ledger();
										
					ledger.setAggregate_key(rs.getString("aggregate_key"));
					ledger.setAccode(rs.getString("accode"));
					ledger.setAcname(rs.getString("acname"));
					ledger.setAcsubcode(rs.getString("acsubcode"));
					ledger.setAcsubname(rs.getString("acsubname"));
					ledger.setDevcode(rs.getString("devcode"));
					ledger.setDevname(rs.getString("devname"));
					ledger.setJe_number(rs.getLong("je_number"));
					ledger.setS_number(rs.getLong("s_number"));
					ledger.setL_number(rs.getLong("l_number"));
					ledger.setYyyy(rs.getInt("yyyy"));
					ledger.setMm(rs.getInt("mm"));
					ledger.setDd(rs.getInt("dd"));
					ledger.setDraccode(rs.getString("draccode"));
					ledger.setDracname(rs.getString("dracname"));
					ledger.setDracsubcode(rs.getString("dracsubcode"));
					ledger.setDracsubname(rs.getString("dracsubname"));
					ledger.setDramount(rs.getDouble("dramount"));
					ledger.setCramount(rs.getDouble("cramount"));
					ledger.setBalance(rs.getDouble("balance"));
					ledger.setDescode(rs.getString("descode"));
					ledger.setDesname(rs.getString("desname"));
					ledger.setSundry(rs.getInt("sundry"));
					ledger.setVendorcode(rs.getString("vendorcode"));
					ledger.setVendorname(rs.getString("vendorname"));
					ledger.setOtherdata(rs.getString("otherdata"));
					ledger.setDrctax(rs.getString("drctax"));
					ledger.setCrctax(rs.getString("crctax"));
					ledger.setPrjcode(rs.getString("prjcode"));
					ledger.setPrjname(rs.getString("prjname"));
					
					ledgers.add(ledger);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ledgers;
	}
	
	public void GENKIN_CHECK() {
		String BUMON;
		long NEN_FROM;
		long TUKI_FROM;
		long HI_FROM;
		long NEN_TO;
		long TUKI_TO;
		long HI_TO;
		long KESSAN_NEN = 0;
		long KESSAN_TUKI = 0;
		long YMD_FROM;
		long YMD_TO;
		long KISHU;
		int dum;
		String GENKIN_AC;

		// フォームの初期値算定
		// NULL値のチェック
		if (cmbBumon.getSelectedItem().equals("")) {
			BUMON = "";
		} else {
			// フォームからデータを取得
			BUMON = String.valueOf(cmbBumon.getSelectedItem()).split(" | ")[0];
		}
		if (txtTukiFrom.getText().equals("")) {
			txtTukiFrom.setText("4");
			TUKI_FROM = 4;
		} else {
			// フォームからデータを取得
			TUKI_FROM = Long.parseLong(txtTukiFrom.getText());
		}
		if (txtTukiTo.getText().equals("")) {
			txtTukiTo.setText("4");
			TUKI_TO = 4;
		} else {
			// フォームからデータを取得
			TUKI_TO = Long.parseLong(txtTukiTo.getText());
		}
		if (txtHiFrom.getText().equals("")) {
			txtHiFrom.setText("1");
			HI_FROM = 1;
		} else {
			// フォームからデータを取得
			HI_FROM = Long.parseLong(txtHiFrom.getText());
		}
		if (txtHiTo.getText().equals("")) {
			txtHiTo.setText("31");
			HI_TO = 31;
		} else {
			// フォームからデータを取得
			HI_TO = Long.parseLong(txtHiTo.getText());
		}

		// フォームからデータを取得
		YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
		YMD_TO = YM_GET(TUKI_TO, HI_TO);
		NEN_FROM = YMD_FROM / 10000;
		NEN_TO = YMD_TO / 10000;
		
		try {
			DBManager db = new DBManager();
			try {
				String sql = "SELECT * FROM basedatas";

				ResultSet rs = db.getRecord(sql);
				if(rs.next()) {
					KESSAN_NEN = rs.getInt(2);
					KESSAN_TUKI = rs.getInt(3);
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
		GENKIN_AC = "1110";
		
		String view_name; 
		StringBuffer sql;
		
		view_name = "debit_expense_item_totals_repetitive_remaining";
		sql = new StringBuffer("SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
		QueryDefs(sql, view_name);

		view_name = "credit_expense_item_totals_repetitive_remaining";
		sql = new StringBuffer("SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
		QueryDefs(sql, view_name);
		
		view_name = "balance_before_provision";
		sql = new StringBuffer("SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0)+ IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode = '"+BUMON+"'");
		QueryDefs(sql, view_name);
		
		view_name = "debit_expense_item_totals";
		sql = new StringBuffer("SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="+ YMD_FROM +" AND yyyy * 10000 + mm * 100 + dd <="+ YMD_TO +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
		QueryDefs(sql, view_name);
	        
		view_name = "credit_expense_item_totals";
		sql = new StringBuffer("SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="+ YMD_FROM +" AND yyyy * 10000 + mm * 100 + dd <="+ YMD_TO +" AND journals.devcode ='"+ BUMON +"' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null))");
		QueryDefs(sql, view_name);
		
		view_name = "cash_check_table";
		sql = new StringBuffer("SELECT DISTINCTROW LEFT(devcode,3) AS department, MAX(department_couses_details_by_opening_balance_auxuliary_ledger.devname) AS max_department_name, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision) AS previous_balance_sum, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.debit_amount) AS debit_amount_sum, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.credit_amount) AS credit_amount_sum, IFNULL(SUM(department_couses_details_by_opening_balance_auxuliary_ledger.debit_amount),0) - IFNULL(SUM(department_couses_details_by_opening_balance_auxuliary_ledger.credit_amount),0) AS today_balance, SUM(department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount) AS total_balance_amount,department_couses_details_by_opening_balance_auxuliary_ledger.acsubcode, department_couses_details_by_opening_balance_auxuliary_ledger.acsubname FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.accode = '1110' GROUP BY LEFT(devcode,3),department_couses_details_by_opening_balance_auxuliary_ledger.acsubcode,department_couses_details_by_opening_balance_auxuliary_ledger.acsubname HAVING department = '010';");
		
		
		//DoCmd.OpenReport "現金チェック表", acPreview
		
		String param = "";
		CallReport("rptCashCheck",param);
	}
	
	public void GENKIN_SUITO() {

		int NARABI;
		long NEN_FROM;			//開始年
		long NEN_TO;			//終了年
		long TUKI_FROM;			//開始月
		long TUKI_TO;			//終了月
		long HI_FROM;			//開始日
		long HI_TO;				//終了日
		long YMD_FROM;
		long YMD_TO;
		long KESSAN_NEN;		//決算年
		long KESSAN_TUKI;		//決算月
		String AC_FROM;			//開始科目
		String AC_TO;			//終了科目
		String ACSEL[] = new String[6]; 	//選択科目配列
		String ACSEL_2[] = new String[6];   //選択科目配列
		String SAI_SEL; 					//細目まで指定
		String BUMON;						//部門
		long KISHU = 0; 
		String SHUKEI_KEY;
		String JOKEN ;
		String KAMOKU_JOKEN;
		double ZANDAKA_TRAN = 0;
		String SQL_TEMP;
		int dum;
		
		
		//フォームの初期値算定
        //NULL値のチェック
		if (cmbBumon.getSelectedItem().equals("")) {
			BUMON = "";
		} else {
			BUMON = String.valueOf(cmbBumon.getSelectedItem()).split(" | ")[0];
		}
		if (cmbNarabi.getSelectedItem().equals("")) {
	    	cmbNarabi.setSelectedItem("1" + " | " + "入力順");
	    }
		if (cmbAcFrom.getSelectedItem().equals("")) {
	    	cmbAcFrom.setSelectedItem("0000");
	    }
		if (cmbAcTo.getSelectedItem().equals("")) {
	    	cmbAcTo.setSelectedItem("9999");
	    }
		if (cmbAcSel1.getSelectedItem().equals("")) {
	    	cmbAcSel1.setSelectedItem("");
	    }
	    if (cmbAcSel2.getSelectedItem().equals("")) {
	    	cmbAcSel2.setSelectedItem("");
	    }
	    if (cmbAcSel3.getSelectedItem().equals("")) {
	    	cmbAcSel3.setSelectedItem("");
	    }
	    if (cmbAcSel4.getSelectedItem().equals("")) {
	    	cmbAcSel4.setSelectedItem("");
	    }
	    if (cmbAcSel5.getSelectedItem().equals("")) {
	    	cmbAcSel5.setSelectedItem("");
	    }
	    if (cmbAcSel6.getSelectedItem().equals("")) {
	    	cmbAcSel6.setSelectedItem("");
	    }
	    if (txtTukiFrom.getText().equals("")) {
	    	txtTukiFrom.setText("4");
	    }
	    if (txtTukiTo.getText().equals("")) {
	    	txtTukiTo.setText("4");
	    }
	    if (txtHiFrom.getText().equals("")) {
	    	txtHiFrom.setText("1");
	    }
	    if (txtHiTo.getText().equals("")) {
	    	txtHiTo.setText("31");
	    }
	    if(cmbSaiSel.getSelectedItem().equals("")) {
	    	cmbSaiSel.setSelectedItem("");
	    }
	  //BUMON = Forms!元帳作成!BUMON
	    BUMON = String.valueOf(cmbBumon.getSelectedItem()).split(" | ")[0];
	    NARABI = Integer.parseInt(String.valueOf(cmbNarabi.getSelectedItem()).split(" | ")[0]);
	    AC_FROM = String.valueOf(cmbAcFrom.getSelectedItem()).split(" | ")[0];
	    AC_TO = String.valueOf(cmbAcTo.getSelectedItem()).split(" | ")[0];
	    ACSEL[0] = String.valueOf(cmbAcSel1.getSelectedItem()).split(" | ")[0];
	    ACSEL[1] = String.valueOf(cmbAcSel2.getSelectedItem()).split(" | ")[0];
	    ACSEL[2] = String.valueOf(cmbAcSel3.getSelectedItem()).split(" | ")[0];
	    ACSEL[3] = String.valueOf(cmbAcSel4.getSelectedItem()).split(" | ")[0];
	    ACSEL[4] = String.valueOf(cmbAcSel5.getSelectedItem()).split(" | ")[0];
	    ACSEL[5] = String.valueOf(cmbAcSel6.getSelectedItem()).split(" | ")[0];
	    
	    if(ACSEL[0].equals(" ")){
	        ACSEL_2[0] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[0] = ACSEL[0];
	    }
	    if(ACSEL[1].equals(" ")){ 
	        ACSEL_2[1] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[1] = ACSEL[1];
		}
	    if(ACSEL[2].equals(" ")){
	        ACSEL_2[2] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[2] = ACSEL[2];
	    }
	    if(ACSEL[3].equals(" ")){
	    	ACSEL_2[3] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[3] = ACSEL[3];
	    }
	    if(ACSEL[4].equals(" ")){
	    	ACSEL_2[4] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[4] = ACSEL[4];
	    }
	    if(ACSEL[5].equals(" ")){
	    	ACSEL_2[5] = "ZZZZZZZ";
	    } else {
	        ACSEL_2[5] = ACSEL[5];
	    }
	    SAI_SEL = "1110";
	    
	    TUKI_FROM = Integer.parseInt(txtTukiFrom.getText());
	    TUKI_TO = Integer.parseInt(txtTukiTo.getText());
	    HI_FROM = Integer.parseInt(txtHiFrom.getText());
	    HI_TO = Integer.parseInt(txtHiTo.getText());
	    YMD_FROM = YM_GET(TUKI_FROM, HI_FROM);
	    YMD_TO = YM_GET(TUKI_TO, HI_TO);
	    NEN_FROM = (int)(YMD_FROM / 10000);
	    NEN_TO = (int)(YMD_TO / 10000);
	    
	    dum = TB_Q_SET(BUMON, YMD_FROM, YMD_TO, KISHU);
    
	    //'元帳トランのデータをクリア
	    //DoCmd.OpenQuery "元帳トランクリア"
	    TableClear("ledger_tran");
	    
	    if(SAI_SEL != " ") {
	    	String sql = "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname,' ', '前繰残高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.keycode >='010' + '1110' AND  department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '010' + '1110' + '999999'";
	    	QueryDefsInsert(sql);
	    	
	    } else if(ACSEL[0].equals(" ") && ACSEL[1].equals(" ") && ACSEL[2].equals(" ") && ACSEL[3].equals(" ") && ACSEL[4].equals(" ") && ACSEL[5].equals(" ")) {
	    	String sql = "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number)SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ', '前繰残高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.keycode >='010' + AC_FROM AND  department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '010' + AC_TO + '999999'";
	    	QueryDefsInsert(sql);
	    	//System.out.println(sql);
	    } else {
	    	KAMOKU_JOKEN = "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[0] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[0] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[1] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[1] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[2] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[2] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[3] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[3] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[4] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[4] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[5] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    	String sql = "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ', '前繰残高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_before_provision, 0 AS yyyy, 0 AS mm, 0 AS dd, 0 AS je_number, 0 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE ("+ KAMOKU_JOKEN +");";
	    	QueryDefsInsert(sql);
	    }
	    ///////////////////////2///////////////
	    if(SAI_SEL != " ") {
	    	String sql = "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ', '残　　高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.keycode >='" + BUMON + SAI_SEL + "' AND  department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + SAI_SEL + "999999" + "'";
	    	QueryDefsInsert(sql);
	    } else if(ACSEL[0].equals(" ") && ACSEL[1].equals(" ") && ACSEL[2].equals(" ") && ACSEL[3].equals(" ") && ACSEL[4].equals(" ") && ACSEL[5].equals(" ")) {
	    	String sql = "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ', '残　　高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE department_couses_details_by_opening_balance_auxuliary_ledger.keycode >='" + BUMON + AC_FROM + "' AND  department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + AC_TO + "999999" + "'";
	    	QueryDefsInsert(sql);
	    }else {
	    	KAMOKU_JOKEN = "(department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[0] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[0] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[1] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[1] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[2] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[2] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[3] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[3] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[4] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[4] + "ZZZZZZ') OR (department_couses_details_by_opening_balance_auxuliary_ledger.keycode >= '" + BUMON + ACSEL_2[5] + "' AND department_couses_details_by_opening_balance_auxuliary_ledger.keycode <= '" + BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    	 String sql = "INSERT INTO ledger_tran (aggregate_key, devcode, devname, draccode, dracname, dracsubcode, dracsubname, balance, yyyy, mm, dd, je_number, l_number) SELECT DISTINCTROW department_couses_details_by_opening_balance_auxuliary_ledger.keycode, department_couses_details_by_opening_balance_auxuliary_ledger.devcode, department_couses_details_by_opening_balance_auxuliary_ledger.devname, ' ', '残　　高', ' ', ' ', department_couses_details_by_opening_balance_auxuliary_ledger.balance_amount, 9999 AS yyyy, 99 AS mm, 99 AS dd, 999999 AS je_number, 99 AS l_number FROM department_couses_details_by_opening_balance_auxuliary_ledger WHERE " + KAMOKU_JOKEN + "";
		    QueryDefsInsert(sql);
	    }
	    //////////////////////3//////////////////////////
	    SQL_TEMP = "INSERT INTO ledger_tran (devcode, devname, je_number, s_number,l_number,yyyy,mm,dd,dramount,cramount,draccode,dracname,dracsubcode,dracsubname,descode, desname, aggregate_key,sundry,vendorcode,vendorname,otherdata) SELECT DISTINCTROW journals.devcode, devtables.devname, journals.je_number,journals.s_number, journals.l_number, journals.yyyy,journals.mm, journals.dd, journals.dramount, 0 AS cramount, journals.craccode AS partner_subject_code, credit_department_subjects_specific.acname AS oponent_course_title, journals.cracsubcode AS partner_details_code, credit_department_subjects_specific.acsubname AS partner_details_name,journals.descode, journals.desname, journals.drkey, ";
	    if(SAI_SEL != " ") {
	    	String sql = SQL_TEMP + "journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM devtables RIGHT JOIN (credit_department_subjects_specific RIGHT JOIN journals ON credit_department_subjects_specific.aggregate_key = journals.crkey) ON devtables.devcode = journals.devcode WHERE ((journals.drkey >='" + BUMON + SAI_SEL /*+ "' AND journals.drkey <='" + BUMON + SAI_SEL + "999999" */+ "') AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + "))";
	    	//System.out.println(sql);
	    	QueryDefsInsert(sql);
	    	
	    } else if(ACSEL[0].equals(" ") && ACSEL[1].equals(" ") && ACSEL[2].equals(" ") && ACSEL[3].equals(" ") && ACSEL[4].equals(" ") && ACSEL[5].equals(" ")) {
	    	String sql = SQL_TEMP + " journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM devtables RIGHT JOIN (credit_department_subjects_specific RIGHT JOIN journals ON credit_department_subjects_specific.aggregate_key = journals.crkey) ON devtables.devcode = journals.devcode WHERE ((journals.drkey >='" + BUMON + SAI_SEL + "' AND journals.drkey <='" + BUMON + AC_TO + "999999" + "') AND (yyyy*10000+mm*100+dd>=" + AC_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + "))";
	    	QueryDefsInsert(sql);
	    }else {
	    	KAMOKU_JOKEN = "(journals.drkey >= '" + BUMON + ACSEL_2[0] + "' AND journals.drkey <= '" + BUMON + ACSEL_2[0] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[1] + "' AND journals.drkey <= '" + BUMON + ACSEL_2[1] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[2] + "' AND journals.drkey <= '" + BUMON + ACSEL_2[2] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[3] + "' AND journals.drkey <= '" + BUMON + ACSEL_2[3] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[4] + "' AND journals.drkey <= '" + BUMON + ACSEL_2[4] + "ZZZZZZ') OR (journals.drkey >= '" + BUMON + ACSEL_2[5] + "' AND journals.drkey <= '" + BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    	String sql = SQL_TEMP + " journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM devtables RIGHT JOIN (credit_department_subjects_specific RIGHT JOIN journals ON credit_department_subjects_specific.aggregate_key = journals.crkey) ON devtables.devcode = journals.devcode WHERE ((" + KAMOKU_JOKEN + ") AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + ") AND debit_amount<>0)";
		    QueryDefsInsert(sql);
	    }
	    ///////////////////////4/////////////////
	    SQL_TEMP = "INSERT INTO ledger_tran (devcode, devname, je_number, s_number,l_number,yyyy,mm,dd,cramount,dramount,draccode,dracname,dracsubcode,dracsubname,descode,desname, aggregate_key,sundry,vendorcode,vendorname,otherdata) SELECT DISTINCTROW journals.devcode, devtables.devname, journals.je_number,journals.s_number, journals.l_number, journals.yyyy,journals.mm, journals.dd, journals.cramount, 0 AS dramount, journals.draccode AS partner_subject_code, debit_department_subjects_specific.acname AS acname, journals.dracsubcode AS dracsubcode, debit_department_subjects_specific.acsubname AS acsubname,journals.descode, journals.desname, journals.crkey, ";
	    if(SAI_SEL != " ") {
	    	String sql = SQL_TEMP + "journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ((journals.crkey >='" + BUMON + SAI_SEL + "' AND journals.crkey <='" + BUMON + SAI_SEL + "999999" + "') AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + "))";
	    	QueryDefsInsert(sql);
	    }else if(ACSEL[0].equals(" ") && ACSEL[1].equals(" ") && ACSEL[2].equals(" ") && ACSEL[3].equals(" ") && ACSEL[4].equals(" ") && ACSEL[5].equals(" ")) {
	    	String sql = SQL_TEMP + "journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE ((journals.crkey >='" + BUMON + AC_FROM + "' AND journals.crkey <='" + BUMON + AC_TO + "999999" + "') AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + "))";
	    	QueryDefsInsert(sql);
	    }else {
	    	KAMOKU_JOKEN = "(journals.crkey >= '" + BUMON + ACSEL_2[0] + "' AND journals.crkey <= '" + BUMON + ACSEL_2[0] + "ZZZZZZ') OR (journals.crkey >= '" + BUMON + ACSEL_2[1] + "' AND journals.crkey <= '" + BUMON + ACSEL_2[1] + "ZZZZZZ') OR (journals.crkey >= '" + BUMON + ACSEL_2[2] + "' AND journals.crkey <= '" + BUMON + ACSEL_2[2] + "ZZZZZZ') OR (journals.crkey >= '" + BUMON + ACSEL_2[3] + "' AND journals.crkey <= '" + BUMON + ACSEL_2[3] + "ZZZZZZ') OR (journals.crkey >= '" + BUMON + ACSEL_2[4] + "' AND journals.crkey <= '" + BUMON + ACSEL_2[4] + "ZZZZZZ') OR (journals.crkey >= '" + BUMON + ACSEL_2[5] + "' AND journals.crkey <= '" + BUMON + ACSEL_2[5] + "ZZZZZZ')";
	    	String sql = SQL_TEMP + " journals.sundry, journals.vendorcode, journals.vendorname, journals.otherdata FROM (devtables RIGHT JOIN journals ON devtables.devcode = journals.devcode) LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE (" + KAMOKU_JOKEN + " AND (yyyy*10000+mm*100+dd>=" + YMD_FROM + " AND yyyy*10000+mm*100+dd<=" + YMD_TO + ") AND (cramount<>0))";
	    	QueryDefsInsert(sql);
	    }
	    
	    if(NARABI == 1) {
	    	SQL_TEMP = "ORDER BY ledger_tran.aggregate_key, ledger_tran.je_number, ledger_tran.l_number, ledger_tran.yyyy, ledger_tran.mm, ledger_tran.dd";
	    } else {
	    	SQL_TEMP = "ORDER BY ledger_tran.aggregate_key, ledger_tran.yyyy, ledger_tran.mm, ledger_tran.dd, ledger_tran.je_number, ledger_tran.l_number";
	    }
	    StringBuffer SQL = new StringBuffer("INSERT INTO ledger(aggregate_key, accode, acname, acsubcode, acsubname, devcode, devname, je_number, s_number, l_number, yyyy, mm, dd, draccode, dracname, dracsubcode, dracsubname, dramount, cramount, balance, descode, desname, sundry, vendorcode, vendorname, otherdata) SELECT DISTINCTROW ledger_tran.aggregate_key, actables.accode AS accode, actables.acname AS acname, actables.acsubcode AS acsubcode, actables.acsubname AS acsubname, ledger_tran.devcode, ledger_tran.devname, ledger_tran.je_number,ledger_tran.s_number,ledger_tran.l_number, ledger_tran.yyyy, ledger_tran.mm, ledger_tran.dd, ledger_tran.draccode, ledger_tran.dracname, ledger_tran.dracsubcode, ledger_tran.dracsubname, ledger_tran.dramount, ledger_tran.cramount, ledger_tran.balance, ledger_tran.descode, ledger_tran.desname, ledger_tran.sundry, ledger_tran.vendorcode, ledger_tran.vendorname, ledger_tran.otherdata FROM ledger_tran INNER JOIN actables ON ledger_tran.aggregate_key = actables.keycode " + SQL_TEMP);
	    
	    TableDefs(SQL, "ledger");
	    
	    List<Ledger> ledgers = getLedger();
    	for(Ledger ldg:ledgers){
    		if(ldg.getDracname().equals("前繰残高")){
    			ZANDAKA_TRAN = ldg.getBalance();
    		} else if(ldg.getDracname().equals("残　　高")){
    			
    		} else{
    			ZANDAKA_TRAN = ZANDAKA_TRAN + ldg.getDramount() - ldg.getCramount();
    			String sqlDel = "UPDATE ledger SET balance="+ZANDAKA_TRAN+" WHERE aggregate_key='"+ldg.getAggregate_key()+"' AND je_number="+ldg.getJe_number();
    			QueryDefsUpdate(sqlDel);
    		}
    		if(ldg.getSundry() == 1){
    			String sqlDel = "UPDATE ledger SET draccode='    ',dracname='諸口',dracsubcode='    ',dracsubname='    ' WHERE aggregate_key='"+ldg.getAggregate_key()+"' AND je_number="+ldg.getJe_number();
    			QueryDefsUpdate(sqlDel);
    		}
    	}
    	
    	 if(NARABI == 1) {
    		 JOKEN = "入力順";
    	 } else {
    		 JOKEN = "日付順";
    	 }
    	 
    	 boolean fo = false;
 		try {
 			DBManager db = new DBManager();
 			
 			try {
 				String sqlSt = "SELECT * FROM reportparameters";
 				
 				ResultSet rs = db.getRecord(sqlSt);
 				
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
 		
 		String ledger_extrantion_cond ="抽出条件　部門" + BUMON + "  " + NEN_FROM + "年" + TUKI_FROM + "月" + HI_FROM + "日から" + NEN_TO + "年" + TUKI_TO + "月" + HI_TO + "日まで・" + JOKEN;
    	try {
			DBManager dbc = new DBManager();
			if(fo) {
				PreparedStatement pstm = dbc.getPreparStamt("UPDATE reportparameters SET ledger_extrantion_cond = '" + ledger_extrantion_cond+ "'");
				pstm.executeUpdate();
			} else {
				PreparedStatement pstm = dbc.getPreparStamt("INSERT INTO reportparameters(ledger_extrantion_cond) VALUES(?)");
				
				pstm.setString(1, ledger_extrantion_cond);
				pstm.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	// Call Report
    	String param="";
    	try {
			DBManager db = new DBManager();
			
			try {
				String sqlRptParam = "SELECT DISTINCTROW reportparameters.ledger_extrantion_cond FROM reportparameters";
				
				ResultSet rs = db.getRecord(sqlRptParam);

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
    	
    	if(NARABI == 1) {
    		CallReport("rptCashAccountingBook",param);
    	} else if(NARABI == 2) {
    		CallReport("rptCashAccountingBook2",param);
    	}
	    
	}
	
	public String getLedgerWithoutDetail()
	{
		String code="";
    	try {
			DBManager db = new DBManager();
			
			try {
				String sqlRptParam = "SELECT DISTINCTROW aggregate_key FROM ledger WHERE je_number<>0 AND je_number<>999999";
				
				ResultSet rs = db.getRecord(sqlRptParam);

				while(rs.next()) {
					if(code.equals("")){
						code=rs.getString(1);
					} else{
						code = code +","+ rs.getString(1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	String origCode;
    	if(code.endsWith(","))
    		origCode = code.substring(0,code.length()-1);
    	else
    		origCode = code;
    	//System.out.println(origCode);
    	return origCode;
	}
	/*public int TB_Q_SET(String BUMON, long YMD_FROM, long YMD_TO, long KISHU) {
		//対象範囲の試算表テーブルを作成する
		int n=0;
		String view_name; 
		StringBuffer sql;
		
		if(!BUMON.equals("999"))
		{
			view_name = "debit_expense_item_totals_repetitive_remaining";
			sql = new StringBuffer("SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
			QueryDefs(sql, view_name);

			view_name = "credit_expense_item_totals_repetitive_remaining";
			sql = new StringBuffer("SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
			QueryDefs(sql, view_name);
			
			view_name = "balance_before_provision";
			sql = new StringBuffer("SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0)+ IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode = '"+BUMON+"'");
			QueryDefs(sql, view_name);
			
			view_name = "debit_expense_item_totals";
			sql = new StringBuffer("SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="+ YMD_FROM +" AND yyyy * 10000 + mm * 100 + dd <="+ YMD_TO +" AND journals.devcode = '"+ BUMON +"' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
			QueryDefs(sql, view_name);
		        
			view_name = "credit_expense_item_totals";
			sql = new StringBuffer("SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="+ YMD_FROM +" AND yyyy * 10000 + mm * 100 + dd <="+ YMD_TO +" AND journals.devcode ='"+ BUMON +"' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null))");
			QueryDefs(sql, view_name);
			
		} else {
			view_name = "debit_expense_item_totals_repetitive_remaining";
			sql = new StringBuffer("SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode <'100' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
			QueryDefs(sql, view_name);
			
			view_name = "credit_expense_item_totals_repetitive_remaining";
			sql = new StringBuffer("SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "+ (KISHU - 1) +"  AND yyyy * 10000 + mm * 100 + dd < "+ YMD_FROM +" AND journals.devcode <'100' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
			QueryDefs(sql, view_name);
			
			view_name = "balance_before_provision";
			sql = new StringBuffer("SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0) + IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode <'100'");
			QueryDefs(sql, view_name); 
			
			view_name = "debit_expense_item_totals";
			sql = new StringBuffer("SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="+ YMD_FROM +" AND yyyy * 10000 + mm * 100 + dd <="+ YMD_TO +" AND journals.devcode <'100' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
			QueryDefs(sql, view_name);
			
			view_name = "credit_expense_item_totals";
			sql = new StringBuffer("SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="+ YMD_FROM +" AND yyyy * 10000 + mm * 100 + dd <="+ YMD_TO +" AND journals.devcode <'100' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null))");
			QueryDefs(sql, view_name);
		}
		n=1;
		
		return n;
	}*/
	
	private static FrmCreatingLedger myInstance;

	public static FrmCreatingLedger getInstance() {
		myInstance = new FrmCreatingLedger();
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
