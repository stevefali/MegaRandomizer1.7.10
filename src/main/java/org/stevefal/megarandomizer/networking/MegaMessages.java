package org.stevefal.megarandomizer.networking;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;
import org.stevefal.megarandomizer.MegaRandomizer1_7_10;
import org.stevefal.megarandomizer.networking.packets.GameRulesSyncS2CPacket;
import org.stevefal.megarandomizer.networking.packets.RequestGameRulesSyncC2SPacket;
import org.stevefal.megarandomizer.networking.packets.SetGameRulesC2SPacket;

public class MegaMessages {

    private static SimpleNetworkWrapper INSTANCE;
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleNetworkWrapper netReg = NetworkRegistry.INSTANCE.newSimpleChannel(MegaRandomizer1_7_10.MODID);
        INSTANCE = netReg;

        /* To Server */
        netReg.registerMessage(SetGameRulesC2SPacket.Handler.class, SetGameRulesC2SPacket.class, id(), Side.SERVER);
        netReg.registerMessage(RequestGameRulesSyncC2SPacket.Handler.class, RequestGameRulesSyncC2SPacket.class, id(), Side.SERVER);


        /* To Client */
        netReg.registerMessage(GameRulesSyncS2CPacket.Handler.class, GameRulesSyncS2CPacket.class, id(), Side.CLIENT);
    }

    public static void sendToServer(IMessage message) {
        INSTANCE.sendToServer(message);
    }

    public static void sendToPlayer(IMessage message, EntityPlayerMP player) {
        INSTANCE.sendTo(message, player);
    }

}
