package com.starlabs.emplpyoeeDbAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.starlabs.ConnectionManger.ConnectionFactory;
import com.starlabs.model.Employee;
// Method to fetch all the employee data from the data base
public class EmployeeDAO {
	public Employee[] getALlEmployee(){
		Connection con = ConnectionFactory.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("Select * from Employee");
			Employee[] emplist = new Employee[noOfRows(res)];
			int index=0;
			while (res.next()){
				emplist[index++]=new Employee(res.getInt(1),res.getString(2),res.getDouble(3),res.getString(4));
				}
			return emplist;
		}catch (SQLException e){
			e.printStackTrace();
			return null;

		}
	}
	// get number of rows in database
	private static int noOfRows(ResultSet res) {
		int count = 0;
		try{
			while(res.next()){
			count++;
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		try{
			res.beforeFirst();
		}catch (SQLException e)
		{
			e.printStackTrace();
		} 
		return count;
	}
	//filter employee by email
	public Employee findEmployeeByEmail(String email)
	{
		Connection con = ConnectionFactory.getConnection();
		Employee emp= null;
		try{
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("Select * from Employee where email=\'"+email+"\'");
			while (res.next()){
				emp = new Employee(res.getInt(1),res.getString(2),res.getDouble(3),res.getString(4));
				} 
			return emp;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//filter employee by id
	public Employee findEmployeeById(int id){
		Connection con = ConnectionFactory.getConnection();
		Employee emp= null;
		try{
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("Select * from Employee where ID=\'"+id+"\'");
			while (res.next()){
				emp = new Employee(res.getInt(1),res.getString(2),res.getDouble(3),res.getString(4));
				} 
			return emp;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	// filter employee by name
	public Employee findEmployeeByName(String name){
		Connection con = ConnectionFactory.getConnection();
		Employee emp= null;
		try{
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("Select * from Employee where name =\'"+name+"\'");
			while (res.next()){
				emp = new Employee(res.getInt(1),res.getString(2),res.getDouble(3),res.getString(4));
				} 
			return emp;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	// update employee name by id
	public int updateEmployeeName(int id, String name){
		Connection con = ConnectionFactory.getConnection();
		try{
			Statement stmt = con.createStatement();
			int count = stmt.executeUpdate("Update employee set name =\'"
										+name+"\' where id="+id);
			return count;
		}catch (SQLException e){
			e.printStackTrace();
			return 0;
		}		
	}
	
	//update employee salary
	public int updateEmployeeSalary(int id, double salary){
		Connection con = ConnectionFactory.getConnection();
		try{
			Statement stmt = con.createStatement();
			int count = stmt.executeUpdate("Update employee set salary ="
										+salary+" where id="+id);
			return count;
		}catch (SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
	// update email by id
	public int updateEmployeeEmail(int id, String email){
		Connection con = ConnectionFactory.getConnection();
		try{
			Statement stmt = con.createStatement();
			int count = stmt.executeUpdate("Update employee set email =\'"
										+email+"\' where id="+id);
			return count;
		}catch (SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
	//delete employee
	public int deleteEmployee(int id){
		Connection con = ConnectionFactory.getConnection();
		try{
			Statement stmt = con.createStatement();
			int count = stmt.executeUpdate("delete from employee where id="+id);
			return count;
		}catch (SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
	// add new employee
	public int addEmployee(){
		Connection con = ConnectionFactory.getConnection();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
		String s;
		int count=0;
		try{
			do{
			/*System.out.print("Enter id: ");
			int id =Integer.parseInt(br.readLine());*/
			System.out.print("Enter name: ");
			String name = br.readLine();
			System.out.print("Enter Salary: ");
			double salary= Double.parseDouble(br.readLine());
			System.out.print("Enter Email: ");
			String email = br.readLine();
							Statement stmt = con.createStatement();
			stmt.executeUpdate("insert into employee values(NULL,"+"\'"+name+"\'"+","+salary+","+"\'"+email+"\')");
			count++;
			System.out.println("Enter Y to add more employee or N to exit");
			s= br.readLine();
			
		}while(s.startsWith("Y")||s.startsWith("y"));
		
		return count;
		}
		catch (SQLException e){
			e.printStackTrace();
			return 0;}
		catch (IOException e){
		 e.printStackTrace();
			return 0;
		}
	}
		
}
	

