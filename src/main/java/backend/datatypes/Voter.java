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
public class Voter extends Person {

	private String regClass;
	private String username;

	public Voter(String ID, String name, String surname, String password, String username, String regClass) {
		super(ID, name, surname, password);

		this.regClass = regClass;
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public String toString() {
		return super.toString() + ", " + regClass;
	}

	public String getClassName() {
		return regClass;
	}

	public String getUsername() {
		return username;
	}

}
