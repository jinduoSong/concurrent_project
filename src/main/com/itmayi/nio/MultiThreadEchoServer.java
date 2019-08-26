package itmayi.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 15:21 2019/8/16.
 * 准备好了再通知我：网络NIO
 */
public class MultiThreadEchoServer {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static class HandleMsg implements Runnable {
        Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            BufferedReader bufferedReader = null;
            PrintWriter printWriter = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //从InputStream 当中读取客户端所发送的数据
                String inputLine = null;
                long millis = System.currentTimeMillis();
                while ((inputLine = bufferedReader.readLine()) != null) {
                    printWriter.println(inputLine);
                }
                long millis1 = System.currentTimeMillis();
                System.out.println("spend:" + (millis1 - millis) + "ms");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null) bufferedReader.close();
                    if (printWriter != null) printWriter.close();
                    clientSocket.close();
                } catch (Exception e) {

                }
            }
        }
    }

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(8000);
        } catch (Exception e) {
            System.out.println(e);
        }
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + "connect!");
                executorService.execute(new HandleMsg(clientSocket));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
