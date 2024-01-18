package poney.fs.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import poney.fs.FancySmelting;

public class FurnaceScreen extends HandledScreen<FurnaceScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(FancySmelting.ID, "textures/gui/furnace_fs.png");

    public FurnaceScreen(FurnaceScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderTitle(context,x,y);
        renderProgressArrow(context, x, y);
        renderFuel(context,x,y);
    }

    private void renderTitle(DrawContext context, int x,int y){
        Text customTitle = handler.getTitle();
        int titleX = x + (this.backgroundWidth - this.textRenderer.getWidth(title)) / 2;
        context.drawTextWithShadow(textRenderer,title,titleX,y+ 5,0xffffff);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 80, y + 35, 176, 0,  handler.getScaledProgress(),15);
        }
    }

    private void renderFuel(DrawContext context,int x,int y){
        int scaled = this.handler.getScaledFuel();
        int differenceScale = 58 - scaled;
        int yOffset = 15 + differenceScale;  // Ajuste a posição vertical da chama com base na diferença de escala

        context.drawTexture(TEXTURE, x + 20, y + 10 + differenceScale, 176, yOffset, 14, scaled);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
