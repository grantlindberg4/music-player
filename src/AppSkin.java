import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;


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

	boolean playing=false;
	/**
	 * Create the frame.
	 */
	public AppSkin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel playPauseLabel = new JLabel("");
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
		
		JLabel previousLabel = new JLabel("");
		previousLabel.setIcon(previous);
		
		JLabel nextLabel = new JLabel("");
		nextLabel.setIcon(next);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(139)
					.addComponent(previousLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(playPauseLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nextLabel)
					.addContainerGap(163, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(nextLabel)
						.addComponent(previousLabel)
						.addComponent(playPauseLabel))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	}

}
