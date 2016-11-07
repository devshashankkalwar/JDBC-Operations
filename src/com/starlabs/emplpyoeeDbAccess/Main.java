package com.starlabs.emplpyoeeDbAccess;


import java.sql.Connection;

import com.starlabs.model.Employee;

/****
 * 
 * @author AveNGeR
 *This is the main class with main psvm method to perform CURD requests to the database
 */



public class Main {
	
	public static void main(String[] args){
	
		
		System.out.println("\n \n");
		EmployeeDAO employeeDao = new EmployeeDAO();
		Employee[] emplist = employeeDao.getALlEmployee();
		for(Employee emp:emplist){
			System.out.println(emp);
		}
		System.out.println("\n \n");
		System.out.println(employeeDao.findEmployeeByEmail("tom@gmail.com") == null?
				"No Match Found":"Employee Found:\n"+employeeDao.findEmployeeByEmail("tom@gmail.com"));
		System.out.println("\n \n");
		System.out.println(employeeDao.findEmployeeById(5) == null?
				"No Match Found":"Employee Found:\n"+employeeDao.findEmployeeById(5));
		System.out.println("\n \n");
		System.out.println(employeeDao.findEmployeeById(15) == null?
				"No Match Found":"Employee Found:\n"+employeeDao.findEmployeeById(15));
		System.out.println("\n \n");
		System.out.println(employeeDao.findEmployeeByName("Harry") == null?
				"No Match Found":"Employee Found:\n"+employeeDao.findEmployeeByName("Harry"));
		System.out.println("\n \n");
		//System.out.println(employeeDao.updateEmployeeName(7, "RIP")+" record updated");
		//System.out.println(employeeDao.updateEmployeeSalary(7, 12000000.00)+" record updated");
		//System.out.println(employeeDao.updateEmployeeEmail(5, "mohammad@gmail.com")+" record updated");
		//System.out.println(employeeDao.deleteEmployee(20)+" record deleted");
	
		System.out.println(employeeDao.addEmployee()+" record added");
	}
	
}
