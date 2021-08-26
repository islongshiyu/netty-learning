package d01;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class ByteBufferGatheringWrites {
    public static void main(String[] args) {

        try (FileChannel channel = new RandomAccessFile("d01/words_zh_CN.txt", "rw").getChannel()) {

            final ByteBuffer buf1 = StandardCharsets.UTF_8.encode("你好");
            final ByteBuffer buf2 = StandardCharsets.UTF_8.encode("小");
            final ByteBuffer buf3 = StandardCharsets.UTF_8.encode("苹果");

            channel.write(new ByteBuffer[]{buf1, buf2, buf3});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
