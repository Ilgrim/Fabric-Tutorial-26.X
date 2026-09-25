package net.kaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.kaupenjoe.tutorialmod.block.ModBlocks;

public class ModStrippableBlocks {
    public static void registerStrippableBlocks() {
        BlockTransformerHelper.registerStripping(ModBlocks.BALSA_LOG, ModBlocks.STRIPPED_BALSA_LOG);
        BlockTransformerHelper.registerStripping(ModBlocks.BALSA_WOOD, ModBlocks.STRIPPED_BALSA_WOOD);
    }
}
