package myclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Arrays;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        var eventLoopGroup = new NioEventLoopGroup(4);
        var bootstrap = new Bootstrap()
                .group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientChannelInitializer());

        try {
            for (var line : Arrays.asList("5:hello", "4:cool", "6:haha", "stop")) {
                var channel = bootstrap.connect("localhost", 8080).sync().channel();
                channel.writeAndFlush(line);
                channel.closeFuture().sync();
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
