package d01;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferScatteringReads {
    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile("d01/words.txt", "r").getChannel()) {
            ByteBuffer buf1 = ByteBuffer.allocate(3);
            ByteBuffer buf2 = ByteBuffer.allocate(3);
            ByteBuffer buf3 = ByteBuffer.allocate(5);

            final ByteBuffer[] buffers = {buf1, buf2, buf3};

            /*
            传入 ByteBuffer[] 数组，通过 FileChannel 将文件中的数据分散到各个 ByteBuffer 中。
             */
            channel.read(buffers);

            for (ByteBuffer b : buffers) {
                b.flip();

                ByteBufferUtil.debugAll(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
