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
public class Person {

	protected String ID;
	protected String name;
	protected String surname;
	protected String password;

	public Person(String ID, String name, String surname, String password) {
		this.ID = ID;
		this.name = name;
		this.surname = surname;
		this.password = password;
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String toString() {
		return ID + ", " + name + " " + surname;
	}
}
