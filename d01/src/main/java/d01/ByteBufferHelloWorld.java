package d01;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j(topic = "d01.ByteBufferHelloWorld")
public class ByteBufferHelloWorld {
    public static void main(String[] args) {

        /*
        获取Channel的方式：1. 输入输出流， 2. RandomAccessFile
         */
        try (FileChannel channel = new FileInputStream("d01/data.txt").getChannel()) {

            /*
            申请10个字节的缓存区
             */
            final ByteBuffer buffer = ByteBuffer.allocate(10);

            while (true) {
                /*
                从channel中读取数据并写入到buffer中 每次读取10个字节 此时为写模式
                读取完毕返回-1
                */

                final int length = channel.read(buffer);

                log.debug("读取到的字节数为：{}", length);

                if (-1 == length) {
                    break;
                }

                /*
                切换至读模式
                 */
                buffer.flip();

                while (buffer.hasRemaining()) {
                    final byte b = buffer.get();

                    log.debug("读取到的字节值为：{}", (char) b);
                }

                /*
                切换至写模式
                 */
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
