package com.ey.application.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.ey.application.controller.DBManager;


public class FrmDescription extends FrmBase {
	// Variables declaration - do not modify                     
    private WiderDropDownCombo cmbCraccode;
    private WiderDropDownCombo cmbCrctax;
    private WiderDropDownCombo cmbDebCode;
    private WiderDropDownCombo cmbDebCode1;
    private WiderDropDownCombo cmbDraccode;
    private WiderDropDownCombo cmbDrctax;
    private JButton cmdAdd;
    private JButton cmdDelete;
    private JButton cmdUpdate;
    private JButton cmdClear;
    private JButton cmdExit;
    private JButton cmdPrinting;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JTable table;
    private DefaultTableModel model;
    private JPanel pnlControl;
    private JPanel pnlMain;
    private JTextField txtCracname;
    private JTextField txtCracsubcode;
    private JTextField txtCracsubname;
    private JTextField txtCramount;
    private JTextField txtDescode;
    private JTextField txtDesname;
    private JTextField txtDevname1;
    private JTextField txtDracname;
    private JTextField txtDracsubcode;
    private JTextField txtDracsubname;
    private JTextField txtDramount;
    private JTextField txtDesid;
    private int row;
    // End of variables declaration  
    
	/**
     * Creates new form Description
     */
    public FrmDescription() {
    	jbInit();
    }
    
