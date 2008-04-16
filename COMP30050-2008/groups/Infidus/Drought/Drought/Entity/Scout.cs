using Drought.Graphics;
using Drought.State;
using Drought.World;

namespace Drought.Entity
{
    class Scout : MovableEntity
    {
        private const float VELOCITY = 1.0f;

        private const float RADIUS = 1.5f;

        private const int FULL_HEALTH = 5;

        private const int WATER_CAPACITY = 4;

        private const float MODEL_SCALE = 1.0f;


        public Scout(GameState gameState, Model3D model, Path path, Terrain terrain, int uid) :
            base(gameState, model, path, terrain, uid)
        {
            setVelocity(VELOCITY);
            setRadius(RADIUS);
            setMaxHealth(FULL_HEALTH);
            setMaxWater(WATER_CAPACITY);
            setModelScale(MODEL_SCALE);
        }
    }
}