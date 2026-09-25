package net.kaupenjoe.tutorialmod.worldgen;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.worldgen.tree.InvertedPyramidFoliagePlacer;
import net.kaupenjoe.tutorialmod.worldgen.tree.SpiralTrunkPlacer;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.OffsetPlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModFeatures {
    // CF => Features with Configuration
    // Tree --> height, what trunks etc etc...
    // How something looks like
    public static final ResourceKey<Feature> OVERWORLD_FLUORITE_ORE_KEY = registerKey("overworld_fluorite_ore");
    public static final ResourceKey<Feature> NETHER_FLUORITE_ORE_KEY = registerKey("nether_fluorite_ore");
    public static final ResourceKey<Feature> END_FLUORITE_ORE_KEY = registerKey("end_fluorite_ore");

    public static final ResourceKey<Feature> BALSA_KEY = registerKey("balsa");

    public static final ResourceKey<Feature> HONEY_BERRY_BUSH_KEY = registerKey("honey_berry_bush");

    public static void bootstrap(BootstrapContext<Feature> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        context.register(OVERWORLD_FLUORITE_ORE_KEY, new OreFeature(
                List.of(BlockReplacement.replace(stoneReplaceables, ModBlocks.FLUORITE_ORE.defaultBlockState()),
                        BlockReplacement.replace(deepslateReplaceables, ModBlocks.FLUORITE_DEEPSLATE_ORE.defaultBlockState())),
                9));
        context.register(NETHER_FLUORITE_ORE_KEY, new OreFeature(netherReplaceables,
                ModBlocks.FLUORITE_NETHER_ORE.defaultBlockState(), 10));
        context.register(END_FLUORITE_ORE_KEY, new OreFeature(endReplaceables,
                ModBlocks.FLUORITE_END_ORE.defaultBlockState(), 12));

        context.register(BALSA_KEY, new TreeFeature.Builder(
                BlockStateProvider.of(ModBlocks.BALSA_LOG),
                new SpiralTrunkPlacer(3, 3, 4),
                // new BendingTrunkPlacer(3, 3, 4, 2, ConstantInt.of(5)),

                BlockStateProvider.of(ModBlocks.BALSA_LEAVES),
                new InvertedPyramidFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), 3),
                // new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.holderOf(Blocks.DIRT)).build());

        context.register(HONEY_BERRY_BUSH_KEY, new SimpleRandomSelectorFeature(
                        HolderSet.direct(PlacementUtils.inlinePlaced(new SimpleBlockFeature(
                                BlockStateProvider.of(ModBlocks.HONEY_BERRY_BUSH
                                        .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))),
                                CountPlacement.of(32),
                                OffsetPlacement.ofTriangle(6, 3),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)))));
    }

    public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
    }
}
