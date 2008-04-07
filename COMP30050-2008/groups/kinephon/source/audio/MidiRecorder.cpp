#include "MidiRecorder.h"

namespace audio
{
    
MidiRecorder::MidiRecorder() {
    
   
}

MidiRecorder::~MidiRecorder() {}

bool MidiRecorder::openFile(int noTracks) {
    
    return true;
}

bool MidiRecorder::openFile(string fileName, int noTracks) {
    
    return false;
}

bool MidiRecorder::closeFile() {
    myFile_.open("writeFile.mid", ios::out | ios::binary);
    if( !myFile_.is_open() )
    {
        return false;
    }
    
    /*header chunk looks like: 
      4D 54 68 64 (MThd) 00 00 00 06 (FIXED) ff ff nn nn dd dd
      # ff ff is the file format. There are 3 formats:
        (0 - single-track; 1 - multiple tracks, synchronous; 2 - multiple tracks, asynchronous
      # nn nn is the number of tracks in the midi file.
      # dd dd is the number of delta-time ticks per quarter note. 
    */
    const char header_first[8] = { 'M', 'T', 'h', 'd', 0, 0, 0, 6 };
    myFile_.write(header_first, 8);
    
    char header_second[6] = { 0x00, 0x01, 0x00, 0x02, 0x00, 0xF0 }; 
    myFile_.write(header_second, 6);
    
    /*track chunks, header:
      4D 54 72 6B (MTrk) xx xx xx xx (lenght of track not including header in bytes) 
     */
    const char track_header[8] = { 0x4D, 0x54, 0x72, 0x6B, 0x00, 0x00, 0x00, 0x1C};
    const char end_of_track[4] = {0x00, 0xFF, 0x2F, 0x00};//absolutely necessary
    char time_signature[8] = {0x00, 0xFF, 0x58, 0x04, 0x04, 0x02, 0x18, 0x08};//opt 
    char tempo[7] = {0x00, 0xFF, 0x51, 0x03, 0x07, 0xA1, 0x20};//opt 
    char smpte_offset[9] = {0x00, 0xFF, 0x54, 0x05, 0x40, 0x00, 0x00, 0x00, 0x00};//opt
    //char track_name[8] = {0x00, 0xFF, 0x03, 0x04 , 't', 'e', 's', 't'};//opt
   
    
    //Write first track:
    myFile_.write(track_header, 8*sizeof(char));
    
    //count bytes in track from here:
    myFile_.write(time_signature, 8);
    myFile_.write(tempo, 7);
    myFile_.write(smpte_offset, 9);
    myFile_.write(end_of_track, 4);
    
    
    //write second track:
    const char track_header_two[8] = { 0x4D, 0x54, 0x72, 0x6B, 0x00, 0x00, 0x01, 0x54};
    //write length of track:
    int len = trackOne_.size();
    cout << len;

    myFile_.write(track_header_two, 8);
  
    
    myFile_.write((char*)&trackOne_[0], 336);//actual data
    myFile_.write(end_of_track, 4);  
    myFile_.close();
    return true;
}

bool MidiRecorder::write(vector<uchar> event) {
    //add delta time, need to know if it's a NOTE ON/OFF, these have to be equally spaced use the 72 as set for length of quarter note,
    //the rest can have delta time 0
    if (event[0] < 160 && event[0] >= 144){//NOTE ON
        trackOne_.push_back(72);
        
    }
    else if (event[0] < 143 && event[0] >= 128){//NOTE OFF
        trackOne_.push_back(72);
        
    }
    else{
        trackOne_.push_back(0);
         
    }
        
    //add midi event data:
    trackOne_.insert(trackOne_.end(), event.begin(), event.end());
   /*
    static int count = 0;
    if(count < 64){
        cout << " " << event[0] <<" " <<  event[1]; 
        if(event.size() == 3) 
            cout << " " << event[2];
        cout << endl;
        count++;
    }*/
    return true;
}



    
    
}
