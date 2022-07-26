/**
 * This is the main method for Assignment 1, Evolved Names.
 */

/**
 * Simulates the evolution of genes until it evolves to the target name.
 * 
 * @author leejos5
 * @version TCSS 342C Spr2021
 */
public class Main {

	public static void main(String[] args) {
		String target = "CHRISTOPHER_PAUL_MARRIOTT";
		
		MyLinkedList<Character> targetGenome = new MyLinkedList<Character>();
		for (int i = 0; i < target.length(); i++) {
			targetGenome.add(target.charAt(i));
		}
		Population newPopulation = new Population(100, 0.05, targetGenome);
		int nextGenCalls = 0;
		double startTime = System.currentTimeMillis();
		while (newPopulation.mostFit.fitness() != 0) {
			newPopulation.nextGeneration();
			nextGenCalls++;
			System.out.println("Most Fit: " + newPopulation.mostFit + ", Fitness: " + newPopulation.mostFit.fitness() +
					", Current Generation: " + nextGenCalls);
		}
		System.out.println("Generation Calls: " + nextGenCalls + ", Execution Time: " + (System.currentTimeMillis() - startTime) + " ms.");
	}
}
