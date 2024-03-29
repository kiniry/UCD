#region Using Statements
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Storage;
using System;
using System.Threading;
using System.Collections.Generic;
using TuneBlaster_.Graphics;
#endregion

namespace TuneBlaster_
{
    /// <summary>
    /// The class that manages the audio within the game
    /// Author Dermot Kirby
    /// </summary>
    class GameAudio
    {
        #region Fields (musicEngine, musicWaveBank, soundBanks, baseCues, drumCues, stringCues, harpCues, synthCues, ballColour)

        private AudioEngine musicEngine;
        private WaveBank musicWaveBank;
        private SoundBank musicSoundBank, synthBank, stringBank, drumBank, ballBank, harpBank, specialBank;
        public AudioListener listener;
        public AudioEmitter emitter;
        public Cue searchCue, bwCue, gameOverCue, deathCue;
        public Thread Alter;
        public Thread Base;
        public Thread Synth;
        public Thread Drums;
        public Thread Strings;
        public Thread Harps;

        Cue baseCue1, baseCue2;
        Cue drumCue1, drumCue2, drumCue3, drumCue4;
        Cue stringCue1, stringCue2, stringCue3, stringCue4;
        Cue harpCue1, harpCue2, harpCue3, harpCue4;
        Cue synthCue1, synthCue2, synthCue3, synthCue4;
        Cue ballCue;
        int cue;
        Image.value ballColour;

        #endregion

        public GameAudio()
        {
            musicEngine = new AudioEngine("Content\\Audio\\Win\\Game Sounds.xgs");
            musicWaveBank = new WaveBank(musicEngine, "Content\\Audio\\Win\\Wave Bank.xwb");

            musicSoundBank = new SoundBank(musicEngine, "Content\\Audio\\Win\\Base Bank.xsb");
            stringBank = new SoundBank(musicEngine, "Content\\Audio\\Win\\String Bank.xsb");
            drumBank = new SoundBank(musicEngine, "Content\\Audio\\Win\\Drum Bank.xsb");
            ballBank = new SoundBank(musicEngine, "Content\\Audio\\Win\\Ball Noise Bank.xsb");
            synthBank = new SoundBank(musicEngine, "Content\\Audio\\Win\\Synth Bank.xsb");
            harpBank = new SoundBank(musicEngine, "Content\\Audio\\Win\\Harp Bank.xsb");
            specialBank = new SoundBank(musicEngine, "Content\\Audio\\Win\\Special Music.xsb");

            Alter = new Thread(new ParameterizedThreadStart(ChangeMelody));
            Base = new Thread(new ThreadStart(ModifyBase));
            Drums = new Thread(new ThreadStart(ModifyDrums));
            Strings = new Thread(new ThreadStart(ModifyStrings));
            Harps = new Thread(new ThreadStart(ModifyHarp));
            Synth = new Thread(new ThreadStart(ModifySynth));

            listener = new AudioListener();
            emitter = new AudioEmitter();
        }

        #region Initialisation Methods (Initialise, Cue Resetters)

        /// <summary>
        /// Plays the initial melody.
        /// </summary>
        public void Initialise()
        {
            resetBaseCues();
            resetBallCues();
            resetStringCues();
            resetDrumCues();
            resetSynthCues();
            resetHarpCues();
            resetSpecialCues();

            baseCue1.Play();
        }

        /// <summary>
        /// Sets the cues equal to the specified files from the sound bank to make them ready to be played again.
        /// </summary>
        public void resetBallCues()
        {
            ballCue = ballBank.GetCue("Ball Noise 1");
            deathCue = ballBank.GetCue("Game Over Sound");
        }

        /// <summary>
        /// Sets the cues equal to the specified files from the sound bank to make them ready to be played again.
        /// </summary>
        public void resetBaseCues()
        {
            baseCue1 = musicSoundBank.GetCue("Base Melody 1");
            baseCue2 = musicSoundBank.GetCue("Base Melody 2");
        }

