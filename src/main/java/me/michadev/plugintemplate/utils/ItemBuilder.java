package me.michadev.plugintemplate.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.michadev.plugintemplate.utils.Common.colorize;

public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(String displayName, Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = this.itemStack.getItemMeta();
        this.itemMeta.setDisplayName(colorize(displayName));
        this.itemStack.setItemMeta(this.itemMeta);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayName(String displayName) {
        itemMeta.setDisplayName(colorize(displayName));
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        if (lore == null) return this;
        List<String> finalLore = new ArrayList<>();
        Arrays.asList(lore).forEach(l-> finalLore.add(colorize(l)));
        itemMeta.setLore(finalLore);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        if (lore == null) return this;
        List<String> finalLore = new ArrayList<>();
        lore.forEach(l-> finalLore.add(colorize(l)));
        itemMeta.setLore(finalLore);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        itemMeta.removeEnchant(enchantment);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
