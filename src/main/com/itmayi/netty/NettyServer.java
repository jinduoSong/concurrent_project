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
 * <p>Description: ��������</p>
 *
 * @author songjd
 * @date 16:11 2019/7/6.
 */

class ServerHanlder extends SimpleChannelHandler {

    //ͨ�����رյ�ʱ��ᱻ����2222
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
        System.out.println("channelClosed");
    }

    //����Ҫ�������� �ر�ͨ����ʱ��Żᱻ����
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        System.out.println("channelDisconnected");
    }

    //���ն˳����쳣
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        System.out.println("exceptionCaught");
    }

    //���ܿͻ�������
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);
        System.out.println("messageReceived");
        System.out.println("�������˻�ȡ�ͻ��˷��������ݣ�" + e.getMessage());
    }
}


public class NettyServer {
    public static void main(String[] args) {
        //1�������������
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //2�����������̳߳� һ�����ڼ����˿� һ��nio����
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService week = Executors.newCachedThreadPool();
        //3�����̳߳ط��빤��
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boos, week));
        //4�����ùܵ�����
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            //���ùܵ�
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                //�������ݵ�ʱ������ΪString����
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("ecoder", new StringEncoder());
                //�����¼�������
                pipeline.addLast("serverHanlder", new ServerHanlder());
                return pipeline;
            }
        });
        //�󶨶˿ں�
        serverBootstrap.bind(new InetSocketAddress(8080));
        System.out.println("���������Ѿ�����");
    }
}
