package Main;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Text class, controls the visuals of the program and updates the elements of the program by reading
 * the user's input and feeding it to the line class methods. 
 */
public class Text implements KeyListener {

    public static String AllText = "";
    private final static String newline = "\n";
    public static int cnt = 0;
    public static boolean addingLine = false;
    public static boolean choosingLine = false;
    public static boolean type = false;
    public static boolean choosingType = false;
    public static boolean gettingLine = false;
    public static boolean changingType = false;
    public static boolean changingLine = false;
    public static boolean gettingPoint = false;
    public static ArrayList<Line> lineList = new ArrayList<Line>();
    public static int current = 0;
    public static String lastMsg= "";
    public static Timer timer;
    public static int index;
    
    private SoundEffects clip;

    public static String input = "";

    public static JFrame frame;
    public static JPanel panel;
    public static JTextArea textArea;
    public static JScrollPane scroll;

    /*
     * Initializes all visual components of the program.
     */
    public Text() throws InterruptedException {
    	
    	clip = new SoundEffects();
    	
        textArea = new JTextArea("Press Enter ...", 30, 80);
        textArea.setCaretPosition(textArea.getText().length());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setLayout(new FlowLayout());
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.BLUE);
        textArea.setFont(new Font("Mechanic", Font.ITALIC, 15));
        textArea.setAutoscrolls(true);
        textArea.addKeyListener(this);
        scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVisible(true);
        textArea.setVisible(true);

        panel = new JPanel();
        panel.add(scroll);
        panel.setLayout(new GridLayout(1,1));
        panel.setBackground(Color.BLACK);
        panel.setVisible(true);

        frame = new JFrame("Line Pro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage((new ImageIcon(getClass().getResource("Hacker.png")).getImage()));
        frame.setResizable(false);
        frame.setBackground(Color.BLACK);
        frame.setLayout(new GridLayout(1,1));
        frame.add(panel);
        frame.addKeyListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        AllText = textArea.getText();

    }

    /*
     * Types a message on the monitor with delay between characters.
     */
    public void slowPrint(final String message) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
    	
        textArea.append("\n");
        AllText = textArea.getText();
        
        if(timer != null && timer.isRunning()) return;
        index = 0;

