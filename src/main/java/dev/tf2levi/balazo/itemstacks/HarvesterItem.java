package dev.tf2levi.balazo.itemstacks;

import dev.tf2levi.balazo.Harvester;
import dev.tf2levi.balazo.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HarvesterItem {
    private final Harvester harvester;

    public HarvesterItem(final Harvester harvester) {
        this.harvester = harvester;
    }

    public ItemStack getItem() {
        ItemStack baseItem = new ItemStack(Material.BLAST_FURNACE, 1);
        ItemMeta meta = baseItem.getItemMeta();

        if (meta == null) {
            return null;
        }

        meta.setDisplayName("§a§lKombájn §f(§6§l lvl " + harvester.getLevel() + ".§f)");

        List<String> loreData = new ArrayList<>();

        loreData.add("");
        loreData.add("§f§m●===§c§l Műszaki Specifikációk §f§m===●");
        loreData.add("");
        loreData.add("§6● §eTulajdonos: §f" + harvester.getOwner().getName());
        loreData.add("§6● §eÜzemanyag szint: §f(§6§l" + harvester.getCurrentFuelLevel() + "§f / §6§l" + harvester.getFuelTankSize() + "§f) - §o" + Utils.getFuelPercent(harvester.getFuelTankSize(), harvester.getCurrentFuelLevel()) + "%");
        loreData.add("§6● §eMunkatempó bónusz: §6§l" + harvester.getSpeedMultiplier() + "§f x");
        loreData.add("§6● §eVágóasztal mérete: §6§l" + harvester.getHarvestBlockWidth() + " §fblokk");
        loreData.add("");
        loreData.add("§f§m●===§c§l Statisztikai Adatok §f§m===●");
        loreData.add("");
        loreData.add("§6● §eEddig learatott termény: §6§l" + harvester.getTotalHarvests() + " §fdb");
        loreData.add("§6● §eSzint: §6§l" + harvester.getLevel() + ".");
        loreData.add("");
        // Fontos hogy ez a sor meglegyen mert ez alapján azonosítjuk a tárgyat.
        loreData.add("§7" + harvester.getId().toString());

        meta.setLore(loreData);
        // Ez fontos mert ez alapján nézzük hogy ez biztos nem játékos által lett létrehozva.
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        baseItem.setItemMeta(meta);

        return baseItem;
    }
}
