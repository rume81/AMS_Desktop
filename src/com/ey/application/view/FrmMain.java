package com.ey.application.view;

import java.awt.*;
import javax.swing.*;

import com.ey.application.model.CommonProperties;

import java.awt.event.*;

public class FrmMain extends JFrame {
	JMenuBar EYMenu 			= new JMenuBar();

	JMenu mnuFile 				= new JMenu();
	JMenu mnuHelp 				= new JMenu();

	JToolBar toolbar 			= new JToolBar();

	JLabel status 				= new JLabel();

	JButton cmdMain 			= new JButton();

	JMenuItem mnMain 			= new JMenuItem();

	JMenuItem mnExit 			= new JMenuItem();
	JMenuItem mnHelp 			= new JMenuItem();
	JMenuItem mnAbout 			= new JMenuItem();

	JDesktopPane Desktop 		= new JDesktopPane();
	
	public FrmMain() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		if(System.getProperty("os.name").contains("Windows"))
			setLookup();
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(1, 1, ScreenSize.width, ScreenSize.height - 43);
		this.setTitle("会計システム");
		this.setResizable(false);
		this.setJMenuBar(EYMenu);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitMsg();
			}
		});

		mnuFile.setText("ファイル");

		mnuHelp.setText("ヘルプ");

		mnMain.setText("メイン");
		mnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmAccountMaintenance frmAccountMaintenance = new FrmAccountMaintenance();
				createFrame(frmAccountMaintenance);
			}
		});		
		

		mnExit.setText("終了");
		mnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitMsg();
			}
		});

		mnHelp.setText("ヘルプのトピック");

		mnAbout.setText("このソフトウェアについて");
		mnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		toolbar.setBounds(new Rectangle(0, 27, 400, 34));
		// toolbar.setBorder(BorderFactory.createEtchedBorder());

		status.setFont(new java.awt.Font("Times New Romans", 3, 10));
		status.setText("Copyright (C)2016-2020, by EY.");
		status.setBorder(BorderFactory.createEtchedBorder());
		status.setBounds(new Rectangle(1, ScreenSize.height-20, 400, 17));

		cmdMain.setToolTipText("メイン");
		cmdMain.setText("メイン");
		cmdMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmAccountMaintenance frmAccountMaintenance = new FrmAccountMaintenance();
				createFrame(frmAccountMaintenance);
			}
		});
		
		
		this.getContentPane().add(toolbar, BorderLayout.NORTH);
		this.getContentPane().add(status, BorderLayout.SOUTH);
		this.getContentPane().add(Desktop, BorderLayout.CENTER);

		EYMenu.add(mnuFile);
		EYMenu.add(mnuHelp);

		mnuFile.add(mnMain);
		mnuFile.addSeparator();
		mnuFile.add(mnExit);

		toolbar.add(cmdMain, null);
		toolbar.addSeparator();

		mnuHelp.add(mnHelp);
		mnuHelp.addSeparator();
		mnuHelp.add(mnAbout);

		Desktop.setBackground(new Color(156,214,230));

		loadBackgroundImage();

	}

	protected void loadBackgroundImage() {
		CommonProperties props = new CommonProperties();
		String imageDir = props.getImageDir();
		ImageIcon icon = new ImageIcon(imageDir+"logo.png");

		int x = (this.getWidth() - icon.getIconWidth()) / 2;
		int y = (this.getHeight() - icon.getIconHeight()) / 2;

		JLabel l = new JLabel(icon);
		l.setBounds(x, y + 80, icon.getIconWidth(), icon.getIconHeight());

		// Place the image in the lowest possible layer so nothing
		// can ever be painted under it.
		Desktop.add(l, new Integer(Integer.MIN_VALUE));
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
	public javax.swing.JDesktopPane getDesktopPane() {
	    return Desktop;
	}
	protected void exitMsg() {
		int opt = JOptionPane.showConfirmDialog(null, "あなたは、アプリケーションを終了しますか？",
				"終了確認", JOptionPane.YES_NO_OPTION);
		if (opt == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void setLookup() {

		try {
			UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
			UIManager.setLookAndFeel(looks[1].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
