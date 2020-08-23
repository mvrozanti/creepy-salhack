package me.ionar.salhack.module.chat;

import me.ionar.salhack.events.player.EventPlayerSendChatMessage;
import me.ionar.salhack.module.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

public class AntiSameMessage extends Module {

    public AntiSameMessage()
    {
        super("AntiSameMessage", new String[]
                        { "ASM" }, "Adds period to end off message to prevent repeating messages.", "NONE",
                0xDB2485, Module.ModuleType.Chat);
    }


    @EventHandler
    private Listener<EventPlayerSendChatMessage> OnSendChatMsg = new Listener<>(p_Event ->
    {
        if (p_Event.Message.startsWith("/"))
            return;

        String l_Message = p_Event.Message;
        String oldMessage = "";
        boolean period = false;

        if (oldMessage == l_Message && period == false) {
            l_Message += ".";
            period = true;
        } else if (oldMessage == l_Message && period == true) {
            l_Message = l_Message.substring(0, l_Message.length() -2);
        }


        p_Event.cancel();
        if (l_Message.length() >= 256) {
            l_Message = l_Message.substring(0, 256);
        }
        mc.getConnection().sendPacket(new CPacketChatMessage(l_Message));
        oldMessage = l_Message;

    });
}
