package dev.tf2levi.balazo;

import dev.tf2levi.balazo.commands.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Logger;

public final class Balazoka extends JavaPlugin {
    private static Balazoka instance;
    private static Logger pluginLogger;

    private static final HashMap<UUID, Harvester> harvestersCache = new LinkedHashMap<>();

    @Override
    public void onEnable() {
        getPluginLogger().info("Plugin indítása folyamatban...");
        instance = this;
        pluginLogger = this.getLogger();

        Bukkit.getPluginManager().registerEvents(new ListenerClass(), this);

        // CUSTOM SETUP START
        Objects.requireNonNull(this.getCommand("balazo")).setExecutor(new BaseCommand());
        // CUSTOM SETUP END

        Utils.logStartupMessages(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        harvestersCache.clear();

        getPluginLogger().info("A plugin leállt.");
    }

    public static Balazoka getInstance() {
        return instance;
    }

    public static Logger getPluginLogger() {
        return pluginLogger;
    }

    public static HashMap<UUID, Harvester> getHarvesters() {
        return harvestersCache;
    }
}
