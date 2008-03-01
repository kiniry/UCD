#ifndef __INTERPRETER_SHAPE_H__
#define __INTERPRETER_SHAPE_H__

#include "Track.h"

/*
 * Author:	EB
 *
 * Store the approximation of a shape to be compared against a movement
 *
 */
namespace interpreter
{

class Shape
{

public:				// Constructor
					// Load the <name>'d shape data 
	/**/			Shape
					(	char const * const	name
					);
	virtual			~Shape					(void);

public:				// Methods
					// Comare a track against this shape
					//	Overload to compare movements, speeds, or accelerations
	virtual float	compare
					(	Track const * const	track
					)	const				pure;

private:
					// Compare array of vector points against the shape data	
	float			compare
					(	int const * const	x,
						int const * const	y
					);

private:
					// 2 dimensional array of shape compare data
	float *			_array;
					// Width of array (height = _length / _width)
	int				_width;
					// Total length of array
	int				_length;

};

}

#endif
