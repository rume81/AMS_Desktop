package com.ey.application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import com.ey.application.controller.DBManager;
import com.ey.application.model.Jurnal;
import com.ey.application.view.FrmAccountRegister.MyTableModel;
import com.lowagie.toolbox.plugins.DvdCover;

public class FrmJournalEntry extends FrmBase {
	
	private JTable CompTable = null;
	private DefaultTableModel CompModel = null;
    //private JButton addButton = null;
    
	
	JPanel pnlMain 				= new JPanel();
	JPanel pnlRow 				= new JPanel();
	
	JLabel lblJournalEntry 		= new JLabel();
	JLabel lblYear 				= new JLabel();
	JLabel lblMonth 			= new JLabel();
	JLabel lblDay 				= new JLabel();
	JLabel lblNo 				= new JLabel();
	JLabel lblLabelNo 			= new JLabel();
	JLabel lblDebitAccount 		= new JLabel();
	JLabel lblDebitAmount 		= new JLabel();
	JLabel lblCreditAccount 	= new JLabel();
	JLabel lblCreditAmount 		= new JLabel();
	JLabel lblVendor 			= new JLabel();
	JLabel lblDebitAccountSub 	= new JLabel();
	JLabel lblDebitTax 			= new JLabel();
	JLabel lblCreditAccountSub 	= new JLabel();
	JLabel lblDebitTax2 		= new JLabel();
	JLabel lblProject 			= new JLabel();
	JLabel lblDescription 		= new JLabel();
	
	JButton cmdSave 			= new JButton();
	JButton cmdSave2 			= new JButton();
	JButton cmdPrint 			= new JButton();
	JButton cmdExit 			= new JButton();
	JButton cmdInsert 			= new JButton();
	JButton cmdNextRecord 		= new JButton();
	JButton cmdPreviousRecord 	= new JButton();
	JButton cmdAllLineCopy 		= new JButton();
	JButton cmdAllLinePaste 	= new JButton();
	JButton cmdAllLineErase 	= new JButton();
	JButton cmdRecordDelete 	= new JButton();
	JButton cmdVendorEntry 		= new JButton();
	JButton cmdAmountClear 		= new JButton();
	JButton cmdSlipCopy 		= new JButton();
	JButton cmdAllCopy 			= new JButton();
	JButton cmdAllPaste 			= new JButton();
	
	JTextField txtYear 			= new JTextField();
	JTextField txtMonth 		= new JTextField();
	JTextField txtDay 			= new JTextField();
	JTextField txtNo 			= new JTextField();
	JTextField txtJournalNumber = new JTextField();
	JTextField txtDevName 		= new JTextField();
	
	JComboBox cmbDebCode 		= new JComboBox();
	JComboBox cmbSlipCopy 		= new JComboBox();
	JScrollPane CompTableScrollpane = null;
	StringBuffer qdfJE 			= new StringBuffer("");
	
	int SelectedRowForEdit = 0;
	
	private JTextField txtLabelNo 			= new JTextField();
	private JTextField txtDebitAccount 		= new JTextField();
	private JText txtDevitAmount 			= new JText(20,'f');
	private JTextField txtCreditAccount 	= new JTextField();
	private JText txtCreditAmount 			= new JText(20,'f');
	private JTextField txtVendor 			= new JTextField();
	private JTextField txtDebitAccountSub 	= new JTextField();
	private JTextField txtDebitAccountSub2 	= new JTextField();
	private JTextField txtCreditAccountSub 	= new JTextField();	
	private JTextField txtCreditAccountSub2 = new JTextField();	
	private JTextField txtProject 			= new JTextField();
	private JTextField txtDescription 		= new JTextField();
	private JTextField txtDebitAccode 		= new JTextField();
	private JTextField txtCreditAccode 		= new JTextField();
	
	private JComboBox cmbDebitAccount 		= new JComboBox();
	private JComboBox cmbCreditAccount 		= new JComboBox();
	private JComboBox cmbVendor 			= new JComboBox();
	private JComboBox cmbDebitTax 			= new JComboBox();
	private JComboBox cmbDebitTax2 			= new JComboBox();
	private JComboBox cmbProject 			= new JComboBox();
	private JComboBox cmbDescription 		= new JComboBox();
	
	private JButton cmdCopy 				= new JButton();
	private JButton cmdPaste 				= new JButton();
	
	private JButton cmdAdd					= new JButton();
	private JButton cmdClear 				= new JButton();
	
	private int FURIDEN_MODE;
	private String serchJournalNo = "";
	
