package com.ey.application.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;

import com.ey.application.controller.DBManager;
import com.ey.application.model.CommonProperties;
/*import com.ey.application.model.FinancialInstitutions;
import com.ey.application.model.NoSpecification;
import com.ey.application.model.SuccessfulBidData3;
import com.ey.application.model.SuccessfulBidDocument;
import com.ey.application.model.SuccessfulBidSituations;
import com.ey.application.model.ThereSpecification;*/

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

/* ========================================
* AMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* JasperReportViewer.java
* --------------------
* Created on June 14, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* June 14, 2016: Original version (Monsur)
*
*/
public class JasperReportViewer extends JInternalFrame {
	private static JasperReportViewer myInstance;

	public static JasperReportViewer getInstance(String name) {
		// if (myInstance == null) {

		return myInstance = new JasperReportViewer(name, "");
		// }
		// return myInstance;
	}

	public static JasperReportViewer getInstance(String name, String param) {
		// if (myInstance == null) {
		return myInstance = new JasperReportViewer(name, param);
		// }
		// return myInstance;
	}

	public JasperReportViewer(String name, String param) {
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				Close(name);
			}
		});
		String workingDir = System.getProperty("user.dir");
		String sourceFileName = "";
		JasperReport jasperMasterReport = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
		Map parameters = null;
		JRBeanCollectionDataSource beanColDataSource = null;
		if (name.equals("rptACListPrint")) {

			this.setTitle("ACList Print");
			sourceFileName = workingDir + "\\rpt\\rptACListPrint.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("devCode", param);
			// parameters.put("CurrentDateTime",
			// String.valueOf(format.format(new Date())));

		} else if (name.equals("transferslipreport")) {
			this.setTitle("振替伝票書");
			sourceFileName = workingDir + "\\rpt\\transferslipreport.jrxml";
			String subsourceFileName = workingDir + "\\rpt\\transferslipreport_subreport.jrxml";

			JasperReport jasperSubReport = null;
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
				jasperSubReport = JasperCompileManager.compileReport(subsourceFileName);

			} catch (JRException e) {
				e.printStackTrace();
			}

			// ReportBeanList ReportBeanList = new ReportBeanList();
			// List<SuccessfulBidDocument> dataList =
			// ReportBeanList.getSuccessfulBidDocument(bidDate,dept_code);
			// beanColDataSource = new JRBeanCollectionDataSource(dataList);

			parameters = new HashMap();
			parameters.put("transferslipreport_subreport", jasperSubReport);
			parameters.put("SUBREPORT_DIR", workingDir + "\\rpt\\");

		} else if (name.equals("Journal")) {
			this.setTitle("仕訳帳");
			sourceFileName = workingDir + "\\rpt\\Journal.jrxml";

			JasperReport jasperSubReport = null;
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);

			} catch (JRException e) {
				e.printStackTrace();
			}

			String params[] = param.split("~");

			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			parameters.put("BUMON", params[0]);
			parameters.put("YMD_FROM", Long.parseLong(params[1]));
			parameters.put("YMD_TO", Long.parseLong(params[2]));
			parameters.put("SIWAKE_FROM", Long.parseLong(params[3]));
			parameters.put("SIWAKE_TO", Long.parseLong(params[4]));

		} else if (name.equals("Journal2")) {
			this.setTitle("仕訳帳2");
			sourceFileName = workingDir + "\\rpt\\Journal2.jrxml";

			JasperReport jasperSubReport = null;
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);

			} catch (JRException e) {
				e.printStackTrace();
			}

			String params[] = param.split("~");

			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			parameters.put("BUMON", params[0]);
			parameters.put("YMD_FROM", Long.parseLong(params[1]));
			parameters.put("YMD_TO", Long.parseLong(params[2]));
			parameters.put("SIWAKE_FROM", Long.parseLong(params[3]));
			parameters.put("SIWAKE_TO", Long.parseLong(params[4]));

		} else if (name.equals("CustomerTrialBalance")) {

			this.setTitle("部門別科目別合計残高試算表");
			sourceFileName = workingDir + "\\rpt\\CustomerTrialBalance.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("reportParameter", param);
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

		} else if (name.equals("trialbalancesubjectsby")) {

			this.setTitle("部門別科目別合計残高試算表");
			sourceFileName = workingDir + "\\rpt\\trialbalancesubjectsby.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("reportParameter", param);
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

		} else if (name.equals("rptTrialBalance")) {

			this.setTitle("部門別科目別合計残高試算表");
			sourceFileName = workingDir + "\\rpt\\rptTrialBalance.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("reportParameter", param);
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

		} else if (name.equals("rptTrialBalanceAllDepartments")) {

			this.setTitle("全部門科目別合計残高試算表");
			sourceFileName = workingDir + "\\rpt\\rptTrialBalanceAllDepartments.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("reportParameter", param);
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

		} else if (name.equals("rptProjectBudget")) {

			this.setTitle("プロジェクト別経費");
			sourceFileName = workingDir + "\\rpt\\rptProjectBudget.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
		}else if (name.equals("rptProjectBudget2")) {

			this.setTitle("プロジェクト別収支");
			sourceFileName = workingDir + "\\rpt\\rptProjectBudget2.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
		} else if (name.equals("rptProjectTrialBalance")) {

			this.setTitle("PRJ試算表");
			sourceFileName = workingDir + "\\rpt\\rptProjectTrialBalance.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			
		} else if (name.equals("rptCashAccountingBook")) {

			this.setTitle("現金出納簿");
			sourceFileName = workingDir + "\\rpt\\rptCashAccountingBook.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("reportParameter", param);
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

		} else if (name.equals("rptCashAccountingBook2")) {

			this.setTitle("現金出納簿2");
			sourceFileName = workingDir + "\\rpt\\rptCashAccountingBook2.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("reportParameter", param);
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

		} else if (name.equals("rptCashCheck")) {
			this.setTitle("現金チェック表");
			sourceFileName = workingDir + "\\rpt\\rptCashCheck.jrxml";
			String subsourceFileName = workingDir+"\\rpt\\rptCashCheck_subreport.jrxml";
			JasperReport jasperSubReport = null;
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
				jasperSubReport = JasperCompileManager.compileReport(subsourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}
			String params[] = param.split("~");

			parameters = new HashMap();
			parameters.put("rptCashCheck_subreport", jasperSubReport);
			parameters.put("SUBREPORT_DIR", workingDir+"\\rpt\\");
			parameters.put("BUMON", params[0]);
			
		} else if (name.equals("subledger")) {
			String title = param.split("~")[1];
			String params = param.split("~")[0];
			this.setTitle(title);
			sourceFileName = workingDir + "\\rpt\\subledger.jrxml";
			String subsourceFileName = workingDir+"\\rpt\\subledger_subreport.jrxml";
			JasperReport jasperSubReport = null;
			System.out.println(sourceFileName);
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
				jasperSubReport = JasperCompileManager.compileReport(subsourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("subledger_subreport", jasperSubReport);
			parameters.put("SUBREPORT_DIR", workingDir+"\\rpt\\");
			parameters.put("title", title);
			parameters.put("reportParameter", params);
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			
		} else if (name.equals("subledger2")) {
			String title = param.split("~")[1];
			String params = param.split("~")[0];
			this.setTitle(title);
			sourceFileName = workingDir + "\\rpt\\subledger2.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("title", title);
			parameters.put("reportParameter", params);
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			
		} else if (name.equals("rptAbstractTablePrinting")) {
			this.setTitle(title);
			sourceFileName = workingDir + "\\rpt\\rptAbstractTablePrinting.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}

			parameters = new HashMap();
			parameters.put("devcode", param);
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			
		}
		
		

		JasperPrint jasperPrint = null;
		if (name.equals("rptACListPrint") || name.equals("transferslipreport") || name.equals("Journal")
				|| name.equals("Journal2") || name.equals("CustomerTrialBalance")
				|| name.equals("trialbalancesubjectsby") || name.equals("rptTrialBalance")
				|| name.equals("rptTrialBalanceAllDepartments") || name.equals("rptProjectBudget") || name.equals("rptProjectBudget2")
				|| name.equals("rptCashAccountingBook") || name.equals("rptCashAccountingBook2")|| name.equals("rptCashCheck")
				|| name.equals("subledger") || name.equals("subledger2") || name.equals("rptAbstractTablePrinting")) {
			DBManager db;
			Connection conn = null;
			try {
				db = new DBManager();
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				jasperPrint = JasperFillManager.fillReport(jasperMasterReport, parameters, conn);
			} catch (JRException e) {
				e.printStackTrace();
			}
		} else {

			try {
				jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}

		JRViewer viewer = new JRViewer(jasperPrint);
		// viewer.clear();
		// viewer = null;
		Container c = this.getContentPane();
		c.removeAll();
		c.add(viewer);
		this.setBounds(1, 1, 800, 600);

	}

	public void Close(String name) {
		if (name.equals("rptACListPrint")) {
			FrmAccountRegister nw = FrmAccountRegister.getInstance();
			nw.setBounds(0, 0, 800, 600);
			nw.getContentPane().setBackground(new Color(176, 224, 230));
			nw.setForeground(Color.black);
			nw.setResizable(false);
			nw.setTitle("勘定科目　登録・修正");
			nw.getContentPane().setLayout(null);

			if (nw.isVisible()) {
			} else {
				getDesktopPane().add(nw);
				nw.setVisible(true);
			}

			nw.setIconifiable(true);
			nw.setMaximizable(false);
			nw.setClosable(true);
		} /*
			 * else if(name.equals("ThereSpecification") ||
			 * name.equals("NoSpecification")) { DateFormat formatter = new
			 * SimpleDateFormat("dd-MM-yyyy"); Date date=null; try { date =
			 * formatter.parse(bidDate); } catch (ParseException e) {
			 * e.printStackTrace(); } FrmInterestRateOrderBidStatus nw =
			 * FrmInterestRateOrderBidStatus.getInstance(date); nw.setBounds(0,
			 * 0, 300, 250); nw.getContentPane().setBackground(new
			 * Color(176,224,230)); nw.setForeground(Color.black);
			 * nw.setResizable(false); nw.setTitle("å…¥æœ­ãƒ‡ãƒ¼ã‚¿å…¥åŠ›");
			 * nw.getContentPane().setLayout(null);
			 * 
			 * if (nw.isVisible()) { } else { getDesktopPane().add(nw);
			 * nw.setVisible(true); }
			 * 
			 * nw.setIconifiable(true); nw.setMaximizable(false);
			 * nw.setClosable(true); }else
			 * if(name.equals("SuccessfulBidStatusTableByBank") ||
			 * name.equals("InterestRateBy")) { DateFormat formatter = new
			 * SimpleDateFormat("dd-MM-yyyy"); Date date=null; try { date =
			 * formatter.parse(bidDate); } catch (ParseException e) {
			 * e.printStackTrace(); }
			 * 
			 * FrmSuccessfulBidDataCreation nw =
			 * FrmSuccessfulBidDataCreation.getInstance(date); nw.setBounds(0,
			 * 0, 430, 320); nw.getContentPane().setBackground(new
			 * Color(176,224,230)); nw.setForeground(Color.black);
			 * nw.setResizable(false); nw.setTitle("å…¥æœ­ãƒ‡ãƒ¼ã‚¿å…¥åŠ›");
			 * nw.getContentPane().setLayout(null);
			 * 
			 * if (nw.isVisible()) { } else { getDesktopPane().add(nw);
			 * nw.setVisible(true); }
			 * 
			 * nw.setIconifiable(true); nw.setMaximizable(false);
			 * nw.setClosable(true); }else if(name.equals("ProvisionalNotice")
			 * || name.equals("SuccessfulBidDocument")) {
			 * 
			 * DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); Date
			 * date=null; try { date = formatter.parse(bidDate); } catch
			 * (ParseException e) { e.printStackTrace(); }
			 * 
			 * FrmBidDocumentPrint nw = FrmBidDocumentPrint.getInstance(date);
			 * nw.setBounds(0, 0, 300, 250);
			 * nw.getContentPane().setBackground(new Color(176,224,230));
			 * nw.setForeground(Color.black); nw.setResizable(false);
			 * nw.setTitle("å…¥æœ­ãƒ‡ãƒ¼ã‚¿å…¥åŠ›");
			 * nw.getContentPane().setLayout(null);
			 * 
			 * if (nw.isVisible()) { } else { getDesktopPane().add(nw);
			 * nw.setVisible(true); }
			 * 
			 * nw.setIconifiable(true); nw.setMaximizable(false);
			 * nw.setClosable(true); }
			 */
		dispose();
	}

	public String ConvertDateToJP(String date) {
		String jpdate = "";
		String ar[] = date.split("-");
		if (ar[2] != null) {
			long cyear = Long.parseLong(ar[2]);
			long i = cyear - 1988;
			int mon = Integer.parseInt(ar[1]);
			int day = Integer.parseInt(ar[0]);

			jpdate = "å¹³æˆ�" + i + "å¹´" + mon + "æœˆ" + day + "æ—¥";
		}
		return jpdate;
	}

	public String ConvertCDateToJP() {
		String datePattern = "dd-MM-yyyy";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
		String date = dateFormatter.format(new Date());
		String jpdate = "";
		String ar[] = date.split("-");
		if (ar[2] != null) {
			long cyear = Long.parseLong(ar[2]);
			long i = cyear - 1988;
			int mon = Integer.parseInt(ar[1]);
			int day = Integer.parseInt(ar[0]);

			jpdate = i + "." + mon + "." + day;
		}
		return jpdate;
	}
}
