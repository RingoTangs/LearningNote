package com.netty.demo.nio;


import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 * {@link ByteBuffer} 示例
 * <p>
 * put到 {@link ByteBuffer} 中是什么类型, get 时也要取出对应的类型,
 * 否则会抛出 {@link BufferUnderflowException}
 *
 * @author yang
 * @date 2021-11-17  14:46
 */
public class ByteBufferGetPutDemo {

    public static void main(String[] args) {
        ByteBuffer bf = ByteBuffer.allocate(1024);

        bf.putInt(100);
        bf.putLong(99);
        bf.putChar('a');
        bf.putShort(Short.parseShort("66"));

        bf.flip();

        System.out.println(bf.getInt());
        System.out.println(bf.getLong());
        System.out.println(bf.getChar());
        System.out.println(bf.getInt());
    }

}
