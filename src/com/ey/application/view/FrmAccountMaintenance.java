package com.ey.application.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* frmBidMain.java
* --------------------
* Created on Feb 5, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* June 2, 2016: Original version (Ole)
*
*/

public class FrmAccountMaintenance extends JInternalFrame {
	JPanel pnlMain 							= new JPanel();
	
	JLabel lblSoruceRegistration 			= new JLabel();
	JLabel lblPublicPostmigration 			= new JLabel();
		
	JButton cmdJournalEntry 				= new JButton();
	JButton cmdJournalCreate 				= new JButton();
	JButton cmdJournalReviseOrPrint		 	= new JButton();
	JButton cmdOpeningBalance 			    = new JButton();
	JButton cmdOriginalBook 				= new JButton();
	JButton cmdAccountMaintenance 			= new JButton();
	JButton cmdTrialBalance 				= new JButton();
	JButton cmdCreatingBalance 				= new JButton();
	JButton cmdAbstractRegistration 		= new JButton();
	JButton cmdMonthlyReport 				= new JButton();
	JButton cmdQuarterlyBudget 				= new JButton();
	JButton cmdProjectCode 					= new JButton();
	JButton cmdEnd 							= new JButton();
    JButton cmdNextScreen                   = new JButton();
    
    JDesktopPane Desktop 					= new JDesktopPane();
	
	public FrmAccountMaintenance() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setBounds(0, 0, 579, 500);
		this.getContentPane().setBackground(new Color(176,224,230));
		this.setForeground(Color.black);
		this.setResizable(false);
		this.setTitle("勘定科目　登録・修正");
		this.getContentPane().setLayout(null);

		this.setIconifiable(true);
		this.setMaximizable(false);
		this.setClosable(true);
		
