package dev.tf2levi.balazo.inventories;

import dev.tf2levi.balazo.Harvester;
import dev.tf2levi.balazo.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlastFurnace;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FuelInterface {
    private final Inventory fuelInterface = Bukkit.createInventory(null, InventoryType.BLAST_FURNACE);
    private final Harvester harvester;

    public FuelInterface(final Harvester harvester) {
        this.harvester = harvester;


    }

    private @Nullable ItemStack getDataItem() {
        ItemStack base = new ItemStack(Material.IRON_HOE, 1);
        ItemMeta baseMeta = base.getItemMeta();

        if (baseMeta == null) return null;

        baseMeta.setDisplayName("§6● §e§lÜZEMANYAG ELLÁTÁS §6●");

        List<String> loreData = new ArrayList<>();

        loreData.add("§aElfogadott üzemanyagtípusok: MAJD IDE");
        loreData.add("§6● §eMaximum üzemanyag: §6§l" + harvester.getFuelTankSize());
        loreData.add("§6● §eJelenlegi üzemanyagszint: §6§l" + harvester.getCurrentFuelLevel() + " §7§o" + Utils.getFuelPercent(harvester.getFuelTankSize(), harvester.getCurrentFuelLevel()) + "%");
        loreData.add("");
        loreData.add("§7§oMinden típusú üzemanyagnak megvan hogy mennyi időt bír ki");
        loreData.add("§7mielőtt elfogy.");

        baseMeta.setLore(loreData);
        baseMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        base.setItemMeta(baseMeta);

        return base;
    }

    public Inventory getFuelInterface() {
        return fuelInterface;
    }
}