        /// <summary>
        /// Sets the cues equal to the specified files from the sound bank to make them ready to be played again.
        /// </summary>
        public void resetDrumCues()
        {
            drumCue1 = drumBank.GetCue("Drum Loop 1");
            drumCue2 = drumBank.GetCue("Drum Loop 2");
            drumCue3 = drumBank.GetCue("Drum Loop 3");
            drumCue4 = drumBank.GetCue("Drum Loop 4");
        }

        /// <summary>
        /// Sets the cues equal to the specified files from the sound bank to make them ready to be played again.
        /// </summary>
        public void resetSynthCues()
        {
            synthCue1 = synthBank.GetCue("Synth Melody 1");
            synthCue2 = synthBank.GetCue("Synth Melody 2");
            synthCue3 = synthBank.GetCue("Synth Melody 3");
            synthCue4 = synthBank.GetCue("Synth Melody 4");
        }

        /// <summary>
        /// Sets the cues equal to the specified files from the sound bank to make them ready to be played again.
        /// </summary>
        public void resetHarpCues()
        {
            harpCue1 = harpBank.GetCue("Harp Melody 1");
            harpCue2 = harpBank.GetCue("Harp Melody 2");
            harpCue3 = harpBank.GetCue("Harp Melody 3");
            harpCue4 = harpBank.GetCue("Harp Melody 4");
        }

        /// <summary>
        /// Sets the cues equal to the specified files from the sound bank to make them ready to be played again.
        /// </summary>
        public void resetStringCues()
        {
            stringCue1 = stringBank.GetCue("String Melody 1");
            stringCue2 = stringBank.GetCue("String Melody 2");
            stringCue3 = stringBank.GetCue("String Melody 3");
            stringCue4 = stringBank.GetCue("String Melody 4");
        }

        /// <summary>
        /// Sets the cues equal to the specified files from the sound bank to make them ready to be played again.
        /// </summary>
        public void resetSpecialCues()
        {
            searchCue = specialBank.GetCue("Search Light Music");
            bwCue = specialBank.GetCue("Black and White Music");
            gameOverCue = specialBank.GetCue("Game Over Music");
        }

        #endregion

        #region Disposer Methods (disposeBallCues, disposeDrumCues, disposeStringCues, disposeSynthCues, disposeHarpCues)

        /// <summary>
        /// Dispose of all the ball noise cues in memory
        /// </summary>
        public void disposeBallCues()
        {
            ballCue.Dispose();
        }

        /// <summary>
        /// Dispose of all the base melody cues in memory
        /// </summary>
        public void disposeBaseCues()
        {
            baseCue1.Dispose();
            baseCue2.Dispose();
        }

        /// <summary>
        /// Dispose of all the drum cues in memory
        /// </summary>
        public void disposeDrumCues()
        {
            drumCue1.Dispose();
            drumCue2.Dispose();
            drumCue3.Dispose();
            drumCue4.Dispose();
        }

        /// <summary>
        /// Dispose of all the synth cues in memory
        /// </summary>
        public void disposeSynthCues()
        {
            synthCue1.Dispose();
            synthCue2.Dispose();
            synthCue3.Dispose();
            synthCue4.Dispose();
        }

        /// <summary>
        /// Dispose of all the harp cues in memory
        /// </summary>
        public void disposeHarpCues()
        {
            harpCue1.Dispose();
            harpCue2.Dispose();
            harpCue3.Dispose();
            harpCue4.Dispose();
        }

        /// <summary>
        /// Dispose of all the string cues in memory
        /// </summary>
        public void disposeStringCues()
        {
            stringCue1.Dispose();
            stringCue2.Dispose();
            stringCue3.Dispose();
            stringCue4.Dispose();
        }

        /// <summary>
        /// Dispose of all the special mode cues in memory
        /// </summary>
        public void disposeSpecialCues()
        {
            searchCue.Dispose();
            bwCue.Dispose();
        }

        #endregion

        #region Threads (InstrChanger, ChangeBase, ChangeDrum, ChangeStrings, ChangeHarp, ChangeSynth)

        /// <summary>
        /// Starts the thread for instrument modification depending on the colour.
        /// </summary>
        public void InstrChanger(Image.value colour)
        {
            disposeBallCues();
            resetBallCues();

            Console.Write("Listener: " + listener.Position + " Emitter: " + emitter.Position);
            ballCue.Apply3D(listener, emitter);
            ballCue.Play();

            Alter = new Thread(new ParameterizedThreadStart(ChangeMelody));
            Alter.Start(colour);
        }

