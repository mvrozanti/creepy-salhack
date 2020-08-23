package me.ionar.salhack.module.chat;

import me.ionar.salhack.events.player.EventPlayerSendChatMessage;
import me.ionar.salhack.managers.ModuleManager;
import me.ionar.salhack.module.Module;
import me.ionar.salhack.module.Value;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

public final class FancyChatModule extends Module
{
    public final Value<Modes> mode = new Value<Modes>("Mode", new String[] {"M"}, "The retard chat mode", Modes.Retard);

    public enum Modes
    {
        Retard,
        UwU,
    }

    public FancyChatModule()
    {
        super("FancyChat", new String[]
                        { "Retard" }, "Makes your chat fancy.", "NONE",
                0xDB2485, ModuleType.Chat);
    }

    @Override
    public String getMetaData()
    {
        return mode.getValue().toString();
    }


    //Right now this code is duplicated in MessageModifierModule, I need to fix that when I get better at java, but for now it works.
    @EventHandler
    private Listener<EventPlayerSendChatMessage> OnSendChatMsg = new Listener<>(p_Event ->
    {
        if(!getSuffix()) {
            if (p_Event.Message.startsWith("/"))
                return;

            String l_Message = p_Event.Message;

            switch (mode.getValue()) {
                case Retard: {
                    boolean l_Flag = false;

                    for (char l_Char : l_Message.toCharArray()) {
                        String l_Val = String.valueOf(l_Char);

                        l_Message += l_Flag ? l_Val.toUpperCase() : l_Val.toLowerCase();

                        if (l_Char != ' ')
                            l_Flag = !l_Flag;
                    }
                    break;
                }
                case UwU:
                    l_Message = l_Message.replace("l", "w").replace("ll", "ww");
            }

            p_Event.cancel();
        }
    });

    /*private static final int[][] FONT = {
            {
                    0xFF21, 0xFF22, 0xFF23, 0xFF24, 0xFF25, 0xFF26, 0xFF27, 0xFF28, 0xFF29, 0xFF2A, 0xFF2B,
                    0xFF2C, 0xFF2D, 0xFF2E, 0xFF2F, 0xFF30, 0xFF31, 0xFF32, 0xFF33, 0xFF34, 0xFF35, 0xFF36,
                    0xFF37, 0xFF38, 0xFF39, 0xFF3A, 0xFF41, 0xFF42, 0xFF43, 0xFF44, 0xFF45, 0xFF46, 0xFF47,
                    0xFF48, 0xFF49, 0xFF4A, 0xFF4B, 0xFF4C, 0xFF4D, 0xFF4E, 0xFF4F, 0xFF50, 0xFF51, 0xFF52,
                    0xFF53, 0xFF54, 0xFF55, 0xFF56, 0xFF57, 0xFF58, 0xFF59, 0xFF5A, 0xFF10, 0xFF11, 0xFF12,
                    0xFF13, 0xFF14, 0xFF15, 0xFF16, 0xFF17, 0xFF18, 0xFF19
            },
            { // Enclosed alphanumerics
                    0x24B6, 0x24B7, 0x24B8, 0x24B9, 0x24BA, 0x24BB, 0x24BC, 0x24BD, 0x24BE, 0x24BF, 0x24C0,
                    0x24C1, 0x24C2, 0x24C3, 0x24C4, 0x24C5, 0x24C6, 0x24C7, 0x24C8, 0x24C9, 0x24CA, 0x24CB,
                    0x24CC, 0x24CD, 0x24CE, 0x24CF, 0x24D0, 0x24D1, 0x24D2, 0x24D3, 0x24D4, 0x24D5, 0x24D6,
                    0x24D7, 0x24D8, 0x24D9, 0x24DA, 0x24DB, 0x24DC, 0x24DD, 0x24DE, 0x24DF, 0x24E0, 0x24E1,
                    0x24E2, 0x24E3, 0x24E4, 0x24E5, 0x24E6, 0x24E7, 0x24E8, 0x24E9, 0x24EA, 0x2460, 0x2461,
                    0x2462, 0x2463, 0x2464, 0x2465, 0x2466, 0x2467, 0x2468
            },
            { // Enclosed alphanumerics, no "zero"
                    0x249C, 0x249D, 0x249E, 0x249F, 0x24A0, 0x24A1, 0x24A2, 0x24A3, 0x24A4, 0x24A5, 0x24A6,
                    0x24A7, 0x24A8, 0x24A9, 0x24AA, 0x24AB, 0x24AC, 0x24AD, 0x24AE, 0x24AF, 0x24B0, 0x24B1,
                    0x24B2, 0x24B3, 0x24B4, 0x24B5, 0x249C, 0x249D, 0x249E, 0x249F, 0x24A0, 0x24A1, 0x24A2,
                    0x24A3, 0x24A4, 0x24A5, 0x24A6, 0x24A7, 0x24A8, 0x24A9, 0x24AA, 0x24AB, 0x24AC, 0x24AD,
                    0x24AE, 0x24AF, 0x24B0, 0x24B1, 0x24B2, 0x24B3, 0x24B4, 0x24B5, 0x0030, 0x2474, 0x2475,
                    0x2476, 0x2477, 0x2478, 0x2479, 0x2480, 0x2481, 0x2482
            },
            {
                    0x1D43, 0x1D47, 0x1D9C, 0x1D48, 0x1D49, 0x1DA0, 0x1D4D, 0x2B0, 0x1DA4, 0x2B2, 0x1D4F,
                    0x2E1,
                    0x1D50, 0x1DAF, 0x1D52, 0x1D56, 0x1DA3, 0x2B3, 0x2E2, 0x1D57, 0x1D58, 0x1D5B, 0x2B7,
                    0x2E3,
                    0x2B8, 0x1DBB, 0x1D43, 0x1D47, 0x1D9C, 0x1D48, 0x1D49, 0x1DA0, 0x1D4D, 0x2B0, 0x1DA4,
                    0x2B2,
                    0x1D4F, 0x2E1, 0x1D50, 0x1DAF, 0x1D52, 0x1D56, 0x1DA3, 0x2B3, 0x2E2, 0x1D57, 0x1D58,
                    0x1D5B,
                    0x2B7, 0x2E3, 0x2B8, 0x1DBB, 0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39
            }
    };*/

    private MessageModifierModule _suffixChat;

    @Override
    public void init()
    {
        _suffixChat = (MessageModifierModule) ModuleManager.Get().GetMod(MessageModifierModule.class);
    }

    public boolean getSuffix() {
        if(_suffixChat.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

}