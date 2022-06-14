/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.managerClasses;

import backend.datatypes.Candidate;
import backend.datatypes.Voter;
import backend.utility.DB;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cliftonb
 */
public class VoterManager {

	public void addNewVoter(Voter voter) throws SQLException {
		String query = "INSERT INTO tblvoters (IDVoter, VUsername ,VFirstName, VSurname, VClass, VPassword) "
			+ "VALUES ('" + voter.getID() + "','" + voter.getUsername() + "','" + voter.getName() + "','"
			+ voter.getSurname() + "','" + voter.getClassName() + "','" + voter.getPassword() + "');";
		SystemManager.db.update(query);
	}

	public String getAllVoters() throws SQLException {
		String query = "SELECT * FROM tblvoters;";
		ResultSet voters = SystemManager.db.query(query);

		String output = "";
		while (voters.next()) {
			output += voters.getString("IDVoter") + ", ";
			output += voters.getString("VUsername") + ", ";
			output += voters.getString("VFirstName") + ", ";
			output += voters.getString("VSurname") + ", ";
			output += voters.getString("VClass");
			output += "\n";

		}

		return output;

	}

	public String voteCount() throws SQLException {
		String query = "SELECT CFirstname, CSurname, COUNT(IDVoter) FROM tblvoting, tblcandidates "
			+ "WHERE tblcandidates.IDCandidate = tblvoting.IDCandidate "
			+ "GROUP BY tblvoting.IDCandidate "
			+ "ORDER BY COUNT(IDVoter);";
		ResultSet voters = SystemManager.db.query(query);

		return DB.toString(voters);
	}

	public void vote(Candidate c) throws SQLException {
		String query = "INSERT INTO tblvoting (IDCandidate, IDVoter) "
			+ "VALUES ('" + c.getID() + "','" + SystemManager.voter.getID() + "');";

		SystemManager.db.update(query);
	}
}
