using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Drought.Graphics;
using Drought.World;

namespace Drought.Entity
{

    public class MovableEntity
    {
        private Vector3 position;

        private Vector3 prevPosition;

        private Vector3 heading;

        private Vector3 rotation;

        private Vector3 normal;

        private Vector3 prevNormal;

        private Matrix orientation;

        private float velocity;

        private Path path;

        private Model model;

        private Texture2D[] modelTextures;

        private Texture2D selector;

        private bool selected;

        private LineTool lineTool;

        /** A unique identifier for this entity. */
        public readonly int uniqueID;

        public MovableEntity(DroughtGame game, Model model, Texture2D[] modelTextures, Path path, int uid)
        {
            uniqueID = uid;
            this.path = path;
            position = path.getPosition();
            prevPosition = path.getPosition();
            heading = new Vector3(0, 0, 0);
            rotation = new Vector3(0, 0, 0);
            normal = path.getNormal();
            orientation = Matrix.Identity;
            velocity = 0.1f;
            this.model = model;
            this.modelTextures = modelTextures;
            selected = false;
            lineTool = new LineTool(game.GraphicsDevice);
            selector = game.Content.Load<Texture2D>("Textures/selector");
        }

        public void move()
        {
            if (!path.isFinished()) {
                path.addDistance(velocity);
                prevPosition.X = position.X;
                prevPosition.Y = position.Y;
                prevPosition.Z = position.Z;
                position = path.getPosition();
                prevNormal.X = normal.X;
                prevNormal.Y = normal.Y;
                prevNormal.Z = normal.Z;
                normal = path.getNormal();
                normal.Normalize();

                heading = position - prevPosition;
                heading.Normalize();

                orientation.Up = normal;
                orientation.Right = Vector3.Cross(orientation.Up, heading);
                orientation.Right = Vector3.Normalize(orientation.Right);
                orientation.Forward = Vector3.Cross(-orientation.Right, orientation.Up);
                orientation.Forward = Vector3.Normalize(orientation.Forward);
            }
        }

        public void setPath(Path path)
        {
            this.path = path;
        }

        public Path getPath()
        {
            return path;
        }

        public void update()
        {
            move();
            lineTool.setPointsList(path.getRemainingPath());
        }

        public void render(GraphicsDevice graphics, SpriteBatch batch, Camera camera, Effect effect)
        {
            //Console.WriteLine("heading:" + heading + " normal:" + normal + " rotation:"+model.rotationAngles);

            Matrix[] transforms = new Matrix[model.Bones.Count];
            model.CopyAbsoluteBoneTransformsTo(transforms);

            Matrix worldMatrix = orientation * Matrix.CreateTranslation(position);

            int i = 0;
            foreach (ModelMesh mesh in model.Meshes) {
                foreach (Effect currentEffect in mesh.Effects) {
                    currentEffect.CurrentTechnique = effect.Techniques["Textured"];

                    currentEffect.Parameters["xWorldViewProjection"].SetValue(transforms[mesh.ParentBone.Index] * worldMatrix * camera.getViewMatrix() * camera.getProjectionMatrix());
                    currentEffect.Parameters["xWorld"].SetValue(worldMatrix);
                    currentEffect.Parameters["xTexture"].SetValue(modelTextures[i++]);

                    //HLSL testing
                    //HardCoded Light params need to be replaced with the values from the Sun.
                    currentEffect.Parameters["xEnableLighting"].SetValue(true);
                    currentEffect.Parameters["xLightPosition"].SetValue(new Vector3(0, 0, 200));
                    currentEffect.Parameters["xLightPower"].SetValue(1);
                }
                mesh.Draw();
            }

            lineTool.render(camera.getViewMatrix(), camera.getProjectionMatrix());

            if (selected) {
                Vector3 ringPos = graphics.Viewport.Project(position, camera.getProjectionMatrix(), camera.getViewMatrix(), Matrix.Identity);
                if (ringPos.Z < 1) {
                    batch.Begin();
                    int radius = 20;
                    Rectangle recty = new Rectangle((int)ringPos.X - radius, (int)ringPos.Y - radius, radius * 2, radius * 2);
                    batch.Draw(selector, recty, Color.White);
                    batch.End();
                }
            }
        }

        public Vector3 getPosition()
        {
            return position;
        }

        public void setPosition(Vector3 aPosition)
        {
            position = aPosition;
        }

        public Matrix getOrientation()
        {
            return orientation;
        }

        public void setOrientation(Matrix anOrientation)
        {
            orientation = anOrientation;
        }

        public void setSelected(bool selected)
        {
            this.selected = selected;
        }

        public bool isSelected()
        {
            return selected;
        }
    }
}
