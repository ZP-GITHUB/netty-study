package com.zp.ark.server.socket;

/**
 * @author ZP
 * @date 2022/1/21.
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(MyServerHandler.class);

    /**
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        String channelId = channel.id().toString();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端。channelId：" + channelId);
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");

        //构建设备信息｛下位机、中继器、IO板卡｝
        Device device = new Device();
        device.setChannelId(channelId);
        device.setNumber(UUID.randomUUID().toString());
        device.setIp(channel.remoteAddress().getHostString());
        device.setPort(channel.remoteAddress().getPort());
        device.setConnectTime(new Date());
        //添加设备信息
        deviceGroup.put(channelId, device);
        CacheUtil.cacheClientChannel.put(channelId, channel);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object objMsgJsonStr) throws Exception {
        //接收设备发来信息
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息内容：" + objMsgJsonStr);

        //转发消息到Ws
        CacheUtil.wsChannelGroup.writeAndFlush(new TextWebSocketFrame(objMsgJsonStr.toString()));
    }

}
