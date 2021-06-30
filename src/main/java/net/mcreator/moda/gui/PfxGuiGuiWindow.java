
package net.mcreator.moda.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.moda.ModaMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class PfxGuiGuiWindow extends ContainerScreen<PfxGuiGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	TextFieldWidget potion_id;
	public PfxGuiGuiWindow(PfxGuiGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("moda:textures/pfx_gui.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		potion_id.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (potion_id.isFocused())
			return potion_id.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		potion_id.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Potion Effects Maker", 37, 12, -13421773);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		potion_id = new TextFieldWidget(this.font, this.guiLeft + 27, this.guiTop + 67, 120, 20, new StringTextComponent("Type in Potion Id")) {
			{
				setSuggestion("Type in Potion Id");
			}
			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion("Type in Potion Id");
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion("Type in Potion Id");
				else
					setSuggestion(null);
			}
		};
		PfxGuiGui.guistate.put("text:potion_id", potion_id);
		potion_id.setMaxStringLength(32767);
		this.children.add(this.potion_id);
		this.addButton(new Button(this.guiLeft + 45, this.guiTop + 102, 85, 20, new StringTextComponent("Apply Effect"), e -> {
			if (true) {
				ModaMod.PACKET_HANDLER.sendToServer(new PfxGuiGui.ButtonPressedMessage(0, x, y, z));
				PfxGuiGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 32, this.guiTop + 134, 110, 20, new StringTextComponent("Clear All Effects"), e -> {
			if (true) {
				ModaMod.PACKET_HANDLER.sendToServer(new PfxGuiGui.ButtonPressedMessage(1, x, y, z));
				PfxGuiGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 45, this.guiTop + 39, 85, 20, new StringTextComponent("Clear Effect"), e -> {
			if (true) {
				ModaMod.PACKET_HANDLER.sendToServer(new PfxGuiGui.ButtonPressedMessage(2, x, y, z));
				PfxGuiGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
	}
}