		this.getContentPane().add(pnlMain, null);
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
	      public void internalFrameClosing(InternalFrameEvent e) {
	    	  Close();
	      }
	    });

		pnlMain.setBackground(new Color(176,224,230));
		pnlMain.setBorder(BorderFactory.createEtchedBorder());
		pnlMain.setBounds(new Rectangle(9, 9, 555, 480));
		pnlMain.setLayout(null);
		
		lblSoruceRegistration.setText("Source Registration");
		lblSoruceRegistration.setBounds(40, 20, 200, 40);
		lblSoruceRegistration.setVisible(false);
		
		lblPublicPostmigration.setText("Public post Migration System");
		lblPublicPostmigration.setBounds(300, 20, 200, 40);
		lblPublicPostmigration.setVisible(false);
		
		cmdJournalEntry.setText("振替伝票  入力"); 
		cmdJournalEntry.setToolTipText("振替伝票  入力");
		cmdJournalEntry.setVisible(true);
		cmdJournalEntry.setBounds(40,70,200,40);
        cmdJournalEntry.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int FURIDEN_MODE = 1;
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
		});

		cmdJournalCreate.setText("仕　訳　帳");
        cmdJournalCreate.setToolTipText("仕　訳　帳");
        cmdJournalCreate.setVisible(true);
        cmdJournalCreate.setBounds(300,70,200,40);
        cmdJournalCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmJournalCreate jc = FrmJournalCreate.getInstance();
				jc.setBounds(0, 0, 300, 420);
				jc.getContentPane().setBackground(new Color(176,224,230));
				jc.setForeground(Color.black);
				jc.setResizable(false);
				jc.setTitle("仕訳帳作成");
				jc.getContentPane().setLayout(null);
				jc.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
				if (jc.isVisible()) {
				} else {
					getDesktopPane().add(jc);
					jc.setVisible(true);
				}

				jc.setIconifiable(true);
				jc.setMaximizable(false);
				jc.setClosable(true);
				dispose();
			}
		});

        cmdJournalReviseOrPrint.setText("振替伝票　修正・印刷 ");
        cmdJournalReviseOrPrint.setToolTipText("振替伝票　修正・印刷");
        cmdJournalReviseOrPrint.setVisible(true);
        cmdJournalReviseOrPrint.setBounds(40,120,200,40);
        cmdJournalReviseOrPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmDocumentSearch ds = FrmDocumentSearch.getInstance();
				ds.setBounds(0, 0, 640, 550);
				ds.getContentPane().setBackground(new Color(176,224,230));
				ds.setForeground(Color.black);
				ds.setResizable(false);
				ds.setTitle("伝票検索");
				ds.getContentPane().setLayout(null);
				ds.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
				if (ds.isVisible()) {
				} else {
					getDesktopPane().add(ds);
					ds.setVisible(true);
				}

				ds.setIconifiable(true);
				ds.setMaximizable(false);
				ds.setClosable(true);
				dispose();
			}
		});

        cmdOpeningBalance.setText("期首残高設定");
        cmdOpeningBalance.setToolTipText("期首残高設定");
        cmdOpeningBalance.setVisible(true);
        cmdOpeningBalance.setBounds(300,120,200,40);
        cmdOpeningBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmOpeningBalanceMaintenance ds = frmOpeningBalanceMaintenance.getInstance();
				ds.setBounds(0, 0, 800, 600);
				ds.getContentPane().setBackground(new Color(176,224,230));
				ds.setForeground(Color.black);
				ds.setResizable(false);
				ds.setTitle("期首残高メンテ");
				ds.getContentPane().setLayout(null);
				ds.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
				if (ds.isVisible()) {
				} else {
					getDesktopPane().add(ds);
					ds.setVisible(true);
				}

				ds.setIconifiable(true);
				ds.setMaximizable(false);
				ds.setClosable(true);
				dispose();
			}
		});

        cmdOriginalBook.setText("元  帳");
        cmdOriginalBook.setToolTipText("元  帳");
        cmdOriginalBook.setVisible(true);
        cmdOriginalBook.setBounds(40,170,200,40);
        cmdOriginalBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCreatingLedger ds = FrmCreatingLedger.getInstance();
				ds.setBounds(0, 0, 520, 600);
				ds.getContentPane().setBackground(new Color(176,224,230));
				ds.setForeground(Color.black);
				ds.setResizable(false);
				ds.setTitle("元  帳");
				ds.getContentPane().setLayout(null);
				ds.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
				if (ds.isVisible()) {
				} else {
					getDesktopPane().add(ds);
					ds.setVisible(true);
				}

				ds.setIconifiable(true);
				ds.setMaximizable(false);
				ds.setClosable(true);
				dispose();
			}
		});
        
        cmdAccountMaintenance.setToolTipText("勘定科目　登録・修正");
		cmdAccountMaintenance.setBounds(300, 170, 200, 40);
		cmdAccountMaintenance.setVisible(true);
		cmdAccountMaintenance.setText("勘定科目　登録・修正");
		cmdAccountMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmAccountRegister nw = FrmAccountRegister.getInstance();
				nw.setBounds(0, 0, 800, 600);
				nw.getContentPane().setBackground(new Color(176,224,230));
				nw.setForeground(Color.black);
				nw.setResizable(false);
				nw.setTitle("勘定科目　登録・修正");
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
		});

        cmdTrialBalance.setText("残高試算表");
        cmdTrialBalance.setToolTipText("残高試算表");
        cmdTrialBalance.setVisible(true);
        cmdTrialBalance.setBounds(40,220,200,40);
        cmdTrialBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmTrialBalance ds = FrmTrialBalance.getInstance();
				ds.setBounds(0, 0, 300, 440);
				ds.getContentPane().setBackground(new Color(176,224,230));
				ds.setForeground(Color.black);
				ds.setResizable(false);
				ds.setTitle("伝票検索");
				ds.getContentPane().setLayout(null);
				ds.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
				if (ds.isVisible()) {
				} else {
					getDesktopPane().add(ds);
					ds.setVisible(true);
				}

				ds.setIconifiable(true);
				ds.setMaximizable(false);
				ds.setClosable(true);
				dispose();
			}
		});

        cmdCreatingBalance.setText("Creating Balance of Payments Statements");
        cmdCreatingBalance.setToolTipText("Creating Balance of Payments Statements");
        cmdCreatingBalance.setVisible(false);
        cmdCreatingBalance.setBounds(300,220,200,40);

        cmdAbstractRegistration.setText("摘要登録");
        cmdAbstractRegistration.setToolTipText("摘要登録");
        cmdAbstractRegistration.setVisible(true);
        cmdAbstractRegistration.setBounds(40,270,200,40);
        cmdAbstractRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmDescription ds = FrmDescription.getInstance();
				ds.setBounds(0, 0, 941, 561);
				ds.getContentPane().setBackground(new Color(176,224,230));
				ds.setForeground(Color.black);
				ds.setResizable(false);
				ds.setTitle("摘要登録修正");
				ds.getContentPane().setLayout(null);
				ds.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
				if (ds.isVisible()) {
				} else {
					getDesktopPane().add(ds);
					ds.setVisible(true);
				}

				ds.setIconifiable(true);
				ds.setMaximizable(false);
				ds.setClosable(true);
				dispose();
			}
		});

        cmdMonthlyReport.setText("Monthly Report");
        cmdMonthlyReport.setToolTipText("Monthly Report");
        cmdMonthlyReport.setVisible(false);
        cmdMonthlyReport.setBounds(300,270,200,40);

        cmdQuarterlyBudget.setText("Quarterly Budget");
        cmdQuarterlyBudget.setToolTipText("Quarterly Budget");
        cmdQuarterlyBudget.setVisible(false);
        cmdQuarterlyBudget.setBounds(40,320,200,40);

        cmdProjectCode.setText("Project Code Maintenance");
        cmdProjectCode.setToolTipText("Project Code Maintenance");
        cmdProjectCode.setVisible(false);
        cmdProjectCode.setBounds(300,320,200,40);

        cmdNextScreen.setText("Next Screen");
        cmdNextScreen.setToolTipText("Next Screen");
        cmdNextScreen.setVisible(false);
        cmdNextScreen.setBounds(140,370,100,40);

		
							
		cmdEnd.setToolTipText("閉じる");
		cmdEnd.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdEnd.setBounds(new Rectangle(400, 370, 100, 40));
		cmdEnd.setVisible(true);
		cmdEnd.setText("閉じる");
		cmdEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		pnlMain.add(lblSoruceRegistration, null);
		pnlMain.add(lblPublicPostmigration, null);
		pnlMain.add(cmdJournalEntry, null);
		pnlMain.add(cmdJournalCreate, null);
		pnlMain.add(cmdJournalReviseOrPrint, null);
		pnlMain.add(cmdOpeningBalance, null);
		pnlMain.add(cmdOriginalBook, null);
        pnlMain.add(cmdAccountMaintenance, null);
		pnlMain.add(cmdAccountMaintenance, null);
		pnlMain.add(cmdTrialBalance, null);
		pnlMain.add(cmdCreatingBalance, null);
		pnlMain.add(cmdAbstractRegistration, null);
		pnlMain.add(cmdMonthlyReport, null);
		pnlMain.add(cmdQuarterlyBudget);
		pnlMain.add(cmdProjectCode);
        pnlMain.add(cmdNextScreen);
		pnlMain.add(cmdEnd, null);
	}
 	
 	public void createFrame(JInternalFrame frm) {
		JInternalFrame frame = frm;
		frame.setVisible(true);
		Desktop.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
	}
 	
 	public void Close(){
 		dispose();
 	}
 	
 	public static FrmAccountMaintenance myInstance;
 	
 	public static FrmAccountMaintenance getInstance() {
 		if (myInstance == null) {
 	        myInstance = new FrmAccountMaintenance();
 	    }
 	    return myInstance;
 	}
 	
}