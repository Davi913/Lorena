package com.davizin.anticheat.packets;

import com.davizin.anticheat.core.Check;
import com.davizin.anticheat.models.data.PlayerData;
import io.netty.channel.*;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketHandler {

    public void inject(PlayerData player) {
        Player target = Bukkit.getPlayer(player.id);
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {

            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {

                for (Check check : player.checks) {

                    check.check(player, (Packet<?>) packet);
                }

                Bukkit.getServer().getConsoleSender().sendMessage("§3PACKET READ: §f" + packet.toString());
                super.channelRead(channelHandlerContext, packet);
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {

                for (Check check : player.checks) {

                    check.check(player, (Packet<?>) packet);
                }

                super.write(channelHandlerContext, packet, channelPromise);
            }

        };

        ChannelPipeline pipeline = ((CraftPlayer) target).getHandle().playerConnection.networkManager.channel.pipeline();

        pipeline.addBefore("packet_handler", target.getName(), channelDuplexHandler);

    }

    public void remove(PlayerData player) {

        Player target = Bukkit.getPlayer(player.id);

        Channel channel = ((CraftPlayer) target).getHandle().playerConnection.networkManager.channel;

        channel.eventLoop().submit(() -> {

            channel.pipeline().remove(target.getName());
            return null;
        });
    }

}
