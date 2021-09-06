package d01;

import java.nio.ByteBuffer;

public class ByteBufferPacketHandle {
    public static void main(String[] args) {
         /*
         网络上有多条数据发送给服务端，数据之间使用 \n 进行分隔
         但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有 3 条为
             Hello,world\n
             I'm Zhang San\n
             How are you?\n
         变成了下面的两个 ByteBuffer (黏包，半包)
             Hello,world\nI'm Zhang San\nHo
             w are you?\n
         现在要求你编写程序，将错乱的数据恢复成原始的按 \n 分隔的数据
         */
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm Zhang San\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {

        source.flip();

        for (int i = 0; i < source.limit(); i++) {
            final byte b = source.get(i);

            if (b == '\n') {
                /*
                计算一条完整的消息长度
                 */
                int len = i + 1 - source.position();

                ByteBuffer target = ByteBuffer.allocate(len);

                /*
                从 source 读取数据写入到 target
                 */

                for (int j = 0; j < len; j++) {
                    target.put(source.get());
                }

                ByteBufferUtil.debugAll(target);
            }
        }

        source.compact();
    }
}
