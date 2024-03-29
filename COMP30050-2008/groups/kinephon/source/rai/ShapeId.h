#ifndef __INTERPRETER_SHAPEID_H__
#define __INTERPRETER_SHAPEID_H__

namespace interpreter
{

/**
 * List of all shapes by their id in the kinephon.sec shape file
 * This header was generated by Shed
 * @author EB
 * @version 1.0
 */
namespace esid
{

	sid const TRIANGLE            = 1; // Movement
	sid const TRI_SOFT            = 2; // - Speed
	sid const TRI_SHARP           = 3; // - Speed
	sid const TRI_SLOW            = 4; // - Accel
	sid const DYNAMICS_PIANO      = 5; // Speed
	sid const DYNAMICS_FORTE      = 6; // Accel
	sid const DYNAMICS_PIANISSIMO = 7; // Movement
	sid const DYNAMICS_FORTISSIMO = 8; // Movement
	sid const RHYTHM_1_4          = 9; // Movement
	sid const RHYTHM_2_4          = 10; // Movement
	sid const RHYTHM_3_4          = 11; // Movement
	sid const RHYTHM_4_4          = 12; // Movement
	sid const RHYTHM_1_2          = 13; // Movement
	sid const RHYTHM_2_3          = 14; // Movement
	sid const RHYTHM_NONE         = 15; // Movement
	sid const CHORDS_FIRST        = 16; // Movement
	sid const CHORDS_SECOND       = 17; // Movement
	sid const CHORDS_THIRD        = 18; // Movement
	sid const CHORDS_123        = 18; // Movement
	sid const CHORDS_ONEOFF       = 19; // Movement
	sid const CHORDS_NONE         = 20; // Movement
	sid const INSTRUMENT_CLASSIC  = 21; // Movement
	sid const INSTRUMENT_CRAZY    = 22; // Movement
	sid const INSTRUMENT_WIND     = 23; // Movement

}

}

#endif
