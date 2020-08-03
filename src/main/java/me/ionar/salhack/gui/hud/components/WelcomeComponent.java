package me.ionar.salhack.gui.hud.components;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.ionar.salhack.SalHackMod;
import me.ionar.salhack.gui.hud.HudComponentItem;
import me.ionar.salhack.main.Wrapper;
import me.ionar.salhack.module.Value;
import me.ionar.salhack.util.render.RenderUtil;
import me.ionar.salhack.gui.hud.HudComponentItem;
import me.ionar.salhack.managers.ImageManager;
import me.ionar.salhack.managers.TickRateManager;
import me.ionar.salhack.util.imgs.SalDynamicTexture;
import me.ionar.salhack.util.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;


public class WelcomeComponent extends HudComponentItem
{
    public final Value<Boolean> Reliant = new Value<Boolean>("Reliant", new String[]
            { "" }, "Shows reliant text instead of salhack", false);

    private String WelcomeString = "Welcome " + ChatFormatting.WHITE + mc.getSession().getUsername();

    public WelcomeComponent()
    {
        super("Welcome", 5, 5);
        SetHidden(false);
    }



    @Override
    public void render(int p_MouseX, int p_MouseY, float p_PartialTicks)
    {
        super.render(p_MouseX, p_MouseY, p_PartialTicks);

        if (Reliant.getValue())
        {
            final String l_Text = "Reliant (rel-1.12.2-Forge)";

            Wrapper.GetMC().fontRenderer.drawStringWithShadow(l_Text, GetX(), GetY(), 0xFFFFFF);

            SetWidth(Wrapper.GetMC().fontRenderer.getStringWidth(l_Text));
            SetHeight(Wrapper.GetMC().fontRenderer.FONT_HEIGHT);
        }
        else
        {
            RenderUtil.drawStringWithShadow(WelcomeString, GetX(), GetY(), 0x2ACCED);

            SetWidth(RenderUtil.getStringWidth(WelcomeString));
            SetHeight(RenderUtil.getStringHeight(WelcomeString));
        }
    }
}
