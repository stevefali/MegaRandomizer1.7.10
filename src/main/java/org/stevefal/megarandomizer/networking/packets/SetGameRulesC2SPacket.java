package org.stevefal.megarandomizer.networking.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import org.stevefal.megarandomizer.gamerules.MegaGameRules;
import org.stevefal.megarandomizer.networking.MegaMessages;

public class SetGameRulesC2SPacket implements IMessage {

    private boolean isDoBlockRandomDrops;
    private boolean isDoEntityRandomDrops;
    private boolean isDoPlayerRandomDrops;

    public SetGameRulesC2SPacket() {
    }

    public SetGameRulesC2SPacket(boolean isDoBlockRandomDrops, boolean isDoEntityRandomDrops, boolean isDoPlayerRandomDrops) {
        this.isDoBlockRandomDrops = isDoBlockRandomDrops;
        this.isDoEntityRandomDrops = isDoEntityRandomDrops;
        this.isDoPlayerRandomDrops = isDoPlayerRandomDrops;
    }


    @Override
    public void fromBytes(ByteBuf byteBuf) {
        this.isDoBlockRandomDrops = byteBuf.readBoolean();
        this.isDoEntityRandomDrops = byteBuf.readBoolean();
        this.isDoPlayerRandomDrops = byteBuf.readBoolean();

    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeBoolean(isDoBlockRandomDrops);
        byteBuf.writeBoolean(isDoEntityRandomDrops);
        byteBuf.writeBoolean(isDoPlayerRandomDrops);
    }

    public static class Handler implements IMessageHandler<SetGameRulesC2SPacket, IMessage> {

        @Override
        public IMessage onMessage(SetGameRulesC2SPacket setGameRulesC2SPacket, MessageContext messageContext) {
            World world = messageContext.getServerHandler().playerEntity.getEntityWorld();
            world.getGameRules().setOrCreateGameRule(MegaGameRules.RULE_DO_BLOCK_RANDOM_DROPS, String.valueOf(setGameRulesC2SPacket.isDoBlockRandomDrops));
            world.getGameRules().setOrCreateGameRule(MegaGameRules.RULE_DO_ENTITY_RANDOM_DROPS, String.valueOf(setGameRulesC2SPacket.isDoEntityRandomDrops));
            world.getGameRules().setOrCreateGameRule(MegaGameRules.RULE_DO_PLAYER_RANDOM_DROPS, String.valueOf(setGameRulesC2SPacket.isDoPlayerRandomDrops));

            MegaMessages.sendToPlayer(new GameRulesSyncS2CPacket(setGameRulesC2SPacket.isDoBlockRandomDrops,
                            setGameRulesC2SPacket.isDoEntityRandomDrops, setGameRulesC2SPacket.isDoPlayerRandomDrops),
                    messageContext.getServerHandler().playerEntity);

            return null;
        }
    }


}
