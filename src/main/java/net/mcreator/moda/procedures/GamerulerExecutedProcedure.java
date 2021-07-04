package net.mcreator.moda.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.moda.item.BattlestaffItem;
import net.mcreator.moda.ModaModElements;
import net.mcreator.moda.ModaMod;

import java.util.Map;
import java.util.HashMap;

@ModaModElements.ModElement.Tag
public class GamerulerExecutedProcedure extends ModaModElements.ModElement {
	public GamerulerExecutedProcedure(ModaModElements instance) {
		super(instance, 55);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ModaMod.LOGGER.warn("Failed to load dependency entity for procedure GamerulerExecuted!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			if (!dependencies.containsKey("cmdparams"))
				ModaMod.LOGGER.warn("Failed to load dependency cmdparams for procedure GamerulerExecuted!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ModaMod.LOGGER.warn("Failed to load dependency x for procedure GamerulerExecuted!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ModaMod.LOGGER.warn("Failed to load dependency y for procedure GamerulerExecuted!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ModaMod.LOGGER.warn("Failed to load dependency z for procedure GamerulerExecuted!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ModaMod.LOGGER.warn("Failed to load dependency world for procedure GamerulerExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("1984"))) {
			if ((!((entity instanceof PlayerEntity)
					? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.NETHERITE_HELMET, (int) (1)))
					: false))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(Items.NETHERITE_HELMET, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
			if ((!((entity instanceof PlayerEntity)
					? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.NETHERITE_CHESTPLATE, (int) (1)))
					: false))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(Items.NETHERITE_CHESTPLATE, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
			if ((!((entity instanceof PlayerEntity)
					? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.NETHERITE_BOOTS, (int) (1)))
					: false))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(Items.NETHERITE_BOOTS, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
			if ((!((entity instanceof PlayerEntity)
					? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(BattlestaffItem.block, (int) (1)))
					: false))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(BattlestaffItem.block, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
			if ((!((entity instanceof PlayerEntity)
					? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.ELYTRA, (int) (1)))
					: false))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(Items.ELYTRA, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(Items.FIREWORK_ROCKET, (int) (1));
				_setstack.setCount((int) 64);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		} else {
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						"tellraw @p {\"text\":\"Incorrect Passkey\",\"color\":\"red\"}");
			}
		}
	}
}
