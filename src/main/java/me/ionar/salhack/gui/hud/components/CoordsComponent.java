package me.ionar.salhack.gui.hud.components;

import java.text.DecimalFormat;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.ionar.salhack.SalHackMod;
import me.ionar.salhack.events.player.EventPlayerUpdateMoveState;
import me.ionar.salhack.gui.hud.HudComponentItem;
import me.ionar.salhack.main.Wrapper;
import me.ionar.salhack.managers.ModuleManager;
import me.ionar.salhack.module.Value;
import me.ionar.salhack.module.world.AutoTunnelModule;
import me.ionar.salhack.module.world.CoordsSpooferModule;
import me.ionar.salhack.util.render.RenderUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;

public class CoordsComponent extends HudComponentItem
{
    public final Value<Boolean> NetherCoords = new Value<Boolean>("NetherCoords", new String[]
    { "NC" }, "Displays nether coords.", true);
    public final Value<Modes> Mode = new Value<Modes>("Mode", new String[]
    { "Mode" }, "Mode of displaying coordinates", Modes.Inline);

    public enum Modes
    {
        Inline, NextLine,
    }

    final DecimalFormat Formatter = new DecimalFormat("#.#");

    public CoordsComponent()
    {
        super("Coords", 2, 245);
    }

    public String format(double p_Input)
    {
        String l_Result = Formatter.format(p_Input);

        if (!l_Result.contains("."))
            l_Result += ".0";

        return l_Result;
    }

    @Override
    public void render(int p_MouseX, int p_MouseY, float p_PartialTicks)
    {
        super.render(p_MouseX, p_MouseY, p_PartialTicks);

        switch (Mode.getValue())
        {
            case Inline:
                String l_Coords = String.format("%sXYZ %s%s, %s, %s", ChatFormatting.GRAY, ChatFormatting.WHITE,
                        format(getX()), format(mc.player.posY), format(getZ()));

                if (NetherCoords.getValue())
                {
                    l_Coords += String.format(" %s[%s%s, %s%s]", ChatFormatting.GRAY, ChatFormatting.WHITE,
                            mc.player.dimension != -1 ? format(getX() / 8) : format(getX() * 8),
                            mc.player.dimension != -1 ? format(getZ() / 8) : format(getZ() * 8),
                            ChatFormatting.GRAY);
                }
                SetWidth(RenderUtil.getStringWidth(l_Coords));
                SetHeight(RenderUtil.getStringHeight(l_Coords));

                RenderUtil.drawStringWithShadow(l_Coords, GetX(), GetY(), -1);

                break;
            case NextLine:
                String l_X = String.format("%sX: %s%s [%s]", ChatFormatting.GRAY, ChatFormatting.WHITE, format(getX()), NetherCoords.getValue() ? mc.player.dimension != -1 ? format(getX() / 8) : format(getX() * 8) : "");
                String l_Y = String.format("%sY: %s%s [%s]", ChatFormatting.GRAY, ChatFormatting.WHITE, format(mc.player.posY), NetherCoords.getValue() ? format(mc.player.posY) : "");
                String l_Z = String.format("%sZ: %s%s [%s]", ChatFormatting.GRAY, ChatFormatting.WHITE, format(getZ()), NetherCoords.getValue() ? mc.player.dimension != -1 ? format(getZ() / 8) : format(getZ() * 8) : "");
                RenderUtil.drawStringWithShadow(l_X, GetX(), GetY(), -1);
                RenderUtil.drawStringWithShadow(l_Y, GetX(), GetY()+RenderUtil.getStringHeight(l_X), -1);
                RenderUtil.drawStringWithShadow(l_Z, GetX(), GetY()+RenderUtil.getStringHeight(l_X)+RenderUtil.getStringHeight(l_Y), -1);

                SetWidth(RenderUtil.getStringWidth(l_X));
                SetHeight(RenderUtil.getStringHeight(l_X)*3);
                break;
            default:
                break;
        }

    }

    private CoordsSpooferModule _getCoords = (CoordsSpooferModule) ModuleManager.Get().GetMod(CoordsSpooferModule.class) ;

    private Boolean getCoordSpoofer() {
        return _getCoords.isEnabled();
    }

    private int randX() {
        int i = (int)(Math.random() * 2) +1;
        if (i==1) {
            i = (int)((Math.random() * 30000000) + 0) * -1;
        } else {
            i = (int)((Math.random() * 30000000) + 0);
        }
        return i;
    }

    private int randZ() {
        int i = (int)(Math.random() * 2) +1;
        if (i==1) {
            i = (int)((Math.random() * 30000000) + 0) * -1;
        } else {
            i = (int)((Math.random() * 30000000) + 0);
        }
        return i;
    }

    private double getX() {
        if(getCoordSpoofer()) {
            return mc.player.posX + randX();
        }
        return mc.player.posX;
    }

    private double getZ() {
        if(getCoordSpoofer()) {
            return mc.player.posZ + randZ();
        }
        return mc.player.posZ;
    }

}
