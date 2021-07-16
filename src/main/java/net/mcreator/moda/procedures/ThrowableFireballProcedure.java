package net.mcreator.moda.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.Hand;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Blocks;

import net.mcreator.moda.ModaModElements;
import net.mcreator.moda.ModaMod;

import java.util.Map;
import java.util.HashMap;

@ModaModElements.ModElement.Tag
public class ThrowableFireballProcedure extends ModaModElements.ModElement {
	public ThrowableFireballProcedure(ModaModElements instance) {
		super(instance, 45);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ModaMod.LOGGER.warn("Failed to load dependency entity for procedure ThrowableFireball!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ModaMod.LOGGER.warn("Failed to load dependency x for procedure ThrowableFireball!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ModaMod.LOGGER.warn("Failed to load dependency y for procedure ThrowableFireball!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ModaMod.LOGGER.warn("Failed to load dependency z for procedure ThrowableFireball!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ModaMod.LOGGER.warn("Failed to load dependency world for procedure ThrowableFireball!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Items.FIRE_CHARGE, (int) (1)).getItem())) {
			if (world instanceof ServerWorld) {
				Entity entityToSpawn = new FireballEntity(EntityType.FIREBALL, (World) world);
				entityToSpawn.setLocationAndAngles((x + 0.5), (y + 0.5), (z + 0.5), (float) 0, (float) 0);
				entityToSpawn.setRenderYawOffset((float) 0);
				entityToSpawn.setMotion(0, 0, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(entityToSpawn.getPosition()),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			if ((!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayerEntity) {
						return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
					} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
						NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
								.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
						return _npi != null && _npi.getGameType() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity)))) {
				if (entity instanceof LivingEntity) {
					ItemStack _setstack = new ItemStack(Blocks.AIR, (int) (1));
					_setstack.setCount((int) 1);
					((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
					if (entity instanceof ServerPlayerEntity)
						((ServerPlayerEntity) entity).inventory.markDirty();
				}
			}
		}
	}

	@SubscribeEvent
	public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		PlayerEntity entity = event.getPlayer();
		if (event.getHand() != entity.getActiveHand()) {
			return;
		}
		double i = event.getPos().getX();
		double j = event.getPos().getY();
		double k = event.getPos().getZ();
		IWorld world = event.getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
