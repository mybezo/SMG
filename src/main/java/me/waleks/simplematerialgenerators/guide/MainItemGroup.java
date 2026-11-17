package me.waleks.simplematerialgenerators.guide;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

/**
 * The addon's entry in the guide. It lists the items of a (hidden) {@link ItemGroup}
 * and can show a button at a fixed slot that opens another group.
 */
public class MainItemGroup extends FlexItemGroup {

    private final ItemGroup source;
    private int buttonSlot = -1;
    private ItemGroup buttonGroup;

    @ParametersAreNonnullByDefault
    public MainItemGroup(NamespacedKey key, ItemStack item, ItemGroup source) {
        super(key, item);
        this.source = source;
    }

    /**
     * Shows the icon of the given group at a fixed slot, clicking it opens the group.
     */
    public void setButton(int slot, @Nullable ItemGroup group) {
        this.buttonSlot = slot;
        this.buttonGroup = group;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        return !getVisibleItems(p).isEmpty();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        open(p, profile, mode, 1);
    }

    @ParametersAreNonnullByDefault
    private void open(Player p, PlayerProfile profile, SlimefunGuideMode mode, int page) {
        if (mode == SlimefunGuideMode.SURVIVAL_MODE) {
            profile.getGuideHistory().add(this, page);
        }

        boolean hasButton = buttonGroup != null;
        List<SlimefunItem> items = getVisibleItems(p);
        int perPage = GuideMenus.LAST_SLOT - GuideMenus.FIRST_SLOT + 1 - (hasButton ? 1 : 0);
        int pages = Math.max(1, (items.size() - 1) / perPage + 1);
        int current = Math.min(Math.max(page, 1), pages);

        ChestMenu menu = GuideMenus.create(p, profile, mode);
        GuideMenus.addPageButtons(menu, p, current, pages, next -> open(p, profile, mode, next));

        int slot = GuideMenus.FIRST_SLOT;
        for (int i = perPage * (current - 1); i < items.size() && i < perPage * current; i++) {
            if (slot == buttonSlot && hasButton) {
                slot++;
            }
            GuideMenus.addItem(menu, slot, p, profile, mode, this, current, items.get(i));
            slot++;
        }

        if (hasButton) {
            menu.addItem(buttonSlot, buttonGroup.getItem(p));
            menu.addMenuClickHandler(buttonSlot, (pl, s, item, action) -> {
                SlimefunGuide.openItemGroup(profile, buttonGroup, mode, 1);
                return false;
            });
        }

        menu.open(p);
    }

    @ParametersAreNonnullByDefault
    private List<SlimefunItem> getVisibleItems(Player p) {
        List<SlimefunItem> list = new ArrayList<>();
        for (SlimefunItem item : source.getItems()) {
            if (!item.isHidden() && !item.isDisabledIn(p.getWorld())) {
                list.add(item);
            }
        }
        return list;
    }
}
