import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

enum LoopSetting {
	NEVER,
	ONCE,
	INFINITELY
}

public class SoundPlayer {
    private static AudioInputStream audioStream;
    private static AudioFormat format;
    private static DataLine.Info info;
    private static Clip clip;
    private static long songTime;
    public static LoopSetting repeat;
    public static boolean playing;
    public static int songIndex;

    public SoundPlayer() {
    	SoundPlayer.repeat = LoopSetting.NEVER;
        SoundPlayer.playing = false;
        SoundPlayer.songTime = 0;
        SoundPlayer.songIndex = 0;
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
    	playing = true;
        try {
	        audioStream = AudioSystem.getAudioInputStream(song);
	        format = audioStream.getFormat();
	        info = new DataLine.Info(Clip.class, format);

	        clip = (Clip) AudioSystem.getLine(info);
	        clip.open(audioStream);
	        clip.start();
//            long length = clip.getMicrosecondLength()/1000;

//            try {
//                Thread.sleep(100);
//            }
//            catch(InterruptedException ie) {
//                ie.printStackTrace();
//            }
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
    }

//    public static void stop() {
//    	if(clip != null) {
//    		if(paused) {
//    			pause();
//    		}
//    		if(clip.isActive()) {
//    			repeat = LoopSetting.NEVER;
//    			clip.stop();
//    			clip.flush();
//    			clip.close();
//    			paused = false;
//    			songTime = 0;
//    		}
//    	}
//    }

    public static void pause() {
    	playing = false;
    	if(clip == null || !clip.isOpen()) {
    		return;
    	}
    	if(playing) {
    		clip.setMicrosecondPosition(songTime);
    		clip.start();
    	}
    	else {
    		songTime = clip.getMicrosecondPosition();
    		clip.stop();
    	}
    }

    public static void setRepeat() {
    	if(clip == null || !clip.isOpen()) {
    		repeat = LoopSetting.NEVER;
    		return;
    	}
    	switch(repeat) {
    		case NEVER:
    			clip.loop(0);
    			break;
    		case ONCE:
    			clip.loop(1);
    			break;
    		case INFINITELY:
    			clip.loop(clip.LOOP_CONTINUOUSLY);
    			break;
    	}
    }
}
