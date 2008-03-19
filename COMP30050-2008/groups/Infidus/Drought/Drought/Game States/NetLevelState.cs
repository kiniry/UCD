using System;
using System.Collections.Generic;
using System.Text;
using Drought;
using Drought.State;
using Drought.World;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Drought.Entity;
using Drought.Input;
using Drought.Network;

namespace Drought.GameStates
{
    /** 
     * Branch of LevelState; a testbed for early networking functions,
     * so any broken functionality won't impede others' efforts.
     */
    class NetLevelState : GameState 
    {
        private Terrain terrain;

        private Camera camera;

        private HeightMap heightMap;

        private TextureMap textureMap;

        private WaterMap waterMap;

        private NormalMap normalMap;

        private DeviceInput input;

        private List<MovableEntity> entities;

        private Effect modelEffect;

        private Dictionary<int, Texture2D[]> modelTextures;

        private enum modelIndexes { Skybox, XYZ, Truck, Car };

        private Model[] models;

        private List<MovableEntity> localEntities;

        private List<MovableEntity> remoteEntities;

        private NetworkManager networkManager;

        private bool hosting;

        public NetLevelState(IStateManager manager, Game game, string fileName, bool isHost) :
            base(manager, game)
        {
            networkManager = ((Game1)game).getNetworkManager();
            hosting = isHost;

            input = DeviceInput.getInput();
            heightMap = new HeightMap(fileName);
            textureMap = new TextureMap(fileName);

            terrain = new Terrain(getGraphics(), getContentManager(), heightMap, textureMap);

            camera = new Camera(game, heightMap);

            models = new Model[4];
            modelTextures = new Dictionary<int, Texture2D[]>();

            loadContent();

            normalMap = new NormalMap(heightMap);

            localEntities = new List<MovableEntity>();
            int uid = 0;
            List<Vector3> nodes = new List<Vector3>();
            for (int i = 100; i < 200; i++)
                nodes.Add(new Vector3(i, i, heightMap.getHeight(i, i)));
            localEntities.Add(new MovableEntity(models[(int)modelIndexes.Car], modelTextures[(int)modelIndexes.Car], new Path(nodes, normalMap), uid++));

            nodes = new List<Vector3>();
            for (int i = 100; i < 200; i++)
                nodes.Add(new Vector3(i, 200, heightMap.getHeight(i, 200)));
            localEntities.Add(new MovableEntity(models[(int)modelIndexes.Car], modelTextures[(int)modelIndexes.Car], new Path(nodes, normalMap), uid++));
            
            nodes = new List<Vector3>();
            for (int i = 100; i < 200; i++)
                nodes.Add(new Vector3(200, i, heightMap.getHeight(200, i)));
            localEntities.Add(new MovableEntity(models[(int)modelIndexes.Car], modelTextures[(int)modelIndexes.Car], new Path(nodes, normalMap), uid++));

            uid = 0;
            remoteEntities = new List<MovableEntity>();
            nodes = new List<Vector3>();
            for (int i = 100; i > 0; i--)
                nodes.Add(new Vector3(i, i, heightMap.getHeight(i, i)));
            remoteEntities.Add(new MovableEntity(models[(int)modelIndexes.Car], modelTextures[(int)modelIndexes.Car], new Path(nodes, normalMap), uid++));

            nodes = new List<Vector3>();
            for (int i = 100; i > 0; i--)
                nodes.Add(new Vector3(i, 200, heightMap.getHeight(i, 200)));
            remoteEntities.Add(new MovableEntity(models[(int)modelIndexes.Car], modelTextures[(int)modelIndexes.Car], new Path(nodes, normalMap), uid++));

            nodes = new List<Vector3>();
            for (int i = 100; i > 0; i--)
                nodes.Add(new Vector3(200, i, heightMap.getHeight(200, i)));
            remoteEntities.Add(new MovableEntity(models[(int)modelIndexes.Car], modelTextures[(int)modelIndexes.Car], new Path(nodes, normalMap), uid++));
            
            if (hosting) {
                List<MovableEntity> tempList = localEntities;
                localEntities = remoteEntities;
                remoteEntities = tempList;
            }
        }

