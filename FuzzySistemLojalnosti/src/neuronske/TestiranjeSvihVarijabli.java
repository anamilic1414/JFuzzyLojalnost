package neuronske;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.error.MeanAbsoluteError;
import org.neuroph.core.learning.error.MeanSquaredError;
import org.neuroph.eval.ClassifierEvaluator;
import org.neuroph.eval.ErrorEvaluator;
import org.neuroph.eval.Evaluation;
import org.neuroph.eval.classification.ClassificationMetrics;
import org.neuroph.eval.classification.ConfusionMatrix;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;
import org.neuroph.util.data.norm.MaxNormalizer;
import org.neuroph.util.data.norm.Normalizer;

public class TestiranjeSvihVarijabli implements LearningEventListener {

	String dataSetFile = "trening.txt";
	int inputsCount = 7;
	int outputsCount = 1;
	int maxHidden = 15;
	int minHidden = 10;
	double minLearningRate = 0.1;
	double maxLearningRate = 0.9;
	List<Trening> treninzi = new ArrayList<Trening>();
	int brojTreninga = 0;
	Trening minError, minIteration;

	public static void main(String[] args) {
		(new TestiranjeSvihVarijabli()).run();
	}

	public void run() {
		System.out.println("Creating data set...");

		// create training set from file
		DataSet trainingSet = DataSet.createFromFile(dataSetFile, inputsCount, outputsCount, ",");

		for (int hidden = minHidden; hidden < maxHidden; hidden++) {
			double learningRate = minLearningRate;
			while (learningRate <= maxLearningRate) {
				MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(inputsCount, hidden, outputsCount);
				BackPropagation bp = neuralNet.getLearningRule();
				bp.setLearningRate(learningRate);
				bp.setMaxIterations(100);
				bp.setMaxError(0.01);
				bp.addListener(this);
				brojTreninga++;
				System.out.println("----------------");
				System.out.println("Broj " + brojTreninga);
				System.out.println("Hidden neurons " + hidden + ", learning rate " + learningRate);
				neuralNet.learn(trainingSet);
				double totalError = bp.getTotalNetworkError();
				int iterations = bp.getCurrentIteration();
				Trening t =new Trening(neuralNet, trainingSet, hidden, learningRate, totalError, iterations);
				treninzi.add(t);
				learningRate+=0.1;
			}
		}
		evaluate();
		System.out.println("Testiranje neuronske mreze sa min greskom: ");
		testNeuralNetwork(minError.getNeuralNetwork(), minError.getDataSet()); 
		System.out.println("Testiranje neuronske mreze sa min brojem iteracija: ");
		testNeuralNetwork(minIteration.getNeuralNetwork(), minIteration.getDataSet());
	}

	public void testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet) {

		System.out.println("Showing inputs, desired output and neural network output for every row in test set.");

		for (DataSetRow testSetRow : testSet.getRows()) {
			neuralNet.setInput(testSetRow.getInput());
			neuralNet.calculate();
			double[] networkOutput = neuralNet.getOutput();

			System.out.println("Input: " + Arrays.toString(testSetRow.getInput()));
			System.out.println("Output: " + Arrays.toString(networkOutput));
			//System.out.println("Desired output" + Arrays.toString(testSetRow.getDesiredOutput()));
		}
	}

	public void evaluate(/*NeuralNetwork neuralNet, DataSet dataSet*/) {

		double sumError = 0, sumIteracije=0;
		minError = treninzi.get(0);
		minIteration = treninzi.get(0);
		
		for(Trening trening : treninzi) {
			if(trening.getError() < minError.getError()) {
				minError = trening;
			}
			if(trening.getIteration() < minIteration.getIteration()) {
				minIteration = trening;
			}
			sumError+= trening.getError();
			sumIteracije += trening.getIteration();
		}
		System.out.println("Srednja vrednost greske: "+(sumError/treninzi.size()));
		System.out.println("Srednji broj iteracija: "+(sumIteracije/treninzi.size()));

	}

	@Override
	public void handleLearningEvent(LearningEvent event) {
		MomentumBackpropagation bp = (MomentumBackpropagation) event.getSource();
		System.out
				.println(bp.getCurrentIteration() + ". iteration | Total network error: " + bp.getTotalNetworkError());

	}

}
