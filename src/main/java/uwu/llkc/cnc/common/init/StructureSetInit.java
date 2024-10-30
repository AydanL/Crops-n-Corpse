package uwu.llkc.cnc.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import uwu.llkc.cnc.CNCMod;

import java.util.List;

public class StructureSetInit {
    public static final ResourceKey<StructureSet> LOST_HOUSES = ResourceKey.create(Registries.STRUCTURE_SET,
            CNCMod.rl("lost_houses"));

    public static void bootstrap(BootstrapContext<StructureSet> context) {
        var lookup = context.lookup(Registries.STRUCTURE);
        context.register(LOST_HOUSES, new StructureSet(
                List.of(
                        new StructureSet.StructureSelectionEntry(
                                lookup.getOrThrow(StructureInit.HOUSE_GRILL),
                                1
                        ),
                        new StructureSet.StructureSelectionEntry(
                                lookup.getOrThrow(StructureInit.HOUSE_MAILBOX),
                                1
                        ),
                        new StructureSet.StructureSelectionEntry(
                                lookup.getOrThrow(StructureInit.HOUSE_POOL),
                                1
                        )
                ),
                new RandomSpreadStructurePlacement(34, 8, RandomSpreadType.LINEAR, 12938764)
        ));
    }
}
