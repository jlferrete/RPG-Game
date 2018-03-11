package sonido;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Bso extends Frame {
	private static final long serialVersionUID = 1L;

	public Bso() {
		File sf = new File("aMusic.wav");
		AudioFileFormat aff;
		AudioInputStream ais;
		try {
			aff = AudioSystem.getAudioFileFormat(sf);
			ais = AudioSystem.getAudioInputStream(sf);
			AudioFormat af = aff.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat(),
					((int) ais.getFrameLength() * af.getFrameSize()));
			Clip ol = (Clip) AudioSystem.getLine(info);
			ol.open(ais);
			ol.loop(Clip.LOOP_CONTINUOUSLY);
			//ol.close();
		} catch (UnsupportedAudioFileException ee) {
			System.out.println(ee.getMessage());
		} catch (IOException ea) {
			System.out.println(ea.getMessage());
		} catch (LineUnavailableException LUE) {
			System.out.println(LUE.getMessage());
		}
	}

	
}