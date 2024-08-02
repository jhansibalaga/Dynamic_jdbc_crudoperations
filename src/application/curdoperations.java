package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class curdoperations {
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "root";	
	private static Connection conn;
	private static PreparedStatement pmst;

	public static void main(String[] args) {
		Scanner src = new Scanner(System.in);
		int ch;
		do {
			display();
			System.out.println("enter your choice :");
			ch = Integer.parseInt(src.next());
			switch (ch) {
			case 1: 
				createdatabase();
				break;			
			case 2:
				dropdatabase();
				break;						
			case 3: 
				createtable();
				break;			
			
			case 4: 
				droptable();
				break;			
			
			case 5: 
				insertdata();
				break;			
			
			case 6: 
				deletedata();
				break;	
				
			case 7: 
				update();
				break;					
					
			case 8: 
				getalldata();
				break;			
			
			case 9: 
				getbyid();
				break;			
			
			case 10: 
				System.exit(0);
				break;			
			
			default: 
				System.out.println("invalid choice");
			
			
			}
			
		}
		while(ch>0);

	}

	private static void getbyid() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter database name :");
		String url = "jdbc:mysql://localhost:3306/"+sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name :");
			String query = "select * from "+sc.next() + " where id = ? ";
			pmst = conn.prepareStatement(query);
			System.out.println("enter id :");
			pmst.setInt(1,sc.nextInt());
			ResultSet rs = pmst.executeQuery();
			while(rs.next()) {
				System.out.println("ID :"+ rs.getInt("ID"));
				System.out.println("NAME : "+rs.getString("NAME"));
				System.out.println("EMAIL : "+rs.getString("EMAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
	}

	private static void getalldata() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter database name :");
		String url = "jdbc:mysql://localhost:3306/"+sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name :");
			String query = "select * from "+sc.next();
			pmst = conn.prepareStatement(query);
			ResultSet rs = pmst.executeQuery();
			while(rs.next()) {
				System.out.println("ID :"+ rs.getInt("ID"));
				System.out.println("NAME : "+rs.getString("NAME"));
				System.out.println("EMAIL : "+rs.getString("EMAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

	private static void deletedata() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter database name :");
		String url = "jdbc:mysql://localhost:3306/"+sc.next();		
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name :");
			String query = "delete from "+sc.next()+ " where id = ? ";
			pmst = conn.prepareStatement(query);
			System.out.println("enter id :");
			pmst.setInt(1,sc.nextInt());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("successfully deleted");
			}
			else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void insertdata() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter database name :");
		String url = "jdbc:mysql://localhost:3306/"+sc.next();		
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name :");
			String query = "insert into "+sc.next() + "(id,name,email)" + "values(?,?,?)";
			pmst = conn.prepareStatement(query);
			System.out.println("enter id :");
			pmst.setInt(1,sc.nextInt());
			System.out.println("enter name :");
			pmst.setString(2,sc.next());
			System.out.println("enter email :");
			pmst.setString(3,sc.next());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("successfully inserted");
			}
			else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void update() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter database name :");
		String url = "jdbc:mysql://localhost:3306/"+sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name :");
			String query = "update "+sc.next()+" set name = ? , email = ? where id = ? "; 
			pmst = conn.prepareStatement(query);
			System.out.println("enter name :");
			pmst.setString(1,sc.next());
			System.out.println("enter email :");
			pmst.setString(2,sc.next());
			System.out.println("enter id :");
			pmst.setInt(3,sc.nextInt());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("table updated");
			}
			else {
				System.out.println("table not updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void droptable() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter database name :");
		String url = "jdbc:mysql://localhost:3306/"+sc.next();		
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name :");
			String query = "drop table "+sc.next();
			pmst = conn.prepareStatement(query);
			int i = pmst.executeUpdate();
			if(i==0) {
				System.out.println("table dropped");
			}
			else {
				System.out.println("table not dropped");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void createtable() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter database name :");
		String url = "jdbc:mysql://localhost:3306/"+sc.next();
		
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter table name :");
			String query = "create table "+sc.next() + "(id int,name varchar(30),email varchar(50))";
			pmst = conn.prepareStatement(query);
			int i = pmst.executeUpdate();
			if(i==0) {
				System.out.println("table created");
			}
			else {
				System.out.println("table not created");
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void dropdatabase() {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter database name :");
		String url = "jdbc:mysql://localhost:3306/"+sc.next();
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);	
			String query = "drop database "+sc.next();
			pmst = conn.prepareStatement(query);
			int i = pmst.executeUpdate();
			if(i==0) {
				System.out.println("databse dropped");
			}
			else {
				System.out.println("database not dropped");
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void createdatabase() {
		Scanner sc = new Scanner(System.in);
		 String url = "jdbc:mysql://localhost:3306/";
		 
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("enter database name :");
			String query = "create database "+ sc.next();
			pmst = conn.prepareStatement(query);
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("database created");
			}
			else {
				System.out.println("database not created");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void display() {
		System.out.println("==============================");
		System.out.println("\t1 . create database");
		System.out.println("\t2 . drop database");
		System.out.println("\t3 . create table");
		System.out.println("\t4 . drop table");
		System.out.println("\t5 . insert data");
		System.out.println("\t6 . delete data");
		System.out.println("\t7 . update");
		System.out.println("\t8 . get all data");
		System.out.println("\t9 . get by id");
		System.out.println("\t10 . exit");
		System.out.println("==============================");
	}
}
