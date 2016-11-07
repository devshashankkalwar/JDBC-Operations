package com.starlabs.ConnectionManger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/***
 * 
 * @author AveNGeR
 *Connection Factory class based on Single ton design pattern 
 *designed to open at max two connections to the Mysql database
 *gets all the Properties parameters from application.properties file
 *where in client can modify the params easily
 *
 */
public class ConnectionFactory {
	
	private static Connection con1,con2;
	private static boolean flag=true;
	private Properties prop;
	
	private ConnectionFactory(){
		prop = new Properties();
		try{
		prop.load(new FileInputStream(new File("application.properties")));
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		}
		
	public static Connection getConnection(){
		ConnectionFactory connfact = new ConnectionFactory();
		String host= connfact.prop.getProperty("host");
		String port= connfact.prop.getProperty("port");
		String db= connfact.prop.getProperty("dbname");
		String uname= connfact.prop.getProperty("username");
		String pass= connfact.prop.getProperty("password");
			
			
				try{
				Class.forName("com.mysql.jdbc.Driver");	
				if (con1==null){
				
				con1 = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?useSSL=false", uname,pass);
				return con1;
				} 
				else if(con2== null){
				
				con2 = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?useSSL=false", uname,pass);
				return con2;
				}
				else if (flag==true){
					flag = false;
					return con1;
				}
				else {
					flag=true;
					return con2;
				}
				}
				
				catch (ClassNotFoundException e) {
					e.printStackTrace();
					return null;
				} catch (SQLException e1) {
					e1.printStackTrace();
					return null;
				}
				
				finally {
					try {
						if(con1!=null)
				            con1.close();
						if(con2!=null)
							con2.close();
				        }catch (SQLException e1) {
							e1.printStackTrace();
							return null;
						}
				    }
				
	}
}


