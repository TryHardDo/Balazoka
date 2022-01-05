package dev.tf2levi.harvestmachine;

import dev.tf2levi.harvestmachine.workers.HarvestWorker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class Harvester
{
    // A kombájn ID-je
    private final UUID id;
    // Az a player akié a kombájn
    private Player owner = null;
    // A kombájn szintje
    private int level = 0;
    // A kombájn tárolókapacitása
    private double fuelTankSize = 2;
    // A kombájn aktuális üzemanyagszintje
    private double currentFuelLevel = 0;
    // A kombájn gyorsasági szorzója
    private int speedMultiplier = 1;
    // Az összes beszedett termés
    private long totalHarvests = 0;
    // A kombájn aktuális helyzete
    private Location currentPosition = null;
    // A kombájn vágóasztal mérete
    private int harvestBlockWidth = 1;
    // A Bukkit task ami alatt fut a kombájn
    private BukkitTask task = null;
    // A kombájn eszköztára
    private Inventory inventory = null;
    // A kombájn fut e jelenleg vagy sem
    private boolean currentlyRunning = false;

    public Harvester(final Player owner, final Location placedLocation)
    {
        this.id = UUID.randomUUID();

        setOwner(owner);
        setCurrentPosition(placedLocation);

        setInventory(Bukkit.createInventory(null, 9));
    }

    public Harvester(final Player owner, final Location placedLocation, final UUID id)
    {
        this.id = id;

        setOwner(owner);
        setCurrentPosition(placedLocation);

        setInventory(Bukkit.createInventory(null, 9));
    }

    public Harvester(final Player owner, final Location placedLocation, final UUID id, final Inventory savedInv)
    {
        this.id = id;

        setOwner(owner);
        setCurrentPosition(placedLocation);

        setInventory(savedInv);
    }

    public void startHarvest()
    {
        setTask(new HarvestWorker(this).startProcess());

        setCurrentlyRunning(true);
    }

    public void stopHarvest()
    {
        getTask().cancel();

        setCurrentlyRunning(false);
    }

    public UUID getId()
    {
        return id;
    }

    public Player getOwner()
    {
        return owner;
    }

    public void setOwner(Player owner)
    {
        this.owner = owner;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public double getFuelTankSize()
    {
        return fuelTankSize;
    }

    public void setFuelTankSize(double fuelTankSize)
    {
        this.fuelTankSize = fuelTankSize;
    }

    public double getCurrentFuelLevel()
    {
        return currentFuelLevel;
    }

    public void setCurrentFuelLevel(double currentFuelLevel)
    {
        this.currentFuelLevel = currentFuelLevel;
    }

    public int getSpeedMultiplier()
    {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(int speedMultiplier)
    {
        this.speedMultiplier = speedMultiplier;
    }

    public long getTotalHarvests()
    {
        return totalHarvests;
    }

    public void setTotalHarvests(long totalHarvests)
    {
        this.totalHarvests = totalHarvests;
    }

    public Location getCurrentPosition()
    {
        return currentPosition;
    }

    public void setCurrentPosition(Location currentPosition)
    {
        this.currentPosition = currentPosition;
    }

    public int getHarvestBlockWidth()
    {
        return harvestBlockWidth;
    }

    public void setHarvestBlockWidth(int harvestBlockWidth)
    {
        this.harvestBlockWidth = harvestBlockWidth;
    }

    public BukkitTask getTask()
    {
        return task;
    }

    public void setTask(BukkitTask task)
    {
        this.task = task;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }

    public boolean isCurrentlyRunning()
    {
        return currentlyRunning;
    }

    public void setCurrentlyRunning(boolean currentlyRunning)
    {
        this.currentlyRunning = currentlyRunning;
    }
}
