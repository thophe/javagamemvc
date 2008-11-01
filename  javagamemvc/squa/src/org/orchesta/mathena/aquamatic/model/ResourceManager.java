/**
 * This is a java version of the game "Aquamatics" migrated from MacOS
 * Copyright (C) 2006  You XU 
 * Mathematics Department, Nanjing University
 * 03/30/2006
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *	
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * @author You XU
 * It is straitforward that ResourceManager must be a singleton
 *
 */

package org.orchesta.mathena.aquamatic.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;

import javax.imageio.ImageIO;


public class ResourceManager {
	
	private ResourceManager () {}
		
	private static ResourceManager rm = new ResourceManager();
	
	public static ResourceManager getInstance()
	{
		return rm;
	}
	
	
	
	public BufferedImage getEmptyMap() {
		BufferedImage background = new BufferedImage(15*30, 15*30, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D gb = background.createGraphics();
		gb.setColor(new Color(180,180,180));
		gb.drawRoundRect(5,5,440,440 ,10,10);
		gb.drawRoundRect(5,6,440,439, 10,11);
		gb.drawRoundRect(5,7,440,438, 11,11);
		return background;
	}
	
	public BufferedImage getAtom(int index)
	{
		return (BufferedImage) atomHash.get(new Integer(index));
	}
	
	public  String getLocalizedMsg(Locale l) 
	{
		if (l.equals(Locale.CHINESE)) return ("Moved Steps:"); 
		else return ("Moved: ");	
	}
	
	/**
	 * 
	 * @param map
	 */
	public BufferedImage getGoal() {
		int [][] goal= map.getMolecular();
		int moleLength = goal[0].length;
		int moleNumber = goal.length;
		BufferedImage molemap = new BufferedImage(moleLength*30+30,moleNumber*30+30,BufferedImage.TYPE_4BYTE_ABGR);
		Graphics moleg = molemap.getGraphics();
		Canvas ca = new Canvas();
		for (int k =0; k< moleNumber; k++)
			for (int y = 0; y<goal[k].length; y++)
				moleg.drawImage(map.getRm().getAtom(goal[k][y]), 30* y, 30*k, new Color(255,255,255, 0) , ca);
		return molemap;
	}
	
	public GameMap getGameMap() {
		// Need not be singleton, I can access GameMap here
		return map;
	}
	
	public GameMap getGameMap(String level) {
		atomHash.clear();
		map.setFail();
		try {
			File f = new File("resources/abilder.png");
    		BufferedImage bi = ImageIO.read(f);   		    		
			File f2 = new File("levels/level_"+level);
	 		BufferedReader br = new BufferedReader(new FileReader(f2));
	 		String tag;
			int rowNumber =0 , moleNumber=0; 
			String[] mole = new String[15];	
			while ((tag = br.readLine())!=null)
			{
				//System.out.println(tag);
				char inital = tag.charAt(0);
				switch (inital) {
					case '[': break; 	//[Level]
					
					case 'N': {map.setName(tag.substring(5)); continue; } //Name
					
					case 'a': {

						// Create an empty image;
						BufferedImage empty = new BufferedImage(30,30,BufferedImage.TYPE_4BYTE_ABGR);
			    		Graphics2D g = empty.createGraphics();
			    		Canvas c = new Canvas();
			    		// Find the right atom;
			    		BufferedImage atom;
			    		int id = (int)tag.charAt(5);
			    		if (tag.charAt(7)<='D'&&tag.charAt(7)>='A') {
			    			int ai = (int)(tag.charAt(7)-'A');
			    			atom = bi.getSubimage(62+31*ai, 93, 30,30);
			    		}
			    		
			    		else if (tag.charAt(7)=='o') {
			    			//System.out.println("THIS IS AI:"+ai);
			    			atom = bi.getSubimage(31,31*3, 30, 30);	
			    		} else {
			    			int ai = (int)(tag.charAt(7)-'1');
			    			//System.out.println("THIS IS AI IN number:"+ai);
			    			atom = bi.getSubimage(ai*31,0,30,30);
				    	}
						
						g.drawImage(atom, 0, 0, new Color(255,255,255, 0) , c);
						
						int i = tag.length()-9;
						while (i>0) {
							char k = tag.charAt(8+i);
							
							if (k>='a')
							{	
								int ki = (int) (k-'a');
								BufferedImage affix = bi.getSubimage(31*ki, 31, 30, 30);
			    				g.drawImage(affix, 0, 0, new Color(255,255,255, 0), c);
								i--;
							} else {
								int ki = (int) (k-'A');
								BufferedImage affix = bi.getSubimage(31*ki, 62, 30, 30);
			    				g.drawImage(affix, 0, 0, new Color(255,255,255, 0), c);
								i--;
							}
						}
						
						//System.out.println(id + "::::ID");
						putAtom(id, empty.getSubimage(0,0,30,30));
						// PUT empty to a hashtable;
						continue;
					}	
				
					case 'f': {
						for (int i =0; i<15; i++) 
							map.setMap(rowNumber,i, tag.charAt(i+8));
						
						rowNumber++;
						continue;
					}
					
					case 'm': {
						mole[moleNumber] = tag.substring(7);
						moleNumber++;
					}
				}
						
			}
			int moleLength = mole[0].length();
			int[][] goal = new int[moleNumber][moleLength]; 
			for (int k =0; k< moleNumber; k++)
				{
					for (int y = 0; y<mole[k].length(); y++)
					{
						goal[k][y] = (int)mole[k].charAt(y);
						//System.out.print((char)map[k][y]);
					}
					//System.out.println("");
				}
						
			map.setMolecular(goal);
			// The goal;
			putAtom(GameMap.BLANK, bi.getSubimage(186,93,30,30));
			putAtom(GameMap.WALL, bi.getSubimage(9*31, 31,30, 30));
			putAtom(GameMap.UP, bi.getSubimage(8*31, 62, 30, 30)); //UP
			putAtom(GameMap.DOWN, bi.getSubimage(8*31, 93, 30,30));	 //DOWN
			putAtom(GameMap.LEFT, bi.getSubimage(7*31, 93, 30,30));  //LEFT
			putAtom(GameMap.RIGHT, bi.getSubimage(9*31, 93,30, 30)); //RIGHT
		}catch(IOException e) {e.printStackTrace();}
		map.setRm(this);
		return map;
	}
	
	private GameMap map = new GameMap();	
	private Hashtable atomHash = new Hashtable();
	private void putAtom(int index, BufferedImage bi)
	{
		atomHash.put(new Integer(index), bi);
	}

}
