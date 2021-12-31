package dev.tf2levi.balazo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Utils {
    public static void logStartupMessages(@NotNull Plugin p) {
        String version = p.getDescription().getVersion();
        String apiVersion = Bukkit.getVersion();

        Bukkit.getConsoleSender().sendMessage(
                "§8*",
                "§8| §eBálázóka - §6" + version,
                "§8| §fDeveloped by: §7TryHardDO",
                "§8| §eA plugin készen áll a használatra!",
                "§8| §eAPI verzió: §6" + apiVersion,
                "§8*"
        );
    }

    public static double getFuelPercent(final double fuelTankSize, final double currentTankLevel) {
        return (currentTankLevel / fuelTankSize) * 100;
    }
}