        public override void loadContent()
        {
            modelEffect = getContentManager().Load<Effect>("EffectFiles/model");

            //initialise models
            models[(int)modelIndexes.Truck] = getContentManager().Load<Model>("Models/Truck/truck");
            models[(int)modelIndexes.XYZ] = getContentManager().Load<Model>("Models/xyz");
            models[(int)modelIndexes.Car] = getContentManager().Load<Model>("Models/Car/car");
            models[(int)modelIndexes.Skybox] = getContentManager().Load<Model>("Models/Skybox/skybox");

            terrain.loadContent();
            terrain.setProjectionMatrix(camera.getProjectionMatrix());
            terrain.setViewMatrix(camera.getViewMatrix());

            for (int index = 0; index < models.Length; index++) {
                Model model = models[index];

                int textureCount = 0;
                foreach (ModelMesh mesh in model.Meshes)
                    textureCount += mesh.Effects.Count;

                Texture2D[] textures = new Texture2D[textureCount];

                int i = 0;
                foreach (ModelMesh mesh in model.Meshes)
                    foreach (BasicEffect basicEffect in mesh.Effects)
                        textures[i++] = basicEffect.Texture;

                modelTextures.Add(index, textures);

                foreach (ModelMesh mesh in model.Meshes)
                    foreach (ModelMeshPart meshPart in mesh.MeshParts)
                        meshPart.Effect = modelEffect.Clone(getGraphics());
            }
        }

        public override void background()
        {
            Console.WriteLine("NetLevelState in background");
            //throw new Exception("The method or operation is not implemented.");
        }

        public override void foreground()
        {
            Console.WriteLine("NetLevelState in foreground");
            //throw new Exception("The method or operation is not implemented.");
        }

        public override void update(GameTime gameTime)
        {
            if (input.isKeyPressed(GameKeys.CAM_FORWARD))
                camera.forward();
            else if (input.isKeyPressed(GameKeys.CAM_BACK))
                camera.back();

            if (input.isKeyPressed(GameKeys.CAM_LEFT))
                camera.left();
            else if (input.isKeyPressed(GameKeys.CAM_RIGHT))
                camera.right();

            if (input.isKeyPressed(GameKeys.CAM_ASCEND))
                camera.ascend();
            else if (input.isKeyPressed(GameKeys.CAM_DESCEND))
                camera.descend();
            
            if (input.isKeyPressed(GameKeys.CAM_ZOOM_IN))
                camera.zoomIn();
            else if (input.isKeyPressed(GameKeys.CAM_ZOOM_OUT))
                camera.zoomOut();
            
            if (input.isKeyPressed(GameKeys.CAM_ROTATE_UP))
                camera.rotateUp();
            else if (input.isKeyPressed(GameKeys.CAM_ROTATE_DOWN))
                camera.rotateDown();

            if (input.isKeyPressed(GameKeys.CAM_ROTATE_LEFT))
                camera.rotateLeft();
            else if (input.isKeyPressed(GameKeys.CAM_ROTATE_RIGHT))
                camera.rotateRight();
            
            camera.update(gameTime);
            terrain.setViewMatrix(camera.getViewMatrix());
            terrain.update(gameTime);

            for (int i = 0; i < localEntities.Count; i++)
                localEntities[i].update();

            foreach (MovableEntity entity in localEntities) {
                networkManager.sendPos(entity);
                //Console.WriteLine("sent: " + entity.getPosition());
            }
            
            while (networkManager.hasMoreData()) {
                Vector3 vec = networkManager.recievePos();
                int uid = networkManager.recieveUID();
                //Console.WriteLine("uid: " + uid + " recieved");
                foreach (MovableEntity entity in remoteEntities)
                    if (entity.uniqueID == uid)
                        entity.setPosition(vec);
                //if (vec != new Vector3()) Console.WriteLine("recieved: " + vec);
            }
        }

        public override void render(GraphicsDevice graphics, SpriteBatch spriteBatch)
        {
            graphics.RenderState.FillMode = FillMode.Solid;
            graphics.RenderState.CullMode = CullMode.None;

            graphics.SamplerStates[0].AddressU = TextureAddressMode.Wrap;
            graphics.SamplerStates[0].AddressV = TextureAddressMode.Wrap;

            graphics.RenderState.DepthBufferEnable = true;
            graphics.RenderState.AlphaBlendEnable = false;
            graphics.RenderState.AlphaTestEnable = false;

            graphics.Clear(ClearOptions.DepthBuffer, Color.Black, 1.0f, 0);

            terrain.render();

            for (int i = 0; i < localEntities.Count; i++)
                localEntities[i].render(graphics, camera, modelEffect);
            for (int i = 0; i < remoteEntities.Count; i++)
                remoteEntities[i].render(graphics, camera, modelEffect);
        }
    }
}