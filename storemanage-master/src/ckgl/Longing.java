package ckgl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.*;
import javax.swing.*;
public class Longing extends JFrame implements ActionListener{
	public Longing(){
		JLabel tpLabel=new JLabel(new ImageIcon("logo.gif"));
		JLabel userlLabel =new JLabel("�û���:");
		final JTextField userTextField =new JTextField(15);
		JLabel passwLabel =new JLabel(" ��   ��:");
		final JPasswordField passwordField=new JPasswordField(15);
		JButton lButton=new JButton("��¼");
		JButton exitButton=new JButton("�˳�");
		exitButton.addActionListener(new ActionListener(){
			/**
			 * �˳���ť
			 */
			public void actionPerformed(ActionEvent e) {
				System .exit(0);
			}
		});
		
		/**
		 * ��¼��ť�Ӽ���
		 * ��¼��ҳ��
		 */
		lButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String user=userTextField .getText().trim();
				String password=passwordField.getText().trim();
				link(user,password);	
			}
		});
		this.setLayout(new FlowLayout());
		this.add(tpLabel);
		this.add(userlLabel);
		this.add(userTextField);
		this.add(passwLabel);
		this.add(passwordField);
		this.add(lButton);this.add(exitButton);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(270, 180);
		this.setLocation(550, 380);
		this.setTitle("�û���¼");
	}
	
	public static void main(String args[]){
		Longing longing=new Longing();
	}
	public void link(String user,String password){
	if (user.length()==0||password.length()==0) {
		JOptionPane.showMessageDialog(null, "�˺Ż����벻��Ϊ��");
		return;
	}
		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/cangkuguanli";
			String userString="root";
			String passwString="leikewei000";
			connection=DriverManager.getConnection(url,userString,passwString);
			statement=connection.createStatement();
			String sql="select * from mima where user='"+user+"' and password='"+password+"'";
			ResultSet resultSet=statement .executeQuery(sql);
			/**
			 * ������ѯ
			 */
			if (resultSet.next()) {
			/**
			* ������ҳ��
			*/
				StoreManage appManage=new StoreManage();
				System.out.println("�����ɹ�");
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "�������");
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
