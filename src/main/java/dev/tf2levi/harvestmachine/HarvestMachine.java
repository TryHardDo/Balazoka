package dev.tf2levi.harvestmachine;

import dev.tf2levi.harvestmachine.commands.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

public final class HarvestMachine extends JavaPlugin
{
    private static final HashMap<UUID, Harvester> harvestersCache = new LinkedHashMap<>();

    private static final boolean DEBUG_MODE = true;

    private static HarvestMachine instance;
    private static Logger pluginLogger;

    public static HarvestMachine getInstance()
    {
        return instance;
    }

    public static Logger getPluginLogger()
    {
        return pluginLogger;
    }

    public static HashMap<UUID, Harvester> getHarvesters()
    {
        return harvestersCache;
    }

    public static boolean isDebugModeEnabled()
    {
        return DEBUG_MODE;
    }

    @Override
    public void onEnable()
    {
        instance = this;
        pluginLogger = this.getLogger();

        getPluginLogger().info("Plugin indítása folyamatban...");


        Bukkit.getPluginManager().registerEvents(new ListenerClass(), this);

        Objects.requireNonNull(this.getCommand("harvestmachine")).setExecutor(new BaseCommand());

        Utils.logStartupMessages(this);
    }

    @Override
    public void onDisable()
    {
        Bukkit.getScheduler().cancelTasks(this);
        harvestersCache.clear();

        getPluginLogger().info("A plugin leállt.");
    }
}
