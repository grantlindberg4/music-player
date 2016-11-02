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
	
	ImageIcon play = new ImageIcon(this.getClass().getResource("playButton.png"));
	ImageIcon pause = new ImageIcon(this.getClass().getResource("pauseButton.png"));
	ImageIcon previous = new ImageIcon(this.getClass().getResource("previousButton.png"));
	ImageIcon next = new ImageIcon(this.getClass().getResource("skipButton.png"));
	ImageIcon shuffle = new ImageIcon(this.getClass().getResource("shuffleButton.png"));
	ImageIcon shuffleOff = new ImageIcon(this.getClass().getResource("shuffleOffButton.png"));
	ImageIcon repeatOn = new ImageIcon(this.getClass().getResource("repeatButton.png"));
	ImageIcon repeatOff = new ImageIcon(this.getClass().getResource("repeatOffButton.png"));
	ImageIcon repeat1 = new ImageIcon(this.getClass().getResource("repeatOneButton.png"));
	ImageIcon backgroundPic = new ImageIcon(this.getClass().getResource("background.png"));

	String[] columes = {"Title", "Artist", "Length", "Album", "Count"};
	String[][] data = {{"Victorious", "Panic! At the Disco", "2:58", "Death of a Bachelor", "0"}};
	
	boolean playing=false;
	boolean shuffleOn=false;
	boolean repeatAll=false;
	boolean repeatOne=false;
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
				if(!shuffleOn){
					shuffleOn=true;
					shuffleLabel.setIcon(shuffle);
				}else{
					shuffleOn=false;
					shuffleLabel.setIcon(shuffleOff);
				}
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
				if(playing){
					playing=false;
					playPauseLabel.setIcon(pause);
				}else{
					playing=true;
					playPauseLabel.setIcon(play);
				}
			}
		});
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 11, 419, 193);
		contentPane.add(scrollPane);
		
		table = new JTable(data, columes);
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
		
	}
}
