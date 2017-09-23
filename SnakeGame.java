import java.awt.*;
import java.awt.event.*;

class SnakeGame extends Frame implements KeyListener
{
    Label lb;
    int a, b, c, d;
    int sx, sy, fx, fy;   // starting & final( front) point pixels
    int l;
    int todo;
    int noaction;
    int px, py;   //  random point pixel
    int tx, ty;    // temporary point in snake
    int cr;
    int score;


    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    public SnakeGame(String s)
    {
        super(s);
        setSize(500, 500);
        setBackground(new Color(143, 183, 255));
        setLayout(new FlowLayout());
        lb = new Label("Snake Game For You ..");
        add(lb);
        setLocation(300, 100);

        a = b = 60;
        c = d = 400;
        sx = sy = 80;
        fx = sx + 10;
        fy = sy;
        l = 10;
        addKeyListener(this);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });
        setResizable(false);
        setVisible(true);
    }


    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(a, b, c, d);

        g.setColor(Color.WHITE);
        g.drawLine(sx, sy, fx, fy);

        g.setColor(Color.BLACK);
        g.drawString("score =" + score, 90, 480);
        if (cr == 0)
        {
            cr = 1;
            random();
        }

        g.setColor(Color.WHITE);
        g.drawLine(px, py, px, py);


//    repetetive code fragment Beginning

        if (fx == sx)
        {
            if (fy > sy)
                ty = sy;
            else
                ty = fy;
            int add = 0;
            while (add <= l)
            {
                if (fx == px && ty + add == py)
                {
                    cr = 0;
                    l = l + 5;
                    score = score + 10;
                    if (fx == sx)
                    {
                        if (fy > sy)
                            fy = fy + 5;
                        else
                            fy = fy - 5;
                    } else if (fy == sy)
                    {
                        if (fx > sx)
                            fx = fx + 5;
                        else
                            fx = fx - 5;
                    }
                }
                add++;
            }

        }
        if (fy == sy)
        {
            if (fx > sx)
                tx = sx;
            else
                tx = fx;

            int add = 0;
            while (add <= l)
            {
                if (tx + add == px && fy == py)
                {
                    cr = 0;
                    l = l + 5;
                    score = score + 10;
                    if (fx == sx)
                    {
                        if (fy > sy)
                            fy = fy + 5;
                        else
                            fy = fy - 5;
                    } else if (fy == sy)
                    {
                        if (fx > sx)
                            fx = fx + 5;
                        else
                            fx = fx - 5;
                    }
                }
                add++;
            }

        }
//    repetetive code fragment end

        if (todo == 0)
            start();
        else if (todo == 1)
            loopYchange();
        else if (todo == 2)
            loopXchange();
        else if (todo == 100)
            g.drawString("OOPS! Game over..You scored=>  " + score, a + 100, b + d / 2);

    }


    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    public void keyPressed(KeyEvent ke)
    {
        if (noaction != 1)
        {
            int key = ke.getKeyCode();
            switch (key)
            {
                case KeyEvent.VK_UP:
                    if (sy == fy)
                    {
                        if (fx > sx)
                            sx = sx + l;
                        else
                            sx = sx - l;

                        fy = fy - l;
                        todo = 1;
                        repaint();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (sy == fy)
                    {
                        if (fx > sx)
                            sx = sx + l;
                        else
                            sx = sx - l;

                        fy = fy + l;
                        todo = 1;
                        repaint();
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (sx == fx)
                    {
                        if (fy > sy)
                            sy = sy + l;
                        else
                            sy = sy - l;

                        fx = fx - l;
                        todo = 2;
                        repaint();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (sx == fx)
                    {
                        if (fy > sy)
                            sy = sy + l;
                        else
                            sy = sy - l;

                        fx = fx + l;
                        todo = 2;
                        repaint();
                    }
                    break;
            }
        } else
        {
            dispose();
            new SnakeGame("Surendra Productions");
        }
    }




    public void start()
    {
        if (fx >= c + a)
            stop();

        else
        {
            fx++;
            sx++;
        }
        try
        {
            Thread.sleep(50);
        } catch (Exception e)
        {
        }
        repaint();
    }


    public void loopYchange()
    {

        if (sy - fy > 0)
        {
            if (fy <= b)
                stop();
            else
            {
                fy--;
                sy--;
            }
        } else
        {
            if (fy >= b + d)
                stop();
            else
            {
                fy++;
                sy++;
            }
        }

        try
        {
            Thread.sleep(50);
        } catch (Exception e)
        {
        }
        repaint();

    }


    public void loopXchange()
    {
        if (fx - sx > 0)
        {
            if (fx >= c + a)
                stop();
            else
            {
                fx++;
                sx++;
            }
        } else
        {
            if (fx <= a)
                stop();
            else
            {
                fx--;
                sx--;
            }
        }

        try
        {
            Thread.sleep(50);
        } catch (Exception e)
        {
        }
        repaint();
    }


    public void stop()
    {
        noaction = 1;
        todo = 100;
        repaint();
    }

    public void random()
    {
        java.util.Random rd = new java.util.Random();
        int rx = rd.nextInt(c - a) + 1;
        px = rx + a;
        int ry = rd.nextInt(d - b) + 1;
        py = ry + b;

    }

    public static void main(String args[])
    {
        new SnakeGame("Surendra Productions");
    }
}

