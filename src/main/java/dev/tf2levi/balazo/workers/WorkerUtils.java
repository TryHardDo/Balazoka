package dev.tf2levi.balazo.workers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

// Nem PUBLIC mert csak ezen a packagen belül lesz használva. Később ez persze változhat...
class WorkerUtils {
    static @Nullable List<Block> performScan(final Location workingLocation) {
        List<Block> harvestList = new ArrayList<>();

        for (BlockFace face : BlockFace.values()) {
            Block currentBlock = workingLocation.getBlock().getRelative(face);

            if (!(currentBlock.getBlockData() instanceof Ageable)) {
                continue;
            }

            Ageable ageable = ((Ageable) currentBlock.getBlockData());

            if (ageable.getAge() != ageable.getMaximumAge()) {
                continue;
            }

            harvestList.add(currentBlock);
        }

        if (harvestList.isEmpty()) {
            return null;
        }

        return harvestList;
    }

    static @Nullable HashMap<Material, Integer> performHarvest(final @NotNull List<Block> scannedList) {
        if (scannedList.isEmpty()) {
            return null;
        }

        HashMap<Material, Integer> harvestCache = new LinkedHashMap<>();

        for (Block block : scannedList) {
            Material currentMat = block.getType();

            if (harvestCache.size() == 0 || !harvestCache.containsKey(currentMat)) {
                harvestCache.put(currentMat, 1);

                removeHarvestedItem(block);
                continue;
            }

            harvestCache.replace(currentMat, harvestCache.get(currentMat) + 1);

            removeHarvestedItem(block);

        }

        return harvestCache;
    }

    static void removeHarvestedItem(final Block b) {
        if (b == null) {
            return;
        }

        b.setType(Material.AIR, true);
    }
}
