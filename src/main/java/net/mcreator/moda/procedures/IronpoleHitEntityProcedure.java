package net.mcreator.moda.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.moda.item.IronPoleItem;
import net.mcreator.moda.ModaModElements;
import net.mcreator.moda.ModaMod;

import java.util.Map;

@ModaModElements.ModElement.Tag
public class IronpoleHitEntityProcedure extends ModaModElements.ModElement {
	public IronpoleHitEntityProcedure(ModaModElements instance) {
		super(instance, 57);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				ModaMod.LOGGER.warn("Failed to load dependency sourceentity for procedure IronpoleHitEntity!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Blocks.AIR, (int) (1)).getItem())) {
			if (sourceentity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(IronPoleItem.block, (int) (1));
				_setstack.setCount((int) 1);
				((LivingEntity) sourceentity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (sourceentity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) sourceentity).inventory.markDirty();
			}
		} else {
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(IronPoleItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
		}
	}
}
