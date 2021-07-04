package net.mcreator.moda.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.moda.item.IronPoleItem;
import net.mcreator.moda.ModaModElements;
import net.mcreator.moda.ModaMod;

import java.util.Map;

@ModaModElements.ModElement.Tag
public class IronpoleUsedProcedure extends ModaModElements.ModElement {
	public IronpoleUsedProcedure(ModaModElements instance) {
		super(instance, 59);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ModaMod.LOGGER.warn("Failed to load dependency entity for procedure IronpoleUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = new ItemStack(IronPoleItem.block, (int) (1));
			((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
					((PlayerEntity) entity).container.func_234641_j_());
		}
	}
}
