package uwu.llkc.cnc.common.init;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import uwu.llkc.cnc.CNCMod;

import java.util.List;

public class StructureTemplatePoolInit {
    public static final ResourceKey<StructureTemplatePool> HOUSE_GRILL = ResourceKey.create(Registries.TEMPLATE_POOL,
            CNCMod.rl("house_grill"));
    public static final ResourceKey<StructureTemplatePool> HOUSE_MAILBOX = ResourceKey.create(Registries.TEMPLATE_POOL,
            CNCMod.rl("house_mailbox"));
    public static final ResourceKey<StructureTemplatePool> HOUSE_POOL = ResourceKey.create(Registries.TEMPLATE_POOL,
            CNCMod.rl("house_pool"));

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        var templatePoolLookup = context.lookup(Registries.TEMPLATE_POOL);

        context.register(HOUSE_GRILL, new StructureTemplatePool(
                templatePoolLookup.getOrThrow(Pools.EMPTY),
                List.of(
                        Pair.of(StructurePoolElement.single(CNCMod.rlStr("house_grill")), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));
        context.register(HOUSE_MAILBOX, new StructureTemplatePool(
                templatePoolLookup.getOrThrow(HOUSE_GRILL),
                List.of(
                        Pair.of(StructurePoolElement.single(CNCMod.rlStr("house_mailbox")), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));
        context.register(HOUSE_POOL, new StructureTemplatePool(
                templatePoolLookup.getOrThrow(HOUSE_MAILBOX),
                List.of(
                        Pair.of(StructurePoolElement.single(CNCMod.rlStr("house_pool")), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));
    }
}
