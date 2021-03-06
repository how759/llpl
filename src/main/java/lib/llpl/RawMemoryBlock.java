/* 
 * Copyright (C) 2018 Intel Corporation
 *
 * SPDX-License-Identifier: BSD-3-Clause
 * 
 */

package lib.llpl;

class RawMemoryBlock extends MemoryBlock<Raw> {
    private static final long METADATA_SIZE = 8;


    RawMemoryBlock(Heap heap, long size) {
        super(heap, size, true);
    }

    RawMemoryBlock(Heap heap, long poolAddress, long offset) {
        super(heap, poolAddress, offset, true);
    }

    @Override
    public void copyFromMemory(MemoryBlock<?> srcBlock, long srcOffset, long dstOffset, long length) {
        nativeCopyBlockToBlock(srcBlock.directAddress(), srcBlock.baseOffset() + srcOffset, directAddress(), baseOffset() + dstOffset, length);
    }

    @Override
    public void copyFromArray(byte[] srcArray, int srcOffset, long dstOffset, int length) {
        nativeCopyFromByteArray(srcArray, srcOffset, directAddress(), baseOffset() + dstOffset, length);
    }

    @Override
    public void setMemory(byte val, long offset, long length) {
        nativeSetMemory(directAddress(), baseOffset() + offset, val, length);
    }

    @Override
    long baseOffset() { 
        return METADATA_SIZE; 
    }

    @Override
    public void setByte(long offset, byte value) {
        setRawByte(offset, value);
    }

    @Override
    public void setShort(long offset, short value) {
        setRawShort(offset, value);
    }

    @Override
    public void setInt(long offset, int value) {
        setRawInt(offset, value);
    }

    @Override
    public void setLong(long offset, long value) {
        setRawLong(offset, value);
    }
}
