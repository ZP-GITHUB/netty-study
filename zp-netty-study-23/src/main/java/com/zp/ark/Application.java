package com.zp.ark;

/**
 * @author ZP
 * @date 2022/1/21.
 */
@SpringBootApplication
@ComponentScan("org.itstack.demo.ark")
public class Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(Application.class);

    @Value("${netty.socket.port}")
    private int nettyServerPort;
    @Value("${netty.websocket.port}")
    private int nettyWsServerPort;
    //默认线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //启动NettyServer-socket
        logger.info("启动NettyServer服务，启动端口：{}", nettyServerPort);
        NettyServer nettyServer = new NettyServer(new InetSocketAddress(nettyServerPort));
        Future<Channel> future = executorService.submit(nettyServer);
        Channel channel = future.get();
        if (null == channel) {
            throw new RuntimeException("netty server-s open error channel is null");
        }
        while (!channel.isActive()) {
            logger.info("启动NettyServer服务，循环等待启动...");
            Thread.sleep(500);
        }
        logger.info("启动NettyServer服务，完成：{}", channel.localAddress());
        CacheUtil.serverInfoMap.put(nettyServerPort, new ServerInfo("NettySocket", NetUtil.getHost(), nettyServerPort, new Date()));

        //启动NettyServer-websocket
        logger.info("启动NettyWsServer服务，启动端口：{}", nettyWsServerPort);
        WsNettyServer wsNettyServer = new WsNettyServer(new InetSocketAddress(nettyWsServerPort));
        Future<Channel> wsFuture = executorService.submit(wsNettyServer);
        Channel wsChannel = wsFuture.get();
        if (null == wsChannel) {
            throw new RuntimeException("netty server-ws open error channel is null");
        }
        while (!wsChannel.isActive()) {
            logger.info("启动NettyWsServer服务，循环等待启动...");
            Thread.sleep(500);
        }
        logger.info("启动NettyWsServer服务，完成：{}", wsChannel.localAddress());
        CacheUtil.serverInfoMap.put(nettyServerPort, new ServerInfo("NettyWsSocket", NetUtil.getHost(), nettyServerPort, new Date()));
    }

}
