package me.waleks.simplematerialgenerators.guide;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link ItemGroup} that holds items but is not listed in the main menu of the guide.
 * It is opened from one of this addon's menus instead. Its items can still be searched.
 */
public class HiddenItemGroup extends ItemGroup {

    @ParametersAreNonnullByDefault
    public HiddenItemGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @Override
    public boolean isVisible(@Nonnull Player p) {
        return false;
    }
}
