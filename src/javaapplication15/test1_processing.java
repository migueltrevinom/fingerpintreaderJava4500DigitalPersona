import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class test1_processing extends PApplet {





// The serial port:
Serial myPort;

public void setup() {
// List all the available serial ports:
println(Serial.list());

// Open the port you are using at the rate you want:
myPort = new Serial(this, Serial.list()[0], 9600);

// Send a capital "A" out the serial port
  myPort.write('a');
}

public void draw() {

  }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "test1_processing" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
