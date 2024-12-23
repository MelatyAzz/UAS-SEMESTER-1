//Nama  : Melaty Az Zahrani
// NIM  : 244107060012
// No   : 18

import java.util.InputMismatchException;
import java.util.Scanner;

public class UAS1F18 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Menu Utama ===");

        // Input jumlah tim
        System.out.print("Masukkan jumlah tim: ");
        int teamCount = 0;

        try {
            teamCount = scanner.nextInt();
            if (teamCount <= 0) {
                System.out.println("Jumlah tim harus lebih dari 0.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Masukkan angka bulat positif.");
            return;
        }
        scanner.nextLine(); // Membersihkan buffer

        // Input nama tim
        String[] teams = new String[teamCount];
        for (int i = 0; i < teamCount; i++) {
            System.out.print("Masukkan nama tim ke-" + (i + 1) + ": ");
            teams[i] = scanner.nextLine();
        }

        // Input skor untuk setiap tim dan level
        int[][] scores = new int[teams.length][2];
        for (int i = 0; i < teams.length; i++) {
            for (int level = 1; level <= 2; level++) {
                scores[i][level - 1] = validateScore(scanner, level, teams[i]);
            }
        }

        // Menampilkan tabel skor dan total skor
        System.out.println("\nTabel Skor dan Total Skor");
        printSeparator(40, "=");
        System.out.printf("%-15s%-10s%-10s%-10s%n", "Tim", "Level 1", "Level 2", "Total");
        printSeparator(40, "-");

        // Menghitung total skor
        int[] finalScores = new int[teams.length];
        for (int i = 0; i < teams.length; i++) {
            int totalScore = scores[i][0] + scores[i][1];

            // Penalti jika total skor genap
            if (totalScore % 2 == 0) {
                totalScore -= 15;
            }

            // Bonus Buff Kemenangan
            if (scores[i][0] > 50 && scores[i][1] > 50) {
                totalScore += 18;
            }

            finalScores[i] = totalScore;
            System.out.printf("%-15s%-10d%-10d%-10d%n", teams[i], scores[i][0], scores[i][1], totalScore);
        }

        // Menentukan pemenang
        String winner = null;
        int maxScore = Integer.MIN_VALUE;
        int level2Max = Integer.MIN_VALUE;

        for (int i = 0; i < teams.length; i++) {
            int totalScore = finalScores[i];
            int level2Score = scores[i][1];

            if (totalScore > maxScore || (totalScore == maxScore && level2Score > level2Max)) {
                maxScore = totalScore;
                level2Max = level2Score;
                winner = teams[i];
            }
        }

        System.out.println("\nSelamat kepada Tim " + winner + " yang telah memenangkan kompetisi!");
        scanner.close();
    }

    // Fungsi untuk mencetak separator
    private static void printSeparator(int length, String character) {
        for (int i = 0; i < length; i++) {
            System.out.print(character);
        }
        System.out.println();
    }

    // Fungsi untuk memvalidasi skor
    private static int validateScore(Scanner scanner, int level, String teamName) {
        while (true) {
            try {
                System.out.print("Masukkan skor untuk Tim " + teamName + " di Level " + level + ": ");
                int score = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer
                if (score < 0) {
                    System.out.println("Skor tidak boleh negatif. Masukkan ulang.");
                } else {
                    return (level == 1 && score < 35) ? 0 : score;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Masukkan angka bulat positif.");
                scanner.next(); // Membersihkan input yang salah
            }
        }
    }
}
