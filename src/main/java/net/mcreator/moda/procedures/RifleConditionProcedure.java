package net.mcreator.moda.procedures;

import net.minecraft.item.ItemStack;

import net.mcreator.moda.ModaModElements;
import net.mcreator.moda.ModaMod;

import java.util.Map;

@ModaModElements.ModElement.Tag
public class RifleConditionProcedure extends ModaModElements.ModElement {
	public RifleConditionProcedure(ModaModElements instance) {
		super(instance, 36);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				ModaMod.LOGGER.warn("Failed to load dependency itemstack for procedure RifleCondition!");
			return false;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		return (((itemstack).getOrCreateTag().getDouble("timeLeft")) == 0);
	}
}
