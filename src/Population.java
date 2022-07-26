/**
 * This is the Population class for TCSS 342C Assignment 1, Evolved Names.
 */
import java.util.Random;

/**
 * Represents a population object, carrying and sorting a list of Genomes
 * 
 * @author leejos5
 * @version TCSS 342C Spr2021
 */
public class Population {

	/**
	 * The list of Genomes of the population.
	 */
	public MyLinkedList<Genome> population;
	
	/**
	 * The Genome with the highest fitness.
	 */
	public Genome mostFit;
	
	/**
	 * The size of the population.
	 */
	private int size;
	
	/**
	 * The rate of mutation of the population.
	 */
	private double mutationRate;
	
	/**
	 * Constructor for the population object.
	 * @param theSize the size of the population.
	 * @param theMutationRate the mutation rate of the population.
	 * @param theTarget the target name of the population.
	 */
	public Population(int theSize, double theMutationRate, MyLinkedList<Character> theTarget) {
		size = theSize;
		mutationRate = theMutationRate;
		population = new MyLinkedList<Genome>();
		Genome newGenome = new Genome(theTarget);
		mostFit = newGenome;
		population.add(newGenome);
		for (int i = 0; i < theSize - 1; i++) {
			Genome nextGenome = new Genome(theTarget);
			population.add(nextGenome);
		}
	}
	
	/**
	 * Evolves the population into the next generation by mutating random Genomes.
	 */
	public void nextGeneration() {
		Random rand = new Random();
		for (int i = 0; i < size / 2; i++) {
			population.remove(0);
		}
		for (int i = 0; i < size / 2; i++) {
			Genome genomeToClone = population.get(rand.nextInt(size / 2));
			Genome clonedGenome = new Genome(genomeToClone);
			if (rand.nextInt(2) < 1) {
				clonedGenome.mutate(mutationRate);
			} else {
				Genome otherGenome = population.get(rand.nextInt(size / 2));
				clonedGenome.crossover(otherGenome);
				clonedGenome.mutate(mutationRate);
			}
			population.add(clonedGenome);
		}
		population.sort();
		mostFit = population.get(size - 1);
	}
	
	/**
	 * Creates a string representation of the population.
	 * @String the population.
	 */
	public String toString() {
		String result = population.get(0) + "";
		for (int i = 1; i < population.size(); i++) {
			result += ", " + population.get(i);
		}
		return result + "";
	}
}
