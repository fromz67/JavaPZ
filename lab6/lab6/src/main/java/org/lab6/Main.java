package org.lab6;

import java.util.ArrayList;
import java.util.List;

/**
 * Main.
 */
public class Main {
    public static void main(String[] args) {

        RollingStockLinkedList<SimpleRollingStock> list = new RollingStockLinkedList<>();
        System.out.println("Initial empty list: " + list);

        SimpleRollingStock single = new SimpleRollingStock("RS-01", 100);
        RollingStockLinkedList<SimpleRollingStock> singleList = new RollingStockLinkedList<>(single);
        System.out.println("Single-element list: " + singleList);

        List<SimpleRollingStock> initialCollection = new ArrayList<>();
        initialCollection.add(new SimpleRollingStock("RS-02", 80));
        initialCollection.add(new SimpleRollingStock("RS-03", 120));
        initialCollection.add(new SimpleRollingStock("RS-04", 60));

        RollingStockLinkedList<SimpleRollingStock> fromCollection =
                new RollingStockLinkedList<>(initialCollection);
        System.out.println("List created from collection: " + fromCollection);


        // add(E)
        fromCollection.add(new SimpleRollingStock("RS-05", 90));
        System.out.println("After add(RS-05): " + fromCollection);

        // add(int, E)
        fromCollection.add(2, new SimpleRollingStock("RS-06", 110));
        System.out.println("After add at index 2 (RS-06): " + fromCollection);

        // get(int)
        SimpleRollingStock atIndexTwo = fromCollection.get(2);
        System.out.println("Element at index 2: " + atIndexTwo);

        // set(int, E)
        SimpleRollingStock old = fromCollection.set(1, new SimpleRollingStock("RS-07", 70));
        System.out.println("Replaced " + old + " with RS-07 at index 1: " + fromCollection);

        // remove(Object)
        boolean removed = fromCollection.remove(atIndexTwo);
        System.out.println("Removed previously stored object (" + atIndexTwo + "): " + removed);
        System.out.println("List after remove(object): " + fromCollection);

        // remove(int)
        SimpleRollingStock removedByIndex = fromCollection.remove(0);
        System.out.println("Removed by index 0: " + removedByIndex);
        System.out.println("List after remove(0): " + fromCollection);

        // contains / indexOf / lastIndexOf
        SimpleRollingStock rs08 = new SimpleRollingStock("RS-08", 50);
        fromCollection.add(rs08);
        fromCollection.add(rs08); // intentional duplicate
        System.out.println("After adding RS-08 twice: " + fromCollection);
        System.out.println("Contains RS-08? " + fromCollection.contains(rs08));
        System.out.println("First index of RS-08: " + fromCollection.indexOf(rs08));
        System.out.println("Last index of RS-08: " + fromCollection.lastIndexOf(rs08));

        // subList
        List<SimpleRollingStock> subList = fromCollection.subList(1, fromCollection.size());
        System.out.println("SubList from 1 to size: " + subList);

        // clear
        singleList.clear();
        System.out.println("Single-element list after clear(): " + singleList
                + ", isEmpty=" + singleList.isEmpty());
    }
}
