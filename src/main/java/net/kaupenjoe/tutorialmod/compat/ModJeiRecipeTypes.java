package net.kaupenjoe.tutorialmod.compat;

import mezz.jei.api.recipe.types.IRecipeType;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.recipe.custom.CrystallizerRecipe;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

public class ModJeiRecipeTypes {
    public static final IRecipeType<RecipeHolder<CrystallizerRecipe>> CRYSTALLIZER =
            create(TutorialMod.MOD_ID, "crystallizer", CrystallizerRecipe.class);

    // From Occultism: https://github.com/klikli-dev/occultism/blob/version/26.1.2/src/main/java/com/klikli_dev/occultism/integration/jei/impl/JeiRecipeTypes.java
    // Under MIT-License
    public static <R extends Recipe<?>> IRecipeType<RecipeHolder<R>> create(String modid, String name, Class<? extends R> recipeClass) {
        Identifier uid = Identifier.fromNamespaceAndPath(modid, name);
        @SuppressWarnings({"unchecked", "RedundantCast"})
        Class<? extends RecipeHolder<R>> holderClass = (Class<? extends RecipeHolder<R>>) (Object) RecipeHolder.class;
        return IRecipeType.create(uid, holderClass);
    }
}
