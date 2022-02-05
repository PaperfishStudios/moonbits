package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementsProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.item.HoneycombItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;
import net.paperfish.moonbits.MBEntities;
import net.paperfish.moonbits.MBItems;
import net.paperfish.moonbits.Moonbits;

import java.util.function.Consumer;

public class MBAdvancementsProvider extends FabricAdvancementsProvider {
    private static final EntityType<?>[] BREEDABLE_ANIMALS = new EntityType[]{EntityType.HORSE, EntityType.DONKEY, EntityType.MULE, EntityType.SHEEP, EntityType.COW, EntityType.MOOSHROOM,
            EntityType.PIG, EntityType.CHICKEN, EntityType.WOLF, EntityType.OCELOT, EntityType.RABBIT, EntityType.LLAMA, EntityType.CAT, EntityType.PANDA, EntityType.FOX, EntityType.BEE,
            EntityType.HOGLIN, EntityType.STRIDER, EntityType.GOAT, EntityType.AXOLOTL, MBEntities.MOOBLOOM};
    private static final Item[] FISH_ITEMS = new Item[]{Items.COD, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.SALMON};
    private static final Item[] FISH_BUCKET_ITEMS = new Item[]{Items.COD_BUCKET, Items.TROPICAL_FISH_BUCKET, Items.PUFFERFISH_BUCKET, Items.SALMON_BUCKET};
    private static final Item[] FOOD_ITEMS = new Item[]{
            Items.APPLE, Items.MUSHROOM_STEW, Items.BREAD, Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE,
            Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON, Items.COOKIE, Items.MELON_SLICE,
            Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH, Items.SPIDER_EYE, Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO,
            Items.GOLDEN_CARROT, Items.PUMPKIN_PIE, Items.RABBIT, Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON, Items.CHORUS_FRUIT, Items.BEETROOT, Items.BEETROOT_SOUP,
            Items.DRIED_KELP, Items.SUSPICIOUS_STEW, Items.SWEET_BERRIES, Items.HONEY_BOTTLE, Items.GLOW_BERRIES, MBItems.ROASTED_BERRIES};
    private static final Item[] AXE_ITEMS = new Item[]{Items.WOODEN_AXE, Items.GOLDEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE};

    protected MBAdvancementsProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {

    }
    
