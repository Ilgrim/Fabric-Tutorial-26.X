package net.kaupenjoe.tutorialmod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.fabricmc.fabric.api.client.recipe.v1.sync.ClientRecipeSynchronizedEvent;
import net.fabricmc.fabric.api.recipe.v1.sync.SynchronizedRecipes;
import net.fabricmc.fabric.impl.recipe.sync.SynchronizedRecipesImpl;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.menu.custom.CrystallizerScreen;
import net.kaupenjoe.tutorialmod.recipe.ModRecipes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

@JeiPlugin
public class JEITutorialModPlugin implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "jei_plugin");
    }

    public static SynchronizedRecipes recipeMap = SynchronizedRecipesImpl.EMPTY;
    public JEITutorialModPlugin() {
        ClientRecipeSynchronizedEvent.EVENT.register((client, recipes) -> recipeMap = recipes);
    }

    // From Occultism
    // Under MIT License
    @SuppressWarnings({"unchecked", "rawtypes"})
    private <I extends RecipeInput, T extends Recipe<I>> List<RecipeHolder<T>> getRecipes(SynchronizedRecipes recipeMap, RecipeType<T> type) {
        return (List) recipeMap.getAllOfType(type);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrystallizerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(ModJeiRecipeTypes.CRYSTALLIZER, this.getRecipes(recipeMap,
                ModRecipes.CRYSTALLIZER_TYPE));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrystallizerScreen.class, 74, 30, 22, 20,
                ModJeiRecipeTypes.CRYSTALLIZER);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(ModJeiRecipeTypes.CRYSTALLIZER, new ItemStack(ModBlocks.CRYSTALLIZER.asItem()));
    }
}
