package org.hannes.scoundrel.rs.util;

import java.nio.ByteBuffer;

import org.hannes.scoundrel.net.Session;
import org.hannes.scoundrel.util.Serializable;

/**
 * 
 * @author Red
 *
 */
public class BitMap implements Serializable {

	/**
	 * Bit mask out
	 */
	public static final int[] BIT_MASK_OUT = new int[32];
	
	static {
		for(int i = 0; i < BIT_MASK_OUT.length; i++) {
			BIT_MASK_OUT[i] = (1 << i) - 1;
		}
	}

	/**
	 * Current bit position
	 */
	private int bitPosition;

	/**
	 * The buffer
	 */
	private ByteBuffer buffer;

	public BitMap(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	public BitMap(int size) {
		this (ByteBuffer.allocate(size));
	}

	public BitMap() {
		this (64);
	}

	/**
	 * Write a specific amount of bits to the buffer
	 * 
	 * TODO: document
	 * 
	 * @param numBits
	 * @param value
	 * @return
	 */
	public BitMap put(int numBits, int value) {
		int position = (bitPosition + 7) / 8;

		/*
		 * 
		 */
		buffer.position(position);
		
		/*
		 * 
		 */
		int bytePos = bitPosition >> 3;
		int bitOffset = 8 - (bitPosition & 7);
		
		/*
		 * 
		 */
		bitPosition += numBits;
		
		/*
		 * 
		 */
		for (; numBits > bitOffset; bitOffset = 8) {
			byte b = buffer.get(bytePos);
			buffer.put(bytePos, (byte) (b & ~BIT_MASK_OUT[bitOffset]));
			buffer.put(bytePos, (byte) (b | (value >> (numBits - bitOffset)) & BIT_MASK_OUT[bitOffset]));
			bytePos++;
			numBits -= bitOffset;
		}
		
		/*
		 * 
		 */
		byte b = buffer.get(bytePos);
		if (numBits == bitOffset) {
			buffer.put(bytePos, (byte) (b & ~BIT_MASK_OUT[bitOffset]));
			buffer.put(bytePos, (byte) (b | value & BIT_MASK_OUT[bitOffset]));
		}
		
		/*
		 * 
		 */
		else {
			buffer.put(bytePos, (byte) (b & ~(BIT_MASK_OUT[numBits] << (bitOffset - numBits))));
			buffer.put(bytePos, (byte) (b | (value & BIT_MASK_OUT[numBits]) << (bitOffset - numBits)));
		}
		return this;
	}

	@Override
	public ByteBuffer serialize(Session session) {
		return buffer;
	}

}