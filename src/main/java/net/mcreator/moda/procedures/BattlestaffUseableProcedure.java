package net.mcreator.moda.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.moda.item.BattlestaffItem;
import net.mcreator.moda.ModaModElements;
import net.mcreator.moda.ModaMod;

import java.util.Map;

@ModaModElements.ModElement.Tag
public class BattlestaffUseableProcedure extends ModaModElements.ModElement {
	public BattlestaffUseableProcedure(ModaModElements instance) {
		super(instance, 52);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ModaMod.LOGGER.warn("Failed to load dependency entity for procedure BattlestaffUseable!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(BattlestaffItem.block, (int) (1)).getItem());
	}
}
