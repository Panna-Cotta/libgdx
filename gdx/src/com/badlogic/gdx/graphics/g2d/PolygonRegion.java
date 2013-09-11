/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.graphics.g2d;

/** Defines a polygon shape on top of a texture region to avoid drawing transparent pixels.
 * <p>
 * THIS STUFF IS A WIP
 * <p>
 * @see PolygonRegionLoader
 * @author Stefan Bachmann
 * @author Nathan Sweet */
public class PolygonRegion {
	final float[] textureCoords; // texture coordinates in atlas coordinates
	final float[] vertices; // pixel coordinates relative to source image.
	final short[] triangles;
	final TextureRegion region;

	/** Creates a PolygonRegin by triangulating the polygon coordinates in vertices and calculates uvs based on that. TextureRegion
	 * can come from an atlas.
	 * @param region the region used for drawing
	 * @param vertices contains 2D polygon coordinates in pixels relative to source region */
	public PolygonRegion (TextureRegion region, float[] vertices, short[] triangles) {
		this.region = region;
		this.vertices = vertices;
		this.triangles = triangles;

		textureCoords = new float[vertices.length];
		float uvWidth = region.u2 - region.u;
		float uvHeight = region.v2 - region.v;
		for (int i = 0, n = vertices.length; i < n; i++) {
			textureCoords[i] = region.getU() + uvWidth * (vertices[i] / region.getRegionWidth());
			i++;
			textureCoords[i] = region.getV() + uvHeight * (1 - (vertices[i] / region.getRegionHeight()));
		}
	}

	/** Returns the vertices in local space. */
	public float[] getVertices () {
		return vertices;
	}

	public short[] getTriangles () {
		return triangles;
	}

	public float[] getTextureCoords () {
		return textureCoords;
	}

	public TextureRegion getRegion () {
		return region;
	}
}
