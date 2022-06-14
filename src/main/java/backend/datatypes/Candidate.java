/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.datatypes;

/**
 *
 * @author Cliftonb
 */
public class Candidate extends Person {

	private int noOfVotes;
	private String regClass;

	public Candidate(String ID, String name, String surname, String password, int noOfVotes, String regClass) {
		super(ID, name, surname, password);
		this.noOfVotes = noOfVotes;
		this.regClass = regClass;
	}

	public String getClassName() {
		return regClass;
	}

	public void setVoteCount(int count) {
		this.noOfVotes = count;
	}

	public int getVoteCount() {
		return this.noOfVotes;
	}

	public String toString() {
		return super.toString() + ", " + noOfVotes;
	}

}
