package com.xyz.easyexcel.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩工具优化对比
 */
public class FileZipUtils {

    /**
     * 文件压缩 1.0版
     *
     * @param path 文件目录
     * @return String 压缩文件路径
     */
    public static String zipFileV1(String path) {
        // 要压缩的文件夹
        File file = new File(path);
        IdWorker idWorker = new IdWorker(1, 1);
        StringBuilder zipPath = new StringBuilder(file.getParent()).append(File.separator).append(idWorker.nextId()).append(".zip");
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toString()));
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    zos.putNextEntry(new ZipEntry(file.getName() + file.separator + files[i].getName()));
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(files[i]));
                    while (true) {
                        byte[] b = new byte[100];
                        int len = bis.read(b);
                        if (len == -1) break;
                        zos.write(b, 0, len);
                    }
                    bis.close();
                }
            }
            zos.close();
            return zipPath.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 文件压缩 2.0版
     *
     * @param path 压缩目录
     * @return String 压缩文件路径
     */
    public static String zipFileV2(String path) {
        // 要压缩的文件夹
        File file = new File(path);
        IdWorker idWorker = new IdWorker(1, 1);
        StringBuilder zipPath = new StringBuilder(file.getParent()).append(File.separator).append(idWorker.nextId()).append(".zip");
        try {
            // 创建文件压缩输出流
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath.toString()));
            // 使用buffer缓存
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);
            // 如果是目录则压缩
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName() + file.separator + files[i].getName()));
                    // 获取文件输入流放入buffer缓存
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(files[i]));
                    int temp = 0;
                    while ((temp = bufferedInputStream.read()) != -1) {
                        bufferedOutputStream.write(temp);
                    }
                }
            }
            return zipPath.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 文件压缩 3.0版
     *
     * @param path 问价目录
     * @return String 压缩文件路径
     */
    public static String zipFileV3(String path) {
        // 要压缩的文件夹
        File file = new File(path);
        IdWorker idWorker = new IdWorker(1, 1);
        StringBuilder zipPath = new StringBuilder(file.getParent()).append(File.separator).append(idWorker.nextId()).append(".zip");
        try {
            // 创建文件压缩输出流
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath.toString()));
            // 使用channel
            WritableByteChannel writableByteChannel = Channels.newChannel(zipOutputStream);
            // 如果是目录则压缩
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName() + file.separator + files[i].getName()));
                    // 获取文件输入流放入Channel中
                    FileChannel fileChannel = new FileInputStream(files[i]).getChannel();
                    // 使用transferTo方法连接两个Channel通道
                    fileChannel.transferTo(0, files[i].length(), writableByteChannel);
                }
            }
            return zipPath.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 文件压缩 4.0版
     *
     * @param path 问价目录
     * @return String 压缩文件路径
     */
    public static String zipFileV4(String path) {
        // 要压缩的文件夹
        File file = new File(path);
        IdWorker idWorker = new IdWorker(1, 1);
        StringBuilder zipPath = new StringBuilder(file.getParent()).append(File.separator).append(idWorker.nextId()).append(".zip");
        try {
            // 创建文件压缩输出流
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath.toString()));
            // 使用channel
            WritableByteChannel writableByteChannel = Channels.newChannel(zipOutputStream);
            // 如果是目录则压缩
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName() + file.separator + files[i].getName()));
                    // 使用内存映射文件
                    MappedByteBuffer mappedByteBuffer = new RandomAccessFile(files[i], "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, files[i].length());
                    writableByteChannel.write(mappedByteBuffer);
                }
            }
            return zipPath.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 文件压缩 5.0版
     *
     * @param path 问价目录
     * @return String 压缩文件路径
     */
    public static String zipFileV5(String path) {
        // 要压缩的文件夹
        File file = new File(path);
        IdWorker idWorker = new IdWorker(1, 1);
        StringBuilder zipPath = new StringBuilder(file.getParent()).append(File.separator).append(idWorker.nextId()).append(".zip");
        try {
            // 如果是目录则压缩
            if (file.isDirectory()) {
                WritableByteChannel writableByteChannel = Channels.newChannel(new FileOutputStream(zipPath.toString()));
                // 打开管道
                Pipe pipe = Pipe.open();
                ZipOutputStream zipOutputStream = new ZipOutputStream(Channels.newOutputStream(pipe.sink()));
                WritableByteChannel fileOutputChannel = Channels.newChannel(zipOutputStream);

                File[] files = file.listFiles();
                long totalFileSize = 0;
                for (int i = 0; i < files.length; i++) {
                    totalFileSize = Math.addExact(totalFileSize, files[i].length());
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName() + file.separator + files[i].getName()));
                    FileChannel fileInputChannel = new FileInputStream(files[i]).getChannel();
                    fileInputChannel.transferTo(0, files[i].length(), fileOutputChannel);
                    fileInputChannel.close();
                }

                //获取读通道
                ReadableByteChannel readableByteChannel = pipe.source();
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) totalFileSize * 10);
                while (readableByteChannel.read(byteBuffer) >= 0) {
                    byteBuffer.flip();
                    writableByteChannel.write(byteBuffer);
                    byteBuffer.clear();
                }
            }
            return zipPath.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
