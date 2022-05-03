package todo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class signup {

	private JFrame frmTo;
	private JTextField tffirstname;
	private JTextField tflastname;
	private JTextField tfusername;
	private JTextField tfpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup window = new signup();
					window.frmTo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTo = new JFrame();
		frmTo.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Yash\\Downloads\\todo.jpg"));
		frmTo.getContentPane().setBackground(new Color(102, 255, 153));
		frmTo.setTitle("TO-DO");
		frmTo.setBounds(100, 100, 450, 298);
		frmTo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTo.getContentPane().setLayout(null);
		
		JLabel lblSignUp = new JLabel("SIGN UP");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(new Color(0, 51, 255));
		lblSignUp.setFont(new Font("Eras Bold ITC", Font.BOLD | Font.ITALIC, 18));
		lblSignUp.setBounds(144, 0, 121, 32);
		frmTo.getContentPane().add(lblSignUp);
		
		JLabel lblFirstName = new JLabel("FIRST NAME");
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setForeground(new Color(0, 51, 255));
		lblFirstName.setFont(new Font("Lucida Handwriting", Font.BOLD, 14));
		lblFirstName.setBounds(10, 45, 121, 32);
		frmTo.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("LAST NAME");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setForeground(new Color(0, 51, 255));
		lblLastName.setFont(new Font("Lucida Handwriting", Font.BOLD, 14));
		lblLastName.setBounds(10, 77, 121, 32);
		frmTo.getContentPane().add(lblLastName);
		
		JLabel lblUserName = new JLabel("USER NAME");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setForeground(new Color(0, 51, 255));
		lblUserName.setFont(new Font("Lucida Handwriting", Font.BOLD, 14));
		lblUserName.setBounds(10, 110, 121, 32);
		frmTo.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(0, 51, 255));
		lblPassword.setFont(new Font("Lucida Handwriting", Font.BOLD, 14));
		lblPassword.setBounds(10, 140, 121, 32);
		frmTo.getContentPane().add(lblPassword);
		
		tffirstname = new JTextField();
		tffirstname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tffirstname.setColumns(10);
		tffirstname.setBounds(154, 52, 194, 20);
		frmTo.getContentPane().add(tffirstname);
		
		tflastname = new JTextField();
		tflastname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tflastname.setColumns(10);
		tflastname.setBounds(154, 84, 194, 20);
		frmTo.getContentPane().add(tflastname);
		
		tfusername = new JTextField();
		tfusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfusername.setColumns(10);
		tfusername.setBounds(154, 117, 194, 20);
		frmTo.getContentPane().add(tfusername);
		
		tfpassword = new JTextField();
		tfpassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfpassword.setColumns(10);
		tfpassword.setBounds(154, 147, 194, 20);
		frmTo.getContentPane().add(tfpassword);
		
		JButton btnSignUp = new JButton("SIGN UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				try {

					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Yash@2842");
					PreparedStatement pstmt = conn.prepareStatement("insert into signup(username,password,firstname,lastname) values(?,?,?,?)");
					pstmt.setString(1, tfusername.getText());
					pstmt.setString(2, tfpassword.getText());
					pstmt.setString(3, tffirstname.getText());
					pstmt.setString(4, tflastname.getText());
					int x=0;
					x=pstmt.executeUpdate();
					System.out.println(x);
					if(x==1)
					{
						login.main(null);
					}
					else {
						System.out.println("Can not inserted");
					}
				}catch(SQLException e) {
					JOptionPane.showMessageDialog(null, "Use Different username");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSignUp.setForeground(new Color(0, 51, 255));
		btnSignUp.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 18));
		btnSignUp.setBounds(108, 205, 157, 37);
		frmTo.getContentPane().add(btnSignUp);
	}

}