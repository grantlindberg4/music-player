import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

import java.io.*;
import java.util.*;


public class AppSkin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppSkin frame = new AppSkin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ImageIcon play = new ImageIcon("src/images/playButton.png");
	ImageIcon pause = new ImageIcon("src/images/pauseButton.png");
	ImageIcon previous = new ImageIcon("src/images/previousButton.png");
	ImageIcon next = new ImageIcon("src/images/skipButton.png");
	ImageIcon shuffle = new ImageIcon("src/images/shuffleButton.png");
	ImageIcon shuffleOff = new ImageIcon("src/images/shuffleOffButton.png");
	ImageIcon repeatOn = new ImageIcon("src/images/repeatButton.png");
	ImageIcon repeatOff = new ImageIcon("src/images/repeatOffButton.png");
	ImageIcon repeat1 = new ImageIcon("src/images/repeatOneButton.png");
	ImageIcon backgroundPic = new ImageIcon("src/images/background.png");

	String[] columns = {"Title", "Artist", "Length", "Album", "Count"};
	String[][] data = {{"Victorious", "Panic! At the Disco", "2:58", "Death of a Bachelor", "0"}};

	// Break this into the SoundPlayer class
	boolean shuffleOn=false;
	boolean repeatAll=false;
	boolean repeatOne=false;

	private SoundPlayer soundPlayer;
	public ArrayList<File> songList;

	private JTable table;
	/**
	 * Create the frame.
	 */
	public AppSkin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel previousLabel = new JLabel("");
		previousLabel.setBounds(61, 219, 36, 36);
		previousLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		previousLabel.setIcon(previous);

		JLabel nextLabel = new JLabel("");
		nextLabel.setBounds(103, 219, 36, 36);
		nextLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		nextLabel.setIcon(next);

		JLabel repeatLabel = new JLabel("");
		repeatLabel.setBounds(353, 219, 36, 36);
		repeatLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!repeatAll && !repeatOne){
					repeatAll=true;
					repeatLabel.setIcon(repeatOn);
				}else if(repeatAll && !repeatOne){
					repeatAll=false;
					repeatOne=true;
					repeatLabel.setIcon(repeat1);
				}else if(!repeatAll && repeatOne){
					repeatOne=false;
					repeatLabel.setIcon(repeatOff);
				}
			}
		});
		repeatLabel.setIcon(repeatOff);

		JLabel shuffleLabel = new JLabel("");
		shuffleLabel.setBounds(398, 219, 36, 36);
		shuffleLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(shuffleOn){
					shuffleLabel.setIcon(shuffleOff);
				}else{
					shuffleLabel.setIcon(shuffle);
				}

				shuffleOn = !shuffleOn;
			}
		});
		shuffleLabel.setIcon(shuffleOff);
		contentPane.setLayout(null);


		JLabel playPauseLabel = new JLabel("");
		playPauseLabel.setBounds(15, 215, 40, 40);
		playPauseLabel.setIcon(play);
		playPauseLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(SoundPlayer.playing){
					playPauseLabel.setIcon(play);
					SoundPlayer.pause();

				}
				else {
					playPauseLabel.setIcon(pause);
//					soundPlayer.play(songList);
					File currSong = songList.get(SoundPlayer.songIndex);
					SoundPlayer.play(currSong);
				}
			}
		});
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 11, 419, 193);
		contentPane.add(scrollPane);

		table = new JTable(data, columns);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(false);
		table.setDragEnabled(true);
		scrollPane.setViewportView(table);
		contentPane.add(playPauseLabel);
		contentPane.add(previousLabel);
		contentPane.add(nextLabel);
		contentPane.add(repeatLabel);
		contentPane.add(shuffleLabel);

		JLabel background = new JLabel("");
		background.setBounds(0, 0, 444, 271);
		background.setIcon(backgroundPic);
		contentPane.add(background);

		// Set up sound player
		soundPlayer = new SoundPlayer();
		songList = new ArrayList<File>();

		File song1 = new File("src/sounds/applause.wav");
		songList.add(song1);
		File song2 = new File("src/sounds/beep1.wav");
		songList.add(song2);
		File song3 = new File("src/sounds/beep2.wav");
		songList.add(song3);
		File song4 = new File("src/sounds/beep3.wav");
		songList.add(song4);
		File song5 = new File("src/sounds/beep4.wav");
		songList.add(song5);
		File song6 = new File("src/sounds/beep5.wav");
		songList.add(song6);
		File song7 = new File("src/sounds/beep6.wav");
		songList.add(song7);
		File song8 = new File("src/sounds/beep7.wav");
		songList.add(song8);
	}
}
