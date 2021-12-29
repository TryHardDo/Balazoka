package dev.tf2levi.balazo;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Harvester {
    private Player owner;
    private int level;
    private int inventorySize;
    private double fuelTankSize;
    private int speedMultiplier;
    private Location currentPosition;
    private int harvestBlockWidth;

    public Harvester() {}

    public Player getOwner() {
        return owner;
    }

    public int getLevel() {
        return level;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public double getFuelTankSize() {
        return fuelTankSize;
    }

    public int getSpeedMultiplier() {
        return speedMultiplier;
    }
}
