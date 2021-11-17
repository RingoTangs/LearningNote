package com.netty.demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * {@link MappedByteBuffer} 示例
 * <p>
 * 可以让文件直接在内存(堆外内存)修改, 操作系统不需要再拷贝一次
 *
 * @author yang
 * @date 2021-11-17  15:13
 */
public class MappedByteBufferDemo {

    public static void main(String[] args) throws IOException {

        // 所谓动态读取是指从文件的任意位置开始访问文件，而不是必须从文件开始位置读取到文件末尾。
        RandomAccessFile raf = new RandomAccessFile("file01.txt", "rw");
        FileChannel channel = raf.getChannel();

        /*
         * mode: FileChannel.MapMode.READ_WRITE 使用的模式
         * position: 可以直接修改内存的起始位置
         * size：映射到内存的大小。即, 将 "file01.txt" 的多个字节映射到内存; 可以直接修改的范围是[0, 5)
         */
        MappedByteBuffer mbf = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        byte[] bytes = "张".getBytes();

        // 这样就可以直接修改文件了
        mbf.put(bytes, 0, bytes.length);

        channel.close();
        raf.close();
    }

}
