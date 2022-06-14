/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.managerClasses;

import backend.datatypes.Person;
import backend.datatypes.Voter;
import backend.utility.DB;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cliftonb
 */
public class SystemManager {

	public static DB db;
	public static Person admin;
	public static Voter voter;
	public static CandidateManager cm;
	public static VoterManager vm;

	private static final String adminPasswords = "data//AdminPassword.txt";

	public SystemManager() {
		try {
			db = new DB();
			System.out.println("SM: DB object created");
			cm = new CandidateManager();
			System.out.println("SM: Candidates initialised");
			vm = new VoterManager();
			System.out.println("SM: Voters initialised");
		} catch (ClassNotFoundException ex) {
			System.out.println("SM: Database driver could not be loaded");
			Logger.getLogger(SystemManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			System.out.println("SM: Database connection or query issue");
			Logger.getLogger(SystemManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static boolean validateUser(boolean admin, String username, String password) throws SQLException {
		if (admin) {
			try {
				Scanner fileSc = new Scanner(new File(adminPasswords));

				while (fileSc.hasNextLine()) {
					String line = fileSc.nextLine();

					Scanner lineSc = new Scanner(line).useDelimiter("#");

					String adminID = lineSc.next();
					String adminName = lineSc.next();
					String adminSurname = lineSc.next();
					String adminUsername = lineSc.next();
					String adminPassword = lineSc.next();

					if (adminUsername.equalsIgnoreCase(username) && adminPassword.equals(password)) {
						SystemManager.admin = new Person(adminID, adminName, adminSurname, adminPassword);
						return true;
					}
				}

				fileSc.close();
				return false;
			} catch (FileNotFoundException ex) {
				System.out.println("Could not locate admin passwords file");
				Logger.getLogger(SystemManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			String query = "SELECT * FROM tblVoters WHERE VUsername = '" + username + "' AND VPassword = '" + password + "';";
			ResultSet rs = SystemManager.db.query(query);

			if (!rs.isBeforeFirst()) {
				return false;
			} else {
				rs.next();
				String vID = rs.getString("IDVoter");
				String vUsername = rs.getString("VUsername");
				String vName = rs.getString("VFirstname");
				String vSurname = rs.getString("VSurname");
				String vRegCl = rs.getString("VClass");
				String vPassword = rs.getString("VPassword");

				voter = new Voter(vID, vName, vSurname, vPassword, vUsername, vRegCl);
				return true;
			}

		}
		return false;
	}

}
