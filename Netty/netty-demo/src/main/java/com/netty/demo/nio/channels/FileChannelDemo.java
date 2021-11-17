package com.netty.demo.nio.channels;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * {@link FileChannel} 使用示例
 *
 * @author yang
 * @date 2021-11-17  10:48
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
//        writeFile2Disk();
//        readFromFile();
//        copyFile();
        copyFileByTransfer();
    }


    /**
     * 文件拷贝, 通过 {@link FileChannel#transferTo(long, long, WritableByteChannel)}
     */
    public static void copyFileByTransfer() throws IOException {
        // 1: 创建文件输入流
        FileInputStream fis = new FileInputStream("file01.txt");
        // 2: 通过 FileInputStream 创建对应的 FileChannel
        FileChannel fisFileChannel = fis.getChannel();

        // 3: 创建文件输出流
        FileOutputStream fos = new FileOutputStream("file01-copy.txt");
        // 4: 创建 FileOutputStream 对应的 FileChannel
        FileChannel fosFileChannel = fos.getChannel();
        // 5: 将 fisFileChannel 的数据转移到 fosFileChannel
        System.out.println(fisFileChannel.transferTo(0, fisFileChannel.size(), fosFileChannel));

        fosFileChannel.close();
        fisFileChannel.close();
        fos.close();
        fis.close();
    }

    /**
     * 文件拷贝
     */
    public static void copyFile() throws IOException {
        final int capacity = 4;

        // 1: 创建文件输入流
        FileInputStream fis = new FileInputStream("file01.txt");
        // 2: 通过 FileInputStream 创建对应的 FileChannel
        FileChannel fisFileChannel = fis.getChannel();
        // 3: 创建一个缓冲区 ByteBuffer
        ByteBuffer bf = ByteBuffer.allocate(capacity);

        // 4: 创建文件输出流
        FileOutputStream fos = new FileOutputStream("file01-copy.txt");
        // 5: 创建 FileOutputStream 对应的 FileChannel
        FileChannel fosFileChannel = fos.getChannel();

        int pos = 0;

        // 6: FileChannel 数据写入到 ByteBuffer
        while (fisFileChannel.read(bf, pos) != -1) {
            // 5: 对 ByteBuffer 进行翻转 ==> 缓冲区由 write 切换为 read
            bf.flip();
            // 8: 将 ByteBuffer 的内容写入到 FileChannel
            fosFileChannel.write(bf);

            pos += capacity;
            bf.clear();
        }

        fosFileChannel.close();
        fisFileChannel.close();
        fos.close();
        fis.close();
    }

    /**
     * 从文件读数据
     */
    public static void readFromFile() throws IOException {
        // 1: 创建文件输入流
        FileInputStream fis = new FileInputStream("file01.txt");
        // 2: 通过 FileInputStream 创建对应的 FileChannel
        FileChannel fileChannel = fis.getChannel();
        // 3: 创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 4: FileChannel 数据写入到 ByteBuffer
        fileChannel.read(byteBuffer);
        // 5: 输出 ByteBuffer 中的内容
        System.out.println(new String(byteBuffer.array()));

        fileChannel.close();
        fis.close();
    }

    /**
     * 使用 {@link FileChannel} 将文件写入到磁盘
     */
    public static void writeFile2Disk() throws IOException {
        String str = "Hello World";
        // 1: 创建文件输出流
        FileOutputStream fos = new FileOutputStream("file01.txt", true);
        // 2: 通过 FileOutputStream 获取对应的 FileChannel
        FileChannel fileChannel = fos.getChannel();
        // 3: 创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 4: 将 str 放入 ByteBuffer
        byteBuffer.put(str.getBytes());
        // 5: 对 ByteBuffer 进行翻转
        byteBuffer.flip();
        // 6: ByteBuffer 数据写入到 FileChannel
        fileChannel.write(byteBuffer);

        fileChannel.close();
        fos.close();
    }
}
