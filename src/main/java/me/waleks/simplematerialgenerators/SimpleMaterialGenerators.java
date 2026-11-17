package me.waleks.simplematerialgenerators;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.waleks.simplematerialgenerators.compressed.CompressedSetup;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SimpleMaterialGenerators extends JavaPlugin implements SlimefunAddon {

    private static SimpleMaterialGenerators instance;

    @Override
    public void onEnable() {
        setInstance(this);

        SMGItemSetup.setup(this);
        SMGItems.SMG_MAIN_GROUP.addGroup(SMGItems.SMG_ITEM_CATEGORY);
        CompressedSetup.setup(this);
        SMGItems.SMG_MAIN_GROUP.register(this);
    }

    @Override
    public void onDisable() {
        setInstance(null);
    }

    @Nonnull
    @Override
    public String getBugTrackerURL() {
        return "https://github.com/mybezo/SMG/issues";
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nonnull
    public static SimpleMaterialGenerators getInstance() {
        return instance;
    }

    private static void setInstance(@Nullable SimpleMaterialGenerators instance) {
        SimpleMaterialGenerators.instance = instance;
    }
}
