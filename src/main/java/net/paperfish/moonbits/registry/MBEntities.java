package net.paperfish.moonbits.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.BiomeKeys;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.entity.*;

public class MBEntities {

    public static final TagKey<EntityType<?>> LIGHTWEIGHT = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(Moonbits.MODID, "lightweight"));

    public static final EntityType<GrizzlyBearEntity> GRIZZLY_BEAR = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("moonbits", "grizzly_bear"),
            FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(GrizzlyBearEntity::new)
                    .defaultAttributes(GrizzlyBearEntity::createGrizzAttributes)
                    .dimensions(EntityDimensions.fixed(1.0F, 0.8F)).build());

    public static final EntityType<GrasshatEntity> GRASSHAT = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("moonbits", "grasshat"),
            FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(GrasshatEntity::new)
                    .defaultAttributes(GrasshatEntity::createAttributes)
                    .dimensions(EntityDimensions.fixed(1.0F, 1.2F)).build());

    public static final EntityType<ItemHookEntity> ITEM_HOOK_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("moonbits", "item_hook"),
            FabricEntityTypeBuilder.<ItemHookEntity>create(SpawnGroup.MISC, ItemHookEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).fireImmune()
                    .trackRangeChunks(10).trackedUpdateRate(Integer.MAX_VALUE).build());
    public static final EntityType<GlowItemHookEntity> GLOW_ITEM_HOOK_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("moonbits", "glow_item_hook"),
            FabricEntityTypeBuilder.<GlowItemHookEntity>create(SpawnGroup.MISC, GlowItemHookEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).fireImmune()
                    .trackRangeChunks(10).trackedUpdateRate(Integer.MAX_VALUE).build());

    public static final EntityType<SeatBlockEntity> SEAT_BLOCK_ENTITY = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("moonbits", "seatblock"),
        FabricEntityTypeBuilder.<SeatBlockEntity>create(SpawnGroup.MISC, SeatBlockEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.35f)).fireImmune().disableSummon().build());
        
    public static void registerEntities() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(
                BiomeKeys.BIRCH_FOREST,
                BiomeKeys.OLD_GROWTH_BIRCH_FOREST,
                BiomeKeys.TAIGA,
                BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
                BiomeKeys.OLD_GROWTH_PINE_TAIGA,
                BiomeKeys.SNOWY_TAIGA,
                BiomeKeys.DARK_FOREST,
                BiomeKeys.WOODED_BADLANDS
        ), SpawnGroup.CREATURE, GRIZZLY_BEAR, 8, 1, 4);
    }
}
