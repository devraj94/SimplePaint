/*
This a simple implementation of paint written in java.
for color buttons and geometric shapes buttons i've used a small pics of them(icons)..u can simly name the buttons.i've resized the pics to 20X20 pixels.
Author:K.Maharshi Devaraj
Date: 18-10-2013
*/

import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
public class paintassign {
        JFrame frame;
        JPanel panel;
        JFrame framesize;
        JTextField sizewidth;
        JTextField sizeheight;
        BufferedImage image1=null;
        Boolean save=false;
        JFrame frametext;
        JTextField text;
        JFrame thickner;
        JTextField thickno;
        Boolean drag=false;
        int present=2;
        int thicknor=1;
        Boolean filledselection=false;
        Color currentcolor=Color.black;
    public static void main(String [] args){
            paintassign a=new paintassign();
            a.go();
    }
    
    public void go(){
            final PAD drawPad=new PAD();
             frame=new JFrame();
             Container contain=frame.getContentPane();
             panel=new JPanel();
             GroupLayout layout=new GroupLayout(panel);
             panel.setLayout(layout);
             layout.setAutoCreateContainerGaps(true);
             layout.setAutoCreateGaps(true);
             Icon iconB = new ImageIcon("paintassignimgs/blue.gif");
                 Icon iconM = new ImageIcon("paintassignimgs/magenta.gif");
                 Icon iconR = new ImageIcon("paintassignimgs/red.gif");
                 Icon iconBl = new ImageIcon("paintassignimgs/black.gif");
                 Icon iconG = new ImageIcon("paintassignimgs/green.gif");
                 Icon iconY = new ImageIcon("paintassignimgs/yellow.gif");
                 Icon rectangle = new ImageIcon("paintassignimgs/rectangle.gif");
                 JButton rectangleButton = new JButton(rectangle);
                 Icon roundrect = new ImageIcon("paintassignimgs/roundrect.gif");
                 JButton roundrectButton = new JButton(roundrect);
                 Icon oval = new ImageIcon("paintassignimgs/oval.gif");
                 JButton ovalButton = new JButton(oval);
                 Icon triangle = new ImageIcon("paintassignimgs/triangle.gif");
                 JButton triangleButton = new JButton(triangle);
                 Icon rhombus = new ImageIcon("paintassignimgs/rhombus.gif");
                 JButton rhombusButton = new JButton(rhombus);
                 Icon righttriangle = new ImageIcon("paintassignimgs/righttriangle.gif");
                 JButton righttriangleButton = new JButton(righttriangle);
                 Icon strightline = new ImageIcon("paintassignimgs/strightline.gif");
                 JButton strightlineButton = new JButton(strightline);
                 Icon curved = new ImageIcon("paintassignimgs/curved.gif");
                 JButton curvedButton = new JButton(curved);
                 JButton clearButton = new JButton("Clear");
                 JButton openimageButton = new JButton("new image");
                 clearButton.setToolTipText("Keyboard: Shift + C");
                 JButton saveButton = new JButton("Save");
                 saveButton.setToolTipText("KeyBoard : Ctrl + S");
                 Icon eraser = new ImageIcon("paintassignimgs/eraser.gif");
                 JButton eraserButton = new JButton(eraser);
                 Icon Text = new ImageIcon("paintassignimgs/Text.gif");
                 JButton textButton = new JButton(Text);
                 JButton resizeButton = new JButton("Resize");
                 Icon colorselect = new ImageIcon("paintassignimgs/colorselect.png");
                 final JButton colorButton = new JButton(colorselect);
                 
         panel.getActionMap().put("foo", new AbstractAction() {
             public void actionPerformed(ActionEvent e) {
                 JFileChooser chooser = new JFileChooser(  );
                 chooser.setFileSelectionMode( JFileChooser.CUSTOM_DIALOG);
                 chooser.showSaveDialog( null );
                 File file=chooser.getSelectedFile() ;
                 drawPad.save(file);
             }
         });
         panel.getActionMap().put("fooclear", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               drawPad.clear();
            }
        });
         panel.getActionMap().put("fooexit", new AbstractAction() {
             public void actionPerformed(ActionEvent e) {
            	 if(!save){
            		  int result = JOptionPane.showConfirmDialog(
                              frame,
                              "Are you sure you want to exit the application?",
                              "Exit Application",
                              JOptionPane.YES_NO_OPTION);

                          if (result == JOptionPane.YES_OPTION){
                         	 frame.setVisible(false);
                              frame.dispose();
                              System.exit(0);
                          }
            	 }
             }
         });
                  
                  InputMap inputMap = panel.getInputMap();
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "foo");
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_DOWN_MASK), "fooclear");
		         inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "fooexit");
		                 
                 String []thickmeasure={"1","2","3","5","10","15","own"};
                 final JComboBox thickness=new JComboBox(thickmeasure);
                 thickness.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                int no=1;
                                String s = (String) thickness.getSelectedItem();
                                if(s.compareTo("own")==0){
                                        no=1;
                                }else{
                                        no=Integer.parseInt(s);
                                }
                                drawPad.setthickness(s,no);
                        }
                });
                 
                 String []Type={"Bordered","Filled"};
                 final JComboBox type=new JComboBox(Type);
                 type.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                String s = (String) type.getSelectedItem();
                                if(s.compareTo("Filled")==0){
                                        filledselection=true;
                                }else{
                                        filledselection=false;
                                }
                        }
                });
                 
                    
                 
                 colorButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                Color newcolor=JColorChooser.showDialog(colorButton, "Choose Your color", currentcolor);
                        if(newcolor!=null){
                                currentcolor=newcolor;
                                drawPad.colorselect(newcolor);
                        }
                
                        }
                });
                 
                 strightlineButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                present=1;
                        }
                });
                 
                 curvedButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                present=2;
                        }
                });
                 
                 ovalButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                present=3;
                        }
                });
                 
                 rectangleButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                present=4;
                        }
                });
                 
                 roundrectButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                present=5;
                        }
                });
                 
                 triangleButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                present=8;
                        }
                });
                 
                 righttriangleButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                present=9;
                        }
                });
                 
                 rhombusButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                present=10;
                        }
                });
                 eraserButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                present=11;
                                drawPad.white();
                        }
                });
                 textButton.addActionListener(new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                             present=12;
                     }
             });
                 
                clearButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                drawPad.clear();
                        }
                });
                saveButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                JFileChooser chooser = new JFileChooser(  );
                chooser.setFileSelectionMode( JFileChooser.CUSTOM_DIALOG);
                chooser.showSaveDialog( null );
                File file=chooser.getSelectedFile() ;
                drawPad.save(file);
                        }
                });
             
                openimageButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                            JFileChooser chooser = new JFileChooser(  );
	            chooser.setFileSelectionMode( JFileChooser.OPEN_DIALOG);
	            chooser.showOpenDialog( null );
	            File file=chooser.getSelectedFile() ;
	            drawPad.openimage(file);
                    }
                 });
                JButton redButton = new JButton(iconR);
                redButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                drawPad.red();
                        }

                });
                JButton yellowButton = new JButton(iconY);
                yellowButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                drawPad.yellow();
                        }

                });
                
                JButton blackButton = new JButton(iconBl);
                blackButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                drawPad.black();
                        }
                });
                
                JButton magentaButton = new JButton(iconM);
                magentaButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                drawPad.magenta();
                        }
                });
                
                JButton blueButton = new JButton(iconB);
                blueButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                drawPad.blue();
                        }
                });
                
                JButton greenButton = new JButton(iconG);
                greenButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                drawPad.green();
                        }
                });
                
                resizeButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                            drawPad.sizechange();
                    }
                   });
                
                blackButton.setPreferredSize(new Dimension(18,18));
                magentaButton.setPreferredSize(new Dimension(18, 18));
                redButton.setPreferredSize(new Dimension(18, 18));
                blueButton.setPreferredSize(new Dimension(18, 18));
                greenButton.setPreferredSize(new Dimension(18,18));
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10,10,10)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(redButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(greenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(blackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yellowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(magentaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(colorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10,10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(thickness, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rhombusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(righttriangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(strightlineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ovalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(triangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE)
                            .addComponent(curvedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rectangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eraserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)                                
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(roundrectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(openimageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10,10,10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(magentaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(redButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(blueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(strightlineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(curvedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ovalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rectangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eraserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(resizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(blackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(greenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(yellowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(thickness, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rhombusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(righttriangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(triangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(roundrectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(openimageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10,10,10)
                                .addComponent(colorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                       
                        .addContainerGap())
                );
             contain.add(BorderLayout.NORTH,panel);
             contain.add(BorderLayout.CENTER,drawPad);
             frame.setSize(1000,600);
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
    }
    
    class PAD extends JComponent{
                private static final long serialVersionUID = 1L;
                java.awt.Image image;
            Graphics2D graphics2D;
            Graphics2D dupegraphics2D;
            int currentX;
                int currentY;
                int oldX;
                int oldY;
                int prevX;
                int prevY;
            public PAD(){
                    setDoubleBuffered(false);
                    addMouseListener(new MouseAdapter(){
                            public void mousePressed(MouseEvent e){
                                    oldX = e.getX();
                                    oldY = e.getY();
                                    if(present==12){
                                    	write(oldX,oldY);
                                    	repaint();
                                    }
                            }
                    });
                    addMouseMotionListener(new MouseMotionAdapter(){
                            public void mouseDragged(MouseEvent e){
                                    drag=true;
                                     currentX = e.getX();
                             currentY = e.getY();
                             if (present==2) {
                                if(graphics2D!=null){
                                        graphics2D.drawLine(oldX, oldY, currentX, currentY);
                                        repaint();
                                                oldX = currentX;
                                                oldY = currentY;
                                }
                             }else if (present==11) {
                                     graphics2D.setColor(Color.white);
                                if(graphics2D!=null){
                                        graphics2D.drawLine(oldX, oldY, currentX, currentY);
                                        repaint();
                                        graphics2D.setColor(currentcolor);
                                                oldX = currentX;
                                                oldY = currentY;
                                }
                             }
                             else {
                                repaint();
                             }
                             prevX = currentX;
                             prevY = currentY;
                                    
                            }

                    });
            addMouseListener(new MouseAdapter(){
                            public void mouseReleased(MouseEvent e){
                                    if(drag && !filledselection){
                                            drag=false;
                                    int x1=oldX;
                                    int y1=oldY;
                                    int x2=currentX;
                                    int y2=currentY;
                                        int len=Math.abs(currentX-oldX);
                                int height=Math.abs(currentY-oldY);
                                if(present==3 || present==4 || present==5){
                                         if (x2 < x1) {  
                                         int temp = x1;
                                         x1 = x2;
                                         x2 = temp;
                                      }
                                      if (y2 < y1) {  
                                         int temp = y1;
                                         y1 = y2;
                                         y2 = temp;
                                      }
                                }
                                        if(present==1){
                                        graphics2D.drawLine(oldX, oldY, currentX, currentY);
                                        }
                                        else if(present==3){
                                        graphics2D.drawOval(x1, y1,x2-x1,y2-y1);
                                }else if(present==4){
                                        graphics2D.drawRect(x1, y1, x2-x1,y2-y1);
                                }else if(present==5){
                                        graphics2D.drawRoundRect(x1, y1,x2-x1,y2-y1, Math.abs(oldX-currentX)/10, Math.abs(oldX-currentX)/10);
                                }else if(present==8){
                                        int [] xs={oldX+len/2,oldX,currentX};
                                        int ys[]={oldY,currentY,currentY};
                                                graphics2D.drawPolygon(xs, ys, 3);
                                        }
                                else if(present==9){
                                        int [] xs={oldX,oldX,currentX};
                                        int ys[]={oldY,currentY,currentY};
                                                graphics2D.drawPolygon(xs, ys, 3);
                                }else if(present==10){
                                        int [] xs={oldX+len/2,oldX,oldX+len/2,currentX};
                                        int ys[]={oldY,oldY+height/2,currentY,oldY+height/2};
                                                graphics2D.drawPolygon(xs, ys, 4);
                                }
                                    repaint();
                                 
                                    }else if(drag &&filledselection){
                                            drag=false;
                                            int x1=oldX;
                                    int y1=oldY;
                                    int x2=currentX;
                                    int y2=currentY;
                                        int len=Math.abs(currentX-oldX);
                                int height=Math.abs(currentY-oldY);
                                if(present==3 || present==4 || present==5){
                                         if (x2 < x1) {  
                                         int temp = x1;
                                         x1 = x2;
                                         x2 = temp;
                                      }
                                      if (y2 < y1) {  
                                         int temp = y1;
                                         y1 = y2;
                                         y2 = temp;
                                      }
                                }
                                        if(present==1){
                                        graphics2D.drawLine(oldX, oldY, currentX, currentY);
                                        }
                                        else if(present==3){
                                        graphics2D.fillOval(x1, y1,x2-x1,y2-y1);
                                }else if(present==4){
                                        graphics2D.fillRect(x1, y1,x2-x1,y2-y1);
                                }else if(present==5){
                                        graphics2D.fillRoundRect(x1, y1,x2-x1,y2-y1, Math.abs(oldX-currentX)/10, Math.abs(oldX-currentX)/10);
                                }else if(present==8){
                                        int [] xs={oldX+len/2,oldX,currentX};
                                        int ys[]={oldY,currentY,currentY};
                                                graphics2D.fillPolygon(xs, ys, 3);
                                        }
                                else if(present==9){
                                        int [] xs={oldX,oldX,currentX};
                                        int ys[]={oldY,currentY,currentY};
                                                graphics2D.fillPolygon(xs, ys, 3);
                                }else if(present==10){
                                        int [] xs={oldX+len/2,oldX,oldX+len/2,currentX};
                                        int ys[]={oldY,oldY+height/2,currentY,oldY+height/2};
                                                graphics2D.fillPolygon(xs, ys, 4);
                                }
                                    repaint();
                                 
                                    }
                                    
                            }
                    });   
            }

            public void paintComponent(Graphics g){
            	 if(image == null){
                     image = createImage(getSize().width, getSize().height); 
                 graphics2D = (Graphics2D)image.getGraphics();
                 graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                 clear();
         }
                     
                g.drawImage(image, 0, 0, null); 
                 if (drag) {
                         Graphics2D gf=(Graphics2D)g;
                         gf.setColor(currentcolor);
                         gf.setStroke(new BasicStroke(thicknor));
                         chooseone(gf);
              }
                            save=false;
            }
    
        public void chooseone(Graphics2D gf){
                int x1=oldX;
                    int y1=oldY;
                    int x2=currentX;
                    int y2=currentY;
                        int len=Math.abs(currentX-oldX);
                int height=Math.abs(currentY-oldY);
                if(present==3 || present==4 || present==5){
                         if (x2 < x1) {  
                         int temp = x1;
                         x1 = x2;
                         x2 = temp;
                      }
                      if (y2 < y1) {  
                         int temp = y1;
                         y1 = y2;
                         y2 = temp;
                      }
                }
                if(filledselection){
                        if(present==1){
                            gf.drawLine(oldX, oldY, currentX, currentY);
                    }else if(present==3){
                            gf.fillOval(x1, y1,x2-x1,y2-y1);
                    }else if(present==4){
                            gf.fillRect(x1, y1,x2-x1,y2-y1);
                    }else if(present==5){
                            gf.fillRoundRect(x1, y1,x2-x1,y2-y1, Math.abs(oldX-currentX)/10, Math.abs(oldX-currentX)/10);
                    }else if(present==8){
                            int [] xs={oldX+len/2,oldX,currentX};
                            int ys[]={oldY,currentY,currentY};
                                    gf.fillPolygon(xs, ys, 3);
                            }
                    else if(present==9){
                            int [] xs={oldX,oldX,currentX};
                            int ys[]={oldY,currentY,currentY};
                                    gf.fillPolygon(xs, ys, 3);
                    }else if(present==10){
                            int [] xs={oldX+len/2,oldX,oldX+len/2,currentX};
                            int ys[]={oldY,oldY+height/2,currentY,oldY+height/2};
                                    gf.fillPolygon(xs, ys, 4);
                    }
                }else{
                        if(present==1){
                            gf.drawLine(oldX, oldY, currentX, currentY);
                    }else if(present==3){
                            gf.drawOval(x1, y1,x2-x1,y2-y1);
                    }else if(present==4){
                            gf.drawRect(x1, y1,x2-x1,y2-y1);
                    }else if(present==5){
                            gf.drawRoundRect(x1, y1,x2-x1,y2-y1, Math.abs(oldX-currentX)/10, Math.abs(oldX-currentX)/10);
                    }else if(present==8){
                            int [] xs={oldX+len/2,oldX,currentX};
                            int ys[]={oldY,currentY,currentY};
                                    gf.drawPolygon(xs, ys, 3);
                            }
                    else if(present==9){
                            int [] xs={oldX,oldX,currentX};
                            int ys[]={oldY,currentY,currentY};
                                    gf.drawPolygon(xs, ys, 3);
                    }else if(present==10){
                            int [] xs={oldX+len/2,oldX,oldX+len/2,currentX};
                            int ys[]={oldY,oldY+height/2,currentY,oldY+height/2};
                                    gf.drawPolygon(xs, ys, 4);
                    }
                }
        }
            public void clear(){
                    graphics2D.setPaint(Color.white);
                    graphics2D.fillRect(0, 0, getSize().width, getSize().height);
                    graphics2D.setPaint(Color.black);
                    currentcolor=Color.black;
                    repaint();
                    save=true;
            }
            
            public void save(File file){
                    try {
                            String s=file.toString();
                            s=s+".png";
                      ImageIO.write((RenderedImage)image, "png", new File(s));
                      System.out.println("panel saved as image");
                      save=true;
                  } catch (Exception e) {
                      System.out.println("panel not saved" + e.getMessage());
                  }
            }
            public void setthickness(String s,int fno){
                    if(s.compareTo("own")==0){
                             thickner=new JFrame();
                             thickno=new JTextField();
                         thickner.getContentPane().add(BorderLayout.CENTER,thickno);
                             thickner.setSize(150,70);
                       thickner.setVisible(true);
                       ActionListener listen =new ActionListener(){
                                public void actionPerformed(ActionEvent event) {
                                        String ner=thickno.getText();
                                        setthickness(ner,Integer.parseInt(ner));
                                        thicknor=Integer.parseInt(ner);
                                        thickner.setVisible(false);
                                }
                       };
                           thickno.addActionListener(listen);
                    }
                    graphics2D.setStroke(new BasicStroke(fno));
                   
           }
    
            public void colorselect(Color colornow){
                    graphics2D.setPaint(colornow);
                    repaint();
            }
            
            public void white(){
                    graphics2D.setPaint(Color.white);
                    repaint();
            }
            
            public void red(){
                    graphics2D.setPaint(Color.red);
                    currentcolor=Color.red;
                    repaint();
            }
            
            public void black(){
                    graphics2D.setPaint(Color.black);
                    currentcolor=Color.black;
                    repaint();
            }
            public void yellow(){
                    graphics2D.setPaint(Color.yellow);
                    currentcolor=Color.yellow;
                    repaint();
            }
            public void magenta(){
                    graphics2D.setPaint(Color.magenta);
                    currentcolor=Color.magenta;
                    repaint();
            }
            public void blue(){
                    graphics2D.setPaint(Color.blue);
                    currentcolor=Color.blue;
                    repaint();
            }
            public void green(){
                    graphics2D.setPaint(Color.green);
                    currentcolor=Color.green;
                    repaint();
            }
            public void write(int x,int y){
            	frametext=new JFrame();
				  text=new JTextField();
				  text.addActionListener(new textlisten(this,x,y));
			      frametext.getContentPane().add(BorderLayout.CENTER,text);
			 	 frametext.setSize(100,70);
			    frametext.setVisible(true);
                repaint();
        }
            
            public void openimage(File file){
            	clear();
            	String path=file.toString();
                try{
                    image1 = ImageIO.read(new File(path)); 
           		 }catch(Exception e){
           			 e.printStackTrace();
           		 }
                image = createImage(image1.getWidth(),image1.getHeight()); 
                graphics2D = (Graphics2D)image.getGraphics();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
               
                clear();
                graphics2D.drawImage(image1, 0, 0, this);
                repaint();
            }
            
            public void inserttext(String s,int x,int y){
            	graphics2D.drawString(s, x, y);
            	repaint();
            }
            
            public void sizechange(){
            	framesize=new JFrame();
				  sizewidth=new JTextField("width.......");
				  sizeheight=new JTextField("height......");
				  JButton confirm=new JButton("proceed");
				  confirm.addActionListener(new croplisten(this));
				  framesize.getContentPane().add(BorderLayout.WEST,sizewidth);
				  framesize.getContentPane().add(BorderLayout.CENTER,sizeheight);
				  framesize.getContentPane().add(BorderLayout.EAST,confirm);
			 	 framesize.setSize(250,60);
			    framesize.setVisible(true);
            }
            
            public void cropping(int x,int y){
            	   graphics2D.setPaint(Color.white);
                   graphics2D.fillRect(0, 0, image1.getWidth(), image1.getHeight());
                   graphics2D.setPaint(Color.black);
                   currentcolor=Color.black;
                   repaint();
                   image = createImage(x,y); 
                   graphics2D = (Graphics2D)image.getGraphics();
                   graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                   clear();
                   graphics2D.drawImage(image1, 0, 0, x, y, null);
                   save=true;
            }

    }
    class textlisten implements ActionListener{
    	PAD sd=new PAD();
    	int x1,y1;
    	textlisten(PAD s,int x,int y){
    		sd=s;
    		x1=x;
    		y1=y;
    	}
    	
  	  public void actionPerformed(ActionEvent event) {
  		 
  	  String spde=text.getText();
  	  frametext.dispose();
  	  sd.inserttext(spde,x1,y1);
  	  }
    }
    
    class croplisten implements ActionListener{
    	PAD sd=new PAD();
    	int x1,y1;
    	croplisten(PAD sdf){
    		sd=sdf;
    	}
    	public void actionPerformed(ActionEvent event) {
    		x1=Integer.parseInt(sizewidth.getText());
    		y1=Integer.parseInt(sizeheight.getText());
    		 framesize.dispose();
     		 sd.cropping(x1,y1); 
    	  	  }
    	    }
  
  
}
