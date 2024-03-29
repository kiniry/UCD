#include "Conductor.h"
namespace audio {
    
Conductor::Conductor():
	midi_(NULL),
   noNoteCount_(0),
	timeStep_(0),
	melodyStep_(0),
   melodyLength_(0),
   pedalingFreq_(0),
   pedalingCounter_(0),
	hasAccompaniment_(false),
	hasChords_(false),
	hasRhythm_(false),
   hasMelody_(false),
   hasPedaling_(false),
	hasReverb_(false),
   modulation_(0),//default value to NO modulation
	rhythm_(RHYTHM_NONE),
	chords_(CHORDS_NONE),
   currentChord_(60),//default middle C, since we need something if we start with NO_NOTE
	dynamics_(DYNAMICS_FORTE)
    
{}

Conductor::~Conductor() {
    setModulation(0); //just in case reset this, or it will be remembered
	delete midi_;
}

bool Conductor::initialize(bool recording, string portName) {
	midi_ = new MidiPlayer();
	if (!midi_->initialize(recording, portName))
    	return false;
    else {
        setInstrument(INSTRUMENT_CLASSIC);
        setDynamics(DYNAMICS_FORTE);
        setModulation(0); //just in case someone forgot to switch it off last time
    	return true;//now we have 4 channels to play on
    } 
}

//###########################
//# SIMPLE PLAY 
//###########################
void Conductor::play() {
    ulong deltaTime = 58 + noNoteCount_ * 58;
    //############
    //# TIME STEP 0
    //############
	if (timeStep_ == 0) {//1st quarter 
        if (hasMelody_) {// if no melody, no accompaniment/chords, only rhythm can be played
            uchar pitch = melody_[melodyStep_];
            uchar pitchVelocity = melody_[melodyStep_];
            melodyStep_ = (melodyStep_ + 2) % melodyLength_;
            if (pitch != NO_NOTE) {
                midi_->releaseChannel(CHANNEL_LEAD, deltaTime);
                deltaTime = 0;
                noNoteCount_ = 0;
                midi_->playLead(pitch, pitchVelocity, 0);
                currentChord_ = pitch-12;   
                if (hasAccompaniment_) {
                midi_->releaseChannel(CHANNEL_ACCOMPANY, 0);
                midi_->playAccompaniment(pitch-12, pitchVelocity, 0); 
                }
            }
            else 
                noNoteCount_++;
                
             if (hasChords_) {
               midi_->sendControlChange(CHANNEL_CHORD, 64, 127, deltaTime); //turn hold ON 
               deltaTime = 0;
               noNoteCount_ = 0;
                    if (chords_ == CHORDS_FIRST)         midi_->playChord(currentChord_,20, 0);
                    if (chords_ == CHORDS_SECOND)   midi_->playChord(currentChord_-4,20, 0);
                    if (chords_ == CHORDS_THIRD)        midi_->playChord(currentChord_-7,20, 0);
                    if (chords_ == CHORDS_123)              midi_->playNote(CHANNEL_CHORD, currentChord_,30, 0);
             }
        }
        if (hasRhythm_) {//RHYTHM_1_4, RHYTHM_2_4, RHYTHM_3_4, RHYTHM_4_4, RHYTHM_1_2, RHYTHM_2_3
            midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
            deltaTime = 0;
            midi_->playPercussion(60, 127, 0);//high attack velocity
            noNoteCount_ = 0;
        }
        
    }
    //############
    //# TIME STEP 1
    //############
    else if (timeStep_ == 1) {//2nd quarter
        if (hasMelody_) {// if no melody, no accompaniment/chords, only rhythm can be played
            uchar pitch = melody_[melodyStep_];
            uchar pitchVelocity = melody_[melodyStep_];
            melodyStep_ = (melodyStep_ + 2) % melodyLength_;
            if (pitch != NO_NOTE) {
                midi_->releaseChannel(CHANNEL_LEAD, deltaTime);
                deltaTime = 0;
                noNoteCount_ = 0;
                midi_->playLead(pitch, pitchVelocity, 0);
                if (hasAccompaniment_) {
                  midi_->releaseChannel(CHANNEL_ACCOMPANY, 0);
                  midi_->playAccompaniment(pitch-12, pitchVelocity, 0);
                }
            }
            else
            noNoteCount_++;
            
             if (hasChords_) {
                if (chords_ == CHORDS_123){
                    midi_->playNote(CHANNEL_CHORD, currentChord_+4,30, deltaTime);
                    deltaTime = 0;
                    noNoteCount_ = 0;
                    }
            }
          
        }
        if (hasRhythm_) {//RHYTHM_1_4, RHYTHM_2_4, RHYTHM_3_4, RHYTHM_4_4, RHYTHM_1_2, RHYTHM_2_3
            if (rhythm_ == RHYTHM_2_4 || rhythm_ == RHYTHM_3_4 || rhythm_ == RHYTHM_4_4) {
                midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
                deltaTime = 0;
                midi_->playPercussion(60, 127, 0);
                noNoteCount_ = 0;
            }
            
        }
      
    }
    //############
    //# TIME STEP 2
    //############
    else if (timeStep_ == 2) {//3rd quarter
        if (hasMelody_) {// if no melody, no accompaniment/chords, only rhythm can be played
            uchar pitch = melody_[melodyStep_];
            uchar pitchVelocity = melody_[melodyStep_];
            melodyStep_ = (melodyStep_ + 2) % melodyLength_;
            if (pitch != NO_NOTE) {
                midi_->releaseChannel(CHANNEL_LEAD, deltaTime);
                deltaTime = 0;
                midi_->playLead(pitch, pitchVelocity, 0);
                if (hasAccompaniment_) {
                  midi_->releaseChannel(CHANNEL_ACCOMPANY, 0);
                  midi_->playAccompaniment(pitch-12, pitchVelocity, 0);
                }
            noNoteCount_ = 0;
            }
            else
                noNoteCount_++;
        
            if (hasChords_) {
                if (chords_ == CHORDS_123){
                midi_->playNote(CHANNEL_CHORD, currentChord_+7,30, deltaTime);
                deltaTime = 0;
                noNoteCount_ = 0;
                }
            }
        }
        
        if (hasRhythm_) {//RHYTHM_1_4, RHYTHM_2_4, RHYTHM_3_4, RHYTHM_4_4, RHYTHM_1_2, RHYTHM_2_3
            if (rhythm_ == RHYTHM_3_4 || rhythm_ == RHYTHM_4_4 || rhythm_ == RHYTHM_1_2) {
                midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
                deltaTime = 0;
                midi_->playPercussion(60, 127, 0);
                noNoteCount_ = 0;
            }
        }
              
    }
    //############
    //# TIME STEP 3
    //############
    else if (timeStep_ == 3) {//4th quarter
        if (hasMelody_) {// if no melody, no accompaniment/chords, only rhythm can be played
            uchar pitch = melody_[melodyStep_];
            uchar pitchVelocity = melody_[melodyStep_];
            melodyStep_ = (melodyStep_ + 2) % melodyLength_;
            if (pitch != NO_NOTE) {
                midi_->releaseChannel(CHANNEL_LEAD, deltaTime);
                deltaTime = 0;
                midi_->playLead(pitch, pitchVelocity, 0);
                if (hasAccompaniment_) {
                    midi_->releaseChannel(CHANNEL_ACCOMPANY, 0);
                    midi_->playAccompaniment(pitch-12, pitchVelocity, 0);
                }
            noNoteCount_ = 0;
            }
            else
                noNoteCount_++;
        
           if (hasChords_) {
            midi_->sendControlChange(CHANNEL_CHORD, 64, 0, deltaTime); //turn hold OFF
            deltaTime = 0;
            noNoteCount_ = 0;
                if (chords_ == CHORDS_123){
                midi_->releaseNote(CHANNEL_CHORD, currentChord_, 0);
                midi_->releaseNote(CHANNEL_CHORD, currentChord_+4, 0);
                midi_->releaseNote(CHANNEL_CHORD, currentChord_+7, 0);
                }
                else {
                midi_->releaseChannel(CHANNEL_CHORD, 0);
                }
            
            }
        }
        
                
        if (hasRhythm_) {//RHYTHM_1_4, RHYTHM_2_4, RHYTHM_3_4, RHYTHM_4_4, RHYTHM_1_2, RHYTHM_2_3
            if (rhythm_ == RHYTHM_4_4) {
                midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
                deltaTime = 0;
                midi_->playPercussion(60, 127, 0);
                noNoteCount_ = 0;
            }
        }
               
    }
    //do this in any case:
    if (hasPedaling_){
        if (pedalingCounter_ == 0) {
            
            midi_->sendControlChange(CHANNEL_LEAD, 64, 0, 0); //turn hold OFF
            midi_->sendControlChange(CHANNEL_ACCOMPANY, 64, 0, 0); //turn hold OFF       
            midi_->sendControlChange(CHANNEL_LEAD, 64, 127, 0); //turn hold ON
            midi_->sendControlChange(CHANNEL_ACCOMPANY, 64, 127, 0); //turn hold OFF
        }
        pedalingCounter_ = (pedalingCounter_ + 1) % pedalingFreq_;   
    }    
    
    timeStep_ = (timeStep_ + 1) % 4;
}
//###########################
//# PLAY WITH LEAD
//###########################
void Conductor::play(uchar note, int octave, uchar pitchVelocity){
    ulong deltaTime = 58 + noNoteCount_ * 58;
    uchar pitch = note + octave * 12;
    if (pitch != NO_NOTE) {//if we dont have NO_NOTE message
        midi_->releaseChannel(CHANNEL_LEAD, deltaTime);
        deltaTime = 0;
        midi_->playLead(pitch, pitchVelocity, 0);
        noNoteCount_ = 0;
    }
    else //now we need to put in a break
        noNoteCount_++;
    //############
    //# TIME STEP 0
    //############
    if (timeStep_ == 0) {//1st quarter 
        if (pitch != NO_NOTE) {
         currentChord_ = pitch-12; //save this for later in case we have a NO_NOTE
                
            if (hasAccompaniment_) {
                midi_->releaseChannel(CHANNEL_ACCOMPANY, 0);
                midi_->playAccompaniment(pitch-12, pitchVelocity, 0); 
            }
        }
        if (hasChords_) {
         midi_->sendControlChange(CHANNEL_CHORD, 64, 127, deltaTime); //turn hold ON
            if (chords_ == CHORDS_FIRST)        midi_->playChord(currentChord_,20, 0);
            if (chords_ == CHORDS_SECOND)   midi_->playChord(currentChord_-4,20, 0);
            if (chords_ == CHORDS_THIRD)        midi_->playChord(currentChord_-7,20, 0);
            if (chords_ == CHORDS_123)              midi_->playNote(CHANNEL_CHORD, currentChord_,30, 0);
         deltaTime = 0;
         noNoteCount_ = 0;        
         
        }
        
        if (hasRhythm_) {
            midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
            deltaTime = 0;
            midi_->playPercussion(60, 127, 0);//high attack velocity
            noNoteCount_ = 0;
        }
       
    }
     //############
    //# TIME STEP 1
    //############
    else if (timeStep_ == 1) {//2nd quarter
        if (pitch != NO_NOTE) {
            if (hasAccompaniment_) {
                midi_->releaseChannel(CHANNEL_ACCOMPANY, 0);
                midi_->playAccompaniment(pitch-12, pitchVelocity, 0);
            }
         }
        if (hasChords_) {
            if (chords_ == CHORDS_123) {
            midi_->playNote(CHANNEL_CHORD, currentChord_+4,30, deltaTime);
            deltaTime = 0;
            noNoteCount_ = 0;
            }
        }
        if (hasRhythm_) {
            if (rhythm_ == RHYTHM_2_4 || rhythm_ == RHYTHM_3_4 || rhythm_ == RHYTHM_4_4) {
                midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
                deltaTime = 0;
                midi_->playPercussion(60, 127, 0);
                noNoteCount_ = 0;
            }
        }
       
    }
     //############
    //# TIME STEP 2
    //############
    else if (timeStep_ == 2) {//3rd quarter
        if (pitch != NO_NOTE) {
            if (hasAccompaniment_) {
                midi_->releaseChannel(CHANNEL_ACCOMPANY, 0);
                midi_->playAccompaniment(pitch-12, pitchVelocity, 0);
            }
        }
        if (hasChords_) {
            if (chords_ == CHORDS_123) {
            midi_->playNote(CHANNEL_CHORD, currentChord_+7,30, deltaTime);
            deltaTime = 0;
            noNoteCount_ = 0;
            }
        }
        
        if (hasRhythm_) {
            if (rhythm_ == RHYTHM_3_4 || rhythm_ == RHYTHM_4_4 || rhythm_ == RHYTHM_1_2) {
                midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
                deltaTime = 0;
                midi_->playPercussion(60, 127, 0);
                noNoteCount_ = 0;
            }
        }
              
    }
    //############
    //# TIME STEP 3
    //############
    else if (timeStep_ == 3) {//4th quarter
        if (pitch != NO_NOTE) {
            if (hasAccompaniment_) {
                midi_->releaseChannel(CHANNEL_ACCOMPANY, 0);
                midi_->playAccompaniment(pitch-12, pitchVelocity, 0);
            }
        }        
        if (hasChords_) {
        midi_->sendControlChange(CHANNEL_CHORD, 64, 0, deltaTime); //turn hold OFF
        deltaTime = 0;
        noNoteCount_ = 0;
           if (chords_ == CHORDS_123){
            midi_->releaseNote(CHANNEL_CHORD, currentChord_, 0);
            midi_->releaseNote(CHANNEL_CHORD, currentChord_+4, 0);
            midi_->releaseNote(CHANNEL_CHORD, currentChord_+7, 0);
           }
           else{
            midi_->releaseChannel(CHANNEL_CHORD, 0);
           }
           
         }
        
        if (hasRhythm_) {
            if (rhythm_ == RHYTHM_4_4) {
                midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
                deltaTime = 0;
                midi_->playPercussion(60, 127, 0);
                noNoteCount_ = 0;
            }
           
        }
       
    }
    //do this in every case:
    
    if (hasPedaling_) {
        cout << " time: "<< timeStep_ << " pedalingCounter: " << pedalingCounter_ << endl;
        if (pedalingCounter_ == 0) {
            
            midi_->sendControlChange(CHANNEL_LEAD, 64, 0, 0); //turn hold OFF
            midi_->sendControlChange(CHANNEL_ACCOMPANY, 64, 0, 0); //turn hold OFF       
            midi_->sendControlChange(CHANNEL_LEAD, 64, 127, 0); //turn hold ON
            midi_->sendControlChange(CHANNEL_ACCOMPANY, 64, 127, 0); //turn hold OFF
        }
        pedalingCounter_ = (pedalingCounter_ + 1) % pedalingFreq_;   
    }    
    if (hasMelody_) {//melody is ignored, but keep counting
            melodyStep_ = (melodyStep_ + 2) % melodyLength_;
    }
    timeStep_ = (timeStep_ + 1) % 4;
}

//###########################
//# PLAY WITH LEAD AND ACCOMPANIMENT
//###########################	
void Conductor::play(uchar note, int octave, uchar pitchVelocity, uchar accNote, int accOctave, uchar accVelocity) {
    ulong deltaTime = 58 + noNoteCount_ * 58;
    uchar pitch = note + octave * 12;
    uchar accompany = accNote + accOctave * 12;
    if (pitch != NO_NOTE) {//if we dont have NO_NOTE message
        midi_->releaseChannel(CHANNEL_LEAD, deltaTime);
        deltaTime = 0;
        midi_->playLead(pitch, pitchVelocity, 0);
    }
    if (accompany != NO_NOTE) {
        midi_->releaseChannel(CHANNEL_ACCOMPANY, deltaTime);
        deltaTime = 0;
        midi_->playAccompaniment(accompany, accVelocity, 0); 
    }
    if (pitch == NO_NOTE && accompany == NO_NOTE) 
        noNoteCount_++;
    else
        noNoteCount_ = 0; 
     
    //############
    //# TIME STEP 0
    //############   
    if (timeStep_ == 0) {//1st quarter 
        if (pitch != NO_NOTE) {
            currentChord_ = pitch-12;
        }
         if (hasChords_) {
            midi_->sendControlChange(CHANNEL_CHORD, 64, 127, deltaTime); //turn hold ON
            deltaTime = 0;   
            noNoteCount_ = 0;   
            if (chords_ == CHORDS_FIRST)        midi_->playChord(currentChord_,20, 0);
            if (chords_ == CHORDS_SECOND)   midi_->playChord(currentChord_-4,20, 0);
            if (chords_ == CHORDS_THIRD)        midi_->playChord(currentChord_-7,20, 0);
            if (chords_ == CHORDS_123)              midi_->playNote(CHANNEL_CHORD, currentChord_,30, 0);
          
        
        }
        if (hasRhythm_) {
            midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
            deltaTime = 0;
            midi_->playPercussion(60, 127, 0);//high attack velocity
            noNoteCount_ = 0;
        }
       
    }
    //############
    //# TIME STEP 1
    //############
    else if (timeStep_ == 1) {//2nd quarter
        
         if (hasChords_) {
            if (chords_ == CHORDS_123) {
            midi_->playNote(CHANNEL_CHORD, currentChord_+4,30, deltaTime);
            deltaTime = 0;
            noNoteCount_ = 0;
            }
        }
        if (hasRhythm_) {
            if (rhythm_ == RHYTHM_2_4 || rhythm_ == RHYTHM_3_4 || rhythm_ == RHYTHM_4_4) {
                midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
                deltaTime = 0;
                midi_->playPercussion(60, 127, 0);
                noNoteCount_ = 0;
            }  
        }
       
    }
    //############
    //# TIME STEP 2
    //############
    else if (timeStep_ == 2) {//3rd quarter
         if (hasChords_) {
            if (chords_ == CHORDS_123) {
            midi_->playNote(CHANNEL_CHORD, currentChord_+7,30, deltaTime);
            deltaTime = 0;
            noNoteCount_ = 0;
            }
        }
        if (hasRhythm_) {
            if (rhythm_ == RHYTHM_3_4 || rhythm_ == RHYTHM_4_4 || rhythm_ == RHYTHM_1_2) {
                midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
                deltaTime = 0;
                midi_->playPercussion(60, 127, 0);
                noNoteCount_ = 0;
            }
        }
            
    }
    //############
    //# TIME STEP 3
    //############
    else if (timeStep_ == 3) {//4th quarter
        if (hasChords_) {
        midi_->sendControlChange(CHANNEL_CHORD, 64, 0, deltaTime); //turn hold OFF
        deltaTime = 0;
        noNoteCount_ = 0;
          if (chords_ == CHORDS_123){
            midi_->releaseNote(CHANNEL_CHORD, currentChord_, 0);
            midi_->releaseNote(CHANNEL_CHORD, currentChord_+4, 0);
            midi_->releaseNote(CHANNEL_CHORD, currentChord_+7, 0);
           }
           else {
            midi_->releaseChannel(CHANNEL_CHORD, 0);
           }
      
         }
        if (hasRhythm_) {
            if (rhythm_ == RHYTHM_4_4) {
                midi_->releaseChannel(CHANNEL_PERCUSSION, deltaTime);
                deltaTime = 0;
                midi_->playPercussion(60, 127, 0);
                noNoteCount_ = 0;
            }
        }
        
    }
    //do this in every case:
    if (hasPedaling_) {
        cout << " time: "<< timeStep_ << " pedalingCounter: " << pedalingCounter_ << endl;
        if (pedalingCounter_ == 0) {
            
            midi_->sendControlChange(CHANNEL_LEAD, 64, 0, 0); //turn hold OFF
            midi_->sendControlChange(CHANNEL_ACCOMPANY, 64, 0, 0); //turn hold OFF       
            midi_->sendControlChange(CHANNEL_LEAD, 64, 127, 0); //turn hold ON
            midi_->sendControlChange(CHANNEL_ACCOMPANY, 64, 127, 0); //turn hold OFF
        }
        pedalingCounter_ = (pedalingCounter_ + 1) % pedalingFreq_;   
    }    
    if (hasMelody_) {//melody is ignored, but keep counting
            melodyStep_ = (melodyStep_ + 2) % melodyLength_;
    }
    timeStep_ = (timeStep_ + 1) % 4;
}

void Conductor::playImmediate(uchar note, int octave, uchar velocity, ulong deltaTime) {
    uchar pitch = note + octave * 12;
    midi_->releaseChannel(CHANNEL_LEAD, 0);
    midi_->playLead(pitch, velocity, 0);
}	

Instrument Conductor::getInstrument() {
    return instrument_;
}
 
bool Conductor::getAccompaniment() {
    return hasAccompaniment_;
}
 
Chords Conductor::getChords() {
    return chords_;
}

Rhythm Conductor::getRhythm() {
    return rhythm_;
}
     
Dynamics Conductor::getDynamics() {
    return dynamics_;
}

vector<uchar> Conductor::getMelody() {
    return melody_;
}
    
bool Conductor::getPedaling() {
    return hasPedaling_;
}
     
bool Conductor::getReverberation() {
    return hasReverb_;
}
    
//make accompaniment the same as the lead
void Conductor::setInstrument(Instrument instrument) {
    if (instrument == INSTRUMENT_CLASSIC) {
        midi_->sendProgramChange(CHANNEL_LEAD, 0);
        midi_->sendProgramChange(CHANNEL_ACCOMPANY, 0);
        midi_->sendProgramChange(CHANNEL_CHORD, 48);
        midi_->sendProgramChange(CHANNEL_PERCUSSION, 118);
    }
    else if (instrument == INSTRUMENT_CRAZY) {
        midi_->sendProgramChange(CHANNEL_LEAD, 115);
        midi_->sendProgramChange(CHANNEL_ACCOMPANY, 115);
        midi_->sendProgramChange(CHANNEL_CHORD, 122);
        midi_->sendProgramChange(CHANNEL_PERCUSSION, 121);
    }
    else if (instrument == INSTRUMENT_WIND) {
        midi_->sendProgramChange(CHANNEL_LEAD, 56);
        midi_->sendProgramChange(CHANNEL_ACCOMPANY, 56);
        midi_->sendProgramChange(CHANNEL_CHORD, 66);
        midi_->sendProgramChange(CHANNEL_PERCUSSION, 118);
    }
    instrument_ = instrument;
}
	
//make paramOne == 0 mean that the accompaniment plays the same note, just lower volume, for the time being
void Conductor::setAccompaniment(bool isOn) {
    hasAccompaniment_ = isOn;
}
	
void Conductor::setChords(bool isOn, Chords chords) {
    hasChords_ = isOn;
    midi_->releaseChannel(CHANNEL_CHORD, 0);
    if (chords_ == CHORDS_123){
       midi_->releaseNote(CHANNEL_CHORD, currentChord_, 0);
       midi_->releaseNote(CHANNEL_CHORD, currentChord_+4, 0);
       midi_->releaseNote(CHANNEL_CHORD, currentChord_+7, 0);
    }
   chords_ = chords;
}
	
void Conductor::setRhythm(bool isOn, Rhythm rhythm) {
    hasRhythm_ = isOn;
    midi_->releaseChannel(CHANNEL_PERCUSSION, 0);//for safety so that we dont end up with endless note
    rhythm_ = rhythm;
}
	
void Conductor::setDynamics(Dynamics dynamics) {
    // DYNAMICS_PIANO, DYNAMICS_FORTE, DYNAMICS_PIANISSIMO, DYNAMICS_FORTISSIMO 
    dynamics_ = dynamics;
    if (dynamics ==  DYNAMICS_PIANO) {//lead volume: 60
        midi_->sendControlChange(CHANNEL_LEAD, 7, 45, 0);
        midi_->sendControlChange(CHANNEL_ACCOMPANY, 7, 30, 0);
        midi_->sendControlChange(CHANNEL_CHORD, 7, 35, 0);
        midi_->sendControlChange(CHANNEL_PERCUSSION, 7, 15, 0);
        
    }
    else if (dynamics ==  DYNAMICS_FORTE) {//lead volume: 90
        midi_->sendControlChange(CHANNEL_LEAD, 7, 70, 0);
        midi_->sendControlChange(CHANNEL_ACCOMPANY, 7, 45, 0);
        midi_->sendControlChange(CHANNEL_CHORD, 7, 60, 0);
        midi_->sendControlChange(CHANNEL_PERCUSSION, 7, 30, 0);
        
    }
    else if (dynamics ==  DYNAMICS_PIANISSIMO) {//lead volume: 30
        midi_->sendControlChange(CHANNEL_LEAD, 7, 25, 0);
        midi_->sendControlChange(CHANNEL_ACCOMPANY, 7, 15, 0);
        midi_->sendControlChange(CHANNEL_CHORD, 7, 20, 0);
        midi_->sendControlChange(CHANNEL_PERCUSSION, 7, 10, 0);
        
    }
    else if (dynamics ==  DYNAMICS_FORTISSIMO) {//lead volume: 127
        midi_->sendControlChange(CHANNEL_LEAD, 7, 127, 0);
        midi_->sendControlChange(CHANNEL_ACCOMPANY, 7, 90, 0);
        midi_->sendControlChange(CHANNEL_CHORD, 7, 110, 0);
        midi_->sendControlChange(CHANNEL_PERCUSSION, 7, 55, 0);
        
    }
}

void Conductor::setMelody(vector<uchar> melody) {//passed by copy to prevent simultaneous access chaos  
	melodyStep_ = 0;
	melody_ = melody;
   melodyLength_ = melody_.size();
   currentChord_ = 60;
    if ( (melodyLength_ % 2 ) != 0)//check if right format, if not disregard last information
        melodyLength_--;
    if (melodyLength_ == 0)//if no notes in vector, nothing to play 
        hasMelody_ = false;
    else 
        hasMelody_ = true;
}

void Conductor::setPedaling(bool isOn, int frequency) {
    hasPedaling_ = isOn;
    pedalingFreq_ = frequency;
    pedalingCounter_ = 0;
}

void Conductor::setReverberation(bool isOn) {
    hasReverb_ = isOn;
    if (isOn) {
        midi_->sendControlChange(CHANNEL_LEAD, 91,127, 0);//turn on
        midi_->sendControlChange(CHANNEL_ACCOMPANY, 91,127, 0);//turn on
    }
    else {
        midi_->sendControlChange(CHANNEL_LEAD, 91,0, 0); //turn off
        midi_->sendControlChange(CHANNEL_ACCOMPANY, 91,0, 0);//turn on
    }
}

void Conductor::pressPanicButton() {
    midi_->releaseChannel(CHANNEL_CHORD, 0);//just to make sure, this is doing funny things
    midi_->sendControlChange(CHANNEL_CHORD, 64, 0, 0); //turn hold OFF
    if (chords_ == CHORDS_123){
        midi_->releaseNote(CHANNEL_CHORD, currentChord_, 0);
        midi_->releaseNote(CHANNEL_CHORD, currentChord_+4, 0);
        midi_->releaseNote(CHANNEL_CHORD, currentChord_+7, 0);
    }
   midi_->panic(0);
}

void Conductor::setModulation(uchar position) {
    if (position == 0) {
        midi_->sendControlChange(CHANNEL_LEAD, 1, 0, 0);
        midi_->sendControlChange(CHANNEL_ACCOMPANY, 1, 0, 0);
        midi_->sendControlChange(CHANNEL_CHORD, 1, 0, 0);
        midi_->sendControlChange(CHANNEL_PERCUSSION, 1, 0, 0);
    }
    else {
        midi_->sendControlChange(CHANNEL_LEAD, 1, position, 0);
        midi_->sendControlChange(CHANNEL_ACCOMPANY, 1, position, 0);
        midi_->sendControlChange(CHANNEL_CHORD, 1, position, 0);
        midi_->sendControlChange(CHANNEL_PERCUSSION, 1, position, 0);
    }
}

void Conductor::setPan(uchar position) {
    midi_->sendControlChange(CHANNEL_LEAD, 1, position, 0);
    //midi_->sendControlChange(CHANNEL_LEAD, 5, position);
}
}