        /// <summary>
        /// Starts the thread for base melody modification.
        /// </summary>
        public void ChangeBase()
        {
            Base = new Thread(new ThreadStart(ModifyBase));
            Base.Start();
        }

        /// <summary>
        /// Starts the thread for piano melody modification.
        /// </summary>
        public void ChangeDrums()
        {
            Drums = new Thread(new ThreadStart(ModifyDrums));
            Drums.Start();
        }

        /// <summary>
        /// Starts the thread for string melody modification.
        /// </summary>
        public void ChangeStrings()
        {
            Strings = new Thread(new ThreadStart(ModifyStrings));
            Strings.Start();
        }

        /// <summary>
        /// Starts the thread for synth melody modification.
        /// </summary>
        public void ChangeSynth()
        {
            Synth = new Thread(new ThreadStart(ModifySynth));
            Synth.Start();
        }

        /// <summary>
        /// Starts the thread for harp melody modification.
        /// </summary>
        public void ChangeHarp()
        {
            Harps = new Thread(new ThreadStart(ModifyHarp));
            Harps.Start();
        }

        #endregion

        #region Melody Modifiers (ModifyBase, ModifyDrums, ModifyStrings, ModifySynth, ModifyHarp)

        /// <summary>
        /// Modifies the initial melody.
        /// </summary>
        public void ModifyBase()
        {
            if (baseCue1 != null && baseCue1.IsPlaying)
            {
                baseCue1.Stop(AudioStopOptions.AsAuthored);
                while (!baseCue1.IsStopped) { }
                resetBaseCues();
                baseCue2.Play();
            }

            else if (baseCue2 != null && baseCue2.IsPlaying)
            {
                baseCue2.Stop(AudioStopOptions.AsAuthored);
                while (!baseCue2.IsStopped) { }
                resetBaseCues();
                baseCue1.Play();
            }
        }

        /// <summary>
        /// Modifies the drum loop.
        /// </summary>
        public void ModifyDrums()
        {
            if (drumCue1 != null && drumCue1.IsPlaying)
            {
                drumCue1.Stop(AudioStopOptions.AsAuthored);
                while (!drumCue1.IsStopped) { }
                if (drumCue1 != null && !drumCue2.IsPlaying && !drumCue3.IsPlaying && !drumCue4.IsPlaying)
                {
                    drumCue1.Dispose();
                    resetDrumCues();
                    drumCue2.Play();
                }
            }

            else if (drumCue2 != null && drumCue2.IsPlaying)
            {
                drumCue2.Stop(AudioStopOptions.AsAuthored);
                while (!drumCue2.IsStopped) { }
                if (drumCue1 != null && !drumCue2.IsPlaying && !drumCue3.IsPlaying && !drumCue4.IsPlaying)
                {
                    drumCue2.Dispose();
                    resetDrumCues();
                    drumCue3.Play();
                }
            }

            else if (drumCue3 != null && drumCue3.IsPlaying)
            {
                drumCue3.Stop(AudioStopOptions.AsAuthored);
                while (!drumCue3.IsStopped) { }
                if (drumCue1 != null && !drumCue2.IsPlaying && !drumCue3.IsPlaying && !drumCue4.IsPlaying)
                {
                    drumCue3.Dispose();
                    resetDrumCues();
                    drumCue4.Play();
                }
            }

            else if (drumCue4 != null && drumCue4.IsPlaying)
            {
                drumCue4.Stop(AudioStopOptions.AsAuthored);
                while (!drumCue4.IsStopped) { }
                if (drumCue1 != null && !drumCue2.IsPlaying && !drumCue3.IsPlaying && !drumCue4.IsPlaying)
                {
                    drumCue4.Dispose();
                    resetDrumCues();
                    drumCue1.Play();
                }
            }
        }

