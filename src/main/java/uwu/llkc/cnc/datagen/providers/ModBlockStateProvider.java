package uwu.llkc.cnc.datagen.providers;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import uwu.llkc.cnc.CNCMod;
import uwu.llkc.cnc.common.blocks.CustomCakeBlock;
import uwu.llkc.cnc.common.blocks.PlantCropBlock;
import uwu.llkc.cnc.common.init.BlockRegistry;

public class ModBlockStateProvider extends BlockStateProvider {


    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CNCMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        crop(BlockRegistry.PEASHOOTER_CROP.get(),
                models().crop("pea_crop_stage0", CNCMod.rl("block/pea_crop_stage0")),
                models().crop("pea_crop_stage0", CNCMod.rl("block/pea_crop_stage0")),
                models().crop("pea_crop_stage1", CNCMod.rl("block/pea_crop_stage1")),
                models().crop("pea_crop_stage1", CNCMod.rl("block/pea_crop_stage1")),
                models().crop("pea_crop_stage2", CNCMod.rl("block/pea_crop_stage2")),
                models().crop("pea_crop_stage2", CNCMod.rl("block/pea_crop_stage2")),
                models().crop("pea_crop_stage2", CNCMod.rl("block/pea_crop_stage2")),
                models().crop("pea_crop_stage3", CNCMod.rl("block/pea_crop_stage3")));

        crop(BlockRegistry.SUNFLOWER_CROP.get(),
                models().crop("sunflower_crop_stage0", CNCMod.rl("block/sunflower_crop_stage0")),
                models().crop("sunflower_crop_stage0", CNCMod.rl("block/sunflower_crop_stage0")),
                models().crop("sunflower_crop_stage1", CNCMod.rl("block/sunflower_crop_stage1")),
                models().crop("sunflower_crop_stage1", CNCMod.rl("block/sunflower_crop_stage1")),
                models().crop("sunflower_crop_stage2", CNCMod.rl("block/sunflower_crop_stage2")),
                models().crop("sunflower_crop_stage2", CNCMod.rl("block/sunflower_crop_stage2")),
                models().crop("sunflower_crop_stage2", CNCMod.rl("block/sunflower_crop_stage2")),
                models().crop("sunflower_crop_stage3", CNCMod.rl("block/sunflower_crop_stage3")));

