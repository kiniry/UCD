using System;
using System.Collections.Generic;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

using Microsoft.Xna.Framework.Content;

namespace Drought.World
{
    class Terrain
    {
        public struct VertexMultiTextured
        {
            public Vector3 Position;
            public Vector3 Normal;
            public Vector4 TextureCoordinate;
            public Vector4 TextureWeights;

            public static int SizeInBytes = (3 + 3 + 4 + 4) * 4;
            public static VertexElement[] VertexElements = new VertexElement[]
              {
                  new VertexElement( 0, 0, VertexElementFormat.Vector3, VertexElementMethod.Default, VertexElementUsage.Position, 0 ),
                  new VertexElement( 0, sizeof(float) * 3, VertexElementFormat.Vector3, VertexElementMethod.Default, VertexElementUsage.Normal, 0 ),
                  new VertexElement( 0, sizeof(float) * 6, VertexElementFormat.Vector4, VertexElementMethod.Default, VertexElementUsage.TextureCoordinate, 0 ),
                  new VertexElement( 0, sizeof(float) * 10, VertexElementFormat.Vector4, VertexElementMethod.Default, VertexElementUsage.TextureCoordinate, 1 ),
              };
        }

        GraphicsDevice device;
        Effect effect;
        ContentManager content;

        VertexBuffer vb;
        IndexBuffer ib;

        VertexMultiTextured[] vertices;
        int[] indices;

        Texture2D sandTexture;
        Texture2D waterTexture;
        Texture2D stoneTexture;
        Texture2D errorTexture;

		HeightMap heightMap;
		int width, height;

        TextureMap textureMap;
        float textureZoom;

        public double oldTime = 0;
        public double counter = Math.PI;
        public Vector3 lightDirection = new Vector3(0.5f, (float)Math.Sin(Math.PI), (float)Math.Sin(Math.PI));

        public Terrain(GraphicsDevice device, ContentManager content, HeightMap heightMap, TextureMap textureMap)
        {
            this.device = device;
            this.content = content;
            this.heightMap = heightMap;
            this.textureMap = textureMap;

            initialise();
        }

        public void initialise()
        {
            width  = heightMap.getMapWidth();
            height = heightMap.getMapHeight();

            textureZoom = 40.0f;

            setUpVertices();
            setUpIndices();
            setUpNormals();
            finaliseBuffers();
        }

        public void loadContent()
        {
            effect = content.Load<Effect>("EffectFiles/terrain");

            sandTexture  = content.Load<Texture2D>("Textures/sand");
            waterTexture = content.Load<Texture2D>("Textures/water");
            stoneTexture = content.Load<Texture2D>("Textures/stone");
            errorTexture = content.Load<Texture2D>("Textures/error");
        }

        private void setUpVertices()
        {
            vertices = new VertexMultiTextured[width * height];

            for (int x = 0; x < width; x++)
            {
                for (int y = 0; y < height; y++)
                {
                    vertices[x + y * width].Position = new Vector3(x, y, heightMap.getHeight(x, y));
                    vertices[x + y * width].Normal = new Vector3(0, 0, 0);
                    vertices[x + y * width].TextureCoordinate.X = (float)x / textureZoom;
                    vertices[x + y * width].TextureCoordinate.Y = (float)y / textureZoom;
                    
                    vertices[x + y * width].TextureWeights = textureMap.getValue(x,y);
                }
            }
        }

        private void setUpIndices()
        {
            indices = new int[(width - 1) * (height - 1) * 6];

            for (int x = 0; x < width - 1; x++)
            {
                for (int y = 0; y < height - 1; y++)
                {
                    indices[(x + y * (width - 1)) * 6]     = (x + 1) + (y + 1) * width;
                    indices[(x + y * (width - 1)) * 6 + 1] = (x + 1) + y * width;
                    indices[(x + y * (width - 1)) * 6 + 2] = x + y * width;

                    indices[(x + y * (width - 1)) * 6 + 3] = (x + 1) + (y + 1) * width;
                    indices[(x + y * (width - 1)) * 6 + 4] = x + y * width;
                    indices[(x + y * (width - 1)) * 6 + 5] = x + (y + 1) * width;
                }
            }
        }

        private void setUpNormals()
        {
            for (int i = 0; i < indices.Length/3; i++)
            {
                Vector3 v = vertices[indices[i * 3 + 1]].Position - vertices[indices[i * 3]].Position;
                Vector3 w = vertices[indices[i * 3]].Position - vertices[indices[i * 3 + 2]].Position;
                Vector3 n = Vector3.Cross(v, w);
                n.Normalize();

                vertices[indices[i * 3]].Normal += n;
                vertices[indices[i * 3 + 1]].Normal += n;
                vertices[indices[i * 3 + 2]].Normal += n;
            }

            for (int i = 0; i < vertices.Length; i++)
                vertices[i].Normal.Normalize();
        }

        public void finaliseBuffers()
        {
            vb = new VertexBuffer(device, VertexMultiTextured.SizeInBytes * vertices.Length, BufferUsage.WriteOnly);
            vb.SetData(vertices);

            ib = new IndexBuffer(device, typeof(int), (width - 1) * (height - 1) * 6, BufferUsage.WriteOnly);
            ib.SetData(indices);
        }

        public void update(GameTime gameTime)
        {
            double currTime = gameTime.TotalGameTime.Ticks;

            if (currTime > oldTime + 10)
            {
                float lightDir = (float)(Math.Sin(counter % (Math.PI * 2)));
                counter += lightDir < 0 ? 0.001 : 0.01;
                lightDirection.Y = lightDir;
                lightDirection.Z = lightDir;
                oldTime = currTime;
            }
        }

        public void render()
        {
            Matrix worldMatrix = Matrix.Identity;

            effect.CurrentTechnique = effect.Techniques["MultiTextured"];
            
            effect.Parameters["xWaterTexture"].SetValue(waterTexture);
            effect.Parameters["xSandTexture"].SetValue(sandTexture);
            effect.Parameters["xStoneTexture"].SetValue(stoneTexture);
            effect.Parameters["xErrorTexture"].SetValue(errorTexture);

            effect.Parameters["xWorld"].SetValue(worldMatrix);
            effect.Parameters["xEnableLighting"].SetValue(true);
            effect.Parameters["xLightDirection"].SetValue(lightDirection); 

            effect.Begin();

            foreach (EffectPass pass in effect.CurrentTechnique.Passes)
            {
                pass.Begin();

                device.Vertices[0].SetSource(vb, 0, VertexMultiTextured.SizeInBytes);
                device.Indices = ib;
                device.VertexDeclaration = new VertexDeclaration(device, VertexMultiTextured.VertexElements);
                device.DrawIndexedPrimitives(PrimitiveType.TriangleList, 0, 0, height * width, 0, (width - 1) * (height - 1) * 2);

                pass.End();
            }
            effect.End();
        }

        public void setViewMatrix(Matrix viewMatrix)
        {
            effect.Parameters["xView"].SetValue(viewMatrix);
        }

        public void setProjectionMatrix(Matrix projectionMatrix)
        {
            effect.Parameters["xProjection"].SetValue(projectionMatrix);
        }
    }
}