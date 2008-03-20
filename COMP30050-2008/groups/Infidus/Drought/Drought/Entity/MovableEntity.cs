using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.Xna.Framework;
using Drought.World;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Content;

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

        private bool selected, oldSelected;

        /** A unique identifier for this entity. */
        public readonly int uniqueID;

        public MovableEntity(Model model, Texture2D[] modelTextures, Texture2D circle, Path path, int uid)
        {
            uniqueID = uid;
            this.path = path;
            position = path.getPosition();
            prevPosition = path.getPosition();
            heading = new Vector3(0, 0, 0);
            rotation = new Vector3(0, 0, 0);
            normal = path.getNormal();
            orientation = Matrix.Identity;
            velocity = 0.05f;
            this.model = model;
            this.modelTextures = modelTextures;
            selector = circle;
            selected = false;
            oldSelected = false;
        }

        public void move()
        {
            if (!path.isFinished())
            {
                path.addDistance(velocity);
                prevPosition.X = position.X;
                prevPosition.Y = position.Y;
                prevPosition.Z = position.Z;
                position = path.getPosition();
                prevNormal.X = normal.X;
                prevNormal.Y = normal.Y;
                prevNormal.Z = normal.Z;
                normal = path.getNormal();

                heading = Vector3.Subtract(prevPosition, position);
                heading.Normalize();
                normal.Normalize();

                orientation.Up = normal;
                orientation.Right = Vector3.Cross(orientation.Up, heading);
                orientation.Right = Vector3.Normalize(orientation.Right);
                orientation.Forward = Vector3.Cross(-orientation.Right, orientation.Up);
                orientation.Forward = Vector3.Normalize(orientation.Forward);

                if (selected && !oldSelected) {
                    //Console.WriteLine("selected!");
                }
                if (!selected && oldSelected) {
                    //Console.WriteLine("unselected!");
                }
                oldSelected = selected;
            }
        }

        public void setPath(Path path)
        {
            this.path = path;
        }

        public void update()
        {
            move();
        }

        public void render(GraphicsDevice graphics, SpriteBatch batch, Camera camera, Effect effect)
        {
            //Console.WriteLine("heading:" + heading + " normal:" + normal + " rotation:"+model.rotationAngles);

            Matrix worldMatrix = orientation * Matrix.CreateTranslation(position);

            int i = 0;
            foreach (ModelMesh mesh in model.Meshes)
            {
                foreach (Effect currentEffect in mesh.Effects)
                {
                    currentEffect.CurrentTechnique = effect.Techniques["Textured"];

                    currentEffect.Parameters["xWorld"].SetValue(worldMatrix);
                    currentEffect.Parameters["xView"].SetValue(camera.getViewMatrix());
                    currentEffect.Parameters["xProjection"].SetValue(camera.getProjectionMatrix());
                    currentEffect.Parameters["xTexture"].SetValue(modelTextures[i++]);
                }
                mesh.Draw();
            }

            if (selected) {
                Vector3 v3 = graphics.Viewport.Project(position, camera.getProjectionMatrix(), camera.getViewMatrix(), Matrix.Identity);
                if (v3.Z < 1) {
                    batch.Begin();
                    Vector3 dist = camera.getPosition() - position;
                    int radius = 20;
                    Rectangle recty = new Rectangle((int)v3.X - radius, (int)v3.Y - radius, radius*2, radius*2);
                    batch.Draw(selector, recty, Color.White);
                    batch.End();
                }
            }
        }

        public Vector3 getPosition() {
            return position;
        }

        public void setPosition(Vector3 aPosition) {
            position = aPosition;
        }

        public void setSelected(bool selected) {
            this.selected = selected;
        }

        public bool isSelected() {
            return selected;
        }
    }
}
