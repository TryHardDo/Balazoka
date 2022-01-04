package dev.tf2levi.balazo.gui;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class GUITools {
    public static ItemStack createGuiItem(final Material material, final int quantity, final String displayName, final List<String> loreData, final List<ItemFlag> itemFlags, final HashMap<Enchantment, Integer> enchantments) {
        ItemStack baseItem = new ItemStack(material, quantity);

        ItemMeta baseItemMeta = baseItem.getItemMeta();

        if (baseItemMeta == null) return null;

        if (displayName != null) baseItemMeta.setDisplayName(displayName);

        if (loreData != null && !loreData.isEmpty()) baseItemMeta.setLore(loreData);

        if (itemFlags != null) {
            ItemFlag[] primitiveItemFlags = new ItemFlag[itemFlags.size()];
            for (int i = 0; i < itemFlags.size(); i++) {
                primitiveItemFlags[i] = itemFlags.get(i);
            }

            baseItemMeta.addItemFlags(primitiveItemFlags);
        }

        if (enchantments != null && !enchantments.isEmpty()) {
            enchantments.forEach((enchantment, level) -> {
                baseItemMeta.addEnchant(enchantment, level, true);
            });
        }

        baseItem.setItemMeta(baseItemMeta);

        return baseItem;
    }
}
