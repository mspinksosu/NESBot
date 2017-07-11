import java.util.Scanner;
import java.io.*;

public class ConvertPRG
{
	public static void main(String[] args)
	{
		// Be sure the input is valid
		if(args.length < 2)
		{
			System.error.println("USAGE: java ConvertPRG <infile> <outfile>");
			System.exit(1);
		}
		
		// Use these to be sure it's okay to overwrite
		// an existing file.
		Scanner userIn = new Scanner(System.in);
		char overwriteChar = 'n';

		// Use these to see whether the files exist
		File inFile;
		File outFile;

		// Use these to read and write the files
		DataInputStream in;
		DataOutputStream out;

		// Store the ROM data in here, minus INES header
		byte[] rom = new byte[0x4000];
		
		try
		{
			inFile = new File(args[0]);
			outFile = new File(args[1]);
			
			// Make sure the input file exists. Chastise foolish user if it doesn't.
			if(!inFile.isFile())
			{
				System.error.println("File " + inFile.getName() + " not found.");
				System.exit(1);
			}
			
			// It's okay if the output file already exists, but make sure
			// we really want to overwrite it if it does.
			if(outFile.isFile())
			{
				// If this loop repeats, the user input gibberish.
				while(overwriteChar != 'y')
				{
					System.out.println("Really overwrite " + outFile.getName() + "? (y/n)");
					overwriteChar = userIn.next().charAt(0).toLowerCase();
					
					if(overwriteChar == 'n')
					{
						System.exit(1);
					}
				}
			}
			
			in = new DataInputStream(new FileInputStream(inFile));
			out = new DataOutputStream(new FileOutputStream(outFile));
			
			in.skipBytes(16); // skip the INES header
			in.read(rom); // read in the prg rom data

			out.write(rom, 0, rom.length); // write the rom without INES header
			out.write(rom, 0, rom.length); // write the same data to bank 2
			
			in.finalize();
			out.finalize();

			// Let's just be sure the sizes are correct.
			System.out.println("Size of input file: 0x" + Integer.toHexString(inFile.getTotalSpace()));
			System.out.println("(Expected: 0x4010)");
			System.out.println("Size of output file: 0x" + Integer.toHexString(outFile.getTotalSpace()));
			System.out.println("(Expected: 0x8000)");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}