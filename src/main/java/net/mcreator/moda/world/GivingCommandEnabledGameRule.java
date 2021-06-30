package net.mcreator.moda.world;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import net.mcreator.moda.ModaModElements;

import java.lang.reflect.Method;

@ModaModElements.ModElement.Tag
public class GivingCommandEnabledGameRule extends ModaModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.BooleanValue> gamerule = GameRules.register("givingCommandEnabled", GameRules.Category.CHAT,
			create(true));
	public GivingCommandEnabledGameRule(ModaModElements instance) {
		super(instance, 22);
	}

	public static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class);
			createGameruleMethod.setAccessible(true);
			return (GameRules.RuleType<GameRules.BooleanValue>) createGameruleMethod.invoke(null, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