        /// <summary>
        /// Modifies the string melody.
        /// </summary>
        public void ModifyStrings()
        {
            if (stringCue1 != null && stringCue1.IsPlaying)
            {
                stringCue1.Stop(AudioStopOptions.AsAuthored);
                while (!stringCue1.IsStopped) { }
                if (stringCue1 != null && !stringCue1.IsPlaying)
                {
                    stringCue1.Dispose();
                    resetStringCues();
                    stringCue2.Play();
                }
            }

            else if (stringCue2 != null && stringCue2.IsPlaying)
            {
                stringCue2.Stop(AudioStopOptions.AsAuthored);
                while (!stringCue2.IsStopped) { }
                if (stringCue2 != null && !stringCue2.IsPlaying)
                {
                    stringCue2.Dispose();
                    resetStringCues();
                    stringCue3.Play();
                }
            }

            else if (stringCue3 != null && stringCue3.IsPlaying)
            {
                stringCue3.Stop(AudioStopOptions.AsAuthored);
                while (!stringCue3.IsStopped) { }
                if (stringCue3 != null && !stringCue3.IsPlaying)
                {
                    stringCue3.Dispose();
                    resetStringCues();
                    stringCue4.Play();
                }
            }

            else if (stringCue4 != null && stringCue4.IsPlaying)
            {
                stringCue4.Stop(AudioStopOptions.AsAuthored);
                while (!stringCue4.IsStopped) { }
                if (stringCue4 != null && !stringCue4.IsPlaying)
                {
                    stringCue4.Dispose();
                    resetStringCues();
                    stringCue1.Play();
                }
            }
        }

        /// <summary>
        /// Modifies the synth melody.
        /// </summary>
        public void ModifySynth()
        {
            if (synthCue1 != null && synthCue1.IsPlaying)
            {
                synthCue1.Stop(AudioStopOptions.AsAuthored);
                while (!synthCue1.IsStopped) { }
                if (synthCue1 != null && !synthCue1.IsPlaying)
                {
                    synthCue1.Dispose();
                    resetSynthCues();
                    synthCue2.Play();
                }
            }

            else if (synthCue2 != null && synthCue2.IsPlaying)
            {
                synthCue2.Stop(AudioStopOptions.AsAuthored);
                while (!synthCue2.IsStopped) { }
                if (synthCue2 != null && !synthCue2.IsPlaying)
                {
                    synthCue2.Dispose();
                    resetSynthCues();
                    synthCue3.Play();
                }
            }

            else if (synthCue3 != null && synthCue3.IsPlaying)
            {
                synthCue3.Stop(AudioStopOptions.AsAuthored);
                while (!synthCue3.IsStopped) { }
                if (synthCue3 != null && !synthCue3.IsPlaying)
                {
                    synthCue3.Dispose();
                    resetSynthCues();
                    synthCue4.Play();
                }
            }

            else if (synthCue4 != null && synthCue4.IsPlaying)
            {
                synthCue4.Stop(AudioStopOptions.AsAuthored);
                while (!synthCue4.IsStopped) { }
                if (synthCue4 != null && !synthCue4.IsPlaying)
                {
                    synthCue4.Dispose();
                    resetSynthCues();
                    synthCue1.Play();
                }
            }
        }

        /// <summary>
        /// Modifies the harp melody.
        /// </summary>
        public void ModifyHarp()
        {
            if (harpCue1 != null && harpCue1.IsPlaying)
            {
                harpCue1.Stop(AudioStopOptions.AsAuthored);
                while (!harpCue1.IsStopped) { }
                if (harpCue1 != null && !harpCue1.IsPlaying)
                {
                    harpCue1.Dispose();
                    resetHarpCues();
                    harpCue2.Play();
                }
            }

            else if (harpCue2 != null && harpCue2.IsPlaying)
            {
                harpCue2.Stop(AudioStopOptions.AsAuthored);
                while (!harpCue2.IsStopped) { }
                if (harpCue2 != null && !harpCue2.IsPlaying)
                {
                    harpCue2.Dispose();
                    resetHarpCues();
                    harpCue3.Play();
                }
            }

            else if (harpCue3 != null && harpCue3.IsPlaying)
            {
                harpCue3.Stop(AudioStopOptions.AsAuthored);
                while (!harpCue3.IsStopped) { }
                if (harpCue3 != null && !harpCue3.IsPlaying)
                {
                    harpCue3.Dispose();
                    resetHarpCues();
                    harpCue4.Play();
                }
            }

            else if (harpCue4 != null && harpCue4.IsPlaying)
            {
                harpCue4.Stop(AudioStopOptions.AsAuthored);
                while (!harpCue4.IsStopped) { }
                if (harpCue4 != null && !harpCue4.IsPlaying)
                {
                    harpCue4.Dispose();
                    resetHarpCues();
                    harpCue1.Play();
                }
            }
        }

