package uwu.llkc.cnc.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.llkc.cnc.CNCMod;
import uwu.llkc.cnc.common.worldgen.structures.FlatGroundStructure;

import java.util.function.Supplier;

public class StructureTypeRegistry {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister
            .create(Registries.STRUCTURE_TYPE, CNCMod.MOD_ID);

    public static final Supplier<StructureType<FlatGroundStructure>> FLAT_GROUND = STRUCTURE_TYPES.register(
            "flat_ground",
            () -> () -> FlatGroundStructure.CODEC
    );
}
