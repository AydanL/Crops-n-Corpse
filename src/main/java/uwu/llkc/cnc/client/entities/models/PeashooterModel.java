package uwu.llkc.cnc.client.entities.models;


import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.jetbrains.annotations.NotNull;
import uwu.llkc.cnc.CNCMod;
import uwu.llkc.cnc.client.entities.animations.PeashooterAnimations;
import uwu.llkc.cnc.common.entities.plants.Peashooter;

public class PeashooterModel extends HierarchicalModel<Peashooter> {
	public static final ModelLayerLocation MAIN_LAYER = new ModelLayerLocation(CNCMod.rl("peashooter"), "main");
	private final ModelPart root;
	private final ModelPart rootStem;
	private final ModelPart rootHead;

	public PeashooterModel(ModelPart root) {
		this.root = root.getChild("root");
		this.rootStem = this.root.getChild("rootStem");
		this.rootHead = rootStem.getChild("rootHead");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 30).addBox(-2.52F, -1.5F, -2.51F, 5.02F, 2.0F, 5.02F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition rootStem = root.addOrReplaceChild("rootStem", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Stem = rootStem.addOrReplaceChild("Stem", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition stem_r1 = Stem.addOrReplaceChild("stem_r1", CubeListBuilder.create().texOffs(36, 7).addBox(-1.0F, -9.5F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2007F, 0.0F, 0.0F));

		PartDefinition rootHead = rootStem.addOrReplaceChild("rootHead", CubeListBuilder.create(), PartPose.offset(0.0F, -7.5F, 1.5F));

		PartDefinition Head = rootHead.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 1).addBox(-2.5F, -5.0F, -3.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition Leaf = Head.addOrReplaceChild("Leaf", CubeListBuilder.create().texOffs(30, 0).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, 3.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition Snout = Head.addOrReplaceChild("Snout", CubeListBuilder.create().texOffs(17, 0).addBox(-1.5F, -3.0F, -2.75F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -2.5F, -0.1309F, 0.0F, 0.0F));

		PartDefinition Mouth = Snout.addOrReplaceChild("Mouth", CubeListBuilder.create().texOffs(23, 7).addBox(-2.0F, -2.0F, -1.5F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, -2.5F, -0.1309F, 0.0F, 0.0F));

		PartDefinition Leaves = root.addOrReplaceChild("Leaves", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition LeafEast = Leaves.addOrReplaceChild("LeafEast", CubeListBuilder.create().texOffs(18, 16).addBox(-6.0F, -1.0F, -2.5F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition LeafSouth = Leaves.addOrReplaceChild("LeafSouth", CubeListBuilder.create().texOffs(0, 21).addBox(-2.5F, -1.0F, 0.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition LeafNorth = Leaves.addOrReplaceChild("LeafNorth", CubeListBuilder.create().texOffs(17, 24).addBox(-2.5F, -1.0F, -6.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition LeafWest = Leaves.addOrReplaceChild("LeafWest", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -1.0F, -2.5F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.5F, 0.0F, 0.0F, 0.0F, -0.2618F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Peashooter entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(entity.attack, PeashooterAnimations.ATTACK, ageInTicks);
		this.animate(entity.idle, PeashooterAnimations.IDLE, ageInTicks);
		this.animate(entity.die, PeashooterAnimations.DEATH, ageInTicks);

		rootStem.yRot = (float) Math.toRadians(netHeadYaw);
		rootHead.xRot = ((float) Math.toRadians(headPitch));
	}

	@Override
	public @NotNull ModelPart root() {
		return root;
	}
}