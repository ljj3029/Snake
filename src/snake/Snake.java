package snake;
import java.io.IOException;

import javax.swing.JFrame;

public class Snake {

	public static void main(String[] args) throws IOException{

		JFrame frame = new JFrame();

		frame.setTitle("Snake");
		frame.setBounds(0,0,1216,939);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().add(imagePane, BorderLayout.CENTER);
		SnakePanel panel = new SnakePanel();
		frame.add(panel);
		frame.setVisible(true);

		//AudioPlayer.player.stop(audioStream);
	}

}

