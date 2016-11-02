import java.io.File;

public class Main {
    public static void main(String[] args) {
        SoundPlayer soundPlayer = new SoundPlayer();

	    File song = new File("sounds/applause.wav");

        soundPlayer.play(song);
    }
}
