package vivo.chainpaper.dto;

public class Block {
    long blockIndex=0;
    long blockOffset=0;

    public Block(long blockIndex, long blockOffset) {
        this.blockIndex = blockIndex;
        this.blockOffset = blockOffset;
    }

    public long getBlockIndex() {
        return blockIndex;
    }

    public void setBlockIndex(long blockIndex) {
        this.blockIndex = blockIndex;
    }

    public long getBlockOffset() {
        return blockOffset;
    }

    public void setBlockOffset(long blockOffset) {
        this.blockOffset = blockOffset;
    }
}