        #endregion

        /// <summary>
        /// Method that regulates the modification of the song.
        /// </summary>
        public void ChangeMelody(Object colour)
        {
            ballColour = (Image.value)colour;

            if (baseCue1.IsPlaying)
                cue = 1;

            if (baseCue2.IsPlaying)
                cue = 2;

            // Base melody is modified every time balls are destroyed.
            ChangeBase();

            // If the destoyed balls are blue
            if (ballColour == Image.value.blue)
            {
                // If the drums aren't playing
                if (!drumCue1.IsPlaying && !drumCue2.IsPlaying && !drumCue3.IsPlaying && !drumCue4.IsPlaying)
                {
                    // If there's a drum cue in memory, reset it
                    if (drumCue1 != null)
                        resetDrumCues();

                    if (cue == 1)
                        // Wait until the base melody has finished.
                        while (!baseCue1.IsStopped)
                        {}

                    else if (cue == 2)
                        // Wait until the base melody has finished.
                        while (!baseCue2.IsStopped)
                        {}

                    // Play the drums now so that it is in sync.
                    drumCue1.Play();
                }

                // Modify the currently playing drums
                else
                    ChangeDrums();
            }

            // If the destoyed balls are green
            if (ballColour == Image.value.green)
            {
                // If the strings aren't playing
                if (!stringCue1.IsPlaying && !stringCue2.IsPlaying && !stringCue3.IsPlaying && !stringCue4.IsPlaying)
                {
                    // If there's a string cue in memory, reset it
                    if (stringCue1 != null)
                        resetStringCues();

                    if (cue == 1)
                        // Wait until the base melody has finished.
                        while (!baseCue1.IsStopped)
                        { }

                    else if (cue == 2)
                        // Wait until the base melody has finished.
                        while (!baseCue2.IsStopped)
                        { }

                    // Play the strings now so that it is in sync.
                    stringCue1.Play();                                 
                }

                // Modify the currently playing stringed instrument
                else
                    ChangeStrings();
            }

            // If the destoyed balls are red
            if (ballColour == Image.value.red)
            {
                // If the harp isn't playing
                if (!harpCue1.IsPlaying && !harpCue2.IsPlaying && !harpCue3.IsPlaying && !harpCue4.IsPlaying)
                {
                    // If there's a harp cue in memory, reset it
                    if (harpCue1 != null)
                        resetHarpCues();

                    if (cue == 1)
                        // Wait until the base melody has finished.
                        while (!baseCue1.IsStopped)
                        { }

                    else if (cue == 2)
                        // Wait until the base melody has finished.
                        while (!baseCue2.IsStopped)
                        { }

                    // Play the harp now so that it is in sync.
                    harpCue1.Play();
                }

                // Modify the currently playing harp
                else
                    ChangeHarp();
            }

            // If the destoyed balls are purple
            if (ballColour == Image.value.purple)
            {
                // If the synth isn't playing
                if (!synthCue1.IsPlaying && !synthCue2.IsPlaying && !synthCue3.IsPlaying && !synthCue4.IsPlaying)
                {
                    // If there's a synth cue in memory, destroy it
                    if (synthCue1 != null)
                        resetSynthCues();

                    if (cue == 1)
                        // Wait until the base melody has finished.
                        while (!baseCue1.IsStopped)
                        { }

                    else if (cue == 2)
                        // Wait until the base melody has finished.
                        while (!baseCue2.IsStopped)
                        { }

                    // Play the synth now so that it is in sync.
                    synthCue1.Play();
                }

                 // Modify the currently playing synth
                 else
                    ChangeSynth();
            }
        }

