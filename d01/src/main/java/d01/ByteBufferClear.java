package d01;

import java.nio.ByteBuffer;

public class ByteBufferClear {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put(new byte[]{'a', 'b', 'c', 'd'});

        /*
        position: [4], limit: [10]。
         */
        ByteBufferUtil.debugAll(buffer);

        buffer.flip();

        buffer.get();
        buffer.get();
        buffer.get();

        /*
        position: [3], limit: [4]。
         */
        ByteBufferUtil.debugAll(buffer);

        /*
        清空并切换至写模式。
         */
        buffer.clear();

        /*
        position: [0], limit: [10]。
         */
        ByteBufferUtil.debugAll(buffer);
    }
}
