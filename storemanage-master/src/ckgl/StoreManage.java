package ckgl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import com.sun.java.swing.plaf.windows.resources.windows;

import sun.awt.image.ImageWatched.Link;
import sun.util.logging.resources.logging;


public class StoreManage extends JFrame implements ActionListener{
	JTable bgTable,bg2Table,bg3Table;
	JPanel ProductManage;
	ProductInfo  proinfo1;
	SupplierInfo suppinfo1;
	ViewInfo viewinfo1;
	int combo;
	Timer timeAction;
	String ww;

	public StoreManage(){
		//InitGlobalFont(new Font("�����п�", Font.PLAIN, 12));
		//JLabel time=new JLabel();
		/**
		 * ���ò˵�
		 */
		JMenuBar cdlBar=new JMenuBar();
		JMenu cdMenu=new JMenu("�û�����");
		JMenu cdMenu2=new JMenu("ϵͳ����");
		JMenu cdMenu3=new JMenu("����");

		//�����˵�����
		JMenuItem help=new JMenuItem("��ϵ����");
		help.setAccelerator(KeyStroke.getKeyStroke("Ctrl"+'L'));
		help.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null , "����QQ��741047261");
				Desktop desktop=Desktop.getDesktop();
				try {
					desktop.browse(new URI("http://leikewei521.tk"));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}

		});
		//�û�����˵�����
		JMenuItem zhuceItem=new JMenuItem("��Ʒע��");
		zhuceItem.setMnemonic('Z');
		zhuceItem.setAccelerator(KeyStroke.getKeyStroke("Ctrl"+'Z'));
		JMenuItem adduser=new JMenuItem("����û�");
		adduser.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AddUser add=new AddUser();
				//������������ڵ�رպ��������򲻻��˳�
				add.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}

		});
		JMenuItem changeItem=new JMenuItem("�����޸�");
		changeItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Change change=new Change();
				change.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}

		});
		//ϵͳ���ò˵�����
		JMenuItem window=new JMenuItem("Window���");
		window.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new WindowsLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}	
			}

		});
		JMenuItem xitongItem=new JMenuItem("Nimbus���");
		xitongItem.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}

			}

		});
		JMenuItem winclassItem=new JMenuItem("WindowsClassic���");
		winclassItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
			}
		});
		cdMenu.add(adduser);cdMenu.add(changeItem);
		cdMenu2.add(window);cdMenu2.add(xitongItem);cdMenu2.add(winclassItem);
		cdMenu3 .add(help);cdMenu3.add(zhuceItem);
		cdlBar.add(cdMenu);cdlBar.add(cdMenu2);cdlBar.add(cdMenu3);


		/**
		 * ��ҳ��
		 */


		JLabel hyLabel=new JLabel(new ImageIcon("hy2.jpg"));//��ӭͼƬ
		JTabbedPane xxk=new JTabbedPane();
		JButton exitButton=new JButton("�˳�");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		/**
		 * ��Ʒ����ҳ��
		 */

		//��ӱ��
		ProductManage=new JPanel();
		proinfo1=new ProductInfo();
		bgTable=new JTable(proinfo1);

		JScrollPane gdScrollPane=new JScrollPane(bgTable);
		//��Ʒ�������˵�
		final JPopupMenu popupMenu=new JPopupMenu();
		JMenu jMenu=new JMenu("�˵�");
		JMenuItem  jMenuItem[]={new JMenuItem("�鿴����"),new JMenuItem("ˢ��"),new JMenuItem("�����Ʒ"),
				new JMenuItem("��Ʒ����"),new JMenuItem("��Ʒ����"),new JMenuItem("�˳�")};
		for (int i = 0; i <2; i++) {
			popupMenu.add(jMenuItem[i]);
		}
		popupMenu.addSeparator();
		for (int i = 2; i <5 ; i++) {
			popupMenu.add(jMenuItem[i]);	
		}
		popupMenu.addSeparator();
		popupMenu.add(jMenuItem[5]);
		jMenuItem[0].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//�鿴����
				int xz=bgTable.getSelectedRow();
				if (xz==-1) {
					JOptionPane.showMessageDialog(null, "��ѡ������Ҫ�鿴����Ʒ");
				}else {
					new Detail(proinfo1, xz);

				}
			}
		});
		jMenuItem[1].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//ˢ��
				proinfo1=new ProductInfo();
				bgTable.setModel(proinfo1);
			}
		});
		jMenuItem[2].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//�����Ʒ
				new TianJia(); 

			}
		});
		jMenuItem[3].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//��Ʒ����
				final int xz3=bgTable.getSelectedRow();
				if (xz3==-1) {
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫ��������Ʒ");
					return;
				}
				new AddCargob(proinfo1, xz3);
				proinfo1=new ProductInfo();
				bgTable.setModel(proinfo1);
			}
		});
		jMenuItem[4].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��Ʒ����
				int xz3=bgTable.getSelectedRow();
				if (xz3==-1) {
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫ��������Ʒ");
					return;
				}else {
					new GoCargob(proinfo1,xz3);
				}

			}
		});
		//�˳���Ʒ����˵�
		jMenuItem[5].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(false);
			}
		});
		bgTable.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(true);
					popupMenu.show(e.getComponent(),e.getX(),e.getY());
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed(e);

			}

		});
		//��ť���
		JButton addButton=new JButton("��Ӳ�Ʒ");
		addButton.addActionListener(this);
		addButton.setActionCommand("tianjia");
		JButton deletButton=new JButton("ɾ����Ʒ");
		deletButton.addActionListener(this);
		deletButton.setActionCommand("shanchu");
		JButton addcargobButton=new JButton("��Ʒ����");
		addcargobButton.addActionListener(this);
		addcargobButton.setActionCommand("jinhuo");
		JButton gocargoButton=new JButton("��Ʒ����");
		gocargoButton.addActionListener(this);
		gocargoButton.setActionCommand("chuhuo");
		//�����Ͱ�ť���һ��
		JPanel mb2=new JPanel();
		JPanel mb1=new JPanel();
		mb1.add(gdScrollPane);
		mb2.add(addButton);mb2.add(deletButton);mb2.add(addcargobButton);mb2.add(gocargoButton);
		//���ô�ѡ��Ĳ���
		ProductManage.setLayout(new FlowLayout());
		ProductManage.add(mb1);ProductManage.add(mb2);
		ProductManage.setSize(450,500);
		ProductManage.setVisible(true);

		/**
		 * ��Ӧ�̹���ҳ��
		 */

		JPanel supplier=new JPanel();
		suppinfo1=new SupplierInfo();
		bg2Table=new JTable(suppinfo1);

		JScrollPane gd2=new JScrollPane(bg2Table);
		final JPopupMenu popupMenu2=new JPopupMenu();
		JMenu jMenu2=new JMenu("�˵�");
		JMenuItem  jMenuItem2[]={new JMenuItem("�鿴����"),new JMenuItem("ˢ��"),new JMenuItem("��ӹ�����"),
				new JMenuItem("�޸Ĺ�����"),new JMenuItem("�˳�")};
		for (int i = 0; i <2; i++) {
			popupMenu2.add(jMenuItem2[i]);
		}
		popupMenu2.addSeparator();
		for (int i = 2; i <4 ; i++) {
			popupMenu2.add(jMenuItem2[i]);	
		}
		popupMenu2.addSeparator();
		popupMenu2.add(jMenuItem2[4]);
		/**
		 * �Բ˵����м�����ʵ���书��
		 */
		jMenuItem2[0].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//�鿴����
				int xz2=bg2Table.getSelectedRow();
				if (xz2==-1) {
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�鿴�Ĺ�����", "����", JOptionPane.WARNING_MESSAGE);
				}else {
					new Detail2(suppinfo1, xz2);
				}

			}
		});
		jMenuItem2[1].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//ˢ��
				suppinfo1=new SupplierInfo();
				bg2Table.setModel(suppinfo1);
			}
		});
		jMenuItem2[2].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ӹ�����
				new addsupplier(); 

			}
		});
		jMenuItem2[3].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				final int xz2=bg2Table.getSelectedRow();
				if (xz2==-1) {
					JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ������");
					return;
				} else {
					new ChangeSupplier(suppinfo1, xz2);
				}
			}
		});
		//�˳��˵�
		jMenuItem2[4].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu2.show(false);
			}
		});
		/*
		 * �Ա���мӼ��������˵�
		 */
		bg2Table.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu2.show(true);
					popupMenu2.show(e.getComponent(),e.getX(),e.getY());
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed(e);

			}

		});


		//��ť���
		JButton addsupplierButton=new JButton("��ӹ�Ӧ��");
		addsupplierButton.addActionListener(this);
		addsupplierButton.setActionCommand("tianjiagonghuo");
		JButton deletesupplierButton=new JButton("ɾ����Ӧ��");
		deletesupplierButton.addActionListener(this);
		deletesupplierButton.setActionCommand("shanchugonghuo");
		JButton changesupplierButton=new JButton("�޸Ĺ�Ӧ��");
		changesupplierButton.addActionListener(this);
		changesupplierButton.setActionCommand("xiugaigonghuo");
		//�������ӵ������
		JPanel supplierPanel=new JPanel();
		supplierPanel.add(addsupplierButton);
		supplierPanel.add(deletesupplierButton);
		supplierPanel.add(changesupplierButton);
		supplier.add(gd2);
		supplier.add(supplierPanel,BorderLayout.SOUTH);

		/**
		 *  ��Ʒ��ͼ
		 * 
		 */
		JPanel view=new JPanel();
		viewinfo1=new ViewInfo();
		bg3Table=new JTable(viewinfo1);
		JScrollPane gd3=new JScrollPane(bg3Table);
		view.add(gd3);
		JLabel label=new JLabel("����");
		String string[]={"���","������","���ڴ˼۸�","���ڴ˼۸�"};
		final JComboBox<String> comboBox=new JComboBox<String>(string);
		final JTextField wriTextField=new JTextField(10);
		ww=wriTextField.getText();
		combo=comboBox.getSelectedIndex();	
		JButton exportButton=new JButton("��������");
		exportButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//�������ݵ��ı�
				selectFromDb();
				JOptionPane.showMessageDialog(null, "����ɹ�");

			}
		});
		JButton lookButton=new JButton("��ѯ");
		//�Բ�ѯ��ť�Ӽ���
		lookButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int combo=comboBox.getSelectedIndex();
				String ss=null;
				if (wriTextField.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "��������Ҫ��ѯ������","����",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (combo==0) {
					ss="select * from productinfo where id='"+wriTextField.getText()+"'";
				}else if (combo==1) {
					ss="select * from productinfo where supplier='"+wriTextField.getText()+"'";
				}else if (combo==2) {
					//���ڴ˼۸�
					int ii=Integer.parseInt(wriTextField.getText());
					ss="select * from productinfo where price>'"+ii+"'";
				}else if (combo==3) {
					//���ڴ˼۸�
					int ii=Integer.parseInt(wriTextField.getText());
					ss="select * from productinfo where price<'"+ii+"'";
				}
				viewinfo1=new ViewInfo(ss);
				bg3Table.setModel(viewinfo1);
			}	
		});

		JPanel mb=new JPanel();
		mb.setLayout(new FlowLayout());
		mb.add(label);mb.add(comboBox);mb.add(wriTextField);mb.add(lookButton);mb.add(exportButton);
		view.add(mb,BorderLayout.SOUTH);

		/**
		 * ��������
		 */
		JPanel functionPanel=new JPanel();
		ImageIcon background=new ImageIcon("·��С.jpg");
		JLabel image=new JLabel(background);
		JCheckBox remindBox=new JCheckBox("��������          ");
		final JCheckBox noChange=new JCheckBox("��������          ");
		final JCheckBox lockBox=new JCheckBox("�����û�          ");
		final JCheckBox timeBox=new JCheckBox("��ʾʱ��          ");
		JRadioButton windowButton=new JRadioButton("Window ���         ");
		JRadioButton numisButton=new JRadioButton("Nimbus ���      ");
		JRadioButton windowClassButton=new JRadioButton("Window Classic ���");
		final JButton logoutButton=new JButton("�û�ע��");
		JButton exitButton2=new JButton("�û�ע��");
		ButtonGroup styleButtonGroup=new ButtonGroup();
		styleButtonGroup.add(windowButton);styleButtonGroup.add(numisButton);styleButtonGroup.add(windowClassButton);
		//������
		windowButton.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					UIManager.setLookAndFeel(new WindowsLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}	
			}
		});
		numisButton.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
			}
		});
		winclassItem.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
				} catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
			}
		});

		//�������Ѽ���
		remindBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				java.sql.Connection connection=null;
				java.sql.Statement statement=null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/cangkuguanli","root","leikewei000");
					statement=connection.createStatement();
					String sql="select * from productinfo where inver<'100'";
					ResultSet resultSet=statement.executeQuery(sql);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		//�û�ע������
		logoutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Longing();
				setVisible(false);
			}
		});
		//�������ݼ���
		noChange.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				//���ñ�񲻿ɸ���
				boolean nochange= noChange.isSelected();
				if (nochange) {
					bgTable.setEnabled(false);
					bg2Table.setEnabled(false);
					bg3Table.setEnabled(false);
				}else {
					bgTable.setEnabled(true);
					bg2Table.setEnabled(true);
					bg3Table.setEnabled(true);
				}
			}
		});
		//��ʾʱ�����
		timeBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				//����ʱ��
				final boolean box=timeBox.isSelected();
				if (box==true) {
					JLabel time=new JLabel();
					setTimer(time);
				}else if (box==false) {
					setTitle("�ֿ����ϵͳ");
					timeAction.stop();
					return;
				}	
			}
		});
		//�����û�����
		lockBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				boolean lock=lockBox.isSelected();
				if (lock) {
					logoutButton.setEnabled(false);
				}else {
					logoutButton.setEnabled(true);
				}
			}
		});
		//�û�ע�����
		exitButton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new AddUser();
			}
		});
		JLabel fontLabel=new JLabel("                      ����ѡ��   ");
		String fontString[]={"����","����","�����п�"};
		final JComboBox<String> fontComboBox=new JComboBox<String>(fontString);
		//������ѡ��Ӽ���
		JButton confirmButton=new JButton("ѡ��");
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//�޸�����
				if (fontComboBox.getSelectedIndex()==0) {
					InitGlobalFont(new Font("�����п�", Font.PLAIN, 12));
				}
			}
		});
		functionPanel.setLayout(new FlowLayout());
		functionPanel.add(image,BorderLayout.NORTH);
		functionPanel.add(remindBox);functionPanel.add(noChange);
		functionPanel.add(lockBox);functionPanel.add(timeBox);
		functionPanel.add(windowButton);functionPanel.add(windowClassButton);functionPanel.add(numisButton);
		functionPanel.add(fontLabel);functionPanel.add(fontComboBox);functionPanel.add(confirmButton);
		JPanel mbfPanel=new JPanel();
		mbfPanel.add(logoutButton);mbfPanel.add(exitButton2);
		functionPanel.add(mbfPanel,BorderLayout.SOUTH);


		/**
		 * ���������
		 */
		xxk.add(ProductManage,"��Ʒ����");
		xxk.add(supplier,"��Ӧ�̹���");
		xxk.add(view,"��Ʒ��ͼ");
		xxk.add(functionPanel,"��������");
		this.add(hyLabel,BorderLayout.NORTH);
		this.add(xxk,BorderLayout.CENTER);
		this.add(exitButton,BorderLayout.SOUTH);
		//������ҳ��Ĳ���
		//this.setTimer(time);
		this.setTitle("�ֿ����ϵͳ");
		this.setJMenuBar(cdlBar);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(465, 750);
		this.setLocation(530, 80);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("����.png").getImage());

	}

	/**
	 * ��������
	 */
	public void actionPerformed(ActionEvent e) {
		//��Ӳ�Ʒ
		if(e.getActionCommand().equals("tianjia")){
			TianJia tianJia=new TianJia();
			proinfo1=new ProductInfo();
			bgTable.setModel(proinfo1);
			tianJia.setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
		//ɾ����Ʒ
		else if (e.getActionCommand().equals("shanchu")) {
			int xz=this.bgTable.getSelectedRow();
			//�ж��Ƿ�ѡ��
			if(xz==-1){
				JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ���Ĳ�Ʒ");
				return;
			}else {
				//��ʼɾ����Ʒ
				if (JOptionPane.showConfirmDialog(null, "ȷ��ɾ���˲�Ʒ��")==JOptionPane.YES_OPTION) {
					deleteproduct(xz);
				}else {
					return;
				}					
			}

			//��Ʒ����
		}else if (e.getActionCommand().equals("jinhuo")) {
			int xz3=this.bgTable.getSelectedRow();
			if (xz3==-1) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ҫ��������Ʒ");
			}
			new AddCargob(proinfo1, xz3);
			proinfo1=new ProductInfo();
			bgTable.setModel(proinfo1);

		}else if (e.getActionCommand().equals("chuhuo")) {
			int xz3=this.bgTable.getSelectedRow();
			if (xz3==-1) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ҫ��������Ʒ");
				return;
			}else {
				new GoCargob(proinfo1,xz3);
			}
		}
		//��ӹ�����	
		else if(e.getActionCommand().equals("tianjiagonghuo")){
			addsupplier addsupplier=new addsupplier();
			addsupplier.setDefaultCloseOperation(HIDE_ON_CLOSE);

			//ɾ��������
		}else if (e.getActionCommand().equals("shanchugonghuo")) {
			int xz2=this.bg2Table.getSelectedRow();
			if (xz2==-1) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ���Ĺ�����");
				return;
			}else {
				if (JOptionPane.showConfirmDialog(null, "ȷ��ɾ���˹����̣�")==JOptionPane.YES_OPTION) {
					DeleteSupplier(xz2);
				}else {
					return;
				}			
			}

			//�޸Ĺ�����
		}else if (e.getActionCommand().equals("xiugaigonghuo")) {
			int xz2=this.bg2Table.getSelectedRow();
			if (xz2==-1) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ������");
				return;
			} else {
				new ChangeSupplier(suppinfo1, xz2);
			}
		}

	}
	/**
	 * 
	 * �������ݿ�
	 * @return 
	 */
	public Connection LinkData(){
		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://loaclhost:3306/cangkuguanli";
			String userString="root";
			String passwordString="leikewei000";
			connection=DriverManager.getConnection(url,userString,passwordString);
			statement=connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}



	/**
	 * ִ��ɾ����Ʒ
	 * @param xz����ѡ����
	 */
	public void deleteproduct (int xz) {
		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		PreparedStatement preparedStatement=null;
		String st=(String)proinfo1.getValueAt(xz,0);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/cangkuguanli";
			String userString="root";
			String passwString="leikewei000";
			connection=DriverManager.getConnection(url,userString,passwString);
			statement=connection.createStatement();
			//��̬ɾ��ѡ����
			preparedStatement=connection.prepareStatement("delete from productinfo where id=?");
			preparedStatement.setString(1,st);
			preparedStatement.executeUpdate();		
			JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
			proinfo1=new ProductInfo();
			bgTable.setModel(proinfo1);
			connection.close();
			statement.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ִ��ɾ��������
	 * 
	 */
	public void DeleteSupplier(int xz2) {
		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		String st=(String)suppinfo1.getValueAt(xz2,0);
		PreparedStatement preparedStatement=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/cangkuguanli";
			String userString="root";
			String passwordString="leikewei000";
			connection=DriverManager.getConnection(url, userString, passwordString);
			statement=connection.createStatement();
			preparedStatement=connection.prepareStatement("delete from supplierinfo where name=?");
			preparedStatement.setString(1,st);
			preparedStatement.executeUpdate();		
			JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
			suppinfo1=new SupplierInfo();
			bg2Table.setModel(suppinfo1);
			statement.close();
			connection.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/*
	 * ��ʾʱ��
	 * 
	 */
	//����Timer 1000msʵ��һ�ζ��� ʵ����һ���߳�   
	private void setTimer(JLabel time){   
		final JLabel varTime = time;   
		timeAction = new Timer(1000, new ActionListener() {          

			public void actionPerformed(ActionEvent e) { 
				//�����Ժ���Ϊ��λ�ĵ�ǰʱ��
				long timemillis = System.currentTimeMillis();   
				//ת��������ʾ��ʽ   
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				//format�����ø�ʽ��
				varTime.setText(df.format(new Date(timemillis)));   

				String timeString=varTime.getText();
				setTitle("�ֿ����ϵͳ          "+timeString);
			}      
		});            
		timeAction.start();  
	}

	/**
	 * �ı�����
	 * @param args
	 */
	private void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys();keys.hasMoreElements(); )
		{Object key = keys.nextElement();Object value = UIManager.get(key);
		if (value instanceof FontUIResource){UIManager.put(key, fontRes);}}
	}
	/**
	 * �����ݵ��뵽�ı���
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public void selectFromDb(){

		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/cangkuguanli";
			String userString="root";
			String passwordString="leikewei000";
			connection=DriverManager.getConnection(url, userString, passwordString);
			statement=connection.createStatement();
			String sql="select * from productinfo";
			ResultSet resultSet=statement.executeQuery(sql);
			OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream("c.txt"),"UTF-8");
			BufferedWriter bw = new BufferedWriter(output);
		
			while (resultSet.next()) {
				String id=resultSet.getString(1);	
				String name=resultSet.getString(2);
				String price=resultSet.getString(3);
				
				
				bw.write("id="+id+"   "+"name="+name+"    "+"price="+price+"\n");
				
			}
			bw.close();
			output.close();
			



		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String args[]){
		StoreManage appManage=new StoreManage();
	}

}