        timer = new javax.swing.Timer(10,new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                AllText = textArea.getText();
                textArea.setEditable(false);
                textArea.setText(textArea.getText() + String.valueOf(message.charAt(index)));
                index++;

                if (index >= message.length()) {
                    timer.stop();
                    SoundEffects.clip2.stop();
                    textArea.setEditable(true);
                }
                AllText = textArea.getText();
            }
        });
        timer.start();
        try {
			clip.Run2("Typing - Sound effect ( download ).wav");
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        SoundEffects.clip2.loop(Clip.LOOP_CONTINUOUSLY);
        AllText = textArea.getText();
        textArea.setCaretPosition(textArea.getText().length());
        AllText = textArea.getText();
    }

    @Override
    /*
     * Not used.
     */
    public void keyTyped(KeyEvent e) {

    }

    @Override
    /*
     * Checks to see the moments in which the enter key is pressed and
     * calls on a methods to update the content of the program.
     */
    public void keyPressed(KeyEvent e) {
    	
        if(e.getKeyCode() == KeyEvent.VK_ENTER && cnt == 0) {
            try {
				slowPrint("Welcome To Line Pro, To Begin Please Enter One Of The Following Commands:\nAdd Line: To Add A Line\nChange Line1 or Line2: To Change Line1 or Line2\nValues1 or Values2: To Get Values For Line1 or Line2\nCheck Point1 or Point2: To Check A Point For Line1 or Line2\nCheck Interception: To Check If Two Lines Intercept\nInfo: To Get Instructions On The Program\nExamples Of How To Enter Equations:\n3x+4y=26     6x-4y=19\n3x+4=y     9x+20=y" + newline + newline);
            } catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            cnt++;
            AllText = textArea.getText();
        }else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            cnt += 2;
            //input = textArea.getText().substring(textArea.getText().length() - (textArea.getText().length() - AllText.length()) + 1);
            input = textArea.getText().substring(textArea.getText().length() - (textArea.getText().length() - AllText.length()));
            if(input.equalsIgnoreCase("Add Line")) {
            	if(lineList.size() < 2) {
            		try {
						slowPrint("Choose the way in which you want to input the new line. Type SI for Slope-Intercept form or ABC for Standard Form: " + newline + newline);
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		choosingType = true;
            	} else {
            		try {
						slowPrint("There is already 2 lines added. " + newline + newline);
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            } else if(choosingType) {
            	if(input.equalsIgnoreCase("ABC")) {
            		type = true;
                    gettingLine = true;
                    choosingType = false;
                    try {
						slowPrint("Now enter the equation in the form of Ax+By=C : " + newline + newline);
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            	else if(input.equalsIgnoreCase("SI")) {
            		type = false;
            		gettingLine = true;
            		choosingType = false;
            		try {
						slowPrint("Now enter the equation in the form of mx+b=y : " + newline + newline);
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	} 
            	else {
            		try {
    					slowPrint("Wrong Information, Please Try Again\nYou wrote: " + input + newline + newline);
    				} catch (LineUnavailableException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				} catch (UnsupportedAudioFileException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				} catch (InterruptedException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
            	}
            } else if(gettingLine) {
            	try {
            		addLine(input);
                	gettingLine = false;
            	} catch (Exception e1) {
            		try {
    					slowPrint("Wrong Information, Please Try Again\nYou wrote: " + input + newline + newline);
    				} catch (LineUnavailableException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				} catch (IOException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				} catch (UnsupportedAudioFileException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				} catch (InterruptedException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				}
            	}
            } else if(input.equalsIgnoreCase("Change Line1")) {
            	current = 1;
            	try {
					slowPrint("You choosed to change line 1. Now enter in which form you want the new line. Type SI for Slope-Intercept Form or ABC for Standard Form. " + newline + newline);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	changingType = true;
            } else if(input.equalsIgnoreCase("Change Line2")) {
            	current = 2;
            	try {
					slowPrint("You choosed to change line 2. Now enter in which form you want the new line. Type SI for Slope-Intercept Form or ABC for Standard Form. " + newline + newline);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	changingType = true;
            } else if(changingType) {
            	if(input.equalsIgnoreCase("ABC")) {
            		type = true;
            		changingType = false;
            		changingLine = true;
            		try {
						slowPrint("You choosed the Standard Form. Now enter the new equation in the form Ax+By=C : " + newline + newline);
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            	else if(input.equalsIgnoreCase("SI")) {
            		type = false;
            		changingType = false;
            		changingLine = true;
            		try {
						slowPrint("You choosed the Slope-Intercept Form. Now enter the new equation in the form mx+b=y : " + newline + newline);
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            	else {
            		try {
    					slowPrint("Wrong Information, Please Try Again\nYou wrote: " + input + newline + newline);
    				} catch (LineUnavailableException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				} catch (UnsupportedAudioFileException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				} catch (InterruptedException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
            	}
            } else if(changingLine) {
            	try {
            		changeLine(input);
                	changingLine = false;
            	} catch (Exception e1) {
            		try {
    					slowPrint("Wrong Information, Please Try Again\nYou wrote: " + input + newline + newline);
    				} catch (LineUnavailableException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				} catch (IOException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				} catch (UnsupportedAudioFileException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				} catch (InterruptedException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				}
            	}
            } else if(input.equalsIgnoreCase("Values1")) {
            	current = 1;
            	try {
					showValue();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else if(input.equalsIgnoreCase("Values2")) {
            	current = 2;
            	try {
					showValue();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else if(input.equalsIgnoreCase("Check Point1")) {
            	current = 1;
            	try {
					slowPrint("You chooses to check a point for line 1. Now input the point in the form of x,y : " + newline + newline);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	gettingPoint = true;
            } else if(input.equalsIgnoreCase("Check Point2")) {
            	current = 2;
            	try {
					slowPrint("You chooses to check a point for line 2. Now input the point in the form of x,y : " + newline + newline);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
            	gettingPoint = true;
            } else if(gettingPoint) {
            	try {
            		checkPoint(input);
            		gettingPoint = false;
            	} catch(Exception e1) {
            		try {
    					slowPrint("Wrong Information, Please Try Again\nYou wrote: " + input + newline + newline);
    				} catch (LineUnavailableException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				} catch (IOException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				} catch (UnsupportedAudioFileException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				} catch (InterruptedException e11) {
    					// TODO Auto-generated catch block
    					e11.printStackTrace();
    				}
            	}
            } else if(input.equalsIgnoreCase("Check Interception")) {
            	try {
					checkInterception();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else if(input.equalsIgnoreCase("Info")) {
            	try {
					showInstructions();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                try {
					slowPrint("Wrong Information, Please Try Again\nYou wrote: " + input + newline + newline);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            AllText = textArea.getText();
        }

    }

    /*
     * Slowly prints the instructions on how to operate the program through the use of the slow print method.
     */
    private void showInstructions() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		
    	slowPrint("Please Enter One Of The Following Commands:\nAdd Line: To Add A Line\nChange Line1 or Line2: To Change A Line1 or Line2\nValues1 or Values2: To Get Values For Line1 or Line2\nCheck Point1 or Point2: To Check A Point For Line1 or Line2\nCheck Interception: To Check If Two Lines Intercept\nInfo: To Get Instructions On The Program\nExamples Of How To Enter Equations:\n3x+4y=26     6x-4y=19\n3x+4=y     9x+20=y" + newline + newline);
    	
	}

    /*
     * Checks if the lines intercept and slow prints the coordinates of the interception.
     */
	private void checkInterception() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		
    	if(lineList.get(0).intercepts(lineList.get(1))) {
    		slowPrint("The lines intercept at x = " + lineList.get(0).interception(lineList.get(1))[0] + " and y = " + lineList.get(0).interception(lineList.get(1))[1] + newline + newline);
    	} else {
    		slowPrint("Lines do not intercept." + newline + newline);
    	}
	}

	/*
	 * Checks where a point lands relative to one of the lines and prints out the information of the point.
	 */
	private void checkPoint(String input) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		
    	if(lineList.get(current - 1).isOnLine(Double.parseDouble(input.substring(0, input.indexOf(","))), Double.parseDouble(input.substring(input.indexOf(",") + 1)))) {
    		slowPrint("Point is on line " + current + newline + newline);
    	} else if(lineList.get(current - 1).checkPoint(Double.parseDouble(input.substring(0, input.indexOf(","))), Double.parseDouble(input.substring(input.indexOf(",") + 1)))) {
    		slowPrint("Point is above line " + current + newline + newline);
    	} else {
    		slowPrint("Point is below line " + current + newline + newline);
    	}
    	
	}

	/*
	 * Slow prints the values that corresponds on of the lines.
	 */
	private void showValue() throws Exception, IOException, UnsupportedAudioFileException, InterruptedException {
    	
    	slowPrint("A: " + lineList.get(current - 1).getA() + " B: " + lineList.get(current - 1).getB() + " C: " + lineList.get(current - 1).getC() + " Slope: " + lineList.get(current - 1).getSlope() + " y-Intercept: " + lineList.get(current - 1).getIntercept() + newline + newline);
    	
	}
	
	/*
	 * Changes the line to a different line by taking input from the user.
	 */

	private void changeLine(String input) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
		
    	if(type) {
    		lineList.set(current - 1, new Line(Double.parseDouble(input.substring(0, input.indexOf("x"))), Double.parseDouble(input.substring(input.indexOf("x") + 1, input.indexOf("y"))), Double.parseDouble(input.substring(input.indexOf("=") + 1))));
    	} else {
    		lineList.set(current - 1, new Line(Double.parseDouble(input.substring(0, input.indexOf("x"))), Double.parseDouble(input.substring(input.indexOf("x") + 1, input.indexOf("=")))));
    	}
    	
    	slowPrint("Line " + current + " has been changed to " + input + newline + newline);
    	
	}

	/*
	 * Adds a secondary line to the program by adding a line to the line list.
	 */
	private void addLine(String input) throws LineUnavailableException, IOException, UnsupportedAudioFileException, Exception {
		if(type) {
			lineList.add(new Line(Double.parseDouble(input.substring(0, input.indexOf("x"))), Double.parseDouble(input.substring(input.indexOf("x") + 1, input.indexOf("y"))), Double.parseDouble(input.substring(input.indexOf("=") + 1))));
		} else {
			lineList.add(new Line(Double.parseDouble(input.substring(0, input.indexOf("x"))), Double.parseDouble(input.substring(input.indexOf("x") + 1, input.indexOf("=")))));
		}
		slowPrint("The line " + input + " was added with success." + newline + newline);
	}

	@Override
	/*
	 * Not used.
	 */
    public void keyReleased(KeyEvent e) {

    }
    
    
}