    private void jbInit() {

        pnlMain = new JPanel();
        pnlControl = new JPanel();
        jLabel1 = new JLabel();
        cmdExit = new JButton();
        jPanel1 = new JPanel();
        jLabel2 = new JLabel();
        cmbDebCode = new WiderDropDownCombo();
        cmdPrinting = new JButton();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        cmbDebCode1 = new WiderDropDownCombo();
        txtDesname = new JTextField();
        txtDevname1 = new JTextField();
        txtDracsubname = new JTextField();
        cmbDraccode = new WiderDropDownCombo();
        txtDracsubcode = new JTextField();
        txtDescode = new JTextField();
        txtDramount = new JTextField();
        txtDracname = new JTextField();
        cmbDrctax = new WiderDropDownCombo();
        cmbCraccode = new WiderDropDownCombo();
        txtCracsubcode = new JTextField();
        txtCracname = new JTextField();
        txtCracsubname = new JTextField();
        txtCramount = new JTextField();
        cmbCrctax = new WiderDropDownCombo();
        cmdDelete = new JButton();
        cmdAdd = new JButton();
        cmdUpdate = new JButton();
        cmdClear = new JButton();
        txtDesid= new JTextField();
        
        departmentCode();
        debitSubjectCode();
        consumptionTaxClassification();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				Close();
			}
		});
        setBackground(new java.awt.Color(176, 224, 230));
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(176, 224, 230));
        pnlMain.setLayout(null);

        pnlControl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlControl.setOpaque(false);
        pnlControl.setLayout(null);

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("摘要登録・修正");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setOpaque(true);
        pnlControl.add(jLabel1);
        jLabel1.setBounds(10, 10, 230, 29);

        cmdExit.setText("閉じる");
        pnlControl.add(cmdExit);
        cmdExit.setBounds(390, 10, 108, 40);
        cmdExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        jLabel2.setText("部門別　　印刷");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 5, 90, 30);

        jPanel1.add(cmbDebCode);
        cmbDebCode.setBounds(110, 5, 170, 30);
        cmbDebCode.setWide(true);
        
        cmdPrinting.setText("印　刷");
        jPanel1.add(cmdPrinting);
        cmdPrinting.setBounds(290, 5, 90, 30);
        cmdPrinting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TEKIYO_PRINT();
			}
        });
        

        pnlControl.add(jPanel1);
        jPanel1.setBounds(510, 10, 390, 40);

        jLabel3.setText("借方勘定細目");
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlControl.add(jLabel3);
        jLabel3.setBounds(300, 88, 200, 22);

        jLabel4.setText("消費税");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pnlControl.add(jLabel4);
        jLabel4.setBounds(505, 88, 50, 22);

        jLabel5.setText("摘　要　の　内　容");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlControl.add(jLabel5);
        jLabel5.setBounds(95, 60, 200, 50);

        jLabel6.setText("借方勘定科目");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlControl.add(jLabel6);
        jLabel6.setBounds(300, 60, 200, 22);

        jLabel7.setText("摘要コード");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pnlControl.add(jLabel7);
        jLabel7.setBounds(10, 60, 80, 22);

        jLabel8.setText("借方金額");
        jLabel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pnlControl.add(jLabel8);
        jLabel8.setBounds(505, 60, 95, 22);

        jLabel9.setText("貸方勘定科目");
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlControl.add(jLabel9);
        jLabel9.setBounds(605, 60, 200, 22);

        jLabel10.setText("貸方金額");
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pnlControl.add(jLabel10);
        jLabel10.setBounds(810, 60, 95, 22);

        jLabel11.setText("貸方勘定細目");
        jLabel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlControl.add(jLabel11);
        jLabel11.setBounds(605, 90, 200, 22);

        jLabel12.setText("消費税");
        jLabel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        pnlControl.add(jLabel12);
        jLabel12.setBounds(810, 90, 50, 22);

        pnlControl.add(cmbDebCode1);
        cmbDebCode1.setBounds(10, 115, 80, 30);
        cmbDebCode1.setWide(true);
        
        pnlControl.add(txtDesname);
        txtDesname.setBounds(95, 150, 200, 65);
        
        pnlControl.add(txtDevname1);
        txtDevname1.setBounds(95, 115, 405, 30);
        
        pnlControl.add(txtDracsubname);
        txtDracsubname.setBounds(385, 186, 115, 30);

        pnlControl.add(cmbDraccode);
        cmbDraccode.setBounds(300, 150, 80, 30);
        cmbDraccode.setWide(true);
        
        pnlControl.add(txtDracsubcode);
        txtDracsubcode.setBounds(300, 186, 80, 30);
        pnlControl.add(txtDescode);
        
        txtDescode.setBounds(10, 150, 80, 30);
        pnlControl.add(txtDramount);
        txtDramount.setBounds(505, 150, 95, 30);
        txtDramount.setHorizontalAlignment(JTextField.RIGHT);
        txtDramount.setText("0");
        
        pnlControl.add(txtDracname);
        txtDracname.setBounds(385, 150, 115, 30);
        
        txtDesid.setBounds(10,180,60,30);
        txtDesid.setVisible(false);
        pnlControl.add(txtDesid);

        pnlControl.add(cmbDrctax);
        cmbDrctax.setBounds(505, 186, 80, 30);
        cmbDrctax.setWide(true);

        pnlControl.add(cmbCraccode);
        cmbCraccode.setBounds(605, 150, 80, 30);
        cmbCraccode.setWide(true);
        
        pnlControl.add(txtCracsubcode);
        txtCracsubcode.setBounds(605, 186, 80, 30);
        
        pnlControl.add(txtCracname);
        txtCracname.setBounds(690, 150, 115, 30);
        
        pnlControl.add(txtCracsubname);
        txtCracsubname.setBounds(690, 186, 115, 30);
        
        pnlControl.add(txtCramount);
        txtCramount.setBounds(810, 150, 95, 30);
        txtCramount.setHorizontalAlignment(JTextField.RIGHT);
        txtCramount.setText("0");

        pnlControl.add(cmbCrctax);
        cmbCrctax.setBounds(810, 186, 80, 30);
        cmbCrctax.setWide(true);
        
        model = new DefaultTableModel();
        table = new JTable(model);
        
        cmdDelete.setText("削除");
        pnlControl.add(cmdDelete);
        cmdDelete.setBounds(460, 220, 80, 30);
        cmdDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete();
				Clear();
				loadDescriptionData();
			}
        });
        
        cmdAdd.setText("追加");
        pnlControl.add(cmdAdd);
        cmdAdd.setBounds(280, 220 , 80, 30);
        cmdAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add();
				Clear();
				loadDescriptionData();
			}
        });
        
        cmdUpdate.setText("更新");
        pnlControl.add(cmdUpdate);
        cmdUpdate.setBounds(370, 220 , 80, 30);
        cmdUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update();
				Clear();
				loadDescriptionData();
			}
        });
        
        cmdClear.setText("クリア");
        pnlControl.add(cmdClear);
        cmdClear.setBounds(550, 220, 80, 30);
        cmdClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
        });

        pnlMain.add(pnlControl);
        pnlControl.setBounds(10, 10, 910, 260);
        
        
        
        model.addColumn("id");
        model.addColumn("部門コード");
        model.addColumn("部門名");
        model.addColumn("借方科目コード");
        model.addColumn("借方科目名");
        model.addColumn("借方細目コード");
        model.addColumn("借方細目名");
        model.addColumn("貸方科目コード");
        model.addColumn("貸方科目名");
        model.addColumn("貸方細目コード");
        model.addColumn("貸方細目名");
        model.addColumn("借方金額");
        model.addColumn("貸方金額");
        model.addColumn("摘要コード");
        model.addColumn("摘要名");
        model.addColumn("借方消費税区分");
        model.addColumn("貸方消費税区分");   
        
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(70);
        table.getColumnModel().getColumn(8).setPreferredWidth(90);
        table.getColumnModel().getColumn(9).setPreferredWidth(70);
        table.getColumnModel().getColumn(10).setPreferredWidth(100);
        table.getColumnModel().getColumn(11).setPreferredWidth(70);
        table.getColumnModel().getColumn(12).setPreferredWidth(100);
        table.getColumnModel().getColumn(13).setPreferredWidth(100);
        table.getColumnModel().getColumn(14).setPreferredWidth(70);
        table.getColumnModel().getColumn(15).setPreferredWidth(100);
        table.getColumnModel().getColumn(16).setPreferredWidth(70);
                
        table.addMouseListener(new java.awt.event.MouseAdapter()
        {
        	public void mouseClicked(java.awt.event.MouseEvent e)
        	{
        		row= table.rowAtPoint(e.getPoint());
        		//int col= table.columnAtPoint(e.getPoint());
        		Clear(); 		
        		
        		txtDesid.setText(table.getModel().getValueAt(row,0).toString());
        		cmbDebCode1.setSelectedItem(table.getModel().getValueAt(row,1).toString(),true);
        		txtDevname1.setText(table.getModel().getValueAt(row,2).toString());
        		cmbDraccode.setSelectedItem(table.getModel().getValueAt(row,3).toString(),true);
        		txtDracname.setText(table.getModel().getValueAt(row,4).toString());
        		txtDracsubcode.setText(table.getModel().getValueAt(row,5).toString());
        		txtDracsubname.setText(table.getModel().getValueAt(row,6).toString());
        		cmbCraccode.setSelectedItem(table.getModel().getValueAt(row,7).toString(),true);
        		txtCracname.setText(table.getModel().getValueAt(row,8).toString());
        	    txtCracsubcode.setText(table.getModel().getValueAt(row,9).toString());
        	    txtCracsubname.setText(table.getModel().getValueAt(row,10).toString());
        	    String val = table.getModel().getValueAt(row,11).toString();
        	    if(!val.equals(""))
        	    	txtDramount.setText(val);
        	    else
        	    	txtDramount.setText("0");
        	    val = "";
        	    val = table.getModel().getValueAt(row,12).toString();
        	    if(!val.equals(""))
        	    	txtCramount.setText(val);
        	    else
        	    	txtCramount.setText("0");
        	    txtDescode.setText(table.getModel().getValueAt(row,13).toString());
        	    txtDesname.setText(table.getModel().getValueAt(row,14).toString());
        	    cmbDrctax.setSelectedItem(table.getModel().getValueAt(row,15).toString(),true);
        	    cmbCrctax.setSelectedItem(table.getModel().getValueAt(row,16).toString(),true);
        	    
        	}
        });
        table.setBorder(UIManager.getBorder("DesktopIcon.border"));
        table.setRowHeight(27);
        
        TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn(table.getColumnModel().getColumn(0));
        
        JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.getViewport().add(table);

        pnlMain.add(sp, new Integer(0), 0);
        sp.setBounds(10, 280, 910, 240);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        sp.setOpaque(true);
        sp.getViewport().setBackground(new Color(176,224,230));

        getContentPane().add(pnlMain);
        pnlMain.setBounds(0, 0, 930, 530);
        
        loadDescriptionData();
        
        
    }
    
    private static FrmDescription myInstance;

	public static FrmDescription getInstance() {
		myInstance = new FrmDescription();
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
	
	public void loadDescriptionData() {
		try {
			DBManager db = new DBManager();
			try {
				DefaultTableModel dtm = (DefaultTableModel)table.getModel();
				ResultSet rs;
				rs = db.getRecord("SELECT description.desid,description.devcode,description.devname,description.draccode,description.dracname,description.dracsubcode,"
						+ "description.dracsubname,description.craccode,description.cracname,description.cracsubcode,description.cracsubname,"
						+ "description.dramount,description.cramount,description.descode,description.desname,description.drctax,"
						+ "description.crctax FROM description ORDER BY description.descode");
				
				NumberFormat formatter = new DecimalFormat("#0.00");
				//NumberFormat formatter1 = new DecimalFormat("#0.00000");
				
				int rowCount = dtm.getRowCount();
				for(int i=0;i<rowCount;i++)
					dtm.removeRow(0);
				
				while(rs.next()) {
					String desid=String.valueOf(rs.getInt(1));
					String devcode=rs.getString(2);
					String devname=rs.getString(3);
					String draccode=rs.getString(4);
					String dracname=rs.getString(5);
					String dracsubcode=rs.getString(6);
					String dracsubname=rs.getString(7);
					String craccode=rs.getString(8);
					String cracname=rs.getString(9);
					String cracsubcode=rs.getString(10);
					String cracsubname=rs.getString(11);
					
					double debitAmount = rs.getDouble(12);
					String dramount= "0";
					if(debitAmount != 0)
						dramount=formatter.format(debitAmount);
						
					double creditAmount=rs.getDouble(13);
					String cramount="";
					if(creditAmount != 0)
						cramount=String.valueOf(creditAmount);
					
					String descode=rs.getString(14);
					String desname=rs.getString(15);
					String drctax=rs.getString(16);
					String crctax=rs.getString(17);
															
					dtm.addRow(new Object[] {desid,devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname, cracsubcode, cracsubname,dramount,cramount,descode,desname,drctax,crctax});
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
	
	public void departmentCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT devcode, devname FROM devtables");
				cmbDebCode.addItem("");
				cmbDebCode1.addItem("");
				while (rs.next()) {
					String devcode = rs.getString(1);
					String devname = rs.getString(2);

					String Iteam = devcode + " | " + devname;
					cmbDebCode.addItem(Iteam);
					cmbDebCode1.addItem(Iteam);
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
	
	public void debitSubjectCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT DISTINCTROW devcodeselect.agkey, devcodeselect.acname, devcodeselect.acsubname FROM devcodeselect");
				cmbDraccode.addItem("");
				cmbCraccode.addItem("");
				while (rs.next()) {
					String agkey = rs.getString(1);
					String acname = rs.getString(2);
					String acsubname = rs.getString(3);

					String Iteam = agkey + " | " + acname + " | " + acsubname;
					cmbDraccode.addItem(Iteam);
					cmbCraccode.addItem(Iteam);
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
	
	public void consumptionTaxClassification() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT DISTINCTROW ctc.code,ctc.name FROM ctc");
				cmbDrctax.addItem("");
				cmbCrctax.addItem("");
				while (rs.next()) {
					String code = rs.getString(1);
					String name = rs.getString(2);

					String Iteam = code + " | " + name;
					cmbDrctax.addItem(Iteam);
					cmbCrctax.addItem(Iteam);
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
	
	public void Clear(){
		cmbDebCode1.setSelectedItem("");
		txtDevname1.setText("");
		cmbDraccode.setSelectedItem("");
		txtDracname.setText("");
		txtDracsubcode.setText("");
		txtDracsubname.setText("");
		cmbCraccode.setSelectedItem("");
		txtCracname.setText("");
	    txtCracsubcode.setText("");
	    txtCracsubname.setText("");
	    txtDramount.setText("0");
	    txtCramount.setText("0");
	    txtDescode.setText("");
	    txtDesname.setText("");
	    cmbDrctax.setSelectedItem("");
	    cmbCrctax.setSelectedItem("");
	}
	
	public void Update(){
		String sqlUpdate = "UPDATE description SET "
				+ "devcode='"+cmbDebCode1.getSelectedItem().toString().split(" | ")[0]+"',"
				+ "devname='"+txtDevname1.getText()+"',"
				+ "draccode='"+cmbDraccode.getSelectedItem().toString().split(" | ")[0]+"',"
				+ "dracname='"+txtDracname.getText()+"',"
				+ "dracsubcode='"+txtDracsubcode.getText()+"',"
				+ "dracsubname='"+txtDracsubname.getText()+"',"
				+ "craccode='"+cmbCraccode.getSelectedItem().toString().split(" | ")[0]+"',"
				+ "cracname='"+txtCracname.getText()+"',"
				+ "cracsubcode='"+txtCracsubcode.getText()+"',"
				+ "cracsubname='"+txtCracsubname.getText()+"',"
				+ "dramount="+txtDramount.getText()+","
				+ "cramount="+txtCramount.getText()+","
				+ "descode='"+txtDescode.getText()+"',"
				+ "desname='"+txtDesname.getText()+"',"
				+ "drctax='"+cmbDrctax.getSelectedItem().toString().split(" | ")[0]+"',"
				+ "crctax='"+cmbCrctax.getSelectedItem().toString().split(" | ")[0]+"' "
				+ "WHERE desid="+txtDesid.getText();
				
				
		QueryDefsUpdate(sqlUpdate);
	}
	
	public void Delete(){
		String sqlDelete = "DELETE FROM description WHERE desid="+txtDesid.getText();
		QueryDefsUpdate(sqlDelete);
	}
	
	public void Add(){
		String sqlAdd = "INSERT INTO description (devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname, cracsubcode, cracsubname, dramount, cramount, descode, desname, drctax, crctax) "
				+ "VALUES('"+cmbDebCode1.getSelectedItem().toString().split(" | ")[0] + "','"
				+ txtDevname1.getText()+ "','" +cmbDraccode.getSelectedItem().toString().split(" | ")[0] + "','" +txtDracname.getText() + "','"
				+ txtDracsubcode.getText()+ "','"+txtDracsubname.getText() + "','" + cmbCraccode.getSelectedItem().toString().split(" | ")[0] + "','"
				+ txtCracname.getText() + "','" + txtCracsubcode.getText()+ "','" + txtCracsubname.getText()+ "'," + txtDramount.getText()+ "," + txtCramount.getText() + ",'"
				+ txtDescode.getText()+ "','" + txtDesname.getText()+ "','" + cmbDrctax.getSelectedItem().toString().split(" | ")[0] + "','" + cmbCrctax.getSelectedItem().toString().split(" | ")[0]+"')";
		QueryDefsUpdate(sqlAdd);
	}
	
	public void TEKIYO_PRINT(){
		//DoCmd.OpenReport "摘要テーブル印刷", acPreview
		String param="";
		if(cmbDebCode.getSelectedItem().toString().split(" | ")[0].equals("")){
			JOptionPane.showMessageDialog(null, "部門コードを選択してください。");
			return;
		}
		param = cmbDebCode.getSelectedItem().toString().split(" | ")[0];
		CallReport("rptAbstractTablePrinting",param);
	}
}
