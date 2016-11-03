import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class SoundPlayer {
    private static AudioInputStream audioStream;
    private static AudioFormat format;
    private static DataLine.Info info;
    private static Clip clip;
    public static boolean repeat;

    public SoundPlayer() {
        SoundPlayer.repeat = false;
    }

    public void play(ArrayList<File> songList) {
        for(int i = 0; i < songList.size(); i++) {
            play(songList.get(i));
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public static void play(File song) {
        try {
	        audioStream = AudioSystem.getAudioInputStream(song);
	        format = audioStream.getFormat();
	        info = new DataLine.Info(Clip.class, format);

	        clip = (Clip) AudioSystem.getLine(info);
	        clip.open(audioStream);

            if(repeat) {
                clip.loop(clip.LOOP_CONTINUOUSLY);
            }
            else {
                clip.loop(0);
            }

	        clip.start();
            long length = clip.getMicrosecondLength()/1000;

            try {
                Thread.sleep(length);
            }
            catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        catch(UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        }
        catch(LineUnavailableException lue) {
            lue.printStackTrace();
        }
        catch(IOException ioe) {
           ioe.printStackTrace();
        }

        clip.close();
    }
}