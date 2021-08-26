package d01;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferWrite {
    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);

        /*
        1. 调用 FileChannel 的 read() 方法，从 Channel 中读取出数据写入到 ByteBuffer 中。d01/rw.txt 内容为：12345 共 5 字节。
         */

        try (RandomAccessFile file = new RandomAccessFile("d01/rw.txt", "rw")) {
            final FileChannel channel = file.getChannel();

            /*
            该方法返回读取到的字节数，如果没有读取到数据则返回 -1。
             */
            final int readBytes = channel.read(buffer);

            /*
            输出 5。
             */
            System.out.println(readBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        position: [5], limit: [10]。
         */
        ByteBufferUtil.debugAll(buffer);

        /*
        2. 调用 ByteBuffer 自己的 put() 方法。
         */

        /*
        调用单个字节参数方法 写入 'a' 0x61。
         */
        buffer.put((byte) 0x61);

        /*
        position: [6], limit: [10]。
         */
        ByteBufferUtil.debugAll(buffer);

        /*
        调用字节数组参数方法 写入 'b' 0x62 'c' 0x63 'd' 0x64。
         */
        buffer.put(new byte[]{(byte) 0x62, (byte) 0x63, (byte) 0x64});

        /*
        position: [9], limit: [10]。
         */
        ByteBufferUtil.debugAll(buffer);
    }
}
