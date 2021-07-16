
package net.mcreator.moda.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.client.util.ITooltipFlag;

import net.mcreator.moda.ModaModElements;

import java.util.List;

@ModaModElements.ModElement.Tag
public class EpicSwordItem extends ModaModElements.ModElement {
	@ObjectHolder("moda:epic_sword")
	public static final Item block = null;
	public EpicSwordItem(ModaModElements instance) {
		super(instance, 40);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 20000;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 10f;
			}

			public int getHarvestLevel() {
				return 5;
			}

			public int getEnchantability() {
				return 45;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.REDSTONE, (int) (1)), new ItemStack(Items.DIAMOND, (int) (1)));
			}
		}, 3, 3f, new Item.Properties().group(ItemGroup.TOOLS).isImmuneToFire()) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("Forged to handle the Hardest Enchantments,"));
				list.add(new StringTextComponent("this sword is attracted to Great Enchantments"));
			}
		}.setRegistryName("epic_sword"));
	}
}
