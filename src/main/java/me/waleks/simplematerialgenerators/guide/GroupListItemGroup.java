package me.waleks.simplematerialgenerators.guide;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

/**
 * A guide menu that lists other item groups, for example the addon's own menu or "Compressed Items".
 * It can also show one group at a fixed slot.
 */
public class GroupListItemGroup extends FlexItemGroup {

    private final boolean listedInMainMenu;
    private final List<ItemGroup> groups = new ArrayList<>();
    private int fixedSlot = -1;
    private ItemGroup fixedGroup;

    /**
     * @param listedInMainMenu whether this menu is shown in the main menu of the guide,
     *                         otherwise it is only opened from another menu
     */
    @ParametersAreNonnullByDefault
    public GroupListItemGroup(NamespacedKey key, ItemStack item, boolean listedInMainMenu) {
        super(key, item);
        this.listedInMainMenu = listedInMainMenu;
    }

    public void addGroup(@Nonnull ItemGroup group) {
        groups.add(group);
    }

    /**
     * Shows the given group at a fixed slot, instead of in the list.
     */
    public void setFixedGroup(int slot, @Nullable ItemGroup group) {
        this.fixedSlot = slot;
        this.fixedGroup = group;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        return listedInMainMenu && (fixedGroup != null || groups.stream().anyMatch(GroupListItemGroup::hasItems));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        if (mode == SlimefunGuideMode.SURVIVAL_MODE) {
            profile.getGuideHistory().add(this, 1);
        }

        ChestMenu menu = GuideMenus.create(p, profile, mode);

        int slot = GuideMenus.FIRST_SLOT;
        for (ItemGroup group : groups) {
            if (slot == fixedSlot && fixedGroup != null) {
                slot++;
            }
            if (!hasItems(group) || slot > GuideMenus.LAST_SLOT) {
                continue;
            }
            addGroup(menu, slot, p, profile, mode, group);
            slot++;
        }

        if (fixedGroup != null) {
            addGroup(menu, fixedSlot, p, profile, mode, fixedGroup);
        }

        menu.open(p);
    }

    @ParametersAreNonnullByDefault
    private static void addGroup(ChestMenu menu, int slot, Player p, PlayerProfile profile, SlimefunGuideMode mode, ItemGroup group) {
        menu.addItem(slot, group.getItem(p));
        menu.addMenuClickHandler(slot, (pl, s, item, action) -> {
            SlimefunGuide.openItemGroup(profile, group, mode, 1);
            return false;
        });
    }

    private static boolean hasItems(@Nonnull ItemGroup group) {
        return group instanceof FlexItemGroup || !group.getItems().isEmpty();
    }
}