	public FrmJournalEntry(int FURIDEN_MODE) {
		try {
			this.FURIDEN_MODE = FURIDEN_MODE;
			serchJournalNo = "";
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		this.getContentPane().add(pnlMain);
		this.getContentPane().add(pnlRow);
		
		//this.setTitle("会計システム" & Format(rst!決算年) & "年" & Format(rst!決算月) & "月期");
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				Close();
			}
		});
		
		pnlMain.setBackground(new Color(176, 224, 230));
		pnlMain.setBorder(BorderFactory.createEtchedBorder());
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pnlMain.setBounds(9, 9, ScreenSize.width-50, 207);
		pnlMain.setLayout(null);
		
		lblJournalEntry.setText("振替伝票");
		lblJournalEntry.setBounds(20, 20, 80, 30);
		lblJournalEntry.setVisible(true);
		lblJournalEntry.setBorder(BorderFactory.createRaisedBevelBorder());
		lblJournalEntry.setHorizontalAlignment(SwingConstants.CENTER);
		/*lblJournalEntry.setHorizontalTextPosition(SwingConstants.CENTER);*/
		
		lblYear.setText("年");
		lblYear.setBounds(170, 20, 20, 30);
		lblYear.setVisible(true);
		lblYear.setBorder(null);
		//lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblMonth.setText("月");
		lblMonth.setBounds(225, 20, 20, 30);
		lblMonth.setVisible(true);
		lblMonth.setBorder(null);
		
		lblDay.setText("日");
		lblDay.setBounds(285, 20, 20, 30);
		lblDay.setVisible(true);
		lblDay.setBorder(null);
		
		lblNo.setText("伝票番号");
		lblNo.setBounds(20, 60, 60, 30);
		lblNo.setVisible(true);
		lblNo.setBorder(BorderFactory.createLoweredBevelBorder());
		
		lblLabelNo.setText("行番");
		lblLabelNo.setBounds(20, 110, 30, 30);
		lblLabelNo.setVisible(true);
		lblLabelNo.setBorder(BorderFactory.createLoweredBevelBorder());
		
		lblDebitAccount.setText("借方勘定科目");
		lblDebitAccount.setBounds(55, 110, 365, 30);
		lblDebitAccount.setVisible(true);
		lblDebitAccount.setBorder(BorderFactory.createLoweredBevelBorder());
		lblDebitAccount.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDebitAmount.setText("借方金額");
		lblDebitAmount.setBounds(420, 110, 120, 30);
		lblDebitAmount.setVisible(true);
		lblDebitAmount.setBorder(BorderFactory.createLoweredBevelBorder());
		lblDebitAmount.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblCreditAccount.setText("貸方勘定科目");
		lblCreditAccount.setBounds(545, 110, 375, 30);
		lblCreditAccount.setVisible(true);
		lblCreditAccount.setBorder(BorderFactory.createLoweredBevelBorder());
		lblCreditAccount.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblCreditAmount.setText("貸方金額");
		lblCreditAmount.setBounds(920, 110, 125, 30);
		lblCreditAmount.setVisible(true);
		lblCreditAmount.setBorder(BorderFactory.createLoweredBevelBorder());
		lblCreditAmount.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblVendor.setText("取引先");
		lblVendor.setBounds(1050, 110, 220, 30);
		lblVendor.setVisible(true);
		lblVendor.setBorder(BorderFactory.createLoweredBevelBorder());
		lblVendor.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDebitAccountSub.setText("借方勘定細目");
		lblDebitAccountSub.setBounds(55, 140, 365, 30);
		lblDebitAccountSub.setVisible(true);
		lblDebitAccountSub.setBorder(BorderFactory.createLoweredBevelBorder());
		lblDebitAccountSub.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDebitTax.setText("消費税");
		lblDebitTax.setBounds(420, 140, 120, 30);
		lblDebitTax.setVisible(true);
		lblDebitTax.setBorder(BorderFactory.createLoweredBevelBorder());
		lblDebitTax.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblCreditAccountSub.setText("貸方勘定細目");
		lblCreditAccountSub.setBounds(545, 140, 375, 30);
		lblCreditAccountSub.setVisible(true);
		lblCreditAccountSub.setBorder(BorderFactory.createLoweredBevelBorder());
		lblCreditAccountSub.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDebitTax2.setText("消費税");
		lblDebitTax2.setBounds(920, 140, 125, 30);
		lblDebitTax2.setVisible(true);
		lblDebitTax2.setBorder(BorderFactory.createLoweredBevelBorder());
		lblDebitTax2.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblProject.setText("プロジェクト");
		lblProject.setBounds(1050, 140, 220, 30);
		lblProject.setVisible(true);
		lblProject.setBorder(BorderFactory.createLoweredBevelBorder());
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDescription.setText("摘　　　　　　　　要");
		lblDescription.setBounds(55, 170, 1215, 30);
		lblDescription.setVisible(true);
		lblDescription.setBorder(BorderFactory.createLoweredBevelBorder());
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		
		cmdSave.setText("登録");
		cmdSave.setVisible(true);
		cmdSave.setBounds(310, 20, 70, 30);
		cmdSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				if (CompTable.getCellEditor() != null)
					CompTable.getCellEditor().stopCellEditing();
				save();
				FURIDEN_TO_SIWAKE(1);
				
				if(FURIDEN_MODE != 2){
					int totalRows = CompTable.getModel().getRowCount();
					for(int i=0;i<totalRows;i++) {
						CompModel.removeRow(0);
					}
				}
				txtLabelNo.setText(String.valueOf(getLineNumber()));
			}
		});
		
		cmdSave2.setText("登録2");
		cmdSave2.setVisible(true);
		cmdSave2.setBounds(385, 20, 70, 30);
		cmdSave2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (CompTable.getCellEditor() != null)
					CompTable.getCellEditor().stopCellEditing();
				save();
				FURIDEN_TO_SIWAKE(2);
				
				/*int totalRows = CompTable.getModel().getRowCount();
				for(int i=0;i<totalRows;i++) {
					CompModel.removeRow(0);
				}*/
				txtLabelNo.setText(String.valueOf(getLineNumber()));
			}
		});
		
		cmdPrint.setText("印刷");
		cmdPrint.setVisible(true);
		cmdPrint.setBounds(460, 20, 70, 30);
		
		cmdExit.setText("閉じる");
		cmdExit.setVisible(true);
		cmdExit.setBounds(535, 20, 70, 30);
		cmdExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});
		
		cmdInsert.setText("挿入");
		cmdInsert.setVisible(true);
		cmdInsert.setBounds(610, 20, 70, 30);
		
		cmdNextRecord.setText("次の伝票");
		cmdNextRecord.setVisible(true);
		cmdNextRecord.setBounds(685, 20, 80, 30);
		
		cmdPreviousRecord.setText("前の伝票");
		cmdPreviousRecord.setVisible(true);
		cmdPreviousRecord.setBounds(770, 20, 80, 30);
		
		cmdAllLineCopy.setText("全行コピー");
		cmdAllLineCopy.setVisible(true);
		cmdAllLineCopy.setBounds(855, 20, 90, 30);
		
		cmdAllLinePaste.setText("全行貼付");
		cmdAllLinePaste.setVisible(true);
		cmdAllLinePaste.setBounds(950, 20, 80, 30);
		
		cmdAllLineErase.setText("全行消去");
		cmdAllLineErase.setVisible(true);
		cmdAllLineErase.setBounds(1035, 20, 80, 30);
		
		cmdRecordDelete.setText("伝票削除");
		cmdRecordDelete.setVisible(false);
		cmdRecordDelete.setBounds(1110, 20, 50, 30);
		
		cmdVendorEntry.setText("取引先登録");
		cmdVendorEntry.setVisible(true);
		cmdVendorEntry.setBounds(370, 60, 120, 30);
		
		cmdAmountClear.setText("金額のみクリア");
		cmdAmountClear.setVisible(true);
		cmdAmountClear.setBounds(500, 60, 120, 30);
		
		cmdSlipCopy.setText("伝票コピー");
		cmdSlipCopy.setVisible(true);
		cmdSlipCopy.setBounds(630, 60, 120, 30);
		
		cmdAllCopy.setText("登録");
		cmdAllCopy.setVisible(false);
		cmdAllCopy.setBounds(240, 100, 100, 30);
		
		cmdAllPaste.setText("登録");
		cmdAllPaste.setVisible(false);
		cmdAllPaste.setBounds(500, 100, 100, 30);
		
		DateFormat Dateyear = new SimpleDateFormat("yyyy");
		Date date = new Date();
		txtYear.setText(Dateyear.format(date));
		txtYear.setVisible(true);
		txtYear.setBounds(120, 20, 50, 30);
		
		DateFormat DateMonth = new SimpleDateFormat("MM");
		txtMonth.setText(DateMonth.format(date));
		txtMonth.setVisible(true);
		txtMonth.setBounds(195, 20, 30, 30);
		
		DateFormat DateDay = new SimpleDateFormat("dd");
		txtDay.setText(DateDay.format(date));
		txtDay.setVisible(true);
		txtDay.setBounds(255, 20, 30, 30);
		
		txtNo.setVisible(true);
		txtNo.setBounds(90, 60, 80, 30);
		
		txtJournalNumber.setVisible(false);
		txtJournalNumber.setBounds(90,30,30,30);
		
		txtDevName.setText("name");
		txtDevName.setVisible(true);
		txtDevName.setBounds(260, 60, 100, 30);
		
		cmbDebCode.setVisible(true);
		cmbDebCode.setBounds(180, 60, 80, 30);
		cmbDebCode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				BumonSansho();
			}
		});
		
		cmbSlipCopy.setVisible(true);
		cmbSlipCopy.setBounds(760, 60, 80, 30);
		
		pnlMain.add(lblJournalEntry);
		pnlMain.add(lblYear);
		pnlMain.add(lblMonth);
		pnlMain.add(lblDay);
		pnlMain.add(lblNo);
		pnlMain.add(lblLabelNo);
		pnlMain.add(lblDebitAccount);
		pnlMain.add(lblJournalEntry);
		pnlMain.add(lblCreditAccount);
		pnlMain.add(lblDebitAmount);
		pnlMain.add(lblCreditAmount);
		pnlMain.add(lblVendor);
		pnlMain.add(lblDebitAccountSub);
		pnlMain.add(lblDebitTax);
		pnlMain.add(lblCreditAccountSub);
		pnlMain.add(lblDebitTax2);
		pnlMain.add(lblProject);
		pnlMain.add(lblDescription);
		pnlMain.add(cmdSave);
		pnlMain.add(cmdSave2);
		pnlMain.add(cmdPrint);
		pnlMain.add(cmdExit);
		pnlMain.add(cmdInsert);
		pnlMain.add(cmdNextRecord);
		pnlMain.add(cmdPreviousRecord);
		pnlMain.add(cmdAllLineCopy);
		pnlMain.add(cmdAllLinePaste);
		pnlMain.add(cmdAllLineErase);
		pnlMain.add(cmdRecordDelete);
		pnlMain.add(cmdVendorEntry);
		pnlMain.add(cmdAmountClear);
		pnlMain.add(cmdSlipCopy);
		pnlMain.add(cmdAllCopy);
		pnlMain.add(cmdAllPaste);
		pnlMain.add(txtYear);
		pnlMain.add(txtMonth);
		pnlMain.add(txtDay);
		pnlMain.add(txtNo);
		pnlMain.add(txtJournalNumber);
		pnlMain.add(txtDevName);
		pnlMain.add(cmbDebCode);
		pnlMain.add(cmbSlipCopy);
		
		
		pnlRow.setBackground(new Color(176, 224, 230));
		pnlRow.setBorder(BorderFactory.createEtchedBorder());
		pnlRow.setBounds(9, 230, ScreenSize.width-50, 140);
		pnlRow.setLayout(null);
		
		cmbDebitAccount.addItemListener(new ItemListener(){
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		    	if (e.getStateChange() == ItemEvent.SELECTED) {
			    	//txtLabelNo.setText("1");
		    		//cmbCreditAccount.requestFocus();
		    		////////////
		    		String value = String.valueOf(cmbDebitAccount.getSelectedItem());
            		if(!value.equals("")){
            			String[] splitVal = value.split(" | ");
            			String key = splitVal[0].trim();
            			getDetails(key,1);
            		}	    	
            		
            		//txtDevitAmount.grabFocus();
		    	}
		    }
		});
		
		setSize(220,105);
		setBorder(BorderFactory.createEtchedBorder());
		setBackground(new Color(176,224,230));
		
		txtLabelNo.setVisible(true);
		txtLabelNo.setEditable(false);
		txtLabelNo.setBounds(20, 5, 35, 30);
		
		
		txtDebitAccount.setVisible(true);
		txtDebitAccount.setBounds(315, 5, 100, 30);
		txtDebitAccount.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  	change();
			  }
			  public void removeUpdate(DocumentEvent e) {
				change();
			  }
			  public void insertUpdate(DocumentEvent e) {
				change();
			  }

			  public void change() {
				  if (FURIDEN_MODE==2){
					  CompModel.setValueAt(txtDebitAccount.getText(), SelectedRowForEdit, 2);
				  }
			  }
		});
		
		txtDevitAmount.setVisible(true);
		txtDevitAmount.setBounds(415, 5, 125, 30);
		/*txtDevitAmount.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		      }
		      public void focusLost(FocusEvent e) {
		    	  cmbCreditAccount.requestFocus();
		      }
		});*/
		txtDevitAmount.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
            	txtCreditAmount.setText(txtDevitAmount.getText());
            	change();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
            	txtCreditAmount.setText(txtDevitAmount.getText());
            	change();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            	change();
            }
            
            public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtDevitAmount.getText(), SelectedRowForEdit, 6);
				}
			}
        });
		
		txtCreditAccount.setVisible(true);
		txtCreditAccount.setBounds(800, 5, 120, 30);
		/*txtCreditAmount.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		      }
		      public void focusLost(FocusEvent e) {
		    	 //cmbDebitAccount.grabFocus();
		      }
		});*/
		txtCreditAccount.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				change();
			}
			public void removeUpdate(DocumentEvent e) {
				change();
			}
			public void insertUpdate(DocumentEvent e) {
				change();
			}

			public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtCreditAccount.getText(), SelectedRowForEdit, 5);
				}
			}
		});
		
		txtCreditAmount.setVisible(true);
		txtCreditAmount.setBounds(920, 5, 125, 30);
		txtCreditAmount.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
            	//txtDevitAmount.setText(txtCreditAmount.getText());
            	change();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
            	//txtDevitAmount.setText(txtCreditAmount.getText());
            	change();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            	change();
            }
            
            public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtCreditAmount.getText(), SelectedRowForEdit, 3);
				}
			}
        });
		
		txtVendor.setVisible(true);
		txtVendor.setBounds(1105, 5, 160, 30);
		txtVendor.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				change();
			}
			public void removeUpdate(DocumentEvent e) {
				change();
			}
			public void insertUpdate(DocumentEvent e) {
				change();
			}

			public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtVendor.getText(), SelectedRowForEdit, 8);
				}
			}
		});
		/*txtVendor.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		      }
		      public void focusLost(FocusEvent e) {
		    	 cmbDebitTax.requestFocus();
		      }
		});*/
		
		txtDebitAccountSub.setVisible(true);
		txtDebitAccountSub.setBounds(55, 35, 260, 30);
		txtDebitAccountSub.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				change();
			}
			public void removeUpdate(DocumentEvent e) {
				change();
			}
			public void insertUpdate(DocumentEvent e) {
				change();
			}

			public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtDebitAccountSub.getText(), SelectedRowForEdit, 9);
				}
			}
		});
		
		txtDebitAccountSub2.setVisible(true);
		txtDebitAccountSub2.setBounds(315, 35, 100, 30);
		txtDebitAccountSub2.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				change();
			}
			public void removeUpdate(DocumentEvent e) {
				change();
			}
			public void insertUpdate(DocumentEvent e) {
				change();
			}

			public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtDebitAccountSub2.getText(), SelectedRowForEdit, 10);
				}
			}
		});
		
		txtCreditAccountSub.setVisible(true);
		txtCreditAccountSub.setBounds(540, 35, 260, 30);
		txtCreditAccountSub.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				change();
			}
			public void removeUpdate(DocumentEvent e) {
				change();
			}
			public void insertUpdate(DocumentEvent e) {
				change();
			}

			public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtCreditAccountSub.getText(), SelectedRowForEdit, 12);
				}
			}
		});
		
		txtCreditAccountSub2.setVisible(true);
		txtCreditAccountSub2.setBounds(800, 35, 120, 30);
		txtCreditAccountSub2.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				change();
			}
			public void removeUpdate(DocumentEvent e) {
				change();
			}
			public void insertUpdate(DocumentEvent e) {
				change();
			}

			public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtCreditAccountSub2.getText(), SelectedRowForEdit, 13);
				}
			}
		});
		
		txtProject.setVisible(true);
		txtProject.setBounds(1105, 35, 160, 30);
		txtProject.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				change();
			}
			public void removeUpdate(DocumentEvent e) {
				change();
			}
			public void insertUpdate(DocumentEvent e) {
				change();
			}

			public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtProject.getText(), SelectedRowForEdit, 16);
				}
			}
		});
		/*txtProject.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		      }
		      public void focusLost(FocusEvent e) {
		    	 cmbDescription.requestFocus();
		      }
		});*/
		
		txtDescription.setVisible(true);
		txtDescription.setBounds(315, 65, 950, 30);
		txtDescription.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				change();
			}
			public void removeUpdate(DocumentEvent e) {
				change();
			}
			public void insertUpdate(DocumentEvent e) {
				change();
			}
			public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtDescription.getText(), SelectedRowForEdit, 18);
				}
			}
		});
		
		cmbDebitAccount.setVisible(true);
		cmbDebitAccount.setBounds(55, 5, 200, 30);
		
		txtDebitAccode.setVisible(true);
		txtDebitAccode.setBounds(255, 5, 60, 30);
		txtDebitAccode.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				change();
			}
			public void removeUpdate(DocumentEvent e) {
				change();
			}
			public void insertUpdate(DocumentEvent e) {
				change();
			}

			public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtDebitAccode.getText(), SelectedRowForEdit, 1);
				}
			}
		});
		
		cmbCreditAccount.setVisible(true);
		cmbCreditAccount.setBounds(540, 5, 200, 30);
		cmbCreditAccount.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
            	if(e.getStateChange() == ItemEvent.SELECTED) {
            		String value = String.valueOf(cmbCreditAccount.getSelectedItem());
            		if(!value.equals("")){
            			String[] splitVal = value.split(" | ");
            			String key = splitVal[0].trim();
            			getDetails(key,2);
            		}
            		//cmbVendor.grabFocus();
            	}
            }
        });
		
		txtCreditAccode.setVisible(true);
		txtCreditAccode.setBounds(740, 5, 60, 30);
		txtCreditAccode.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				change();
			}
			public void removeUpdate(DocumentEvent e) {
				change();
			}
			public void insertUpdate(DocumentEvent e) {
				change();
			}

			public void change() {
				if (FURIDEN_MODE==2){
					CompModel.setValueAt(txtCreditAccode.getText(), SelectedRowForEdit, 4);
				}
			}
		});
		
		cmbVendor.setVisible(true);
		cmbVendor.setBounds(1045, 5, 60, 30);
		cmbVendor.addItem("");
		cmbVendor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(!String.valueOf(cmbVendor.getSelectedItem()).equals("")) {
						TORI_SANSHO(101);
					}
				}
				if (FURIDEN_MODE==2){
					String val = String.valueOf(cmbVendor.getSelectedItem());
					if(val.equals("null"))
						CompModel.setValueAt("", SelectedRowForEdit, 7);
					else
						CompModel.setValueAt(val, SelectedRowForEdit, 7);
				}
			}
		});
		
		cmbDebitTax.setVisible(true);
		cmbDebitTax.setBounds(415, 35, 60, 30);
		getDebitTaxCombo();
		cmbDebitTax.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (FURIDEN_MODE==2){
					String val = String.valueOf(cmbDebitTax.getSelectedItem());
					if(val.equals("null"))
						CompModel.setValueAt("", SelectedRowForEdit, 11);
					else
						CompModel.setValueAt(val, SelectedRowForEdit, 11);
				}
			}
		});
		
		cmbDebitTax2.setVisible(true);
		cmbDebitTax2.setBounds(920, 35, 55, 30);
		getDebitTaxCombo2();
		cmbDebitTax2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (FURIDEN_MODE==2){
					String val = String.valueOf(cmbDebitTax2.getSelectedItem());
					if(val.equals("null"))
						CompModel.setValueAt("", SelectedRowForEdit, 14);
					else
						CompModel.setValueAt(val, SelectedRowForEdit, 14);
				}
			}
		});
		
		cmbProject.setVisible(true);
		cmbProject.setBounds(1045, 35, 60, 30);
		cmbProject.addItem("");
		cmbProject.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()== ItemEvent.SELECTED) {
					PRJCODE();
				}
				//txtProject.grabFocus();
				if (FURIDEN_MODE==2){
					String val = String.valueOf(cmbProject.getSelectedItem());
					if(val.equals("null"))
						CompModel.setValueAt("", SelectedRowForEdit, 15);
					else
						CompModel.setValueAt(val, SelectedRowForEdit, 15);
				}
			}
		});
		
		cmbDescription.setVisible(true);
		cmbDescription.setBounds(55, 65, 260, 30);
		cmbDescription.addItem("");
		cmbDescription.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//txtDescription.grabFocus();
				if (FURIDEN_MODE==2){
					String val = String.valueOf(cmbDescription.getSelectedItem());
					if(val.equals("null"))
						CompModel.setValueAt("", SelectedRowForEdit, 17);
					else
						CompModel.setValueAt(val, SelectedRowForEdit, 17);
				}
			}
		});
		
		cmdCopy.setText("Copy");
		cmdCopy.setVisible(true);
		cmdCopy.setBounds(475, 35, 65, 30);
		
		cmdPaste.setText("Paste");
		cmdPaste.setVisible(true);
		cmdPaste.setBounds(975, 35, 70, 30);
		
		cmdAdd.setText("いれる");
		cmdAdd.setVisible(true);
		cmdAdd.setBounds(500, 100, 80, 30);
		cmdAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector nRow = addRow();
				if(nRow!=null)
					CompModel.addRow(nRow);
				clear();
			}
		});
		
		cmdClear.setText("クリア");
		cmdClear.setVisible(true);
		cmdClear.setBounds(600, 100, 80, 30);
		cmdClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		
		setLayout(null);
		
		pnlRow.add(txtLabelNo);
		pnlRow.add(txtDebitAccount);
		pnlRow.add(txtDevitAmount);
		pnlRow.add(txtCreditAccount);
		pnlRow.add(txtCreditAmount);
		pnlRow.add(txtVendor);
		pnlRow.add(txtDebitAccountSub);
		pnlRow.add(txtDebitAccountSub2);
		pnlRow.add(txtCreditAccountSub);
		pnlRow.add(txtCreditAccountSub2);
		pnlRow.add(txtProject);
		pnlRow.add(txtDescription);
		
		pnlRow.add(cmbDebitAccount);
		pnlRow.add(txtDebitAccode);
		pnlRow.add(cmbCreditAccount);
		pnlRow.add(txtCreditAccode);
		pnlRow.add(cmbVendor);
		
		pnlRow.add(cmbDebitTax);
		pnlRow.add(cmbProject);
		pnlRow.add(cmbDescription);
		pnlRow.add(cmbDebitTax2);
		pnlRow.add(cmdCopy);
		pnlRow.add(cmdPaste);
		pnlRow.add(cmdAdd);
		pnlRow.add(cmdClear);
		
		
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
        Set<KeyStroke> keys = new HashSet<>();
		keys.add(enter);
        keys.add(tab);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, keys);
		
		
			
		
		
		CompModel = new DefaultTableModel();
        CompTable = new JTable(CompModel);
        
        CompModel.addColumn("行番"); 
        CompModel.addColumn("借方科目コード");
        CompModel.addColumn("借方科目名");
        CompModel.addColumn("借方金額");
        CompModel.addColumn("貸方科目コード");
        CompModel.addColumn("貸方科目名");
        CompModel.addColumn("貸方金額");
        CompModel.addColumn("取引先コード");
        CompModel.addColumn("取引先名");
        
        CompModel.addColumn("借方細目コード");
        CompModel.addColumn("借方細目名");
        CompModel.addColumn("借方消費税区分");
        CompModel.addColumn("貸方細目コード");
        CompModel.addColumn("貸方細目名");
        CompModel.addColumn("貸方消費税区分");
        CompModel.addColumn("PRJCODE");
        CompModel.addColumn("PRJNAME");
        CompModel.addColumn("摘要コード");
        CompModel.addColumn("摘要名");
        
        CompTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        CompTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        CompTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        CompTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        CompTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        CompTable.getColumnModel().getColumn(5).setPreferredWidth(80);
        CompTable.getColumnModel().getColumn(6).setPreferredWidth(70);
        CompTable.getColumnModel().getColumn(7).setPreferredWidth(90);
        CompTable.getColumnModel().getColumn(8).setPreferredWidth(70);
        CompTable.getColumnModel().getColumn(9).setPreferredWidth(100);
        CompTable.getColumnModel().getColumn(10).setPreferredWidth(70);
        CompTable.getColumnModel().getColumn(11).setPreferredWidth(100);
        CompTable.getColumnModel().getColumn(12).setPreferredWidth(100);
        CompTable.getColumnModel().getColumn(13).setPreferredWidth(70);
        CompTable.getColumnModel().getColumn(14).setPreferredWidth(100);
        CompTable.getColumnModel().getColumn(15).setPreferredWidth(70);
        CompTable.getColumnModel().getColumn(16).setPreferredWidth(70);
        CompTable.getColumnModel().getColumn(17).setPreferredWidth(70);
        CompTable.getColumnModel().getColumn(18).setPreferredWidth(50);
        
        CompTable.addMouseListener(new java.awt.event.MouseAdapter()
        {
        	public void mouseClicked(java.awt.event.MouseEvent e)
        	{
        		if (FURIDEN_MODE==2){
        			SelectedRowForEdit= CompTable.rowAtPoint(e.getPoint());
        		        		       		
        			setValueFromTableToField(SelectedRowForEdit);
        		
        		}
        	    
        	}
        });
        //CompModel.addRow(new Object[]{"Column 1", "Column 2", "Column 3"});
        
        JScrollPane CompTableScrollpane = new JScrollPane(CompTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        CompTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        CompTableScrollpane.setBounds(9, 390, ScreenSize.width-50, 180);
        CompTableScrollpane.setOpaque(true);
        CompTableScrollpane.getViewport().setBackground(new Color(176,224,230));
        
        this.getContentPane().add(CompTableScrollpane);
        //this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        departmentCode();
        journalCopy();
        descriptionCode();
        projectCode();
        vendorCode();
        
        txtLabelNo.setText(String.valueOf(getLineNumber()));
       
        if(FURIDEN_MODE==2){
        	getSearchData();
        }
	}//End jbInit
	
	public void clear() {
		cmbDebitAccount.setSelectedItem("");
		txtDebitAccode.setText("");
		txtDebitAccount.setText("");
		txtDevitAmount.setText("");
		cmbCreditAccount.setSelectedItem("");
		txtCreditAccode.setText("");
		txtCreditAccount.setText("");
		txtCreditAmount.setText("");
		cmbVendor.setSelectedItem("");
		txtVendor.setText("");
		txtDebitAccountSub.setText("");
		txtDebitAccountSub2.setText("");
		cmbDebitTax.setSelectedItem("");
		txtCreditAccountSub.setText("");
		txtCreditAccountSub2.setText("");
		cmbDebitTax2.setSelectedItem("");
		cmbProject.setSelectedItem("");
		txtProject.setText("");
		cmbDescription.setSelectedItem("");
		txtDescription.setText("");
		txtLabelNo.setText(String.valueOf(getLineNumber()));
		
	}
	
	public int getLineNumber() {
		int lineNum = 0;
		lineNum = CompModel.getRowCount();
		lineNum++;
		//System.out.println(lineNum);
		return lineNum;
	}
	
	public Vector addRow() {
		
		String lineNum 				= txtLabelNo.getText();
		String debitAccountCode 	= txtDebitAccode.getText();
		String debitAccountName 	= txtDebitAccount.getText();
		String debitAmount 			= txtDevitAmount.getText();
		String creditAccountCode 	= txtCreditAccode.getText();
		String creditAccountName 	= txtCreditAccount.getText();
		String creditAmount 		= txtCreditAmount.getText();
		String vendorCode = "";			
		if(!cmbVendor.getSelectedItem().equals(""))
		vendorCode= String.valueOf(cmbVendor.getSelectedItem());
		
		String vendorName 			= txtVendor.getText();
		String debitDetailsCode 	= txtDebitAccountSub.getText();
		String debitDeailsName 		= txtDebitAccountSub2.getText();
		String debitConsumptionTax 	= "";
		if(cmbDebitTax.getSelectedItem()!=null)		
		debitConsumptionTax = String.valueOf(cmbDebitTax.getSelectedItem());
		
		String creditSpecificCode 	= txtCreditAccountSub.getText();
		String creditSpecificName 	= txtCreditAccountSub2.getText();
		
		String CreditConsumptionTax = "";
		if(cmbDebitTax2.getSelectedItem()!=null)
		CreditConsumptionTax = String.valueOf(cmbDebitTax2.getSelectedItem());
		
		String projectCode 			= "";
		if(!cmbProject.getSelectedItem().equals(""))
		projectCode =		String.valueOf(cmbProject.getSelectedItem());
		String projectName = txtProject.getText();
		
		String DescriptionCode 		= "";
		if(!cmbDescription.getSelectedItem().equals(""))
		DescriptionCode = String.valueOf(cmbDescription.getSelectedItem());
		String descriptionName 		= txtDescription.getText();
		
		Vector ob = null;
		
		if(debitAccountCode.equals("") && debitAccountName.equals("") && debitAmount.equals("") && creditAccountCode.equals("") && creditAccountName.equals("") && creditAmount.equals("") && vendorCode.equals("")
				&& vendorName.equals("") && debitDetailsCode.equals("") && debitDeailsName.equals("") && debitConsumptionTax.equals("") && creditSpecificCode.equals("") && creditSpecificName.equals("") && CreditConsumptionTax.equals("") && projectCode.equals("") && projectName.equals("") && DescriptionCode.equals("") && descriptionName.equals("")) 
		{
			
		} else {
			ob = new Vector();
		ob.add(lineNum);
		ob.add(debitAccountCode);
		ob.add(debitAccountName);
		ob.add(debitAmount);
		ob.add(creditAccountCode);
		ob.add(creditAccountName);
		ob.add(creditAmount);
		ob.add(vendorCode);
		ob.add(vendorName);
		ob.add(debitDetailsCode);
		ob.add(debitDeailsName);
		ob.add(debitConsumptionTax);
		ob.add(creditSpecificCode);
		ob.add(creditSpecificName);
		ob.add(CreditConsumptionTax);
		ob.add(projectCode);
		ob.add(projectName);
		ob.add(DescriptionCode);
		ob.add(descriptionName);
		}
		return ob;
		
	}
	
	public void getDebitTaxCombo()
	{
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT acparameters.keycode, acparameters.taxcr FROM acparameters");
				cmbDebitTax.addItem("");
				while (rs.next()) {
					String val = rs.getString(2);
					cmbDebitTax.addItem(val);
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
	
	public void getDebitTaxCombo2()
	{
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT acparameters.keycode, acparameters.taxcr FROM acparameters");
				cmbDebitTax2.addItem("");
				while (rs.next()) {
					String val = rs.getString(2);
					cmbDebitTax2.addItem(val);
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
	
	public void getDetails(String key,int SHORI){
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT * FROM kaikei.devcodeselect WHERE agkey ='"+key+"'");
				
				if(rs.next()) {
					String subject_code = rs.getString(3);
					String DebitAccount = rs.getString(4);
					String DebitAccountSub = rs.getString(5);
					String DebitAccountSub2 = rs.getString(6);
					String DebitTax = rs.getString(7);
					if(SHORI==1){
						txtDebitAccode.setText(subject_code);
						txtDebitAccount.setText(DebitAccount);
						txtDebitAccountSub.setText(DebitAccountSub);
						txtDebitAccountSub2.setText(DebitAccountSub2);
						cmbDebitTax.setSelectedItem(DebitTax);
					} else{
						txtCreditAccode.setText(subject_code);
						txtCreditAccount.setText(DebitAccount);
						txtCreditAccountSub.setText(DebitAccountSub);
						txtCreditAccountSub2.setText(DebitAccountSub2);
						cmbDebitTax2.setSelectedItem(DebitTax);
					}
				} else{
					JOptionPane.showMessageDialog(null, "該当科目がありません");
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
	
	public void setDropDownDefultValue()
    {
    	if(cmbDebitAccount.getItemCount()>0)
			cmbDebitAccount.setSelectedIndex(0);
		if(cmbCreditAccount.getItemCount()>0)
			cmbCreditAccount.setSelectedIndex(0);
    }
	
	
	public void save() {
		/*try {
			DBManager dbC = new DBManager();
						
			try{
				StringBuffer strSql = new StringBuffer("DELETE FROM jewrk");
				boolean fo1= dbC.doQuery(strSql.toString());
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//Extra by ALA
		deletejewrk();
		try {
			DBManager db = new DBManager();
			
			int totalRows = CompTable.getModel().getRowCount();
			int saveCount = 0;
			for(int i = 0; i<totalRows; i++) {
				
				int je_number = 0; 	
				if(!txtJournalNumber.getText().equals(""))
				je_number = Integer.parseInt(txtJournalNumber.getText());
				
				int s_number =0;
				if(!txtNo.getText().equals(""))
				s_number = Integer.parseInt(txtNo.getText());
				
				String devcode		= String.valueOf(cmbDebCode.getSelectedItem());
				String devname		= txtDevName.getText();
				
				int l_number = Integer.parseInt(String.valueOf(CompTable.getModel().getValueAt(i, 0)));
				
				int yyyy  = 0;
				if(!txtYear.getText().equals(""))
				yyyy	= Integer.parseInt(txtYear.getText());
				int mm =0;
				if(!txtMonth.getText().equals(""))
				mm	= Integer.parseInt(txtMonth.getText());
				
				int dd =0;
				if(!txtDay.getText().equals(""))
				dd			= Integer.parseInt(txtDay.getText());
				
				String draccode		= String.valueOf(CompTable.getModel().getValueAt(i, 1));
				
				String dracname		= String.valueOf(CompTable.getModel().getValueAt(i, 2));
				
				Double dramount=0.0D;
				if(!String.valueOf(CompTable.getModel().getValueAt(i, 3)).equals(""))
					dramount 	=Double.parseDouble(String.valueOf(CompTable.getModel().getValueAt(i, 3)));
				
				String cracode		= String.valueOf(CompTable.getModel().getValueAt(i, 4));
				String cracname 	= String.valueOf(CompTable.getModel().getValueAt(i, 5));
				
				Double cramount = 0.0D;
				
				if(!CompTable.getModel().getValueAt(i, 6).equals(""))
					cramount = Double.parseDouble(String.valueOf(CompTable.getModel().getValueAt(i, 6)));
				
				String vendorcode	= String.valueOf(CompTable.getModel().getValueAt(i, 7));
				String vendorname	= String.valueOf(CompTable.getModel().getValueAt(i, 8));
				String dracsubcode	= String.valueOf(CompTable.getModel().getValueAt(i, 9));
				String dracsubname	= String.valueOf(CompTable.getModel().getValueAt(i, 10));
				String drctax		= String.valueOf(CompTable.getModel().getValueAt(i, 11));
				String cracsubcode	= String.valueOf(CompTable.getModel().getValueAt(i, 12));
				String cracsbname	= String.valueOf(CompTable.getModel().getValueAt(i, 13));
				String crctax		= String.valueOf(CompTable.getModel().getValueAt(i, 14));
				String prjcode		= String.valueOf(CompTable.getModel().getValueAt(i, 15));
				String prjname		= String.valueOf(CompTable.getModel().getValueAt(i, 16));
				String descode		= String.valueOf(CompTable.getModel().getValueAt(i, 17));
				String desname		= String.valueOf(CompTable.getModel().getValueAt(i, 18));
			
				if(l_number==0 && draccode.equals("") && dracname.equals("")&& dramount ==0 && cracode.equals("") && cracname.equals("") && cramount==0 && vendorcode.equals("")
						&& vendorname.equals("") && dracsubcode.equals("") && dracsubname.equals("") && drctax.equals("") && cracsubcode.equals("") && cracsbname.equals("") && crctax.equals("") && prjcode.equals("") && prjname.equals("") && descode.equals("") && desname.equals("")) 
				{
					continue;
				} else {
					
					PreparedStatement pstm =db.getPreparStamt("INSERT INTO jewrk(je_number,s_number,devcode,devname,l_number,draccode,dracname,dramount,craccode,cracname,cramount,vendorcode,vendorname,"
							+ "dracsubcode,dracsubname,drctax,cracsubcode,cracsubname,yyyy,mm,dd,crctax,prjcode,prjname,descode,desname) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
					
					//System.out.println("================================== devcode =============================="+devcode);
					pstm.setInt(1, je_number);
					pstm.setInt(2, s_number);
					pstm.setString(3, devcode);
					pstm.setString(4, devname);
					pstm.setInt(5, l_number);
					pstm.setString(6, draccode);
					pstm.setString(7, dracname);
					pstm.setDouble(8, dramount);
					pstm.setString(9, cracode);
					pstm.setString(10, cracname);
					pstm.setDouble(11, cramount);
					pstm.setString(12, vendorcode);
					pstm.setString(13, vendorname);
					pstm.setString(14, dracsubcode);
					pstm.setString(15, dracsubname);
					pstm.setString(16, drctax);
					pstm.setString(17, cracsubcode);
					pstm.setString(18, cracsbname);
					pstm.setInt(19, yyyy);
					pstm.setInt(20, mm);
					pstm.setInt(21, dd);
					pstm.setString(22, crctax);
					pstm.setString(23, prjcode);
					pstm.setString(24, prjname);
					pstm.setString(25, descode);
					pstm.setString(26, desname);
					
					pstm.executeUpdate();
					
					//Remove Row
					//PanelTableModel CompModel =(PanelTableModel) CompTable.getModel();
					//CompModel.removeRow(0);
				}
				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// End of jbInit
	private static FrmJournalEntry myInstance;

	public static FrmJournalEntry getInstance(int FURIDEN_MODE) {
		myInstance = new FrmJournalEntry(FURIDEN_MODE);
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
	
	
	/// For Bottom panel
	/*public JPanel CreateBottomPanel() {
	    addButton = new JButton("Add New Row");
	    addButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            Object source = ae.getSource();
	            if (source == addButton) {
	                CompModel.addRow();
	            }
	        }
	    });
	    JPanel panel = new JPanel(new GridBagLayout());
	    panel.add(addButton);
	    return panel;
	}*/
	
	public void departmentCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord(
						"SELECT devcode FROM devtables");
				//cmbPrintByDev.addItem("");
				while (rs.next()) {
					cmbDebCode.addItem(rs.getString(1));
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
	
	public void vendorCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord(
						"SELECT vendors.vendorcode, vendors.vendorname FROM vendors");
				//cmbPrintByDev.addItem("");
				while (rs.next()) {
					cmbVendor.addItem(rs.getString(1));
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
	
	public void projectCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord(
						"SELECT project_code.prjcode, project_code.prjname FROM project_code");
				//cmbPrintByDev.addItem("");
				while (rs.next()) {
					cmbProject.addItem(rs.getString(1));
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
	
	public void descriptionCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				String sql = "SELECT DISTINCT description.descode, MAX(description.desname) AS abstractname FROM description WHERE description.devcode LIKE CONCAT(MID("+cmbDebCode.getSelectedItem()+",1,2),'%') GROUP BY description.descode";
				//System.out.println(sql);
				rs = db.getRecord(sql);
				//cmbPrintByDev.addItem("");
				while (rs.next()) {
					cmbDescription.addItem(rs.getString(1));
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
	
	public void journalCopy() {
		String slipCopy = (String)cmbDebCode.getSelectedItem();
		
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord(
						"SELECT journals.s_number, journals.desname AS abstract_name FROM journals WHERE journals.devcode = '"+slipCopy+"'" 
						+ "GROUP BY journals.s_number ORDER BY journals.s_number DESC LIMIT 1");
				//cmbPrintByDev.addItem("");
				while (rs.next()) {
					cmbSlipCopy.addItem(rs.getString(1));
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
	
	public void BumonSansho() {
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rs;
				rs = db.getRecord(
						"SELECT devname FROM devtables WHERE devcode =" + cmbDebCode.getSelectedItem());
				//cmbPrintByDev.addItem("");
				if (rs.next()) {
					txtDevName.setText(rs.getString(1));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bumonSelect(String.valueOf(cmbDebCode.getSelectedItem()));
	}
	
	//("SELECT agkey, acname acsubname FROM devcodeselect");
	
	
	
	public void bumonSelect(String devcode) {
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
		// Needs for Shori 4
		/*try {
			DBManager db = new DBManager();
			DBManager dbC = new DBManager();
			float minAmount = 0;
			//if(!txtMinimumAmount.getText().equals(""))
				//minAmount = Float.parseFloat(txtMinimumAmount.getText());
			try {
				boolean fo = db.doQuery("DROP VIEW IF EXISTS bumonSelect");	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
			
			try{
				StringBuffer strSql = new StringBuffer("CREATE VIEW bumonSelect AS SELECT courses.subjectcode, MAX(courses.subjectname) AS subject_name FROM courses WHERE((courses.devcode) = '"+devcode+"') GROUP BY courses.subjectcode ORDER BY courses.subjectcode");
				boolean fo1= dbC.doQuery(strSql.toString());
				
				//System.out.println(strSql);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT devcodeselect.agkey, devcodeselect.acname, devcodeselect.acsubname FROM devcodeselect");
				
				Vector  modelo = new Vector();
				modelo.add("");
				
				cmbDebitAccount.addItem("");
				cmbCreditAccount.addItem("");
				while (rs.next()) {
					String val = rs.getString(1);
					String acname = rs.getString(2);
					String acsub = rs.getString(3);
					
					String Iteam = val+" | "+acname+" | "+acsub;
					cmbDebitAccount.addItem(Iteam);
					cmbCreditAccount.addItem(Iteam);
			         
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
	
	public void FURIDEN_TO_SIWAKE (int handan1){
		//To post the contents of the transfer slip to the journal table.
		//Database processing related
		//Start Variables
		String BUMON="";
		String BUMON_N="";
		long YY,MM,DD;
		int SHOKUCHI; 
		int SS;
		long LAST_DEN_NO=0;
		long LAST_SIWAKE_NO=0;
		int GYO;
		
		//Data in front of the slip
		int MAEBAN;
		int MAENEN;
		int MAETUKI;
		int MAEHI;
		String MAEBUMONCODE;
		String MAEBUMONNAME;
		long NEXT_NO;
		//End Variables
		
		//When in edit mode, delete the original data.
		if(FURIDEN_MODE == 2){
			deleteJournalData();
		} 
				
		//Retention of non-consolidated data
		BUMON = String.valueOf(cmbDebCode.getSelectedItem());
		BUMON_N = txtDevName.getText();
		YY = Long.parseLong(txtYear.getText());
		MM = Long.parseLong(txtMonth.getText());
		DD = Long.parseLong(txtDay.getText());
		
		//Judgment of composite journal
		SHOKUCHI = 0;
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rs = db.getRecord("SELECT jewrk.draccode,jewrk.dracsubcode, jewrk.craccode,"+
						"jewrk. cracsubcode, (sum(dramount) - sum(cramount)) AS difference "+ 
						"FROM jewrk GROUP BY jewrk.draccode, jewrk.dracsubcode, "+ 
						"jewrk.craccode, jewrk.cracsubcode");
				while(rs.next()) {
					double difference = rs.getDouble(5);
					if(difference!=0)
						SHOKUCHI = 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Judgment of composite journal
		SS = 1; //default is a simple serial number processing
		/*If F! Debit item code 1 = "1110" Or F! Credit courses code 1 = "1110" Or F! Debit item code 2 = "1110" Or F! Credit courses code 2 = "1110" Or F! Debit subjects code 3 = "1110" = Or F! credit courses code 3 "1110" Or F! debit item code 4 = "1110" Or F! credit courses code 4 = "1110" Or F! debit item code 5 = "1110" Or F! credit courses code 5 = "1110" Or F! debit item code 6 = "1110" Or F! credit courses code 6 = "1110" Or F! debit item code 7 = "1110" Or F! credit course code 7 = "1110" Or F! debit item code 8 = "1110" Or F! credit courses code 8 = "1110" Or F! debit item code 9 = "1110" Or F! credit courses code 9 = "1110" Or F! debit item code 10 = "1110" Or F! credit courses code 10 = "1110" Then
		SS = 1
		ElseIf (F! Debit item code 1> = "1120" And F! Debit item code 1 <= "1150") Or (F! Credit courses code 1> = "1120" And F! Credit courses code 1 <= " 1150 ") Or (F! debit item code 2> =" 1120 "And F! debit item code 2 <=" 1150 ") Or (F! credit courses code 2> =" 1120 "And F! credit courses code 2 < = "1150") Or (F! debit item code 3> = "1120" And F! debit item code 3 <= "1150") Or (F! credit courses code 3> = "1120" And F! credit course code 3 <= "1140") Or (F! debit item code 4> = "1120" And F! debit item code 4 <= "1150") Or (F! credit courses code 4> = "1120" And F! credit course code 4 <= "1150") Or (F! debit item code 5> = "1120" And F! debit item code 5 <= "1150") Or (F! credit courses code 5> = "1120" And F ! credit courses code 5 <= "1150") Then
		SS = 2
		ElseIf (F! Debit item code 6> = "1120" And F! Debit item code 6 <= "1150") Or (F! Credit courses code 6> = "1120" And F! Credit courses code 6 <= " 1150 ") Or (F! debit item code 7> =" 1120 "And F! debit item code 7 <=" 1150 ") Or (F! credit courses code 7> =" 1120 "And F! credit courses code 7 < = "1150") Or (F! debit item code 8> = "1120" And F! debit item code 8 <= "1150") Or (F! credit courses code 8> = "1120" And F! credit course code 8 <= "1140") Or (F! debit item code 9> = "1120" And F! debit item code 9 <= "1150") Or (F! credit courses code 9> = "1120" And F! credit course code 9 <= "1150") Or (F! debit item code 10> = "1120" And F! debit item code 10 <= "1150") Or (F! credit courses code 10> = "1120" And F ! credit courses code 10 <= "1150") Then
		SS = 2
		ElseIf F! Debit item code 1 = "1170" Or F! Credit courses code 1 = "1170" Or F! Debit item code 2 = "1170" Or F! Credit courses code 2 = "1170" Or F! Debit subjects code 3 = "1170" = Or F! credit courses code 3 "1170" Or F! debit item code 4 = "1170" Or F! credit courses code 4 = "1170" Or F! debit item code 5 = "1170" Or F! credit courses code 5 = "1170" Or F! debit item code 6 = "1170" Or F! credit courses code 6 = "1170" Or F! debit item code 7 = "1170" Or F! credit course code 7 = "1170" Or F! debit item code 8 = "1170" Or F! credit courses code 8 = "1170" Or F! debit item code 9 = "1170" Or F! credit courses code 9 = "1170" Or F! debit item code 10 = "1170" Or F! credit courses code 10 = "1170" Then
		SS = 3
		Else
		SS = 4
		End If
		When the new mode, the acquisition of slip number*/
		
		if(FURIDEN_MODE == 1){
			if(SS == 1){
				//In the case of cash
				try {
					DBManager db = new DBManager();
					DBManager dbC = new DBManager();
					
					try {
						boolean fo = db.doQuery("DROP VIEW IF EXISTS lastslipnumber ");	
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					} finally {
						db.close();
					}
					
					try{
						StringBuffer strSql = new StringBuffer("CREATE VIEW lastslipnumber AS SELECT journals.s_number, journals.devcode, journals.draccode, journals.craccode "+
								"FROM journals WHERE journals.devcode ='"+cmbDebCode.getSelectedItem()+"' ORDER BY journals.s_number DESC");
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
						ResultSet rs = db.getRecord("SELECT * FROM lastslipnumber");
						LAST_DEN_NO = 0;
						if(rs.next()) {
							LAST_DEN_NO = rs.getLong(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						db.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(SS == 2){
				//In the case of cash
				try {
					DBManager db = new DBManager();
					DBManager dbC = new DBManager();
					
					try {
						boolean fo = db.doQuery("DROP VIEW IF EXISTS lastslipnumber");	
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					} finally {
						db.close();
					}
					
					try{
						StringBuffer strSql = new StringBuffer("CREATE VIEW lastslipnumber AS SELECT journals.s_number, journals.devcode, journals.draccode, journals.craccode FROM journals WHERE not(journals.draccode = '1110' OR journals.craccode = '1110') "+
								"AND (journals.draccode = '1120' OR journals.craccode = '1120' OR journals.draccode = '1130' OR journals.craccode = '1130' OR journals.draccode = '1140' OR journals.craccode = '1140' "+
								"OR journals.draccode = '1150' OR journals.craccode = '1150') AND journals.devcode = '"+cmbDebCode.getSelectedItem()+"' AND journals.mm ="+txtMonth.getText()+" ORDER BY journals.s_number DESC");
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
						ResultSet rs = db.getRecord("SELECT * FROM lastslipnumber");
						LAST_DEN_NO = 2000;
						if(rs.next()) {
							LAST_DEN_NO = rs.getLong(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						db.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(SS == 3){
				//In the case of cash
				try {
					DBManager db = new DBManager();
					DBManager dbC = new DBManager();
					
					try {
						boolean fo = db.doQuery("DROP VIEW IF EXISTS lastslipnumber ");	
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					} finally {
						db.close();
					}
					
					try{
						StringBuffer strSql = new StringBuffer("CREATE VIEW lastslipnumber AS SELECT journals.s_number, journals.devcode, journals.draccode, journals.craccode FROM journals WHERE NOT(journals.draccode = '1110' OR journals.craccode = '1110') "+ 
								"AND NOT(journals.draccode = '1120' OR journals.craccode = '1120' OR journals.draccode= '1130' OR journals.craccode = '1130' OR journals.draccode ='1140' OR journals.craccode = '1140' "+
								"OR journals.draccode = '1150' OR journals.craccode ='1150') AND (journals.draccode = '1170' or journals.craccode = '1170') "+ 
								"AND journals.devcode = '"+cmbDebCode.getSelectedItem()+"' AND journals.mm ="+txtMonth.getText()+" ORDER BY journals.s_number DESC");
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
						ResultSet rs = db.getRecord("SELECT * FROM lastslipnumber");
						LAST_DEN_NO = 5000;
						if(rs.next()) {
							LAST_DEN_NO = rs.getLong(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						db.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(SS == 4){
				//In the case of cash
				try {
					DBManager db = new DBManager();
					DBManager dbC = new DBManager();
					
					try {
						boolean fo = db.doQuery("DROP VIEW IF EXISTS lastslipnumber ");	
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					} finally {
						db.close();
					}
					
					try{
						StringBuffer strSql = new StringBuffer("CREATE VIEW lastslipnumber AS SELECT journals.s_number, journals.devcode, journals.draccode, journals.craccode FROM journals WHERE "+
								"NOT (journals.draccode = '1110' OR journals.craccode = '1110') AND NOT (journals.draccode = '1120' OR journals.craccode = '1120' OR journals.draccode = '1130' "+ 
								"or journals.craccode = '1130' or journals.draccode = '1140' or journals.craccode = '1140' or journals.draccode = '1150' or journals.craccode = '1150') "+
								"and not (journals.draccode = '1170' or journals.craccode = '1170') and journals.devcode = '"+cmbDebCode.getSelectedItem()+  
								"' AND journals.mm = '"+txtMonth.getText()+"' AND journals.s_number> 7000 ORDER BY journals.s_number DESC"); 
												
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
						ResultSet rs = db.getRecord("SELECT * FROM lastslipnumber");
						LAST_DEN_NO = 7000;
						if(rs.next()) {
							LAST_DEN_NO = rs.getLong(1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						db.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			try {
				DBManager db = new DBManager();
				DBManager dbC = new DBManager();
				
				try {
					boolean fo = db.doQuery("DROP VIEW IF EXISTS lastjournalnumber");	
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					db.close();
				}
				
				try{
					StringBuffer strSql = new StringBuffer("CREATE VIEW lastjournalnumber AS SELECT journals.je_number FROM journals ORDER BY journals.je_number DESC"); 
											
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
					ResultSet rs = db.getRecord("SELECT * FROM lastjournalnumber");
					LAST_SIWAKE_NO = 0;
					if(rs.next()) {
						LAST_SIWAKE_NO = rs.getLong(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//新規伝票の転記
	    //F.Requery
		//0301追加
		try {
			boolean Fo=false;
			DBManager db = new DBManager();
			try {
				ResultSet rs = db.getRecord("SELECT * FROM jewrk WHERE (IFNULL(jewrk.draccode,0) = 0 AND jewrk.draccode <> '') AND (jewrk.dramount = 0 OR IFNULL(jewrk.dramount,0)=0)");
				if(rs.next()) {
					Fo=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}
			if(Fo){
				JOptionPane.showMessageDialog(null, "借方金額が入力されていない行があります");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			boolean Fo=false;
			DBManager db = new DBManager();
			try {
				ResultSet rs = db.getRecord("SELECT * FROM jewrk WHERE (IFNULL(jewrk.craccode,0) = 0 AND jewrk.craccode <> '') AND (jewrk.cramount = 0 OR IFNULL (jewrk.cramount,0)=0)");
				if(rs.next()) {
					Fo=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}
			if(Fo){
				JOptionPane.showMessageDialog(null, "貸方金額が入力されていない行があります");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//--------------------
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rs = db.getRecord("SELECT IFNULL(sum(jewrk.dramount),0) -  IFNULL(sum(jewrk.cramount),0) AS difference FROM jewrk");
				if(rs.next()) {
					double difference = rs.getDouble(1);
					if(difference!=0)
						JOptionPane.showMessageDialog(null, "貸借金額が不一致です");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			boolean Fo=false;
			DBManager db = new DBManager();
			try {
				ResultSet rs = db.getRecord("SELECT jewrk.draccode,sum(jewrk.dramount) AS sumofdebitamount FROM jewrk GROUP BY jewrk.draccode HAVING(jewrk.draccode is null OR jewrk.draccode = '') AND (sum(jewrk.dramount) <> 0)");
				if(rs.next()) {
					Fo=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}	
			if(Fo){
				JOptionPane.showMessageDialog(null, "金額が入っているのに科目が入力されていません");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			boolean Fo=false;
			DBManager db = new DBManager();
			try {
				ResultSet rs = db.getRecord("SELECT jewrk.craccode, sum(jewrk.cramount) AS sumofcreditamount FROM jewrk GROUP BY jewrk.craccode HAVING(jewrk.craccode is null OR jewrk.craccode = '') AND (sum(jewrk.cramount) <> 0)");
				if(rs.next()) {
					Fo=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}	
			if(Fo){
				JOptionPane.showMessageDialog(null, "金額が入っているのに科目が入力されていません");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			boolean Fo=false;
			DBManager db = new DBManager();
			try {
				ResultSet rs = db.getRecord("SELECT jewrk.dramount, jewrk.cramount FROM jewrk WHERE (((jewrk.dramount) = 0) AND ((jewrk.cramount) = 0))");
				if(rs.next()) {
					Fo=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}	
			if(Fo){
				JOptionPane.showMessageDialog(null, "金額が入っているのに科目が入力されていません");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(txtJournalNumber.getText().equals("") && FURIDEN_MODE == 2)
		{
				JOptionPane.showMessageDialog(null, "仕訳番号が入っていません");
				return;
		}
		
		if((txtYear.getText().equals("")) || (txtMonth.getText().equals("")) || (txtDay.getText().equals("")) || (txtYear.getText()==null) || (txtMonth.getText()==null) || (txtDay.getText()==null))
		{
			JOptionPane.showMessageDialog(null,"年月日が入っていません");
			return;
		}
		//行番の設定
	    GYO = 1;
	    List<Jurnal> journal = new ArrayList<Jurnal>();
	    try {
			DBManager db = new DBManager();
			try {
				ResultSet rs = db.getRecord("SELECT * FROM jewrk ORDER BY l_number");
				while(rs.next()) {
					Jurnal jor= new Jurnal();
					jor.setS_number(rs.getLong(1));
					jor.setJe_number(rs.getLong(2));
					jor.setL_number(rs.getLong(3));
					
					journal.add(jor);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    
	    
	    //部門、仕訳番号、伝票番号、計上年月日、借方キー、貸方キーの設定
	    if(FURIDEN_MODE == 1){
	        //新規伝票の時
	    	updateJournalNumberNew(LAST_SIWAKE_NO,LAST_DEN_NO,BUMON,BUMON_N,YY,MM,DD);
	    } else{
	        //修正の時（仕訳番号、伝票番号はそのまま）
	    	updateJournalNumberUpdate(BUMON, BUMON_N, YY, MM, DD);
	    }
	    
	    for(Jurnal jor:journal){
	    	updateJournalLnumber(GYO,jor);
	    	GYO=GYO+1;
	    }
	    
	    updateJournaldrkeycrkey();
	    
	    //仕訳テーブルへ追加
	    try {
	    	DBManager db = new DBManager();
		    try {
				PreparedStatement insertJournals= db.getPreparStamt(
						"INSERT INTO journals(je_number,s_number,l_number,drkey,crkey,devcode,devname,draccode,dracname,dracsubcode,dracsubname,craccode,cracname,cracsubcode,cracsubname,yyyy,mm,dd,"+
						"dramount,cramount,balance,descode,desname,memo,transtime,sundry,drctax,crctax,drctaxamount,crctaxamount,vendorcode,vendorname,otherdata,receivecode,prjcode,prjname) "+
						" SELECT je_number,s_number,l_number,drkey,crkey,devcode,devname,draccode,dracname,dracsubcode,dracsubname,craccode,cracname,cracsubcode,cracsubname,yyyy,mm,dd,"+
						"dramount,cramount,balance,descode,desname,memo,NOW() AS transtime, "+ SHOKUCHI +" as sundry,drctax,crctax,drctaxamount,crctaxamount, "+
						"vendorcode,vendorname,otherdata,receivecode,prjcode,prjname FROM jewrk");
	
				insertJournals.executeUpdate();
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		    finally {
				db.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    //仕訳登録履歴へ追加
	    try {
	    	DBManager db = new DBManager();
		    try {
				PreparedStatement insertJournals= db.getPreparStamt(
						"INSERT INTO jewrk_buf (je_number,s_number,l_number,drkey,crkey,devcode,devname,draccode,dracname,dracsubcode,dracsubname,craccode,cracname,cracsubcode,cracsubname,yyyy,mm,dd,"+
						"dramount,cramount,balance,descode,desname,memo,transtime,sundry,drctax,crctax,drctaxamount,crctaxamount,vendorcode,vendorname,otherdata,receivecode,prjcode,prjname) "+
						"SELECT je_number,s_number,l_number,drkey,crkey,devcode,devname,draccode,dracname,dracsubcode,dracsubname,craccode,cracname,cracsubcode,cracsubname,yyyy,mm,dd, "+
						"dramount,cramount,balance,descode,desname,memo,NOW() AS transtime, "+ SHOKUCHI +" as sundry,drctax,crctax,drctaxamount,crctaxamount, "+
						"vendorcode,vendorname,otherdata,receivecode,prjcode,prjname FROM jewrk");
	
				insertJournals.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		    finally {
				db.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    if(FURIDEN_MODE == 1)
	    {
	    	//新規伝票場合、次の伝票に項目を送る
	        JOptionPane.showMessageDialog(null,"伝票番号　" + LAST_DEN_NO + 1 + "番で登録しました");
	        //伝票の印刷
	        try {
				DBManager db = new DBManager();
				DBManager dbC = new DBManager();
				
				try {
					boolean fo = db.doQuery("DROP VIEW IF EXISTS JE_search_result_no");	
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					db.close();
				}
				
				try{
					StringBuffer strSql = new StringBuffer("CREATE VIEW JE_search_result_no AS SELECT je_number FROM journals WHERE je_number =" + (LAST_SIWAKE_NO + 1)); 
											
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
				DBManager dbC = new DBManager();
				
				try {
					boolean fo = db.doQuery("DROP VIEW IF EXISTS JE_search_result");	
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					db.close();
				}
				
				try{
					StringBuffer qdfJE = new StringBuffer("CREATE VIEW JE_search_result AS "+
							"SELECT journals.je_number, journals.s_number, journals.l_number, journals.devcode, journals.devname, journals.draccode, journals.dracname,journals.dracsubcode,"+ 
							"journals.dracsubname, journals.craccode,journals.cracname,journals.cracsubcode,journals.cracsubname,journals.yyyy,journals.mm, journals.dd, journals.dramount, "+ 
							"journals.cramount,journals.balance,journals.descode, journals.desname,journals.transtime, journals.drctax, journals.crctax, journals.vendorcode, "+
							"journals.vendorname, journals.otherdata, journals.prjcode, journals.prjname, ctc_1.name FROM JE_search_result_no LEFT JOIN journals ON JE_search_result_no.je_number= "+
							"journals.je_number LEFT JOIN ctc ON journals.drctax = ctc.code LEFT JOIN ctc AS ctc_1 ON journals.crctax = ctc_1.code WHERE journals.devcode='"+BUMON+"' "+
							"ORDER BY journals.s_number, journals.l_number"); 
											
					boolean fo1= dbC.doQuery(qdfJE.toString());
					
					//System.out.println(qdfJE);
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					dbC.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        //Call FURI_REP_MAKE
	        FURI_REP_MAKE();
	        //DoCmd.OpenReport "transfer slip",,, "journal number =" & Format (LAST_SIWAKE_NO + 1)
	        //DoCmd.Close acReport, "transfer slip"

	        //Storage of data in front of the slip
	        MAENEN = Integer.parseInt(txtYear.getText());
	        MAETUKI = Integer.parseInt(txtMonth.getText());
	        MAEHI = Integer.parseInt(txtDay.getText());
	        MAEBUMONCODE = String.valueOf(cmbDebCode.getSelectedItem());
	        MAEBUMONNAME = txtDevName.getText();
	        //Clear the current input data
	        if(handan1 == 1){
	        	//Call FURIDEN_CLEAR
	        	FURIDEN_CLEAR();
	        } 
	        else{
	        	try{
	        		DBManager db = new DBManager();
	        		try {
	        			PreparedStatement furiden_clear = db.getPreparStamt("UPDATE jewrk set dramount = 0,cramount = 0");

	        			furiden_clear.executeUpdate();

	        		} catch (Exception e) {
	        			e.printStackTrace();
	        		} finally {
	        			db.close();
					}
	        	} catch (Exception e) {
					e.printStackTrace();
	        	}
	        }
	     
	        //Ready for the next slip input
	        txtYear.setText(String.valueOf(MAENEN));
	        txtMonth.setText(String.valueOf(MAETUKI));
	        txtDay.setText(String.valueOf(MAEHI));
	        cmbDebCode.setSelectedItem(MAEBUMONCODE);
	        txtDevName.setText(MAEBUMONNAME);
	        txtJournalNumber.setText("");
	        journalCopy();
	    }
	    
	    if(FURIDEN_MODE == 2){
        	try {
    			DBManager db = new DBManager();
    			try {
    				String sql = "SELECT * FROM JE_search_result WHERE  je_number not in("+serchJournalNo+") order by je_number";
    				System.out.println(sql);
    				
    				ResultSet rs = db.getRecord(sql);
    				if(rs.next()) {
    					int j_num = rs.getInt(1);
    					if(j_num>Integer.parseInt(txtJournalNumber.getText())){
    						NEXT_NO = j_num;
    						serchJournalNo=serchJournalNo+","+String.valueOf(j_num);
    					    //Call SIWAKE_TO_FURIDEN(NEXT_NO)
    						SIWAKE_TO_FURIDEN(NEXT_NO);
    					} else{
    						
    						JOptionPane.showMessageDialog(null, "次の伝票はありません。");
    						//Call FURIDEN_CLEAR
    			        	FURIDEN_CLEAR();
    					}
    				} else{
    					JOptionPane.showMessageDialog(null, "次の伝票はありません。");
						//Call FURIDEN_CLEAR
			        	FURIDEN_CLEAR();
			        	clear();
			        	int totalRows = CompTable.getModel().getRowCount();
			    		for(int i=0;i<totalRows;i++) {
			    			CompModel.removeRow(0);
			    		}
    				}
    			} catch (Exception e) {
    				e.printStackTrace();
    			}finally {
    				db.close();
    			}			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
	}
	
	/*public void FURI_REP_MAKE() {
		//Transfer slip reporting
		//Previous journal numbers
		long MAE_BAN; 
		//before the slip number
		long MAE_DEN; 
		String TEKIYO =""; 
		String MAE_BUMONCODE = "";
		String MAE_BUMONNAME ;
		int MAE_NEN,MAE_TUKI,MAE_HI;
		
		If OBJ_EXIST ( "transfer slip report for data deletion") = 2 Then
		        //DoCmd.DeleteObject A_QUERY, "for a transfer slip report data deletion"
		    //End If
		if() {
			
		}
		
		try {
			//DBManager db = new DBManager();
			DBManager dbC = new DBManager();
			
			try {
				boolean fo = db.doQuery("DROP VIEW IF EXISTS deletetransferslipreport");	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
			
			try{
				//StringBuffer strSql = new StringBuffer("CREATE VIEW deletetransferslipreport AS DELETE transfer_slip.devcode, transfer_slip.devname, transfer_slip.je_number, transfer_slip.s_number, transfer_slip.yyyy, transfer_slip.mm,transfer_slip.dd, transfer_slip.abstract_name FROM transfer_slip"); 
				StringBuffer strSql = new StringBuffer("DELETE FROM transfer_slip");
				
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
		
		//TEKIYO = "";
		List<Jurnal> journ = new ArrayList<Jurnal>();
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rs = db.getRecord("SELECT * FROM JE_search_result");
				if(rs.next()) {
					String desname = rs.getString("desname");
					if(!desname.equals("")){
						TEKIYO = desname + (char) 13 + (char) 10;
					}
					
					MAE_BUMONCODE = rs.getString("devcode");
					MAE_BUMONNAME = rs.getString("devname");
					MAE_NEN = rs.getInt("yyyy");
					MAE_TUKI = rs.getInt("mm");
					MAE_HI = rs.getInt("dd");
					MAE_BAN = rs.getLong("je_number"); 
					MAE_DEN = rs.getLong("s_number"); 
					
					while(rs.next()){
						Jurnal journal = new Jurnal();
						long journal_num = rs.getLong("je_number");
						if(journal_num != MAE_BAN)
						{
							journal.setDevcode(MAE_BUMONCODE);
							journal.setDevname(MAE_BUMONNAME);
							journal.setJe_number(MAE_BAN);
							journal.setS_number(MAE_DEN);
							journal.setYyyy(MAE_NEN);
							journal.setMm(MAE_TUKI);
							journal.setDd(MAE_HI);
							if(TEKIYO.getBytes().length>254)
							{
								TEKIYO = LeftB(TEKIYO,254);
							}
							journal.setDesname(TEKIYO);
							
							journ.add(journal);
						}
						
						desname = rs.getString("desname");
						if(!desname.equals("")){
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
					if(TEKIYO.getBytes().length>254)
					{
						TEKIYO = LeftB(TEKIYO,254);
					}
					journal.setDesname(TEKIYO);
					
					journ.add(journal);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Jurnal jor:journ){
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

			//dispose();
	}*/
	
	
	public void SIWAKE_TO_FURIDEN(long SHORI){
		//検索仕訳データから振替伝票へ
		//データベース処理関連
		
		int GYO;
		String Utime="";
		int handan;
		
		handan = 0;
		/*if(qdfJE.toString().contains("jewrk_buf")==false){
			try {
				DBManager db = new DBManager();
				try {
					ResultSet rs = db.getRecord("SELECT DATE_FORMAT(transtime, '%d%b%Y%T%f') AS formula1 FROM JE_search_result GROUP BY DATE_FORMAT(transtime, '%d%b%Y%T%f') ORDER BY DATE_FORMAT(transtime, '%d%b%Y%T%f') DESC");
					if(rs.next()) {
						Utime = String.valueOf(rs.getDate(1));
						handan = 100;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					db.close();
				}			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		//if(handan == 100){
			try {
				DBManager db = new DBManager();
				DBManager dbI = new DBManager();
				try {
					//ResultSet rs = db.getRecord("SELECT *, DATE_FORMAT(transtime, '%d%b%Y%T%f') AS formula1 FROM JE_search_result WHERE DATE_FORMAT(transtime, '%d%b%Y%T%f') = '" + Utime + "'");
					ResultSet rs = db.getRecord("SELECT * FROM JE_search_result WHERE je_number=" + SHORI );
					if(rs.next()) {
						int journalNo = rs.getInt(1);
						//if(journalNo==SHORI){
							FURIDEN_CLEAR();
							//仕訳データの読み込み
							txtJournalNumber.setText(String.valueOf(journalNo));
							txtNo.setText(String.valueOf(rs.getInt(2)));
							txtYear.setText(String.valueOf(rs.getInt(14)));
							txtMonth.setText(String.valueOf(rs.getInt(15)));
							txtDay.setText(String.valueOf(rs.getInt(16)));
							txtDevName.setText(rs.getString(5));
							cmbDebCode.setSelectedItem(rs.getString(4));
							getNextData(journalNo);
							
						//}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					db.close();
				}
				
				try {
					PreparedStatement ps = dbI.getPreparStamt(
							"INSERT INTO jewrk (je_number, s_number, l_number, devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname,"
							+ "cracsubcode, cracsubname, yyyy, mm, dd, dramount, cramount, balance, descode, desname, transtime, drctax, crctax , vendorcode, vendorname, otherdata, prjcode, prjname) "
							+ "SELECT je_number, s_number, l_number, devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname,"
							+ "cracsubcode, cracsubname, yyyy, mm, dd, dramount, cramount, balance, descode, desname, transtime, drctax, crctax , vendorcode, vendorname, otherdata, prjcode, prjname FROM JE_search_result WHERE je_number = "+ SHORI/*+" AND DATE_FORMAT(transtime, '%d%b%Y%T%f') = '"+ Utime + "'"*/);

					ps.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					dbI.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		//}
		/*else{
			try {
				DBManager db = new DBManager();
				DBManager dbI = new DBManager();
				try {
					ResultSet rs = db.getRecord("SELECT * FROM JE_search_result");
					while(rs.next()) {
						int journalNo = rs.getInt(1);
						if(journalNo==SHORI){
							FURIDEN_CLEAR();
							//仕訳データの読み込み
							txtJournalNumber.setText(String.valueOf(journalNo));
							txtNo.setText(String.valueOf(rs.getInt(2)));
							txtYear.setText(String.valueOf(rs.getInt(14)));
							txtMonth.setText(String.valueOf(rs.getInt(15)));
							txtDay.setText(String.valueOf(rs.getInt(16)));
							txtDevName.setText(rs.getString(5));
							cmbDebCode.setSelectedItem(rs.getString(4));
							getNextData(journalNo);
							
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					db.close();
				}
				
				try {
					PreparedStatement ps = db.getPreparStamt("INSERT INTO jewrk (je_number, s_number, l_number, devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname, "
							+ "cracsubcode, cracsubname, yyyy, mm, dd, dramount, cramount, balance, descode, desname, transtime, drctax, crctax , vendorcode, vendorname, otherdata, prjcode, prjname) "
							+ "SELECT je_number, s_number, l_number, devcode, devname, draccode, dracname, dracsubcode, dracsubname, craccode, cracname, "
							+ "cracsubcode, cracsubname, yyyy, mm, dd, dramount, cramount, balance, descode, desname, transtime, drctax, crctax , vendorcode, vendorname, otherdata, prjcode, prjname FROM JE_search_result WHERE je_number = "+ SHORI);

					ps.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
	}
	
	public void TORI_SANSHO(int SHORI) {
		String StrSQL;
		Integer dum;
		String GYO;
		
		String shori = String.valueOf(SHORI);
		
		if(shori.substring(1, 2).equals("0")) {
			GYO = Right(shori,1);
		} else {
			GYO = Right(shori,2);
		}
		
		if(shori.substring(0,1).equals("1")) {
			if(!String.valueOf(cmbVendor.getSelectedItem()).equals("")) {
				try {
					boolean fo = false;
					DBManager db = new DBManager();
					try {

						ResultSet rs;
						rs = db.getRecord("SELECT * FROM vendors WHERE vendorcode ='" +String.valueOf(cmbVendor.getSelectedItem())+"'");
						
						if(rs.next()) {
							txtVendor.setText(rs.getString(2));
							fo=true;
						}
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					} finally {
						db.close();
					}
					if(!fo)
					{
						JOptionPane.showMessageDialog(null, "該当科目がありません");
					} else {
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void PRJCODE() {
		if(String.valueOf(cmbProject.getSelectedItem()).equals(""))
			return;
		try {
			boolean fo = false;
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT * FROM project_code WHERE prjcode ='" +String.valueOf(cmbProject.getSelectedItem())+"'");
				
				if(rs.next()) {
					txtProject.setText(rs.getString(2));
					fo=true;
				}
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
			if(!fo)
			{
				JOptionPane.showMessageDialog(null, "該当科目がありません");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void TEKIYO_TO_FURIDEN(int GYO) {
		Integer GYO2;
		String StrSQL;
		
		
		
		
	}
	
	public String Right(String str, int pos)
	 {
		 String val = str;
		 int len = val.length();
		 int right_pos = len-(pos);
		 
		 String right = val.substring(right_pos, len);
		 
		 return right;
	 }
	
	//delete
	public void FURIDEN_CLEAR(){
		try{
    		DBManager db = new DBManager();
    		try {
    			PreparedStatement furiden_clear = db.getPreparStamt("DELETE FROM jewrk");

    			furiden_clear.executeUpdate();

    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			db.close();
			}
    	}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//update
	public void updateJournaldrkeycrkey()
	{
		try{
			DBManager db= new DBManager();
			try {
				PreparedStatement psUpdateActableBgbalance = db
						.getPreparStamt("UPDATE jewrk SET jewrk.drkey = CONCAT(jewrk.devcode,jewrk.draccode,jewrk.dracsubcode), jewrk.crkey = CONCAT(jewrk.devcode,jewrk.craccode,jewrk.cracsubcode)");
	
				psUpdateActableBgbalance.executeUpdate();
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//update
	public void updateJournalNumberUpdate(String BUMON,String BUMON_N,long YY,long MM,long DD)
	{
		try{
			DBManager db= new DBManager();
			try {
				String sql = "UPDATE jewrk SET jewrk.je_number ="+txtJournalNumber.getText()+", jewrk.s_number = "+ txtNo.getText() +", jewrk.devcode = '" + BUMON + "', jewrk.devname = '" + BUMON_N + "',jewrk.yyyy = " + YY + ", jewrk.mm = "+ MM +", jewrk.dd = "+ DD;
				PreparedStatement psUpdateActableBgbalance = db
						.getPreparStamt(sql);
				
				System.out.println(sql);
	
				psUpdateActableBgbalance.executeUpdate();
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//update
	public void updateJournalNumberNew(long LAST_SIWAKE_NO,long LAST_DEN_NO,String BUMON,String BUMON_N,long YY,long MM,long DD)
	{
		try{
			DBManager db= new DBManager();
			try {
				PreparedStatement psUpdateActableBgbalance = db
						.getPreparStamt("UPDATE jewrk SET jewrk.je_number ="+(LAST_SIWAKE_NO + 1)+", jewrk.s_number ="+(LAST_DEN_NO + 1)+", jewrk.devcode = '"+ BUMON +"', jewrk.devname = '"+ BUMON_N +"', jewrk.yyyy ="+ YY +",jewrk.mm = "+ MM +", jewrk.dd = "+ DD);
	
				psUpdateActableBgbalance.executeUpdate();
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	//update journal l_number
	public void updateJournalLnumber(int GYO,Jurnal obj){
		try{
			DBManager db= new DBManager();
			try {
				PreparedStatement psUpdateActableBgbalance = db
						.getPreparStamt("UPDATE jewrk SET jewrk.l_number ="+GYO+" WHERE s_number="+obj.getS_number()+" AND je_number="+obj.getJe_number());
	
				psUpdateActableBgbalance.executeUpdate();
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//corresponding journal number data deletion
	public void deleteJournalData() {
		DBManager db;
		try {
			db = new DBManager();

			/*PreparedStatement psDelete = db.getPreparStamt("DELETE journals.je_number, journals.l_number, journals.devcode,"+ 
			"journals.devname, journals.draccode, journals.dracname,"+ 
			"journals.dracsubcode, journals.dracsubname, journals.craccode,"+ 
			"journals.cracname, journals.cracsubcode, journals.cracsubname,"+ 
			"journals.yyyy, journals.mm, journals.dd, journals.dramount,"+ 
			"journals.cramount, journals.balance, journals.descode, journals.desname,"+ 
			"journals.memo,journals.transtime FROM journals"+ 
			"WHERE journals.je_number = "+txtJournalNumber.getText()+" AND devcode ="+cmbDebCode.getSelectedItem());*/
			String sql = "DELETE FROM journals WHERE journals.je_number = "+txtJournalNumber.getText()+" AND devcode ='"+cmbDebCode.getSelectedItem()+"'";
			PreparedStatement psDelete = db.getPreparStamt(sql);
			
			System.out.println(sql);

			psDelete.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deletejewrk() {
		DBManager db;
		try {
			db = new DBManager();

			PreparedStatement psDelete = db.getPreparStamt("DELETE FROM jewrk");

			psDelete.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getSearchData() {
		int jeNo=0;
		try{
			DBManager dbf= new DBManager();
			try {
				ResultSet rs;
				rs = dbf.getRecord("SELECT * FROM je_search_result order by je_number");
				
				if(rs.next()){
					jeNo= rs.getInt(1);
					txtJournalNumber.setText(String.valueOf(jeNo));
					txtNo.setText(rs.getString(2));
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				dbf.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		serchJournalNo=String.valueOf(jeNo);
		getNextData(jeNo);
	}
	
	public void getNextData(int jeNo)
	{
		int totalRows = CompTable.getModel().getRowCount();
		for(int i=0;i<totalRows;i++) {
			CompModel.removeRow(0);
		}
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rs;
				rs = db.getRecord("SELECT * FROM je_search_result WHERE je_number ="+jeNo+" order by je_number");
				int row = 1;
		        while(rs.next()) {
		        	//int journalNo = rs.getInt(1);
			        String LineNo = rs.getString(3);
			        String draccode = rs.getString(6);
			        String dracname = rs.getString(7);
			        String dramount = rs.getString(17);
			        String craccode = rs.getString(10);
			        String cracname = rs.getString(11);
			        String dcramount = rs.getString(18);
			        String vendorCode = rs.getString(25);
					String vendorName =rs.getString(26);
					String dracsubcode = rs.getString(8);
					String dracsubname = rs.getString(9);
					String debitTax = rs.getString(23);
					String cracsubcode = rs.getString(12);
					String cracsubname = rs.getString(13);
					String DebitTax2 = rs.getString(24);						
					String projectcode = rs.getString(28);
					String projectname = rs.getString(29);
					String Description = rs.getString(20);
					String detailDescription = rs.getString(21);
							
			        CompModel.addRow(new Object[]{LineNo,draccode,dracname,dramount,craccode,cracname,dcramount,vendorCode,
			    /*8*/    vendorName,dracsubcode,dracsubname,debitTax,cracsubcode,cracsubname,DebitTax2,projectcode
			    /*16*/    ,projectname,Description,detailDescription});
			            	
			        if(row==1){
			        	SelectedRowForEdit = 0;
			        	String creditAccount = craccode+cracsubcode+" | "+cracname+" | "+cracsubname;
						String devitAccount = draccode+dracsubcode+" | "+dracname+" | "+dracsubname;
				        cmbDebitAccount.setSelectedItem(devitAccount);
								
						txtDevitAmount.setText(dramount);
						txtDebitAccode.setText(draccode);
						txtDebitAccount.setText(dracname);
						txtDebitAccountSub.setText(dracsubcode);
						txtDebitAccountSub2.setText(dracsubname);
							
				        cmbCreditAccount.setSelectedItem(creditAccount);
								
						cmbDebitTax.setSelectedItem(debitTax);
						txtCreditAccode.setText(craccode);
						txtCreditAccount.setText(cracname);
						txtCreditAccountSub.setText(cracsubcode);
						txtCreditAccountSub2.setText(cracsubname);
						cmbDebitTax2.setSelectedItem(DebitTax2);
						cmbVendor.setSelectedItem(vendorCode);
						txtVendor.setText(vendorName);
						cmbProject.setSelectedItem(projectcode);
						txtProject.setText(projectname);
						cmbDescription.setSelectedItem(Description);
						txtDescription.setText(detailDescription);
						txtCreditAmount.setText(dcramount);
			        }
			        row++;
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
	
	public void setValueFromTableToField(int row){
				
		String LineNo = CompModel.getValueAt(row,0).toString();
        String draccode = CompModel.getValueAt(row,1).toString();
        String dracname = CompModel.getValueAt(row,2).toString();
        String dramount = CompModel.getValueAt(row,3).toString();
        String craccode = CompModel.getValueAt(row,4).toString();
        String cracname = CompModel.getValueAt(row,5).toString();
        String dcramount = CompModel.getValueAt(row,6).toString();
        String vendorCode = CompModel.getValueAt(row,7).toString();
		String vendorName = CompModel.getValueAt(row,8).toString();
		String dracsubcode = CompModel.getValueAt(row,9).toString();
		String dracsubname = CompModel.getValueAt(row,10).toString();
		String debitTax = CompModel.getValueAt(row,11).toString();
		String cracsubcode = CompModel.getValueAt(row,12).toString();
		String cracsubname = CompModel.getValueAt(row,13).toString();
		String DebitTax2 = CompModel.getValueAt(row,14).toString();						
		String projectcode = CompModel.getValueAt(row,15).toString();
		String projectname = CompModel.getValueAt(row,16).toString();
		String Description = CompModel.getValueAt(row,17).toString();
		String detailDescription = CompModel.getValueAt(row,18).toString();
		      
        
        String creditAccount = craccode+cracsubcode+" | "+cracname+" | "+cracsubname;
		String devitAccount = draccode+dracsubcode+" | "+dracname+" | "+dracsubname;
	    cmbDebitAccount.setSelectedItem(devitAccount);
					
	    txtDevitAmount.setText(dramount);
		txtDebitAccode.setText(draccode);
		txtDebitAccount.setText(dracname);
		txtDebitAccountSub.setText(dracsubcode);
		txtDebitAccountSub2.setText(dracsubname);
				
	    cmbCreditAccount.setSelectedItem(creditAccount);
					
		cmbDebitTax.setSelectedItem(debitTax);
		txtCreditAccode.setText(craccode);
		txtCreditAccount.setText(cracname);
		txtCreditAccountSub.setText(cracsubcode);
		txtCreditAccountSub2.setText(cracsubname);
		cmbDebitTax2.setSelectedItem(DebitTax2);
		cmbVendor.setSelectedItem(vendorCode);
		txtVendor.setText(vendorName);
		cmbProject.setSelectedItem(projectcode);
		txtProject.setText(projectname);
		cmbDescription.setSelectedItem(Description);
		txtDescription.setText(detailDescription);
		txtCreditAmount.setText(dcramount);
	}
	
}
//End Main Class

/*class PanelTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    private static int totalrowCount;
    
    public PanelTableModel(){
    	totalrowCount = 0;
    }
    @Override
    public int getColumnCount() {
        return 1;
    }
    public static void setTotalrowCount(int val){
    	totalrowCount = val;
    }
	public void addRow() {
		int j=1;
		for(int i=totalrowCount;i>0;i--){
			Comp rowObject =(Comp) super.getValueAt(i-1, 0);
			rowObject.tLabelNo = String.valueOf(j);
			//super.setValueAt(rowObject, i-1, 0);
			j++;
		}
        super.addRow(new Object[]{new Comp("0", "", "", "","", "", "","","","","", "", "", "","", "", "", "","", "", "")});
        totalrowCount++;
    }
	
	public void removeRow(int rowNo){
		totalrowCount--;
		super.removeRow(rowNo);
		
	}
}*/

class Comp {
    public String tLabelNo;
    public String tDebitAccount;
    public String tDevitAmount;
    public String tCreditAccount;
    public String tCreditAmount;
    public String tVendor;
    public String tDebitAccountSub;
    public String tDebitAccountSub2;
	public String tCreditAccountSub;	
    public String tCreditAccountSub2;	
	public String tProject;
    public String tDescription;
    public String tDebitAccode;
    public String tCreditAccode;
	
    public String cDebitAccount;
    public String cCreditAccount;
    public String cVendor;
    public String cDebitTax;
    public String cDebitTax2;
    public String cProject;
    public String cDescription;

    public Comp(String tLabelNo, String tDebitAccount, String tDevitAmount, String tCreditAccount, String tCreditAmount, String tVendor,
    		String tDebitAccountSub,String tDebitAccountSub2,String tCreditAccountSub,String tCreditAccountSub2,String tProject,String tDescription,String tDebitAccode,String tCreditAccode,String cDebitAccount,String cCreditAccount,String cVendor,String cDebitTax,String cDebitTax2,String cProject,String cDescription) {
        this.tLabelNo = tLabelNo;
        this.tDebitAccount = tDebitAccount;
        this.tDevitAmount = tDevitAmount;
        this.tCreditAccount = tCreditAccount;
        this.tCreditAmount = tCreditAmount;
        this.tVendor = tVendor;
        this.tDebitAccountSub = tDebitAccountSub;
        this.tDebitAccountSub2 = tDebitAccountSub2;
        this.tCreditAccountSub = tCreditAccountSub;
        this.tCreditAccountSub2 = tCreditAccountSub2;
        this.tProject = tProject;
        this.tDescription = tDescription;
        this.tDebitAccode = tDebitAccode;
        this.tCreditAccode = tCreditAccode;
        this.cDebitAccount = cDebitAccount;
        this.cCreditAccount = cCreditAccount;
        this.cVendor = cVendor;
        this.cDebitTax = cDebitTax;
        this.cDebitTax2 = cDebitTax2;
        this.cProject = cProject;
        this.cDescription = cDescription;
    }
}