        /// <summary>
        /// In the event of a special mode, pause all the currently playing cues.
        /// </summary>
        public void pauseAllCues()
        {
            if (drumCue1.IsPlaying)
                drumCue1.Pause();
            if (drumCue2.IsPlaying)
                drumCue2.Pause();
            if (drumCue3.IsPlaying)
                drumCue3.Pause();
            if (drumCue4.IsPlaying)
                drumCue4.Pause();

            if (stringCue1.IsPlaying)
                stringCue1.Pause();
            if (stringCue2.IsPlaying)
                stringCue2.Pause();
            if (stringCue3.IsPlaying)
                stringCue3.Pause();
            if (stringCue4.IsPlaying)
                stringCue4.Pause();

            if (harpCue1.IsPlaying)
                harpCue1.Pause();
            if (harpCue2.IsPlaying)
                harpCue2.Pause();
            if (harpCue3.IsPlaying)
                harpCue3.Pause();
            if (harpCue4.IsPlaying)
                harpCue4.Pause();

            if (synthCue1.IsPlaying)
                synthCue1.Pause();
            if (synthCue2.IsPlaying)
                synthCue2.Pause();
            if (synthCue3.IsPlaying)
                synthCue3.Pause();
            if (synthCue4.IsPlaying)
                synthCue4.Pause();

            if (baseCue1.IsPlaying)
                baseCue1.Pause();
            if (baseCue2.IsPlaying)
                baseCue2.Pause();
        }

        /// <summary>
        /// When a special mode ends, resume the cues that were paused.
        /// </summary>
        public void resumeAllCues()
        {
            if (baseCue2.IsPaused)
                baseCue2.Resume();
            if (baseCue1.IsPaused)
                baseCue1.Resume();

            if (synthCue4.IsPaused)
                synthCue4.Resume();
            if (synthCue3.IsPaused)
                synthCue3.Resume();
            if (synthCue2.IsPaused)
                synthCue2.Resume();
            if (synthCue1.IsPaused)
                synthCue1.Resume();

            if (harpCue4.IsPaused)
                harpCue4.Resume();
            if (harpCue3.IsPaused)
                harpCue3.Resume();
            if (harpCue2.IsPaused)
                harpCue2.Resume();
            if (harpCue1.IsPaused)
                harpCue1.Resume();

            if (stringCue4.IsPaused)
                stringCue4.Resume();
            if (stringCue3.IsPaused)
                stringCue3.Resume();
            if (stringCue2.IsPaused)
                stringCue2.Resume();
            if (stringCue1.IsPaused)
                stringCue1.Resume();

            if (drumCue4.IsPaused)
                drumCue4.Resume();
            if (drumCue3.IsPaused)
                drumCue3.Resume();
            if (drumCue2.IsPaused)
                drumCue2.Resume();
            if (drumCue1.IsPaused)
                drumCue1.Resume();
        }            

        /// <summary>
        /// Updates the audio in the game.
        /// </summary>
        public void UpdateAudio()
        {
            musicEngine.Update();
        }

        /// <summary>
        /// Sets the position of the ball that caused an explosion.
        /// </summary>
        public void setPosition(Vector3 pos)
        {
            emitter.Position = pos;
        }

        /// <summary>
        /// Suspend any threads currently executing
        /// </summary>       
        public void SuspendThreads()
        {
            if (Alter.IsAlive)
                Alter.Suspend();
            if (Base.IsAlive)
                Base.Suspend();
            if (Synth.IsAlive)
                Synth.Suspend();
            if (Harps.IsAlive)
                Harps.Suspend();
            if (Strings.IsAlive)
                Strings.Suspend();
            if (Drums.IsAlive)
                Drums.Suspend();
        }

        /// <summary>
        /// Resume any threads currently suspended
        /// </summary>       
        public void ResumeThreads()
        {
            if (Alter.IsAlive)
                Alter.Resume();
            if (Base.IsAlive)
                Base.Resume();
            if (Synth.IsAlive)
                Synth.Resume();
            if (Harps.IsAlive)
               Harps.Resume();
            if (Strings.IsAlive)
                Strings.Resume();
            if (Drums.IsAlive)
                Drums.Resume();
        }
    }
}
