package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
public class SnakePanel extends JPanel implements KeyListener,ActionListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	class MusicPlay extends Thread{
		FileInputStream music;//声明文件流对象
		sun.audio.AudioStream play_music;//声明音频流对象

		MusicPlay(){
		this.start();
		}
		/*
		  *线程运行
		  */
		public void run(){

		//循环播放
		while(true){
			try{
			music=new FileInputStream(gongFile);//创建文件流对象
			play_music=new sun.audio.AudioStream(music);//创建音频流对象

				
			}
			catch(Exception e){System.out.println(e);}
				 
			sun.audio.AudioPlayer.player.start(play_music);//开始播放
			if(gongFile.equalsIgnoreCase("01. I Am You.wav")){
				Music="《I Am You》";
			}else if(gongFile.equalsIgnoreCase("02. Booty Music.wav")){
				Music="《Booty Music》";
			}else if(gongFile.equalsIgnoreCase("03. We Don't Talk Anymore.wav")){
				Music="《We Don't Talk Anymore》";
			}else if(gongFile.equalsIgnoreCase("00.Unity.wav")){
				Music="《Unity》";
			} 
			 try{
		 Thread.sleep(60000*5);//音频播放时间
		 }catch(Exception e){ System.out.println(e);};
		}
		}

		}
	int width=1200;
	int height=900;
	double wts;
	double hts;
	int foodnumber = 20;																						//食物数量最大设置
	int[] foodxs = new int [foodnumber];
	int[] foodys = new int [foodnumber];
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon title = new ImageIcon("title.jpg");
	int[] snakex = new int[1426];
	int[] snakey = new int[1426];
	String fangxiang ="R";
	int len = 3;
	int score=0;
	int level=1;
	String Music="";
	String mode="";
	boolean isstarted =false;
	boolean isfalse =false;
	boolean iswin =false;
	boolean havewall=false;
	boolean	ismode=false;
	Random rand = new Random();
	Timer timer;
	boolean hw;
	int nb;
	int s;
	int length;
	String gongFile;
	InputStream in00;

	public SnakePanel()throws IOException {		
		this.setFocusable(true);
		this.addKeyListener(this);
		setup();
		for(int i=0;i<foodxs.length;i++) {
				foodxs[i] = rand.nextInt(46)*25+25;
				foodys[i] = rand.nextInt(31)*25+100;
		}
	}
	public void setup() {
		
		isfalse=false;
		isstarted=false;
		iswin=false;
		len = 3;
		fangxiang = "R";
		snakex[0]=100;
		snakey[0]=125;
		snakex[1]=75;
		snakey[1]=125;
		snakex[2]=50;
		snakey[2]=125;	
	}
	public void paint(Graphics g) {
		wts=(double)(this.getWidth())/width;
		hts=(double)(this.getHeight())/height;
		//System.out.println(this.getWidth());
		//System.out.println(this.getHeight());
		//System.out.println(wts);
		//System.out.println(hts);
		super.paint(g);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
		//title.paintIcon(this, g, 25, 11);
		 g.drawImage(title.getImage(), (int)(25*wts), 0, (int)(1150*wts), (int)(95*hts), this);
		g.fillRect((int)(25*wts), (int)(100*hts), (int)(1150*wts), (int)(775*hts));
		//画蛇头
		if(fangxiang.equals("R")) {
			//right.paintIcon(this, g, snakex[0], snakey[0]);
		   g.drawImage(right.getImage(), (int)(snakex[0]*wts), (int)(snakey[0]*hts), (int)((25)*wts), (int)((25)*hts), this);
		}else if(fangxiang.equals("L")) {
			//left.paintIcon(this, g, snakex[0], snakey[0]);
			g.drawImage(left.getImage(), (int)(snakex[0]*wts), (int)(snakey[0]*hts),  (int)((25)*wts), (int)((25)*hts), this);
		}else if(fangxiang.equals("U")) {
			//up.paintIcon(this, g, snakex[0], snakey[0]);
			g.drawImage(up.getImage(), (int)(snakex[0]*wts), (int)(snakey[0]*hts),  (int)((25)*wts), (int)((25)*hts), this);
		}else if(fangxiang.equals("D")) {
			//down.paintIcon(this, g, snakex[0], snakey[0]);
			g.drawImage(down.getImage(), (int)(snakex[0]*wts), (int)(snakey[0]*hts),  (int)((25)*wts), (int)((25)*hts), this);
		}
		for(int i = 1 ; i<len ;i++) {
			//body.paintIcon(this, g, snakex[i], snakey[i]);
			g.drawImage(body.getImage(), (int)(snakex[i]*wts), (int)(snakey[i]*hts),  (int)((25)*wts), (int)((25)*hts), this);
		}
		
		
		for(int i=0;i<level&&i<foodxs.length;i++) {
			g.drawImage(food.getImage(), (int)(foodxs[i]*wts), (int)(foodys[i]*hts),  (int)((25)*wts), (int)((25)*hts), this);
		}

		g.setColor(getBackground());
		g.setFont(new Font("宋体",Font.PLAIN,20));
		g.drawString("Music:"+Music, (int)(30*wts), (int)(60*hts));
		g.drawString("Mode:"+mode, (int)(365*wts), (int)(60*hts));
		g.drawString("Level:"+level, (int)(750*wts), (int)(60*hts));
		g.drawString("Score:"+score, (int)(900*wts), (int)(60*hts));
		g.drawString("Length:"+len, (int)(1050*wts), (int)(60*hts));
		
		if(!ismode) {
			g.setColor(getBackground());
			g.setFont(new Font("宋体",Font.ITALIC+Font.BOLD,30));
			g.drawString("Please Press  ", (int)(100*wts), (int)(425*hts));//capital letter
			g.drawString("Capital Letter  ", (int)(850*wts)+30, (int)(425*hts));
			g.drawString("S(Simple),N(Normal),D(Difficult) or H(Hell) ", (int)(300*wts), (int)(525*hts));
			g.drawString("To Start!", (int)(500*wts), (int)(625*hts));
		}else if(!isstarted) {
			g.setColor(getBackground());
			g.setFont(new Font("宋体",Font.ITALIC+Font.BOLD,30));
			g.drawString("Press Space To Start/Pause!", (int)(375*wts), (int)(525*hts));
		}
		
		if(isfalse) {
			g.setColor(getBackground());
			g.setFont(new Font("宋体",Font.ITALIC+Font.BOLD,30));
			g.drawString("Game Over,Press Space To Restart!", (int)(375*wts), (int)(525*hts));
					}
		if(len==length) {																			//过关长度设置
			iswin=true;
			g.setColor(getBackground());
			g.setFont(new Font("宋体",Font.ITALIC+Font.BOLD,30));
			g.drawString("Congratulations ! To meet new challenges!", (int)(225*wts), (int)(450*hts));
			g.drawString("Press Space To Restart!", (int)(325*wts), (int)(525*hts));
		}
		}
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		int KeyCode = arg0.getKeyCode();
		if(KeyCode==KeyEvent.VK_SPACE) {
			if(isfalse) {
				setup();
				score=0;
			}else if(iswin){
				level++;
				switch((level+s-2)%4) {
				case 0:{
						gongFile="01. I Am You.wav";
						break;
				}
				case 1:{
						gongFile="02. Booty Music.wav";
						break;
				}
				case 2:{
						gongFile="03. We Don't Talk Anymore.wav";
						break;
				}
				case 3:{
						gongFile="00.Unity.wav";
				}
				}
				nb-=2;
				length+=10;
				timer.stop();	
				timer = new Timer(nb,this);																		//速度大小设
				timer.start();	
				setup();
			}
			else{
				isstarted=!isstarted;
			}
		} else if(KeyCode==KeyEvent.VK_UP&& fangxiang!="D") {
			fangxiang="U";
		} else if(KeyCode==KeyEvent.VK_DOWN&& fangxiang!="U") {
			fangxiang="D";
		} else if(KeyCode==KeyEvent.VK_LEFT&& fangxiang!="R") {
			fangxiang="L";
		} else if(KeyCode==KeyEvent.VK_RIGHT&& fangxiang!="L") {
			fangxiang="R";
		} else if(!ismode&&(KeyCode==KeyEvent.VK_S||KeyCode==KeyEvent.VK_N||KeyCode==KeyEvent.VK_D||KeyCode==KeyEvent.VK_H)) {
						if(KeyCode==KeyEvent.VK_S) {
						s=1;isstarted=!isstarted;ismode=!ismode;length=60;
					}else if(KeyCode==KeyEvent.VK_N) {
						s=2;isstarted=!isstarted;ismode=!ismode;length=60;
					}else if(KeyCode==KeyEvent.VK_D) {
						s=3;isstarted=!isstarted;ismode=!ismode;length=60;
					}else if(KeyCode==KeyEvent.VK_H) {
						s=0;isstarted=!isstarted;ismode=!ismode;length=60;
					}
						switch(s) {
						case 1:	gongFile="01. I Am You.wav";hw=false;nb=100;
								Music="《I Am You》";mode="Simple";
								break;
						case 2:	gongFile="02. Booty Music.wav";hw=false;nb=80;
								Music="《Booty Music》";mode="Normal";
								break;
						case 3:	gongFile="03. We Don't Talk Anymore.wav";hw=true;nb=100;
								Music="《We Don't Talk Anymore》";mode="Difficult";
								break;
						default :gongFile="00.Unity.wav";hw=true;nb=80;
								Music="《Unity》";mode="Difficult";
				} 
						new MusicPlay();
						havewall =hw;            		//是否有墙设置
						timer = new Timer(nb,this);			//速度大小设
						timer.start();		
			}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(isstarted&&!isfalse&&!havewall&&!iswin&&ismode) {
				for(int i=len;i>0;i-- ) {
					snakex[i]=snakex[i-1];
					snakey[i]=snakey[i-1];
				}
				if(fangxiang.equals("R")) {
					snakex[0]=snakex[0]+25;
					if(snakex[0]>1150) {
						snakex[0]=25;
					}
				}else if(fangxiang.equals("L")) {
					snakex[0]=snakex[0]-25;
					if(snakex[0]<25) {
						snakex[0]=1150;
					}
				}else if(fangxiang.equals("U")) {
					snakey[0]=snakey[0]-25;
					if(snakey[0]<100) {
						snakey[0]=850;
					}
				}else if(fangxiang.equals("D")) {
					snakey[0]=snakey[0]+25;
					if(snakey[0]>850) {
						snakey[0]=100;
					}
				}
		}
		if(isstarted&&!isfalse&&havewall&&!iswin&&ismode) {
			for(int i=len;i>0;i-- ) {
				snakex[i]=snakex[i-1];
				snakey[i]=snakey[i-1];
			}
			if(fangxiang.equals("R")) {
				snakex[0]=snakex[0]+25;
				if(snakex[0]>1150) {
					snakex[0]=1150;
					isfalse=true;
				}
			}else if(fangxiang.equals("L")) {
				snakex[0]=snakex[0]-25;
				if(snakex[0]<25) {
					snakex[0]=25;
					isfalse=true;
				}
			}else if(fangxiang.equals("U")) {
				snakey[0]=snakey[0]-25;
				if(snakey[0]<100) {
					snakey[0]=100;
					isfalse=true;
				}
			}else if(fangxiang.equals("D")) {
				snakey[0]=snakey[0]+25;
				if(snakey[0]>850) {
					snakey[0]=850;
					isfalse=true;
				}
			}
	}

		for(int i=0;i<level&&i<foodxs.length;i++) {
			if(snakex[0]==foodxs[i]&&snakey[0]==foodys[i]) {
				len++;
				score++;
				foodxs[i] = rand.nextInt(46)*25+25;
				foodys[i] = rand.nextInt(31)*25+100;
				break;
			}
		}
		for(int i=4;i<len;i++) {
			if(snakex[0]==snakex[i]&&snakey[0]==snakey[i]) {
				isfalse=true;
				break;
			}
		}
		
		repaint();

		
		
	}
}
