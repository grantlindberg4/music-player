import java.io.*;
import javax.sound.sampled.*;

public class SoundPlayer {
    public static void main(String[] args) {
        SoundPlayer sound = new SoundPlayer("sounds/Dragostea-Din-Tei.wav");
        InputStream stream = new ByteArrayInputStream(sound.getSamples());

        sound.play(stream);
        System.exit(0);
    }

    private AudioFormat format;
    private byte[] samples;

    public SoundPlayer(String file) {
        try {
            AudioInputStream stream =
                AudioSystem.getAudioInputStream(new File(file));
            format = stream.getFormat();
            samples = getSamples(stream);
        }
        catch(UnsupportedAudioFileException ex) {
            System.out.println("This format is not supported!");
        }
        catch(IOException ex) {
            System.out.println("I/O Error!");
        }
    }

    public byte[] getSamples() {
        return samples;
    }

    public byte[] getSamples(AudioInputStream audioStream) {
        int length = (int)(audioStream.getFrameLength()*format.getFrameSize());
        byte[] samples = new byte[length];
        DataInputStream dataStream = new DataInputStream(audioStream);
        try {
            dataStream.readFully(samples);
        }
        catch(IOException ex) {
            System.out.println("I/O Error!");
        }

        return samples;
    }

    public void play(InputStream source) {
        int bufferSize = format.getFrameSize() *
                         Math.round(format.getSampleRate()/10);

        byte[] buffer = new byte[bufferSize];

        SourceDataLine dataLine;
        try {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            dataLine = (SourceDataLine)AudioSystem.getLine(info);
            dataLine.open(format, bufferSize);
        }
        catch(LineUnavailableException ex) {
            System.out.println("Line was unavailable!");
            return;
        }

        dataLine.start();

        try {
            int bytesRead = 0;
            while(bytesRead != -1) {
                bytesRead = source.read(buffer, 0, buffer.length);
                if(bytesRead != -1) {
                    dataLine.write(buffer, 0, bytesRead);
                }
            }
        }
        catch(IOException ex) {
            System.out.println("I/O Error!");
        }

        dataLine.drain();
        dataLine.close();
    }
}
