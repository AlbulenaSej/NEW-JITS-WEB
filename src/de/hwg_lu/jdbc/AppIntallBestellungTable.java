package de.hwg_lu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;


public class AppIntallBestellungTable {

	public Connection dbConn = null;

	public static void main(String[] args) throws NoConnectionException, SQLException{
		AppIntallBestellungTable myApp = new AppIntallBestellungTable();
		myApp.dbConn = new PostgreSQLAccess().getConnection(); 
		myApp.installBestellungTable();
		System.out.println("Postgress geladen");
	}

	public void installBestellungTable() throws SQLException{
		System.out.println("installBestellungTable() gestartet"); 
//		this.dropBestellungTable();
//		System.out.println("Table gedroppt");
		this.createBestellungTable();
		System.out.println("neuTable");
		this.showBestellungData();
	}

	public void dropBestellungTable() throws SQLException {
		String sql = "drop table Bestellung";
		System.out.println(sql);
			dbConn.createStatement().executeUpdate(sql);
			Statement myStat = dbConn.createStatement();
			myStat.executeUpdate(sql);
			System.out.println("Tabelle Bestellung gedroppt");
	}
	



	public void createBestellungTable() throws SQLException{
		String sql = " ";
		 sql = "create table Bestellung ("
		 		+ "wnr INT NOT NULL, "
				+ "email VARCHAR(256) NOT NULL, "
				+ "anr INT NOT NULL, "
				+ "aname VARCHAR(256) NOT NULL, "
				+ "menge INT NOT NULL, "
				+ "preis NUMERIC NOT NULL)";
		System.out.println(sql);
		Statement myStat = dbConn.createStatement();
		myStat.executeUpdate(sql);
		System.out.println("Execute");
		System.out.println("Tabelle Bestellung angelegt");
	}

	
	

	public void showBestellungData() throws SQLException{
		String sql = " SELECT wnr, email, anr, aname, menge, preis " +
			"FROM Bestellung ";
		System.out.println(sql);
		ResultSet dbRes = dbConn.createStatement().executeQuery(sql);
		while (dbRes.next()){
			String wnr = dbRes.getString("wnr");
			String email   = dbRes.getString("email");
			String anr    = dbRes.getString("anr");
			String aname = dbRes.getString("aname");
			String menge    = dbRes.getString("menge");
			String preis   = dbRes.getString("preis");
			String outString = wnr + ", ";
			outString += email + ", ";
			outString += anr + ", ";
			outString += aname + ", ";
			outString += menge + ", ";
			outString += preis;
			
			System.out.println(outString);
		}
	}

} 