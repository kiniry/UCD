//#include <boost/python.hpp>
//#include </usr/include/python2.5/Python.h>
#include <iostream>
#include <string>
#include "Python.h"
#include "WiimoteInterface.h"
#include "IRReport.h"
using namespace std;

int main_glue(int, char **) {
	WiimoteInterface wiinterface;
	cout << "interface created" << endl;
	string wiimote = wiinterface.findWiimote();
	cout << "search complete" << endl;
	while (wiimote == "NOT_FOUND") {
		cout << "wiimote not found, trying again" << endl;
		wiimote = wiinterface.findWiimote();
	}
	wiinterface.connectTo(wiimote);
	cout << "connected" << endl;

	int intervals = 0;
	int cur_time = 0;
	int last_time = 0;
	int counter = 0;
	for (int i = 0; i < 1000; i++) {
		IRReport report  = wiinterface.receiveReport();
		cout << i;
		//the whole if/else block servers only as feedback and provides
		//simple statistics, it should not be included in the release.
		if (report.isValid()) {
			counter++;
			last_time = cur_time;
			cur_time = report.getTimestamp();
			if (last_time != 0) {
				intervals += (cur_time - last_time);
			}
			report.print();
		} else {
			cout << "NOT VALID" << endl;
		}
	}
	
	cout << intervals/counter;

	cout << "done" << endl;
	return 0;


	/*Py_Initialize();

	//char* FileName = "/media/data/programming/linux/cpp/workspace/Kinephon/source/wiimote/main.py";
	char* FileName = "code.py";
	PyObject* PyFileObject = PyFile_FromString(FileName, "r");
	if (PyFileObject == NULL) {
		PyErr_Clear();
		return 1;
	}



	PyRun_SimpleFile(PyFile_AsFile(PyFileObject), FileName);




	PyObject * module = PyImport_AddModule("__main__"); // borrowed reference

	assert(module);                                     // __main__ should always exist
	PyObject * dictionary = PyModule_GetDict(module);   // borrowed reference
	assert(dictionary);                                 // __main__ should have a dictionary

	PyObject * wiimote_address
	= PyDict_GetItemString(dictionary, "wiimote_address");    // borrowed reference
	assert(wiimote_address);
	assert(PyString_Check(wiimote_address));
	char* wiimote_address_string = PyString_AsString(wiimote_address); 

	std::cout << wiimote_address_string << std::endl;

	string string_address(wiimote_address_string); 

	if (string_address != "NOT_FOUND") {
		PyRun_SimpleString("establish_connection(wiimote_address)");
		PyRun_SimpleString("initialise_ir_camera()");

		for (int i=0; i<5000; i++) {
			PyRun_SimpleString("report = receive_report()");

			PyObject * report = PyDict_GetItemString(dictionary, "report");
			assert(report);
			assert(PyList_Check(report));
			int size = PyList_Size(report);

			for (int j=0; j < size; j++) {
				PyObject * item = PyList_GetItem(report, j);
				assert(item);
				assert(PyInt_Check(item));
				int item_value = PyInt_AsSsize_t(item);

//				if (item_value > 99) {
//					cout << " ";
//				} else if (item_value < 10) {
//					cout << "   ";
//				} else {
//					cout << "  ";
//				}
//				cout << item_value;

			}
			//cout << "\n";
		}


	} else {
		//try again?
	}

	PyRun_SimpleString("close_connection()");
	cout << "transmission complete";


	//PyRun_SimpleString("result = 5 ** 2");
	Py_Finalize();
	cout << "python stopped";
	return 0; */
}

