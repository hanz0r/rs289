package org.hannes.scoundrel.rs.entity.render;

import java.nio.ByteBuffer;

// @Attribute(0x100)
public class Animation implements Attribute {

	@Override
	public void serialize(ByteBuffer buffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int mask() {
		return 0x100;
	}

}