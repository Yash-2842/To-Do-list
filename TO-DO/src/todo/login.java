package todo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class login {

	private JFrame frmTodo;
	public static JTextField tfusername;
	private JTextField tfpassword;
	public int id;	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmTodo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTodo = new JFrame();
		frmTodo.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Yash\\Downloads\\todo.jpg"));
		frmTodo.getContentPane().setBackground(new Color(102, 255, 153));
		frmTodo.getContentPane().setLayout(null);
		
		JLabel lblLogIn = new JLabel("LOG IN");
		lblLogIn.setForeground(new Color(0, 51, 255));
		lblLogIn.setFont(new Font("Eras Bold ITC", Font.BOLD | Font.ITALIC, 18));
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setBounds(143, 0, 121, 32);
		frmTodo.getContentPane().add(lblLogIn);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(new Color(0, 51, 255));
		lblUsername.setFont(new Font("Lucida Handwriting", Font.BOLD, 14));
		lblUsername.setBounds(10, 72, 121, 32);
		frmTodo.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(0, 51, 255));
		lblPassword.setFont(new Font("Lucida Handwriting", Font.BOLD, 14));
		lblPassword.setBounds(10, 115, 121, 32);
		frmTodo.getContentPane().add(lblPassword);
		
		tfusername = new JTextField();
		tfusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfusername.setBounds(166, 79, 194, 20);
		frmTodo.getContentPane().add(tfusername);
		tfusername.setColumns(10);
		
		tfpassword = new JTextField();
		tfpassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfpassword.setColumns(10);
		tfpassword.setBounds(166, 122, 194, 20);
		frmTodo.getContentPane().add(tfpassword);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Yash@2842");
					String username = tfusername.getText();
					String password = tfpassword.getText();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from signup ");
					int x=0;
					while(rs.next())
					{
						System.out.println(rs.getString(2)+"     "+rs.getString(3));
						if(rs.getString(2).equals(username) && rs.getString(3).equals(password))
						{
							x=1;
							id=rs.getInt("Id");
							todolist.main(null);
							System.out.println("successfully logined");
						}
					}
					if(x==0)
						JOptionPane.showMessageDialog(null,"Invalid Username or Password");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLogin.setForeground(new Color(0, 51, 255));
		btnLogin.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 15));
		btnLogin.setBounds(166, 176, 112, 37);
		frmTodo.getContentPane().add(btnLogin);
		
		JLabel lblNotRegistered = new JLabel("not registered?");
		lblNotRegistered.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
		lblNotRegistered.setBounds(152, 224, 112, 26);
		frmTodo.getContentPane().add(lblNotRegistered);
		
		JLabel lblSignup = new JLabel("Signup");
		lblSignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				signup s=new signup();
				s.main(null);
			}
		});
		lblSignup.setForeground(new Color(0, 51, 204));
		lblSignup.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 15));
		lblSignup.setBounds(263, 224, 78, 26);
		frmTodo.getContentPane().add(lblSignup);
		frmTodo.setBackground(Color.LIGHT_GRAY);
		frmTodo.setTitle("TO-DO");
		frmTodo.setBounds(100, 100, 450, 300);
		frmTodo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
