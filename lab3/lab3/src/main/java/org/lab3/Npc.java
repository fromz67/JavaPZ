package org.lab3;

import java.util.Objects;

public class Npc {

    private final String name;
    private final int health;
    private final int level;
    private final String faction;
    private final boolean isHostile;
    public Npc(String name, int health, int level, String faction, boolean isHostile) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.faction = faction;
        this.isHostile = isHostile;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public String getFaction() {
        return faction;
    }

    public boolean isHostile() {
        return isHostile;
    }

    @Override
    public String toString() {
        return String.format(
                "Npc{ім'я='%s', здоров'я=%d, рівень=%d, фракція='%s', ворожий=%b}",
                name, health, level, faction, isHostile
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Npc npc = (Npc) o;
        return health == npc.health &&
                level == npc.level &&
                isHostile == npc.isHostile &&
                Objects.equals(name, npc.name) &&
                Objects.equals(faction, npc.faction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health, level, faction, isHostile);
    }
}