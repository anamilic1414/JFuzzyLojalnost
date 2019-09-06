package neuronske;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;

public class Trening {
	
	public Trening(NeuralNetwork neuralNetwork, DataSet dataSet, int hiddenNeurons, double learningRate, double error,
			int iteration) {
		super();
		this.neuralNetwork = neuralNetwork;
		this.dataSet = dataSet;
		this.hiddenNeurons = hiddenNeurons;
		this.learningRate = learningRate;
		this.error = error;
		this.iteration = iteration;
	}
	private NeuralNetwork neuralNetwork;
	private DataSet dataSet;
	private int hiddenNeurons;
	private double learningRate;
	private double error;
	private int iteration;
	
	public NeuralNetwork getNeuralNetwork() {
		return neuralNetwork;
	}
	public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
		this.neuralNetwork = neuralNetwork;
	}
	public DataSet getDataSet() {
		return dataSet;
	}
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}
	public int getHiddenNeurons() {
		return hiddenNeurons;
	}
	public void setHiddenNeurons(int hiddenNeurons) {
		this.hiddenNeurons = hiddenNeurons;
	}
	public double getLearningRate() {
		return learningRate;
	}
	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}
	public double getError() {
		return error;
	}
	public void setError(double error) {
		this.error = error;
	}
	public int getIteration() {
		return iteration;
	}
	public void setIteration(int iteration) {
		this.iteration = iteration;
	}
	
}