    public void story(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Task.create().display(Blocks.GRASS_BLOCK, 
                new TranslatableText("advancements.story.root.title"), 
                new TranslatableText("advancements.story.root.description"), 
                new Identifier("textures/gui/advancements/backgrounds/stone.png"), AdvancementFrame.TASK, false, false, false)
                .criterion("crafting_table", InventoryChangedCriterion.Conditions.items(Blocks.CRAFTING_TABLE)).build(consumer, "story/root");
        Advancement mine_stone = Advancement.Task.create().parent(root).display(Items.WOODEN_PICKAXE, 
                new TranslatableText("advancements.story.mine_stone.title"), 
                new TranslatableText("advancements.story.mine_stone.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("get_stone", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().tag(ItemTags.STONE_TOOL_MATERIALS).build())).build(consumer, "story/mine_stone");
        Advancement upgrade_tools = Advancement.Task.create().parent(mine_stone).display(Items.STONE_PICKAXE,
                new TranslatableText("advancements.story.upgrade_tools.title"),
                new TranslatableText("advancements.story.upgrade_tools.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("stone_pickaxe", InventoryChangedCriterion.Conditions.items(Items.STONE_PICKAXE)).build(consumer, "story/upgrade_tools");

        Advancement mine_peat = Advancement.Task.create().parent(upgrade_tools).display(MBItems.PEAT,
                        new TranslatableText("advancements.story.mine_peat.title"),
                        new TranslatableText("advancements.story.mine_peat.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("peat", InventoryChangedCriterion.Conditions.items(MBItems.PEAT)).build(consumer, "story/mine_peat");
        Advancement mine_coal = Advancement.Task.create().parent(mine_peat).display(Items.COAL,
                        new TranslatableText("advancements.story.mine_coal.title"),
                        new TranslatableText("advancements.story.mine_coal.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("coal", InventoryChangedCriterion.Conditions.items(Items.COAL)).build(consumer, "story/mine_coal");
        Advancement craft_furnace = Advancement.Task.create().parent(upgrade_tools).display(Blocks.FURNACE,
                        new TranslatableText("advancements.story.craft_furnace.title"),
                        new TranslatableText("advancements.story.craft_furnace.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("furnace", InventoryChangedCriterion.Conditions.items(Items.FURNACE)).build(consumer, "story/craft_furnace");

        Advancement smelt_iron = Advancement.Task.create().parent(craft_furnace).display(Items.IRON_INGOT,
                new TranslatableText("advancements.story.smelt_iron.title"),
                new TranslatableText("advancements.story.smelt_iron.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("iron", InventoryChangedCriterion.Conditions.items(Items.IRON_INGOT)).build(consumer, "story/smelt_iron");
        Advancement smelt_copper = Advancement.Task.create().parent(craft_furnace).display(Items.COPPER_INGOT,
                        new TranslatableText("advancements.story.smelt_copper.title"),
                        new TranslatableText("advancements.story.smelt_copper.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("copper", InventoryChangedCriterion.Conditions.items(Items.COPPER_INGOT)).build(consumer, "story/smelt_copper");
        Advancement smelt_gold = Advancement.Task.create().parent(craft_furnace).display(Items.GOLD_INGOT,
                        new TranslatableText("advancements.story.smelt_gold.title"),
                        new TranslatableText("advancements.story.smelt_gold.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("gold", InventoryChangedCriterion.Conditions.items(Items.GOLD_INGOT)).build(consumer, "story/smelt_gold");
        Advancement golden_apple = Advancement.Task.create().parent(smelt_gold).display(Items.GOLDEN_APPLE,
                        new TranslatableText("advancements.story.golden_apple.title"),
                        new TranslatableText("advancements.story.golden_apple.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("golden_apple", InventoryChangedCriterion.Conditions.items(Items.GOLDEN_APPLE)).build(consumer, "story/golden_apple");

        Advancement iron_tools = Advancement.Task.create().parent(smelt_iron).display(Items.IRON_PICKAXE,
                new TranslatableText("advancements.story.iron_tools.title"),
                new TranslatableText("advancements.story.iron_tools.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("iron_pickaxe", InventoryChangedCriterion.Conditions.items(Items.IRON_PICKAXE)).build(consumer, "story/iron_tools");
        Advancement craft_compass = Advancement.Task.create().parent(smelt_iron).display(Items.COMPASS,
                        new TranslatableText("advancements.story.craft_compass.title"),
                        new TranslatableText("advancements.story.craft_compass.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("compass", InventoryChangedCriterion.Conditions.items(Items.COMPASS)).build(consumer, "story/craft_compass");
        Advancement blast_furnace = Advancement.Task.create().parent(smelt_iron).display(Items.BLAST_FURNACE,
                        new TranslatableText("advancements.story.blast_furnace.title"),
                        new TranslatableText("advancements.story.blast_furnace.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("blast_furnace", InventoryChangedCriterion.Conditions.items(Items.BLAST_FURNACE)).build(consumer, "story/blast_furnace");

        Advancement mine_diamond = Advancement.Task.create().parent(iron_tools).display(Items.DIAMOND,
                new TranslatableText("advancements.story.mine_diamond.title"),
                new TranslatableText("advancements.story.mine_diamond.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("diamond", InventoryChangedCriterion.Conditions.items(Items.DIAMOND)).build(consumer, "story/mine_diamond");
        Advancement mine_lapis = Advancement.Task.create().parent(iron_tools).display(Items.LAPIS_LAZULI,
                        new TranslatableText("advancements.story.mine_lapis.title"),
                        new TranslatableText("advancements.story.mine_lapis.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("lapis", InventoryChangedCriterion.Conditions.items(Items.LAPIS_LAZULI)).build(consumer, "story/mine_lapis");
        Advancement mine_emerald = Advancement.Task.create().parent(iron_tools).display(Items.EMERALD,
                        new TranslatableText("advancements.story.mine_emerald.title"),
                        new TranslatableText("advancements.story.mine_emerald.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("emerald", InventoryChangedCriterion.Conditions.items(Items.EMERALD)).build(consumer, "story/mine_emerald");
        Advancement mine_amethyst = Advancement.Task.create().parent(iron_tools).display(Items.AMETHYST_SHARD,
                        new TranslatableText("advancements.story.mine_amethyst.title"),
                        new TranslatableText("advancements.story.mine_amethyst.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("amethyst", InventoryChangedCriterion.Conditions.items(Items.AMETHYST_SHARD)).build(consumer, "story/mine_amethyst");

        Advancement lava_bucket = Advancement.Task.create().parent(smelt_iron).display(Items.LAVA_BUCKET,
                new TranslatableText("advancements.story.lava_bucket.title"),
                new TranslatableText("advancements.story.lava_bucket.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("lava_bucket", InventoryChangedCriterion.Conditions.items(Items.LAVA_BUCKET)).build(consumer, "story/lava_bucket");
        Advancement obtain_armor = Advancement.Task.create().parent(smelt_iron).display(Items.IRON_CHESTPLATE,
                new TranslatableText("advancements.story.obtain_armor.title"),
                new TranslatableText("advancements.story.obtain_armor.description"), null, AdvancementFrame.TASK, true, true, false)
                .criteriaMerger(CriterionMerger.OR)
                    .criterion("iron_helmet", InventoryChangedCriterion.Conditions.items(Items.IRON_HELMET))
                    .criterion("iron_chestplate", InventoryChangedCriterion.Conditions.items(Items.IRON_CHESTPLATE))
                    .criterion("iron_leggings", InventoryChangedCriterion.Conditions.items(Items.IRON_LEGGINGS))
                    .criterion("iron_boots", InventoryChangedCriterion.Conditions.items(Items.IRON_BOOTS)).build(consumer, "story/obtain_armor");
        Advancement.Task.create().parent(mine_lapis).display(Items.ENCHANTED_BOOK,
                new TranslatableText("advancements.story.enchant_item.title"),
                new TranslatableText("advancements.story.enchant_item.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("enchanted_item", EnchantedItemCriterion.Conditions.any()).build(consumer, "story/enchant_item");
        Advancement form_obsidian = Advancement.Task.create().parent(lava_bucket).display(Blocks.OBSIDIAN,
                new TranslatableText("advancements.story.form_obsidian.title"),
                new TranslatableText("advancements.story.form_obsidian.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("obsidian", InventoryChangedCriterion.Conditions.items(Blocks.OBSIDIAN)).build(consumer, "story/form_obsidian");
        Advancement.Task.create().parent(obtain_armor).display(Items.SHIELD,
                new TranslatableText("advancements.story.deflect_arrow.title"),
                new TranslatableText("advancements.story.deflect_arrow.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("deflected_projectile", EntityHurtPlayerCriterion.Conditions.create(DamagePredicate.Builder.create().type(DamageSourcePredicate.Builder.create().projectile(true)).blocked(true))).build(consumer, "story/deflect_arrow");
        Advancement.Task.create().parent(mine_diamond).display(Items.DIAMOND_CHESTPLATE,
                new TranslatableText("advancements.story.shiny_gear.title"),
                new TranslatableText("advancements.story.shiny_gear.description"), null, AdvancementFrame.TASK, true, true, false)
                .criteriaMerger(CriterionMerger.OR)
                    .criterion("diamond_helmet", InventoryChangedCriterion.Conditions.items(Items.DIAMOND_HELMET))
                    .criterion("diamond_chestplate", InventoryChangedCriterion.Conditions.items(Items.DIAMOND_CHESTPLATE))
                    .criterion("diamond_leggings", InventoryChangedCriterion.Conditions.items(Items.DIAMOND_LEGGINGS))
                    .criterion("diamond_boots", InventoryChangedCriterion.Conditions.items(Items.DIAMOND_BOOTS)).build(consumer, "story/shiny_gear");
        Advancement enter_the_nether = Advancement.Task.create().parent(form_obsidian).display(Items.FLINT_AND_STEEL,
                new TranslatableText("advancements.story.enter_the_nether.title"),
                new TranslatableText("advancements.story.enter_the_nether.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("entered_nether", ChangedDimensionCriterion.Conditions.to(World.NETHER)).build(consumer, "story/enter_the_nether");
        Advancement.Task.create().parent(enter_the_nether).display(Items.GOLDEN_APPLE,
                new TranslatableText("advancements.story.cure_zombie_villager.title"),
                new TranslatableText("advancements.story.cure_zombie_villager.description"), null, AdvancementFrame.GOAL, true, true, false)
                .criterion("cured_zombie", CuredZombieVillagerCriterion.Conditions.any()).build(consumer, "story/cure_zombie_villager");
        Advancement follow_ender_eye = Advancement.Task.create().parent(enter_the_nether).display(Items.ENDER_EYE,
                new TranslatableText("advancements.story.follow_ender_eye.title"),
                new TranslatableText("advancements.story.follow_ender_eye.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("in_stronghold", LocationArrivalCriterion.Conditions.create(LocationPredicate.feature(StructureFeature.STRONGHOLD))).build(consumer, "story/follow_ender_eye");
        Advancement.Task.create().parent(follow_ender_eye).display(Blocks.END_STONE,
                new TranslatableText("advancements.story.enter_the_end.title"),
                new TranslatableText("advancements.story.enter_the_end.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("entered_end", ChangedDimensionCriterion.Conditions.to(World.END)).build(consumer, "story/enter_the_end");

    }
    public void nether(Consumer<Advancement> consumer) {

    }
    public void end(Consumer<Advancement> consumer) {

    }
    public void husbandry(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Task.create().display(Blocks.HAY_BLOCK,
                new TranslatableText("advancements.husbandry.root.title"),
                new TranslatableText("advancements.husbandry.root.description"),
                new Identifier("textures/gui/advancements/backgrounds/husbandry.png"), AdvancementFrame.TASK, false, false, false)
                .criterion("consumed_item", ConsumeItemCriterion.Conditions.any()).build(consumer, "husbandry/root");

        Advancement plant_seed = Advancement.Task.create().parent(root).display(Items.WHEAT,
                new TranslatableText("advancements.husbandry.plant_seed.title"),
                new TranslatableText("advancements.husbandry.plant_seed.description"), null, AdvancementFrame.TASK, true, true, false)
                .criteriaMerger(CriterionMerger.OR)
                    .criterion("wheat", PlacedBlockCriterion.Conditions.block(Blocks.WHEAT))
                    .criterion("pumpkin_stem", PlacedBlockCriterion.Conditions.block(Blocks.PUMPKIN_STEM))
                    .criterion("melon_stem", PlacedBlockCriterion.Conditions.block(Blocks.MELON_STEM))
                    .criterion("beetroots", PlacedBlockCriterion.Conditions.block(Blocks.BEETROOTS))
                    .criterion("nether_wart", PlacedBlockCriterion.Conditions.block(Blocks.NETHER_WART))
                    .criterion("sweet_pits", PlacedBlockCriterion.Conditions.block(MBItems.SWEET_BERRY_PITS))
                    .criterion("glow_pits", PlacedBlockCriterion.Conditions.block(MBItems.GLOW_BERRY_PITS)).build(consumer, "husbandry/plant_seed");
        Advancement breed_an_animal = Advancement.Task.create().parent(root).display(Items.WHEAT,
                new TranslatableText("advancements.husbandry.breed_an_animal.title"),
                new TranslatableText("advancements.husbandry.breed_an_animal.description"), null, AdvancementFrame.TASK, true, true, false)
                .criteriaMerger(CriterionMerger.OR).criterion("bred", BredAnimalsCriterion.Conditions.any()).build(consumer, "husbandry/breed_an_animal");
        this.requireFoodItemsEaten(Advancement.Task.create()).parent(plant_seed).display(Items.APPLE,
                new TranslatableText("advancements.husbandry.balanced_diet.title"),
                new TranslatableText("advancements.husbandry.balanced_diet.description"), null, AdvancementFrame.CHALLENGE, true, true, false)
                .rewards(AdvancementRewards.Builder.experience(100)).build(consumer, "husbandry/balanced_diet");
        Advancement.Task.create().parent(plant_seed).display(Items.NETHERITE_HOE,
                new TranslatableText("advancements.husbandry.netherite_hoe.title"),
                new TranslatableText("advancements.husbandry.netherite_hoe.description"), null, AdvancementFrame.CHALLENGE, true, true, false)
                .rewards(AdvancementRewards.Builder.experience(100)).criterion("netherite_hoe", InventoryChangedCriterion.Conditions.items(Items.NETHERITE_HOE)).build(consumer, "husbandry/obtain_netherite_hoe");
        Advancement tame_an_animal = Advancement.Task.create().parent(root).display(Items.LEAD,
                new TranslatableText("advancements.husbandry.tame_an_animal.title"),
                new TranslatableText("advancements.husbandry.tame_an_animal.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("tamed_animal", TameAnimalCriterion.Conditions.any()).build(consumer, "husbandry/tame_an_animal");
        this.requireListedAnimalsBred(Advancement.Task.create()).parent(breed_an_animal).display(Items.GOLDEN_CARROT,
                new TranslatableText("advancements.husbandry.breed_all_animals.title"),
                new TranslatableText("advancements.husbandry.breed_all_animals.description"), null, AdvancementFrame.CHALLENGE, true, true, false)
                .rewards(AdvancementRewards.Builder.experience(100)).build(consumer, "husbandry/bred_all_animals");

        Advancement fishy_business = this.requireListedFishCaught(Advancement.Task.create()).parent(root).criteriaMerger(CriterionMerger.OR).display(Items.FISHING_ROD,
                new TranslatableText("advancements.husbandry.fishy_business.title"),
                new TranslatableText("advancements.husbandry.fishy_business.description"), null, AdvancementFrame.TASK, true, true, false)
                .build(consumer, "husbandry/fishy_business");
        Advancement tactical_fishing = this.requireListedFishBucketsFilled(Advancement.Task.create()).parent(fishy_business).criteriaMerger(CriterionMerger.OR).display(Items.PUFFERFISH_BUCKET,
                new TranslatableText("advancements.husbandry.tactical_fishing.title"),
                new TranslatableText("advancements.husbandry.tactical_fishing.description"), null, AdvancementFrame.TASK, true, true, false)
                .build(consumer, "husbandry/tactical_fishing");
        Advancement axolotl_in_a_bucket = Advancement.Task.create().parent(tactical_fishing).criteriaMerger(CriterionMerger.OR).criterion(Registry.ITEM.getId(Items.AXOLOTL_BUCKET).getPath(), FilledBucketCriterion.Conditions.create(ItemPredicate.Builder.create().items(Items.AXOLOTL_BUCKET).build())).display(Items.AXOLOTL_BUCKET,
                new TranslatableText("advancements.husbandry.axolotl_in_a_bucket.title"),
                new TranslatableText("advancements.husbandry.axolotl_in_a_bucket.description"), null, AdvancementFrame.TASK, true, true, false)
                .build(consumer, "husbandry/axolotl_in_a_bucket");
        Advancement.Task.create().parent(axolotl_in_a_bucket).criterion("kill_axolotl_target",
                EffectsChangedCriterion.Conditions.create(EntityPredicate.Builder.create().type(EntityType.AXOLOTL).build())).display(Items.TROPICAL_FISH_BUCKET,
                new TranslatableText("advancements.husbandry.kill_axolotl_target.title"),
                new TranslatableText("advancements.husbandry.kill_axolotl_target.description"), null, AdvancementFrame.TASK, true, true, false)
                .build(consumer, "husbandry/kill_axolotl_target");
        this.requireAllCatsTamed(Advancement.Task.create()).parent(tame_an_animal).display(Items.COD,
                new TranslatableText("advancements.husbandry.complete_catalogue.title"),
                new TranslatableText("advancements.husbandry.complete_catalogue.description"), null, AdvancementFrame.CHALLENGE, true, true, false)
                .rewards(AdvancementRewards.Builder.experience(50)).build(consumer, "husbandry/complete_catalogue");

        Advancement campfire = Advancement.Task.create().parent(root).display(Items.WHEAT,
                        new TranslatableText("advancements.husbandry.campfire.title"),
                        new TranslatableText("advancements.husbandry.campfire.description"), null, AdvancementFrame.TASK, true, true, false)
                .criteriaMerger(CriterionMerger.OR)
                .criterion("campfire", PlacedBlockCriterion.Conditions.block(Blocks.CAMPFIRE))
                .criterion("soul_campfire", PlacedBlockCriterion.Conditions.block(Blocks.SOUL_CAMPFIRE)).build(consumer, "husbandry/campfire");
        Advancement smoker = Advancement.Task.create().parent(root).display(Items.SMOKER,
                        new TranslatableText("advancements.story.smoker.title"),
                        new TranslatableText("advancements.story.smoker.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("smoker", InventoryChangedCriterion.Conditions.items(Items.SMOKER)).build(consumer, "story/smoker");
        Advancement safely_harvest_honey = Advancement.Task.create().parent(campfire).criterion("safely_harvest_honey",
                ItemUsedOnBlockCriterion.Conditions.create(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().tag(BlockTags.BEEHIVES).build()).smokey(true),
                ItemPredicate.Builder.create().items(Items.GLASS_BOTTLE))).display(Items.HONEY_BOTTLE,
                new TranslatableText("advancements.husbandry.safely_harvest_honey.title"),
                new TranslatableText("advancements.husbandry.safely_harvest_honey.description"), null, AdvancementFrame.TASK, true, true, false)
                .build(consumer, "husbandry/safely_harvest_honey");
        Advancement wax_on = Advancement.Task.create().parent(safely_harvest_honey).display(Items.HONEYCOMB,
                new TranslatableText("advancements.husbandry.wax_on.title"),
                new TranslatableText("advancements.husbandry.wax_on.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("wax_on", ItemUsedOnBlockCriterion.Conditions.create(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().keySet()).build()), ItemPredicate.Builder.create().items(Items.HONEYCOMB))).build(consumer, "husbandry/wax_on");
        Advancement.Task.create().parent(wax_on).display(Items.STONE_AXE,
                new TranslatableText("advancements.husbandry.wax_off.title"),
                new TranslatableText("advancements.husbandry.wax_off.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("wax_off", ItemUsedOnBlockCriterion.Conditions.create(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get().keySet()).build()), ItemPredicate.Builder.create().items(AXE_ITEMS))).build(consumer, "husbandry/wax_off");
        Advancement.Task.create().parent(root).criterion("silk_touch_nest",
                BeeNestDestroyedCriterion.Conditions.create(Blocks.BEE_NEST, ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))), NumberRange.IntRange.exactly(3))).display(Blocks.BEE_NEST,
                new TranslatableText("advancements.husbandry.silk_touch_nest.title"),
                new TranslatableText("advancements.husbandry.silk_touch_nest.description"), null, AdvancementFrame.TASK, true, true, false)
                .build(consumer, "husbandry/silk_touch_nest");

        Advancement.Task.create().parent(root).display(Items.OAK_BOAT,
                new TranslatableText("advancements.husbandry.ride_a_boat_with_a_goat.title"),
                new TranslatableText("advancements.husbandry.ride_a_boat_with_a_goat.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("ride_a_boat_with_a_goat", StartedRidingCriterion.Conditions.create(EntityPredicate.Builder.create().vehicle(EntityPredicate.Builder.create().type(EntityType.BOAT).passenger(EntityPredicate.Builder.create().type(EntityType.GOAT).build()).build()))).build(consumer, "husbandry/ride_a_boat_with_a_goat");
        Advancement.Task.create().parent(root).display(Items.GLOW_INK_SAC,
                new TranslatableText("advancements.husbandry.make_a_sign_glow.title"),
                new TranslatableText("advancements.husbandry.make_a_sign_glow.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("make_a_sign_glow", ItemUsedOnBlockCriterion.Conditions.create(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().tag(BlockTags.SIGNS).build()), ItemPredicate.Builder.create().items(Items.GLOW_INK_SAC))).build(consumer, "husbandry/make_a_sign_glow");

    }
    public void adventure(Consumer<Advancement> consumer) {

    }
    public void architect(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Task.create().display(Blocks.BRICKS,
                        new TranslatableText("advancements.architect.root.title"),
                        new TranslatableText("advancements.architect.root.description"),
                        new Identifier(Moonbits.MOD_ID, "textures/gui/advancements/backgrounds/architect.png"), AdvancementFrame.TASK, false, false, false)
                .criterion("crafting_table", InventoryChangedCriterion.Conditions.items(Blocks.CRAFTING_TABLE)).build(consumer, "architect/root");
        //todo: add the kiln lmao
        Advancement kiln = Advancement.Task.create().parent(root).display(Items.BLAST_FURNACE,
                        new TranslatableText("advancements.architect.kiln.title"),
                        new TranslatableText("advancements.architect.kiln.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("kiln", InventoryChangedCriterion.Conditions.items(Items.BLAST_FURNACE)).build(consumer, "story/kiln");
    }
    public void redstone(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Task.create().display(Items.REDSTONE,
                        new TranslatableText("advancements.redstone.root.title"),
                        new TranslatableText("advancements.redstone.root.description"),
                        new Identifier(Moonbits.MOD_ID, "textures/gui/advancements/backgrounds/quartz.png"), AdvancementFrame.TASK, false, false, false)
                .criterion("crafting_table", InventoryChangedCriterion.Conditions.items(Items.REDSTONE)).build(consumer, "redstone/root");
        Advancement redstone_torch = Advancement.Task.create().parent(root).display(Items.REDSTONE_TORCH,
                        new TranslatableText("advancements.redstone.redstone_torch.title"),
                        new TranslatableText("advancements.redstone.redstone_torch.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("redstone_torch", InventoryChangedCriterion.Conditions.items(Blocks.REDSTONE_TORCH)).build(consumer, "redstone/redstone_torch");
        Advancement repeater = Advancement.Task.create().parent(redstone_torch).display(Items.REPEATER,
                        new TranslatableText("advancements.redstone.repeater.title"),
                        new TranslatableText("advancements.redstone.repeater.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("repeater", InventoryChangedCriterion.Conditions.items(Blocks.REPEATER)).build(consumer, "redstone/repeater");
        Advancement comparator = Advancement.Task.create().parent(redstone_torch).display(Items.COMPARATOR,
                        new TranslatableText("advancements.redstone.comparator.title"),
                        new TranslatableText("advancements.redstone.comparator.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("comparator", InventoryChangedCriterion.Conditions.items(Blocks.COMPARATOR)).build(consumer, "redstone/comparator");

        Advancement piston = Advancement.Task.create().parent(root).display(Items.PISTON,
                        new TranslatableText("advancements.redstone.piston.title"),
                        new TranslatableText("advancements.redstone.piston.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("piston", InventoryChangedCriterion.Conditions.items(Blocks.PISTON)).build(consumer, "redstone/piston");
        Advancement sticky_piston = Advancement.Task.create().parent(piston).display(Items.SLIME_BALL,
                        new TranslatableText("advancements.redstone.sticky_piston.title"),
                        new TranslatableText("advancements.redstone.sticky_piston.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("sticky_piston", InventoryChangedCriterion.Conditions.items(Blocks.STICKY_PISTON)).build(consumer, "redstone/sticky_piston");
        Advancement slime_block = Advancement.Task.create().parent(sticky_piston).display(Items.SLIME_BLOCK,
                        new TranslatableText("advancements.redstone.slime_block.title"),
                        new TranslatableText("advancements.redstone.slime_block.description"), null, AdvancementFrame.TASK, true, true, false)
                .criterion("slime_block", InventoryChangedCriterion.Conditions.items(Blocks.SLIME_BLOCK)).build(consumer, "redstone/slime_block");


        Advancement mine_quartz = Advancement.Task.create().parent(root).display(Items.QUARTZ,
                        new TranslatableText("advancements.redstone.mine_quartz.title"),
                        new TranslatableText("advancements.redstone.mine_quartz.description"), null, AdvancementFrame.TASK, false, false, false)
                .criterion("quartz", InventoryChangedCriterion.Conditions.items(Items.QUARTZ)).build(consumer, "redstone/mine_quartz");
        Advancement observer = Advancement.Task.create().parent(mine_quartz).display(Items.OBSERVER,
                        new TranslatableText("advancements.redstone.observer.title"),
                        new TranslatableText("advancements.redstone.observer.description"), null, AdvancementFrame.TASK, false, false, false)
                .criterion("observer", InventoryChangedCriterion.Conditions.items(Blocks.OBSERVER)).build(consumer, "redstone/observer");

        Advancement sculk_sensor = Advancement.Task.create().parent(root).display(Items.SCULK_SENSOR,
                        new TranslatableText("advancements.redstone.sculk_sensor.title"),
                        new TranslatableText("advancements.redstone.sculk_sensor.description"), null, AdvancementFrame.TASK, false, false, false)
                .criterion("sculk_sensor", InventoryChangedCriterion.Conditions.items(Items.SCULK_SENSOR)).build(consumer, "redstone/sculk_sensor");
    }

    private Advancement.Task requireFoodItemsEaten(Advancement.Task task) {
        for (Item item : FOOD_ITEMS) {
            task.criterion(Registry.ITEM.getId(item).getPath(), ConsumeItemCriterion.Conditions.item(item));
        }
        return task;
    }

    private Advancement.Task requireListedAnimalsBred(Advancement.Task task) {
        for (EntityType<?> entityType : BREEDABLE_ANIMALS) {
            task.criterion(EntityType.getId(entityType).toString(), BredAnimalsCriterion.Conditions.create(EntityPredicate.Builder.create().type(entityType)));
        }
        task.criterion(EntityType.getId(EntityType.TURTLE).toString(), BredAnimalsCriterion.Conditions.create(EntityPredicate.Builder.create().type(EntityType.TURTLE).build(), EntityPredicate.Builder.create().type(EntityType.TURTLE).build(), EntityPredicate.ANY));
        return task;
    }

    private Advancement.Task requireListedFishBucketsFilled(Advancement.Task task) {
        for (Item item : FISH_BUCKET_ITEMS) {
            task.criterion(Registry.ITEM.getId(item).getPath(), FilledBucketCriterion.Conditions.create(ItemPredicate.Builder.create().items(item).build()));
        }
        return task;
    }

    private Advancement.Task requireListedFishCaught(Advancement.Task task) {
        for (Item item : FISH_ITEMS) {
            task.criterion(Registry.ITEM.getId(item).getPath(), FishingRodHookedCriterion.Conditions.create(ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.Builder.create().items(item).build()));
        }
        return task;
    }

    private Advancement.Task requireAllCatsTamed(Advancement.Task task) {
        CatEntity.TEXTURES.forEach((type, id) -> task.criterion(id.getPath(), TameAnimalCriterion.Conditions.create(EntityPredicate.Builder.create().type((Identifier)id).build())));
        return task;
    }
}
