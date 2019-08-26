package itmayi.memory;

import java.text.DecimalFormat;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 19:17 2019/8/13.
 * ���ڴ���Ų���
 */
public class MemoryDemo {

    public static void main(String[] args) throws InterruptedException {
        byte[] bytes01 = new byte[1 * 1024 * 1024];
        System.out.println("������1M�ڴ�");
        jvmInfo();
        Thread.sleep(3000);
        byte[] bytes02 = new byte[4 * 1024 * 1024];
        System.out.println("������4M�ڴ�");
        jvmInfo();
    }

    public static String toM(long mavMemory) {
        float num = (float) mavMemory / (1024 * 1024);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(num);
    }

    public static void jvmInfo() {
        //����ڴ�������Ϣ
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("MaxMemory:" + toM(maxMemory) + "M");
        //�����ڴ�
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("freeMemory:" + toM(freeMemory) + "M");
        //��ʹ���ڴ�
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory:" + toM(totalMemory) + "M");
    }
}
