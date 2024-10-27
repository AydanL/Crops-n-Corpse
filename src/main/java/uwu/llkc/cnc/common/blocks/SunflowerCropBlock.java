package uwu.llkc.cnc.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.llkc.cnc.common.init.BlockRegistry;

import javax.annotation.Nullable;

public class SunflowerCropBlock extends DoublePlantBlock implements BonemealableBlock {
    public static final MapCodec<SunflowerCropBlock> CODEC = simpleCodec(SunflowerCropBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    public static final int MAX_AGE = 2;
    private static final int DOUBLE_PLANT_AGE_INTERSECTION = 2;
    private static final VoxelShape[] UPPER_SHAPE_BY_AGE = new VoxelShape[]{
            box(0, 0, 0, 16, 11, 16),
            box(0, 0, 0, 16, 11, 16),
            box(0, 0, 0, 16, 11, 16)
    };
    private static final VoxelShape[] LOWER_SHAPE_BY_AGE = new VoxelShape[]{
            box(0, 0, 0, 16, 5, 16),
            box(0, 0, 0, 16, 15, 16),
            box(0, 0, 0, 16, 16, 16),
    };


    public SunflowerCropBlock(Properties properties) {
        super(properties);
    }

    private static boolean canGrowInto(LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        return blockstate.isAir() || blockstate.is(BlockRegistry.SUNFLOWER_CROP.get());
    }

    private static boolean sufficientLight(LevelReader level, BlockPos pos) {
        return CropBlock.hasSufficientLight(level, pos);
    }

    private static boolean isLower(BlockState state) {
        return state.is(BlockRegistry.SUNFLOWER_CROP.get()) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    private static boolean isDouble(int age) {
        return age >= 2;
    }

    @Override
    public MapCodec<SunflowerCropBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER
                ? UPPER_SHAPE_BY_AGE[state.getValue(AGE)]
                : LOWER_SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    public BlockState updateShape(
            BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos
    ) {
        if (isDouble(state.getValue(AGE))) {
            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        } else {
            return state.canSurvive(level, currentPos) ? state : Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        net.neoforged.neoforge.common.util.TriState soilDecision = level.getBlockState(pos.below()).canSustainPlant(level, pos.below(), Direction.UP, state);
        return isLower(state) && !sufficientLight(level, pos) ? soilDecision.isTrue() : super.canSurvive(state, level, pos);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getBlock() instanceof FarmBlock;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof Ravager && level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            level.destroyBlock(pos, true, entity);
        }

        super.entityInside(state, level, pos, entity);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return false;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    /**
     * Performs a random tick on a block.
     */
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        float f = CropBlock.getGrowthSpeed(state, level, pos);
        boolean flag = random.nextInt((int) (25.0F / f) + 1) == 0;
        if (flag) {
            this.grow(level, state, pos, 1);
        }
    }

    private void grow(ServerLevel level, BlockState state, BlockPos pos, int ageIncrement) {
        int i = Math.min(state.getValue(AGE) + ageIncrement, 2);
        if (this.canGrow(level, pos, state, i)) {
            if (isMaxAge(state)) {
                level.setBlock(pos, Blocks.SUNFLOWER.defaultBlockState(), 27);
                level.setBlock(pos.above(), Blocks.SUNFLOWER.defaultBlockState().setValue(SunflowerBlock.HALF, DoubleBlockHalf.UPPER), 27);
            } else {
                BlockState blockstate = state.setValue(AGE, i);
                level.setBlock(pos, blockstate, 2);
                if (isDouble(i)) {
                    level.setBlock(pos.above(), blockstate.setValue(HALF, DoubleBlockHalf.UPPER), 3);
                }
            }
        }
    }

    private boolean canGrow(LevelReader reader, BlockPos pos, BlockState state, int age) {
        return sufficientLight(reader, pos) && (!isDouble(age) || canGrowInto(reader, pos.above()));
    }

    private boolean isMaxAge(BlockState state) {
        return state.getValue(AGE) >= 2;
    }

    @Nullable
    private PosAndState getLowerHalf(LevelReader level, BlockPos pos, BlockState state) {
        if (isLower(state)) {
            return new PosAndState(pos, state);
        } else {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            return isLower(blockstate) ? new PosAndState(blockpos, blockstate) : null;
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        PosAndState posandstate = this.getLowerHalf(level, pos, state);
        return posandstate != null && this.canGrow(
                level, posandstate.pos, posandstate.state, posandstate.state.getValue(AGE) + 1
        );
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        PosAndState posandstate = this.getLowerHalf(level, pos, state);
        if (posandstate != null) {
            this.grow(level, posandstate.state, posandstate.pos, 1);
        }
    }

    record PosAndState(BlockPos pos, BlockState state) {
    }
}
