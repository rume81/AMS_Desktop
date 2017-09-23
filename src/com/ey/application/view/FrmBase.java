package com.ey.application.view;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ey.application.controller.DBManager;
import com.ey.application.model.Jurnal;

public class FrmBase extends JInternalFrame {

	public String LeftB(String st, int len) {
		String res = "";
		for (int i = 1; i < st.length(); i++) {
			if (res.getBytes().length > len)
				break;
			res = st.substring(0, i + 1);
		}
		return res;
	}

	public void FURI_REP_MAKE() {
		// Transfer slip reporting
		// Previous journal numbers
		long MAE_BAN;
		// before the slip number
		long MAE_DEN;
		String TEKIYO = "";
		String MAE_BUMONCODE = "";
		String MAE_BUMONNAME;
		int MAE_NEN, MAE_TUKI, MAE_HI;

		/*
		 * If OBJ_EXIST ( "transfer slip report for data deletion") = 2 Then
		 *         //DoCmd.DeleteObject A_QUERY,
		 * "for a transfer slip report data deletion"     //End If if() {
		 * 
		 * }
		 */

		try {
			// DBManager db = new DBManager();
			DBManager dbC = new DBManager();

			/*
			 * try { boolean fo = db.doQuery(
			 * "DROP VIEW IF EXISTS deletetransferslipreport"); } catch
			 * (SQLException sqle) { sqle.printStackTrace(); } finally {
			 * db.close(); }
			 */

			try {
				// StringBuffer strSql = new StringBuffer("CREATE VIEW
				// deletetransferslipreport AS DELETE transfer_slip.devcode,
				// transfer_slip.devname, transfer_slip.je_number,
				// transfer_slip.s_number, transfer_slip.yyyy,
				// transfer_slip.mm,transfer_slip.dd,
				// transfer_slip.abstract_name FROM transfer_slip");
				StringBuffer strSql = new StringBuffer("DELETE FROM transfer_slip");

				boolean fo1 = dbC.doQuery(strSql.toString());

				// System.out.println(strSql);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// TEKIYO = "";
		List<Jurnal> journ = new ArrayList<Jurnal>();
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rs = db.getRecord("SELECT * FROM JE_search_result");
				if (rs.next()) {
					String desname = rs.getString("desname");
					if (!desname.equals("")) {
						TEKIYO = desname + (char) 13 + (char) 10;
					}

					MAE_BUMONCODE = rs.getString("devcode");
					MAE_BUMONNAME = rs.getString("devname");
					MAE_NEN = rs.getInt("yyyy");
					MAE_TUKI = rs.getInt("mm");
					MAE_HI = rs.getInt("dd");
					MAE_BAN = rs.getLong("je_number");
					MAE_DEN = rs.getLong("s_number");

					while (rs.next()) {
						Jurnal journal = new Jurnal();
						long journal_num = rs.getLong("je_number");
						if (journal_num != MAE_BAN) {
							journal.setDevcode(MAE_BUMONCODE);
							journal.setDevname(MAE_BUMONNAME);
							journal.setJe_number(MAE_BAN);
							journal.setS_number(MAE_DEN);
							journal.setYyyy(MAE_NEN);
							journal.setMm(MAE_TUKI);
							journal.setDd(MAE_HI);
							if (TEKIYO.getBytes().length > 254) {
								TEKIYO = LeftB(TEKIYO, 254);
							}
							journal.setDesname(TEKIYO);

							journ.add(journal);
						}

						desname = rs.getString("desname");
						if (!desname.equals("")) {
							TEKIYO = desname + (char) 13 + (char) 10;
						}

						MAE_BUMONCODE = rs.getString("devcode");
						MAE_BUMONNAME = rs.getString("devname");
						MAE_NEN = rs.getInt("yyyy");
						MAE_TUKI = rs.getInt("mm");
						MAE_HI = rs.getInt("dd");
						MAE_BAN = rs.getLong("je_number");
						MAE_DEN = rs.getLong("s_number");
					}
					Jurnal journal = new Jurnal();
					journal.setDevcode(MAE_BUMONCODE);
					journal.setDevname(MAE_BUMONNAME);
					journal.setJe_number(MAE_BAN);
					journal.setS_number(MAE_DEN);
					journal.setYyyy(MAE_NEN);
					journal.setMm(MAE_TUKI);
					journal.setDd(MAE_HI);
					if (TEKIYO.getBytes().length > 254) {
						TEKIYO = LeftB(TEKIYO, 254);
					}
					journal.setDesname(TEKIYO);

					journ.add(journal);

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Jurnal jor : journ) {
			try {
				DBManager db = new DBManager();

				try {
					PreparedStatement ps = db.getPreparStamt(
							"INSERT INTO transfer_slip (devcode, devname, je_number, s_number, yyyy, mm, dd, abstract_name) VALUES(?,?,?,?,?,?,?,?)");

					ps.setString(1, jor.getDevcode());
					ps.setString(2, jor.getDevname());
					ps.setLong(3, jor.getJe_number());
					ps.setLong(4, jor.getS_number());
					ps.setInt(5, jor.getYyyy());
					ps.setInt(6, jor.getMm());
					ps.setInt(7, jor.getDd());
					ps.setString(8, jor.getDesname());

					ps.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		JasperReportViewer nw = JasperReportViewer.getInstance("transferslipreport");
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// dispose();
	}

	// To get the closing date
	public long YM_GET(long TUKI_DATA, long HI_DATA) {
		long KESSAN_NEN = 0;
		long KESSAN_TUKI = 0;
		long NEN_DATA = 0;

		try {
			DBManager db = new DBManager();

			try {
				String sql = "SELECT * FROM basedatas";

				ResultSet rs = db.getRecord(sql);

				if (rs.next()) {
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

		if (TUKI_DATA > KESSAN_TUKI) {
			NEN_DATA = KESSAN_NEN - 1;
		}
		if (TUKI_DATA <= KESSAN_TUKI) {
			NEN_DATA = KESSAN_NEN;
		}
		return (NEN_DATA * 10000 + TUKI_DATA * 100 + HI_DATA);
	}

	public void QueryDefs(StringBuffer sql, String ViewName) {
		try {
			DBManager db = new DBManager();
			DBManager dbC = new DBManager();

			try {
				boolean fo = db.doQuery("DROP VIEW IF EXISTS " + ViewName);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}

			try {
				StringBuffer strSql = new StringBuffer("CREATE VIEW " + ViewName + " AS " + sql.toString());
				// System.out.println(strSql);
				boolean fo1 = dbC.doQuery(strSql.toString());

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void QueryDefsInsert(String sql) {
		try {
			DBManager dbC = new DBManager();

			try {
				StringBuffer strSql = new StringBuffer(sql);
				System.out.println(strSql);
				boolean fo1 = dbC.doQuery(strSql.toString());

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void QueryDefsUpdate(String sql) {
		try {
			DBManager dbC = new DBManager();

			try {
				StringBuffer strSql = new StringBuffer(sql);
				System.out.println(strSql);
				boolean fo1 = dbC.doQuery(strSql.toString());

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String Right(String txt, int index) {
		String output = txt;
		int len = txt.length();
		if (len >= index) {
			int endpos = len - index;
			output = txt.substring(endpos, len);
		}
		return output;
	}

	public void TableClear(String TableName) {
		try {
			DBManager db = new DBManager();

			try {
				boolean fo = db.doQuery("DELETE FROM " + TableName);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void TableDefs(StringBuffer sql, String TableName) {
		try {
			DBManager db = new DBManager();
			DBManager dbC = new DBManager();

			try {
				boolean fo = db.doQuery("DELETE FROM " + TableName);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}

			try {
				StringBuffer strSql = new StringBuffer(sql.toString());
				System.out.println(strSql);
				boolean fo1 = dbC.doQuery(strSql.toString());

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int TB_Q_SET(String BUMON, long YMD_FROM, long YMD_TO, long KISHU) {
		// 対象範囲の試算表テーブルを作成する
		int n = 0;
		String view_name;
		StringBuffer sql;

		if (!BUMON.equals("999")) {
			view_name = "debit_expense_item_totals_repetitive_remaining";
			sql = new StringBuffer(
					"SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "
							+ (KISHU - 1) + "  AND yyyy * 10000 + mm * 100 + dd < " + YMD_FROM
							+ " AND journals.devcode = '" + BUMON
							+ "' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
			QueryDefs(sql, view_name);

			view_name = "credit_expense_item_totals_repetitive_remaining";
			sql = new StringBuffer(
					"SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "
							+ (KISHU - 1) + "  AND yyyy * 10000 + mm * 100 + dd < " + YMD_FROM
							+ " AND journals.devcode = '" + BUMON
							+ "' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
			QueryDefs(sql, view_name);

			view_name = "balance_before_provision";
			sql = new StringBuffer(
					"SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0)+ IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode = '"
							+ BUMON + "'");
			QueryDefs(sql, view_name);

			view_name = "debit_expense_item_totals";
			sql = new StringBuffer(
					"SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
							+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO + " AND journals.devcode = '"
							+ BUMON + "' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
			QueryDefs(sql, view_name);

			view_name = "credit_expense_item_totals";
			sql = new StringBuffer(
					"SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
							+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO + " AND journals.devcode ='"
							+ BUMON + "' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null))");
			QueryDefs(sql, view_name);

		} else {
			view_name = "debit_expense_item_totals_repetitive_remaining";
			sql = new StringBuffer(
					"SELECT DISTINCTROW journals.drkey AS debit_key, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey = debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "
							+ (KISHU - 1) + "  AND yyyy * 10000 + mm * 100 + dd < " + YMD_FROM
							+ " AND journals.devcode <'100' GROUP BY journals.drkey HAVING ((Not (Max(journals.draccode)) Is NULL))");
			QueryDefs(sql, view_name);

			view_name = "credit_expense_item_totals_repetitive_remaining";
			sql = new StringBuffer(
					"SELECT DISTINCTROW journals.crkey AS credit_key, Max(journals.devcode) AS department_code, Max(credit_department_subjects_specific.devname) AS department_name,Max(journals.craccode) AS credit_item_code,Max(credit_department_subjects_specific.acname) AS credit_course_title,Max(journals.cracsubcode) AS credit_details_code, Max(credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd> "
							+ (KISHU - 1) + "  AND yyyy * 10000 + mm * 100 + dd < " + YMD_FROM
							+ " AND journals.devcode <'100' GROUP BY journals.crkey HAVING ((Not (Max(journals.craccode)) Is NULL))");
			QueryDefs(sql, view_name);

			view_name = "balance_before_provision";
			sql = new StringBuffer(
					"SELECT DISTINCTROW actables.keycode,actables.devcode,actables.accode,actables.acname,actables.acsubcode,actables.acsubname,(IFNULL(actables.budget,0) + IFNULL(actables.budgetmd,0)) as budget, bgbalance + IFNULL(debit_amount,0) - IFNULL(credit_amount,0) AS balance_before_provision FROM actables LEFT JOIN debit_expense_item_totals_repetitive_remaining ON actables.keycode = debit_expense_item_totals_repetitive_remaining.debit_key LEFT JOIN credit_expense_item_totals_repetitive_remaining ON actables.keycode = credit_expense_item_totals_repetitive_remaining.credit_key WHERE actables.devcode <'100'");
			QueryDefs(sql, view_name);

			view_name = "debit_expense_item_totals";
			sql = new StringBuffer(
					"SELECT DISTINCTROW journals.drkey, Max(journals.devcode) AS department_code, Max(debit_department_subjects_specific.devname) AS department_name, Max(journals.draccode) AS debit_item_code, Max(debit_department_subjects_specific.acname) AS debit_course_title, Max(journals.dracsubcode) AS debit_details_code, Max(debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(journals.dramount) AS debit_amount FROM journals LEFT JOIN debit_department_subjects_specific ON journals.drkey=debit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
							+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO
							+ " AND journals.devcode <'100' GROUP BY journals.drkey HAVING((Not(Max(journals.draccode)) IS NULL))");
			QueryDefs(sql, view_name);

			view_name = "credit_expense_item_totals";
			sql = new StringBuffer(
					"SELECT DISTINCTROW journals.crkey, MAX(journals.devcode) AS department_code, MAX(credit_department_subjects_specific.devname) AS department_name, MAX(journals.craccode) AS credit_item_code, MAX(credit_department_subjects_specific.acname) AS credit_course_title, MAX(journals.cracsubcode) AS credit_details_code, MAX(credit_department_subjects_specific.acsubname) AS credit_details_name, SUM(journals.cramount) AS credit_amount FROM journals LEFT JOIN credit_department_subjects_specific ON journals.crkey = credit_department_subjects_specific.aggregate_key WHERE yyyy * 10000 + mm * 100 + dd >="
							+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO
							+ " AND journals.devcode <'100' GROUP BY journals.crkey HAVING((Not(Max(journals.craccode)) Is Null))");
			QueryDefs(sql, view_name);
		}
		n = 1;

		return n;
	}

	public int TB_Q_SET2(String BUMON, long YMD_FROM, long YMD_TO, long KISHU) {
		// 対象範囲の試算表テーブルを作成する（科目コードで集約）
		int n = 0;
		String view_name;
		StringBuffer sql;

		// DoCmd.OpenQuery "科目キー仕訳テーブル作成"
		sql = new StringBuffer(
				"INSERT INTO subjects_key_journal (je_number,s_number,l_number,drkey,crkey,devcode,devname,draccode,dracname,dracsubcode,dracsubname,craccode,cracname,cracsubcode,cracsubname,yyyy,mm,dd,dramount,cramount,balance,descode,desname,memo,transtime,sundry,vendorcode,vendorname,otherdata,prjcode,prjname,drctax,crctax) SELECT DISTINCTROW journals.je_number,journals.s_number,journals.l_number, CONCAT(devcode,draccode) AS drkey,CONCAT(devcode,craccode) AS crkey, journals.devcode,journals.devname,journals.draccode,journals.dracname,journals.dracsubcode,journals.dracsubname,journals.craccode,journals.cracname,journals.cracsubcode,journals.cracsubname,journals.yyyy,journals.mm,journals.dd,journals.dramount,journals.cramount,journals.balance,journals.descode,journals.desname,journals.memo,journals.transtime,journals.sundry,journals.vendorcode,journals.vendorname,journals.otherdata,journals.prjcode,journals.prjname,journals.drctax,journals.crctax FROM journals");
		TableDefs(sql, "subjects_key_journal");

		view_name = "debit_department_subjects_totals_repetitive_remaining";
		sql = new StringBuffer(
				"SELECT DISTINCTROW subjects_key_journal.drkey AS debit_key, Max(subjects_key_journal.devcode) AS department_code, Max(debit_department_subjects.devname) AS department_name,Max(subjects_key_journal.draccode) AS debit_item_code, Max(debit_department_subjects.acname) AS debit_course_title, Sum(subjects_key_journal.dramount) AS debit_amount FROM subjects_key_journal LEFT JOIN debit_department_subjects ON subjects_key_journal.drkey =debit_department_subjects.aggregate_key WHERE (yyyy * 10000 + mm * 100 + dd > "
						+ (KISHU - 1) + ") AND (yyyy * 10000 +mm * 100 + dd < " + YMD_FROM
						+ ") AND subjects_key_journal.devcode = '" + BUMON
						+ "' GROUP BY subjects_key_journal.drkey HAVING((Not(Max(subjects_key_journal.draccode))Is Null))");
		QueryDefs(sql, view_name);

		view_name = "credit_department_subjects_totals_repetitive_remaining";
		sql = new StringBuffer(
				"SELECT DISTINCTROW subjects_key_journal.crkey AS credit_key, Max(subjects_key_journal.devcode) AS department_code, Max(credit_department_subjects.devname) AS department_name,Max(subjects_key_journal.craccode) AS credit_item_code, Max(credit_department_subjects.acname) AS debit_course_title, Sum(subjects_key_journal.cramount) AS credit_amount FROM credit_department_subjects RIGHT JOIN subjects_key_journal ON credit_department_subjects.aggregate_key = subjects_key_journal.crkey WHERE (yyyy * 10000 + mm * 100 + dd > "
						+ (KISHU - 1) + ")  AND (yyyy * 10000 +mm * 100 + dd < " + YMD_FROM
						+ ") AND subjects_key_journal.devcode ='" + BUMON
						+ "' GROUP BY subjects_key_journal.crkey HAVING((Not(Max(subjects_key_journal.craccode))Is Null))");
		QueryDefs(sql, view_name);

		view_name = "before_provision_balance_aggregation";
		sql = new StringBuffer(
				"SELECT DISTINCTROW actablesaggregation2.aggregate_key,actablesaggregation2.department_code,actablesaggregation2.item_code,actablesaggregation2.item_name,actablesaggregation2.budget,(actablesaggregation2.beginning_balance + IFNULL(debit_department_subjects_totals_repetitive_remaining.debit_amount,0) - IFNULL(credit_department_subjects_totals_repetitive_remaining.credit_amount,0)) AS balance_before_provision FROM actablesaggregation2 LEFT JOIN debit_department_subjects_totals_repetitive_remaining ON actablesaggregation2.aggregate_key = debit_department_subjects_totals_repetitive_remaining.debit_key LEFT JOIN credit_department_subjects_totals_repetitive_remaining ON actablesaggregation2.aggregate_key = credit_department_subjects_totals_repetitive_remaining.credit_key WHERE actablesaggregation2.department_code = '"
						+ BUMON + "'");
		QueryDefs(sql, view_name);

		view_name = "debit_department_subjects_totals";
		sql = new StringBuffer(
				"SELECT DISTINCTROW subjects_key_journal.drkey AS debit_key, Max(subjects_key_journal.devcode) AS department_code, Max(debit_department_subjects.devname) AS department_name, Max(subjects_key_journal.draccode) AS debit_item_code, Max(debit_department_subjects.acname) AS debit_course_title,Sum(subjects_key_journal.dramount) AS debit_amount FROM subjects_key_journal LEFT JOIN debit_department_subjects ON subjects_key_journal.drkey = debit_department_subjects.aggregate_key WHERE (yyyy * 10000 + mm * 100 + dd >= "
						+ YMD_FROM + ") AND (yyyy * 10000 + mm * 100 + dd <= " + YMD_TO
						+ ") AND (subjects_key_journal.devcode = '" + BUMON
						+ "') GROUP BY subjects_key_journal.drkey HAVING((Not(Max(subjects_key_journal.draccode))Is Null))");
		QueryDefs(sql, view_name);

		view_name = "credit_department_subjects_totals";
		sql = new StringBuffer(
				"SELECT DISTINCTROW subjects_key_journal.crkey, Max(subjects_key_journal.devcode) AS department_code, Max(credit_department_subjects.devname) AS department_name, Max(subjects_key_journal.craccode) AS credit_item_code, Max(credit_department_subjects.acname) AS credit_course_title,Sum(subjects_key_journal.cramount) AS credit_amount FROM subjects_key_journal LEFT JOIN credit_department_subjects ON subjects_key_journal.crkey=credit_department_subjects.aggregate_key WHERE (yyyy * 10000 + mm * 100 + dd >= "
						+ YMD_FROM + ") AND (yyyy * 10000 + mm * 100 + dd <= " + YMD_TO
						+ ")AND (subjects_key_journal.devcode = '" + BUMON
						+ "') GROUP BY subjects_key_journal.crkey HAVING ((Not(Max(subjects_key_journal.craccode))Is Null))");
		QueryDefs(sql, view_name);

		n = 1;

		return n;
	}

	public int TB_Q_SET3(String BUMON, long YMD_FROM, long YMD_TO, long KISHU) {
		// 対象範囲の取引先別試算表テーブルを作成する
		int n = 0;
		String view_name;
		StringBuffer sql;

		view_name = "vendor_specific_debit_expense_item_totals_repetitive_remaining";
		sql = new StringBuffer(
				"SELECT DISTINCTROW trading_destination_journal.drkey, Max(trading_destination_journal.devcode) AS department_code,Max(customer_specific_debit_department_subjects_specific.devname) AS department_name, Max(trading_destination_journal.draccode) AS debit_item_code,Max(customer_specific_debit_department_subjects_specific.acname) AS debit_course_title, Max(trading_destination_journal.dracsubcode) AS debit_details_code,Max(customer_specific_debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(trading_destination_journal.dramount) AS debit_amount FROM trading_destination_journal LEFT JOIN customer_specific_debit_department_subjects_specific ON trading_destination_journal.drkey = customer_specific_debit_department_subjects_specific.keycode WHERE (yyyy * 10000 + mm * 100 + dd) > "
						+ (KISHU - 1) + " AND (yyyy * 10000 + mm * 100 + dd) < " + YMD_FROM
						+ " AND trading_destination_journal.devcode = ' " + BUMON
						+ "' GROUP BY trading_destination_journal.drkey HAVING(Not(Max(trading_destination_journal.draccode))Is Null)");
		QueryDefs(sql, view_name);

		view_name = "vendor_specific_credit_expense_item_totals_repetitive_remaining";
		sql = new StringBuffer(
				"SELECT DISTINCTROW trading_destination_journal.crkey, Max(trading_destination_journal.devcode) AS department_code,Max(customer_specific_credit_department_subjects_specific.devname) AS department_name, Max(trading_destination_journal.craccode) AS credit_item_code,Max(customer_specific_credit_department_subjects_specific.acname) AS credit_course_title, Max(trading_destination_journal.cracsubcode) AS credit_details_code,Max(customer_specific_credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(trading_destination_journal.cramount) AS credit_amount FROM trading_destination_journal LEFT JOIN customer_specific_credit_department_subjects_specific ON trading_destination_journal.crkey = customer_specific_credit_department_subjects_specific.keycode WHERE (yyyy * 10000 + mm * 100 + dd) > "
						+ (KISHU - 1) + " AND (yyyy * 10000 + mm * 100 + dd) < " + YMD_FROM
						+ " AND trading_destination_journal.devcode = ' " + BUMON
						+ "' GROUP BY trading_destination_journal.crkey HAVING(Not(Max(trading_destination_journal.craccode))Is Null)");
		QueryDefs(sql, view_name);

		view_name = "vendor_balance_before_provision";
		sql = new StringBuffer(
				"SELECT DISTINCTROW vendor_specific_subject_code.keycode,vendor_specific_subject_code.devcode,vendor_specific_subject_code.accode,actables.acname,vendor_specific_subject_code.acsubcode,actables.acsubname,IFNULL(vendor_specific_subject_code.balance,0) + IFNULL(vendor_specific_debit_expense_item_totals_repetitive_remaining.debit_amount,0) - IFNULL(vendor_specific_credit_expense_item_totals_repetitive_remaining.credit_amount,0) AS balance_before_provision,vendor_specific_subject_code.vendorcode,vendors.vendorname FROM vendors RIGHT JOIN actables RIGHT JOIN vendor_specific_subject_code LEFT JOIN vendor_specific_debit_expense_item_totals_repetitive_remaining ON vendor_specific_subject_code.keycode = vendor_specific_debit_expense_item_totals_repetitive_remaining.debit_item_code LEFT JOIN vendor_specific_credit_expense_item_totals_repetitive_remaining ON vendor_specific_subject_code.keycode = vendor_specific_credit_expense_item_totals_repetitive_remaining.credit_item_code ON actables.devcode = vendor_specific_subject_code.devcode AND actables.accode = vendor_specific_subject_code.accode AND actables.acsubcode = vendor_specific_subject_code.acsubcode ON vendors.vendorcode = vendor_specific_subject_code.vendorcode WHERE vendor_specific_subject_code.devcode ='"
						+ BUMON + "'");
		QueryDefs(sql, view_name);

		view_name = "vendor_debit_expense_item_totals";
		sql = new StringBuffer(
				"SELECT DISTINCTROW trading_destination_journal.drkey, Max(trading_destination_journal.devcode) AS department_code, Max(customer_specific_debit_department_subjects_specific.devname) AS department_name, Max(trading_destination_journal.draccode) AS debit_item_code, Max(customer_specific_debit_department_subjects_specific.acname) AS debit_course_title, Max(trading_destination_journal.dracsubcode) AS debit_details_code, Max(customer_specific_debit_department_subjects_specific.acsubname) AS debit_details_name, Sum(trading_destination_journal.dramount) AS debit_amount FROM trading_destination_journal LEFT JOIN customer_specific_debit_department_subjects_specific ON trading_destination_journal.drkey = customer_specific_debit_department_subjects_specific.keycode WHERE yyyy * 10000 + mm * 100 + dd >= "
						+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <=" + YMD_TO
						+ " AND trading_destination_journal.devcode = '" + BUMON
						+ "' GROUP BY trading_destination_journal.drkey HAVING((Not(Max(trading_destination_journal.draccode)) Is Null))");
		QueryDefs(sql, view_name);

		view_name = "vendor_credit_expense_item_totals";
		sql = new StringBuffer(
				"SELECT DISTINCTROW trading_destination_journal.crkey, Max(trading_destination_journal.devcode) AS department_code, Max(customer_specific_credit_department_subjects_specific.devname) AS department_name, Max(trading_destination_journal.craccode) AS credit_item_code, Max(customer_specific_credit_department_subjects_specific.acname) AS credit_course_title, Max(trading_destination_journal.cracsubcode) AS credit_details_code, Max(customer_specific_credit_department_subjects_specific.acsubname) AS credit_details_name, Sum(trading_destination_journal.cramount) AS credit_amount FROM trading_destination_journal LEFT JOIN customer_specific_credit_department_subjects_specific ON trading_destination_journal.crkey = customer_specific_credit_department_subjects_specific.keycode WHERE yyyy * 10000 + mm * 100 + dd >= "
						+ YMD_FROM + " AND yyyy * 10000 + mm * 100 + dd <= " + YMD_TO
						+ " AND trading_destination_journal.devcode = '" + BUMON
						+ "' GROUP BY trading_destination_journal.crkey HAVING((Not(Max(trading_destination_journal.craccode)) Is Null))");
		QueryDefs(sql, view_name);

		n = 1;

		return n;
	}

	public void CallReport(String rptName, String param) {
		JasperReportViewer nw = JasperReportViewer.getInstance(rptName, param);
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
