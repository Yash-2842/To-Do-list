package todo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class todolist {

	private JFrame frmTodo;
	private JTextField tftask;
	private JTable table;
	private JButton btnComplete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					todolist window = new todolist();
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
	public todolist() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void display()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Yash@2842");
			String un = login.tfusername.getText();
			PreparedStatement pstmt = conn.prepareStatement("select * from task where username=?");
			pstmt.setString(1,un);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rd =  rs.getMetaData();
			int a=rd.getColumnCount();
			DefaultTableModel df=(DefaultTableModel) table.getModel();
			df.setRowCount(0);
			while(rs.next())
			{
				Vector v2=new Vector();
				for(int j=1;j<=a;j++)
				{
					v2.add(rs.getString("title"));
					v2.add(rs.getString("done"));
				}
				df.addRow(v2);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void initialize() {
		frmTodo = new JFrame();
		frmTodo.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Yash\\Downloads\\todo.jpg"));
		frmTodo.getContentPane().setBackground(new Color(102, 255, 153));
		frmTodo.setTitle("TO-DO");
		frmTodo.setBounds(100, 100, 588, 485);
		frmTodo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTodo.getContentPane().setLayout(null);
		
		JLabel lblTodoList = new JLabel("TO-DO LIST");
		lblTodoList.setHorizontalAlignment(SwingConstants.CENTER);
		lblTodoList.setForeground(new Color(0, 51, 255));
		lblTodoList.setFont(new Font("Eras Bold ITC", Font.BOLD | Font.ITALIC, 18));
		lblTodoList.setBounds(211, 0, 144, 32);
		frmTodo.getContentPane().add(lblTodoList);
		
		JLabel lblEnterTaskTo = new JLabel("Enter Task To Add");
		lblEnterTaskTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTaskTo.setForeground(new Color(0, 51, 255));
		lblEnterTaskTo.setFont(new Font("Lucida Handwriting", Font.BOLD, 14));
		lblEnterTaskTo.setBounds(0, 24, 185, 32);
		frmTodo.getContentPane().add(lblEnterTaskTo);
		
		tftask = new JTextField();
		tftask.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tftask.setColumns(10);
		tftask.setBounds(10, 50, 465, 29);
		frmTodo.getContentPane().add(tftask);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Yash@2842");
					
					PreparedStatement prestmt = conn.prepareStatement("insert into task values(?,?,?,?)");
					prestmt.setInt(1, 0);
					prestmt.setString(2, tftask.getText());
					prestmt.setString(3, "Incomplete");
					prestmt.setString(4,login.tfusername.getText());
					prestmt.executeUpdate();
					tftask.setText(null);
					display();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAdd.setForeground(new Color(0, 51, 255));
		btnAdd.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 14));
		btnAdd.setBounds(10, 78, 77, 29);
		frmTodo.getContentPane().add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 552, 322);
		frmTodo.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Title", "Done"
			}
		));
		scrollPane.setViewportView(table);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Yash@2842");
			DefaultTableModel dl =(DefaultTableModel) table.getModel();
			int i=table.getSelectedRow();
			String s= dl.getValueAt(i, 0).toString();
			PreparedStatement pstmt = conn.prepareStatement("update task set title=? where title=? and username=?");
			pstmt.setString(1, tftask.getText());
			pstmt.setString(2, s);
			pstmt.setString(3,login.tfusername.getText());
			pstmt.executeUpdate();
			display();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnEdit.setForeground(new Color(0, 51, 255));
		btnEdit.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 14));
		btnEdit.setBounds(97, 78, 77, 29);
		frmTodo.getContentPane().add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Yash@2842");
				DefaultTableModel dl =(DefaultTableModel) table.getModel();
				int i=table.getSelectedRow();
				String s= dl.getValueAt(i, 0).toString();
				PreparedStatement pstmt = conn.prepareStatement("delete from task where title=? and username=?");
				pstmt.setString(1, s);
				pstmt.setString(2, login.tfusername.getText());
				pstmt.executeUpdate();
				display();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		});
		btnDelete.setForeground(new Color(0, 51, 255));
		btnDelete.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 14));
		btnDelete.setBounds(184, 78, 103, 29);
		frmTodo.getContentPane().add(btnDelete);
		
		btnComplete = new JButton("Complete");
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","Yash@2842");
				DefaultTableModel dl =(DefaultTableModel) table.getModel();
				int i=table.getSelectedRow();
				String s= dl.getValueAt(i, 0).toString();
				
				PreparedStatement pstmt = conn.prepareStatement("update task set done=? where title=? and username=?");
				if(btnComplete.getText()=="Complete")
				{	
					pstmt.setString(1, "Complete");
					table.setValueAt("Complete", i, 1);
				}else {
					pstmt.setString(1, "InComplete");
					table.setValueAt("InComplete", i, 1);
				}
				pstmt.setString(2, s);
				pstmt.setString(3, login.tfusername.getText());
				int x=pstmt.executeUpdate();
				System.out.println(x);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnComplete.setForeground(new Color(0, 51, 255));
		btnComplete.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 14));
		btnComplete.setBounds(297, 78, 178, 29);
		frmTodo.getContentPane().add(btnComplete);
		display();
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		    	  if(table.hasFocus()) {
		    	  String str=table.getValueAt(table.getSelectedRow(),1).toString();
		    	  
		    	if(str.startsWith("I"))
		    		btnComplete.setText("Complete");
		    	else
		    		btnComplete.setText("InComplete");
		    	  }
		      }

		    });
	}
	
}
