/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.managerClasses;

import backend.datatypes.Candidate;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cliftonb
 */
public class CandidateManager {

	private Candidate[] caArr;
	private int size;
	private int currentCandidate;

	public CandidateManager() throws SQLException {
		caArr = new Candidate[100];
		size = 0;
		currentCandidate = 0;

		String query = "SELECT * FROM tblcandidates;";
		ResultSet candidates = SystemManager.db.query(query);

		while (candidates.next()) {
			String CID = candidates.getString("IDCandidate");
			String firstname = candidates.getString("CFirstName");
			String surname = candidates.getString("CSurname");
			String className = candidates.getString("CClass");
			int noVotes = candidates.getInt("CNoOfVotes");

			caArr[size] = new Candidate(CID, firstname, surname, null, noVotes, className);
			size++;
		}

		populateCandidateVotes();
	}

	public int getSize() {
		return this.size;
	}

	public Candidate getCurrentCandidate() {
		return caArr[currentCandidate];
	}

	public Candidate nextCandidate() {
		if (currentCandidate == size - 1) {
			return caArr[size - 1];
		} else {
			return caArr[++currentCandidate];
		}
	}

	public Candidate previousCandidate() {
		if (currentCandidate == 0) {
			return caArr[0];
		} else {
			return caArr[--currentCandidate];
		}
	}

	public void setCurrentCandidate(int i) {
		if (i >= 0 && i < size) {
			currentCandidate = i;
		}
	}

	public void addNewCandidate(Candidate can) throws SQLException {
		String query = "INSERT INTO tblcandidates (IDCandidate, CFirstName, CSurname, CClass) "
			+ "VALUES ('" + can.getID() + "','" + can.getName() + "','" + can.getSurname() + "','" + can.getClassName() + "');";
		SystemManager.db.update(query);

		//if successful add to the array
		caArr[size] = can;
		size++;
	}

	private void populateCandidateVotes() throws SQLException {

		for (int i = 0; i < size; i++) {
			String query = "SELECT COUNT(IDCandidate) FROM tblvoting WHERE IDCandidate = '" + caArr[i].getID() + "';";
			ResultSet rs = SystemManager.db.query(query);
			rs.next();
			int votes = rs.getInt(1);

			caArr[i].setVoteCount(votes);

		}
	}

	public String toString() {
		String output = "";
		for (int i = 0; i < size; i++) {
			output += caArr[i] + "\n";
		}

		return output;
	}

}
