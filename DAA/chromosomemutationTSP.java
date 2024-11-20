import java.util.*;

public class chromosomemutationTSP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the matrix (m):");
        int m = scanner.nextInt();

        int[][] matrix = new int[m][m];
        System.out.println("Enter the matrix values (use -1 for infinity):");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Input Matrix:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == -1) {
                    System.out.print("INF ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }

        List<Integer> chromosome = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            chromosome.add(i);
        }

        Collections.shuffle(chromosome);

        System.out.println("Initial Chromosome (Random Tour): " + chromosome);
        chromosome = mutate(chromosome);
        System.out.println("Mutated Chromosome (After Mutation): " + chromosome);
    }

    public static List<Integer> mutate(List<Integer> chromosome) {
        Random random = new Random();
        int size = chromosome.size();
        int index1 = random.nextInt(size);
        int index2 = random.nextInt(size);

        while (index1 == index2) {
            index2 = random.nextInt(size);
        }

        Collections.swap(chromosome, index1, index2);
        return chromosome;
    }
}