        simpleBlock(BlockRegistry.TRAFFIC_CONE.get(), models().getExistingFile(CNCMod.rl("block/traffic_cone")));
        logBlock(BlockRegistry.WALNUT_LOG.get());
        simpleBlockItem(BlockRegistry.WALNUT_LOG.get(), models().getExistingFile(CNCMod.rl("block/walnut_log")));
        logBlock(BlockRegistry.STRIPPED_WALNUT_LOG.get());
        simpleBlockItem(BlockRegistry.STRIPPED_WALNUT_LOG.get(), models().getExistingFile(CNCMod.rl("block/stripped_walnut_log")));
        simpleBlock(BlockRegistry.WALNUT_LEAVES.get(), models().leaves(CNCMod.rlStr("walnut_leaves"), blockTexture(BlockRegistry.WALNUT_LEAVES.get())).renderType("cutout"));
        axisBlock(BlockRegistry.WALNUT_WOOD.get(), CNCMod.rl("block/walnut_log"), CNCMod.rl("block/walnut_log"));
        simpleBlockItem(BlockRegistry.WALNUT_WOOD.get(), models().getExistingFile(CNCMod.rl("block/walnut_wood")));
        axisBlock(BlockRegistry.STRIPPED_WALNUT_WOOD.get(), CNCMod.rl("block/stripped_walnut_log"), CNCMod.rl("block/stripped_walnut_log"));
        simpleBlockItem(BlockRegistry.STRIPPED_WALNUT_WOOD.get(), models().getExistingFile(CNCMod.rl("block/stripped_walnut_wood")));
        simpleBlock(BlockRegistry.WALNUT_SAPLING.get(), models().cross("walnut_sapling", CNCMod.rl("item/walnut_sapling")).renderType("cutout"));
        simpleBlockWithItem(BlockRegistry.WALNUT_PLANKS.get(), cubeAll(BlockRegistry.WALNUT_PLANKS.get()));
        signBlock(BlockRegistry.STANDING_WALNUT_SIGN.get(), BlockRegistry.WALNUT_WALL_SIGN.get(), CNCMod.rl("block/stripped_walnut_log"));
        simpleBlock(BlockRegistry.WALL_HANGING_WALNUT_SIGN.get(), models().sign("wall_hanging_walnut_sign", CNCMod.rl("block/stripped_walnut_log")));
        simpleBlock(BlockRegistry.CEILING_HANGING_WALNUT_SIGN.get(), models().sign("ceiling_hanging_walnut_sign", CNCMod.rl("block/stripped_walnut_log")));
        trapdoorBlock(BlockRegistry.WALNUT_TRAPDOOR.get(), CNCMod.rl("block/walnut_trapdoor"), true);
        simpleBlockItem(BlockRegistry.WALNUT_TRAPDOOR.get(), models().getExistingFile(CNCMod.rl("block/walnut_trapdoor_bottom")));
        doorBlock(BlockRegistry.WALNUT_DOOR.get(), CNCMod.rl("block/walnut_door_bottom"), CNCMod.rl("block/walnut_door_top"));
        buttonBlock(BlockRegistry.WALNUT_BUTTON.get(), CNCMod.rl("block/walnut_planks"));
        pressurePlateBlock(BlockRegistry.WALNUT_PRESSURE_PLATE.get(), CNCMod.rl("block/walnut_planks"));
        simpleBlockItem(BlockRegistry.WALNUT_PRESSURE_PLATE.get(), models().getExistingFile(CNCMod.rl("block/walnut_pressure_plate")));
        fenceBlock(BlockRegistry.WALNUT_FENCE.get(), CNCMod.rl("block/walnut_planks"));
        fenceGateBlock(BlockRegistry.WALNUT_FENCE_GATE.get(), CNCMod.rl("block/walnut_planks"));
        simpleBlockItem(BlockRegistry.WALNUT_FENCE_GATE.get(), models().getExistingFile(CNCMod.rl("block/walnut_fence_gate")));
        slabBlock(BlockRegistry.WALNUT_SLAB.get(), CNCMod.rl("block/walnut_planks"), CNCMod.rl("block/walnut_planks"));
        simpleBlockItem(BlockRegistry.WALNUT_SLAB.get(), models().getExistingFile(CNCMod.rl("block/walnut_slab")));
        stairsBlock(BlockRegistry.WALNUT_STAIRS.get(), CNCMod.rl("block/walnut_planks"));
        simpleBlockItem(BlockRegistry.WALNUT_STAIRS.get(), models().getExistingFile(CNCMod.rl("block/walnut_stairs")));

        cake(10,
                CNCMod.rl("block/cherry_chocolate_cake_side"),
                CNCMod.rl("block/cherry_chocolate_cake_bottom"),
                CNCMod.rl("block/cherry_chocolate_cake_top"),
                CNCMod.rl("block/cherry_chocolate_cake_side"),
                CNCMod.rl("block/cherry_chocolate_cake_inner"),
                "cherry_chocolate_cake", BlockRegistry.CHOCOLATE_CHERRY_CAKE.get());

        simpleBlockItem(BlockRegistry.CHOCOLATE_CHERRY_CAKE.get(), models().getExistingFile(CNCMod.rl("item/chocolate_cherry_cake")));
    }

    void crop(PlantCropBlock block, BlockModelBuilder... builders) {
        VariantBlockStateBuilder variantBlockStateBuilder = getVariantBuilder(block);
        variantBlockStateBuilder.forAllStates(blockState -> ConfiguredModel.builder().modelFile(builders[blockState.getValue(PlantCropBlock.AGE)].renderType("cutout")).build());
    }

    void cake(int size,
              ResourceLocation particle,
              ResourceLocation bottom,
              ResourceLocation top,
              ResourceLocation side,
              ResourceLocation inside,
              String name, CustomCakeBlock block) {
        int offset = 16 - size;
        getVariantBuilder(block).forAllStates(state -> {
            int slice = state.getValue(block.bites);
            return ConfiguredModel.builder()
                    .modelFile(
                            models().getBuilder(name + "_slice_" + (block.maxBites - slice))
                                    .texture("particle", particle)
                                    .texture("bottom", bottom)
                                    .texture("top", top)
                                    .texture("side", side)
                                    .texture("inside", inside)
                                    .element()
                                    .from(offset, 0, offset)
                                    .to(offset + slice * 2, 8, size)
                                    .faces(((direction, faceBuilder) -> {
                                        switch (direction) {
                                            case DOWN -> faceBuilder.texture("#bottom")
                                                    .cullface(Direction.DOWN);
                                            case UP -> faceBuilder.texture("#top");
                                            case NORTH, SOUTH, EAST -> faceBuilder.texture("#side");
                                            case WEST -> faceBuilder.texture("#inside");
                                        }
                                    })).end()
                    ).build();
        });
    }
}
