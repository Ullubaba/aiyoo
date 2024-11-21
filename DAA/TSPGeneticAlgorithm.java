import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TSPGeneticAlgorithm {

    static final int[][] DISTANCE_MATRIX = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };

    static final int POPULATION_SIZE = 10;
    static final int NUM_GENERATIONS = 100;
    static final double MUTATION_RATE = 0.1;

    public static void main(String[] args) {
        List<List<Integer>> population = initializePopulation();
       
        for (int generation = 0; generation < NUM_GENERATIONS; generation++) {
            population = evolvePopulation(population);
        }
       
        List<Integer> bestSolution = findBestSolution(population);
        int bestCost = calculateRouteCost(bestSolution);

        System.out.println("Best Solution: " + bestSolution);
        System.out.println("Best Cost: " + bestCost);
    }

    // Initialize population with random routes
    static List<List<Integer>> initializePopulation() {
        List<List<Integer>> population = new ArrayList<>();
        List<Integer> cities = new ArrayList<>();
        for (int i = 0; i < DISTANCE_MATRIX.length; i++) {
            cities.add(i);
        }

        for (int i = 0; i < POPULATION_SIZE; i++) {
            List<Integer> route = new ArrayList<>(cities);
            Collections.shuffle(route);
            population.add(route);
        }
        return population;
    }

    // Evolve population using selection, crossover, and mutation
    static List<List<Integer>> evolvePopulation(List<List<Integer>> population) {
        List<List<Integer>> newPopulation = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            List<Integer> parent1 = selectParent(population);
            List<Integer> parent2 = selectParent(population);

            List<Integer> offspring = crossover(parent1, parent2);
            mutate(offspring);

            newPopulation.add(offspring);
        }

        return newPopulation;
    }

    // Select a parent using tournament selection
    static List<Integer> selectParent(List<List<Integer>> population) {
        Random random = new Random();
        List<Integer> parent = population.get(random.nextInt(POPULATION_SIZE));
        return parent;
    }

    // Perform crossover between two parents
    static List<Integer> crossover(List<Integer> parent1, List<Integer> parent2) {
        Random random = new Random();
        int crossoverPoint = random.nextInt(parent1.size());
       
        List<Integer> offspring = new ArrayList<>(parent1.subList(0, crossoverPoint));
        for (Integer city : parent2) {
            if (!offspring.contains(city)) {
                offspring.add(city);
            }
        }
        return offspring;
    }

    // Perform mutation by swapping two cities
    static void mutate(List<Integer> route) {
        Random random = new Random();
        if (random.nextDouble() < MUTATION_RATE) {
            int i = random.nextInt(route.size());
            int j = random.nextInt(route.size());
            Collections.swap(route, i, j);
        }
    }

    // Find the best solution in the population
    static List<Integer> findBestSolution(List<List<Integer>> population) {
        List<Integer> bestSolution = population.get(0);
        int bestCost = calculateRouteCost(bestSolution);

        for (List<Integer> route : population) {
            int currentCost = calculateRouteCost(route);
            if (currentCost < bestCost) {
                bestCost = currentCost;
                bestSolution = route;
            }
        }

        return bestSolution;
    }

    // Calculate the cost of a given route
    static int calculateRouteCost(List<Integer> route) {
        int cost = 0;

        for (int i = 0; i < route.size() - 1; i++) {
            cost += DISTANCE_MATRIX[route.get(i)][route.get(i + 1)];
        }

        cost += DISTANCE_MATRIX[route.get(route.size() - 1)][route.get(0)]; // Return to start
        return cost;
    }
}