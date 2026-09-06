package org.stevefal.megarandomizer.networking.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;

public class GameRulesSyncS2CPacket implements IMessage {

    private boolean isDoBlockRandomDrops;
    private boolean isDoEntityRandomDrops;
    private boolean isDoPlayerRandomDrops;
    private boolean isDoVolatileDrops;
    public GameRulesSyncS2CPacket(){}

    public GameRulesSyncS2CPacket(boolean isDoBlocks, boolean isDoEntities, boolean isDoPlayer, boolean isDoVolatile) {
        this.isDoBlockRandomDrops = isDoBlocks;
        this.isDoEntityRandomDrops = isDoEntities;
        this.isDoPlayerRandomDrops = isDoPlayer;
        this.isDoVolatileDrops = isDoVolatile;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        this.isDoBlockRandomDrops = byteBuf.readBoolean();
        this.isDoEntityRandomDrops = byteBuf.readBoolean();
        this.isDoPlayerRandomDrops = byteBuf.readBoolean();
        this.isDoVolatileDrops = byteBuf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeBoolean(isDoBlockRandomDrops);
        byteBuf.writeBoolean(isDoEntityRandomDrops);
        byteBuf.writeBoolean(isDoPlayerRandomDrops);
        byteBuf.writeBoolean(isDoVolatileDrops);
    }

    public static class Handler implements IMessageHandler<GameRulesSyncS2CPacket, IMessage> {


        @Override
        public IMessage onMessage(GameRulesSyncS2CPacket gameRulesSyncS2CPacket, MessageContext messageContext) {
            WorldClient worldClient = Minecraft.getMinecraft().theWorld;
            worldClient.getGameRules().setOrCreateGameRule(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS, String.valueOf(gameRulesSyncS2CPacket.isDoBlockRandomDrops));
            worldClient.getGameRules().setOrCreateGameRule(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS, String.valueOf(gameRulesSyncS2CPacket.isDoEntityRandomDrops));
            worldClient.getGameRules().setOrCreateGameRule(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS, String.valueOf(gameRulesSyncS2CPacket.isDoPlayerRandomDrops));
            worldClient.getGameRules().setOrCreateGameRule(MegaGameRules.RULE_DO_VOLATILE_DROPS, String.valueOf(gameRulesSyncS2CPacket.isDoVolatileDrops));


            return null;
        }
    }

}
