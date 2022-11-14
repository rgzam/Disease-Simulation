# Disease Simulation
## How to play
In order to run the file one must specify the test files if not specified it will run the 
default values. To run the config from the jar you must hit the command java Disease.jar test1.txt
to run the first test from the config files.The purpose of this program is to model the 
spread of a disease through a
population. The representation can be humans, rabbits, birds, etc. and the disease could be 
spread via physical contact, airborne, fluids, etc.
The program will have diferent colors representing their state Blue is Alive, Green is was infected
but recovered, Red means infected and the color Black is considered to be dead.


## Configuration
The simulation's configuration determines the number of agents and locations.
the dimensions of the screen, exposure distance, sickness time, recovery chance, incubation period,
etc. In order for the config file to work, the program has to be run from the command line using
the test files. One can run the given test files or create their own test config file to change the 
parameters.
## Bugs
For the dimensions of height and width, the display size is locked at 1000 by 1000.
since changing it to the requested amount will create problems in our code. Running some of the configurations
will not change certain parameters such as dimension, exposure distance, and incubation.

## Unfinished Features
Did not include a button to rerun the simulation with the same saved configuration files.We don't have a plot.
where it displays time for the x-axis and agents for the y-axis. We don't have a log of agents dying at
certain times.