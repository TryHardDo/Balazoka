package dev.tf2levi.balazo.gui;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GUIItem {
    private final ItemStack baseItem;
    private final int itemHashCode;

    public GUIItem(final @NotNull ItemStack baseItem) {
        this.baseItem = baseItem;
        this.itemHashCode = baseItem.hashCode();
    }

    public int getItemHashCode() {
        return itemHashCode;
    }

    public ItemStack getBaseItem() {
        return baseItem;
    }

    public void executeAction() {

    }
}
