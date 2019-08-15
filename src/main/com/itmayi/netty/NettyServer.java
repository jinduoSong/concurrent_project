package itmayi.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title: </p>
 * <p>Description: 服务器端</p>
 *
 * @author songjd
 * @date 16:11 2019/7/6.
 */

class ServerHanlder extends SimpleChannelHandler {

    //通道被关闭的时候会被触发2222
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
        System.out.println("channelClosed");
    }

    //必须要建立连接 关闭通道的时候才会被触发
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        System.out.println("channelDisconnected");
    }

    //接收端出现异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        System.out.println("exceptionCaught");
    }

    //接受客户端数据
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);
        System.out.println("messageReceived");
        System.out.println("服务器端获取客户端发来的数据：" + e.getMessage());
    }
}


public class NettyServer {
    public static void main(String[] args) {
        //1、创建服务对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //2、创建两个线程池 一个用于监听端口 一个nio监听
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService week = Executors.newCachedThreadPool();
        //3、将线程池放入工程
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boos, week));
        //4、设置管道工程
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            //设置管道
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                //传输数据的时候设置为String类型
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("ecoder", new StringEncoder());
                //设置事件监听类
                pipeline.addLast("serverHanlder", new ServerHanlder());
                return pipeline;
            }
        });
        //绑定端口号
        serverBootstrap.bind(new InetSocketAddress(8080));
        System.out.println("服务器端已经启动");
    }
}
