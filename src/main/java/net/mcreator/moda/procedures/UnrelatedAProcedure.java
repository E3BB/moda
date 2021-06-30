package net.mcreator.moda.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.moda.ModaModElements;
import net.mcreator.moda.ModaMod;

import java.util.Map;
import java.util.HashMap;

@ModaModElements.ModElement.Tag
public class UnrelatedAProcedure extends ModaModElements.ModElement {
	public UnrelatedAProcedure(ModaModElements instance) {
		super(instance, 43);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ModaMod.LOGGER.warn("Failed to load dependency entity for procedure UnrelatedA!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				ModaMod.LOGGER.warn("Failed to load dependency sourceentity for procedure UnrelatedA!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(((sourceentity) + "" + (" has set you for Attack"))), (true));
		}
		if (sourceentity instanceof PlayerEntity && !sourceentity.world.isRemote()) {
			((PlayerEntity) sourceentity).sendStatusMessage(new StringTextComponent((("You have set ") + "" + (entity) + "" + ("for Attack"))),
					(true));
		}
	}

	@SubscribeEvent
	public void onEntitySetsAttackTarget(LivingSetAttackTargetEvent event) {
		LivingEntity entity = event.getTarget();
		LivingEntity sourceentity = event.getEntityLiving();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", sourceentity.getPosX());
		dependencies.put("y", sourceentity.getPosY());
		dependencies.put("z", sourceentity.getPosZ());
		dependencies.put("world", sourceentity.getEntityWorld());
		dependencies.put("entity", entity);
		dependencies.put("sourceentity", sourceentity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
