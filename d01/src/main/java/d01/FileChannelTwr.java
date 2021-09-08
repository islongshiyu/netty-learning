package d01;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTwr {
    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile("d01/non-exist-file.txt", "rw").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);

            channel.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}