package net.paperfish.moonbits.registry;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MBBlockFamily {
    private final Block baseBlock;
    final Map<Variant, Block> variants = Maps.newHashMap();
    public List<Block> cuttable = new ArrayList<>();
    public List<Block> childBlocks = new ArrayList<>();
    boolean generateModels = true;
    boolean generateRecipes = true;
    @Nullable
    String group;
    @Nullable
    String unlockCriterionName;

    MBBlockFamily(Block baseBlock) {
        this.baseBlock = baseBlock;
    }

    public Block getBaseBlock() {
        return this.baseBlock;
    }

    public Map<Variant, Block> getVariants() {
        return this.variants;
    }

    public Block getVariant(Variant variant) {
        return this.variants.get(variant);
    }

    public boolean shouldGenerateModels() {
        return this.generateModels;
    }

    public boolean shouldGenerateRecipes() {
        return this.generateRecipes;
    }

    public Optional<String> getGroup() {
        if (StringUtils.isBlank(this.group)) {
            return Optional.empty();
        }
        return Optional.of(this.group);
    }

    public Optional<String> getUnlockCriterionName() {
        if (StringUtils.isBlank(this.unlockCriterionName)) {
            return Optional.empty();
        }
        return Optional.of(this.unlockCriterionName);
    }

    public static class Builder {
        private final MBBlockFamily family;

        public Builder(Block baseBlock) {
            this.family = new MBBlockFamily(baseBlock);
        }

        public MBBlockFamily build() {
            return this.family;
        }

        public MBBlockFamily.Builder button(Block block) {
            this.family.variants.put(Variant.BUTTON, block);
            return this;
        }

        public MBBlockFamily.Builder chiseled(Block block) {
            this.family.variants.put(Variant.CHISELED, block);
            return this;
        }

        public MBBlockFamily.Builder cracked(Block block) {
            this.family.variants.put(Variant.CRACKED, block);
            return this;
        }

        public MBBlockFamily.Builder cut(Block block) {
            this.family.variants.put(Variant.CUT, block);
            return this;
        }

        public MBBlockFamily.Builder door(Block block) {
            this.family.variants.put(Variant.DOOR, block);
            return this;
        }

        public MBBlockFamily.Builder fence(Block block) {
            this.family.variants.put(Variant.FENCE, block);
            return this;
        }

        public MBBlockFamily.Builder fenceGate(Block block) {
            this.family.variants.put(Variant.FENCE_GATE, block);
            return this;
        }

        public MBBlockFamily.Builder sign(Block block, Block wallBlock) {
            this.family.variants.put(Variant.SIGN, block);
            this.family.variants.put(Variant.WALL_SIGN, wallBlock);
            return this;
        }

        public MBBlockFamily.Builder slab(Block block) {
            this.family.variants.put(Variant.SLAB, block);
            return this;
        }

        public MBBlockFamily.Builder stairs(Block block) {
            this.family.variants.put(Variant.STAIRS, block);
            return this;
        }

        public MBBlockFamily.Builder pressurePlate(Block block) {
            this.family.variants.put(Variant.PRESSURE_PLATE, block);
            return this;
        }

        public MBBlockFamily.Builder polished(Block block) {
            this.family.variants.put(Variant.POLISHED, block);
            return this;
        }

        public MBBlockFamily.Builder trapdoor(Block block) {
            this.family.variants.put(Variant.TRAPDOOR, block);
            return this;
        }

        public MBBlockFamily.Builder wall(Block block) {
            this.family.variants.put(Variant.WALL, block);
            return this;
        }
        
        public MBBlockFamily.Builder pillar(Block block) {
            this.family.variants.put(Variant.PILLAR, block);
            return this;
        }
        public MBBlockFamily.Builder column(Block block, Block cap) {
            this.family.variants.put(Variant.COLUMN, block);
            this.family.variants.put(Variant.CARVED, cap);
            return this;
        }
        
        public MBBlockFamily.Builder mossy(Block block) {
            this.family.variants.put(Variant.MOSSY, block);
            return this;
        }

        public MBBlockFamily.Builder bookshelf(Block block) {
            this.family.variants.put(Variant.BOOKSHELF, block);
            return this;
        }

        public MBBlockFamily.Builder planterBox(Block block) {
            this.family.variants.put(Variant.PLANTER_BOX, block);
            return this;
        }

        public MBBlockFamily.Builder carpet(Block block) {
            this.family.variants.put(Variant.CARPET, block);
            return this;
        }

        public MBBlockFamily.Builder stonecut(Block block) {
            this.family.cuttable.add(block);
            return this;
        }
        public MBBlockFamily.Builder stonecut(List<Block> blocks) {
            this.family.cuttable.addAll(blocks);
            return this;
        }

        public MBBlockFamily.Builder child(MBBlockFamily child) {
            child.getVariants().forEach((variant, block) -> this.family.childBlocks.add(block));
            this.family.childBlocks.addAll(child.cuttable);
            this.family.childBlocks.addAll(child.childBlocks);
            return this;
        }

        public MBBlockFamily.Builder noGenerateModels() {
            this.family.generateModels = false;
            return this;
        }

        public MBBlockFamily.Builder noGenerateRecipes() {
            this.family.generateRecipes = false;
            return this;
        }

        public MBBlockFamily.Builder group(String group) {
            this.family.group = group;
            return this;
        }

        public MBBlockFamily.Builder unlockCriterionName(String unlockCriterionName) {
            this.family.unlockCriterionName = unlockCriterionName;
            return this;
        }
    }

    public static enum Variant {
        BUTTON("button"),
        CHISELED("chiseled"),
        CRACKED("cracked"),
        CUT("cut"),
        DOOR("door"),
        FENCE("fence"),
        FENCE_GATE("fence_gate"),
        SIGN("sign"),
        SLAB("slab"),
        STAIRS("stairs"),
        PRESSURE_PLATE("pressure_plate"),
        POLISHED("polished"),
        TRAPDOOR("trapdoor"),
        WALL("wall"),
        WALL_SIGN("wall_sign"),
        PILLAR("pillar"),
        COLUMN("column"),
        CARVED("carved"),
        MOSSY("mossy"),
        BOOKSHELF("bookshelf"),
        PLANTER_BOX("planter_box"),
        CARPET("carpet"),
        SC("stonecut");

        private final String name;

        private Variant(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
