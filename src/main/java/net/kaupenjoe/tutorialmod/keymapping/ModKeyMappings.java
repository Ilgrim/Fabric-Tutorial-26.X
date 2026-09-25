package net.kaupenjoe.tutorialmod.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.client.KeyMapping;

public class ModKeyMappings {
    public static final KeyMapping KAUPEN_KEYMAPPING = KeyMappingHelper.registerKeyMapping(
            new KeyMapping("key.tutorialmod.kaupen_key",
                    InputConstants.KEY_K, KeyMapping.Category.MISC));


    public static void register() {
        TutorialMod.LOGGER.info("Registering ModKeyMappings for " + TutorialMod.MOD_ID);
    }
}
