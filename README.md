# NESBot
A Nintendo NES powered robot
***
####About This Project
I've been asked to help a friend on an endeavour to build and program a Nintendo NES to drive motors and servos. The goal of this project is to learn hardware design and how to program the NES's 2A03 processor. I be will designing most of the hardware for this project.
***
####Program Execution
We are going to strive to use the NES processor as much as possible, and not rely on a microcontroller to do all the heavy lifting. This means that the program must be executed from a real cartridge, just like it would back in the day. 
***
####Cartridge Memory
Right now my cartridge is based on an [NROM cart](http://wiki.nesdev.com/w/index.php/NROM) which will have 32kB of program memory and 8kB of character memory.

So far, I have successfully made a reprogrammable cartridge using [AT28C256 EEPROMs](http://www.atmel.com/Images/doc0006.pdf) and a hand modified Baseball cartridge. They were programmed using a ChipMax Programmer that I'm borrowing. Cheaper EEPROM programmers can be sourced from eBay, but your mileage may vary.

We may end up putting the code on a flash memory chip or a microntroller in the future. Regardless of how the code is stored, it still must be presented to the NES like a real EPROM chip.
***
####Hardware Interface
We plan to use the exapnsion port on the bottom of the NES. The overall goal is to place an unmodified NES on a chassis with an expansion port connector, plug in the cartridge and go.

Some of the expansion pins are mapped to the directly to the cartridge. Since the NES CPU has very little periphals, we could place any needed peripherals (for example, a timer) in the cartridge itself.
***
####Communication
This part of the process will most likely need a microcontroller. I plan to hold data received wirelessly in a buffer, to be read by the NES CPU via SPI.
