using System;

namespace TuneBlaster_
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        static void Main(string[] args)
        {
            using (Engine game = new Engine())
            {
                game.Run();
            }
        }
    }
}

