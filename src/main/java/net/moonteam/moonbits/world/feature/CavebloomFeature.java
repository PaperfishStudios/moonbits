package net.moonteam.moonbits.world.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.moonteam.moonbits.MBBlocks;
import net.moonteam.moonbits.MBData;
import net.moonteam.moonbits.block.CavebloomFlowerBlock;
import net.moonteam.moonbits.block.CavebloomVineBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CavebloomFeature extends Feature<CavebloomFeatureConfig> {
    public static Tag<Block> canPlaceOn = MBData.TOUGH_DIRT;
    public static List<Direction> directions = Lists.newArrayList();

    public CavebloomFeature(Codec<CavebloomFeatureConfig> codec) {
        super(codec);
        directions.add(Direction.UP);
        Direction.Type.HORIZONTAL.forEach(directions::add);
    }

    @Override
    public boolean generate(FeatureContext<CavebloomFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        CavebloomFeatureConfig cfg = context.getConfig();
        int flowers = cfg.flowerSpead().get(random);
        if (!isAirOrWater(structureWorldAccess.getBlockState(blockPos))) {
            return false;
        }
        List<Direction> list = shuffleDirections(cfg, random);
        if (generate(structureWorldAccess, blockPos, structureWorldAccess.getBlockState(blockPos), cfg, random, list, true)) {
            return true;
        }
        BlockPos.Mutable mutable = blockPos.mutableCopy();
        block0: for (Direction direction : list) {
            mutable.set(blockPos);
            List<Direction> list2 = shuffleDirections(cfg, random, direction.getOpposite());
            for (int i = 0; i < cfg.searchRange().get(random); ++i) {
                mutable.set((Vec3i)blockPos, direction);
                BlockState blockState = structureWorldAccess.getBlockState(mutable);
                if (!isAirOrWater(blockState) && !(blockState.isOf(MBBlocks.CAVEBLOOM_VINE) || blockState.isOf(MBBlocks.CAVEBLOOM_FLOWERS))) continue block0;
                if (!generate(structureWorldAccess, mutable, blockState, cfg, random, list2, i < flowers)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean generate(StructureWorldAccess world, BlockPos pos, BlockState state, CavebloomFeatureConfig config, Random random, List<Direction> directions, boolean flower) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        for (Direction direction : directions) {
            BlockState blockState = world.getBlockState(mutable.set((Vec3i)pos, direction));
            if (!canPlaceOn.contains(blockState.getBlock()) && !blockState.isOf(MBBlocks.TOUGH_GRASS)) continue;
            if (flower) {
                CavebloomFlowerBlock flowerBlock = (CavebloomFlowerBlock)MBBlocks.CAVEBLOOM_FLOWERS;
                BlockState blockState2 = flowerBlock.withDirection(state, world, pos, direction);
                if (blockState2 == null) {
                    return false;
                }
                world.setBlockState(pos, blockState2, Block.NOTIFY_ALL);
                world.getChunk(pos).markBlockForPostProcessing(pos);
                if (random.nextFloat() < config.spreadChance().get(random)) {
                    flowerBlock.trySpreadRandomly(blockState2, world, pos, direction, random, true);
                }
            }
            else {
                CavebloomVineBlock vineBlock = (CavebloomVineBlock)MBBlocks.CAVEBLOOM_VINE;
                BlockState blockState2 = vineBlock.withDirection(state, world, pos, direction);
                if (blockState2 == null) {
                    return false;
                }
                world.setBlockState(pos, blockState2, Block.NOTIFY_ALL);
                world.getChunk(pos).markBlockForPostProcessing(pos);
                if (random.nextFloat() < config.spreadChance().get(random)) {
                    vineBlock.trySpreadRandomly(blockState2, world, pos, direction, random, true);
                }
            }
            return true;
        }
        return false;
    }

    public static List<Direction> shuffleDirections(CavebloomFeatureConfig config, Random random) {
        ArrayList<Direction> list = Lists.newArrayList(directions);
        Collections.shuffle(list, random);
        return list;
    }

    public static List<Direction> shuffleDirections(CavebloomFeatureConfig config, Random random, Direction excluded) {
        List<Direction> list = directions.stream().filter(direction -> direction != excluded).collect(Collectors.toList());
        Collections.shuffle(list, random);
        return list;
    }

    private static boolean isAirOrWater(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER);
    }
}
