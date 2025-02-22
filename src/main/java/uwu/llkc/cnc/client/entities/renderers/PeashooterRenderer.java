package uwu.llkc.cnc.client.entities.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import uwu.llkc.cnc.CNCMod;
import uwu.llkc.cnc.client.entities.models.PeashooterModel;
import uwu.llkc.cnc.common.entities.plants.Peashooter;

public class PeashooterRenderer extends MobRenderer<Peashooter, PeashooterModel> {
    public static final ResourceLocation TEXTURE = CNCMod.rl("textures/entity/peashooter.png");

    public PeashooterRenderer(EntityRendererProvider.Context context) {
        super(context, new PeashooterModel(context.bakeLayer(PeashooterModel.MAIN_LAYER)), 0);
    }

    @Override
    protected void scale(Peashooter livingEntity, PoseStack poseStack, float partialTickTime) {
        super.scale(livingEntity, poseStack, partialTickTime);
        poseStack.scale(1.2f, 1.2f, 1.2f);
    }

    @Override
    protected float getFlipDegrees(Peashooter livingEntity) {
        return 0f;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Peashooter peashooter) {
        return TEXTURE;
    }
}
