package uwu.llkc.cnc.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import uwu.llkc.cnc.CNCMod;
import uwu.llkc.cnc.common.entities.Peashooter;

import java.util.function.Supplier;

public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CNCMod.MOD_ID);

    public static final Supplier<EntityType<Peashooter>> PEASHOOTER = ENTITY_TYPES.register("peashooter",
            () -> EntityType.Builder.of(Peashooter::new, MobCategory.MISC).sized(1, 1).build(CNCMod.rlStr("peashooter")));
}
