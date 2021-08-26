package d01;

import java.nio.ByteBuffer;

public class ByteBufferRewind {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put(new byte[]{'a', 'b', 'c', 'd'});

        /*
        position: [4], limit: [10]。
         */
        ByteBufferUtil.debugAll(buffer);

        buffer.flip();

        /*
        position: [0], limit: [4]。
         */
        ByteBufferUtil.debugAll(buffer);

        buffer.get();
        buffer.get();

        /*
        position: [2], limit: [4]。
         */
        ByteBufferUtil.debugAll(buffer);

        /*
        将 position 置为 0，便于重新读取数据。
         */
        buffer.rewind();

        /*
        position: [0], limit: [4]。
         */
        ByteBufferUtil.debugAll(buffer);
    }
}
