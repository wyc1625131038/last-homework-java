package ckgl;
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class TianJia extends JFrame{
	public static void main(String args[]){
		TianJia tianJia=new TianJia();
	}
	public TianJia(){
		JLabel numberLabl=new JLabel("���");
		JLabel nameLabell=new JLabel("����");
		JLabel priceLabel=new JLabel("�۸�");
		JLabel inverLabel=new JLabel("���");
		JLabel classLabel=new JLabel("���");
		JLabel supplierLabel=new JLabel("������");
		final JTextField numberField=new JTextField(10);
		final JTextField nameField=new JTextField(10);
		final JTextField priceField=new JTextField(10);
		final JTextField inverField=new JTextField(10);
		final JTextField classField=new JTextField(10);
		java.sql.Connection connection=null;
		java.sql.Statement statement=null;
		//String [] gys={"֣���ṤѧԺ","֣�ݴ�ѧ","���ϿƼ�ѧԺ","���Ϲ�ҵ��ѧ","�廪��ѧ"};
		final JComboBox comboBox = new JComboBox();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/cangkuguanli";
			String userString="root";
			String passwString="leikewei000";
			connection=DriverManager.getConnection(url,userString,passwString);
			statement=connection.createStatement();
			String sqlString="select * from supplier";
			ResultSet resultSet=statement.executeQuery(sqlString);		
			while (resultSet.next()) {
				comboBox.addItem(resultSet.getString("supplier"));
				
			}
			statement.close();
			connection.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		JButton quxiaoButton=new JButton("ȡ��");
		quxiaoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}});
		JButton tianjiaButton=new JButton("���");
		tianjiaButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				java.sql.Connection connection=null;
				java.sql.Statement statement=null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String url="jdbc:mysql://localhost:3306/cangkuguanli";
					String userString="root";
					String passwString="123456";
					connection=DriverManager.getConnection(url,userString,passwString);
					statement=connection.createStatement();
					String sqlString="insert into productinfo (id,name,price,inver,class,supplier)values('"+numberField.getText()+"','"+nameField.getText()+"','"+priceField.getText()
					+"','"+inverField.getText()+"','"+classField.getText()+"','"+comboBox.getSelectedItem()+"')";
					statement.executeUpdate(sqlString);
					statement.close();
					connection.close();
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
					
			}catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "���ʧ��");
				}
			}
		});
		this.setLayout(new FlowLayout());
		this.add(numberLabl);this.add(numberField);
		this.add(nameLabell);this.add(nameField);
		this.add(priceLabel);this.add(priceField);
		this.add(inverLabel);this.add(inverField);
		this.add(classLabel);this.add(classField);
		this.add(supplierLabel);this.add(comboBox);
		this.add(tianjiaButton);this.add(quxiaoButton);
		this.setVisible(true);
		this.setTitle("��Ӳ�Ʒ");
		this.setSize(180, 240);
		this.setLocation(650,330);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
		
	}
}
