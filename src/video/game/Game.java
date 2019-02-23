package video.game;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

/**
 *
 * @author MarcelA00821875
 */
public class Game implements Runnable{
    
    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    String title;
    private int width;
    private int height;
    private Thread thread;              
    private boolean running;            //sets up the game
    private Bar bar;                    //use a bar
    private Ball ball;                  //use a ball
    private KeyManager keyManager;      //manages the keyboard
    
    public Game(String title, int width, int height){
    this.title = title;
    this.width = width;
    this.height = height;
    running = false;
    keyManager = new KeyManager();
    }
    
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    
    private void init(){
      display = new Display(title, getWidth(), getHeight());
      Assets.init();
      bar = new Bar(getWidth() / 2 - 50, getHeight() -50, 100, 25, this);
      ball = new Ball(getWidth() / 4, getHeight() -300, 40, 40, this);
      //starts to listen the keyboard input
      display.getJframe().addKeyListener(keyManager);
    }
    @Override
    public void run() {
        init();
        //frames per second
        int fps = 50;
        double timeTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while(running){
          now = System.nanoTime();
          //acumulates times in delta
          delta += (now - lastTime) / timeTick;
          lastTime = now;
          
          if(delta >= 1){
            tick();
            render();
            delta --;
          }
        }
        stop();
    }
    
    public KeyManager getKeyManager(){return keyManager;}
    
    private void tick() {
        keyManager.tick();
        //advance bar with collision
        bar.tick();
        ball.tick();
    }
    
    private void render() {
        //get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /*if its null, we define one with 3 buffers to display images of the game but 
        after clearing the Rectangle, getting the grapic object frome the buffer 
        strategy element. show the graphic and dispose it to the trash system.
        */
        if(bs == null){
          display.getCanvas().createBufferStrategy(3);
        }
        else{
          g = bs.getDrawGraphics();
          g.drawImage(Assets.background,0,0,width,height,null);
          bar.render(g);
          ball.render(g);
          // Prevents stutter on Linux.
          Toolkit.getDefaultToolkit().sync();
          bs.show();
          g.dispose();
        }
    }
    public synchronized void start(){
        if(!running){
          running = true;
          thread = new Thread(this);
          thread.start();
        }
    }
    public synchronized void stop(){
        if(running){
          running = false;
          try{
           //waits till thread dieas
           thread.join();
          } catch(InterruptedException ie){
            ie.printStackTrace();
          }
        }
    }   
}
