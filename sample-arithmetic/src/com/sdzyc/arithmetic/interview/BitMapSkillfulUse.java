package com.sdzyc.arithmetic.interview;

/**
 * BitMapSkillfulUse class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/24 16:07
 */
public class BitMapSkillfulUse {
    // 每一个word是一个long类型的元素，对应一个64位的二进制数据
    private long[] words;
    // Bitmap 的位数大小
    private int size;

    /**
     *  bit 位大小
     * @param bitSize
     */
    public BitMapSkillfulUse(long bitSize) {
        this.size = size;
        this.words = new long[getWordIndex(size -1 ) + 1];
    }

    public boolean getBit(int bitIndex) {
        assertIndex(bitIndex);
        int wordIndex = getWordIndex(bitIndex);

        System.out.println("get:"+wordIndex);
        // 求 与， 是否存在
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }
    /**
     * 把Bitmap 某一位设置为true
     * @param bitIndex
     */
    public void setBit(int bitIndex) {
        assertIndex(bitIndex);
        int wordIndex = getWordIndex(bitIndex);
        System.out.println("set:"+wordIndex);
        // 原值与插入值 或 (新增还能去重)
        words[wordIndex] |= (1L << bitIndex);
    }
    /**
     *  定位Bitmap某一位所对应的word
     * @param bitIndex
     * @return
     */
    private int getWordIndex(int bitIndex) {
        // 右移6位，相当于除以64
        return bitIndex >> 6;
    }

    public void assertIndex(int bitIndex) {
        boolean check =  bitIndex < 0 || bitIndex > (size -1);
        if(check) {
            throw new IndexOutOfBoundsException("超过有效范围");
        }
    }

    public static void main(String[] args) {
        BitMapSkillfulUse bitmap = new BitMapSkillfulUse(256);
        bitmap.setBit(126);
        bitmap.setBit(55);
        bitmap.setBit(127);
        bitmap.setBit(124);
        System.out.println(bitmap.getBit(126));
        System.out.println(bitmap.getBit(55));
        System.out.println(bitmap.getBit(127));

    }

}
