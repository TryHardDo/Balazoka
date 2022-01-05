package dev.tf2levi.harvestmachine;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public class Utils
{
    public static void logStartupMessages(@NotNull Plugin p)
    {
        String version = p.getDescription().getVersion();
        String apiVersion = Bukkit.getVersion();

        Bukkit.getConsoleSender().sendMessage(
                "§8*",
                "§8| §eHarvestMachine - §6" + version,
                "§8| §fDeveloped by: §7TryHardDO",
                "§8| §eA plugin készen áll a használatra!",
                "§8| §eAPI verzió: §6" + apiVersion,
                "§8*"
        );
    }

    public static double getFuelPercent(final double fuelTankSize, final double currentTankLevel)
    {
        return (currentTankLevel / fuelTankSize) * 100;
    }

    public static void updateCache(final Harvester harvester)
    {
        HarvestMachine.getHarvesters().replace(harvester.getId(), harvester);
    }

    public static Harvester searchByLocation(final Location location)
    {
        if (location == null) return null;

        Harvester harvester = null;

        for (Map.Entry<UUID, Harvester> cachePiece : HarvestMachine.getHarvesters().entrySet())
        {
            if (cachePiece.getValue().getCurrentPosition().equals(location))
            {
                harvester = cachePiece.getValue();
                break;
            }
        }

        return harvester;
    }
}
