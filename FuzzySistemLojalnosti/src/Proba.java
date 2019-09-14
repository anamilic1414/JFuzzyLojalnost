import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.Gpr;
import net.sourceforge.jFuzzyLogic.optimization.ErrorFunction;
import net.sourceforge.jFuzzyLogic.optimization.OptimizationDeltaJump;
import net.sourceforge.jFuzzyLogic.optimization.Parameter;
import net.sourceforge.jFuzzyLogic.plot.JDialogFis;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.RuleBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Proba {

	FIS fis;

	public Proba() {
		fis = FIS.load("rules/pravila.fcl");
		if (fis == null) {
			System.err.println("Greska");
			return;
		}
	}

	public String VratiPravila() {
		String pravila = new String();
		for (Rule r : fis.getFunctionBlock("blok").getFuzzyRuleBlock("ruleBlok").getRules())
			pravila = pravila + "\nPravilo " + r.getName() + ": w=[" + r.getWeight() + "] (" + r.getDegreeOfSupport()
					+ ") ";

		return pravila;
	}

	public void SetovanjeVrednosti(double pPotrosnja, int pUcestalost, double pRedovnost, double pReklamacije,
			int pKomunikacija, int pVremenski, int pPreporuke) {
		fis.setVariable("potrosnja", pPotrosnja);
		fis.setVariable("ucestalost", pUcestalost);
		fis.setVariable("redovnostPlacanja", pRedovnost);
		fis.setVariable("reklamacije", pReklamacije);
		fis.setVariable("vremenskiKupac", pVremenski);
		fis.setVariable("preporuke", pPreporuke);
		fis.setVariable("komunikacija", pKomunikacija);
		fis.evaluate();

	}

	public void PrikaziSveGrafike() throws Exception {

		if (JFuzzyChart.UseMockClass) {
			Gpr.debug("Using mock class");
			return; // Nothing done
		}

		// Create a plot
		JDialogFis jdf = new JDialogFis(fis);

		jdf.repaint();

		// Small delay
		Thread.sleep(100);
	}

	public void PrikaziGrafikLojalnost() {
		JFuzzyChart.get().chart(fis.getVariable("lojalnost"), fis.getVariable("lojalnost").getDefuzzifier(), true);
	}

	public void GenerisiTreningPodatke() {

		// TestiranjePodataka();
		UzimanjePodataka();

	}

	public void appendStrToFile(String str) {
		try {
			String fileName = "proba1.txt";
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
			out.write(str + "\n");
			out.close();
		} catch (IOException e) {
			System.out.println("exception occoured" + e);
		}
	}

	public String vratiSkup(double pVrednost) {
		if (pVrednost >= 0 && pVrednost < 0.4) {
			return "nelojalan";
		}
		if (pVrednost >= 0.4 && pVrednost < 0.85) {
			return "bronzana";
		}
		if (pVrednost >= 0.85 && pVrednost < 1) {
			return "srebrna";
		}
		if (pVrednost >= 1 && pVrednost < 1.4) {
			return "zlatna";
		}
		if (pVrednost >= 1.4 && pVrednost < 2) {
			return "dijamantska";
		}
		return "NA";
	}

	public void UzimanjePodataka() {
		// pravljenje konekcije
		String konekcioniString = "jdbc:mysql://localhost/kupci?user=root";

		// deklarisanje JDBC objekata
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(konekcioniString);

			String SQL = "SELECT * FROM kupci.kupcinovi";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				
					SetovanjeVrednosti(rs.getDouble(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7),
							rs.getInt(8), rs.getInt(9));

					appendStrToFile(vratiSkup(fis.getVariable("lojalnost").getValue()) + "," + rs.getString(10));
					
				}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
