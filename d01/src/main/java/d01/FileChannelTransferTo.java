package d01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTransferTo {
    public static void main(String[] args) {
        try (
                FileChannel from = new RandomAccessFile("d01/from.txt", "rw").getChannel();
                FileChannel to = new FileOutputStream("d01/to.txt").getChannel()
        ) {
            final ByteBuffer buffer = ByteBuffer.allocate(4);

            buffer.put(new byte[]{'a', 'b', 'c', 'd'});

            buffer.flip();


            from.write(buffer);


            long start = System.nanoTime();

            /*
            效率高，底层利用操作系统的零拷贝进行优化
             */

            from.transferTo(0, from.size(), to);

            long end = System.nanoTime();

            System.out.println("transferTo 用时：" + (end - start) / 1000_000.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}