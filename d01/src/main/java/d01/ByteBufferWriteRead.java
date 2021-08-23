package d01;

import java.nio.ByteBuffer;

public class ByteBufferWriteRead {
    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);

        /*
        调用单个字节参数方法 写入 'a' 0x61
         */
        buffer.put((byte) 0x61);

        ByteBufferUtil.debugAll(buffer);

        /*
        调用字节数组参数方法 写入 'b' 0x62 'c' 0x63 'd' 0x64
         */
        buffer.put(new byte[]{(byte) 0x62, (byte) 0x63, (byte) 0x64});

        ByteBufferUtil.debugAll(buffer);

        buffer.flip();

        ByteBufferUtil.debugAll(buffer);

        /*
        将读取position[0]的数据 'a'
         */
        System.out.println((char) buffer.get());

        ByteBufferUtil.debugAll(buffer);

        /*
        将未读完的部分向前压缩（这里只是变更 buffer 属性值不做数据清空），然后切换至写模式
         */
        buffer.compact();

        ByteBufferUtil.debugAll(buffer);

        /*
        如果不切换至读模式，直接调用读取方法无法正确读取数据
        下面的代码输出结果为0，此时下面的代码调用 get() 将获取 position[3]的数据，由于上面的代码调用 compact后 position[3]位置数据未被清空，下面的代码将输出 0x64 100
         */

        System.out.println(buffer.get());

        /*
        并且读取完后 position +1
         */
        ByteBufferUtil.debugAll(buffer);
    }
}
