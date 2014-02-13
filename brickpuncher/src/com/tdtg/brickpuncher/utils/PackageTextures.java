package com.tdtg.brickpuncher.utils;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class PackageTextures {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TexturePacker.process("C:\\Users\\Gunslinger\\Documents\\workspace\\protos\\brick1\\brickpuncher-android\\assets\\images",
				"C:\\Users\\Gunslinger\\Documents\\workspace\\protos\\brick1\\brickpuncher-android\\assets\\images", "textures.pack");
	}
}
