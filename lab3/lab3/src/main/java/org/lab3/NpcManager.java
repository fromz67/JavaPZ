package org.lab3;

import java.util.Arrays;
import java.util.Comparator;

public class NpcManager {

    public static void main(String[] args) {
        // Створення масиву
        Npc[] npcs = {
                new Npc("Гоблін", 50, 5, "Гобліни", true),
                new Npc("Стражник", 150, 10, "Королівська варта", false),
                new Npc("Торговець", 100, 5, "Селюки", false),
                new Npc("Орк", 120, 10, "Орки", true),
                new Npc("Ельф-мечник", 90, 10, "Ельфи", false)
        };

        System.out.println("--- Початковий масив NPC ---");
        printNpcArray(npcs);

        Arrays.sort(npcs, Comparator.comparingInt(Npc::getLevel)
                .thenComparing(Npc::getHealth, Comparator.reverseOrder()));

        System.out.println("\n--- Відсортований масив (за рівнем , за здоров'ям ) ---");
        printNpcArray(npcs);

        Npc targetNpc = new Npc("Стражник", 150, 10, "Королівська варта", false);
        System.out.println("\n--- Пошук об'єкта ---");
        System.out.println("Шукаємо: " + targetNpc);

        int foundIndex = -1;

        for (int i = 0; i < npcs.length; i++) {
            if (npcs[i].equals(targetNpc)) {
                foundIndex = i;
                break;
            }
        }

        // Вивід
        if (foundIndex != -1) {
            System.out.println("Результат: Об'єкт знайдено в масиві на позиції (індексі): " + foundIndex);
        } else {
            System.out.println("Результат: Заданий об'єкт в масиві не знайдено.");
        }
    }
    private static void printNpcArray(Npc[] npcArray) {
        for (Npc npc : npcArray) {
            System.out.println(npc);
        }
    }
}
