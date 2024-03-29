package ie.ucd.music.comparison.FindID3;

//Talk To Mark to see if he wants output as a comma separated file.
//Add Possible Support for ID3v2
//
import java.io.*;
import org.jaudiotagger.audio.*;
import org.jaudiotagger.tag.*;
import java.util.logging.*;

public class FindID3 {

    public void FindDirectory(String path1, String path2) {

        File I1 = new File(path1);
        File I2 = new File(path2);
        FindAllID3(I1);
        FindAllID3(I2);
    }

    public static void main(String args[]) {

        if ((args.length != 0) || (args.length > 9)) {
            System.out.println("searching for mp3s");
            File[] locations = new File[10];

            for (int x = 0; x < args.length; x++) {
                locations[x] = new File(args[x]);
            }

            // File dir1 = new File(args[0]);
            // File dir2 = new File(args[1]);
            Boolean alldirs = true;
            for (int z = 0; z < args.length; z++) {
                if (!(locations[z].isDirectory())) {
                    alldirs = false;
                }
            }

            if (alldirs) {

                try {
                    // System.out.println("searching for mp3s in
                    // directories"+args[1] +"
                    // and " + args[0]);
                    // =============================Read ID3 code

                    for (int y = 0; y < args.length; y++) {
                        FindAllID3(locations[y]);
                    }

                    // FindAllID3(dir1);
                    // FindAllID3(dir2);

                    // =============================
                }

                catch (Exception e) {
                    System.out
                            .println("file input error error message was e = "
                                    + e.toString());
                }
            }

            else {
                System.out.println("Invalid directories ");
            }
        } else {
            System.out.println("Program needs TWO parameters: ");
            System.out
                    .println("Author Peter O' Murchu. This program reads in two directories and extracts all mp3 files from them  ");
            System.out
                    .println("Possible errors: 1.You must enter at least two directories. 2.The directories you entered do not exist");
        }

    }

    static private void PrintID3(File mp3) {
        try {
            // Turn off all logging. This should be moved??
            Logger logger = Logger.getLogger("org.jaudiotagger");
            logger.setLevel(Level.OFF);
            File song = mp3;
            // FileInputStream file = new FileInputStream(song);

            AudioFile f = AudioFileIO.read(song);
            Tag tag = f.getTag();

            // System.out.println("Track Length: "
            // + f.getAudioHeader().getTrackLength());
            System.out.println("Sample Rate: "
                    + f.getAudioHeader().getSampleRateAsNumber());

            System.out.println("Artist: " + tag.getFirstArtist());
            System.out.println("Album: " + tag.getFirstAlbum());
            System.out.println("Title: " + tag.getFirstTitle());
            // System.out.println(tag.getFirstComment());
            // System.out.println("Year: " + tag.getFirstYear());
            // System.out.println(tag.getFirstTrack());

            /*
             * Removed. This is for version 1 only
             * int size = (int)song.length(); file.skip(size - 128); byte[]
             * last128 = new byte[128]; file.read(last128); String id3 = new
             * String(last128); String tag = id3.substring(0, 3); if
             * (tag.equals("TAG")) { System.out.println("Title: " +
             * id3.substring(3, 32)); System.out.println("Artist: " +
             * id3.substring(33, 62)); System.out.println("Album: " +
             * id3.substring(63, 91)); System.out.println("Year: " +
             * id3.substring(93, 97)); } else //System.out.println(mp3 + " does
             * not contain" + " ID3 info.");
             */

            // file.close();
        } catch (Exception e) {
            System.out.println("file read error error message was e = "
                    + e.toString());
        }
    }

    static private void FindAllID3(File startDir) {
        File[] allFiles = startDir.listFiles();
        for (int i = 0; i < allFiles.length; i++) {
            if (allFiles[i].isDirectory()) {
                FindAllID3(allFiles[i]);
            } else {
                // System.out.println("got listing" );
                // System.out.println("looop "+i );
                // System.out.println(allFiles[i] );
                // String extension =
                // allFiles[i].substring(allFiles[i].length - 3,
                // allFiles[i].length);
                String tempfname = allFiles[i].getName();
                if ((tempfname.endsWith("mp3")) || (tempfname.endsWith("MP3"))
                        || (tempfname.endsWith("mP3"))
                        || (tempfname.endsWith("Mp3"))) {
                    System.out.println("========================= ");

                }
            }
        }
    }
}
