/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.managerClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cliftonb
 */
public class DatesManager {

	private LocalDate startDate;
	private LocalDate endDate;
	private DateTimeFormatter df = DateTimeFormatter.BASIC_ISO_DATE;
	private final String textPath = "data//Dates.txt";

	public DatesManager() {
		try {
			Scanner sc = new Scanner(new File(textPath));
			if (sc.hasNext()) {
				startDate = LocalDate.parse(sc.next(), df);
				endDate = LocalDate.parse(sc.next(), df);
			}
		} catch (IOException ex) {
			System.out.println("DM: could not locate dates file");
			Logger.getLogger(DatesManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setNewDates(LocalDate startDate, LocalDate endDate) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(textPath, false));
			pw.print(startDate.format(df) + " " + endDate.format(df));
			this.startDate = startDate;
			this.endDate = endDate;

		} catch (IOException ex) {
			System.out.println("DM: could not locate dates file");
			Logger.getLogger(DatesManager.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			pw.close();
		}

	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

}
