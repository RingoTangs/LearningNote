package com.netty.demo.nio;

import java.nio.ByteBuffer;

/**
 * {@link ByteBuffer} 转为 readonly
 *
 * @author yang
 * @date 2021-11-17  15:01
 */
public class ReadonlyBuffer {
    public static void main(String[] args) {
        ByteBuffer bf = ByteBuffer.allocate(1024);
        for (int i = 129; i < 300; ++i) {
            bf.put(Integer.valueOf(i).byteValue());
        }

        bf.flip();

        ByteBuffer roBf = bf.asReadOnlyBuffer();
        while (roBf.hasRemaining()) {
            System.out.println(roBf.get());
        }

    }
}
