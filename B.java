import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;
public class B extends Applet implements KeyListener{
	String s="";
	AudioClip au,au1;
	 int x[]=new int[30];
	 int y[]=new int[30];
	 int flag,i,press,p,t,game,dir,m;
	public void init(){
	au=getAudioClip(getCodeBase(), "SuperMarioWorldTitle.mid");
	//au1=getAudioClip(getCodeBase(), "Jump.wav");
	setBackground(Color.black);
	flag=0;
	press=0;
	i=0;
	game=1;
	dir=1;
	m=10;
	Random rm=new Random();
	t=Math.abs(rm.nextInt())%1300;
	p=Math.abs(rm.nextInt())%600;
	  addKeyListener(this);
	  au.loop();
	  for(i=0;i<m;i++){
			x[i]=160-i*11;
			y[i]=100;
		}
	}
	public void keyTyped(KeyEvent e){
		s=s+e.getKeyChar();
		repaint();
	}
	public void keyPressed(KeyEvent e){
		press=1;
		showStatus("Key Down");
		int key=e.getKeyCode();
		switch(key){
		
        case KeyEvent.VK_LEFT:
		flag=4;
        s+="<Left Arrow>";
        break;
        case KeyEvent.VK_RIGHT:
		flag=1;
        s+="<Right Arrow>";
        break;
		case KeyEvent.VK_UP:
		flag=2;
        s+="<Up Arrow>";
        break;
        case KeyEvent.VK_DOWN:
		flag=3;
        s+="<DOWN Arrow>";
        break;	
		}
		repaint();
	}	
	public void keyReleased(KeyEvent e){
		press=0;
		flag=0;
		showStatus("KeyEvent Up");
	}	
	public void paint(Graphics g){
		if(game==0){
			g.setColor(Color.red);
			g.drawString("gameover",500,500);
			g.drawString("score "+((m-10)*m),500,10);
		}
		else if(game==1){
			g.setColor(Color.red);
		g.drawString("score "+((m-10)*m),500,10);
		
			g.setColor(Color.blue);
			g.fillRect(t,p,11,11);
		for(i=0;i<m;i++){
			if(i==0){
				g.setColor(Color.blue);
		g.fillRect(x[i],y[i],11,11);
		}
		else{
			g.setColor(Color.blue);
			g.drawRect(x[i],y[i],10,10);
		}
		}
		if(press==1){
			for(i=m-1;i>0;i--){
				x[i]=x[i-1];
				y[i]=y[i-1];
		}
	if(flag==1 && dir!=4){
		
       x[0]=x[0]+11;
	   dir=1;
		
	}
	//Up
	else if(flag==2 && dir!=3){
		
		y[0]=y[0]-11;
		dir=2;
	}
	//Down
	else if(flag==3 && dir!=2){
		y[0]=y[0]+11;
        dir=3;		
	}
	//Left
	else if(flag==4 && dir!=1){
		x[0]=x[0]-11;
		dir=4;
	}
		}
		
	//incresing snake
	if((x[0]<=t+11&&x[0]>=t) && (y[0]>=p&&y[0]<=p+11)){
		m=m+1;
	
		Random rm=new Random();
	t=(Math.abs(rm.nextInt())%130)*10;
	p=(Math.abs(rm.nextInt())%60)*10;
	}
	if(x[0]>=1350 || x[0]<=0 || y[0]>=900 || y[0]<=0){
		game=0;
	}
	for(int k=2;k<m;k++){
		if(x[0]==x[k] && y[0]==y[k]){
		game=0;
		}
	}
	try{
		Thread.sleep(100);
	}
	
	catch(InterruptedException e){}
	repaint();
	}
	}
}
/*<applet code="B.class" width=1350 height=900></applet>*/