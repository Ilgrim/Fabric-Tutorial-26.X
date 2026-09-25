package net.kaupenjoe.tutorialmod.worldgen.tree;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.worldgen.ModFeatures;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;

public class ModTreeGrowers {
    public static final TreeGrower BALSA = new TreeGrower(TutorialMod.MOD_ID + ":balsa",
            WeightedList.of(ModFeatures.BALSA_KEY), WeightedList.of(), WeightedList.of(), null);
}
