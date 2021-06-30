package net.mcreator.moda.procedures;

import net.minecraft.world.GameType;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.moda.ModaModElements;
import net.mcreator.moda.ModaMod;

import java.util.Random;
import java.util.Map;
import java.util.Collections;

@ModaModElements.ModElement.Tag
public class TeleporterClickedProcedure extends ModaModElements.ModElement {
	public TeleporterClickedProcedure(ModaModElements instance) {
		super(instance, 12);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ModaMod.LOGGER.warn("Failed to load dependency entity for procedure TeleporterClicked!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				ModaMod.LOGGER.warn("Failed to load dependency itemstack for procedure TeleporterClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		{
			Entity _ent = entity;
			_ent.setPositionAndUpdate(
					(entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
							entity.getEyePosition(1f).add(entity.getLook(1f).x * 25, entity.getLook(1f).y * 25, entity.getLook(1f).z * 25),
							RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.ANY, entity)).getPos().getX()),
					(entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
							entity.getEyePosition(1f).add(entity.getLook(1f).x * 25, entity.getLook(1f).y * 25, entity.getLook(1f).z * 25),
							RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.ANY, entity)).getPos().getY()),
					(entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
							entity.getEyePosition(1f).add(entity.getLook(1f).x * 25, entity.getLook(1f).y * 25, entity.getLook(1f).z * 25),
							RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.ANY, entity)).getPos().getZ()));
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).connection.setPlayerLocation(
						(entity.world
								.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
										entity.getEyePosition(1f).add(entity.getLook(1f).x * 25, entity.getLook(1f).y * 25,
												entity.getLook(1f).z * 25),
										RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.ANY, entity))
								.getPos().getX()),
						(entity.world
								.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
										entity.getEyePosition(1f).add(entity.getLook(1f).x * 25, entity.getLook(1f).y * 25,
												entity.getLook(1f).z * 25),
										RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.ANY, entity))
								.getPos().getY()),
						(entity.world
								.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
										entity.getEyePosition(1f).add(entity.getLook(1f).x * 25, entity.getLook(1f).y * 25,
												entity.getLook(1f).z * 25),
										RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.ANY, entity))
								.getPos().getZ()),
						_ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
			}
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
			{
				ItemStack _ist = (itemstack);
				if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
					_ist.shrink(1);
					_ist.setDamage(0);
				}
			}
		}
	}
}
