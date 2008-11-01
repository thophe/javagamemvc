package org.orchesta.mathena.aquamatic.view;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.orchesta.mathena.aquamatic.model.GameMap;

public class GameMapUI extends JLabel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameMap map;
	private BufferedImage molecular;

	/**
	 * This is the constructor of a View and takes a Model as it argument
	 * 
	 * @param map
	 */

	public GameMapUI(GameMap map) {
		this.map = map;
		// create the link to the Model
		map.addObserver(this);

	}

	/**
	 * This method is called when the Model changes its data. Hence it should
	 * repaint the screen of this View.
	 */
	public void update(Observable o, Object arg) {
		BufferedImage background = map.getRm().getEmptyMap();
		this.setIcon(new ImageIcon(this.getImageMap(background)));
		if (!map.isSuccess()) // GameSuccess Like Google Style
		{
			this.setHorizontalTextPosition(JLabel.CENTER);
			this
					.setText("<HTML><B><H1><I><FONT COLOR='BLUE'>S</FONT>"
							+ "<FONT COLOR='RED'>U</FONT><FONT COLOR='YELLOW'>CC</FONT>"
							+ "<FONT COLOR='BLUE'>E</FONT><FONT COLOR='GREEN'>S</FONT>"
							+ "<FONT COLOR='RED'>S</FONT></I></H1></B></HTML>");
		}
	}

	/**
	 * 
	 * @param bi
	 * @return
	 */
	public BufferedImage getImageMap(BufferedImage bi) {
		Graphics2D g = bi.createGraphics();
		Canvas c = new Canvas();
		int[][] intMap = map.getMap();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				g.drawImage(map.getRm().getAtom(intMap[i][j]), j * 30, i * 30,
						c);
			}
		}
		return bi;
	}

	public BufferedImage getMolecular() {
		return molecular;
	}

}
