package me.ionar.salhack.module.chat;

import me.ionar.salhack.events.player.EventPlayerJoin;
import me.ionar.salhack.events.player.EventPlayerLeave;
import me.ionar.salhack.module.Module;
import me.zero.alpine.fork.listener.Listener;

public class JoinMessage extends Module {
    public JoinMessage()
    {
        super("JoinMessage", new String[]
                        { "JoinMessage" }, "Sends a mesasge to chat when you join.", "NONE",
                0xDB2485, Module.ModuleType.Chat);
    }

    private Listener<EventPlayerJoin> OnJoin = new Listener<>(p_Event -> {
        if(mc.player != null) {
            mc.player.sendChatMessage("test");
        }
    });

}
