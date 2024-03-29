#ifndef MIDIPLAYER_H_
#define MIDIPLAYER_H_

#include "Channel.h"
#include "RtMidi.h"
#include "RtError.h"
#include "../type.h"
#include <string>
using namespace std;

namespace audio {
//platform specific sleep functions
#if defined(__WINDOWS_MM__)
  #include <windows.h>
  #define SLEEP( milliseconds ) Sleep( (DWORD) milliseconds ) 
#else // Unix variants
  #include <unistd.h>
  #define SLEEP( milliseconds ) usleep( (unsigned long) (milliseconds * 1000.0) )
#endif

enum Channels {CHANNEL_LEAD, CHANNEL_ACCOMPANY, CHANNEL_PERCUSSION, CHANNEL_CHORD};
	
/**
 * General Midi player.
 * This class provides facilities to connect to available Midi ports and to
 * play audio based on several dedicated channels, such as rhythm, chords and percussion.
 * This class provides a middle level of abstraction, so it is kept intentionally
 * technical. It is intended not to obscure the details of the Midi messaging mechanism.  
 * @author ED
 * @version  1.0
 */
class MidiPlayer
{
public:
	/**
	 * Creates a new MidiPlayer that is not yet connected to any port.
	 * <code>initialize()</code> has to be called to enable playback.       
	 */
	MidiPlayer();
	
    /**
     * Destroy this MidiPlayer.
     */
	virtual ~MidiPlayer();
	
	/**
	 * Attempts to connect to an available MIDI port.
	 * If more than one port is available, then a choice is given.
    * @param recording true if music piece is to be recorded  
    * @param portName name of port to be used (varies depending on synthesizer)     
	 * @return true if connection has been established, false if an error occured     
	 */
	bool initialize(bool recording, string portName);
	
	/**
	 * Releases all notes except for the chords.
	 * This function needs to be called before new notes are played, 
    * otherwise they will just melt together in one big blur.
    * @param deltaTime delay to be used when writing the MIDI file
    */
	void panic(ulong deltaTime);

    /**
     * Releases all notes played on the given channel.
     * It should be possible to release the channels independently. 
     * They have to be released regularly, or the result will be an
     * indistinguishable blur.
     * @param channel Channel to be released
     * @param deltaTime delay to be used when writing the MIDI file
     */
    void releaseChannel(Channels channel, ulong deltaTime);
    
    /**
	 * Set if music piece is to be recorded.
	 * @param  setOn true when setting recoding ON, false when setting OFF
	 * @return true if recording is ready/file has been closed
	 */
	bool setRecording(bool setOn);
	
	/**
	 * Sends a Control Change message to one channel.
	 * According to General MIDI 2 specification there are the following options:
	 * Bank Select (cc#0/32)-> synthesizer specific to allow for more than 128 instruments, we don't need that
    * Modulation Depth (cc#1)-> frequency moves up&down in a repetitive way, tremolo, characteristic to some instr.
    * Portamento Time (cc#5)-> no audible effect
    * Channel Volume (cc#7)-> simple loudness
    * Balance (cc#8)-> balance between left/right speaker --> no effect audible
    * Pan (cc#10)->panorama, i.e. right and left speaker --> no effect
    * Expression (cc#11)-> allows for volume dynamics to play e.g. crescendos. no fixed spec
    * Hold1 (Damper) (cc#64)-> 0-63 hold pedal off, 64-127 on.
    * Portamento ON/OFF (cc#65)-> 0-63 off, 64-127 on
    * Sostenuto (cc#66)->only already playing notes will be kept, until a signla 0-63 is received
    * Soft (cc#67)-> if ON, then the velocity values of the notes are being reduces slightly, so they seem 'softer'
    * Filter Resonance (Timbre/Harmonic Intensity) (cc#71)->has effcect on notes being 
    * Release Time (cc#72)
    * Attack Time (cc#73) 
    * Brightness (cc#74)
    * Decay Time (cc#75) (new message)
    * Vibrato Rate (cc#76) (new message)
    * Vibrato Depth (cc#77) (new message)
    * Vibrato Delay (cc#78) (new message)
    * Reverb Send Level (cc#91)
    * Chorus Send Level (cc#93)
    * Data Entry (cc#6/38)
    * RPN LSB/MSB (cc#100/101)
	 * Note: which of these will actually be implemented is yet to be decided.
	 * @param channel the channel the message is to be sent to      
	 * @param function the chosen function 
	 * @param value the value of the function
    * @param deltaTime delay to be used when writing the MIDI file  
	 */
	void sendControlChange(Channels channel, uchar function, uchar value, ulong deltaTime);
	
	/**
	 * Sends a Program Change message to one channel.
	 * According to MIDI standard there are 127 different options, which can be grouped 
	 * as follows:
	 * ~ piano
	 * ~ chromatic percussion
	 * ~ organ
	 * ~ guitar
	 * ~ bass
	 * ~ strings
	 * ~ brass
	 * ~ reed
	 * ~ pipe
	 * ~ synth lead
	 * ~ synth pad
	 * ~ synth effects
	 * ~ ethnic 
	 * ~ percussive
	 * ~ sound effects
	 * Note: which of these will actually be used is yet to be decided.
	 * @param channel the channel the message is to be sent to       
	 * @param program selected instrument number
	 */
	void sendProgramChange(Channels channel, uchar program);
	
  	/**
	 * Plays a lead note.
	 * @param pitch the value of the note to be played  
	 * @param velocity velocity of lead note   
    * @param deltaTime delay to be used when writing the MIDI file         
	 */
	void playLead(uchar pitch, uchar velocity, ulong deltaTime);
	
	/**
	 * Plays a accompaniment note.
	 * @param pitch the value of the note to be played  
	 * @param velocity velocity of the note     
    * @param deltaTime delay to be used when writing the MIDI file       
	 */
	void playAccompaniment(uchar pitch, uchar velocity, ulong deltaTime);
	
	/**
	 * Plays a chord.
	 * @param chord the chord to be played  
	 * @param velocity velocity of the chord     
    * @param deltaTime delay to be used when writing the MIDI file       
	 */
	void playChord(uchar chord, uchar velocity, ulong deltaTime);
	
  	/**
	 * Plays a percussion note.
	 * @param pitch the pitch of the note to be played  
	 * @param velocity velocity of the note  
    * @param deltaTime delay to be used when writing the MIDI file          
	 */
	void playPercussion(uchar pitch, uchar velocity, ulong deltaTime);
	
	
  	/**
	 * Plays a note of given pitch and velocity on given channel.
	 * The pitch and velocity value will be interpreted differently for different channels.
	 * @param channel the chnnel to be used
	 * @param pitch the value of the note to be played
	 * @param velocity the velocity of the note
    * @param deltaTime delay to be used when writing the MIDI file  
	 */
	void playNote(Channels channel, uchar pitch, uchar velocity, ulong deltaTime);
    
    /**
     * Releases the last played note on given channel.
     * @param channel the chnnel to be used
     * @param pitch the value of the note to be played
     * @param deltaTime delay to be used when writing the MIDI file  
     */
	void releaseNote(Channels channel, uchar pitch, ulong deltaTime);
	
private:
	RtMidiOut* midiout_;
   MidiRecorder* recorder_;
	vector<uchar> chords_;	
	bool isConnected_;
	Channel* leadChannel_;
	Channel* chordChannel_;
	Channel* accompanyChannel_;
	Channel* percussionChannel_;
};
}
#endif /*MIDIPLAYER_H_*/
