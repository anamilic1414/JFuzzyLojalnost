import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.Gpr;
import net.sourceforge.jFuzzyLogic.optimization.ErrorFunction;
import net.sourceforge.jFuzzyLogic.rule.RuleBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class ErrorFunctionLojalnost  extends ErrorFunction {

	// Debug mode?
	public boolean verbose = true;
	double potrosnja[], ucestalost[], redovnostPlacanja[], reklamacije[], komunikacija[],preporuke[],vremenskiKupac[], lojalnost[];

	public ErrorFunctionLojalnost(String trainingFile) {
		load(trainingFile);
	}

	@Override
	public double evaluate(RuleBlock ruleBlock) {
		double error = 0;

		FunctionBlock fb = ruleBlock.getFunctionBlock();
		Variable varPot = fb.getVariable("potrosnja");
		Variable varUces = fb.getVariable("ucestalost");
		Variable varRed = fb.getVariable("redovnostPlacanja");
		Variable varRekl = fb.getVariable("reklamacije");
		Variable varKom = fb.getVariable("komunikacija");
		Variable varPre = fb.getVariable("preporuke");
		Variable varVrem = fb.getVariable("vremenskiKupac");
		Variable varLa = fb.getVariable("lojalnost");

		if (verbose) System.out.print("Evaluate: ");
		// For all samples
		for (int sample = 0; sample < lojalnost.length; sample++) {
			// Set variables
			varPot.setValue(potrosnja[sample]);
			varUces.setValue(ucestalost[sample]);
			varRed.setValue(redovnostPlacanja[sample]);
			varRekl.setValue(reklamacije[sample]);
			varVrem.setValue(vremenskiKupac[sample]);
			varPre.setValue(preporuke[sample]);
			varKom.setValue(komunikacija[sample]);

			// Evaluate FIS
			fb.evaluate();

			// Get output
			double errLoj = lojalnost[sample] - varLa.getValue();

			// Accumulate error
			error += (errLoj * errLoj);

			if (verbose) Gpr.showMark(sample + 1, 100);
		}

		error = Math.sqrt(error);
		if (verbose) System.out.println("!\tError: " + error);

		return error;
	}

	/**
	 * Load trainig set from a file
	 * @param trainingFile
	 */
	void load(String trainingFile) {
		Gpr.debug("Loading trainig set from file: " + trainingFile);
		String lines[] = Gpr.readFile(trainingFile).split("\n");

		// Count lines (number of examples)
		int lineCount = 0;
		for (String line : lines) {
			if (!line.startsWith("#")) { // Skip comments
				lineCount++;
			}
		}
		Gpr.debug("Lines: " + lineCount);

		// Create samples
		potrosnja = new double[lineCount];
		ucestalost = new double[lineCount];
		redovnostPlacanja = new double[lineCount];
		reklamacije = new double[lineCount];
		komunikacija = new double[lineCount];
		preporuke = new double[lineCount];
		vremenskiKupac = new double[lineCount];
		lojalnost = new double[lineCount];

		// Read examples
		lineCount = 0;
		for (String line : lines) {
			if (!line.startsWith("#")) { // Skip comments
				String recs[] = line.split(",");

				int recNum = 0;
				potrosnja[lineCount] = Gpr.parseDoubleSafe(recs[recNum++]);
				ucestalost[lineCount] = Gpr.parseIntSafe(recs[recNum++]);
				redovnostPlacanja[lineCount] = Gpr.parseDoubleSafe(recs[recNum++]);
				reklamacije[lineCount] = Gpr.parseDoubleSafe(recs[recNum++]);
				komunikacija[lineCount] = Gpr.parseIntSafe(recs[recNum++]);
				preporuke[lineCount] = Gpr.parseIntSafe(recs[recNum++]);
				vremenskiKupac[lineCount] = Gpr.parseIntSafe(recs[recNum++]);
				lojalnost[lineCount] = Gpr.parseDoubleSafe(recs[recNum++]);

				lineCount++;
			}
		}
	}
}
