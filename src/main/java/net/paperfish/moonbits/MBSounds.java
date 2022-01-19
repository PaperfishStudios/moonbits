package net.paperfish.moonbits;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MBSounds {
    // block sounds
    public static final Identifier GLASS_DOOR_OPEN_ID = new Identifier(MoonbitsMain.MOD_ID, "block.glass_door.open");
    public static SoundEvent GLASS_DOOR_OPEN = new SoundEvent(GLASS_DOOR_OPEN_ID);
    public static final Identifier GLASS_DOOR_CLOSE_ID = new Identifier(MoonbitsMain.MOD_ID, "block.glass_door.close");
    public static SoundEvent GLASS_DOOR_CLOSE = new SoundEvent(GLASS_DOOR_CLOSE_ID);

    public static final SoundEvent BLOCK_BUNDLE_BREAK = register("block.bundle.break");
    public static final SoundEvent BLOCK_BUNDLE_FALL = register("block.bundle.fall");
    public static final SoundEvent BLOCK_BUNDLE_HIT = register("block.bundle.hit");
    public static final SoundEvent BLOCK_BUNDLE_PLACE = register("block.bundle.place");
    public static final SoundEvent BLOCK_BUNDLE_STEP = register("block.bundle.step");
    public static final BlockSoundGroup BUNDLE = new BlockSoundGroup(1.0f, 1.0f,
            BLOCK_BUNDLE_BREAK, BLOCK_BUNDLE_STEP, BLOCK_BUNDLE_PLACE, BLOCK_BUNDLE_HIT, BLOCK_BUNDLE_FALL);
    public static final SoundEvent BLOCK_SACK_BREAK = register("block.sack.break");
    public static final SoundEvent BLOCK_SACK_FALL = register("block.sack.fall");
    public static final SoundEvent BLOCK_SACK_HIT = register("block.sack.hit");
    public static final SoundEvent BLOCK_SACK_PLACE = register("block.sack.place");
    public static final SoundEvent BLOCK_SACK_STEP = register("block.sack.step");
    public static final BlockSoundGroup SACK = new BlockSoundGroup(1.0f, 1.0f,
            BLOCK_SACK_BREAK, BLOCK_SACK_STEP, BLOCK_SACK_PLACE, BLOCK_SACK_HIT, BLOCK_SACK_FALL);


    // item sounds
    public static final Identifier WRENCH_ID = new Identifier(MoonbitsMain.MOD_ID, "item.wrench.use");
    public static SoundEvent WRENCH = new SoundEvent(WRENCH_ID);

    public static SoundEvent GRIZZLY_AMBIENT = register("entity.mbgrizzly.ambient");
    public static SoundEvent BABY_GRIZZ_AMBIENT = register("entity.mbgrizzly.ambient_baby");
    public static SoundEvent GRIZZLY_DEATH = register("entity.mbgrizzly.death");
    public static SoundEvent GRIZZLY_HURT = register("entity.mbgrizzly.hurt");
    public static SoundEvent GRIZZLY_STEPS = register("entity.mbgrizzly.step");
    public static SoundEvent GRIZZLY_WARN = register("entity.mbgrizzly.warning");

    public static void initSounds() {
        register("block.glass_door.open", GLASS_DOOR_OPEN);
        register("block.glass_door.close", GLASS_DOOR_CLOSE);

        register("item.wrench.use", WRENCH);
    }

    public static void register(String id, SoundEvent event) {
        Registry.register(Registry.SOUND_EVENT, new Identifier(MoonbitsMain.MOD_ID, id), event);
    }
    public static SoundEvent register(String id) {
        return Registry.register(Registry.SOUND_EVENT, new Identifier(MoonbitsMain.MOD_ID, id), new SoundEvent(new Identifier(MoonbitsMain.MOD_ID, id)));
    }
}
