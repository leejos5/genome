import java.util.Random;

/**
 * This is the Genome class for TCSS 342C Assignment 1, Evolved Names.
 */

/**
 * Represents a Genome object.
 * 
 * @author leejos5
 * @version TCSS 342C Spr2021
 */
public class Genome implements Comparable<Genome> {
	
	/**
	 * Array of possible characters.
	 */
	private final Character[] characters = new Character[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 
	                                       'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
	                                       'X', 'Y', 'Z', '_', '-', '\''};
	
	/**
	 * List of genes in the genome.
	 */
	protected MyLinkedList<Character> genes;
	
	/**
	 * List of genes of the target.
	 */
	private MyLinkedList<Character> target;
	
	
	/**
	 * Constructor for the Genome object.
	 * @param theTarget list of genes for the target.
	 */
	public Genome(MyLinkedList<Character> theTarget) {
		this.genes = new MyLinkedList<Character>();
		this.target = theTarget;
	}
	
	/**
	 * Copy constructor for the Genome object.
	 * @param theGenome the Genome to be copied.
	 */
	public Genome(Genome theGenome) {
		this.genes = theGenome.genes.getAll();
		this.target = theGenome.target;
	}
	
	/**
	 * Mutates the Genome by changing characters in the genes at a "mutationRate" chance.
	 * @param mutationRate the chance for the Genome to mutate.
	 */
	public void mutate(double mutationRate) {
		Random rand = new Random();
		if (rand.nextDouble() < mutationRate) {
			int nextChar = rand.nextInt(characters.length);
			genes.add(characters[nextChar], rand.nextInt(genes.size() + 1));
		}
		if (genes.size() > 0 && rand.nextDouble() < mutationRate) {
			genes.remove(rand.nextInt(genes.size()));
		}
		for (int i = 0; i < genes.size(); i++) {
			if (rand.nextDouble() < mutationRate) {
				genes.set(i, characters[rand.nextInt(characters.length)]);
			}
		}
	}
	
	/**
	 * Crosses over the Genome with a random parent to alter the genes.
	 * @param parent the other parent to be considered.
	 */
	public void crossover(Genome parent) {
		int index = 0;
		MyLinkedList<Character> result = new MyLinkedList<Character>();
		Random rand = new Random();
		boolean endOfString = false;
		while (!endOfString) {
			int choice = rand.nextInt(2);
			if (choice == 0 && index < genes.size()) {
				result.add(genes.get(index));
			} else if (index < parent.genes.size()) {
				result.add(parent.genes.get(index));
			} else {
				endOfString = true;
			}
			index++;
		}
		genes = result;
	}
	
	/**
	 * Calculates the "fitness" of the Genome, as in its similarity to the target.
	 * @return the fitness calculated.
	 */
	public int fitness() {
		int length =  Math.abs(genes.size() - target.size());
		int incorrect = genes.compare(target);
		return (-1 * (2 * length + incorrect));
	}
	
	/**
	 * Compares the Genome's fitness to another Genome.
	 * @param other the Genome to be compared to.
	 * @return the difference in fitness.
	 */
	public int compareTo(Genome other) {
		return this.fitness() - other.fitness();
	}
	
	/**
	 * Returns a string representation of the Genome.
	 * @return the String representation.
	 */
	public String toString() {
		return genes.toString();
	}
}
