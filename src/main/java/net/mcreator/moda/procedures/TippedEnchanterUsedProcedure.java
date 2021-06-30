package net.mcreator.moda.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.moda.enchantment.TippedEnchantment;
import net.mcreator.moda.ModaModElements;
import net.mcreator.moda.ModaMod;

import java.util.Map;

@ModaModElements.ModElement.Tag
public class TippedEnchanterUsedProcedure extends ModaModElements.ModElement {
	public TippedEnchanterUsedProcedure(ModaModElements instance) {
		super(instance, 35);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ModaMod.LOGGER.warn("Failed to load dependency entity for procedure TippedEnchanterUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double decider = 0;
		(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY))
				.addEnchantment(TippedEnchantment.enchantment, (int) 1);
		decider = (double) Math.round((Math.random() * 3));
		if (((decider) == 0)) {
			((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getOrCreateTag()
					.putString("tippedPotion", "poison");
		} else if (((decider) == 1)) {
			((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getOrCreateTag()
					.putString("tippedPotion", "weakness");
		} else if (((decider) == 2)) {
			((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getOrCreateTag()
					.putString("tippedPotion", "glowing");
		} else if (((decider) == 3)) {
			((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getOrCreateTag()
					.putString("tippedPotion", "blindness");
		}
	}
}
