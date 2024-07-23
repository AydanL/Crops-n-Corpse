package uwu.llkc.cnc.common.entities.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uwu.llkc.cnc.common.entities.projectiles.PeaProjectile;
import uwu.llkc.cnc.common.init.EntityTypeRegistry;

public class Peashooter extends CNCPlant {
    public final AnimationState idle = new AnimationState();
    public final AnimationState attack = new AnimationState();
    public final AnimationState die = new AnimationState();

    public Peashooter(EntityType<Peashooter> entityType, Level level) {
        super(entityType, level);
        moveControl = new MoveControl(this) {
            @Override
            public void tick() {}
        };
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        var pos = BlockPos.containing(getX(), getY(), getZ());
        super.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    public static AttributeSupplier.Builder attributes() {
        return CNCPlant.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.FOLLOW_RANGE, 20);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new RangedAttackGoal(this, 1, 20, 30) {
            @Override
            public boolean canContinueToUse() {
                var use = super.canContinueToUse() && distanceTo(getTarget()) < 30;
                var angle = Math.toDegrees(Math.atan((getY() - getTarget().getY()) / (position().subtract(getTarget().position()).horizontalDistance())));
                return use && Math.abs(angle) < 25;
            }
        });
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            idle.startIfStopped(tickCount);
        }
    }

    @Override
    public void die(DamageSource damageSource) {
        level().broadcastEntityEvent(this, (byte)1);
        super.die(damageSource);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
    }

    @Override
    public void setPos(double x, double y, double z) {
        super.setPos(x, y, z);
    }

    @Override
    public PlantCategory getPlantCategory() {
        return PlantCategory.OFFENSIVE;
    }

    @Override
    public float getPlantPacketOverrideFloat() {
        return 0.01f;
    }

    @Override
    public int sunCost() {
        return 16;
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity target, float velocity) {
        PeaProjectile projectile = EntityTypeRegistry.PEA.get().create(level());
        if (projectile == null) return;
        projectile.damage = 3;
        projectile.setPos(this.getX(), this.getEyeY(), this.getZ());
        projectile.setOwner(this);
        var targetPos = target.getBoundingBox().getCenter();
        projectile.shoot(targetPos.x - getX(), targetPos.y - getEyeY(), targetPos.z - getZ(), velocity, 0);
        level().addFreshEntity(projectile);
        level().broadcastEntityEvent(this, (byte) 0);
    }

    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);
        if (id == 0) {
            attack.start(tickCount);
        } else if (id == 1) {
            die.start(tickCount);
        }
    }
}
