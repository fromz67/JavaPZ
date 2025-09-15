package lab.main;

import java.util.Random;
import java.util.Scanner;

public class Main {

    /**
     * Начало. Викликає executeTask() і ловить помилки.
     */
    public static void main(String[] args) {
        try {
            executeTask();
        } catch (Exception e) {
            System.err.println("Сталася непередбачена помилка: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public static void executeTask() {

        Scanner scanner = new Scanner(System.in);
        try {

            int m;
            int n;


            float a;


            while (true) {
                System.out.print("Введіть m n (два додатніх цілi числа, наприклад: 3 4): ");
                String line = scanner.nextLine();
                if (line == null) {
                    throw new IllegalStateException("Ввід недоступний.");
                }
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    System.out.println("Порожній ввід — спробуйте ще раз.");
                    continue;
                }
                String[] parts = trimmed.split("\\s+");
                if (parts.length < 2) {
                    System.out.println("Потрібно вказати два числа, наприклад: 3 4");
                    continue;
                }
                try {
                    m = Integer.parseInt(parts[0]);
                    n = Integer.parseInt(parts[1]);
                    if (m <= 0 || n <= 0) {
                        System.out.println("m та n мають бути додатні. Спробуйте ще раз.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Невірний формат цілих чисел. Спробуйте ще раз.");
                }
            }

            while (true) {
                System.out.print("Введіть a (float, наприклад 2, 2.5 або 2,5): ");
                String line = scanner.nextLine();
                if (line == null) {
                    throw new IllegalStateException("Ввід недоступний.");
                }
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    System.out.println("Порожній ввід — спробуйте ще раз.");
                    continue;
                }
                trimmed = trimmed.replace(',', '.');
                try {
                    a = Float.parseFloat(trimmed);
                    break;
                } catch (NumberFormatException ex) {
                    System.out.println("Невірний формат. Спробуйте ще раз.");
                }
            }

            float[][] B = null;
            float[][] C = null;
            float[] columnAverages = null;

            try {
                float range = Math.abs(a);
                B = new float[m][n];
                Random random = new Random();
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {

                        B[i][j] = (random.nextFloat() * 2f - 1f) * range;
                    }
                }
            } catch (NegativeArraySizeException ex) {
                // Перевірка захисту, але оскільки ми валідуємо m та n раніше — це вряд ли станеться
                System.err.println("Невірні розміри матриці: m та n повинні бути додатніми. " + ex.getMessage());
                return;
            } catch (OutOfMemoryError oom) {
                System.err.println("Помилка пам'яті при виділенні матриці: " + oom.getMessage());
                return;
            }

            // C = a * B
            C = new float[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] = a * B[i][j];
                }
            }

            // Обчислюємо середні по стовпчиках для матриці
            columnAverages = new float[n];
            for (int j = 0; j < n; j++) {
                float sum = 0f;
                for (int i = 0; i < m; i++) {
                    sum += C[i][j];
                }
                columnAverages[j] = sum / m;
            }

            // результати
            System.out.println("\n--- Згенерована матриця B (" + m + " x " + n + ") ---");
            printMatrix(B);

            System.out.println("\n--- Матриця C = a * B ---");
            printMatrix(C);

            System.out.println("\n--- Середні значення по кожному стовпчику матриці C ---");
            for (int j = 0; j < columnAverages.length; j++) {
                System.out.printf("стовпець %d: %10.4f%n", j + 1, columnAverages[j]);
            }

        } catch (IllegalStateException ex) {
            System.err.println("Помилка вводу/виводу: " + ex.getMessage());
        } catch (ArithmeticException ex) {
            System.err.println("Арифметична помилка: " + ex.getMessage());
        } catch (Exception ex) {

            System.err.println("Сталася помилка під час виконання: " + ex.getClass().getSimpleName()
                    + " - " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void printMatrix(float[][] matrix) {
        if (matrix == null) {
            System.out.println("(null)");
            return;
        }
        for (float[] row : matrix) {
            for (float value : row) {
                System.out.printf("%12.6f", value);
            }
            System.out.println();
        }
    }
}

