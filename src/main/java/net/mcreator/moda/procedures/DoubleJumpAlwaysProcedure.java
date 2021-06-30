package net.mcreator.moda.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.moda.ModaModVariables;
import net.mcreator.moda.ModaModElements;
import net.mcreator.moda.ModaMod;

import java.util.Map;
import java.util.HashMap;

@ModaModElements.ModElement.Tag
public class DoubleJumpAlwaysProcedure extends ModaModElements.ModElement {
	public DoubleJumpAlwaysProcedure(ModaModElements instance) {
		super(instance, 4);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ModaMod.LOGGER.warn("Failed to load dependency entity for procedure DoubleJumpAlways!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ModaMod.LOGGER.warn("Failed to load dependency x for procedure DoubleJumpAlways!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ModaMod.LOGGER.warn("Failed to load dependency y for procedure DoubleJumpAlways!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ModaMod.LOGGER.warn("Failed to load dependency z for procedure DoubleJumpAlways!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ModaMod.LOGGER.warn("Failed to load dependency world for procedure DoubleJumpAlways!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((!((world.getBlockState(new BlockPos((int) (Math.floor(x)), (int) ((Math.floor(y)) - 1), (int) (Math.floor(z)))))
				.getBlock() == Blocks.AIR.getDefaultState().getBlock()))
				&& (!((world.getBlockState(new BlockPos((int) (Math.floor(x)), (int) ((Math.floor(y)) - 1), (int) (Math.floor(z)))))
						.getBlock() == Blocks.WATER.getDefaultState().getBlock())))
				&& ((!((world.getBlockState(new BlockPos((int) (Math.floor(x)), (int) ((Math.floor(y)) - 1), (int) (Math.floor(z)))))
						.getBlock() == Blocks.LAVA.getDefaultState().getBlock()))
						&& (!((world.getBlockState(new BlockPos((int) (Math.floor(x)), (int) ((Math.floor(y)) - 1), (int) (Math.floor(z)))))
								.getBlock() == Blocks.VOID_AIR.getDefaultState().getBlock()))))) {
			{
				boolean _setval = (boolean) (true);
				entity.getCapability(ModaModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.double_jump = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
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
}
