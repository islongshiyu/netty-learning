package d01;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j(topic = "d01.FileChannelPosition")
public class FileChannelPosition {
    public static void main(String[] args) {
        /*
        创建一个不存在的 non-exist-file.txt 随机文件读写对象
         */
        try (FileChannel channel = new RandomAccessFile("d01/non-exist-file.txt", "rw").getChannel()) {
            long position = channel.position();

            if (log.isDebugEnabled()) {
                log.debug("当前位置为：{}", position);
            }

            ByteBuffer buffer = ByteBuffer.allocate(10);

            /*
            从 Channel 中读取数据写入到 Buffer 中，由于读取的是一个不存在的文件，因此读取到的字节数为 0
             */
            final int readBytes = channel.read(buffer);

            /*
            position: [0], limit: [10]
             */
            ByteBufferUtil.debugAll(buffer);

            if (log.isDebugEnabled()) {
                log.debug("读取字节数：{}", readBytes);
            }

            /*
            设置 Channel 当前位置为 1，这超出了文件末尾
             */
            channel.position(1);

            buffer.put(new byte[]{'a', 'b', 'c'});

            /*
            position: [3], limit: [10]
             */
            ByteBufferUtil.debugAll(buffer);

            buffer.flip();

            /*
            position: [0], limit: [3]
             */
            ByteBufferUtil.debugAll(buffer);

            /*
            从 Buffer 中读取数据并通过 Channel 写入到文件中，因此必须先切换至 Buffer 读模式
             */
            channel.write(buffer);

            /*
            position: [3], limit: [3]
             */
            ByteBufferUtil.debugAll(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}