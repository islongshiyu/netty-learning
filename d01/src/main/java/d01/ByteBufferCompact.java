package d01;

import java.nio.ByteBuffer;

public class ByteBufferCompact {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put(new byte[]{'a', 'b', 'c', 'd'});

        /*
        position: [4], limit: [10]。
         */
        ByteBufferUtil.debugAll(buffer);

        buffer.flip();

        /*
        'a'。
         */
        System.out.println((char) buffer.get());

        /*
        position: [1], limit: [4]。
         */
        ByteBufferUtil.debugAll(buffer);

        /*
        将未读完的部分向前压缩（这里只是变更 position 的值不做数据清空），并切换至写模式。
         */
        buffer.compact();

        /*
        position: [3], limit: [10]，此时内存中的 ByteBuffer 值为 [98, 99, 100, 100, 0, 0, 0, 0, 0, 0]。
         */
        ByteBufferUtil.debugAll(buffer);
    }
}
