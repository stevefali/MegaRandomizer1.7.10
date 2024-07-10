package org.stevefal.megarandomizer.networking.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.GameRules;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;
import org.stevefal.megarandomizer.networking.MegaMessages;

public class RequestGameRulesSyncC2SPacket implements IMessage {

 public RequestGameRulesSyncC2SPacket() {}

    @Override
    public void fromBytes(ByteBuf byteBuf) {

    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

    }

    public static class Handler implements IMessageHandler<RequestGameRulesSyncC2SPacket, IMessage> {
        @Override
        public IMessage onMessage(RequestGameRulesSyncC2SPacket requestGameRulesSyncC2SPacket, MessageContext messageContext) {
            GameRules gameRules = messageContext.getServerHandler().playerEntity.getEntityWorld().getGameRules();
            MegaMessages.sendToPlayer(new GameRulesSyncS2CPacket(gameRules.getGameRuleBooleanValue(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS),
                    gameRules.getGameRuleBooleanValue(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS),
                    gameRules.getGameRuleBooleanValue(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS)), messageContext.getServerHandler().playerEntity);
            return null;
        }
    }
}
