package org.hannes.scoundrel.net.message;

import java.nio.ByteBuffer;

public class Header {
	
	/**
	 * Indicates the 
	 */
	private static final boolean ESTIMATE_ENABLED = true;

	/**
	 * The size of the message
	 */
	private final int size;
	
	/**
	 * The opcode of the message
	 */
	private final int opcode;
	
	/**
	 * The meta data
	 */
	private final MetaData meta;

	public Header(int size, int opcode, MetaData meta) {
		this.size = size;
		this.opcode = opcode;
		this.meta = meta;
	}
	
	public Header(int size) {
		this (size, -1, MetaData.RAW);
	}

	/**
	 * Wrap the header around the given data
	 * 
	 * @param buffer
	 * @return
	 */
	public static Header wrap(ByteBuffer buffer) {
		if (ESTIMATE_ENABLED) return estimate(buffer);
		return null;
	}

	/**
	 * Attempt to estimate the message's size
	 * 
	 * @param buffer
	 * @return
	 */
	private static Header estimate(ByteBuffer buffer) {
		throw new UnsupportedOperationException("not yet implemented");
	}

	/**
	 * Prepares a byte buffer according to the given header
	 * 
	 * @return
	 */
	public ByteBuffer prepare() {
		ByteBuffer buffer = ByteBuffer.allocate(size + meta.ordinal());
		
		/*
		 * If the opcode is not -1, put the opcode on the first byte
		 */
		if (opcode != -1) {
			buffer.put((byte) opcode);
		}
		
		/*
		 * If the size is defined by the first byte, put it there
		 */
		if (meta == MetaData.DYNAMIC_BYTE) {
			buffer.put((byte) size);
		}
		
		/*
		 * If the size is defined by the first 2 bytes, put em there
		 */
		else if (meta == MetaData.DYNAMIC_SHORT) {
			buffer.putShort((short) size);
		}
		return buffer;
	}

	public int getSize() {
		return size;
	}

	public int getOpcode() {
		return opcode;
	}

	public MetaData getMetaData() {
		return meta;
	}

	@Override
	public String toString() {
		return "Header [size=" + size + ", opcode=" + opcode + ", meta=" + meta + "]";
	}

	/**
	 * 
	 * @author Red
	 *
	 */
	public static enum MetaData {
		RAW, STATIC, DYNAMIC_BYTE, DYNAMIC_SHORT;
	}

}