package d01;

import java.nio.ByteBuffer;

public class ByteBufferMarkReset {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put(new byte[]{'a', 'b', 'c', 'd'});

        buffer.flip();

        buffer.get();
        buffer.get();

        /*
        position: [2], limit: [4]。
         */
        ByteBufferUtil.debugAll(buffer);

        /*
        在 position[2] 做标记
         */
        buffer.mark();

        buffer.get();
        buffer.get();

        /*
        回退到 position[2]
         */
        buffer.reset();

        /*
        position: [2], limit: [4]。
         */
        ByteBufferUtil.debugAll(buffer);
    }
}
