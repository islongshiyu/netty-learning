package d01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put(new byte[]{'c', 'd', 'e', 'f'});

        buffer.flip();

        /*
        这里必须创建一个输出流，数据的走向是从 ByteBuffer 中读取数据，通过 FileChannel 写入到文件中，因此也必须在做下面的操作前切换至读模式。
         */
        try (FileChannel channel = new FileOutputStream("d01/r.txt").getChannel()) {
            /*
            1. 调用 Channel 的 write() 方法，从 ByteBuffer 中读取出数据写入到 FileChannel 中。
             */
            final int writeBytes = channel.write(buffer);

            /*
            4，共从 Buffer 中读取了 4个字节的数据写入到文件 d01/r.txt 中。
             */
            System.out.println(writeBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        position: [4], limit: [4]。
         */
        ByteBufferUtil.debugAll(buffer);

        /*
        2. 调用 ByteBuffer 自己的 get() 方法。
         */

        ByteBuffer buf = ByteBuffer.allocate(10);

        buf.put(new byte[]{'c', 'd', 'e', 'f'});

        buf.flip();

        /*
        调用无参 get() 方法会使 position + 1。
         */
        buf.get();
        buf.get();

        /*
        position: [2], limit: [4]。
         */
        ByteBufferUtil.debugAll(buf);

        /*
        调用有参 get(int i) 方法只会获取对应位置的数据，position 的值不变，下面的代码输出 'e'。
         */

        System.out.println((char) buf.get(2));

        /*
        position: [2], limit: [4]。
         */
        ByteBufferUtil.debugAll(buf);
    }
}
