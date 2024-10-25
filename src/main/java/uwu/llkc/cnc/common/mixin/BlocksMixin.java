package uwu.llkc.cnc.common.mixin;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.llkc.cnc.common.blocks.SunflowerBlock;

@Mixin(Blocks.class)
public class BlocksMixin {
    @Inject(method = "register(Ljava/lang/String;Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/world/level/block/Block;", at = @At("HEAD"), cancellable = true)
    private static void cnc$register(final String name, final Block block, final CallbackInfoReturnable<Block> ci) {
        if (name.equals("sunflower")) {
            if (BuiltInRegistries.BLOCK instanceof MappedRegistry<?> mappedRegistry &&
                    mappedRegistry.unregisteredIntrusiveHolders != null &&
                    mappedRegistry.unregisteredIntrusiveHolders.containsKey(block)) {
                mappedRegistry.unregisteredIntrusiveHolders.remove(block);
                ci.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, name, new SunflowerBlock(block.properties())));
            }
        }
    }
}
